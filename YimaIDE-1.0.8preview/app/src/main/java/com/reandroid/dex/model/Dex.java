package com.reandroid.dex.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Dex implements SmaliFormat {
    @Override // com.reandroid.dex.smali.SmaliFormat
    public abstract void append(SmaliWriter smaliWriter) throws IOException;

    public abstract DexClassRepository getClassRepository();

    public abstract boolean isRemoved();

    public abstract void removeSelf();

    public String toSmaliString() {
        return SmaliWriter.toStringSafe(this);
    }

    public String toString() {
        return toSmaliString();
    }

    public abstract boolean uses(Key key);
}
