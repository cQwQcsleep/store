package com.reandroid.dex.value;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.base.Block;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliValue;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NullValue extends DexValueBlock<Block> {
    public static final NullValue PLACE_HOLDER = new NullValue();

    public NullValue() {
        super(DexValueType.NULL);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(PsiKeyword.NULL);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void fromSmali(SmaliValue smaliValue) {
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public DexValueType<NullValue> getValueType() {
        return DexValueType.NULL;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public int hashCode() {
        return getValueType().getType();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void merge(DexValueBlock<?> dexValueBlock) {
        super.merge(dexValueBlock);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public String toString() {
        return "NullValue";
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public NullValueKey getKey() {
        return NullValueKey.INSTANCE;
    }
}
