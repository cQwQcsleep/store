package com.reandroid.arsc.chunk;

import com.reandroid.arsc.ARSCLib;
import com.reandroid.arsc.array.LibraryInfoArray;
import com.reandroid.arsc.array.SpecTypePairArray;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.coder.CommonType;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.coder.xml.XmlEncodeException;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.PackageBody;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.header.PackageHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.list.OverlayableList;
import com.reandroid.arsc.list.StagedAliasList;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.model.ResourceLibrary;
import com.reandroid.arsc.model.ResourceName;
import com.reandroid.arsc.model.ResourceType;
import com.reandroid.arsc.pool.SpecStringPool;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.pool.TypeStringPool;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.arsc.value.AttributeValue;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.LibraryInfo;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.StagedAliasEntry;
import com.reandroid.arsc.value.ValueHeader;
import com.reandroid.arsc.value.ValueItem;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONItem;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.MergingIterator;
import com.reandroid.utils.io.IOUtil;
import com.reandroid.xml.XMLElement;
import com.reandroid.xml.XMLFactory;
import com.reandroid.xml.XMLUtil;
import defpackage.o0g;
import defpackage.q0g;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PackageBlock extends Chunk<PackageHeader> implements ParentChunk, JSONConvert<JSONObject>, Comparable<PackageBlock>, ResourceLibrary {
    private final PackageBody mBody;
    private boolean mHasValidPrefix;
    private String mPrefix;
    private final SpecStringPool mSpecStringPool;
    private Object mTag;
    private final TypeStringPool mTypeStringPool;
    public static final String NAME_package_id = ObjectsUtil.of("package_id");
    public static final String NAME_package_name = ObjectsUtil.of("package_name");
    private static final String NAME_specs = ObjectsUtil.of("specs");
    public static final String NAME_libraries = ObjectsUtil.of("libraries");
    public static final String NAME_staged_aliases = ObjectsUtil.of("staged_aliases");
    public static final String NAME_overlaybles = ObjectsUtil.of("overlaybles");
    public static final String JSON_FILE_NAME = ObjectsUtil.of("package.json");
    public static final String DIRECTORY_NAME_PREFIX = ObjectsUtil.of("package_");
    public static final String RES_DIRECTORY_NAME = ObjectsUtil.of("res");
    public static final String VALUES_DIRECTORY_NAME = ObjectsUtil.of("values");
    public static final String PUBLIC_XML = ObjectsUtil.of("public.xml");
    public static final String TAG_public = ObjectsUtil.of("public");
    public static final String TAG_resources = ObjectsUtil.of("resources");
    public static final String ATTR_package = ObjectsUtil.of("package");
    public static final String ATTR_id = ObjectsUtil.of(TypeBlock.NAME_id);
    public static final String ATTR_type = ObjectsUtil.of("type");
    public static final String ATTR_name = ObjectsUtil.of(TypeBlock.NAME_name);
    public static final String EMPTY_PACKAGE_NAME = ObjectsUtil.of("empty-package");

    public PackageBlock() {
        super(new PackageHeader(), 3);
        TypeStringPool typeStringPool = new TypeStringPool(false, ((PackageHeader) getHeaderBlock()).getTypeIdOffsetItem());
        this.mTypeStringPool = typeStringPool;
        Block specStringPool = new SpecStringPool(true);
        this.mSpecStringPool = specStringPool;
        PackageBody packageBody = new PackageBody();
        this.mBody = packageBody;
        addChild(typeStringPool);
        addChild(specStringPool);
        addChild(packageBody);
    }

    public static /* synthetic */ boolean b(SpecTypePair specTypePair) {
        return specTypePair != null && specTypePair.isTypeId();
    }

    private static void changePackageIdName(int i, int i2, AttributeValue attributeValue) {
        int nameId = attributeValue.getNameId();
        if (isResourceId(nameId) && ((nameId >> 24) & 255) == i) {
            attributeValue.setNameId((16777215 & nameId) | (i2 << 24));
        }
    }

    private static void changePackageIdValue(int i, int i2, ValueItem valueItem) {
        ValueType valueType = valueItem.getValueType();
        if (valueType == null || !valueType.isReference()) {
            return;
        }
        int data = valueItem.getData();
        if (isResourceId(data) && ((data >> 24) & 255) == i) {
            valueItem.setData((16777215 & data) | (i2 << 24));
        }
    }

    public static PackageBlock createEmptyPackage(final TableBlock tableBlock) {
        PackageBlock packageBlock = new PackageBlock() { // from class: com.reandroid.arsc.chunk.PackageBlock.4
            @Override // com.reandroid.arsc.chunk.PackageBlock, java.lang.Comparable
            public /* bridge */ /* synthetic */ int compareTo(PackageBlock packageBlock2) {
                return super.compareTo(packageBlock2);
            }

            @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
            public int countBytes() {
                return 0;
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public /* bridge */ /* synthetic */ void fromJson(JSONItem jSONItem) {
                super.fromJson((JSONObject) jSONItem);
            }

            @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
            public byte[] getBytes() {
                return new byte[0];
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public int getId() {
                return 0;
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock, com.reandroid.arsc.chunk.ParentChunk
            public /* bridge */ /* synthetic */ MainChunk getMainChunk() {
                return super.getMainChunk();
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public String getName() {
                return PackageBlock.EMPTY_PACKAGE_NAME;
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock, com.reandroid.arsc.chunk.ParentChunk
            /* JADX INFO: renamed from: getSpecStringPool */
            public /* bridge */ /* synthetic */ StringPool mo35getSpecStringPool() {
                return super.mo35getSpecStringPool();
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public TableBlock getTableBlock() {
                return tableBlock;
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public boolean isEmpty() {
                return true;
            }

            @Override // com.reandroid.arsc.base.Block
            public boolean isNull() {
                return true;
            }

            @Override // com.reandroid.arsc.chunk.Chunk, com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
            public void onReadBytes(BlockReader blockReader) throws IOException {
                throw new IOException("Can't read on empty package");
            }

            @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
            public int onWriteBytes(OutputStream outputStream) throws IOException {
                throw new IOException("Can't write on empty package");
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public void setId(int i) {
                if (i == 0) {
                    return;
                }
                w01.a("Can't set id to empty package");
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public void setName(String str) {
                if (str == null || str.length() == 0) {
                    return;
                }
                w01.a("Can't set name to empty package");
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock
            public /* bridge */ /* synthetic */ JSONItem toJson() {
                return super.toJson();
            }

            @Override // com.reandroid.arsc.chunk.PackageBlock, com.reandroid.arsc.chunk.Chunk
            public String toString() {
                return getName();
            }
        };
        packageBlock.setParent(tableBlock);
        return packageBlock;
    }

    private Iterator<SpecTypePair> getAttrSpecs() {
        return getSpecTypePairArray().iterator(new Predicate<SpecTypePair>() { // from class: com.reandroid.arsc.chunk.PackageBlock.3
            @Override // java.util.function.Predicate
            public boolean test(SpecTypePair specTypePair) {
                return specTypePair != null && specTypePair.isTypeAttr();
            }
        });
    }

    private Iterator<SpecTypePair> getIdSpecs() {
        return getSpecTypePairArray().iterator(new Predicate() { // from class: lva
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PackageBlock.b((SpecTypePair) obj);
            }
        });
    }

    private boolean isAndroid() {
        return getId() == 1 && Namespace.PREFIX_ANDROID.equals(getName());
    }

    public static boolean isPackageId(int i) {
        return i != 0 && i > 0 && i <= 255;
    }

    public static boolean isResourceId(int i) {
        return (i == 0 || (16711680 & i) == 0 || (i & (-16777216)) == 0) ? false : true;
    }

    private void refreshSpecStringCount() {
        getHeaderBlock().getSpecStringPoolCount().set(this.mSpecStringPool.size());
    }

    private void refreshSpecStringPoolOffset() {
        getHeaderBlock().getSpecStringPoolOffset().set(countUpTo(this.mSpecStringPool));
    }

    private void refreshTypeStringPoolCount() {
        getHeaderBlock().getTypeStringPoolCount().set(this.mTypeStringPool.size());
    }

    private void refreshTypeStringPoolOffset() {
        getHeaderBlock().getTypeStringPoolOffset().set(countUpTo(this.mTypeStringPool));
    }

    public static int replacePackageId(int i, int i2, int i3) {
        return (isResourceId(i) && ((i >> 24) & 255) == i2) ? (i & 16777215) | (i3 << 24) : i;
    }

    private void serializePublicXmlTypes(XmlSerializer xmlSerializer) throws IOException {
        Iterator<SpecTypePair> specTypePairs = getSpecTypePairs();
        while (specTypePairs.hasNext()) {
            specTypePairs.next().serializePublicXml(xmlSerializer);
        }
    }

    private void writePackageInfo(XmlSerializer xmlSerializer) throws IOException {
        String name = getName();
        if (name != null) {
            xmlSerializer.attribute(null, ATTR_package, name);
        }
        int id = getId();
        if (id != 0) {
            xmlSerializer.attribute(null, ATTR_id, HexUtil.toHex2((byte) id));
        }
        TableBlock tableBlock = getTableBlock();
        if (tableBlock != null && tableBlock.isEmpty() && tableBlock.isNull()) {
            xmlSerializer.attribute(null, TableBlock.ATTR_null_table, "true");
        }
    }

    public void addLibrary(LibraryBlock libraryBlock) {
        if (libraryBlock == null) {
            return;
        }
        Iterator<LibraryInfo> it = libraryBlock.getLibraryInfoArray().listItems().iterator();
        while (it.hasNext()) {
            addLibraryInfo(it.next());
        }
    }

    public void addLibraryInfo(LibraryInfo libraryInfo) {
        getLibraryBlock().addLibraryInfo(libraryInfo);
    }

    public Iterator<ValueItem> allValues() {
        return new MergingIterator(new ComputeIterator(getSpecTypePairs(), new Function() { // from class: mva
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SpecTypePair) obj).allValues();
            }
        }));
    }

    public String buildDecodeDirectoryName() {
        TableBlock tableBlock = getTableBlock();
        return DIRECTORY_NAME_PREFIX + StringsUtil.formatNumber(getIndex() + 1, tableBlock != null ? tableBlock.size() : 0);
    }

    public void changePackageId(int i, int i2) {
        Iterator<ValueItem> itAllValues = allValues();
        while (itAllValues.hasNext()) {
            changePackageId(itAllValues.next(), i, i2);
        }
        if (i == getId()) {
            setId(i2);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(PackageBlock packageBlock) {
        return Integer.compare(getId(), packageBlock.getId());
    }

    public void destroy() {
        getPackageBody().destroy();
        getTypeStringPool().clear();
        mo35getSpecStringPool().clear();
        setId(0);
        setName(XmlPullParser.NO_NAMESPACE);
    }

    @Override // 
    public void fromJson(JSONObject jSONObject) {
        int iOptInt = jSONObject.optInt(NAME_package_id, 0);
        if (iOptInt != 0) {
            setId(iOptInt);
        }
        String strOptString = jSONObject.optString(NAME_package_name, (String) null);
        if (strOptString != null) {
            setName(strOptString);
        }
        getSpecTypePairArray().fromJson(jSONObject.optJSONArray(NAME_specs));
        getLibraryBlock().getLibraryInfoArray().fromJson(jSONObject.optJSONArray(NAME_libraries));
        String str = NAME_staged_aliases;
        if (jSONObject.has(str)) {
            StagedAlias stagedAlias = new StagedAlias();
            stagedAlias.getStagedAliasEntryArray().fromJson(jSONObject.getJSONArray(str));
            getStagedAliasList().add(stagedAlias);
        }
        String str2 = NAME_overlaybles;
        if (jSONObject.has(str2)) {
            getOverlayableList().fromJson(jSONObject.getJSONArray(str2));
        }
    }

    public Entry getAnyEntry(int i) {
        if (((i >> 24) & 255) != getId()) {
            return null;
        }
        return getSpecTypePairArray().getAnyEntry((byte) ((i >> 16) & 255), (short) (i & 65535));
    }

    public ResourceEntry getAttrResource(String str) {
        Iterator<SpecTypePair> attrSpecs = getAttrSpecs();
        while (attrSpecs.hasNext()) {
            ResourceEntry resource = attrSpecs.next().getResource(str);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public Iterator<Entry> getEntries(int i, boolean z) {
        return ((i >> 24) & 255) != getId() ? EmptyIterator.of() : getEntries((i >> 16) & 255, i & 65535, z);
    }

    public Entry getEntry(String str, String str2) {
        Iterator<Entry> entries = getEntries(str, str2);
        Entry entry = null;
        while (entries.hasNext()) {
            Entry next = entries.next();
            if (!next.isNull()) {
                return next;
            }
            if (entry == null) {
                entry = next;
            }
        }
        return entry;
    }

    public int getId() {
        return getHeaderBlock().getPackageId().get();
    }

    public ResourceEntry getIdResource(String str) {
        Iterator<SpecTypePair> idSpecs = getIdSpecs();
        while (idSpecs.hasNext()) {
            ResourceEntry resource = idSpecs.next().getResource(str);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public LibraryBlock getLibraryBlock() {
        return this.mBody.getLibraryBlock();
    }

    public Iterator<LibraryInfo> getLibraryInfo() {
        return getLibraryBlock().iterator();
    }

    public String getName() {
        return getHeaderBlock().getPackageName().get();
    }

    public Entry getOrCreate(ResConfig resConfig, String str, String str2) {
        return getOrCreateSpecTypePair(str).getOrCreateTypeBlock(resConfig).getOrCreateEntry(str2);
    }

    public Entry getOrCreateEntry(byte b, short s, String str) {
        return getSpecTypePairArray().getOrCreateEntry(b, s, str);
    }

    public SpecTypePair getOrCreateSpecTypePair(int i, String str) {
        getOrCreateTypeString(i, str);
        return getSpecTypePairArray().getOrCreate((byte) i);
    }

    public TypeBlock getOrCreateTypeBlock(String str, String str2) {
        return getOrCreateSpecTypePair(str2).getOrCreateTypeBlock(str);
    }

    public TypeString getOrCreateTypeString(int i, String str) {
        return getTypeStringPool().getOrCreate(i, str);
    }

    public OverlayableList getOverlayableList() {
        return this.mBody.getOverlayableList();
    }

    @Override // com.reandroid.arsc.chunk.ParentChunk
    public PackageBlock getPackageBlock() {
        return this;
    }

    public PackageBody getPackageBody() {
        return this.mBody;
    }

    public String getPrefix() {
        String prefix;
        boolean zEquals;
        String str = this.mPrefix;
        if (str != null) {
            return str;
        }
        if (getId() == 1) {
            prefix = Namespace.PREFIX_ANDROID;
            zEquals = prefix.equals(getName());
        } else {
            prefix = ResourceLibrary.toPrefix(getName());
            zEquals = Namespace.isValidPrefix(prefix) && !Namespace.PREFIX_ANDROID.equals(prefix);
            if (!zEquals) {
                prefix = Namespace.PREFIX_APP;
            }
        }
        this.mPrefix = prefix;
        this.mHasValidPrefix = zEquals;
        return prefix;
    }

    public PublicXmlParser getPublicXmlParser() {
        return new PublicXmlParser();
    }

    public Iterator<ResConfig> getResConfigs() {
        return new MergingIterator(new ComputeIterator(getSpecTypePairs(), new Function() { // from class: ova
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SpecTypePair) obj).getResConfigs();
            }
        }));
    }

    public ResourceEntry getResource(int i) {
        int finalizedResId;
        ResourceEntry resource;
        int i2 = (i >> 24) & 255;
        if (i2 == 0) {
            return null;
        }
        if (i2 == getId() && (resource = getResource((i >> 16) & 255, i & 65535)) != null) {
            return resource;
        }
        StagedAliasEntry stagedAliasEntrySearchByStagedResId = searchByStagedResId(i);
        if (stagedAliasEntrySearchByStagedResId == null || (finalizedResId = stagedAliasEntrySearchByStagedResId.getFinalizedResId()) == 0 || finalizedResId == i || ((finalizedResId >> 24) & 255) != getId()) {
            return null;
        }
        return getResource((finalizedResId >> 16) & 255, finalizedResId & 65535);
    }

    public Iterator<ResourceEntry> getResources() {
        return new IterableIterator<SpecTypePair, ResourceEntry>(getSpecTypePairs()) { // from class: com.reandroid.arsc.chunk.PackageBlock.1
            public Iterator<ResourceEntry> iterator(SpecTypePair specTypePair) {
                return specTypePair.getResources();
            }
        };
    }

    public SpecTypePair getSpecTypePair(int i) {
        return getSpecTypePairArray().getSpecTypePair((byte) i);
    }

    public SpecTypePairArray getSpecTypePairArray() {
        return this.mBody.getSpecTypePairArray();
    }

    public Iterator<SpecTypePair> getSpecTypePairs() {
        return getSpecTypePairArray().iterator();
    }

    public StagedAliasList getStagedAliasList() {
        return this.mBody.getStagedAliasList();
    }

    public TableBlock getTableBlock() {
        for (Block parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TableBlock) {
                return (TableBlock) parent;
            }
        }
        return null;
    }

    public Object getTag() {
        return this.mTag;
    }

    public TypeBlock getTypeBlock(byte b, String str) {
        return getSpecTypePairArray().getTypeBlock(b, str);
    }

    public int getTypeIdOffset() {
        return getHeaderBlock().getTypeIdOffset();
    }

    public TypeStringPool getTypeStringPool() {
        return this.mTypeStringPool;
    }

    public Iterator<ResourceType> getTypes() {
        return ComputeIterator.of(getSpecTypePairs(), new Function() { // from class: nva
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new ResourceType((SpecTypePair) obj);
            }
        });
    }

    public BlockList<UnknownChunk> getUnknownChunkList() {
        return this.mBody.getUnknownChunkList();
    }

    public String getUri() {
        return isAndroid() ? Namespace.URI_ANDROID : Namespace.URI_RES_AUTO;
    }

    public boolean hasValidTypeNames() {
        HashSet hashSet = new HashSet();
        Iterator<SpecTypePair> specTypePairs = getSpecTypePairs();
        while (specTypePairs.hasNext()) {
            String typeName = specTypePairs.next().getTypeName();
            if (!CommonType.isCommonTypeName(typeName) || hashSet.contains(typeName)) {
                return false;
            }
            hashSet.add(typeName);
        }
        return true;
    }

    public boolean isEmpty() {
        return getId() == 0 && this.mBody.isEmpty();
    }

    public boolean isMultiPackage() {
        TableBlock tableBlock = getTableBlock();
        if (tableBlock != null) {
            return tableBlock.isMultiPackage();
        }
        return false;
    }

    public boolean isSimilarTo(PackageBlock packageBlock) {
        if (packageBlock == this) {
            return true;
        }
        return packageBlock != null && getId() == packageBlock.getId() && getName().equals(packageBlock.getName()) && getTypeStringPool().size() == packageBlock.getTypeStringPool().size() && mo35getSpecStringPool().size() == packageBlock.mo35getSpecStringPool().size();
    }

    public void linkSpecStringsInternal(SpecStringPool specStringPool) {
        Iterator<SpecTypePair> specTypePairs = getSpecTypePairs();
        while (specTypePairs.hasNext()) {
            specTypePairs.next().linkSpecStringsInternal(specStringPool);
        }
    }

    public void linkTableStringsInternal(TableStringPool tableStringPool) {
        Iterator<SpecTypePair> specTypePairs = getSpecTypePairs();
        while (specTypePairs.hasNext()) {
            specTypePairs.next().linkTableStringsInternal(tableStringPool);
        }
    }

    public Iterable<SpecTypePair> listSpecTypePairs() {
        return getSpecTypePairArray().listItems();
    }

    public List<StagedAlias> listStagedAlias() {
        return getStagedAliasList().getChildes();
    }

    public void merge(PackageBlock packageBlock) {
        if (packageBlock == null || packageBlock == this) {
            return;
        }
        if (getId() != packageBlock.getId()) {
            eq7.a("Can not merge different id packages: ", getId(), "!=", packageBlock.getId());
            return;
        }
        setName(packageBlock.getName());
        getLibraryBlock().merge(packageBlock.getLibraryBlock());
        mo35getSpecStringPool().merge(packageBlock.mo35getSpecStringPool());
        getSpecTypePairArray().merge(packageBlock.getSpecTypePairArray());
        getOverlayableList().merge(packageBlock.getOverlayableList());
        getStagedAliasList().merge(packageBlock.getStagedAliasList());
    }

    public ResourceEntry mergeWithName(ResourceMergeOption resourceMergeOption, ResourceEntry resourceEntry) {
        ResourceEntry resource = getResource(resourceEntry.getType(), resourceEntry.getName());
        if (resource != null && !resource.isEmpty()) {
            return resource;
        }
        Iterator it = resourceEntry.iterator(resourceMergeOption.getKeepEntryConfigs());
        int resourceId = 0;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!entry.isNull()) {
                Entry entryMergeWithName = mergeWithName(resourceMergeOption, entry);
                if (resourceId == 0) {
                    resourceId = entryMergeWithName.getResourceId();
                }
            }
        }
        ResourceEntry resource2 = getResource(resourceId);
        return resource2 == null ? getTableBlock().getResource(resourceId) : resource2;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkLoaded() {
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
        refreshTypeStringPoolOffset();
        refreshTypeStringPoolCount();
        refreshSpecStringPoolOffset();
        refreshSpecStringCount();
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        removeEmpty();
        super.onPreRefresh();
    }

    public boolean packageNameMatches(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(getName())) {
            return true;
        }
        if (str.equals(getPrefix()) && this.mHasValidPrefix) {
            return true;
        }
        return getLibraryBlock().containsLibraryInfo(str);
    }

    public void parsePublicXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        getPublicXmlParser().parse(xmlPullParser);
    }

    public String refreshFull(boolean z) {
        boolean z2;
        int chunkSize = getHeaderBlock().getChunkSize();
        StringBuilder sb = new StringBuilder();
        boolean z3 = true;
        if (removeUnusedSpecs()) {
            sb.append("Removed unused spec strings");
            z2 = true;
        } else {
            z2 = false;
        }
        mo35getSpecStringPool().sort();
        sortTypes();
        if (!z) {
            if (z2) {
                return sb.toString();
            }
            return null;
        }
        refresh();
        int chunkSize2 = getHeaderBlock().getChunkSize();
        if (chunkSize != chunkSize2) {
            if (z2) {
                sb.append("\n");
            }
            sb.append("Package size changed = ");
            sb.append(chunkSize);
            sb.append(", ");
            sb.append(chunkSize2);
        } else {
            z3 = z2;
        }
        if (z3) {
            return sb.toString();
        }
        return null;
    }

    public void removeEmpty() {
        getSpecTypePairArray().removeEmptyPairs();
    }

    public boolean removeUnusedSpecs() {
        return mo35getSpecStringPool().removeUnusedStrings();
    }

    public int resolveResourceId(String str, String str2) {
        return mo35getSpecStringPool().resolveResourceId(str, str2);
    }

    public StagedAliasEntry searchByStagedResId(int i) {
        Iterator<StagedAlias> it = listStagedAlias().iterator();
        while (it.hasNext()) {
            StagedAliasEntry stagedAliasEntrySearchByStagedResId = it.next().searchByStagedResId(i);
            if (stagedAliasEntrySearchByStagedResId != null) {
                return stagedAliasEntrySearchByStagedResId;
            }
        }
        return null;
    }

    public void serializePublicXml(XmlSerializer xmlSerializer, boolean z) throws IOException {
        if (z) {
            xmlSerializer.startDocument("utf-8", null);
            xmlSerializer.text("\n");
            xmlSerializer.startTag(null, TAG_resources);
            writePackageInfo(xmlSerializer);
        }
        serializePublicXmlTypes(xmlSerializer);
        if (z) {
            xmlSerializer.text("\n");
            xmlSerializer.endTag(null, TAG_resources);
            xmlSerializer.endDocument();
            IOUtil.close(xmlSerializer);
        }
    }

    public void setId(int i) {
        getHeaderBlock().getPackageId().set(i);
        this.mPrefix = null;
        this.mHasValidPrefix = false;
    }

    public void setName(String str) {
        getHeaderBlock().getPackageName().set(str);
        this.mPrefix = null;
        this.mHasValidPrefix = false;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void sortTypes() {
        getSpecTypePairArray().sort();
    }

    public JSONObject toJson(boolean z) {
        JSONArray json;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("arsc_lib_version", ARSCLib.getVersion());
        jSONObject.put(NAME_package_id, getId());
        jSONObject.put(NAME_package_name, getName());
        jSONObject.put(NAME_specs, getSpecTypePairArray().toJson(!z));
        LibraryInfoArray libraryInfoArray = getLibraryBlock().getLibraryInfoArray();
        if (libraryInfoArray.size() > 0) {
            jSONObject.put(NAME_libraries, libraryInfoArray.m23toJson());
        }
        StagedAlias stagedAliasMergeAll = StagedAlias.mergeAll(getStagedAliasList().getChildes());
        if (stagedAliasMergeAll != null) {
            jSONObject.put(NAME_staged_aliases, stagedAliasMergeAll.getStagedAliasEntryArray().m30toJson());
        }
        if (z && (json = getOverlayableList().m74toJson()) != null) {
            jSONObject.put(NAME_overlaybles, json);
        }
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id=");
        sb.append(HexUtil.toHex2((byte) getId()));
        sb.append(", name=");
        sb.append(getName());
        int size = getLibraryBlock().size();
        if (size > 0) {
            sb.append(", libraries=");
            sb.append(size);
        }
        return sb.toString();
    }

    public void trimConfigSizes(int i) {
        getSpecTypePairArray().trimConfigSizes(i);
    }

    public int typeIdOf(String str) {
        return getTypeStringPool().idOf(str);
    }

    public String typeNameOf(int i) {
        TypeString byId = getTypeStringPool().getById(i);
        if (byId != null) {
            return byId.get();
        }
        return null;
    }

    @Override // com.reandroid.arsc.chunk.ParentChunk
    public TableBlock getMainChunk() {
        return getTableBlock();
    }

    @Override // com.reandroid.arsc.chunk.ParentChunk
    /* JADX INFO: renamed from: getSpecStringPool, reason: merged with bridge method [inline-methods] */
    public SpecStringPool mo35getSpecStringPool() {
        return this.mSpecStringPool;
    }

    public Entry getOrCreateEntry(byte b, short s, ResConfig resConfig) {
        return getSpecTypePairArray().getOrCreateEntry(b, s, resConfig);
    }

    public TypeBlock getOrCreateTypeBlock(ResConfig resConfig, String str) {
        return getOrCreateSpecTypePair(str).getOrCreateTypeBlock(resConfig);
    }

    public int resolveResourceId(int i, String str) {
        return mo35getSpecStringPool().resolveResourceId(i, str);
    }

    public Iterator<ResourceEntry> getResources(final String str) {
        return new IterableIterator<SpecTypePair, ResourceEntry>(getSpecTypePairs()) { // from class: com.reandroid.arsc.chunk.PackageBlock.2
            public Iterator<ResourceEntry> iterator(SpecTypePair specTypePair) {
                return str.equals(specTypePair.getTypeName()) ? specTypePair.getResources() : EmptyIterator.of();
            }
        };
    }

    public SpecTypePair getSpecTypePair(String str) {
        return getSpecTypePair(typeIdOf(str));
    }

    public TypeBlock getOrCreateTypeBlock(byte b, String str) {
        return getSpecTypePairArray().getOrCreateTypeBlock(b, str);
    }

    public Entry getOrCreate(String str, String str2, String str3) {
        return getOrCreate(ResConfig.parse(str), str2, str3);
    }

    public SpecTypePair getOrCreateSpecTypePair(String str) {
        return getSpecTypePairArray().getOrCreate(str);
    }

    public Iterator<Entry> getEntries(int i, String str) {
        return mo35getSpecStringPool().getEntries(i, str);
    }

    public Iterator<Entry> getEntries(int i) {
        return getEntries(i, true);
    }

    public Entry getEntry(String str, String str2, String str3) {
        return getSpecTypePairArray().getEntry(str, str2, str3);
    }

    public static void changePackageId(ValueItem valueItem, int i, int i2) {
        if (valueItem instanceof AttributeValue) {
            changePackageIdName(i, i2, (AttributeValue) valueItem);
        }
        changePackageIdValue(i, i2, valueItem);
    }

    public Iterator<Entry> getEntries(String str, String str2) {
        return mo35getSpecStringPool().getEntries(str, str2);
    }

    public Entry getEntry(ResConfig resConfig, String str, String str2) {
        return getSpecTypePairArray().getEntry(resConfig, str, str2);
    }

    public Iterator<Entry> getEntries(int i, int i2) {
        return getEntries(i, i2, true);
    }

    public Entry getEntry(ResConfig resConfig, int i, int i2) {
        return getSpecTypePairArray().getEntry(resConfig, i, i2);
    }

    public Iterator<Entry> getEntries(int i, int i2, boolean z) {
        SpecTypePair specTypePair = getSpecTypePair(i);
        if (specTypePair != null) {
            return specTypePair.getEntries(i2, z);
        }
        return EmptyIterator.of();
    }

    public Entry getEntry(byte b, short s, String str) {
        return getSpecTypePairArray().getEntry(b, s, str);
    }

    public void serializePublicXml(XmlSerializer xmlSerializer) throws IOException {
        serializePublicXml(xmlSerializer, true);
    }

    public void serializePublicXml(File file) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(file);
        serializePublicXml(xmlSerializerNewSerializer);
        IOUtil.close(xmlSerializerNewSerializer);
    }

    public ResourceEntry getResource(int i, int i2) {
        Entry anyEntry;
        SpecTypePair specTypePair = getSpecTypePair(i);
        if (specTypePair == null || (anyEntry = specTypePair.getAnyEntry((short) i2)) == null) {
            return null;
        }
        return new ResourceEntry(this, anyEntry.getResourceId());
    }

    public ResourceEntry getResource(ResourceName resourceName) {
        if (resourceName == null || !resourceName.matchesPackageName(getName())) {
            return null;
        }
        return getResource(resourceName.getType(), resourceName.getName());
    }

    public ResourceEntry getResource(String str, String str2) {
        SpecTypePair specTypePair = getSpecTypePair(str);
        if (specTypePair != null) {
            return specTypePair.getResource(str2);
        }
        return null;
    }

    private Entry mergeWithName(ResourceMergeOption resourceMergeOption, Entry entry) {
        Entry orCreate = getOrCreate(entry.getResConfig(), entry.getTypeName(), entry.getName());
        orCreate.mergeWithName(resourceMergeOption, entry);
        return orCreate;
    }

    public String refreshFull() {
        return refreshFull(true);
    }

    @Override // 
    public JSONObject toJson() {
        return toJson(true);
    }

    public static class PublicXmlParser {
        private boolean mInitializeIds;
        private final PackageBlock packageBlock;

        private PublicXmlParser(PackageBlock packageBlock) {
            this.mInitializeIds = true;
            this.packageBlock = packageBlock;
        }

        private boolean isInitializeIds() {
            return this.mInitializeIds;
        }

        private void parseElements(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            while (XMLUtil.ensureStartTag(xmlPullParser) == 2) {
                parsePublicTag(XMLElement.parseElement(xmlPullParser));
            }
        }

        private void parsePublicTag(XMLElement xMLElement) throws XmlEncodeException {
            if (PackageBlock.TAG_public.equals(xMLElement.getName())) {
                String str = PackageBlock.ATTR_id;
                String attributeValue = xMLElement.getAttributeValue(str);
                String str2 = PackageBlock.ATTR_type;
                String attributeValue2 = xMLElement.getAttributeValue(str2);
                String str3 = PackageBlock.ATTR_name;
                String attributeValue3 = xMLElement.getAttributeValue(str3);
                if (attributeValue == null) {
                    q0g.a("Missing attribute: '", str, "', ", xMLElement.getDebugText());
                    return;
                }
                if (attributeValue2 == null) {
                    q0g.a("Missing attribute: '", str2, "', ", xMLElement.getDebugText());
                    return;
                }
                if (attributeValue3 == null) {
                    q0g.a("Missing attribute: '", str3, "', ", xMLElement.getDebugText());
                    return;
                }
                EncodeResult encodeResultEncodeHexOrInteger = ValueCoder.encodeHexOrInteger(attributeValue.trim());
                if (encodeResultEncodeHexOrInteger == null) {
                    o0g.a("Invalid id value: ", xMLElement.getDebugText());
                    return;
                }
                int i = encodeResultEncodeHexOrInteger.value;
                int i2 = (i >> 24) & 255;
                int id = this.packageBlock.getId();
                if (id == 0) {
                    this.packageBlock.setId(i2);
                } else if (id != i2) {
                    return;
                }
                int i3 = (i >> 16) & 255;
                if (i3 == 0) {
                    q0g.a("Type id is zero: '", attributeValue, "', ", xMLElement.getDebugText());
                    return;
                }
                TypeBlock orCreateTypeBlock = this.packageBlock.getOrCreateTypeBlock((byte) this.packageBlock.getOrCreateTypeString(i3, attributeValue2).getId(), XmlPullParser.NO_NAMESPACE);
                Entry orCreateEntry = orCreateTypeBlock.getOrCreateEntry((short) (i & 65535));
                orCreateEntry.setName(attributeValue3, true);
                if (isInitializeIds() && orCreateTypeBlock.isTypeId()) {
                    orCreateEntry.setValueAsBoolean(false);
                    ValueHeader header = orCreateEntry.getHeader();
                    header.setPublic(true);
                    header.setWeak(true);
                }
            }
        }

        private void parseResourcesAttributes(XMLElement xMLElement) throws IOException {
            String attributeValue = xMLElement.getAttributeValue(PackageBlock.ATTR_package);
            if (!StringsUtil.isWhiteSpace(attributeValue) && !PackageBlock.EMPTY_PACKAGE_NAME.equals(attributeValue)) {
                this.packageBlock.setName(attributeValue);
            }
            String attributeValue2 = xMLElement.getAttributeValue(PackageBlock.ATTR_id);
            if (!StringsUtil.isWhiteSpace(attributeValue2) && this.packageBlock.getId() == 0) {
                EncodeResult encodeResultEncodeHexOrInteger = ValueCoder.encodeHexOrInteger(attributeValue2);
                if (encodeResultEncodeHexOrInteger == null || !PackageBlock.isPackageId(encodeResultEncodeHexOrInteger.value)) {
                    throw new XmlEncodeException("Invalid id value: '" + xMLElement.getDebugText() + "'");
                }
                this.packageBlock.setId(encodeResultEncodeHexOrInteger.value);
            }
            if ("true".equals(xMLElement.getAttributeValue(TableBlock.ATTR_null_table))) {
                this.packageBlock.getTableBlock().setNull(true);
            }
        }

        public PublicXmlParser parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            parseResourcesAttributes(xmlPullParser);
            parseElements(xmlPullParser);
            IOUtil.close(xmlPullParser);
            return this;
        }

        public PublicXmlParser setInitializeIds(boolean z) {
            this.mInitializeIds = z;
            return this;
        }

        private void parseResourcesAttributes(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            boolean z;
            if (xmlPullParser.getEventType() == 0) {
                xmlPullParser.next();
                z = true;
            } else {
                z = false;
            }
            if (XMLUtil.ensureStartTag(xmlPullParser) == 2) {
                String str = PackageBlock.TAG_resources;
                if (!str.equals(xmlPullParser.getName())) {
                    if (z) {
                        o0g.a("Expecting <resources> tag but found: ", xmlPullParser.getName());
                        return;
                    }
                    return;
                } else {
                    XMLElement xMLElement = new XMLElement(str);
                    xMLElement.parseAttributes(xmlPullParser);
                    parseResourcesAttributes(xMLElement);
                    xmlPullParser.next();
                    return;
                }
            }
            o0g.a("Expecting xml state START_TAG but found: ", XMLUtil.toEventName(xmlPullParser.getEventType()));
        }
    }
}
