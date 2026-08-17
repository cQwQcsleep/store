package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum KX extends MX {
    public KX() {
        super(2, "IS_NULL");
    }

    @Override // com.android.tools.r8.internal.EX
    public final boolean apply(Object obj) {
        return obj == null;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "Predicates.isNull()";
    }
}
