package com.reandroid.dex.smali.model;

import com.intellij.psi.PsiKeyword;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueNull extends SmaliValue {
    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(PsiKeyword.NULL);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.NULL;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        if (smaliReader.startsWith(new byte[]{110, 117, 108, 108})) {
            smaliReader.skip(4);
        } else {
            l78.a("Not 'null' value", smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public NullValueKey getKey() {
        return NullValueKey.INSTANCE;
    }
}
