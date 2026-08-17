package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum YZ implements ZA {
    /* JADX INFO: Fake field, exist only in values array */
    EF5("DECLARATION"),
    /* JADX INFO: Fake field, exist only in values array */
    EF13("FAKE_OVERRIDE"),
    /* JADX INFO: Fake field, exist only in values array */
    EF21("DELEGATION"),
    /* JADX INFO: Fake field, exist only in values array */
    EF29("SYNTHESIZED");

    public final int b;

    YZ(String str) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
