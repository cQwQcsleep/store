package com.reandroid.arsc.array;

import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.SpecBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.pool.TypeStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SpecTypePairArray extends BlockArray<SpecTypePair> implements JSONConvert<JSONArray>, Comparator<SpecTypePair> {
    private TypeString getOrCreateTypeString(String str) {
        TypeStringPool typeStringPool = getTypeStringPool();
        if (typeStringPool == null) {
            return null;
        }
        TypeString string = typeStringPool.getString(str);
        return string != null ? string : typeStringPool.getOrCreate(typeStringPool.getLastId() + 1, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PackageBlock getPackageBlock() {
        return (PackageBlock) getParentInstance(PackageBlock.class);
    }

    private TypeStringPool getTypeStringPool() {
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock != null) {
            return packageBlock.getTypeStringPool();
        }
        return null;
    }

    private Map<Byte, Integer> mapHighestEntryCount() {
        HashMap map = new HashMap();
        Iterator it = iterator();
        while (it.hasNext()) {
            SpecTypePair specTypePair = (SpecTypePair) it.next();
            int highestEntryCount = specTypePair.getHighestEntryCount();
            byte typeId = specTypePair.getTypeId();
            Integer num = (Integer) map.get(Byte.valueOf(typeId));
            if (num == null || highestEntryCount > num.intValue()) {
                map.put(Byte.valueOf(typeId), Integer.valueOf(highestEntryCount));
            }
        }
        return map;
    }

    private void validateEntryCounts() {
        for (Map.Entry<Byte, Integer> entry : mapHighestEntryCount().entrySet()) {
            byte bByteValue = entry.getKey().byteValue();
            int iIntValue = entry.getValue().intValue();
            SpecTypePair specTypePair = getSpecTypePair(bByteValue);
            specTypePair.getSpecBlock().setEntryCount(iIntValue);
            specTypePair.getTypeBlockArray().setEntryCount(iIntValue);
        }
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            getOrCreate((byte) jSONObject.getJSONObject(SpecBlock.NAME_spec).getInt(TypeBlock.NAME_id)).fromJson(jSONObject);
        }
    }

    public Entry getAnyEntry(byte b, short s) {
        SpecTypePair specTypePair;
        if (b == 0 || (specTypePair = getSpecTypePair(b)) == null) {
            return null;
        }
        return specTypePair.getAnyEntry(s);
    }

    public Entry getEntry(byte b, short s, String str) {
        TypeBlock typeBlock = getTypeBlock(b, str);
        if (typeBlock == null) {
            return null;
        }
        return typeBlock.getEntry(s);
    }

    public int getHighestTypeId() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            int id = ((SpecTypePair) it.next()).getId();
            if (id > i) {
                i = id;
            }
        }
        return i;
    }

    public SpecTypePair getOrCreate(String str) {
        SpecTypePair specTypePair = getSpecTypePair(str);
        if (specTypePair != null) {
            return specTypePair;
        }
        byte id = (byte) getOrCreateTypeString(str).getId();
        SpecTypePair specTypePair2 = (SpecTypePair) createNext();
        specTypePair2.setTypeId(id);
        return specTypePair2;
    }

    public Entry getOrCreateEntry(byte b, short s, String str) {
        return getOrCreateTypeBlock(b, str).getOrCreateEntry(s);
    }

    public TypeBlock getOrCreateTypeBlock(byte b, String str) {
        return getOrCreate(b).getOrCreateTypeBlock(str);
    }

    public int getSmallestTypeId() {
        Iterator it = iterator();
        int i = 0;
        boolean z = false;
        while (it.hasNext()) {
            int id = ((SpecTypePair) it.next()).getId();
            if (!z) {
                i = id;
            }
            if (id < i) {
                i = id;
            }
            z = true;
        }
        return i;
    }

    public SpecTypePair getSpecTypePair(byte b) {
        SpecTypePair specTypePair = (SpecTypePair) get((b & 255) - 1);
        if (specTypePair != null && specTypePair.getTypeId() == b) {
            return specTypePair;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            SpecTypePair specTypePair2 = (SpecTypePair) it.next();
            if (specTypePair2 != null && specTypePair2.getTypeId() == b) {
                return specTypePair2;
            }
        }
        return null;
    }

    public TypeBlock getTypeBlock(byte b, String str) {
        SpecTypePair specTypePair = getSpecTypePair(b);
        if (specTypePair == null) {
            return null;
        }
        return specTypePair.getTypeBlock(str);
    }

    public boolean isEmpty() {
        Iterator<SpecTypePair> it = iterator(true);
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void merge(SpecTypePairArray specTypePairArray) {
        if (specTypePairArray == null || specTypePairArray == this) {
            return;
        }
        for (SpecTypePair specTypePair : specTypePairArray.listItems()) {
            if (!specTypePair.isEmpty()) {
                getOrCreate(specTypePair.getTypeId()).merge(specTypePair);
            }
        }
    }

    public SpecTypePair newInstance() {
        return new SpecTypePair();
    }

    public void onPreRefresh() {
        validateEntryCounts();
    }

    public void onRefreshed() {
    }

    public void removeEmptyPairs() {
        Iterator itClonedIterator = clonedIterator();
        while (itClonedIterator.hasNext()) {
            SpecTypePair specTypePair = (SpecTypePair) itClonedIterator.next();
            specTypePair.removeEmptyTypeBlocks();
            if (specTypePair.isEmpty()) {
                super.remove(specTypePair);
            }
        }
    }

    @Deprecated
    public SpecTypePair searchByTypeName(String str) {
        if (str == null) {
            return null;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            SpecTypePair specTypePair = (SpecTypePair) it.next();
            if (str.equals(specTypePair.getTypeName())) {
                return specTypePair;
            }
        }
        return null;
    }

    public void sort() {
        Iterator<SpecTypePair> it = listItems().iterator();
        while (it.hasNext()) {
            it.next().sortTypes();
        }
        sort(this);
    }

    public JSONArray toJson(boolean z) {
        JSONArray jSONArray = new JSONArray();
        Iterator<SpecTypePair> it = listItems().iterator();
        int i = 0;
        while (it.hasNext()) {
            JSONObject json = it.next().toJson(z);
            if (json != null) {
                jSONArray.put(i, json);
                i++;
            }
        }
        return jSONArray;
    }

    public void trimConfigSizes(int i) {
        Iterator<SpecTypePair> it = iterator(true);
        while (it.hasNext()) {
            it.next().trimConfigSizes(i);
        }
    }

    @Override // java.util.Comparator
    public int compare(SpecTypePair specTypePair, SpecTypePair specTypePair2) {
        return specTypePair.compareTo(specTypePair2);
    }

    public Entry getOrCreateEntry(byte b, short s, ResConfig resConfig) {
        return getOrCreateTypeBlock(b, resConfig).getOrCreateEntry(s);
    }

    public TypeBlock getOrCreateTypeBlock(byte b, ResConfig resConfig) {
        return getOrCreate(b).getOrCreateTypeBlock(resConfig);
    }

    public TypeBlock getOrCreateTypeBlock(String str, ResConfig resConfig) {
        return getOrCreate(str).getOrCreateTypeBlock(resConfig);
    }

    public TypeBlock getOrCreateTypeBlock(String str, String str2) {
        return getOrCreate(str).getOrCreateTypeBlock(str2);
    }

    public Entry getEntry(ResConfig resConfig, int i, int i2) {
        SpecTypePair specTypePair = getSpecTypePair(i);
        if (specTypePair != null) {
            return specTypePair.getEntry(resConfig, i2);
        }
        return null;
    }

    public Entry getEntry(String str, String str2, String str3) {
        ResConfig resConfig = new ResConfig();
        resConfig.parseQualifiers(str);
        return getEntry(resConfig, str2, str3);
    }

    public Entry getAnyEntry(String str, String str2) {
        SpecTypePair specTypePair = getSpecTypePair(str);
        if (specTypePair != null) {
            return specTypePair.getAnyEntry(str2);
        }
        return null;
    }

    public Entry getEntry(ResConfig resConfig, String str, String str2) {
        SpecTypePair specTypePair = getSpecTypePair(str);
        if (specTypePair != null) {
            return specTypePair.getEntry(resConfig, str2);
        }
        return null;
    }

    public SpecTypePair getOrCreate(byte b) {
        SpecTypePair specTypePair = getSpecTypePair(b);
        if (specTypePair != null) {
            return specTypePair;
        }
        SpecTypePair specTypePair2 = (SpecTypePair) createNext();
        specTypePair2.setTypeId(b);
        return specTypePair2;
    }

    public TypeBlock getOrCreate(byte b, ResConfig resConfig) {
        return getOrCreate(b).getTypeBlockArray().getOrCreate(resConfig);
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m28toJson() {
        return toJson(false);
    }

    public SpecTypePair getSpecTypePair(int i) {
        return getSpecTypePair((byte) i);
    }

    public SpecTypePair getSpecTypePair(String str) {
        SpecTypePair specTypePair;
        if (str == null) {
            return null;
        }
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock != null && (specTypePair = getSpecTypePair(packageBlock.typeIdOf(str))) != null) {
            return specTypePair;
        }
        Iterator<SpecTypePair> it = iterator(true);
        while (it.hasNext()) {
            SpecTypePair next = it.next();
            if (next.isEqualTypeName(str)) {
                return next;
            }
        }
        return null;
    }
}
