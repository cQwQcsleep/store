package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M00 implements ZA {
    public static final M00 c = new M00(0, 0, "WARNING");
    public static final M00 d = new M00(1, 1, "ERROR");
    public static final M00 e = new M00(2, 2, "HIDDEN");
    public final int b;

    public M00(int i, int i2, String str) {
        super(str, i);
        this.b = i2;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
