package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlAttribute;
import com.reandroid.arsc.chunk.xml.ResXmlElement;
import com.reandroid.arsc.chunk.xml.ResXmlNode;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.model.FrameworkTable;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.CompoundEntry;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResTableEntry;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.arsc.value.ValueType;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FrameworkOptimizer {
    private APKLogger apkLogger;
    private final ApkModule frameworkApk;
    private boolean mOptimizing;

    public FrameworkOptimizer(ApkModule apkModule) {
        this.frameworkApk = apkModule;
        this.apkLogger = apkModule.getApkLogger();
    }

    private void backupAttributeValues(TableBlock tableBlock, ResXmlAttribute resXmlAttribute) {
        Entry entryWithValue;
        if (resXmlAttribute == null) {
            return;
        }
        ValueType valueType = resXmlAttribute.getValueType();
        if ((valueType != ValueType.REFERENCE && valueType != ValueType.ATTRIBUTE) || (entryWithValue = getEntryWithValue(tableBlock, resXmlAttribute.getData())) == null || isReferenceEntry(entryWithValue) || entryWithValue.isComplex()) {
            return;
        }
        ResValue value = entryWithValue.getTableEntry().getValue();
        ValueType valueType2 = value.getValueType();
        if (valueType2 == ValueType.STRING) {
            resXmlAttribute.setValueAsString(value.getValueAsString());
        } else {
            resXmlAttribute.setTypeAndData(valueType2, value.getData());
        }
    }

    private void backupManifestValue(AndroidManifestBlock androidManifestBlock, TableBlock tableBlock) {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        int data;
        logMessage("Backup manifest values ...");
        ResXmlElement applicationElement = androidManifestBlock.getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_icon)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.REFERENCE) {
            resXmlAttributeSearchAttributeByResourceId = null;
            data = 0;
        } else {
            data = resXmlAttributeSearchAttributeByResourceId.getData();
        }
        backupAttributeValues(tableBlock, androidManifestBlock.getDocumentElement());
        if (resXmlAttributeSearchAttributeByResourceId != null) {
            resXmlAttributeSearchAttributeByResourceId.setTypeAndData(ValueType.REFERENCE, data);
        }
    }

    private void clearFiles(ZipEntryMap zipEntryMap) {
        int size = zipEntryMap.size();
        if (size == 2) {
            return;
        }
        logMessage("Removing files from: " + size);
        InputSource inputSource = zipEntryMap.getInputSource(TableBlock.FILE_NAME);
        InputSource inputSource2 = zipEntryMap.getInputSource(AndroidManifest.FILE_NAME);
        zipEntryMap.clear();
        if (inputSource != null) {
            inputSource.setMethod(8);
        }
        if (inputSource2 != null) {
            inputSource2.setMethod(8);
        }
        zipEntryMap.add(inputSource);
        zipEntryMap.add(inputSource2);
        logMessage("Removed files: " + (size - zipEntryMap.size()));
    }

    private void compressManifest(AndroidManifestBlock androidManifestBlock) {
        logMessage("Compressing manifest ...");
        int iCountBytes = androidManifestBlock.countBytes();
        androidManifestBlock.getDocumentElement().removeIf(new Predicate<ResXmlNode>() { // from class: com.reandroid.apk.FrameworkOptimizer.1
            @Override // java.util.function.Predicate
            public boolean test(ResXmlNode resXmlNode) {
                return ((resXmlNode instanceof ResXmlElement) && ((ResXmlElement) resXmlNode).equalsName(AndroidManifest.TAG_application)) ? false : true;
            }
        });
        ResXmlElement applicationElement = androidManifestBlock.getApplicationElement();
        if (applicationElement != null) {
            applicationElement.clear();
        }
        androidManifestBlock.getStringPool().removeUnusedStrings();
        androidManifestBlock.refresh();
        logMessage("Manifest size reduced by: " + ((((long) (iCountBytes - androidManifestBlock.countBytes())) * 100) / ((long) iCountBytes)) + " %");
    }

    private Entry getEntryWithValue(TableBlock tableBlock, int i, Set<Integer> set) {
        if (set.contains(Integer.valueOf(i))) {
            return null;
        }
        set.add(Integer.valueOf(i));
        ResourceEntry resource = tableBlock.getResource(i);
        Entry entry = resource.get();
        if (entry == null) {
            return null;
        }
        if (isReferenceEntry(entry)) {
            return getEntryWithValue(tableBlock, entry.getTableEntry().getValue().getData(), set);
        }
        if (!entry.isNull()) {
            return entry;
        }
        Iterator it = resource.iterator(true);
        while (it.hasNext()) {
            Entry entry2 = (Entry) it.next();
            if (!isReferenceEntry(entry2) && !entry2.isNull()) {
                return entry2;
            }
        }
        return null;
    }

    private FrameworkTable getFrameworkTable() {
        FrameworkTable tableBlock = this.frameworkApk.getTableBlock();
        if (tableBlock instanceof FrameworkTable) {
            return tableBlock;
        }
        TableBlock framework = toFramework(tableBlock);
        this.frameworkApk.setTableBlock(framework);
        return framework;
    }

    private boolean isReferenceEntry(Entry entry) {
        if (entry == null || entry.isNull()) {
            return false;
        }
        ResTableEntry tableEntry = entry.getTableEntry();
        if ((tableEntry instanceof CompoundEntry) || !(tableEntry instanceof ResTableEntry)) {
            return false;
        }
        ValueType valueType = tableEntry.getValue().getValueType();
        return valueType == ValueType.REFERENCE || valueType == ValueType.ATTRIBUTE;
    }

    private void logError(String str, Throwable th) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logError(str, th);
        }
    }

    private void logVerbose(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    private void optimizeTable(FrameworkTable frameworkTable, AndroidManifestBlock androidManifestBlock) {
        String packageName;
        int iIntValue;
        if (frameworkTable.isOptimized()) {
            return;
        }
        logMessage("Optimizing ...");
        int iCountBytes = frameworkTable.countBytes();
        if (androidManifestBlock != null) {
            Integer versionCode = androidManifestBlock.getVersionCode();
            iIntValue = versionCode != null ? versionCode.intValue() : 0;
            packageName = androidManifestBlock.getPackageName();
            compressManifest(androidManifestBlock);
            backupManifestValue(androidManifestBlock, frameworkTable);
        } else {
            packageName = "framework";
            iIntValue = 0;
        }
        logMessage("Optimizing table ...");
        frameworkTable.optimize(packageName, iIntValue);
        logMessage("Table size reduced by: " + ((((long) (iCountBytes - frameworkTable.countBytes())) * 100) / ((long) iCountBytes)) + " %");
        this.mOptimizing = false;
    }

    private FrameworkTable toFramework(TableBlock tableBlock) {
        logMessage("Converting to framework ...");
        BlockReader blockReader = new BlockReader(tableBlock.getBytes());
        FrameworkTable frameworkTable = new FrameworkTable();
        try {
            frameworkTable.readBytes(blockReader);
            return frameworkTable;
        } catch (IOException e) {
            logError("Error re-loading framework: ", e);
            return frameworkTable;
        }
    }

    public APKLogger getApkLogger() {
        return this.apkLogger;
    }

    public void logMessage(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logMessage(str);
        }
    }

    public void optimize() {
        if (this.mOptimizing) {
            return;
        }
        this.mOptimizing = true;
        if (!this.frameworkApk.hasTableBlock()) {
            logMessage("Don't have: " + TableBlock.FILE_NAME);
            this.mOptimizing = false;
            return;
        }
        optimizeTable(getFrameworkTable(), this.frameworkApk.hasAndroidManifest() ? this.frameworkApk.getAndroidManifest() : null);
        UncompressedFiles uncompressedFiles = this.frameworkApk.getUncompressedFiles();
        uncompressedFiles.clearExtensions();
        uncompressedFiles.clearPaths();
        clearFiles(this.frameworkApk.getZipEntryMap());
        logMessage("Optimized");
    }

    public void setAPKLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }

    private void backupAttributeValues(TableBlock tableBlock, ResXmlElement resXmlElement) {
        if (resXmlElement == null) {
            return;
        }
        Iterator attributes = resXmlElement.getAttributes();
        while (attributes.hasNext()) {
            backupAttributeValues(tableBlock, (ResXmlAttribute) attributes.next());
        }
        Iterator elements = resXmlElement.getElements();
        while (elements.hasNext()) {
            backupAttributeValues(tableBlock, (ResXmlElement) elements.next());
        }
    }

    private Entry getEntryWithValue(TableBlock tableBlock, int i) {
        return getEntryWithValue(tableBlock, i, new HashSet());
    }
}
