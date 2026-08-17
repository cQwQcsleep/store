package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MF {
    public static final MF b = new MF(0, "ONLY_CLASS");
    public static final MF c = new MF(1, "ONLY_MEMBERS");
    public static final MF d = new MF(2, "ONLY_METHODS");
    public static final MF e = new MF(3, "ONLY_FIELDS");
    public static final MF f = new MF(4, "CLASS_AND_MEMBERS");
    public static final MF g = new MF(5, "CLASS_AND_METHODS");
    public static final MF h = new MF(6, "CLASS_AND_FIELDS");

    public MF(int i, String str) {
        super(str, i);
    }

    public final boolean a() {
        return equals(c) || equals(e) || equals(f) || equals(h);
    }

    public final boolean b() {
        return equals(c) || equals(d) || equals(f) || equals(g);
    }

    public final boolean c() {
        return equals(b);
    }

    public final boolean d() {
        return equals(e) || equals(h);
    }

    public final boolean e() {
        return !equals(b);
    }

    public final boolean f() {
        return equals(d) || equals(g);
    }
}
