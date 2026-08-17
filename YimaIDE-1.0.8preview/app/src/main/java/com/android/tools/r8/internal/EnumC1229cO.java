package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.cO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC1229cO {
    public static final EnumC1229cO c = new EnumC1229cO(0, -1, "UNKNOWN");
    public static final EnumC1229cO d = new EnumC1229cO(1, 0, "PREAMBLE");
    public static final EnumC1229cO e = new EnumC1229cO(2, 1, "OBFUSCATED_PACKAGES");
    public final int b;

    public EnumC1229cO(int i, int i2, String str) {
        super(str, i);
        this.b = i2;
    }

    public static EnumC1229cO b(int i) {
        if (i == 0) {
            return d;
        }
        return i == 1 ? e : c;
    }
}
