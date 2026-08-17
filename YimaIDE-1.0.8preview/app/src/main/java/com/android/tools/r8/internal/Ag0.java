package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ag0 {
    public final C2611sd a;
    public int c;
    public String d;
    public int e;
    public int i;
    public X7 j;
    public int k;
    public C3130yg0[] l;
    public int m;
    public C3216zg0[] n;
    public C3216zg0[] o;
    public final C1586gd b = null;
    public C3130yg0[] f = new C3130yg0[Fcntl.S_IRUSR];
    public int g = 1;
    public final X7 h = new X7();

    public Ag0(C2611sd c2611sd) {
        this.a = c2611sd;
    }

    public final C3130yg0 a(Object obj) {
        if (obj instanceof Integer) {
            return a(3, ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return a(3, ((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return a(3, (int) ((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return a(3, ((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return a(3, ((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return a(4, Float.floatToRawIntBits(((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return a(5, ((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return a(6, Double.doubleToRawLongBits(((Double) obj).doubleValue()));
        }
        if (obj instanceof String) {
            return a(8, (String) obj);
        }
        if (obj instanceof C3050xi0) {
            C3050xi0 c3050xi0 = (C3050xi0) obj;
            int iC = c3050xi0.c();
            if (iC == 10) {
                return a(7, c3050xi0.b.substring(c3050xi0.c, c3050xi0.d));
            }
            return iC == 11 ? a(16, c3050xi0.b()) : a(7, c3050xi0.b());
        }
        if (obj instanceof C0497Fs) {
            C0497Fs c0497Fs = (C0497Fs) obj;
            return a(c0497Fs.a, c0497Fs.b, c0497Fs.c, c0497Fs.d, c0497Fs.e);
        }
        if (obj instanceof C2190ng) {
            C2190ng c2190ng = (C2190ng) obj;
            return a(17, a(c2190ng.c, c2190ng.d).a, c2190ng.a, c2190ng.b);
        }
        aca.a("value ", obj);
        return null;
    }

    public final int b(int i, String str) {
        int iHashCode = (str.hashCode() + 129 + i) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 129 && c3130yg0.h == iHashCode && c3130yg0.f == i && c3130yg0.e.equals(str)) {
                return c3130yg0.a;
            }
        }
        return a(new C3130yg0(this.k, 129, str, i, iHashCode));
    }

    public final int b(String str) {
        int iHashCode = (str.hashCode() + 128) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 128 && c3130yg0.h == iHashCode && c3130yg0.e.equals(str)) {
                return c3130yg0.a;
            }
        }
        return a(new C3130yg0(this.k, 128, iHashCode, str));
    }

    public final C3130yg0 b(C3130yg0 c3130yg0) {
        int i = this.e;
        C3130yg0[] c3130yg0Arr = this.f;
        if (i > (c3130yg0Arr.length * 3) / 4) {
            int length = c3130yg0Arr.length;
            int i2 = (length * 2) + 1;
            C3130yg0[] c3130yg0Arr2 = new C3130yg0[i2];
            for (int i3 = length - 1; i3 >= 0; i3--) {
                C3130yg0 c3130yg1 = this.f[i3];
                while (c3130yg1 != null) {
                    int i4 = c3130yg1.h % i2;
                    C3130yg0 c3130yg2 = c3130yg1.i;
                    c3130yg1.i = c3130yg0Arr2[i4];
                    c3130yg0Arr2[i4] = c3130yg1;
                    c3130yg1 = c3130yg2;
                }
            }
            this.f = c3130yg0Arr2;
        }
        this.e++;
        int i5 = c3130yg0.h;
        C3130yg0[] c3130yg0Arr3 = this.f;
        int length2 = i5 % c3130yg0Arr3.length;
        c3130yg0.i = c3130yg0Arr3[length2];
        c3130yg0Arr3[length2] = c3130yg0;
        return c3130yg0;
    }

    public final C3130yg0 a(int i, long j) {
        int i2 = (int) j;
        int i3 = (int) (j >>> 32);
        int i4 = (i + i2 + i3) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[i4 % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == i && c3130yg0.h == i4 && c3130yg0.f == j) {
                return c3130yg0;
            }
        }
        int i5 = this.g;
        X7 x7B = this.h.b(i);
        int i6 = x7B.b;
        int i7 = i6 + 8;
        if (i7 > x7B.a.length) {
            x7B.a(8);
        }
        byte[] bArr = x7B.a;
        bArr[i6] = (byte) (i3 >>> 24);
        bArr[i6 + 1] = (byte) (i3 >>> 16);
        bArr[i6 + 2] = (byte) (i3 >>> 8);
        bArr[i6 + 3] = (byte) i3;
        bArr[i6 + 4] = (byte) (i2 >>> 24);
        bArr[i6 + 5] = (byte) (i2 >>> 16);
        bArr[i6 + 6] = (byte) (i2 >>> 8);
        bArr[i6 + 7] = (byte) i2;
        x7B.b = i7;
        this.g += 2;
        return b(new C3130yg0(i5, i, j, i4));
    }

    public final C3130yg0 a(int i, int i2) {
        int i3 = (i + i2) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[i3 % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == i && c3130yg0.h == i3 && c3130yg0.f == i2) {
                return c3130yg0;
            }
        }
        this.h.b(i).c(i2);
        int i4 = this.g;
        this.g = i4 + 1;
        return b(new C3130yg0(i4, i, i2, i3));
    }

    public final C3130yg0 a(C0497Fs c0497Fs, Object... objArr) {
        X7 x7 = this.j;
        if (x7 == null) {
            x7 = new X7();
            this.j = x7;
        }
        int length = objArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = a(objArr[i]).a;
        }
        int i2 = x7.b;
        x7.d(a(c0497Fs.a, c0497Fs.b, c0497Fs.c, c0497Fs.d, c0497Fs.e).a);
        x7.d(length);
        for (int i3 = 0; i3 < length; i3++) {
            x7.d(iArr[i3]);
        }
        int i4 = x7.b - i2;
        int iHashCode = c0497Fs.hashCode();
        for (Object obj : objArr) {
            iHashCode ^= obj.hashCode();
        }
        int i5 = iHashCode & Integer.MAX_VALUE;
        byte[] bArr = this.j.a;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[i5 % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 64 && c3130yg0.h == i5) {
                int i6 = (int) c3130yg0.f;
                int i7 = 0;
                while (true) {
                    if (i7 < i4) {
                        if (bArr[i2 + i7] != bArr[i6 + i7]) {
                            break;
                        }
                        i7++;
                    } else {
                        this.j.b = i2;
                        return c3130yg0;
                    }
                }
            }
        }
        int i8 = this.i;
        this.i = i8 + 1;
        return b(new C3130yg0(i8, 64, i2, i5));
    }

    public final int a(C3130yg0 c3130yg0) {
        if (this.l == null) {
            this.l = new C3130yg0[16];
        }
        int i = this.k;
        C3130yg0[] c3130yg0Arr = this.l;
        if (i == c3130yg0Arr.length) {
            C3130yg0[] c3130yg0Arr2 = new C3130yg0[c3130yg0Arr.length * 2];
            System.arraycopy(c3130yg0Arr, 0, c3130yg0Arr2, 0, c3130yg0Arr.length);
            this.l = c3130yg0Arr2;
        }
        C3130yg0[] c3130yg0Arr3 = this.l;
        int i2 = this.k;
        this.k = i2 + 1;
        c3130yg0Arr3[i2] = c3130yg0;
        return b(c3130yg0).a;
    }

    public final int a(String str) {
        int iHashCode = (str.hashCode() + 1) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 1 && c3130yg0.h == iHashCode && c3130yg0.e.equals(str)) {
                return c3130yg0.a;
            }
        }
        X7 x7B = this.h.b(1);
        int length = str.length();
        int i = 0;
        if (length <= 65535) {
            int i2 = x7B.b;
            int i3 = i2 + 2;
            if (i3 + length > x7B.a.length) {
                x7B.a(length + 2);
            }
            byte[] bArr = x7B.a;
            bArr[i2] = (byte) (length >>> 8);
            bArr[i2 + 1] = (byte) length;
            while (i < length) {
                char cCharAt = str.charAt(i);
                if (cCharAt >= 1 && cCharAt <= 127) {
                    bArr[i3] = (byte) cCharAt;
                    i++;
                    i3++;
                } else {
                    x7B.b = i3;
                    x7B.a(i, 65535, str);
                    int i4 = this.g;
                    this.g = i4 + 1;
                    return b(new C3130yg0(i4, 1, iHashCode, str)).a;
                }
            }
            x7B.b = i3;
            int i5 = this.g;
            this.g = i5 + 1;
            return b(new C3130yg0(i5, 1, iHashCode, str)).a;
        }
        w01.a("UTF8 string too large");
        return 0;
    }

    public final C3130yg0 a(int i, String str) {
        int iHashCode = (str.hashCode() + i) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == i && c3130yg0.h == iHashCode && c3130yg0.e.equals(str)) {
                return c3130yg0;
            }
        }
        this.h.c(i, a(str));
        int i2 = this.g;
        this.g = i2 + 1;
        return b(new C3130yg0(i2, i, iHashCode, str));
    }

    public final int a(String str, String str2) {
        int iHashCode = ((str2.hashCode() * str.hashCode()) + 12) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 12 && c3130yg0.h == iHashCode && c3130yg0.d.equals(str) && c3130yg0.e.equals(str2)) {
                return c3130yg0.a;
            }
        }
        this.h.a(12, a(str), a(str2));
        int i = this.g;
        this.g = i + 1;
        return b(new C3130yg0(i, iHashCode, str, str2)).a;
    }

    public final C3130yg0 a(int i, int i2, String str, String str2) {
        int iHashCode = (((i2 + 1) * str2.hashCode() * str.hashCode()) + i) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == i && c3130yg0.h == iHashCode && c3130yg0.f == i2 && c3130yg0.d.equals(str) && c3130yg0.e.equals(str2)) {
                return c3130yg0;
            }
        }
        this.h.a(i, i2, a(str, str2));
        int i3 = this.g;
        this.g = i3 + 1;
        return b(new C3130yg0(i3, i, null, str, str2, i2, iHashCode));
    }

    public final C3130yg0 a(int i, String str, String str2, String str3) {
        int iHashCode = ((str3.hashCode() * str2.hashCode() * str.hashCode()) + i) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == i && c3130yg0.h == iHashCode && c3130yg0.c.equals(str) && c3130yg0.d.equals(str2) && c3130yg0.e.equals(str3)) {
                return c3130yg0;
            }
        }
        this.h.a(i, a(7, str).a, a(str2, str3));
        int i2 = this.g;
        this.g = i2 + 1;
        return b(new C3130yg0(i2, i, str, str2, str3, 0L, iHashCode));
    }

    public final C3130yg0 a(int i, String str, String str2, String str3, boolean z) {
        int iHashCode = ((str3.hashCode() * str2.hashCode() * str.hashCode() * i) + 15) & Integer.MAX_VALUE;
        C3130yg0[] c3130yg0Arr = this.f;
        for (C3130yg0 c3130yg0 = c3130yg0Arr[iHashCode % c3130yg0Arr.length]; c3130yg0 != null; c3130yg0 = c3130yg0.i) {
            if (c3130yg0.b == 15 && c3130yg0.h == iHashCode && c3130yg0.f == i && c3130yg0.c.equals(str) && c3130yg0.d.equals(str2) && c3130yg0.e.equals(str3)) {
                return c3130yg0;
            }
        }
        X7 x7 = this.h;
        if (i <= 4) {
            x7.b(i, a(9, str, str2, str3).a);
        } else {
            x7.b(i, a(z ? 11 : 10, str, str2, str3).a);
        }
        int i2 = this.g;
        this.g = i2 + 1;
        return b(new C3130yg0(i2, 15, str, str2, str3, i, iHashCode));
    }
}
