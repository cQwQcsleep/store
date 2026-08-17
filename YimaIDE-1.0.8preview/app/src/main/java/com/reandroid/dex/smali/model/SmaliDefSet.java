package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliDef;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliDefSet<T extends SmaliDef> extends SmaliSet<T> implements SmaliRegion {
    private TypeKey defining;

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendAllWithDoubleNewLine(iterator());
    }

    public abstract T createNew();

    @Override // com.reandroid.dex.smali.model.SmaliSet
    public T createNext(SmaliReader smaliReader) {
        smaliReader.skipWhitespacesOrComment();
        if (SmaliDirective.parse(smaliReader, false) != getSmaliDirective()) {
            return null;
        }
        return (T) createNew();
    }

    public TypeKey getDefining() {
        SmaliClass smaliClass;
        TypeKey typeKey = this.defining;
        return (typeKey != null || (smaliClass = getSmaliClass()) == null) ? typeKey : smaliClass.getKey();
    }

    public SmaliClass getSmaliClass() {
        return (SmaliClass) getParentInstance(SmaliClass.class);
    }

    public void setDefining(TypeKey typeKey) {
        this.defining = typeKey;
    }
}
