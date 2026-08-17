package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2119mo implements Serializable {
    public final AbstractC2205no b;
    public final Object c;

    public C2119mo(AbstractC2205no abstractC2205no, Object obj) {
        abstractC2205no.getClass();
        this.b = abstractC2205no;
        this.c = obj;
    }

    public final Object a() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2119mo)) {
            return false;
        }
        C2119mo c2119mo = (C2119mo) obj;
        if (this.b.equals(c2119mo.b)) {
            return this.b.b(this.c, c2119mo.c);
        }
        return false;
    }

    public final int hashCode() {
        AbstractC2205no abstractC2205no = this.b;
        Object obj = this.c;
        if (obj != null) {
            return abstractC2205no.a(obj);
        }
        abstractC2205no.getClass();
        return 0;
    }

    public final String toString() {
        return this.b + ".wrap(" + this.c + ")";
    }
}
