package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class DX {
    public static void a(int i, int i2) {
        String strA;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                strA = Xf0.a("%s (%s) must not be negative", new Object[]{"index", Integer.valueOf(i)});
            } else {
                if (i2 < 0) {
                    w01.a(CX.a(i2, "negative size: "));
                    return;
                }
                strA = Xf0.a("%s (%s) must be less than size (%s)", new Object[]{"index", Integer.valueOf(i), Integer.valueOf(i2)});
            }
            throw new IndexOutOfBoundsException(strA);
        }
    }

    public static void b(int i, int i2) {
        if (i < 0 || i > i2) {
            jb9.a(a(i, i2, "index"));
        }
    }

    public static void a(Object obj) {
        obj.getClass();
    }

    public static void a(boolean z, String str, Object obj) {
        if (z) {
            return;
        }
        w01.a(Xf0.a(str, new Object[]{obj}));
    }

    public static String a(int i, int i2, String str) {
        if (i < 0) {
            return Xf0.a("%s (%s) must not be negative", new Object[]{str, Integer.valueOf(i)});
        }
        if (i2 >= 0) {
            return Xf0.a("%s (%s) must not be greater than size (%s)", new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)});
        }
        w01.a(CX.a(i2, "negative size: "));
        return null;
    }

    public static void a(int i, int i2, int i3) {
        String strA;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                strA = a(i, i3, "start index");
            } else if (i2 >= 0 && i2 <= i3) {
                strA = Xf0.a("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)});
            } else {
                strA = a(i2, i3, "end index");
            }
            throw new IndexOutOfBoundsException(strA);
        }
    }
}
