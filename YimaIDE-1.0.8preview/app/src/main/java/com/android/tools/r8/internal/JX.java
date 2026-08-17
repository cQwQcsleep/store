package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum JX extends MX {
    public JX() {
        super(1, "ALWAYS_FALSE");
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return false;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "Predicates.alwaysFalse()";
    }
}
