package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum N00 implements ZA {
    c("LANGUAGE_VERSION"),
    d("COMPILER_VERSION"),
    e("API_VERSION");

    public final int b;

    N00(String str) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
