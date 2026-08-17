package com.reandroid.arsc.chunk;

import com.reandroid.arsc.ARSCLib;
import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.array.PackageArray;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.header.HeaderBlock;
import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.header.TableHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.model.ResourceName;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.StagedAliasEntry;
import com.reandroid.arsc.value.ValueItem;
import com.reandroid.common.BytesOutputStream;
import com.reandroid.common.ReferenceResolver;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.MergingIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TableBlock extends Chunk<TableHeader> implements MainChunk, Iterable<PackageBlock>, JSONConvert<JSONObject> {
    private ApkFile mApkFile;
    private PackageBlock mCurrentPackage;
    private PackageBlock mEmptyTablePackage;
    private final List<TableBlock> mFrameWorks;
    private final PackageArray mPackageArray;
    private final TableStringPool mTableStringPool;
    private ReferenceResolver referenceResolver;
    public static final String FILE_NAME = ObjectsUtil.of("resources.arsc");
    public static final String FILE_NAME_JSON = ObjectsUtil.of("resources.arsc.json");
    private static final String NAME_packages = ObjectsUtil.of("packages");
    public static final String NAME_styled_strings = ObjectsUtil.of("styled_strings");
    public static final String JSON_FILE_NAME = ObjectsUtil.of("resources.arsc.json");
    public static final String DIRECTORY_NAME = ObjectsUtil.of("resources");
    public static final String RES_JSON_DIRECTORY_NAME = ObjectsUtil.of("res-json");
    public static final String RES_FILES_DIRECTORY_NAME = ObjectsUtil.of("res-files");
    public static final String ATTR_null_table = ObjectsUtil.of("null-table");

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.reandroid.arsc.array.PackageArray, com.reandroid.arsc.base.Block] */
    public TableBlock() {
        super(new TableHeader(), 2);
        TableHeader tableHeader = (TableHeader) getHeaderBlock();
        TableStringPool tableStringPool = new TableStringPool(true);
        this.mTableStringPool = tableStringPool;
        ?? packageArray = new PackageArray(tableHeader.getPackageCount());
        this.mPackageArray = packageArray;
        this.mFrameWorks = new ArrayCollection();
        addChild(tableStringPool);
        addChild(packageArray);
    }

    public static TableBlock createEmpty() {
        TableBlock tableBlock = new TableBlock();
        tableBlock.initializeAsEmpty();
        return tableBlock;
    }

    public static boolean isResTableBlock(HeaderBlock headerBlock) {
        return headerBlock != null && headerBlock.getChunkType() == ChunkType.TABLE;
    }

    private void linkStringsInternal() {
        linkTableStringsInternal(getTableStringPool());
        for (PackageBlock packageBlock : this) {
            packageBlock.linkSpecStringsInternal(packageBlock.mo35getSpecStringPool());
        }
    }

    public static TableBlock load(File file) throws IOException {
        return load(new FileInputStream(file));
    }

    private void refreshPackageCount() {
        getHeaderBlock().getPackageCount().set(getPackageArray().size());
    }

    public void addFramework(TableBlock tableBlock) {
        if (tableBlock == null || containsFramework(tableBlock)) {
            return;
        }
        this.mFrameWorks.add(tableBlock);
    }

    public void addFrameworks(Iterator<TableBlock> it) {
        Iterator it2 = CollectionUtil.toList(it).iterator();
        while (it2.hasNext()) {
            addFramework((TableBlock) it2.next());
        }
    }

    public Iterator<ValueItem> allValues() {
        return new MergingIterator(new ComputeIterator(getPackages(), new Function() { // from class: j2e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((PackageBlock) obj).allValues();
            }
        }));
    }

    public void changePackageId(int i, int i2) {
        Iterator<PackageBlock> it = iterator();
        while (it.hasNext()) {
            it.next().changePackageId(i, i2);
        }
    }

    public void clear() {
        getPackageArray().destroy();
        getStringPool().clear();
        clearFrameworks();
        refresh();
    }

    public void clearFrameworks() {
        this.mFrameWorks.clear();
    }

    public boolean containsFramework(TableBlock tableBlock) {
        if (tableBlock == null) {
            return false;
        }
        if (isSimilarTo(tableBlock)) {
            return true;
        }
        Iterator<TableBlock> it = this.mFrameWorks.iterator();
        while (it.hasNext()) {
            if (it.next().containsFramework(tableBlock)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<TableBlock> frameworks() {
        List<TableBlock> frameWorks = getFrameWorks();
        return frameWorks.size() == 0 ? EmptyIterator.of() : frameWorks.iterator();
    }

    public void fromJson(JSONObject jSONObject) {
        getPackageArray().fromJson(jSONObject.getJSONArray(NAME_packages));
        refresh();
    }

    public PackageBlock get(int i) {
        return (PackageBlock) getPackageArray().get(i);
    }

    public Iterator<PackageBlock> getAllPackages(PackageBlock packageBlock) {
        return new CombiningIterator(getPackages(packageBlock), new IterableIterator<TableBlock, PackageBlock>(frameworks()) { // from class: com.reandroid.arsc.chunk.TableBlock.8
            public Iterator<PackageBlock> iterator(TableBlock tableBlock) {
                return tableBlock.getPackages();
            }
        });
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public ApkFile getApkFile() {
        return this.mApkFile;
    }

    public ResourceEntry getAttrResource(String str, String str2) {
        Iterator<PackageBlock> allPackages = getAllPackages(str);
        while (allPackages.hasNext()) {
            ResourceEntry attrResource = allPackages.next().getAttrResource(str2);
            if (attrResource != null) {
                return attrResource;
            }
        }
        if (str != null) {
            return getAttrResource(null, str2);
        }
        return null;
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream(getHeaderBlock().getChunkSize());
        try {
            writeBytes((OutputStream) bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    public PackageBlock getCurrentPackage() {
        return this.mCurrentPackage;
    }

    public Iterator<Entry> getEntries(int i, final boolean z) {
        final int i2 = (i >> 16) & 255;
        final int i3 = i & 65535;
        return new IterableIterator<PackageBlock, Entry>(getAllPackages((i >> 24) & 255)) { // from class: com.reandroid.arsc.chunk.TableBlock.3
            public Iterator<Entry> iterator(PackageBlock packageBlock) {
                if (super.getCountValue() <= 0) {
                    return packageBlock.getEntries(i2, i3, z);
                }
                super.stop();
                return null;
            }
        };
    }

    public Entry getEntry(String str, String str2, String str3) {
        Iterator<PackageBlock> allPackages = getAllPackages(str);
        Entry entry = null;
        while (allPackages.hasNext()) {
            Entry entry2 = allPackages.next().getEntry(str2, str3);
            if (entry2 != null) {
                if (!entry2.isNull()) {
                    return entry2;
                }
                if (entry == null) {
                    entry = entry2;
                }
            }
        }
        return entry;
    }

    public List<TableBlock> getFrameWorks() {
        return this.mFrameWorks;
    }

    public ResourceEntry getIdResource(PackageBlock packageBlock, String str, String str2) {
        Iterator<PackageBlock> allPackages = getAllPackages(packageBlock, str);
        while (allPackages.hasNext()) {
            ResourceEntry idResource = allPackages.next().getIdResource(str2);
            if (idResource != null) {
                return idResource;
            }
        }
        if (str != null) {
            return getAttrResource(null, str2);
        }
        return null;
    }

    public ResourceEntry getLocalResource(PackageBlock packageBlock, int i) {
        Iterator<PackageBlock> packages = getPackages(packageBlock);
        while (packages.hasNext()) {
            ResourceEntry resource = packages.next().getResource(i);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public Iterator<ResourceEntry> getLocalResources(final String str) {
        return new IterableIterator<PackageBlock, ResourceEntry>(getPackages((String) null)) { // from class: com.reandroid.arsc.chunk.TableBlock.2
            public Iterator<ResourceEntry> iterator(PackageBlock packageBlock) {
                return packageBlock.getResources(str);
            }
        };
    }

    public PackageBlock getOrCreatePackage(int i, String str) {
        PackageBlock packageBlockById = getPackageArray().getPackageBlockById(i);
        if (packageBlockById == null) {
            return newPackage(i, str);
        }
        return (str == null || packageBlockById == getPackageArray().getPackageBlockByName(str)) ? packageBlockById : newPackage(i, str);
    }

    public PackageArray getPackageArray() {
        return this.mPackageArray;
    }

    public PackageBlock getPackageBlockById(int i) {
        return getPackageArray().getPackageBlockById(i);
    }

    public PackageBlock getPackageBlockByTag(Object obj) {
        for (PackageBlock packageBlock : this) {
            if (Objects.equals(obj, packageBlock.getTag())) {
                return packageBlock;
            }
        }
        return null;
    }

    public Iterator<PackageBlock> getPackages(PackageBlock packageBlock) {
        if (packageBlock == null) {
            packageBlock = getCurrentPackage();
        }
        Iterator<PackageBlock> it = iterator();
        return packageBlock == null ? it : new CombiningIterator(SingleIterator.of(packageBlock), new FilterIterator.Except(it, packageBlock));
    }

    public Iterator<ResConfig> getResConfigs() {
        return new MergingIterator(new ComputeIterator(iterator(), new Function() { // from class: h2e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((PackageBlock) obj).getResConfigs();
            }
        }));
    }

    public ResourceEntry getResource(int i) {
        if (i == 0) {
            return null;
        }
        Iterator<PackageBlock> allPackages = getAllPackages();
        while (allPackages.hasNext()) {
            ResourceEntry resource = allPackages.next().getResource(i);
            if (resource != null) {
                return resource;
            }
        }
        int iResolveStagedAlias = resolveStagedAlias(i, 0);
        if (iResolveStagedAlias != 0 && iResolveStagedAlias != i) {
            Iterator<PackageBlock> allPackages2 = getAllPackages();
            while (allPackages2.hasNext()) {
                ResourceEntry resource2 = allPackages2.next().getResource(iResolveStagedAlias);
                if (resource2 != null) {
                    return resource2;
                }
            }
        }
        return null;
    }

    public Iterator<ResourceEntry> getResources() {
        return new IterableIterator<PackageBlock, ResourceEntry>(getPackages()) { // from class: com.reandroid.arsc.chunk.TableBlock.1
            public Iterator<ResourceEntry> iterator(PackageBlock packageBlock) {
                return packageBlock.getResources();
            }
        };
    }

    public StagedAliasEntry getStagedAlias(int i) {
        Iterator<PackageBlock> allPackages = getAllPackages();
        while (allPackages.hasNext()) {
            StagedAliasEntry stagedAliasEntrySearchByStagedResId = allPackages.next().searchByStagedResId(i);
            if (stagedAliasEntrySearchByStagedResId != null) {
                return stagedAliasEntrySearchByStagedResId;
            }
        }
        return null;
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public TableBlock getTableBlock() {
        return this;
    }

    public TableStringPool getTableStringPool() {
        return this.mTableStringPool;
    }

    public boolean hasFramework() {
        return getFrameWorks().size() != 0;
    }

    public boolean initializeAsEmpty() {
        if (!isEmpty()) {
            return false;
        }
        setNull(true);
        setCurrentPackage(pickOrEmptyPackage());
        return true;
    }

    public boolean isAndroid() {
        PackageBlock packageBlockPickOne = pickOne();
        return packageBlockPickOne != null && "android".equals(packageBlockPickOne.getName()) && packageBlockPickOne.getId() == 1;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        Iterator<PackageBlock> packages = getPackages();
        while (packages.hasNext()) {
            if (!packages.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMultiPackage() {
        return size() > 1;
    }

    public boolean isSimilarTo(TableBlock tableBlock) {
        int size;
        if (tableBlock == this) {
            return true;
        }
        if (tableBlock == null || (size = size()) != tableBlock.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).isSimilarTo(tableBlock.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Iterable
    public Iterator<PackageBlock> iterator() {
        return getPackageArray().iterator();
    }

    public void linkTableStringsInternal(TableStringPool tableStringPool) {
        Iterator<PackageBlock> it = iterator();
        while (it.hasNext()) {
            it.next().linkTableStringsInternal(tableStringPool);
        }
    }

    public Iterable<PackageBlock> listPackages() {
        return getPackageArray().listItems();
    }

    public void merge(TableBlock tableBlock) {
        if (tableBlock == null || tableBlock == this) {
            return;
        }
        getStringPool().merge(tableBlock.getStringPool());
        getPackageArray().merge(tableBlock.getPackageArray());
        refresh();
    }

    public PackageBlock newPackage(int i, String str) {
        PackageBlock packageBlock = (PackageBlock) getPackageArray().createNext();
        packageBlock.setId(i);
        if (str != null) {
            packageBlock.setName(str);
        }
        return packageBlock;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
        refreshPackageCount();
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        getPackageArray().removeIf(new Predicate() { // from class: i2e
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((PackageBlock) obj).isEmpty();
            }
        });
        super.onPreRefresh();
    }

    @Override // com.reandroid.arsc.chunk.Chunk, com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (blockReader.available() == 0) {
            setNull(true);
            return;
        }
        TableHeader headerBlock = getHeaderBlock();
        headerBlock.readBytes(blockReader);
        if (headerBlock.getChunkType() != ChunkType.TABLE) {
            r8g.a("Not resource table: ", headerBlock);
            return;
        }
        InfoHeader headerBlock2 = InfoHeader.read(blockReader);
        PackageArray packageArray = this.mPackageArray;
        packageArray.clear();
        boolean z = false;
        while (headerBlock2 != null && blockReader.isAvailable()) {
            ChunkType chunkType = headerBlock2.getChunkType();
            if (chunkType == ChunkType.STRING) {
                if (!z) {
                    this.mTableStringPool.readBytes(blockReader);
                    z = true;
                }
            } else if (chunkType == ChunkType.PACKAGE) {
                ((PackageBlock) packageArray.createNext()).readBytes(blockReader);
            } else {
                UnknownChunk unknownChunk = new UnknownChunk();
                unknownChunk.readBytes(blockReader);
                addChild(unknownChunk);
            }
            headerBlock2 = blockReader.readHeaderBlock();
        }
        blockReader.close();
        linkStringsInternal();
    }

    public PackageBlock parsePublicXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        PackageBlock packageBlockNewPackage = newPackage(0, null);
        packageBlockNewPackage.parsePublicXml(xmlPullParser);
        return packageBlockNewPackage;
    }

    public PackageBlock pickOne() {
        PackageBlock currentPackage = getCurrentPackage();
        return (currentPackage == null || currentPackage.getTableBlock() != this) ? getPackageArray().pickOne() : currentPackage;
    }

    public PackageBlock pickOrEmptyPackage() {
        PackageBlock packageBlockPickOne = pickOne();
        if (packageBlockPickOne != null) {
            return packageBlockPickOne;
        }
        PackageBlock packageBlock = this.mEmptyTablePackage;
        if (packageBlock != null) {
            return packageBlock;
        }
        PackageBlock packageBlockCreateEmptyPackage = PackageBlock.createEmptyPackage(this);
        this.mEmptyTablePackage = packageBlockCreateEmptyPackage;
        return packageBlockCreateEmptyPackage;
    }

    public void readBytes(File file) throws IOException {
        super.readBytes(new BlockReader(file));
    }

    public String refreshFull() {
        boolean z;
        int chunkSize = getHeaderBlock().getChunkSize();
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        if (getTableStringPool().removeUnusedStrings()) {
            sb.append("Removed unused table strings");
            z = true;
        } else {
            z = false;
        }
        for (PackageBlock packageBlock : this) {
            String strRefreshFull = packageBlock.refreshFull(false);
            if (strRefreshFull != null) {
                if (z) {
                    sb.append("\n");
                }
                sb.append("Package: ");
                sb.append(packageBlock.getName());
                sb.append("\n  ");
                sb.append(strRefreshFull.replaceAll("\n", "\n  "));
                z = true;
            }
        }
        refresh();
        int chunkSize2 = getHeaderBlock().getChunkSize();
        if (chunkSize != chunkSize2) {
            if (z) {
                sb.append("\n");
            }
            sb.append("Table size changed = ");
            sb.append(chunkSize);
            sb.append(", ");
            sb.append(chunkSize2);
        } else {
            z2 = z;
        }
        if (z2) {
            return sb.toString();
        }
        return null;
    }

    public void removeFramework(TableBlock tableBlock) {
        this.mFrameWorks.remove(tableBlock);
    }

    public void removePackage(PackageBlock packageBlock) {
        getPackageArray().remove(packageBlock);
    }

    public boolean removeUnusedSpecs() {
        Iterator<PackageBlock> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().removeUnusedSpecs()) {
                z = true;
            }
        }
        return z;
    }

    public List<Entry> resolveReference(int i, Predicate<Entry> predicate) {
        ReferenceResolver referenceResolver = this.referenceResolver;
        if (referenceResolver == null) {
            referenceResolver = new ReferenceResolver(this);
            this.referenceResolver = referenceResolver;
        }
        return referenceResolver.resolveAll(i, predicate);
    }

    public List<Entry> resolveReferenceWithConfig(int i, ResConfig resConfig) {
        ReferenceResolver referenceResolver = this.referenceResolver;
        if (referenceResolver == null) {
            referenceResolver = new ReferenceResolver(this);
            this.referenceResolver = referenceResolver;
        }
        return referenceResolver.resolveWithConfig(i, resConfig);
    }

    public int resolveResourceId(String str, String str2, String str3) {
        Iterator<Entry> entries = getEntries(str, str2, str3);
        if (entries.hasNext()) {
            return entries.next().getResourceId();
        }
        return 0;
    }

    public int resolveStagedAlias(int i, int i2) {
        StagedAliasEntry stagedAlias = getStagedAlias(i);
        return stagedAlias != null ? stagedAlias.getFinalizedResId() : i2;
    }

    public int searchResourceIdAlias(int i) {
        return resolveStagedAlias(i, 0);
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public void setApkFile(ApkFile apkFile) {
        this.mApkFile = apkFile;
    }

    public void setCurrentPackage(PackageBlock packageBlock) {
        this.mCurrentPackage = packageBlock;
    }

    public int size() {
        return getPackageArray().size();
    }

    public void sortPackages() {
        getPackageArray().sort();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("arsc_lib_version", ARSCLib.getVersion());
        jSONObject.put(NAME_packages, getPackageArray().m25toJson());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return getClass().getSimpleName() + ": packages = " + this.mPackageArray.size() + ", size = " + getHeaderBlock().getChunkSize() + " bytes";
    }

    public void trimConfigSizes(int i) {
        Iterator<PackageBlock> it = iterator();
        while (it.hasNext()) {
            it.next().trimConfigSizes(i);
        }
    }

    public final int writeBytes(File file) throws IOException {
        if (isNull()) {
            a16.a("Can NOT save null block");
            return 0;
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int iWriteBytes = super.writeBytes(fileOutputStream);
        fileOutputStream.close();
        return iWriteBytes;
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public TableStringPool getStringPool() {
        return this.mTableStringPool;
    }

    public void readBytes(InputStream inputStream) throws IOException {
        super.readBytes(new BlockReader(inputStream));
    }

    public static TableBlock load(InputStream inputStream) throws IOException {
        TableBlock tableBlock = new TableBlock();
        tableBlock.readBytes(inputStream);
        return tableBlock;
    }

    public static boolean isResTableBlock(BlockReader blockReader) {
        if (blockReader == null) {
            return false;
        }
        try {
            return isResTableBlock(blockReader.readHeaderBlock());
        } catch (IOException unused) {
            return false;
        }
    }

    public List<Entry> resolveReference(int i) {
        return resolveReference(i, null);
    }

    public static boolean isResTableBlock(InputStream inputStream) {
        try {
            return isResTableBlock(BlockReader.readHeaderBlock(inputStream));
        } catch (IOException unused) {
            return false;
        }
    }

    public Iterator<PackageBlock> getAllPackages(PackageBlock packageBlock, final String str) {
        return new FilterIterator<PackageBlock>(getAllPackages(packageBlock)) { // from class: com.reandroid.arsc.chunk.TableBlock.7
            public boolean test(PackageBlock packageBlock2) {
                String str2 = str;
                if (str2 != null) {
                    return packageBlock2.packageNameMatches(str2);
                }
                return TableBlock.this == packageBlock2.getTableBlock();
            }
        };
    }

    public Iterator<PackageBlock> getAllPackages() {
        return getAllPackages((PackageBlock) null);
    }

    public Iterator<PackageBlock> getAllPackages(final int i) {
        return new FilterIterator<PackageBlock>(getAllPackages()) { // from class: com.reandroid.arsc.chunk.TableBlock.9
            public boolean test(PackageBlock packageBlock) {
                return i == packageBlock.getId();
            }
        };
    }

    public Iterator<PackageBlock> getAllPackages(final String str) {
        return new FilterIterator<PackageBlock>(getAllPackages()) { // from class: com.reandroid.arsc.chunk.TableBlock.10
            public boolean test(PackageBlock packageBlock) {
                String str2 = str;
                if (str2 != null) {
                    return packageBlock.packageNameMatches(str2);
                }
                return TableBlock.this == packageBlock.getTableBlock();
            }
        };
    }

    public PackageBlock pickOne(int i) {
        return getPackageArray().pickOne(i);
    }

    public Iterator<Entry> getEntries(int i) {
        return getEntries(i, true);
    }

    public ResourceEntry getLocalResource(int i) {
        return getLocalResource((PackageBlock) null, i);
    }

    public Iterator<Entry> getEntries(String str, final String str2, final String str3) {
        return new IterableIterator<PackageBlock, Entry>(getAllPackages(str)) { // from class: com.reandroid.arsc.chunk.TableBlock.4
            public Iterator<Entry> iterator(PackageBlock packageBlock) {
                if (super.getCountValue() <= 0) {
                    return packageBlock.getEntries(str2, str3);
                }
                super.stop();
                return null;
            }
        };
    }

    public ResourceEntry getLocalResource(PackageBlock packageBlock, String str, String str2) {
        Iterator<PackageBlock> packages = getPackages(packageBlock);
        while (packages.hasNext()) {
            ResourceEntry resource = packages.next().getResource(str, str2);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public Iterator<PackageBlock> getPackages(final int i) {
        if (i == 0) {
            return EmptyIterator.of();
        }
        return new FilterIterator<PackageBlock>(getPackages()) { // from class: com.reandroid.arsc.chunk.TableBlock.6
            public boolean test(PackageBlock packageBlock) {
                return i == packageBlock.getId();
            }
        };
    }

    public ResourceEntry getLocalResource(String str, String str2) {
        return getLocalResource((String) null, str, str2);
    }

    public Iterator<PackageBlock> getPackages() {
        return getPackages((PackageBlock) null);
    }

    public ResourceEntry getLocalResource(String str, String str2, String str3) {
        Iterator<PackageBlock> packages = getPackages(str);
        while (packages.hasNext()) {
            ResourceEntry resource = packages.next().getResource(str2, str3);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public Iterator<PackageBlock> getPackages(final String str) {
        return new FilterIterator<PackageBlock>(getPackages()) { // from class: com.reandroid.arsc.chunk.TableBlock.5
            public boolean test(PackageBlock packageBlock) {
                String str2 = str;
                if (str2 == null || str2.length() <= 0) {
                    return TableBlock.this == packageBlock.getTableBlock();
                }
                return packageBlock.packageNameMatches(str);
            }
        };
    }

    public ResourceEntry getAttrResource(PackageBlock packageBlock, String str, String str2) {
        Iterator<PackageBlock> allPackages = getAllPackages(packageBlock, str);
        while (allPackages.hasNext()) {
            ResourceEntry attrResource = allPackages.next().getAttrResource(str2);
            if (attrResource != null) {
                return attrResource;
            }
        }
        if (str != null) {
            return getAttrResource(null, str2);
        }
        return null;
    }

    public ResourceEntry getResource(PackageBlock packageBlock, int i) {
        if (i == 0) {
            return null;
        }
        Iterator<PackageBlock> allPackages = getAllPackages(packageBlock);
        while (allPackages.hasNext()) {
            ResourceEntry resource = allPackages.next().getResource(i);
            if (resource != null) {
                return resource;
            }
        }
        int iResolveStagedAlias = resolveStagedAlias(i, 0);
        if (iResolveStagedAlias != 0 && iResolveStagedAlias != i) {
            Iterator<PackageBlock> allPackages2 = getAllPackages(packageBlock);
            while (allPackages2.hasNext()) {
                ResourceEntry resource2 = allPackages2.next().getResource(iResolveStagedAlias);
                if (resource2 != null) {
                    return resource2;
                }
            }
        }
        return null;
    }

    public ResourceEntry getResource(ResourceName resourceName) {
        if (resourceName != null) {
            return getResource(resourceName.getPackageName(), resourceName.getType(), resourceName.getName());
        }
        return null;
    }

    public ResourceEntry getResource(String str, String str2, String str3) {
        Iterator<PackageBlock> allPackages = getAllPackages(str);
        while (allPackages.hasNext()) {
            ResourceEntry resource = allPackages.next().getResource(str2, str3);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public ResourceEntry getResource(PackageBlock packageBlock, String str, String str2) {
        Iterator<PackageBlock> allPackages = getAllPackages(packageBlock);
        while (allPackages.hasNext()) {
            ResourceEntry resource = allPackages.next().getResource(str, str2);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public ResourceEntry getResource(PackageBlock packageBlock, String str, String str2, String str3) {
        Iterator<PackageBlock> allPackages = getAllPackages(packageBlock, str);
        while (allPackages.hasNext()) {
            ResourceEntry resource = allPackages.next().getResource(str2, str3);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }
}
