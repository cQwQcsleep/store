package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum BZ implements ZA {
    /* JADX INFO: Fake field, exist only in values array */
    EF5("CLASS"),
    /* JADX INFO: Fake field, exist only in values array */
    EF13("INTERFACE"),
    /* JADX INFO: Fake field, exist only in values array */
    EF21("ENUM_CLASS"),
    /* JADX INFO: Fake field, exist only in values array */
    EF29("ENUM_ENTRY"),
    /* JADX INFO: Fake field, exist only in values array */
    EF37("ANNOTATION_CLASS"),
    /* JADX INFO: Fake field, exist only in values array */
    EF45("OBJECT"),
    /* JADX INFO: Fake field, exist only in values array */
    EF53("COMPANION_OBJECT");

    public final int b;

    BZ(String str) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
