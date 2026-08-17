package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliInterface extends Smali implements SmaliRegion {
    private TypeKey type;

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        smaliWriter.append((CharSequence) getKey().getTypeName());
    }

    public TypeKey getKey() {
        return this.type;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.IMPLEMENTS;
    }

    public String getTypeName() {
        TypeKey typeKey = this.type;
        if (typeKey != null) {
            return typeKey.getTypeName();
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        SmaliDirective.parse(smaliReader);
        setKey(TypeKey.read(smaliReader));
    }

    public void setKey(Key key) {
        this.type = (TypeKey) key;
    }
}
