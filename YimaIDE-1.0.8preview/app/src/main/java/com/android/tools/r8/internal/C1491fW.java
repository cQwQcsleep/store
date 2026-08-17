package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1491fW implements Serializable {
    public final Object b;
    public final Object c;

    public C1491fW(Object obj, Object obj2) {
        this.b = obj;
        this.c = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1491fW)) {
            return false;
        }
        C1491fW c1491fW = (C1491fW) obj;
        return KB.a(this.b, c1491fW.b) && KB.a(this.c, c1491fW.c);
    }

    public final int hashCode() {
        Object obj = this.b;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.c;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.b + ", " + this.c + ')';
    }
}
