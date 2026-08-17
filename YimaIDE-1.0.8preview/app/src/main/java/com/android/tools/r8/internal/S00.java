package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum S00 implements ZA {
    /* JADX INFO: Fake field, exist only in values array */
    EF5("INTERNAL"),
    /* JADX INFO: Fake field, exist only in values array */
    EF13("PRIVATE"),
    /* JADX INFO: Fake field, exist only in values array */
    EF21("PROTECTED"),
    /* JADX INFO: Fake field, exist only in values array */
    EF29("PUBLIC"),
    /* JADX INFO: Fake field, exist only in values array */
    EF37("PRIVATE_TO_THIS"),
    /* JADX INFO: Fake field, exist only in values array */
    EF45("LOCAL");

    public final int b;

    S00(String str) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
