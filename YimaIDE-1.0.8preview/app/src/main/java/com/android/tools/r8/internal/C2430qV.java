package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2430qV extends AbstractC2515rV {
    public final Object b;

    public C2430qV(C1476fH c1476fH) {
        this.b = c1476fH;
    }

    @Override // com.android.tools.r8.internal.AbstractC2515rV
    public final Object a(Function function) {
        return function.apply(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC2515rV
    public final boolean b() {
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C2430qV) {
            return this.b.equals(((C2430qV) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.b.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC2515rV
    public final Object a() {
        return this.b;
    }
}
