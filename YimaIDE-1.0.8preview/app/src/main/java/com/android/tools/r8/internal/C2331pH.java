package com.android.tools.r8.internal;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2331pH extends AbstractC2587sH {
    public final CE a;

    public C2331pH(CE ce) {
        this.a = ce;
    }

    @Override // com.android.tools.r8.internal.AbstractC2587sH
    public final Object a(Supplier supplier, Function function, Function function2, Function function3) {
        return function2.apply(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C2331pH) {
            return Objects.equals(this.a, ((C2331pH) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a);
    }

    public final String toString() {
        return this.a.toString();
    }
}
