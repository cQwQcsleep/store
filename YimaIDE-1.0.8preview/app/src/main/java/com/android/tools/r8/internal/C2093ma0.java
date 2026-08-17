package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ma0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2093ma0 extends AbstractC2943wV implements Serializable {
    public final AbstractC2943wV b;

    public C2093ma0(AbstractC2943wV abstractC2943wV) {
        abstractC2943wV.getClass();
        this.b = abstractC2943wV;
    }

    @Override // com.android.tools.r8.internal.AbstractC2943wV
    public final AbstractC2943wV a() {
        return this.b;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.b.compare(obj2, obj);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2093ma0) {
            return this.b.equals(((C2093ma0) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return -this.b.hashCode();
    }

    public final String toString() {
        return this.b + ".reverse()";
    }
}
