package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KI {
    public static final KI b;
    public static final KI c;
    public static final KI d;

    static {
        KI ki = new KI(0, "INVARIANT");
        b = ki;
        KI ki2 = new KI(1, "IN");
        c = ki2;
        KI ki3 = new KI(2, "OUT");
        d = ki3;
        new C3059xn(new KI[]{ki, ki2, ki3});
    }

    public KI(int i, String str) {
        super(str, i);
    }
}
