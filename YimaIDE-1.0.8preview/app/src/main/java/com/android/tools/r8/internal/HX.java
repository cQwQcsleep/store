package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HX implements EX, Serializable {
    public final EX b;

    public HX(EX ex) {
        ex.getClass();
        this.b = ex;
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return !this.b.apply(obj);
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean equals(Object obj) {
        if (obj instanceof HX) {
            return this.b.equals(((HX) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return ~this.b.hashCode();
    }

    public final String toString() {
        return "Predicates.not(" + this.b + ")";
    }
}
