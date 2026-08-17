package com.android.tools.r8.internal;

import defpackage.go7;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0586Jd extends AbstractC0663Md {
    public final byte[] d;
    public int e;
    public int f;
    public int g;
    public final int h;
    public int i;
    public int j = Integer.MAX_VALUE;

    public C0586Jd(byte[] bArr, int i, int i2, boolean z) {
        this.d = bArr;
        this.e = i2 + i;
        this.g = i;
        this.h = i;
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
        this.j = iC;
        y();
        return tn;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int b() {
        int i = this.j;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - (this.g - this.h);
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int c(int i) {
        if (i < 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            return 0;
        }
        int i2 = (this.g - this.h) + i;
        if (i2 < 0) {
            go7.a("Failed to parse the message.");
            return 0;
        }
        int i3 = this.j;
        if (i2 > i3) {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return 0;
        }
        this.j = i2;
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
                e(8);
                return true;
            }
            if (i2 == 2) {
                e(l());
                return true;
            }
            if (i2 != 3) {
                if (i2 == 4) {
                    return false;
                }
                if (i2 != 5) {
                    throw new PB();
                }
                e(4);
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
        if (this.e - this.g >= 10) {
            while (i3 < 10) {
                byte[] bArr = this.d;
                int i4 = this.g;
                this.g = i4 + 1;
                if (bArr[i4] < 0) {
                    i3++;
                }
            }
            go7.a("CodedInputStream encountered a malformed varint.");
            return false;
        }
        while (i3 < 10) {
            int i5 = this.g;
            if (i5 == this.e) {
                go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                return false;
            }
            byte[] bArr2 = this.d;
            this.g = i5 + 1;
            if (bArr2[i5] < 0) {
                i3++;
            }
        }
        go7.a("CodedInputStream encountered a malformed varint.");
        return false;
        return true;
    }

    public final void e(int i) throws RB {
        if (i >= 0) {
            int i2 = this.e;
            int i3 = this.g;
            if (i <= i2 - i3) {
                this.g = i3 + i;
                return;
            }
        }
        if (i < 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int f() {
        return l();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int g() {
        return v();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long h() {
        return w();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final float i() {
        return Float.intBitsToFloat(v());
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int j() {
        return l();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final long k() {
        return x();
    }

    /* JADX WARN: Code duplicated, block: B:49:0x009f A[PHI: r3
      0x009f: PHI (r3v10 int) = (r3v9 int), (r3v12 int) binds: [B:25:0x0053, B:29:0x005f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int l() throws RB {
        int i;
        int i2 = this.g;
        int i3 = this.e;
        if (i3 != i2) {
            byte[] bArr = this.d;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.g = i4;
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
                this.g = i5;
                return i;
            }
        }
        long j = 0;
        for (int i14 = 0; i14 < 64; i14 += 7) {
            int i15 = this.g;
            if (i15 == this.e) {
                go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                return 0;
            }
            byte[] bArr2 = this.d;
            this.g = i15 + 1;
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
            int i = this.e;
            int i2 = this.g;
            if (iL <= i - i2) {
                String str = new String(this.d, i2, iL, AbstractC1556gB.b);
                this.g += iL;
                return str;
            }
        }
        if (iL == 0) {
            return XmlPullParser.NO_NAMESPACE;
        }
        if (iL < 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            return null;
        }
        go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final String r() throws RB {
        int iL = l();
        if (iL > 0) {
            int i = this.e;
            int i2 = this.g;
            if (iL <= i - i2) {
                String strA = AbstractC2201nl0.a.a(this.d, i2, iL);
                this.g += iL;
                return strA;
            }
        }
        if (iL == 0) {
            return XmlPullParser.NO_NAMESPACE;
        }
        if (iL <= 0) {
            go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            return null;
        }
        go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final int s() throws RB {
        if (this.g == this.e) {
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
        int i = this.g;
        if (this.e - i < 4) {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return 0;
        }
        byte[] bArr = this.d;
        this.g = i + 4;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    public final long w() throws RB {
        int i = this.g;
        if (this.e - i < 8) {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            return 0L;
        }
        byte[] bArr = this.d;
        this.g = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final long x() throws RB {
        long j;
        long j2;
        long j3;
        int i = this.g;
        int i2 = this.e;
        if (i2 != i) {
            byte[] bArr = this.d;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.g = i3;
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
                this.g = i4;
                return j;
            }
        }
        long j8 = 0;
        for (int i12 = 0; i12 < 64; i12 += 7) {
            int i13 = this.g;
            if (i13 == this.e) {
                go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                return 0L;
            }
            byte[] bArr2 = this.d;
            this.g = i13 + 1;
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
        int i = this.e + this.f;
        this.e = i;
        int i2 = i - this.h;
        int i3 = this.j;
        if (i2 <= i3) {
            this.f = 0;
            return;
        }
        int i4 = i2 - i3;
        this.f = i4;
        this.e = i - i4;
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final void b(int i) {
        this.j = i;
        y();
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final double e() {
        return Double.longBitsToDouble(w());
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final boolean c() {
        return x() != 0;
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
            this.j = iC;
            y();
        } else {
            go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final void a(int i) {
        if (this.i == i) {
            return;
        }
        go7.a("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0040  */
    /* JADX WARN: Code duplicated, block: B:17:0x0043 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:18:0x0045  */
    /* JADX WARN: Code duplicated, block: B:21:0x004f  */
    /* JADX WARN: Code duplicated, block: B:23:0x0055  */
    @Override // com.android.tools.r8.internal.AbstractC0663Md
    public final Q7 d() throws RB {
        byte[] bArrCopyOfRange;
        int iL = l();
        if (iL > 0) {
            int i = this.e;
            int i2 = this.g;
            if (iL <= i - i2) {
                byte[] bArr = this.d;
                Q7 q7 = U7.c;
                U7.a(i2, i2 + iL, bArr.length);
                Q7 q8 = new Q7(U7.d.a(bArr, i2, iL));
                this.g += iL;
                return q8;
            }
        }
        if (iL == 0) {
            return U7.c;
        }
        if (iL > 0) {
            int i3 = this.e;
            int i4 = this.g;
            if (iL <= i3 - i4) {
                int i5 = iL + i4;
                this.g = i5;
                bArrCopyOfRange = Arrays.copyOfRange(this.d, i4, i5);
            } else {
                if (iL <= 0) {
                    go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                    return null;
                }
                if (iL == 0) {
                    bArrCopyOfRange = AbstractC1556gB.d;
                } else {
                    go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                    return null;
                }
            }
        } else {
            if (iL <= 0) {
                go7.a("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                return null;
            }
            if (iL == 0) {
                bArrCopyOfRange = AbstractC1556gB.d;
            } else {
                go7.a("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                return null;
            }
        }
        Q7 q9 = U7.c;
        return new Q7(bArrCopyOfRange);
    }
}
