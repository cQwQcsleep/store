package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AB {
    public static final AB b = new AB(0, "DexIndexed");
    public static final AB c = new AB(1, "DexFilePerClassFile");
    public static final AB d = new AB(2, "ClassFile");

    public AB(int i, String str) {
        super(str, i);
    }

    public final boolean a() {
        return this == d;
    }
}
