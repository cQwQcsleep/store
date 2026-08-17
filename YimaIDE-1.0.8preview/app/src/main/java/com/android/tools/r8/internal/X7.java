package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X7 {
    public byte[] a;
    public int b;

    public X7() {
        this.a = new byte[64];
    }

    public final X7 a(int i, int i2, String str) {
        int length = str.length();
        int i3 = i;
        int i4 = i3;
        while (i3 < length) {
            char cCharAt = str.charAt(i3);
            if (cCharAt < 1 || cCharAt > 127) {
                i4 = cCharAt <= 2047 ? i4 + 2 : i4 + 3;
            } else {
                i4++;
            }
            i3++;
        }
        if (i4 > i2) {
            w01.a("UTF8 string too large");
            return null;
        }
        int i5 = this.b;
        int i6 = i5 - i;
        int i7 = i6 - 2;
        if (i7 >= 0) {
            byte[] bArr = this.a;
            bArr[i7] = (byte) (i4 >>> 8);
            bArr[i6 - 1] = (byte) i4;
        }
        if ((i5 + i4) - i > this.a.length) {
            a(i4 - i);
        }
        int i8 = this.b;
        while (i < length) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 < 1 || cCharAt2 > 127) {
                byte[] bArr2 = this.a;
                if (cCharAt2 <= 2047) {
                    int i9 = i8 + 1;
                    bArr2[i8] = (byte) (((cCharAt2 >> 6) & 31) | 192);
                    i8 += 2;
                    bArr2[i9] = (byte) ((cCharAt2 & '?') | 128);
                } else {
                    bArr2[i8] = (byte) (((cCharAt2 >> '\f') & 15) | 224);
                    int i10 = i8 + 2;
                    bArr2[i8 + 1] = (byte) (((cCharAt2 >> 6) & 63) | 128);
                    i8 += 3;
                    bArr2[i10] = (byte) ((cCharAt2 & '?') | 128);
                }
            } else {
                this.a[i8] = (byte) cCharAt2;
                i8++;
            }
            i++;
        }
        this.b = i8;
        return this;
    }

    public final void b(int i, int i2) {
        int i3 = this.b;
        int i4 = i3 + 4;
        if (i4 > this.a.length) {
            a(4);
        }
        byte[] bArr = this.a;
        bArr[i3] = 15;
        bArr[i3 + 1] = (byte) i;
        bArr[i3 + 2] = (byte) (i2 >>> 8);
        bArr[i3 + 3] = (byte) i2;
        this.b = i4;
    }

    public final X7 c(int i) {
        int i2 = this.b;
        int i3 = i2 + 4;
        if (i3 > this.a.length) {
            a(4);
        }
        byte[] bArr = this.a;
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        this.b = i3;
        return this;
    }

    public final X7 d(int i) {
        int i2 = this.b;
        int i3 = i2 + 2;
        if (i3 > this.a.length) {
            a(2);
        }
        byte[] bArr = this.a;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
        this.b = i3;
        return this;
    }

    public X7(int i) {
        this.a = new byte[i];
    }

    public X7(byte[] bArr) {
        this.a = bArr;
        this.b = bArr.length;
    }

    public final X7 b(int i) {
        int i2 = this.b;
        int i3 = i2 + 1;
        if (i3 > this.a.length) {
            a(1);
        }
        this.a[i2] = (byte) i;
        this.b = i3;
        return this;
    }

    public final X7 c(int i, int i2) {
        int i3 = this.b;
        int i4 = i3 + 3;
        if (i4 > this.a.length) {
            a(3);
        }
        byte[] bArr = this.a;
        bArr[i3] = (byte) i;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) i2;
        this.b = i4;
        return this;
    }

    public final void a(int i, int i2, int i3) {
        int i4 = this.b;
        int i5 = i4 + 5;
        if (i5 > this.a.length) {
            a(5);
        }
        byte[] bArr = this.a;
        bArr[i4] = (byte) i;
        bArr[i4 + 1] = (byte) (i2 >>> 8);
        bArr[i4 + 2] = (byte) i2;
        bArr[i4 + 3] = (byte) (i3 >>> 8);
        bArr[i4 + 4] = (byte) i3;
        this.b = i5;
    }

    public final void a(int i, int i2) {
        int i3 = this.b;
        int i4 = i3 + 2;
        if (i4 > this.a.length) {
            a(2);
        }
        byte[] bArr = this.a;
        bArr[i3] = (byte) i;
        bArr[i3 + 1] = (byte) i2;
        this.b = i4;
    }

    public final X7 a(byte[] bArr, int i, int i2) {
        if (this.b + i2 > this.a.length) {
            a(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.a, this.b, i2);
        }
        this.b += i2;
        return this;
    }

    public final void a(int i) {
        int i2 = this.b;
        byte[] bArr = this.a;
        if (i2 <= bArr.length) {
            int length = bArr.length * 2;
            int i3 = i + i2;
            if (length <= i3) {
                length = i3;
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.a = bArr2;
            return;
        }
        x01.a("Internal error");
    }
}
