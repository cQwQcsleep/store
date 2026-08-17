package com.android.tools.r8.internal;

import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2245oH extends AbstractC2587sH {
    public static final C2245oH a = new C2245oH();

    @Override // com.android.tools.r8.internal.AbstractC2587sH
    public final Object a(Supplier supplier, Function function, Function function2, Function function3) {
        return supplier.get();
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "<any>";
    }
}
