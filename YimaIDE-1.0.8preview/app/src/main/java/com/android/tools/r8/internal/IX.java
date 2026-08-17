package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum IX extends MX {
    public IX() {
        super(0, "ALWAYS_TRUE");
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return true;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "Predicates.alwaysTrue()";
    }
}
