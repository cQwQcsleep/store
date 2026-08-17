package com.reandroid.dex.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliValue;
import com.reandroid.dex.smali.model.SmaliValueBoolean;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BooleanValue extends DexValueBlock<Block> {
    public BooleanValue() {
        super(DexValueType.BOOLEAN);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) Boolean.toString(get()));
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && get() == ((BooleanValue) obj).get();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void fromSmali(SmaliValue smaliValue) {
        set(((SmaliValueBoolean) smaliValue).getValue());
    }

    public boolean get() {
        return getValueSize() == 1;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public TypeKey getDataTypeKey() {
        return TypeKey.TYPE_Z;
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public PrimitiveKey getKey() {
        return PrimitiveKey.of(get());
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.BOOLEAN;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public int hashCode() {
        return ((getValueType().getType() + 31) * 31) + getValueSize();
    }

    public void set(boolean z) {
        setValueSize(z ? 1 : 0);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        set(((PrimitiveKey.BooleanKey) key).value());
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public String toString() {
        return Boolean.toString(get());
    }
}
