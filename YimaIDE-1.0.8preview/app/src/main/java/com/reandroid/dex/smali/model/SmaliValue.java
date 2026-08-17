package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.value.DexValueType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliValue extends Smali implements KeyReference {
    @Override // com.reandroid.dex.key.KeyItem
    public abstract Key getKey();

    public abstract DexValueType<?> getValueType();

    @Override // com.reandroid.dex.smali.SmaliParser
    public abstract void parse(SmaliReader smaliReader) throws IOException;

    @Override // com.reandroid.dex.key.KeyReference
    public abstract void setKey(Key key);
}
