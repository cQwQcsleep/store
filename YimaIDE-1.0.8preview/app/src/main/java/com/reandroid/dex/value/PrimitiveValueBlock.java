package com.reandroid.dex.value;

import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PrimitiveValueBlock extends DexValueBlock<NumberValue> {
    public PrimitiveValueBlock(DexValueType<?> dexValueType) {
        super(new NumberValue(), dexValueType);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.smali.SmaliFormat
    public abstract void append(SmaliWriter smaliWriter) throws IOException;

    @Override // com.reandroid.dex.value.DexValueBlock
    public abstract TypeKey getDataTypeKey();

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public abstract PrimitiveKey getKey();

    public long getSignedValue() {
        return getValueContainer().getSignedNumber();
    }

    public long getUnsigned() {
        return getValueContainer().getUnsignedNumber();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void merge(DexValueBlock<?> dexValueBlock) {
        super.merge(dexValueBlock);
        getValueContainer().merge(((PrimitiveValueBlock) dexValueBlock).getValueContainer());
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        getValueTypeItem().onReadBytes(blockReader);
        NumberValue valueContainer = getValueContainer();
        valueContainer.setSize(getValueSize() + 1);
        valueContainer.readBytes(blockReader);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyReference
    public abstract void setKey(Key key);

    public void setNumberValue(long j) {
        if (j < 0) {
            int i = (int) j;
            if (i == j) {
                setNumberValue(i);
                return;
            }
        }
        NumberValue valueContainer = getValueContainer();
        valueContainer.setNumberValue(j);
        setValueSize(valueContainer.getSize() - 1);
    }

    public void setUnsignedValue(long j) {
        NumberValue valueContainer = getValueContainer();
        valueContainer.setUnsignedNumber(j);
        setValueSize(valueContainer.getSize() - 1);
    }

    public void setValue(long j, int i) {
        NumberValue valueContainer = getValueContainer();
        valueContainer.setNumber(j, i);
        setValueSize(valueContainer.getSize() - 1);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    public void setNumberValue(short s) {
        byte b;
        if (s < 0 && (b = (byte) s) == s) {
            setNumberValue(b);
            return;
        }
        NumberValue valueContainer = getValueContainer();
        valueContainer.setNumberValue(s);
        setValueSize(valueContainer.getSize() - 1);
    }

    public void setNumberValue(int i) {
        short s;
        if (i < 0 && (s = (short) i) == i) {
            setNumberValue(s);
            return;
        }
        NumberValue valueContainer = getValueContainer();
        valueContainer.setNumberValue(i);
        setValueSize(valueContainer.getSize() - 1);
    }

    public void setNumberValue(byte b) {
        NumberValue valueContainer = getValueContainer();
        valueContainer.setNumberValue(b);
        setValueSize(valueContainer.getSize() - 1);
    }
}
