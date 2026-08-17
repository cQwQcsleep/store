package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum MZ implements ZA {
    c("AT_MOST_ONCE"),
    d("EXACTLY_ONCE"),
    e("AT_LEAST_ONCE");

    public final int b;

    MZ(String str) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
