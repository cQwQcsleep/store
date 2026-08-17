package com.android.tools.r8.ir.optimize;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum M {
    c("NEVER"),
    d("SAMECLASS"),
    e("SAMENEST"),
    f("PACKAGE"),
    g("SUBCLASS"),
    h("ALWAYS");

    public final int b;

    M(String str) {
        this.b = i;
    }

    public final boolean b(int i2) {
        return (this.b & i2) != 0;
    }
}
