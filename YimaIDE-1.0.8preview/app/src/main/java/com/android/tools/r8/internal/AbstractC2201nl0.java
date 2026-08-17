package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2201nl0 {
    public static final AbstractC1774il0 a;

    static {
        a = (AbstractC1263cl0.d && AbstractC1263cl0.c && !AbstractC2394q2.b()) ? new C2030ll0() : new C1859jl0();
    }

    public static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt >= 2048) {
                int length2 = charSequence.length();
                int i3 = 0;
                while (i < length2) {
                    char cCharAt2 = charSequence.charAt(i);
                    if (cCharAt2 < 2048) {
                        i3 += (127 - cCharAt2) >>> 31;
                    } else {
                        i3 += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i) < 65536) {
                                throw new C1944kl0(i, length2);
                            }
                            i++;
                        }
                    }
                    i++;
                }
                i2 += i3;
                break;
            }
            i2 += (127 - cCharAt) >>> 31;
            i++;
        }
        if (i2 >= length) {
            return i2;
        }
        u1f.a(((long) i2) + 4294967296L);
        return 0;
    }

    public static int a(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static int a(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }
}
