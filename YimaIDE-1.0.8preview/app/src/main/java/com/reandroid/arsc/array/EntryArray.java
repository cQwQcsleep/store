package com.reandroid.arsc.array;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.pool.SpecStringPool;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class EntryArray extends OffsetBlockArray<Entry> implements JSONConvert<JSONArray> {
    public EntryArray(OffsetArray offsetArray, IntegerReference integerReference, IntegerReference integerReference2) {
        super(offsetArray, integerReference, integerReference2);
    }

    private void fromJsonNonSparse(JSONArray jSONArray) {
        int length = jSONArray.length();
        ensureSize(length);
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                int i2 = jSONObjectOptJSONObject.getInt(TypeBlock.NAME_id);
                ensureSize(i2 + 1);
                super.get(i2).fromJson(jSONObjectOptJSONObject);
            }
        }
    }

    private void fromJsonSparse(JSONArray jSONArray) {
        SparseOffsetsArray sparseOffsetsArray = (SparseOffsetsArray) getOffsetArray();
        sparseOffsetsArray.setSize(0);
        int length = jSONArray.length();
        ensureSize(length);
        sparseOffsetsArray.setSize(length);
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject == null) {
                sparseOffsetsArray.setIdx(i, -1);
            } else {
                int i2 = jSONObjectOptJSONObject.getInt(TypeBlock.NAME_id);
                Entry entry = super.get(i);
                sparseOffsetsArray.setIdx(i, i2);
                entry.fromJson(jSONObjectOptJSONObject);
            }
        }
    }

    private void mergeNonSparse(EntryArray entryArray) {
        ensureSize(entryArray.size());
        Iterator<T> it = entryArray.iterator(true);
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            super.get(entry.getIndex()).merge(entry);
        }
    }

    private void mergeSparse(EntryArray entryArray) {
        Iterator<T> it = entryArray.iterator(true);
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            getOrCreate((short) entry.getId()).merge(entry);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateHighestCount(int i) {
        SpecTypePair specTypePair = (SpecTypePair) getParentInstance(SpecTypePair.class);
        if (specTypePair == null) {
            ensureSize(i);
            return;
        }
        int highestEntryCount = specTypePair.getHighestEntryCount();
        if (i <= highestEntryCount) {
            i = highestEntryCount;
        }
        ensureSize(i);
    }

    public void destroy() {
        for (T t : listItems()) {
            if (t != null) {
                t.setNull(true);
            }
        }
        clear();
    }

    public void fromJson(JSONArray jSONArray) {
        clear();
        if (isSparse()) {
            fromJsonSparse(jSONArray);
        } else {
            fromJsonNonSparse(jSONArray);
        }
        refreshCountAndStart();
    }

    public Entry get(short s) {
        return getEntry(s);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Entry getEntry(String str) {
        TypeBlock typeBlock;
        PackageBlock packageBlock;
        if (str == null || (typeBlock = (TypeBlock) getParentInstance(TypeBlock.class)) == null || (packageBlock = typeBlock.getPackageBlock()) == null) {
            return null;
        }
        Iterator<Entry> entries = packageBlock.getEntries(typeBlock.getTypeName(), str);
        while (entries.hasNext()) {
            Entry next = entries.next();
            if (next.getParentInstance(EntryArray.class) == this) {
                return next;
            }
        }
        return null;
    }

    public int getEntryId(int i) {
        OffsetArray offsetArray = getOffsetArray();
        return offsetArray instanceof SparseOffsetsArray ? ((SparseOffsetsArray) offsetArray).getIdx(i) : i;
    }

    public int getEntryIndex(int i) {
        OffsetArray offsetArray = getOffsetArray();
        return offsetArray instanceof SparseOffsetsArray ? ((SparseOffsetsArray) offsetArray).indexOf(i) : i;
    }

    public int getHighestEntryId() {
        return isSparse() ? ((SparseOffsetsArray) getOffsetArray()).getHighestId() : size() - 1;
    }

    public Entry getOrCreate(short s) {
        int i = s & 65535;
        Entry entry = getEntry(i);
        if (entry != null) {
            return entry;
        }
        boolean zIsSparse = isSparse();
        int size = zIsSparse ? size() + 1 : i + 1;
        updateHighestCount(size);
        if (!zIsSparse) {
            refreshCount();
            return super.get(i);
        }
        SparseOffsetsArray sparseOffsetsArray = (SparseOffsetsArray) getOffsetArray();
        sparseOffsetsArray.ensureArraySize(size());
        int i2 = size - 1;
        sparseOffsetsArray.setIdx(i2, i);
        refreshCount();
        return super.get(i2);
    }

    public Boolean hasComplexEntry() {
        Iterator<T> it = iterator(true);
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (entry.isComplex()) {
                return Boolean.TRUE;
            }
            ValueType valueType = entry.getResValue().getValueType();
            if (valueType != null && valueType != ValueType.REFERENCE && valueType != ValueType.NULL) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public boolean isEmptyEntries() {
        return !iterator(true).hasNext();
    }

    public boolean isSparse() {
        return super.getOffsetArray() instanceof SparseOffsetsArray;
    }

    public void linkSpecStringsInternal(SpecStringPool specStringPool) {
        Iterator<T> it = iterator(true);
        while (it.hasNext()) {
            ((Entry) it.next()).linkSpecStringsInternal(specStringPool);
        }
    }

    public void linkTableStringsInternal(TableStringPool tableStringPool) {
        Iterator<T> it = iterator(true);
        while (it.hasNext()) {
            ((Entry) it.next()).linkTableStringsInternal(tableStringPool);
        }
    }

    public void merge(EntryArray entryArray) {
        if (entryArray == null || entryArray == this || entryArray.isEmptyEntries()) {
            return;
        }
        if (isSparse()) {
            mergeSparse(entryArray);
        } else {
            mergeNonSparse(entryArray);
        }
        refreshCountAndStart();
    }

    public Entry newInstance() {
        return new Entry();
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m21toJson() {
        JSONArray jSONArray = new JSONArray();
        Iterator<T> it = iterator(true);
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            JSONObject json = entry.toJson();
            if (json != null) {
                json.put(TypeBlock.NAME_id, entry.getId());
                jSONArray.put(json);
            }
        }
        return jSONArray;
    }

    @Override // com.reandroid.arsc.array.OffsetBlockArray
    public String toString() {
        return getClass().getSimpleName() + ": size=" + size();
    }

    public Entry getEntry(int i) {
        return super.get(getEntryIndex(i));
    }

    public Entry getEntry(short s) {
        return getEntry(s & 65535);
    }
}
