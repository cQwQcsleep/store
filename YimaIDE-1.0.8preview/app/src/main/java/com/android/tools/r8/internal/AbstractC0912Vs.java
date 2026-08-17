package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0912Vs {
    public static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public final boolean equals(Object obj) {
        boolean z;
        if (obj instanceof AbstractC0912Vs) {
            C0886Us c0886Us = (C0886Us) this;
            byte[] bArr = c0886Us.c;
            int length = bArr.length * 8;
            C0886Us c0886Us2 = (C0886Us) ((AbstractC0912Vs) obj);
            byte[] bArr2 = c0886Us2.c;
            if (length == bArr2.length * 8) {
                if (bArr.length == bArr2.length) {
                    int i = 0;
                    z = true;
                    while (true) {
                        byte[] bArr3 = c0886Us.c;
                        if (i >= bArr3.length) {
                            break;
                        }
                        z &= bArr3[i] == c0886Us2.c[i];
                        i++;
                    }
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        byte[] bArr = ((C0886Us) this).c;
        if (bArr.length * 8 < 32) {
            int i = bArr[0] & 255;
            for (int i2 = 1; i2 < bArr.length; i2++) {
                i |= (bArr[i2] & 255) << (i2 * 8);
            }
            return i;
        }
        boolean z = bArr.length >= 4;
        int length = bArr.length;
        if (z) {
            return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
        }
        k2d.a(Xf0.a("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", new Object[]{Integer.valueOf(length)}));
        return 0;
    }

    public final String toString() {
        byte[] bArr = ((C0886Us) this).c;
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            char[] cArr = b;
            sb.append(cArr[(b2 >> 4) & 15]);
            sb.append(cArr[b2 & 15]);
        }
        return sb.toString();
    }
}
