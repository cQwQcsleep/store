package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliCodeCatch extends SmaliCodeExceptionHandler {
    private TypeKey type;

    @Override // com.reandroid.dex.smali.model.SmaliCodeExceptionHandler
    public void appendType(SmaliWriter smaliWriter) throws IOException {
        getType().append(smaliWriter);
        smaliWriter.append(' ');
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.CATCH;
    }

    public TypeKey getType() {
        return this.type;
    }

    @Override // com.reandroid.dex.smali.model.SmaliCodeExceptionHandler
    public void parseType(SmaliReader smaliReader) throws IOException {
        setType(TypeKey.read(smaliReader));
    }

    public void setType(TypeKey typeKey) {
        this.type = typeKey;
    }
}
