package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.k00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC1876k00 implements ZA {
    public static final EnumC1876k00 c = new EnumC1876k00(0, 0, "CLASS");
    public static final EnumC1876k00 d = new EnumC1876k00(1, 1, "PACKAGE");
    public static final EnumC1876k00 e = new EnumC1876k00(2, 2, "LOCAL");
    public final int b;

    public EnumC1876k00(int i, int i2, String str) {
        super(str, i);
        this.b = i2;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
