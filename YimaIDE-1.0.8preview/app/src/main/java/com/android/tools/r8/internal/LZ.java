package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum LZ implements ZA {
    c("RETURNS_CONSTANT"),
    d("CALLS"),
    e("RETURNS_NOT_NULL");

    public final int b;

    LZ(String str) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
