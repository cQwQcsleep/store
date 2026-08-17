package com.android.tools.r8.internal;

import defpackage.q68;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.mp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC2120mp {
    public static final EnumC2120mp b = new EnumC2120mp(0, "OBJECT");
    public static final EnumC2120mp c = new EnumC2120mp(1, "BOOLEAN");
    public static final EnumC2120mp d = new EnumC2120mp(2, "BYTE");
    public static final EnumC2120mp e = new EnumC2120mp(3, "CHAR");
    public static final EnumC2120mp f = new EnumC2120mp(4, "SHORT");
    public static final EnumC2120mp g = new EnumC2120mp(5, "INT");
    public static final EnumC2120mp h = new EnumC2120mp(6, "FLOAT");
    public static final EnumC2120mp i = new EnumC2120mp(7, "LONG");
    public static final EnumC2120mp j = new EnumC2120mp(8, "DOUBLE");

    public EnumC2120mp(int i2, String str) {
        super(str, i2);
    }

    public static EnumC2120mp a(char c2) {
        if (c2 == 'F') {
            return h;
        }
        if (c2 != 'L') {
            if (c2 == 'S') {
                return f;
            }
            if (c2 == 'V') {
                throw new C1727iB("No member type for void type.");
            }
            if (c2 == 'I') {
                return g;
            }
            if (c2 == 'J') {
                return i;
            }
            if (c2 == 'Z') {
                return c;
            }
            if (c2 != '[') {
                switch (c2) {
                    case 'B':
                        return d;
                    case 'C':
                        return e;
                    case 'D':
                        return j;
                    default:
                        q68.a(c2);
                        return null;
                }
            }
        }
        return b;
    }
}
