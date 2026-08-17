package com.reandroid.arsc.array;

import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.arsc.value.AttributeDataFormat;
import com.reandroid.arsc.value.AttributeType;
import com.reandroid.arsc.value.ResTableMapEntry;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.ValueItem;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class CompoundItemArray<T extends ResValueMap> extends BlockArray<T> implements JSONConvert<JSONArray>, Comparator<ResValueMap> {
    /* JADX WARN: Multi-variable type inference failed */
    private void updateCountToHeader() {
        ((ResTableMapEntry) getParent(ResTableMapEntry.class)).getHeader().setValuesCount(size());
    }

    public void clear() {
        onRemoved();
        super.clear();
    }

    @Override // java.util.Comparator
    public int compare(ResValueMap resValueMap, ResValueMap resValueMap2) {
        if (resValueMap == resValueMap2) {
            return 0;
        }
        if (resValueMap == null) {
            return 1;
        }
        return resValueMap.compareTo(resValueMap2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsType(AttributeType attributeType) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (attributeType == ((ResValueMap) it.next()).getAttributeType()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T createNext() {
        T tCreateNext = super/*com.reandroid.arsc.container.BlockList*/.createNext();
        updateCountToHeader();
        return tCreateNext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONArray jSONArray) {
        clear();
        if (jSONArray == null) {
            return;
        }
        int length = jSONArray.length();
        ensureSize(length);
        for (int i = 0; i < length; i++) {
            get(i).fromJson(jSONArray.getJSONObject(i));
        }
        updateCountToHeader();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T getByName(int i) {
        for (T t : this) {
            if (t != null && i == t.getNameId()) {
                return t;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T getByType(AttributeType attributeType) {
        if (attributeType == null) {
            return null;
        }
        for (T t : this) {
            if (attributeType == t.getAttributeType()) {
                return t;
            }
        }
        return null;
    }

    public AttributeDataFormat[] getFormats() {
        ResValueMap byType = getByType(AttributeType.FORMATS);
        if (byType != null) {
            return AttributeDataFormat.decodeValueTypes(byType.getData() & 255);
        }
        return null;
    }

    public T getOrCreateType(AttributeType attributeType) {
        if (attributeType == null) {
            return null;
        }
        T t = (T) getByType(attributeType);
        if (t != null) {
            return t;
        }
        T t2 = (T) createNext();
        t2.setAttributeType(attributeType);
        return t2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void merge(CompoundItemArray<?> compoundItemArray) {
        if (compoundItemArray == 0 || compoundItemArray == this) {
            return;
        }
        clear();
        int size = compoundItemArray.size();
        ensureSize(size);
        for (int i = 0; i < size; i++) {
            get(i).merge((ValueItem) compoundItemArray.get(i));
        }
        updateCountToHeader();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void mergeWithName(ResourceMergeOption resourceMergeOption, CompoundItemArray<?> compoundItemArray) {
        if (compoundItemArray == 0 || compoundItemArray == this) {
            return;
        }
        clear();
        int size = compoundItemArray.size();
        ensureSize(size);
        for (int i = 0; i < size; i++) {
            get(i).mergeWithName(resourceMergeOption, compoundItemArray.get(i));
        }
        updateCountToHeader();
    }

    public void onPreRefresh() {
        updateCountToHeader();
    }

    public void onRefreshed() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onRemoved() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((ResValueMap) it.next()).onRemoved();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void sort() {
        super/*com.reandroid.arsc.container.BlockList*/.sort(this);
        updateCountToHeader();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.json.JSONConvert
    public JSONArray toJson() {
        JSONArray jSONArray = new JSONArray();
        if (!isNull()) {
            Iterator it = iterator();
            int i = 0;
            while (it.hasNext()) {
                jSONArray.put(i, ((ResValueMap) it.next()).toJson());
                i++;
            }
        }
        return jSONArray;
    }
}
