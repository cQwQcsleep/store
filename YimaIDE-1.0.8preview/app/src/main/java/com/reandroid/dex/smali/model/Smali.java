package com.reandroid.dex.smali.model;

import com.reandroid.common.Origin;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliParser;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Smali implements SmaliFormat, SmaliParser {
    private Origin origin;
    private Smali parent;

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
    }

    public Origin getOrigin() {
        Origin origin = this.origin;
        if (origin != null) {
            return origin;
        }
        Smali parent = getParent();
        if (parent != null) {
            return parent.getOrigin();
        }
        return null;
    }

    public <T extends Smali> T getParent(Class<T> cls) {
        T t = (T) getParent();
        if (t == null) {
            return null;
        }
        return t.getClass() == cls ? t : (T) t.getParent(cls);
    }

    public <T extends Smali> T getParentInstance(Class<T> cls) {
        T t = (T) getParent();
        if (t == null) {
            return null;
        }
        return cls.isInstance(t) ? t : (T) t.getParentInstance(cls);
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public void setParent(Smali smali) {
        if (smali != this) {
            this.parent = smali;
        } else {
            f63.a("Cyclic parent set");
        }
    }

    public String toDebugString() {
        Origin origin = getOrigin();
        if (origin != null) {
            return origin.toString();
        }
        try {
            return toString();
        } catch (Throwable th) {
            return th.getMessage();
        }
    }

    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    public Smali getParent() {
        return this.parent;
    }
}
