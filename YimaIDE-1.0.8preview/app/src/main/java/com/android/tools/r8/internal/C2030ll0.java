package com.android.tools.r8.internal;

import defpackage.ckh;
import defpackage.dkh;
import defpackage.go7;
import defpackage.ydh;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ll0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2030ll0 extends AbstractC1774il0 {
    @Override // com.android.tools.r8.internal.AbstractC1774il0
    public final int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        long j2;
        long j3;
        int i3;
        char cCharAt;
        long j4 = i;
        long j5 = ((long) i2) + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            dkh.a(charSequence.charAt(length - 1), i + i2);
            return 0;
        }
        int i4 = 0;
        while (true) {
            j = 1;
            if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            AbstractC1263cl0.b.a((Object) bArr, AbstractC1263cl0.e + j4, (byte) cCharAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char cCharAt2 = charSequence.charAt(i4);
            if (cCharAt2 >= 128 || j4 >= j5) {
                if (cCharAt2 >= 2048 || j4 > j5 - 2) {
                    j2 = j;
                    if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j4 > j5 - 3) {
                        if (j4 > j5 - 4) {
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                                throw new C1944kl0(i4, length);
                            }
                            ckh.a(cCharAt2, j4);
                            return 0;
                        }
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char cCharAt3 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                AbstractC1180bl0 abstractC1180bl0 = AbstractC1263cl0.b;
                                long j6 = AbstractC1263cl0.e;
                                abstractC1180bl0.a((Object) bArr, j6 + j4, (byte) ((codePoint >>> 18) | 240));
                                abstractC1180bl0.a((Object) bArr, j6 + j4 + j2, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j7 = j4 + 3;
                                abstractC1180bl0.a((Object) bArr, j6 + j4 + 2, (byte) (((codePoint >>> 6) & 63) | 128));
                                j4 += 4;
                                abstractC1180bl0.a((Object) bArr, j6 + j7, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                            } else {
                                i4 = i5;
                            }
                        }
                        throw new C1944kl0(i4 - 1, length);
                    }
                    AbstractC1180bl0 abstractC1180bl1 = AbstractC1263cl0.b;
                    long j8 = AbstractC1263cl0.e;
                    abstractC1180bl1.a((Object) bArr, j8 + j4, (byte) ((cCharAt2 >>> '\f') | 480));
                    abstractC1180bl1.a((Object) bArr, j8 + j4 + j2, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    abstractC1180bl1.a((Object) bArr, j8 + j4 + 2, (byte) ((cCharAt2 & '?') | 128));
                    j3 = j4 + 3;
                } else {
                    long j9 = j4 + j;
                    AbstractC1180bl0 abstractC1180bl2 = AbstractC1263cl0.b;
                    long j10 = AbstractC1263cl0.e;
                    j2 = j;
                    abstractC1180bl2.a((Object) bArr, j10 + j4, (byte) ((cCharAt2 >>> 6) | 960));
                    j4 += 2;
                    abstractC1180bl2.a((Object) bArr, j10 + j9, (byte) ((cCharAt2 & '?') | 128));
                }
                i4++;
                j = j2;
            } else {
                j3 = j4 + j;
                AbstractC1263cl0.b.a((Object) bArr, AbstractC1263cl0.e + j4, (byte) cCharAt2);
                j2 = j;
            }
            j4 = j3;
            i4++;
            j = j2;
        }
        return (int) j4;
    }

    @Override // com.android.tools.r8.internal.AbstractC1774il0
    public final int b(byte[] bArr, int i, int i2) {
        if ((i | i2 | (bArr.length - i2)) < 0) {
            ydh.a("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)});
            return 0;
        }
        long j = i;
        int i3 = (int) (((long) i2) - j);
        int iA = a(bArr, j, i3);
        int i4 = i3 - iA;
        long j2 = j + ((long) iA);
        while (true) {
            byte b = 0;
            while (i4 > 0) {
                long j3 = j2 + 1;
                byte bA = AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + j2);
                if (bA < 0) {
                    b = bA;
                    j2 = j3;
                    break;
                }
                i4--;
                b = bA;
                j2 = j3;
            }
            if (i4 == 0) {
                return 0;
            }
            int i5 = i4 - 1;
            if (b < -32) {
                if (i5 == 0) {
                    return b;
                }
                i4 -= 2;
                if (b < -62) {
                    return -1;
                }
                long j4 = j2 + 1;
                if (AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + j2) > -65) {
                    return -1;
                }
                j2 = j4;
            } else if (b < -16) {
                if (i5 < 2) {
                    return a(bArr, b, j2, i5);
                }
                i4 -= 3;
                long j5 = 1 + j2;
                AbstractC1180bl0 abstractC1180bl0 = AbstractC1263cl0.b;
                long j6 = AbstractC1263cl0.e;
                byte bA2 = abstractC1180bl0.a(bArr, j6 + j2);
                if (bA2 > -65) {
                    return -1;
                }
                if (b == -32 && bA2 < -96) {
                    return -1;
                }
                if (b == -19 && bA2 >= -96) {
                    return -1;
                }
                j2 += 2;
                if (abstractC1180bl0.a(bArr, j6 + j5) > -65) {
                    return -1;
                }
            } else {
                if (i5 < 3) {
                    return a(bArr, b, j2, i5);
                }
                i4 -= 4;
                long j7 = 1 + j2;
                AbstractC1180bl0 abstractC1180bl1 = AbstractC1263cl0.b;
                long j8 = AbstractC1263cl0.e;
                byte bA3 = abstractC1180bl1.a(bArr, j8 + j2);
                if (bA3 > -65) {
                    return -1;
                }
                if ((((bA3 + 112) + (b << 28)) >> 30) != 0) {
                    return -1;
                }
                long j9 = j2 + 2;
                if (abstractC1180bl1.a(bArr, j7 + j8) > -65) {
                    return -1;
                }
                j2 += 3;
                if (abstractC1180bl1.a(bArr, j8 + j9) > -65) {
                    return -1;
                }
            }
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1774il0
    public final String a(byte[] bArr, int i, int i2) throws RB {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int iA = a(bArr, i, i2) + i;
            int i3 = i + i2;
            while (iA < i3) {
                if (AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + ((long) iA)) < 0) {
                    break;
                }
                iA++;
            }
            if (iA == i3) {
                return new String(bArr, i, i2, AbstractC1556gB.a);
            }
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < iA) {
                cArr[i4] = (char) AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + ((long) i));
                i++;
                i4++;
            }
            int i5 = i4;
            while (iA < i3) {
                int i6 = iA + 1;
                AbstractC1180bl0 abstractC1180bl0 = AbstractC1263cl0.b;
                long j = AbstractC1263cl0.e;
                byte bA = abstractC1180bl0.a(bArr, ((long) iA) + j);
                if (bA >= 0) {
                    cArr[i5] = (char) bA;
                    i5++;
                    iA = i6;
                    while (iA < i3) {
                        byte bA2 = AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + ((long) iA));
                        if (bA2 < 0) {
                            break;
                        }
                        iA++;
                        cArr[i5] = (char) bA2;
                        i5++;
                    }
                } else if (bA < -32) {
                    if (i6 < i3) {
                        iA += 2;
                        byte bA3 = abstractC1180bl0.a(bArr, j + ((long) i6));
                        int i7 = i5 + 1;
                        if (bA >= -62 && !AbstractC1689hl0.a(bA3)) {
                            cArr[i5] = (char) ((bA3 & 63) | ((bA & 31) << 6));
                            i5 = i7;
                        } else {
                            go7.a("Protocol message had invalid UTF-8.");
                            return null;
                        }
                    } else {
                        go7.a("Protocol message had invalid UTF-8.");
                        return null;
                    }
                } else if (bA < -16) {
                    if (i6 < i3 - 1) {
                        int i8 = iA + 2;
                        iA += 3;
                        AbstractC1689hl0.a(bA, abstractC1180bl0.a(bArr, ((long) i6) + j), abstractC1180bl0.a(bArr, j + ((long) i8)), cArr, i5);
                        i5++;
                    } else {
                        go7.a("Protocol message had invalid UTF-8.");
                        return null;
                    }
                } else if (i6 < i3 - 2) {
                    byte bA4 = abstractC1180bl0.a(bArr, ((long) i6) + j);
                    int i9 = iA + 3;
                    byte bA5 = abstractC1180bl0.a(bArr, ((long) (iA + 2)) + j);
                    iA += 4;
                    AbstractC1689hl0.a(bA, bA4, bA5, abstractC1180bl0.a(bArr, j + ((long) i9)), cArr, i5);
                    i5 += 2;
                } else {
                    go7.a("Protocol message had invalid UTF-8.");
                    return null;
                }
            }
            return new String(cArr, 0, i5);
        }
        ydh.a("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)});
        return null;
    }

    public static int a(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            AbstractC1774il0 abstractC1774il0 = AbstractC2201nl0.a;
            if (i > -12) {
                return -1;
            }
            return i;
        }
        if (i2 == 1) {
            return AbstractC2201nl0.a(i, AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + j));
        }
        if (i2 == 2) {
            AbstractC1180bl0 abstractC1180bl0 = AbstractC1263cl0.b;
            long j2 = AbstractC1263cl0.e;
            return AbstractC2201nl0.a(i, abstractC1180bl0.a(bArr, j2 + j), abstractC1180bl0.a(bArr, j + 1 + j2));
        }
        x1f.a();
        return 0;
    }

    public static int a(byte[] bArr, long j, int i) {
        int i2 = 0;
        if (i < 16) {
            return 0;
        }
        while (true) {
            int i3 = i2 + 8;
            if (i3 > i) {
                break;
            }
            if ((AbstractC1263cl0.b.c(bArr, AbstractC1263cl0.e + j) & (-9187201950435737472L)) != 0) {
                break;
            }
            j += 8;
            i2 = i3;
        }
        while (i2 < i) {
            long j2 = 1 + j;
            if (AbstractC1263cl0.b.a(bArr, AbstractC1263cl0.e + j) < 0) {
                return i2;
            }
            i2++;
            j = j2;
        }
        return i;
    }
}
