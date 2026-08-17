package com.reandroid.apk.xmlencoder;

import com.reandroid.apk.APKLogger;
import com.reandroid.apk.ApkModule;
import com.reandroid.apk.ApkUtil;
import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.coder.xml.XmlEncodeException;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.xml.source.XMLFileParserSource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FilePathEncoder {
    private final ApkModule apkModule;
    private PackageBlock mCurrentPackage;
    private APKLogger mLogger;

    public FilePathEncoder(ApkModule apkModule) {
        this.apkModule = apkModule;
        this.mLogger = apkModule.getApkLogger();
    }

    private void addInputSource(InputSource inputSource) {
        if (inputSource != null) {
            this.apkModule.add(inputSource);
        }
    }

    private InputSource createInputSource(PackageBlock packageBlock, String str, File file) {
        return isXmlFile(file) ? createXMLEncodeInputSource(packageBlock, str, file) : createRawFileInputSource(str, file);
    }

    private InputSource createRawFileInputSource(String str, File file) {
        FileInputSource fileInputSource = new FileInputSource(file, str);
        fileInputSource.setMethod(0);
        return fileInputSource;
    }

    private InputSource createXMLEncodeInputSource(PackageBlock packageBlock, String str, File file) {
        XMLEncodeSource xMLEncodeSource = new XMLEncodeSource(packageBlock, new XMLFileParserSource(str, file));
        xMLEncodeSource.setApkLogger(this.mLogger);
        return xMLEncodeSource;
    }

    private PackageBlock getCurrentPackage() {
        if (this.mCurrentPackage == null) {
            TableBlock tableBlock = this.apkModule.getTableBlock();
            if (tableBlock == null) {
                x0e.a("TableBlock == null");
                return null;
            }
            PackageBlock packageBlockPickOne = tableBlock.pickOne();
            if (packageBlockPickOne == null) {
                x0e.a("PackageBlock == null");
                return null;
            }
            this.mCurrentPackage = packageBlockPickOne;
        }
        return this.mCurrentPackage;
    }

    private boolean isXmlFile(File file) {
        if (!file.getName().endsWith(".xml")) {
            return false;
        }
        if (!"raw".equals(EncodeUtil.getTypeNameFromResFile(file))) {
            return true;
        }
        logMessage("WARN: Using un-encoded raw xml: " + file);
        return false;
    }

    private void logMessage(String str) {
        APKLogger aPKLogger = this.mLogger;
        if (aPKLogger != null) {
            aPKLogger.logMessage(str);
        }
    }

    public void encodePackageResDir(PackageBlock packageBlock, File file) throws IOException {
        this.mCurrentPackage = packageBlock;
        String str = file.getParentFile().getName() + File.separator + file.getName();
        logMessage("Scan: ".concat(str));
        int iEncodeTypeDir = 0;
        for (File file2 : ApkUtil.listDirectories(file)) {
            if (!ApkUtil.isValuesDirectoryName(file2.getName(), true)) {
                iEncodeTypeDir += encodeTypeDir(file2);
            }
        }
        logMessage("Scanned " + iEncodeTypeDir + " files: " + str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.arsc.coder.xml.XmlEncodeException */
    public int encodeTypeDir(File file) throws XmlEncodeException, IOException {
        List<File> listListFiles = ApkUtil.listFiles(file, null);
        Iterator<File> it = listListFiles.iterator();
        while (it.hasNext()) {
            encodeTypeFileEntry(it.next());
        }
        return listListFiles.size();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.arsc.coder.xml.XmlEncodeException */
    public void encodeTypeFileEntry(File file) throws XmlEncodeException, IOException {
        String typeNameFromResFile = EncodeUtil.getTypeNameFromResFile(file);
        String qualifiersFromResFile = EncodeUtil.getQualifiersFromResFile(file);
        String entryNameFromResFile = EncodeUtil.getEntryNameFromResFile(file);
        String entryPathFromResFile = EncodeUtil.getEntryPathFromResFile(file);
        PackageBlock currentPackage = getCurrentPackage();
        ResourceEntry localResource = currentPackage.getTableBlock().getLocalResource(currentPackage, typeNameFromResFile, entryNameFromResFile);
        if (localResource != null) {
            localResource.getOrCreate(qualifiersFromResFile).setValueAsString(entryPathFromResFile);
            addInputSource(createInputSource(localResource.getPackageBlock(), entryPathFromResFile, file));
            return;
        }
        throw new XmlEncodeException("Local resource not defined: @" + typeNameFromResFile + "/" + entryNameFromResFile + ", for path: " + entryPathFromResFile);
    }

    public void setApkLogger(APKLogger aPKLogger) {
        this.mLogger = aPKLogger;
    }
}
