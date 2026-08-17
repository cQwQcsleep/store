package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.dE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC1303dE implements ZA {
    public static final EnumC1303dE c = new EnumC1303dE(0, 0, "NONE");
    public static final EnumC1303dE d = new EnumC1303dE(1, 1, "INTERNAL_TO_CLASS_ID");
    public static final EnumC1303dE e = new EnumC1303dE(2, 2, "DESC_TO_CLASS_ID");
    public final int b;

    public EnumC1303dE(int i, int i2, String str) {
        super(str, i);
        this.b = i2;
    }

    @Override // com.android.tools.r8.internal.ZA
    public final int a() {
        return this.b;
    }
}
