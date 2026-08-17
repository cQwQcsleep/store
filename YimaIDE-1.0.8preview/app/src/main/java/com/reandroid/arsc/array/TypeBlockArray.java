package com.reandroid.arsc.array;

import com.reandroid.arsc.array.TypeBlockArray;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.SpecBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.header.TypeHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TypeBlockArray extends BlockArray<TypeBlock> implements JSONConvert<JSONArray>, Comparator<TypeBlock> {
    private Boolean mHasComplexEntry;
    private Map<String, TypeBlock> mQualifiersMap;
    private byte mTypeId;

    private void buildQualifiersMap() {
        HashMap map = new HashMap(size());
        this.mQualifiersMap = map;
        Iterator it = iterator();
        while (it.hasNext()) {
            TypeBlock typeBlock = (TypeBlock) it.next();
            map.put(typeBlock.getQualifiers(), typeBlock);
        }
    }

    private TypeBlock getFromQualifiersMap(String str) {
        Map<String, TypeBlock> map = this.mQualifiersMap;
        if (map == null) {
            buildQualifiersMap();
            return this.mQualifiersMap.get(str);
        }
        TypeBlock typeBlock = map.get(str);
        if ((typeBlock == null || str.equals(typeBlock.getQualifiers())) && (typeBlock == null || typeBlock.getParent() != null)) {
            return typeBlock;
        }
        buildQualifiersMap();
        return this.mQualifiersMap.get(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SpecBlock getSpecBlock() {
        SpecTypePair specTypePair = (SpecTypePair) getParent(SpecTypePair.class);
        if (specTypePair != null) {
            return specTypePair.getSpecBlock();
        }
        return null;
    }

    private boolean readTypeBlockArray(BlockReader blockReader) throws IOException {
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        if (headerBlock == null || headerBlock.getChunkType() != ChunkType.TYPE) {
            return false;
        }
        TypeHeader typeHeader = TypeHeader.read(blockReader);
        byte typeId = getTypeId();
        if (typeId != 0 && typeHeader.getId().get() != typeId) {
            return false;
        }
        int position = blockReader.getPosition();
        ((TypeBlock) createNext()).readBytes(blockReader);
        return blockReader.getPosition() > position;
    }

    public static /* synthetic */ boolean s(TypeBlock typeBlock) {
        if (typeBlock == null || typeBlock.isNull()) {
            return false;
        }
        return !typeBlock.isEmpty();
    }

    public void destroy() {
        for (TypeBlock typeBlock : listItems()) {
            if (typeBlock != null) {
                typeBlock.destroy();
            }
        }
        clear();
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                ((TypeBlock) createNext()).fromJson(jSONArray.getJSONObject(i));
            }
        }
    }

    public Entry getEntry(short s, String str) {
        TypeBlock typeBlock = getTypeBlock(str);
        if (typeBlock == null) {
            return null;
        }
        return typeBlock.getEntry(s);
    }

    public int getHighestEntryCount() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            int size = ((TypeBlock) it.next()).getEntryArray().size();
            if (size > i) {
                i = size;
            }
        }
        return i;
    }

    public int getHighestEntryId() {
        Iterator it = iterator();
        int i = -1;
        while (it.hasNext()) {
            int highestEntryId = ((TypeBlock) it.next()).getEntryArray().getHighestEntryId();
            if (highestEntryId > i) {
                i = highestEntryId;
            }
        }
        return i;
    }

    public TypeBlock getOrCreate(ResConfig resConfig) {
        TypeBlock typeBlock = getTypeBlock(resConfig);
        if (typeBlock != null) {
            return typeBlock;
        }
        TypeBlock typeBlock2 = (TypeBlock) createNext();
        typeBlock2.setId(getTypeId() & 255);
        typeBlock2.getResConfig().copyFrom(resConfig);
        return typeBlock2;
    }

    public Entry getOrCreateEntry(short s, String str) {
        return getOrCreate(str).getOrCreateEntry(s);
    }

    public Iterator<ResConfig> getResConfigs() {
        return new ComputeIterator(super.iterator(true), new Function() { // from class: bte
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((TypeBlock) obj).getResConfig();
            }
        });
    }

    public TypeBlock getTypeBlock(ResConfig resConfig) {
        if (resConfig == null) {
            return null;
        }
        TypeBlock fromQualifiersMap = getFromQualifiersMap(resConfig.getQualifiers());
        if (fromQualifiersMap != null) {
            return fromQualifiersMap;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            TypeBlock typeBlock = (TypeBlock) it.next();
            if (typeBlock != null && resConfig.equals(typeBlock.getResConfig())) {
                return typeBlock;
            }
        }
        return null;
    }

    public byte getTypeId() {
        byte typeId;
        byte typeId2;
        SpecBlock specBlock = getSpecBlock();
        if (specBlock != null && (typeId2 = specBlock.getTypeId()) != 0) {
            return typeId2;
        }
        byte b = this.mTypeId;
        if (b != 0) {
            return b;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            TypeBlock typeBlock = (TypeBlock) it.next();
            if (typeBlock != null && (typeId = typeBlock.getTypeId()) != 0) {
                if (specBlock != null) {
                    specBlock.setTypeId(typeId);
                }
                this.mTypeId = typeId;
                return typeId;
            }
        }
        return (byte) 0;
    }

    public TypeString getTypeString() {
        Iterator it = iterator();
        while (it.hasNext()) {
            TypeString typeString = ((TypeBlock) it.next()).getTypeString();
            if (typeString != null) {
                return typeString;
            }
        }
        return null;
    }

    public Boolean hasComplexEntry() {
        Boolean bool = this.mHasComplexEntry;
        if (bool != null) {
            return bool;
        }
        Iterator<TypeBlock> it = listItems(true).iterator();
        while (it.hasNext()) {
            Boolean boolHasComplexEntry = it.next().getEntryArray().hasComplexEntry();
            if (boolHasComplexEntry != null) {
                this.mHasComplexEntry = boolHasComplexEntry;
            }
        }
        return this.mHasComplexEntry;
    }

    public boolean hasDuplicateResConfig(boolean z) {
        HashSet hashSet = new HashSet();
        Iterator<TypeBlock> itIteratorNonEmpty = z ? iteratorNonEmpty() : iterator(true);
        while (itIteratorNonEmpty.hasNext()) {
            Integer numValueOf = Integer.valueOf(itIteratorNonEmpty.next().getResConfig().hashCode());
            if (hashSet.contains(numValueOf)) {
                return true;
            }
            hashSet.add(numValueOf);
        }
        return false;
    }

    public boolean isEmpty() {
        for (TypeBlock typeBlock : listItems()) {
            if (typeBlock != null && !typeBlock.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Iterator<TypeBlock> iterator(final ResConfig resConfig) {
        return iterator(new Predicate() { // from class: yse
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TypeBlock) obj).getResConfig().equals(resConfig);
            }
        });
    }

    public Iterator<TypeBlock> iteratorNonEmpty() {
        return super.iterator(new Predicate() { // from class: ate
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TypeBlockArray.s((TypeBlock) obj);
            }
        });
    }

    public Set<ResConfig> listResConfig() {
        HashSet hashSet = new HashSet();
        Iterator it = iterator();
        while (it.hasNext()) {
            hashSet.add(((TypeBlock) it.next()).getResConfig());
        }
        return hashSet;
    }

    public void merge(TypeBlockArray typeBlockArray) {
        if (typeBlockArray == null || typeBlockArray == this) {
            return;
        }
        int size = typeBlockArray.size();
        for (int i = 0; i < size; i++) {
            TypeBlock typeBlock = (TypeBlock) typeBlockArray.get(i);
            getOrCreate(typeBlock.getResConfig()).merge(typeBlock);
        }
    }

    public TypeBlock newInstance() {
        byte typeId = getTypeId();
        TypeBlock typeBlock = new TypeBlock();
        typeBlock.setTypeId(typeId);
        return typeBlock;
    }

    public void onChanged() {
        super.onChanged();
        this.mQualifiersMap = null;
    }

    @Override // com.reandroid.arsc.base.BlockArray
    public void onReadBytes(BlockReader blockReader) throws IOException {
        boolean typeBlockArray = true;
        while (typeBlockArray) {
            typeBlockArray = readTypeBlockArray(blockReader);
        }
        this.mQualifiersMap = null;
    }

    public void onRefreshed() {
        this.mQualifiersMap = null;
    }

    public void removeEmptyBlocks() {
        removeIf(new Predicate() { // from class: zse
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TypeBlock) obj).isEmpty();
            }
        });
    }

    public boolean removeNullEntries(int i) {
        Iterator<TypeBlock> it = listItems().iterator();
        while (true) {
            boolean z = true;
            while (it.hasNext()) {
                boolean zRemoveNullEntries = it.next().removeNullEntries(i);
                if (!z || !zRemoveNullEntries) {
                    z = false;
                }
            }
            return z;
        }
    }

    public void setEntryCount(int i) {
        Iterator it = iterator();
        while (it.hasNext()) {
            TypeBlock typeBlock = (TypeBlock) it.next();
            if (!typeBlock.isSparse()) {
                typeBlock.setEntryCount(i);
            }
        }
    }

    public void setTypeId(byte b) {
        this.mTypeId = b;
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TypeBlock) it.next()).setTypeId(b);
        }
    }

    public void sort() {
        sort(this);
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m32toJson() {
        int size = size();
        JSONArray jSONArray = new JSONArray(size);
        for (int i = 0; i < size; i++) {
            JSONObject json = ((TypeBlock) get(i)).toJson();
            if (json != null) {
                jSONArray.put(json);
            }
        }
        return jSONArray;
    }

    @Override // java.util.Comparator
    public int compare(TypeBlock typeBlock, TypeBlock typeBlock2) {
        return typeBlock.compareTo(typeBlock2);
    }

    public Entry getOrCreateEntry(short s, ResConfig resConfig) {
        return getOrCreate(resConfig).getOrCreateEntry(s);
    }

    public Entry getEntry(ResConfig resConfig, String str) {
        TypeBlock typeBlock = getTypeBlock(resConfig);
        if (typeBlock != null) {
            return typeBlock.getEntry(str);
        }
        return null;
    }

    public Entry getEntry(ResConfig resConfig, int i) {
        TypeBlock typeBlock = getTypeBlock(resConfig);
        if (typeBlock != null) {
            return typeBlock.getEntry(i);
        }
        return null;
    }

    public TypeBlock getOrCreate(String str) {
        TypeBlock typeBlock = getTypeBlock(str);
        if (typeBlock != null) {
            return typeBlock;
        }
        int highestEntryCount = getHighestEntryCount();
        TypeBlock typeBlock2 = (TypeBlock) createNext();
        typeBlock2.ensureEntriesCount(highestEntryCount);
        typeBlock2.getResConfig().parseQualifiers(str);
        return typeBlock2;
    }

    public TypeBlock getTypeBlock(String str) {
        if (str == null) {
            return null;
        }
        TypeBlock fromQualifiersMap = getFromQualifiersMap(str);
        if (fromQualifiersMap != null) {
            return fromQualifiersMap;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            TypeBlock typeBlock = (TypeBlock) it.next();
            if (typeBlock.getResConfig().isEqualQualifiers(str)) {
                return typeBlock;
            }
        }
        return null;
    }
}
