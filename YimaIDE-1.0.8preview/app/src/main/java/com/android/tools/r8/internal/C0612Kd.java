package com.android.tools.r8.internal;

import defpackage.go7;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Kd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0612Kd extends AbstractC0663Md {
    public final InputStream d;
    public final byte[] e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k = Integer.MAX_VALUE;

    public C0612Kd(InputStream inputStream) {
        Charset charset = AbstractC1556gB.a;
        if (inputStream == null) {
            x0e.a("input");
            throw null;
        }
        this.d = inputStream;
        this.e = new byte[4096];
        this.f = 0;
        this.h = 0;
        this.j = 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final TN a(InterfaceC2346pW interfaceC2346pW, C0415Co c0415Co) throws RB {
        int iL = l();
        a();
        int iC = c(iL);
        this.a++;
        TN tn = (TN) interfaceC2346pW.a(this, c0415Co);
        a(0);
        this.a--;
        if (b() != 0) {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return null;
        }
        this.k = iC;
        y();
        return tn;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int b() {
        int i = this.k;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - (this.j + this.h);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int c(int i) throws RB {
        if (i < 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            return 0;
        }
        int i2 = this.j + this.h + i;
        int i3 = this.k;
        if (i2 > i3) {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return 0;
        }
        this.k = i2;
        y();
        return i3;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final boolean d(int i) throws RB {
        int iS;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 != 0) {
            if (i2 == 1) {
                i(8);
                return true;
            }
            if (i2 == 2) {
                i(l());
                return true;
            }
            if (i2 != 3) {
                if (i2 == 4) {
                    return false;
                }
                if (i2 != 5) {
                    throw new PB();
                }
                i(4);
                return true;
            }
            do {
                iS = s();
                if (iS == 0) {
                    break;
                }
            } while (d(iS));
            a(((i >>> 3) << 3) | 4);
            return true;
        }
        if (this.f - this.h >= 10) {
            while (i3 < 10) {
                byte[] bArr = this.e;
                int i4 = this.h;
                this.h = i4 + 1;
                if (bArr[i4] < 0) {
                    i3++;
                }
            }
            go7.a("CodedInputStream encountered a malformed varint.");
            return false;
        }
        while (i3 < 10) {
            if (this.h == this.f) {
                h(1);
            }
            byte[] bArr2 = this.e;
            int i5 = this.h;
            this.h = i5 + 1;
            if (bArr2[i5] < 0) {
                i3++;
            }
        }
        go7.a("CodedInputStream encountered a malformed varint.");
        return false;
        return true;
    }

    public final byte[] e(int i) throws IOException {
        byte[] bArrF = f(i);
        if (bArrF != null) {
            return bArrF;
        }
        int i2 = this.h;
        int i3 = this.f;
        int length = i3 - i2;
        this.j += i3;
        this.h = 0;
        this.f = 0;
        ArrayList<byte[]> arrayListG = g(i - length);
        byte[] bArr = new byte[i];
        System.arraycopy(this.e, i2, bArr, 0, length);
        for (byte[] bArr2 : arrayListG) {
            System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
            length += bArr2.length;
        }
        return bArr;
    }

    public final byte[] f(int i) throws IOException {
        if (i == 0) {
            return AbstractC1556gB.d;
        }
        if (i < 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            return null;
        }
        int i2 = this.j;
        int i3 = this.h;
        int i4 = i2 + i3 + i;
        if (i4 - this.c > 0) {
            go7.a("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
            return null;
        }
        int i5 = this.k;
        if (i4 > i5) {
            i((i5 - i2) - i3);
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return null;
        }
        int i6 = this.f - i3;
        int i7 = i - i6;
        if (i7 >= 4096 && i7 > this.d.available()) {
            return null;
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.e, this.h, bArr, 0, i6);
        this.j += this.f;
        this.h = 0;
        this.f = 0;
        while (i6 < i) {
            int i8 = this.d.read(bArr, i6, i - i6);
            if (i8 == -1) {
                go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                return null;
            }
            this.j += i8;
            i6 += i8;
        }
        return bArr;
    }

    public final ArrayList g(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int iMin = Math.min(i, 4096);
            byte[] bArr = new byte[iMin];
            int i2 = 0;
            while (i2 < iMin) {
                int i3 = this.d.read(bArr, i2, iMin - i2);
                if (i3 == -1) {
                    go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                    return null;
                }
                this.j += i3;
                i2 += i3;
            }
            i -= iMin;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public final void h(int i) throws RB {
        if (j(i)) {
            return;
        }
        if (i > (this.c - this.j) - this.h) {
            go7.a("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        } else {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final void i(int i) throws RB {
        int i2 = this.f;
        int i3 = this.h;
        int i4 = i2 - i3;
        if (i <= i4 && i >= 0) {
            this.h = i3 + i;
            return;
        }
        if (i < 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            return;
        }
        int i5 = this.j;
        int i6 = i5 + i3;
        int i7 = i6 + i;
        int i8 = this.k;
        if (i7 > i8) {
            i((i8 - i5) - i3);
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return;
        }
        this.j = i6;
        this.f = 0;
        this.h = 0;
        while (i4 < i) {
            try {
                long j = i - i4;
                long jSkip = this.d.skip(j);
                if (jSkip < 0 || jSkip > j) {
                    throw new IllegalStateException(this.d.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                }
                if (jSkip == 0) {
                    break;
                } else {
                    i4 += (int) jSkip;
                }
            } catch (Throwable th) {
                this.j += i4;
                y();
                throw th;
            }
        }
        this.j += i4;
        y();
        if (i4 >= i) {
            return;
        }
        int i9 = this.f;
        int i10 = i9 - this.h;
        this.h = i9;
        h(1);
        while (true) {
            int i11 = i - i10;
            int i12 = this.f;
            if (i11 <= i12) {
                this.h = i11;
                return;
            } else {
                i10 += i12;
                this.h = i12;
                h(1);
            }
        }
    }

    public final boolean j(int i) throws IOException {
        int i2 = this.h;
        int i3 = i2 + i;
        int i4 = this.f;
        if (i3 <= i4) {
            k2d.a(AbstractC1784iv.a(i, "refillBuffer() called when ", " bytes were already available in buffer"));
            return false;
        }
        int i5 = this.c;
        int i6 = this.j;
        if (i > (i5 - i6) - i2 || i6 + i2 + i > this.k) {
            return false;
        }
        if (i2 > 0) {
            if (i4 > i2) {
                byte[] bArr = this.e;
                System.arraycopy(bArr, i2, bArr, 0, i4 - i2);
            }
            this.j += i2;
            this.f -= i2;
            this.h = 0;
        }
        InputStream inputStream = this.d;
        byte[] bArr2 = this.e;
        int i7 = this.f;
        int i8 = inputStream.read(bArr2, i7, Math.min(bArr2.length - i7, (this.c - this.j) - i7));
        if (i8 == 0 || i8 < -1 || i8 > this.e.length) {
            throw new IllegalStateException(this.d.getClass() + "#read(byte[]) returned invalid result: " + i8 + "\nThe InputStream implementation is buggy.");
        }
        if (i8 <= 0) {
            return false;
        }
        this.f += i8;
        y();
        if (this.f >= i) {
            return true;
        }
        return j(i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long k() {
        return x();
    }

    /* JADX WARN: Code duplicated, block: B:48:0x009f A[PHI: r3
      0x009f: PHI (r3v10 int) = (r3v9 int), (r3v12 int) binds: [B:25:0x0053, B:29:0x005f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int l() throws RB {
        int i;
        int i2 = this.h;
        int i3 = this.f;
        if (i3 != i2) {
            byte[] bArr = this.e;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.h = i4;
                return b;
            }
            if (i3 - i4 >= 9) {
                int i5 = i2 + 2;
                int i6 = (bArr[i4] << 7) ^ b;
                if (i6 < 0) {
                    i = i6 ^ (-128);
                } else {
                    int i7 = i2 + 3;
                    int i8 = (bArr[i5] << 14) ^ i6;
                    if (i8 >= 0) {
                        i = i8 ^ 16256;
                    } else {
                        int i9 = i2 + 4;
                        int i10 = i8 ^ (bArr[i7] << 21);
                        if (i10 < 0) {
                            i = (-2080896) ^ i10;
                            i5 = i9;
                        } else {
                            i7 = i2 + 5;
                            byte b2 = bArr[i9];
                            int i11 = (i10 ^ (b2 << 28)) ^ 266354560;
                            if (b2 < 0) {
                                int i12 = i2 + 6;
                                if (bArr[i7] < 0) {
                                    i7 = i2 + 7;
                                    if (bArr[i12] < 0) {
                                        i12 = i2 + 8;
                                        if (bArr[i7] < 0) {
                                            i7 = i2 + 9;
                                            if (bArr[i12] < 0) {
                                                int i13 = i2 + 10;
                                                if (bArr[i7] >= 0) {
                                                    i7 = i13;
                                                }
                                            }
                                        } else {
                                            i7 = i12;
                                        }
                                    }
                                } else {
                                    i7 = i12;
                                }
                            }
                            i = i11;
                        }
                    }
                    i5 = i7;
                }
                this.h = i5;
                return i;
            }
        }
        long j = 0;
        for (int i14 = 0; i14 < 64; i14 += 7) {
            if (this.h == this.f) {
                h(1);
            }
            byte[] bArr2 = this.e;
            int i15 = this.h;
            this.h = i15 + 1;
            byte b3 = bArr2[i15];
            j |= ((long) (b3 & 127)) << i14;
            if ((b3 & 128) == 0) {
                return (int) j;
            }
        }
        go7.a("CodedInputStream encountered a malformed varint.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int m() {
        return v();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long n() {
        return w();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int o() throws RB {
        int iL = l();
        return (-(iL & 1)) ^ (iL >>> 1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long p() throws RB {
        long jX = x();
        return (-(jX & 1)) ^ (jX >>> 1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final String q() throws RB {
        int iL = l();
        if (iL > 0) {
            int i = this.f;
            int i2 = this.h;
            if (iL <= i - i2) {
                String str = new String(this.e, i2, iL, AbstractC1556gB.b);
                this.h += iL;
                return str;
            }
        }
        if (iL == 0) {
            return XmlPullParser.NO_NAMESPACE;
        }
        if (iL > this.f) {
            return new String(e(iL), AbstractC1556gB.b);
        }
        h(iL);
        String str2 = new String(this.e, this.h, iL, AbstractC1556gB.b);
        this.h += iL;
        return str2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final String r() throws IOException {
        byte[] bArrE;
        int iL = l();
        int i = this.h;
        int i2 = this.f;
        if (iL <= i2 - i && iL > 0) {
            bArrE = this.e;
            this.h = i + iL;
        } else {
            if (iL == 0) {
                return XmlPullParser.NO_NAMESPACE;
            }
            i = 0;
            if (iL <= i2) {
                h(iL);
                bArrE = this.e;
                this.h = iL;
            } else {
                bArrE = e(iL);
            }
        }
        return AbstractC2201nl0.a.a(bArrE, i, iL);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int s() throws RB {
        if (this.h == this.f && !j(1)) {
            this.i = 0;
            return 0;
        }
        int iL = l();
        this.i = iL;
        if ((iL >>> 3) != 0) {
            return iL;
        }
        go7.a("Protocol message contained an invalid tag (zero).");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int t() {
        return l();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long u() {
        return x();
    }

    public final int v() throws RB {
        int i = this.h;
        if (this.f - i < 4) {
            h(4);
            i = this.h;
        }
        byte[] bArr = this.e;
        this.h = i + 4;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    public final long w() throws RB {
        int i = this.h;
        if (this.f - i < 8) {
            h(8);
            i = this.h;
        }
        byte[] bArr = this.e;
        this.h = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final long x() throws RB {
        long j;
        long j2;
        long j3;
        int i = this.h;
        int i2 = this.f;
        if (i2 != i) {
            byte[] bArr = this.e;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.h = i3;
                return b;
            }
            if (i2 - i3 >= 9) {
                int i4 = i + 2;
                int i5 = (bArr[i3] << 7) ^ b;
                if (i5 < 0) {
                    j = i5 ^ (-128);
                } else {
                    int i6 = i + 3;
                    int i7 = (bArr[i4] << 14) ^ i5;
                    if (i7 >= 0) {
                        j = i7 ^ 16256;
                        i4 = i6;
                    } else {
                        int i8 = i + 4;
                        int i9 = i7 ^ (bArr[i6] << 21);
                        if (i9 < 0) {
                            j = (-2080896) ^ i9;
                            i4 = i8;
                        } else {
                            long j4 = i9;
                            i4 = i + 5;
                            long j5 = j4 ^ (((long) bArr[i8]) << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                int i10 = i + 6;
                                long j6 = j5 ^ (((long) bArr[i4]) << 35);
                                if (j6 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i4 = i + 7;
                                    j5 = j6 ^ (((long) bArr[i10]) << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i10 = i + 8;
                                        j6 = j5 ^ (((long) bArr[i4]) << 49);
                                        if (j6 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            i4 = i + 9;
                                            long j7 = (j6 ^ (((long) bArr[i10]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                int i11 = i + 10;
                                                if (bArr[i4] >= 0) {
                                                    i4 = i11;
                                                }
                                            }
                                            j = j7;
                                        }
                                    }
                                }
                                j = j6 ^ j2;
                                i4 = i10;
                            }
                            j = j3 ^ j5;
                        }
                    }
                }
                this.h = i4;
                return j;
            }
        }
        long j8 = 0;
        for (int i12 = 0; i12 < 64; i12 += 7) {
            if (this.h == this.f) {
                h(1);
            }
            byte[] bArr2 = this.e;
            int i13 = this.h;
            this.h = i13 + 1;
            byte b2 = bArr2[i13];
            j8 |= ((long) (b2 & 127)) << i12;
            if ((b2 & 128) == 0) {
                return j8;
            }
        }
        go7.a("CodedInputStream encountered a malformed varint.");
        return 0L;
    }

    public final void y() {
        int i = this.f + this.g;
        this.f = i;
        int i2 = this.j + i;
        int i3 = this.k;
        if (i2 <= i3) {
            this.g = 0;
            return;
        }
        int i4 = i2 - i3;
        this.g = i4;
        this.f = i - i4;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final void b(int i) {
        this.k = i;
        y();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long h() {
        return w();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final boolean c() {
        return x() != 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int g() {
        return v();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final void a(int i, SN sn, C0415Co c0415Co) throws RB {
        a();
        this.a++;
        sn.a(this, c0415Co);
        a((i << 3) | 4);
        this.a--;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final void a(H0 h0, C0415Co c0415Co) throws RB {
        int iL = l();
        a();
        int iC = c(iL);
        this.a++;
        h0.a(this, c0415Co);
        a(0);
        this.a--;
        if (b() == 0) {
            this.k = iC;
            y();
        } else {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final double e() {
        return Double.longBitsToDouble(w());
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final void a(int i) throws RB {
        if (this.i == i) {
            return;
        }
        go7.a("Protocol message end-group tag did not match expected tag.");
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int f() {
        return l();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final Q7 d() throws IOException {
        int iL = l();
        int i = this.f;
        int i2 = this.h;
        if (iL <= i - i2 && iL > 0) {
            byte[] bArr = this.e;
            Q7 q7 = U7.c;
            U7.a(i2, i2 + iL, bArr.length);
            Q7 q8 = new Q7(U7.d.a(bArr, i2, iL));
            this.h += iL;
            return q8;
        }
        if (iL == 0) {
            return U7.c;
        }
        byte[] bArrF = f(iL);
        if (bArrF != null) {
            int length = bArrF.length;
            U7.a(0, length, bArrF.length);
            return new Q7(U7.d.a(bArrF, 0, length));
        }
        int i3 = this.h;
        int i4 = this.f;
        int length2 = i4 - i3;
        this.j += i4;
        this.h = 0;
        this.f = 0;
        ArrayList<byte[]> arrayListG = g(iL - length2);
        byte[] bArr2 = new byte[iL];
        System.arraycopy(this.e, i3, bArr2, 0, length2);
        for (byte[] bArr3 : arrayListG) {
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
            length2 += bArr3.length;
        }
        Q7 q9 = U7.c;
        return new Q7(bArr2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int j() {
        return l();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final float i() {
        return Float.intBitsToFloat(v());
    }
}
