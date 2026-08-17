package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0689Nd extends AbstractC0793Rd {
    public final byte[] c;
    public final int d;
    public int e;

    public C0689Nd(int i, byte[] bArr) {
        if (((bArr.length - i) | i) < 0) {
            drd.a("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i)});
            throw null;
        }
        this.c = bArr;
        this.e = 0;
        this.d = i;
    }

    public final void a(byte[] bArr, int i, int i2) {
        try {
            System.arraycopy(bArr, i, this.c, this.e, i2);
            this.e += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(i2)), e);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void b(long j) throws C0741Pd {
        try {
            byte[] bArr = this.c;
            int i = this.e;
            bArr[i] = (byte) (((int) j) & 255);
            bArr[i + 1] = (byte) (((int) (j >> 8)) & 255);
            bArr[i + 2] = (byte) (((int) (j >> 16)) & 255);
            bArr[i + 3] = (byte) (((int) (j >> 24)) & 255);
            bArr[i + 4] = (byte) (((int) (j >> 32)) & 255);
            bArr[i + 5] = (byte) (((int) (j >> 40)) & 255);
            bArr[i + 6] = (byte) (((int) (j >> 48)) & 255);
            this.e = i + 8;
            bArr[i + 7] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void c(long j) throws C0741Pd {
        if (!AbstractC0793Rd.b || this.d - this.e < 10) {
            while (true) {
                long j2 = j & (-128);
                byte[] bArr = this.c;
                if (j2 == 0) {
                    int i = this.e;
                    this.e = i + 1;
                    bArr[i] = (byte) j;
                    return;
                } else {
                    try {
                        int i2 = this.e;
                        this.e = i2 + 1;
                        bArr[i2] = (byte) ((((int) j) & 127) | 128);
                        j >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
                    }
                }
                throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
            }
        }
        while (true) {
            long j3 = j & (-128);
            byte[] bArr2 = this.c;
            if (j3 == 0) {
                int i3 = this.e;
                this.e = i3 + 1;
                AbstractC1263cl0.b.a((Object) bArr2, AbstractC1263cl0.e + ((long) i3), (byte) j);
                return;
            }
            int i4 = this.e;
            this.e = i4 + 1;
            AbstractC1263cl0.b.a((Object) bArr2, AbstractC1263cl0.e + ((long) i4), (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void d(int i) throws C0741Pd {
        try {
            byte[] bArr = this.c;
            int i2 = this.e;
            bArr[i2] = (byte) (i & 255);
            bArr[i2 + 1] = (byte) ((i >> 8) & 255);
            bArr[i2 + 2] = (byte) ((i >> 16) & 255);
            this.e = i2 + 4;
            bArr[i2 + 3] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void e(int i) {
        if (i >= 0) {
            f(i);
        } else {
            c(i);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void f(int i) throws C0741Pd {
        if (AbstractC0793Rd.b && !AbstractC2394q2.b()) {
            int i2 = this.d;
            int i3 = this.e;
            if (i2 - i3 >= 5) {
                int i4 = i & (-128);
                byte[] bArr = this.c;
                if (i4 == 0) {
                    this.e = i3 + 1;
                    byte b = (byte) i;
                    AbstractC1263cl0.b.a((Object) bArr, AbstractC1263cl0.e + ((long) i3), b);
                    return;
                }
                this.e = i3 + 1;
                AbstractC1180bl0 abstractC1180bl0 = AbstractC1263cl0.b;
                long j = AbstractC1263cl0.e;
                abstractC1180bl0.a((Object) bArr, ((long) i3) + j, (byte) (i | 128));
                int i5 = i >>> 7;
                int i6 = i5 & (-128);
                byte[] bArr2 = this.c;
                if (i6 == 0) {
                    int i7 = this.e;
                    this.e = i7 + 1;
                    abstractC1180bl0.a((Object) bArr2, j + ((long) i7), (byte) i5);
                    return;
                }
                int i8 = this.e;
                this.e = i8 + 1;
                abstractC1180bl0.a((Object) bArr2, ((long) i8) + j, (byte) (i5 | 128));
                int i9 = i >>> 14;
                int i10 = i9 & (-128);
                byte[] bArr3 = this.c;
                if (i10 == 0) {
                    int i11 = this.e;
                    this.e = i11 + 1;
                    abstractC1180bl0.a((Object) bArr3, j + ((long) i11), (byte) i9);
                    return;
                }
                int i12 = this.e;
                this.e = i12 + 1;
                abstractC1180bl0.a((Object) bArr3, ((long) i12) + j, (byte) (i9 | 128));
                int i13 = i >>> 21;
                int i14 = i13 & (-128);
                byte[] bArr4 = this.c;
                if (i14 == 0) {
                    int i15 = this.e;
                    this.e = i15 + 1;
                    abstractC1180bl0.a((Object) bArr4, j + ((long) i15), (byte) i13);
                    return;
                }
                int i16 = this.e;
                this.e = i16 + 1;
                abstractC1180bl0.a((Object) bArr4, ((long) i16) + j, (byte) (i13 | 128));
                byte[] bArr5 = this.c;
                int i17 = this.e;
                this.e = i17 + 1;
                abstractC1180bl0.a((Object) bArr5, j + ((long) i17), (byte) (i >>> 28));
                return;
            }
        }
        while (true) {
            int i18 = i & (-128);
            byte[] bArr6 = this.c;
            if (i18 == 0) {
                int i19 = this.e;
                this.e = i19 + 1;
                bArr6[i19] = (byte) i;
                return;
            } else {
                try {
                    int i20 = this.e;
                    this.e = i20 + 1;
                    bArr6[i20] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
                }
            }
            throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void a(int i, TN tn) throws C0741Pd {
        c(i, 2);
        f(tn.c());
        tn.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void a(byte b) throws C0741Pd {
        try {
            byte[] bArr = this.c;
            int i = this.e;
            this.e = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new C0741Pd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void a(int i, boolean z) throws C0741Pd {
        c(i, 0);
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void b(U7 u7) throws C0741Pd {
        f(u7.size());
        Q7 q7 = (Q7) u7;
        a(q7.e, q7.d(), q7.size());
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void b(String str) throws C0741Pd {
        int i = this.e;
        try {
            int iC = AbstractC0793Rd.c(str.length() * 3);
            int iC2 = AbstractC0793Rd.c(str.length());
            if (iC2 == iC) {
                int i2 = i + iC2;
                this.e = i2;
                int iA = AbstractC2201nl0.a.a(str, this.c, i2, this.d - i2);
                this.e = i;
                f((iA - i) - iC2);
                this.e = iA;
                return;
            }
            f(AbstractC2201nl0.a(str));
            byte[] bArr = this.c;
            int i3 = this.e;
            this.e = AbstractC2201nl0.a.a(str, bArr, i3, this.d - i3);
        } catch (C1944kl0 e) {
            this.e = i;
            a(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new C0741Pd(e2);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0793Rd
    public final void c(int i, int i2) {
        f((i << 3) | i2);
    }
}
