package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ld, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0638Ld {
    public int c;
    public final InputStream e;
    public int f;
    public int i;
    public int h = Integer.MAX_VALUE;
    public final byte[] a = new byte[4096];
    public int b = 0;
    public int d = 0;
    public int g = 0;

    public C0638Ld(InputStream inputStream) {
        this.e = inputStream;
    }

    public final boolean a(int i, C0767Qd c0767Qd) throws IOException {
        int i2;
        int i3 = i & 7;
        if (i3 == 0) {
            long jG = g();
            c0767Qd.g(i);
            c0767Qd.d(jG);
            return true;
        }
        if (i3 == 1) {
            long jE = e();
            c0767Qd.g(i);
            c0767Qd.c(jE);
            return true;
        }
        if (i3 == 2) {
            CL clB = b();
            c0767Qd.g(i);
            c0767Qd.g(clB.d.length);
            c0767Qd.a(clB);
            return true;
        }
        if (i3 != 3) {
            if (i3 == 4) {
                return false;
            }
            if (i3 != 5) {
                throw new QB("Protocol message tag had invalid wire type.");
            }
            int iD = d();
            c0767Qd.g(i);
            c0767Qd.f(iD);
            return true;
        }
        c0767Qd.g(i);
        do {
            i2 = i();
            if (i2 == 0) {
                break;
            }
        } while (a(i2, c0767Qd));
        int i4 = ((i >>> 3) << 3) | 4;
        if (this.f != i4) {
            throw new QB("Protocol message end-group tag did not match expected tag.");
        }
        c0767Qd.g(i4);
        return true;
    }

    public final CL b() {
        int iF = f();
        int i = this.b;
        int i2 = this.d;
        if (iF > i - i2 || iF <= 0) {
            return iF == 0 ? T7.b : new CL(c(iF));
        }
        byte[] bArr = new byte[iF];
        System.arraycopy(this.a, i2, bArr, 0, iF);
        CL cl = new CL(bArr);
        this.d += iF;
        return cl;
    }

    public final byte[] c(int i) throws QB {
        if (i <= 0) {
            if (i == 0) {
                return AbstractC1470fB.a;
            }
            throw new QB("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i2 = this.g;
        int i3 = this.d;
        int i4 = i2 + i3 + i;
        int i5 = this.h;
        if (i4 > i5) {
            e((i5 - i2) - i3);
            throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        if (i < 4096) {
            byte[] bArr = new byte[i];
            int i6 = this.b - i3;
            System.arraycopy(this.a, i3, bArr, 0, i6);
            this.d = this.b;
            int i7 = i - i6;
            if (i7 > 0) {
                d(i7);
            }
            System.arraycopy(this.a, 0, bArr, i6, i7);
            this.d = i7;
            return bArr;
        }
        int i8 = this.b;
        this.g = i2 + i8;
        this.d = 0;
        this.b = 0;
        int length = i8 - i3;
        int i9 = i - length;
        ArrayList<byte[]> arrayList = new ArrayList();
        while (i9 > 0) {
            int iMin = Math.min(i9, 4096);
            byte[] bArr2 = new byte[iMin];
            int i10 = 0;
            while (i10 < iMin) {
                InputStream inputStream = this.e;
                int i11 = inputStream == null ? -1 : inputStream.read(bArr2, i10, iMin - i10);
                if (i11 == -1) {
                    throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
                }
                this.g += i11;
                i10 += i11;
            }
            i9 -= iMin;
            arrayList.add(bArr2);
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(this.a, i3, bArr3, 0, length);
        for (byte[] bArr4 : arrayList) {
            System.arraycopy(bArr4, 0, bArr3, length, bArr4.length);
            length += bArr4.length;
        }
        return bArr3;
    }

    public final int d() throws QB {
        int i = this.d;
        if (this.b - i < 4) {
            d(4);
            i = this.d;
        }
        byte[] bArr = this.a;
        this.d = i + 4;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    public final long e() throws QB {
        int i = this.d;
        if (this.b - i < 8) {
            d(8);
            i = this.d;
        }
        byte[] bArr = this.a;
        this.d = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final boolean f(int i) throws IOException {
        int i2 = this.d;
        int i3 = i2 + i;
        int i4 = this.b;
        if (i3 <= i4) {
            StringBuilder sb = new StringBuilder(77);
            sb.append("refillBuffer() called when ");
            sb.append(i);
            sb.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb.toString());
        }
        if (this.g + i2 + i <= this.h && this.e != null) {
            if (i2 > 0) {
                if (i4 > i2) {
                    byte[] bArr = this.a;
                    System.arraycopy(bArr, i2, bArr, 0, i4 - i2);
                }
                this.g += i2;
                this.b -= i2;
                this.d = 0;
            }
            InputStream inputStream = this.e;
            byte[] bArr2 = this.a;
            int i5 = this.b;
            int i6 = inputStream.read(bArr2, i5, bArr2.length - i5);
            if (i6 == 0 || i6 < -1 || i6 > this.a.length) {
                StringBuilder sb2 = new StringBuilder(102);
                sb2.append("InputStream#read(byte[]) returned invalid result: ");
                sb2.append(i6);
                sb2.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb2.toString());
            }
            if (i6 > 0) {
                this.b += i6;
                if ((this.g + i) - 67108864 > 0) {
                    throw new QB("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                }
                j();
                if (this.b >= i) {
                    return true;
                }
                return f(i);
            }
        }
        return false;
    }

    public final long g() {
        long j;
        long j2;
        long j3;
        int i = this.d;
        int i2 = this.b;
        if (i2 != i) {
            byte[] bArr = this.a;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.d = i3;
                return b;
            }
            if (i2 - i3 >= 9) {
                int i4 = i + 2;
                long j4 = (bArr[i3] << 7) ^ b;
                if (j4 >= 0) {
                    int i5 = i + 3;
                    long j5 = j4 ^ ((long) (bArr[i4] << 14));
                    if (j5 >= 0) {
                        j3 = 16256;
                    } else {
                        i4 = i + 4;
                        j4 = j5 ^ ((long) (bArr[i5] << 21));
                        if (j4 < 0) {
                            j2 = -2080896;
                        } else {
                            i5 = i + 5;
                            j5 = j4 ^ (((long) bArr[i4]) << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                i4 = i + 6;
                                j4 = j5 ^ (((long) bArr[i5]) << 35);
                                if (j4 >= 0) {
                                    i5 = i + 7;
                                    j5 = j4 ^ (((long) bArr[i4]) << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i4 = i + 8;
                                        j4 = j5 ^ (((long) bArr[i5]) << 49);
                                        if (j4 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            int i6 = i + 9;
                                            long j6 = (j4 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j6 < 0) {
                                                int i7 = i + 10;
                                                if (bArr[i6] >= 0) {
                                                    i4 = i7;
                                                }
                                            } else {
                                                i4 = i6;
                                            }
                                            j = j6;
                                        }
                                    }
                                    this.d = i4;
                                    return j;
                                }
                                j2 = -34093383808L;
                            }
                        }
                    }
                    j = j5 ^ j3;
                    i4 = i5;
                    this.d = i4;
                    return j;
                }
                j2 = -128;
                j = j4 ^ j2;
                this.d = i4;
                return j;
            }
        }
        return h();
    }

    public final long h() throws QB {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            if (this.d == this.b) {
                d(1);
            }
            byte[] bArr = this.a;
            int i2 = this.d;
            this.d = i2 + 1;
            byte b = bArr[i2];
            j |= ((long) (b & 127)) << i;
            if ((b & 128) == 0) {
                return j;
            }
        }
        throw new QB("CodedInputStream encountered a malformed varint.");
    }

    public final int i() throws QB {
        if (this.d == this.b && !f(1)) {
            this.f = 0;
            return 0;
        }
        int iF = f();
        this.f = iF;
        if ((iF >>> 3) != 0) {
            return iF;
        }
        throw new QB("Protocol message contained an invalid tag (zero).");
    }

    public final void j() {
        int i = this.b + this.c;
        this.b = i;
        int i2 = this.g + i;
        int i3 = this.h;
        if (i2 <= i3) {
            this.c = 0;
            return;
        }
        int i4 = i2 - i3;
        this.c = i4;
        this.b = i - i4;
    }

    public final int b(int i) throws QB {
        if (i >= 0) {
            int i2 = this.g + this.d + i;
            int i3 = this.h;
            if (i2 <= i3) {
                this.h = i2;
                j();
                return i3;
            }
            throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new QB("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public final void d(int i) throws QB {
        if (!f(i)) {
            throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final void e(int i) throws QB {
        int i2 = this.b;
        int i3 = this.d;
        int i4 = i2 - i3;
        if (i <= i4 && i >= 0) {
            this.d = i3 + i;
            return;
        }
        if (i >= 0) {
            int i5 = this.g;
            int i6 = i5 + i3 + i;
            int i7 = this.h;
            if (i6 <= i7) {
                this.d = i2;
                d(1);
                while (true) {
                    int i8 = i - i4;
                    int i9 = this.b;
                    if (i8 > i9) {
                        i4 += i9;
                        this.d = i9;
                        d(1);
                    } else {
                        this.d = i8;
                        return;
                    }
                }
            } else {
                e((i7 - i5) - i3);
                throw new QB("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
            }
        } else {
            throw new QB("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
    }

    public final L0 a(InterfaceC2174nW interfaceC2174nW, C0389Bo c0389Bo) throws QB {
        int iF = f();
        if (this.i < 64) {
            int iB = b(iF);
            this.i++;
            L0 l0 = (L0) interfaceC2174nW.a(this, c0389Bo);
            if (this.f == 0) {
                this.i--;
                this.h = iB;
                j();
                return l0;
            }
            throw new QB("Protocol message end-group tag did not match expected tag.");
        }
        throw new QB("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final void a(int i) {
        this.h = i;
        j();
    }

    public final int a() {
        int i = this.h;
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i - (this.g + this.d);
    }

    public final int c() {
        return f();
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0086 A[PHI: r3
      0x0086: PHI (r3v8 int) = (r3v7 int), (r3v10 int) binds: [B:25:0x0064, B:29:0x0070] A[DONT_GENERATE, DONT_INLINE]] */
    public final int f() {
        int i;
        int i2 = this.d;
        int i3 = this.b;
        if (i3 != i2) {
            byte[] bArr = this.a;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.d = i4;
                return b;
            }
            if (i3 - i4 >= 9) {
                int i5 = i2 + 2;
                int i6 = (bArr[i4] << 7) ^ b;
                long j = i6;
                if (j < 0) {
                    i = (int) ((-128) ^ j);
                } else {
                    int i7 = i2 + 3;
                    int i8 = (bArr[i5] << 14) ^ i6;
                    long j2 = i8;
                    if (j2 >= 0) {
                        i = (int) (16256 ^ j2);
                    } else {
                        int i9 = i2 + 4;
                        int i10 = i8 ^ (bArr[i7] << 21);
                        long j3 = i10;
                        if (j3 < 0) {
                            i = (int) ((-2080896) ^ j3);
                            i5 = i9;
                        } else {
                            i7 = i2 + 5;
                            byte b2 = bArr[i9];
                            int i11 = (int) (((long) (i10 ^ (b2 << 28))) ^ 266354560);
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
                this.d = i5;
                return i;
            }
        }
        return (int) h();
    }
}
