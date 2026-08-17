package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.SpecBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.io.IOException;
import java.util.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SpecFlagsArray extends IntegerArrayBlock implements BlockLoad, JSONConvert<JSONArray> {
    private final IntegerItem entryCount;
    private AbstractList<SpecFlag> specFlagList;

    public SpecFlagsArray(IntegerItem integerItem) {
        this.entryCount = integerItem;
        integerItem.setBlockLoad(this);
        setBlockLoad(this);
    }

    private void flagFromJson(JSONObject jSONObject) {
        setFlag(jSONObject.getInt(TypeBlock.NAME_id), jSONObject.getInt(SpecBlock.NAME_flag));
    }

    private void setFlag(int i, int i2) {
        int i3 = i & 65535;
        ensureArraySize(i3 + 1);
        super.put(i3, i2);
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            setSize(0);
            refresh();
            return;
        }
        int length = jSONArray.length() - 1;
        flagFromJson(jSONArray.getJSONObject(length));
        for (int i = 0; i < length; i++) {
            flagFromJson(jSONArray.getJSONObject(i));
        }
        refresh();
    }

    @Override // com.reandroid.arsc.item.IntegerArrayBlock, com.reandroid.arsc.item.ShortArrayBlock
    public int get(int i) {
        return super.get(i & 65535);
    }

    public SpecFlag getFlag(int i) {
        int i2 = i & 65535;
        if (i2 >= size()) {
            return null;
        }
        return new SpecFlag(this, i2 * 4);
    }

    public AbstractList<SpecFlag> listSpecFlags() {
        if (this.specFlagList == null) {
            this.specFlagList = new AbstractList<SpecFlag>() { // from class: com.reandroid.arsc.item.SpecFlagsArray.1
                @Override // java.util.AbstractList, java.util.List
                public SpecFlag get(int i) {
                    return SpecFlagsArray.this.getFlag(i);
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return SpecFlagsArray.this.size();
                }
            };
        }
        return this.specFlagList;
    }

    public void merge(SpecFlagsArray specFlagsArray) {
        if (specFlagsArray == null || specFlagsArray == this) {
            return;
        }
        ensureArraySize(specFlagsArray.size());
        int[] array = specFlagsArray.toArray();
        int[] array2 = toArray();
        for (int i = 0; i < array.length; i++) {
            int i2 = array[i];
            if (i2 != 0) {
                put(i, i2 | array2[i]);
            }
        }
        refresh();
    }

    @Override // com.reandroid.arsc.io.BlockLoad
    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        IntegerItem integerItem = this.entryCount;
        if (block == integerItem) {
            super.setSize(integerItem.get());
        }
    }

    public void refresh() {
        this.entryCount.set(size());
    }

    public void set(int i, int i2) {
        setFlag(i, i2);
        refresh();
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m68toJson() {
        JSONArray jSONArray = new JSONArray();
        int[] array = toArray();
        for (int i = 0; i < array.length; i++) {
            int i2 = array[i];
            if (i2 != 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(TypeBlock.NAME_id, i);
                jSONObject.put(SpecBlock.NAME_flag, i2);
                jSONArray.put(jSONObject);
            }
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        return jSONArray;
    }
}
