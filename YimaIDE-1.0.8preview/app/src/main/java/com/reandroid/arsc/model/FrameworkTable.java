package com.reandroid.arsc.model;

import com.reandroid.arsc.ARSCLib;
import com.reandroid.arsc.array.SpecTypePairArray;
import com.reandroid.arsc.array.TypeBlockArray;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.ReferenceItem;
import com.reandroid.arsc.item.TableString;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.common.FileChannelInputStream;
import com.reandroid.utils.collection.CollectionUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FrameworkTable extends TableBlock {
    private static final int PROP_COUNT = 10;
    private static final String PROP_NAME = "NAME";
    private static final String PROP_VERSION_CODE = "VERSION_CODE";
    private String frameworkName;
    private boolean mOptimizeChecked;
    private boolean mOptimized;
    private int mainPackageId;
    private int versionCode;

    private void ensureNonNullDefaultEntry(ResourceEntry resourceEntry) {
        Entry entryAny;
        Entry orCreate = resourceEntry.getOrCreate(ResConfig.getDefault());
        if (orCreate.isNull() && (entryAny = resourceEntry.any()) != null) {
            orCreate.merge(entryAny);
            entryAny.setNull(true);
        }
    }

    private void ensureTypeBlockNonNullEntries() {
        Iterator<ResourceEntry> resources = getResources();
        while (resources.hasNext()) {
            ensureNonNullDefaultEntry(resources.next());
        }
    }

    public static FrameworkTable load(File file) throws IOException {
        return load((InputStream) new FileChannelInputStream(file));
    }

    private String loadProperty(String str) {
        if (str == null) {
            return null;
        }
        if (!str.endsWith(":")) {
            str = str.concat(":");
        }
        TableString tableStringLoadPropertyString = loadPropertyString(str);
        if (tableStringLoadPropertyString == null) {
            return null;
        }
        return tableStringLoadPropertyString.get().trim().substring(str.length()).trim();
    }

    private TableString loadPropertyString(String str) {
        TableString tableString;
        if (str == null) {
            return null;
        }
        if (!str.endsWith(":")) {
            str = str.concat(":");
        }
        TableStringPool stringPool = getStringPool();
        for (int i = 0; i < 10 && (tableString = stringPool.get(i)) != null; i++) {
            String str2 = tableString.get();
            if (str2 != null && str2.trim().startsWith(str)) {
                return tableString;
            }
        }
        return null;
    }

    private void optimizeEntries() {
        removeExtraConfigEntries();
        Iterator<PackageBlock> it = listPackages().iterator();
        while (it.hasNext()) {
            removeEmptyBlocks(it.next());
        }
        for (PackageBlock packageBlock : listPackages()) {
            packageBlock.removeEmpty();
            packageBlock.refresh();
        }
    }

    private void optimizeTableString() {
        removeUnusedTableString();
        getStringPool().getStyleArray().clear();
        shrinkTableString();
        removeUnusedTableString();
    }

    private void removeEmptyBlocks(PackageBlock packageBlock) {
        SpecTypePairArray specTypePairArray = packageBlock.getSpecTypePairArray();
        specTypePairArray.sort();
        Iterator itClonedIterator = specTypePairArray.clonedIterator();
        while (itClonedIterator.hasNext()) {
            removeEmptyBlocks((SpecTypePair) itClonedIterator.next());
        }
    }

    private void removeExtraConfigEntries(ResourceEntry resourceEntry) {
        Entry entry = resourceEntry.get();
        if (entry == null) {
            return;
        }
        Iterator it = resourceEntry.iterator(true);
        while (it.hasNext()) {
            Entry entry2 = (Entry) it.next();
            if (entry2 != entry) {
                entry2.setNull(true);
            }
        }
    }

    private void removeUnusedTableString() {
        TableStringPool stringPool = getStringPool();
        stringPool.removeUnusedStrings();
        stringPool.refresh();
    }

    private void shrinkTableString(TableString tableString, TableString tableString2) {
        List list = CollectionUtil.toList(tableString2.getReferences());
        tableString2.clearReferences();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ReferenceItem) it.next()).set(tableString.getIndex());
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            tableString.addReference((ReferenceItem) it2.next());
        }
    }

    private TableString writeProperty(String str, String str2) {
        if (!str.endsWith(":")) {
            str = str.concat(":");
        }
        if (str2 == null) {
            str2 = XmlPullParser.NO_NAMESPACE;
        }
        if (!str2.startsWith(str)) {
            str2 = str.concat(str2);
        }
        TableString tableStringLoadPropertyString = loadPropertyString(str);
        if (tableStringLoadPropertyString == null) {
            return getStringPool().getOrCreate(str2);
        }
        tableStringLoadPropertyString.set(str2);
        return tableStringLoadPropertyString;
    }

    private void writeVersionCode(int i) {
        writeProperty(PROP_VERSION_CODE, String.valueOf(i));
    }

    @Override // com.reandroid.arsc.chunk.TableBlock
    public void clear() {
        this.frameworkName = null;
        this.versionCode = 0;
        this.mainPackageId = 0;
        super.clear();
    }

    public String getFrameworkName() {
        PackageBlock packageBlockPickOne;
        String name;
        if (this.frameworkName == null) {
            this.frameworkName = loadProperty(PROP_NAME);
        }
        if (this.frameworkName == null && (packageBlockPickOne = pickOne()) != null && (name = packageBlockPickOne.getName()) != null && !name.trim().isEmpty()) {
            this.frameworkName = name;
        }
        return this.frameworkName;
    }

    public int getMainPackageId() {
        int i = this.mainPackageId;
        if (i != 0) {
            return i;
        }
        PackageBlock packageBlockPickOne = pickOne();
        if (packageBlockPickOne != null) {
            this.mainPackageId = packageBlockPickOne.getId();
        }
        return this.mainPackageId;
    }

    public int getVersionCode() {
        String strLoadProperty;
        if (this.versionCode == 0 && isOptimized() && (strLoadProperty = loadProperty(PROP_VERSION_CODE)) != null) {
            try {
                this.versionCode = Integer.parseInt(strLoadProperty);
            } catch (NumberFormatException unused) {
            }
        }
        return this.versionCode;
    }

    @Override // com.reandroid.arsc.chunk.TableBlock
    public boolean isAndroid() {
        return "android".equals(getFrameworkName()) && getMainPackageId() == 1;
    }

    public boolean isOptimized() {
        if (!this.mOptimizeChecked) {
            boolean z = true;
            this.mOptimizeChecked = true;
            String strLoadProperty = loadProperty(PROP_VERSION_CODE);
            if (strLoadProperty != null) {
                try {
                    if (Integer.parseInt(strLoadProperty) == 0) {
                        z = false;
                    }
                    this.mOptimized = z;
                } catch (NumberFormatException unused) {
                }
            }
        }
        return this.mOptimized;
    }

    public void optimize(String str, int i) {
        this.mOptimizeChecked = true;
        this.mOptimized = false;
        ensureTypeBlockNonNullEntries();
        optimizeEntries();
        optimizeTableString();
        writeVersionCode(i);
        this.mOptimizeChecked = false;
        setFrameworkName(str);
        refresh();
    }

    public void setFrameworkName(String str) {
        this.frameworkName = str;
        if (isOptimized()) {
            writeProperty(PROP_NAME, str);
        }
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
        if (isOptimized()) {
            writeVersionCode(i);
        }
    }

    @Override // com.reandroid.arsc.chunk.TableBlock, com.reandroid.arsc.chunk.Chunk
    public String toString() {
        if (getHeaderBlock().getChunkType() != ChunkType.TABLE) {
            return super.toString();
        }
        if (!this.mOptimized) {
            return "Unoptimized: " + super.toString();
        }
        return getFrameworkName() + '-' + getVersionCode();
    }

    public static FrameworkTable load(InputStream inputStream) throws IOException {
        FrameworkTable frameworkTable = new FrameworkTable();
        frameworkTable.readBytes(inputStream);
        return frameworkTable;
    }

    private void removeEmptyBlocks(SpecTypePair specTypePair) {
        TypeBlockArray typeBlockArray = specTypePair.getTypeBlockArray();
        if (typeBlockArray.size() < 2) {
            return;
        }
        typeBlockArray.removeEmptyBlocks();
    }

    private void removeExtraConfigEntries() {
        Iterator<ResourceEntry> resources = getResources();
        while (resources.hasNext()) {
            removeExtraConfigEntries(resources.next());
        }
    }

    private void shrinkTableString() {
        TableStringPool stringPool = getStringPool();
        stringPool.getStringsArray().ensureSize(1);
        TableString tableString = stringPool.get(0);
        tableString.set(ARSCLib.getRepo());
        for (TableString tableString2 : stringPool) {
            if (tableString2 != tableString) {
                shrinkTableString(tableString, tableString2);
            }
        }
        stringPool.refresh();
    }
}
