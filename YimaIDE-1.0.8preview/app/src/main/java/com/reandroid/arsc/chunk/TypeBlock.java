package com.reandroid.arsc.chunk;

import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.header.TypeHeader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.SpecString;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.list.EntryItemList;
import com.reandroid.arsc.list.EntryItemOffsetList;
import com.reandroid.arsc.pool.TypeStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.ValueItem;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.IterableIterator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TypeBlock extends Chunk<TypeHeader> implements Iterable<Entry>, JSONConvert<JSONObject>, Comparable<TypeBlock> {
    public static final String NAME_config = "config";
    public static final String NAME_entries = "entries";
    public static final String NAME_id = "id";
    public static final String NAME_is_offset16 = "is_offset16";
    public static final String NAME_is_sparse = "is_sparse";
    public static final String NAME_name = "name";
    private final EntryItemList mEntryArray;
    private TypeString mTypeString;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.EntryItemOffsetList] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.EntryItemList] */
    public TypeBlock() {
        super(new TypeHeader(), 2);
        TypeHeader typeHeader = (TypeHeader) getHeaderBlock();
        ?? entryItemOffsetList = new EntryItemOffsetList(typeHeader.getCountItem());
        ?? entryItemList = new EntryItemList(typeHeader, entryItemOffsetList);
        this.mEntryArray = entryItemList;
        addChild(entryItemOffsetList);
        addChild(entryItemList);
    }

    public static boolean canHaveResourceFile(String str) {
        return !isEqualTypeName("string", str);
    }

    private TypeStringPool getTypeStringPool() {
        PackageBlock packageBlock = getPackageBlock();
        return packageBlock != null ? packageBlock.getTypeStringPool() : (TypeStringPool) ObjectsUtil.cast((Object) null);
    }

    public static boolean isEqualTypeName(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        if (str2 == null) {
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        return trimTypeName(str).equals(trimTypeName(str2));
    }

    private static boolean isWildTypeNamePrefix(char c) {
        return c == '*' || c == '+' || c == '^';
    }

    private void onSetEntryCount(int i) {
        getEntryArray().setSize(i);
    }

    private static String trimTypeName(String str) {
        while (str.length() > 0 && isWildTypeNamePrefix(str.charAt(0))) {
            str = str.substring(1);
        }
        return str;
    }

    public Iterator<ValueItem> allValues() {
        return new IterableIterator<Entry, ValueItem>(iterator()) { // from class: com.reandroid.arsc.chunk.TypeBlock.1
            public Iterator<ValueItem> iterator(Entry entry) {
                return entry.allValues();
            }
        };
    }

    public String buildUniqueDirectoryName() {
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null || !packageBlock.hasValidTypeNames()) {
            return "type_" + HexUtil.toHex2(getTypeId()) + getResConfig().getQualifiers();
        }
        return getTypeName() + getResConfig().getQualifiers();
    }

    public void clear() {
        getEntryArray().clear();
    }

    @Override // java.lang.Comparable
    public int compareTo(TypeBlock typeBlock) {
        int id = getId();
        int id2 = typeBlock.getId();
        if (id != id2) {
            return CompareUtil.compare(id, id2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(isSparse() ? "1" : "0");
        sb.append(getResConfig().getQualifiers());
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(typeBlock.isSparse() ? "1" : "0");
        sb2.append(typeBlock.getResConfig().getQualifiers());
        return string.compareTo(sb2.toString());
    }

    public void destroy() {
        getEntryArray().destroy();
        setId(0);
        setParent(null);
    }

    public void ensureEntriesCount(int i) {
        getEntryArray().ensureSize(i);
    }

    public void fromJson(JSONObject jSONObject) {
        setId(jSONObject.getInt(NAME_id));
        String strOptString = jSONObject.optString(NAME_name);
        if (strOptString != null) {
            setTypeName(strOptString);
        }
        if (isEmpty()) {
            getHeaderBlock().setOffsetType(jSONObject.optBoolean(NAME_is_sparse, false), jSONObject.optBoolean(NAME_is_offset16, false));
        }
        getResConfig().fromJson(jSONObject.getJSONObject(NAME_config));
        getEntryArray().fromJson(jSONObject.getJSONArray(NAME_entries));
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeBytes(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public Entry getEntry(short s) {
        return getEntryArray().getEntry(s);
    }

    public EntryItemList getEntryArray() {
        return this.mEntryArray;
    }

    public int getId() {
        return getHeaderBlock().getId().get();
    }

    public Entry getOrCreateDefinedEntry(String str) {
        int iResolveResourceId;
        Entry entry = getEntry(str);
        if (entry != null) {
            return entry;
        }
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null || (iResolveResourceId = packageBlock.resolveResourceId(getId(), str)) == 0) {
            return null;
        }
        SpecString specString = (SpecString) packageBlock.mo35getSpecStringPool().getOrCreate(str);
        Entry orCreateEntry = getOrCreateEntry((short) (65535 & iResolveResourceId));
        orCreateEntry.setSpecReference(specString);
        return orCreateEntry;
    }

    public Entry getOrCreateEntry(String str) {
        if (str == null) {
            return null;
        }
        Entry entry = getEntry(str);
        if (entry != null) {
            return entry;
        }
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null) {
            return null;
        }
        int iResolveResourceId = packageBlock.resolveResourceId(getId(), str);
        int highestEntryId = iResolveResourceId != 0 ? iResolveResourceId & 65535 : getParentSpecTypePair().getHighestEntryId() + 1;
        SpecString specString = (SpecString) packageBlock.mo35getSpecStringPool().getOrCreate(str);
        Entry orCreateEntry = getOrCreateEntry((short) highestEntryId);
        orCreateEntry.setSpecReference(specString);
        return orCreateEntry;
    }

    public PackageBlock getPackageBlock() {
        SpecTypePair specTypePair = (SpecTypePair) getParent(SpecTypePair.class);
        if (specTypePair != null) {
            return specTypePair.getPackageBlock();
        }
        return null;
    }

    public SpecTypePair getParentSpecTypePair() {
        return (SpecTypePair) getParent(SpecTypePair.class);
    }

    public String getQualifiers() {
        return getResConfig().getQualifiers();
    }

    public ResConfig getResConfig() {
        return getHeaderBlock().getConfig();
    }

    public byte getTypeId() {
        return getHeaderBlock().getId().getByte();
    }

    public String getTypeName() {
        TypeString typeString = getTypeString();
        if (typeString != null) {
            return typeString.get();
        }
        return null;
    }

    public TypeString getTypeString() {
        TypeString typeString = this.mTypeString;
        if (typeString != null) {
            if (typeString.getId() == getTypeId()) {
                return this.mTypeString;
            }
            this.mTypeString = null;
        }
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null) {
            return null;
        }
        TypeString byId = packageBlock.getTypeStringPool().getById(getId());
        this.mTypeString = byId;
        return byId;
    }

    public Boolean hasComplexEntry() {
        SpecTypePair parentSpecTypePair = getParentSpecTypePair();
        if (parentSpecTypePair != null) {
            return parentSpecTypePair.hasComplexEntry();
        }
        return null;
    }

    public boolean isDefault() {
        return getResConfig().isDefault();
    }

    public boolean isEmpty() {
        return getEntryArray().isEmptyEntries();
    }

    public boolean isOffset16() {
        return getHeaderBlock().isOffset16();
    }

    public boolean isSparse() {
        return getHeaderBlock().isSparse();
    }

    public boolean isTypeAttr() {
        TypeString typeString = getTypeString();
        if (typeString != null) {
            return typeString.isTypeAttr();
        }
        return false;
    }

    public boolean isTypeId() {
        TypeString typeString = getTypeString();
        if (typeString != null) {
            return typeString.isTypeId();
        }
        return false;
    }

    @Override // java.lang.Iterable
    public Iterator<Entry> iterator() {
        return getEntryArray().iterator(false);
    }

    public List<Entry> listEntries(boolean z) {
        return CollectionUtil.toList(getEntryArray().iterator(z));
    }

    public void merge(TypeBlock typeBlock) {
        if (typeBlock == null || typeBlock == this) {
            return;
        }
        if (getTypeId() != typeBlock.getTypeId()) {
            eq7.a("Can not merge different id types: ", getTypeId(), "!=", typeBlock.getTypeId());
            return;
        }
        setTypeName(typeBlock.getTypeName());
        if (isEmpty()) {
            getHeaderBlock().setOffsetType(typeBlock.getHeaderBlock().getOffsetType());
        }
        getEntryArray().merge(typeBlock.getEntryArray());
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        getHeaderBlock().getConfig().refresh();
        super.onPreRefresh();
    }

    public int realSize() {
        return getEntryArray().countNonNull();
    }

    public boolean removeNullEntries(int i) {
        int i2 = i & 65535;
        EntryItemList entryArray = getEntryArray();
        entryArray.removeAllNull(i2);
        return entryArray.size() == i2;
    }

    public void setEntryCount(int i) {
        IntegerReference countItem = getHeaderBlock().getCountItem();
        if (i == countItem.get()) {
            return;
        }
        countItem.set(i);
        onSetEntryCount(i);
    }

    public void setId(int i) {
        setTypeId((byte) (i & 255));
    }

    public void setQualifiers(String str) {
        getResConfig().parseQualifiers(str);
    }

    public void setTypeId(byte b) {
        getHeaderBlock().getId().set(b);
    }

    public void setTypeName(String str) {
        TypeStringPool typeStringPool = getTypeStringPool();
        int id = getId();
        TypeString byId = typeStringPool.getById(id);
        if (byId == null) {
            byId = typeStringPool.getOrCreate(id, str);
        }
        byId.set(str);
    }

    public int size() {
        return getEntryArray().size();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        if (isSparse()) {
            jSONObject.put(NAME_is_sparse, true);
        }
        if (isOffset16()) {
            jSONObject.put(NAME_is_offset16, true);
        }
        jSONObject.put(NAME_id, getId());
        jSONObject.put(NAME_name, getTypeName());
        jSONObject.put(NAME_config, getResConfig().toJson());
        jSONObject.put(NAME_entries, getEntryArray().m72toJson());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return getTypeName() + '{' + getHeaderBlock() + '}';
    }

    public Entry getEntry(String str) {
        return getEntryArray().getEntry(str);
    }

    public Entry getEntry(int i) {
        return getEntryArray().getEntry(i);
    }

    public boolean isEqualTypeName(String str) {
        return isEqualTypeName(getTypeName(), str);
    }

    public Entry getOrCreateEntry(short s) {
        return getEntryArray().getOrCreate(s);
    }
}
