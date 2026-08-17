package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1643hD {
    public boolean a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public final C1558gD c() {
        if (this instanceof C1558gD) {
            return (C1558gD) this;
        }
        qu7.a("Not a JSON Array: ", this);
        return null;
    }

    public final C1898kD d() {
        if (this instanceof C1898kD) {
            return (C1898kD) this;
        }
        qu7.a("Not a JSON Object: ", this);
        return null;
    }

    public long e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String g() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            C2754uD c2754uD = new C2754uD(stringWriter);
            c2754uD.e = true;
            Zi0 zi0 = AbstractC2197nj0.a;
            Wi0.a(c2754uD, this);
            return stringWriter.toString();
        } catch (IOException e) {
            x01.a(e);
            return null;
        }
    }
}
