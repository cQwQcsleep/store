package com.reandroid.arsc.value.bag;

import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BagItem {
    private final int data;
    protected final ResValueMap mBagItem;
    private final StringItem string;
    private final ValueType valueType;

    public BagItem(ValueType valueType, int i) {
        if (valueType == ValueType.STRING) {
            w01.a("Use the string constructor instead");
            throw null;
        }
        this.mBagItem = null;
        this.string = null;
        this.valueType = valueType;
        this.data = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void copyTo(ResValueMap resValueMap) {
        ResValueMap resValueMap2 = this.mBagItem;
        if (resValueMap2 != null) {
            resValueMap.setTypeAndData(resValueMap2.getValueType(), this.mBagItem.getData());
            return;
        }
        ValueType valueType = this.valueType;
        ValueType valueType2 = ValueType.STRING;
        if (valueType != valueType2) {
            resValueMap.setTypeAndData(valueType, this.data);
            return;
        }
        TableStringPool stringPool = resValueMap.getStringPool();
        Object parent = this.string.getParent(TableStringPool.class);
        StringItem stringItem = this.string;
        if (stringPool == parent) {
            resValueMap.setTypeAndData(valueType2, stringItem.getIndex());
        } else {
            resValueMap.setTypeAndData(valueType2, stringPool.getOrCreate(stringItem.get()).getIndex());
        }
    }

    public ResValueMap getBagItem() {
        return this.mBagItem;
    }

    public String getStringValue() {
        ResValueMap resValueMap = this.mBagItem;
        if (resValueMap != null) {
            return resValueMap.getValueAsString();
        }
        if (this.valueType == ValueType.STRING) {
            return this.string.getHtml();
        }
        w01.a("Not a string");
        return null;
    }

    public int getValue() {
        ResValueMap resValueMap = this.mBagItem;
        if (resValueMap != null) {
            return resValueMap.getData();
        }
        return this.valueType == ValueType.STRING ? this.string.getIndex() : this.data;
    }

    public ValueType getValueType() {
        ResValueMap resValueMap = this.mBagItem;
        return resValueMap != null ? resValueMap.getValueType() : this.valueType;
    }

    public boolean hasReferenceValue() {
        return getValueType() == ValueType.REFERENCE;
    }

    public boolean hasStringValue() {
        return getValueType() == ValueType.STRING;
    }

    public BagItem(ResValueMap resValueMap) {
        this.mBagItem = resValueMap;
        this.valueType = null;
        this.string = null;
        this.data = 0;
    }

    public BagItem(StringItem stringItem) {
        this.string = stringItem;
        this.mBagItem = null;
        this.valueType = ValueType.STRING;
        this.data = 0;
    }
}
