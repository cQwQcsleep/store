package com.android.tools.r8.internal;

import defpackage.go7;
import defpackage.ydh;
import defpackage.zdh;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1859jl0 extends AbstractC1774il0 {
    @Override // com.android.tools.r8.internal.AbstractC1774il0
    public final int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        char cCharAt;
        int length = charSequence.length();
        int i5 = i2 + i;
        int i6 = 0;
        while (i6 < length && (i4 = i6 + i) < i5 && (cCharAt = charSequence.charAt(i6)) < 128) {
            bArr[i4] = (byte) cCharAt;
            i6++;
        }
        if (i6 == length) {
            return i + length;
        }
        int i7 = i + i6;
        while (i6 < length) {
            char cCharAt2 = charSequence.charAt(i6);
            if (cCharAt2 < 128 && i7 < i5) {
                bArr[i7] = (byte) cCharAt2;
                i7++;
            } else if (cCharAt2 < 2048 && i7 <= i5 - 2) {
                int i8 = i7 + 1;
                bArr[i7] = (byte) ((cCharAt2 >>> 6) | 960);
                i7 += 2;
                bArr[i8] = (byte) ((cCharAt2 & '?') | 128);
            } else {
                if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i7 > i5 - 3) {
                    if (i7 > i5 - 4) {
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i6 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                            throw new C1944kl0(i6, length);
                        }
                        zdh.a(cCharAt2, i7);
                        return 0;
                    }
                    int i9 = i6 + 1;
                    if (i9 != charSequence.length()) {
                        char cCharAt3 = charSequence.charAt(i9);
                        if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                            int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            bArr[i7] = (byte) ((codePoint >>> 18) | 240);
                            bArr[i7 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i10 = i7 + 3;
                            bArr[i7 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i7 += 4;
                            bArr[i10] = (byte) ((codePoint & 63) | 128);
                            i6 = i9;
                        } else {
                            i6 = i9;
                        }
                    }
                    throw new C1944kl0(i6 - 1, length);
                }
                bArr[i7] = (byte) ((cCharAt2 >>> '\f') | 480);
                int i11 = i7 + 2;
                bArr[i7 + 1] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                i7 += 3;
                bArr[i11] = (byte) ((cCharAt2 & '?') | 128);
            }
            i6++;
        }
        return i7;
    }

    /* JADX WARN: Code duplicated, block: B:62:0x0093 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:63:0x0094 A[RETURN] */
    @Override // com.android.tools.r8.internal.AbstractC1774il0
    public final int b(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] >= 0) {
            i++;
        }
        if (i < i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b < 0) {
                    if (b < -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b >= -62) {
                            i += 2;
                            if (bArr[i3] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b < -16) {
                        if (i3 < i2 - 1) {
                            int i4 = i + 2;
                            byte b2 = bArr[i3];
                            if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                                i += 3;
                                if (bArr[i4] > -65) {
                                }
                            }
                            return -1;
                        }
                        int i5 = i2 - i3;
                        if (i5 == 0) {
                            if (b > -12) {
                                return -1;
                            }
                            return b;
                        }
                        if (i5 == 1) {
                            return AbstractC2201nl0.a(b, bArr[i3]);
                        }
                        if (i5 == 2) {
                            return AbstractC2201nl0.a(b, bArr[i3], bArr[i + 2]);
                        }
                        x1f.a();
                        return 0;
                    }
                    if (i3 < i2 - 2) {
                        int i6 = i + 2;
                        byte b3 = bArr[i3];
                        if (b3 <= -65) {
                            if ((((b3 + 112) + (b << 28)) >> 30) == 0) {
                                int i7 = i + 3;
                                if (bArr[i6] <= -65) {
                                    i += 4;
                                    if (bArr[i7] > -65) {
                                    }
                                }
                            }
                        }
                        return -1;
                    }
                    int i8 = i2 - i3;
                    if (i8 == 0) {
                        if (b > -12) {
                            return -1;
                        }
                        return b;
                    }
                    if (i8 == 1) {
                        return AbstractC2201nl0.a(b, bArr[i3]);
                    }
                    if (i8 == 2) {
                        return AbstractC2201nl0.a(b, bArr[i3], bArr[i + 2]);
                    }
                    x1f.a();
                    return 0;
                }
                i = i3;
            }
        }
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1774il0
    public final String a(byte[] bArr, int i, int i2) throws RB {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = bArr[i];
                if (b < 0) {
                    break;
                }
                i++;
                cArr[i4] = (char) b;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b2 = bArr[i];
                if (b2 >= 0) {
                    cArr[i5] = (char) b2;
                    i5++;
                    i = i6;
                    while (i < i3) {
                        byte b3 = bArr[i];
                        if (b3 < 0) {
                            break;
                        }
                        i++;
                        cArr[i5] = (char) b3;
                        i5++;
                    }
                } else if (b2 < -32) {
                    if (i6 < i3) {
                        i += 2;
                        byte b4 = bArr[i6];
                        int i7 = i5 + 1;
                        if (b2 >= -62 && !AbstractC1689hl0.a(b4)) {
                            cArr[i5] = (char) (((b2 & 31) << 6) | (b4 & 63));
                            i5 = i7;
                        } else {
                            go7.a("Protocol message had invalid UTF-8.");
                            return null;
                        }
                    } else {
                        go7.a("Protocol message had invalid UTF-8.");
                        return null;
                    }
                } else if (b2 < -16) {
                    if (i6 < i3 - 1) {
                        int i8 = i + 2;
                        i += 3;
                        AbstractC1689hl0.a(b2, bArr[i6], bArr[i8], cArr, i5);
                        i5++;
                    } else {
                        go7.a("Protocol message had invalid UTF-8.");
                        return null;
                    }
                } else if (i6 < i3 - 2) {
                    byte b5 = bArr[i6];
                    int i9 = i + 3;
                    byte b6 = bArr[i + 2];
                    i += 4;
                    AbstractC1689hl0.a(b2, b5, b6, bArr[i9], cArr, i5);
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
}
