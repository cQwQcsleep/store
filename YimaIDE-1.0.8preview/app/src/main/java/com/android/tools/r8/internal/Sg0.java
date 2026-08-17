package com.android.tools.r8.internal;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Sg0 {
    public static final Logger a = Logger.getLogger(Sg0.class.getName());

    static {
        int i = Aj0.b;
        Aj0 aj0 = AbstractC3222zj0.a;
    }

    public static Q7 a(String str) throws Og0 {
        int i;
        int i2;
        int length;
        int i3;
        byte b;
        byte b2;
        String string = str.toString();
        Q7 q7 = U7.c;
        byte[] bytes = string.getBytes(AbstractC1556gB.b);
        Q7 q8 = new Q7(bytes);
        int length2 = bytes.length;
        byte[] bArr = new byte[length2];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            byte[] bArr2 = q8.e;
            if (i4 >= bArr2.length) {
                if (length2 == i5) {
                    return new Q7(bArr);
                }
                U7.a(0, i5, length2);
                return new Q7(U7.d.a(bArr, 0, i5));
            }
            byte b3 = bArr2[i4];
            if (b3 == 92) {
                int i6 = i4 + 1;
                if (i6 >= bArr2.length) {
                    throw new Og0("Invalid escape sequence: '\\' at end of string.");
                }
                byte b4 = bArr2[i6];
                if (48 > b4 || b4 > 55) {
                    if (b4 == 34) {
                        i = i5 + 1;
                        bArr[i5] = 34;
                    } else if (b4 == 39) {
                        i = i5 + 1;
                        bArr[i5] = 39;
                    } else if (b4 != 63) {
                        if (b4 == 85) {
                            int i7 = i4 + 2;
                            i2 = i4 + 9;
                            if (i2 >= bArr2.length) {
                                throw new Og0("Invalid escape sequence: '\\U' with too few hex chars");
                            }
                            int iA = 0;
                            int i8 = i7;
                            while (true) {
                                int i9 = i4 + 10;
                                if (i8 < i9) {
                                    byte b5 = q8.e[i8];
                                    if (!b(b5)) {
                                        throw new Og0("Invalid escape sequence: '\\U' with too few hex chars");
                                    }
                                    iA = (iA << 4) | a(b5);
                                    i8++;
                                } else {
                                    if (!Character.isValidCodePoint(iA)) {
                                        StringBuilder sb = new StringBuilder("Invalid escape sequence: '\\U");
                                        int iA2 = U7.a(i7, i9, q8.size());
                                        sb.append((iA2 == 0 ? U7.c : new N7(q8.e, i7, iA2)).c());
                                        sb.append("' is not a valid code point value");
                                        throw new Og0(sb.toString());
                                    }
                                    Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(iA);
                                    if (unicodeBlockOf.equals(Character.UnicodeBlock.LOW_SURROGATES) || unicodeBlockOf.equals(Character.UnicodeBlock.HIGH_SURROGATES) || unicodeBlockOf.equals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES)) {
                                        StringBuilder sb2 = new StringBuilder("Invalid escape sequence: '\\U");
                                        int iA3 = U7.a(i7, i9, q8.size());
                                        sb2.append((iA3 == 0 ? U7.c : new N7(q8.e, i7, iA3)).c());
                                        sb2.append("' refers to a surrogate code unit");
                                        throw new Og0(sb2.toString());
                                    }
                                    byte[] bytes2 = new String(new int[]{iA}, 0, 1).getBytes(StandardCharsets.UTF_8);
                                    System.arraycopy(bytes2, 0, bArr, i5, bytes2.length);
                                    length = bytes2.length;
                                }
                            }
                        } else if (b4 == 92) {
                            i = i5 + 1;
                            bArr[i5] = 92;
                        } else if (b4 == 102) {
                            i = i5 + 1;
                            bArr[i5] = 12;
                        } else if (b4 == 110) {
                            i = i5 + 1;
                            bArr[i5] = 10;
                        } else if (b4 == 114) {
                            i = i5 + 1;
                            bArr[i5] = 13;
                        } else if (b4 == 120) {
                            int i10 = i4 + 2;
                            if (i10 >= bArr2.length || !b(bArr2[i10])) {
                                throw new Og0("Invalid escape sequence: '\\x' with no digits");
                            }
                            int iA4 = a(q8.e[i10]);
                            i4 += 3;
                            byte[] bArr3 = q8.e;
                            if (i4 >= bArr3.length || !b(bArr3[i4])) {
                                i4 = i10;
                            } else {
                                iA4 = (iA4 * 16) + a(q8.e[i4]);
                            }
                            i3 = i5 + 1;
                            bArr[i5] = (byte) iA4;
                        } else if (b4 == 97) {
                            i = i5 + 1;
                            bArr[i5] = 7;
                        } else if (b4 != 98) {
                            switch (b4) {
                                case 116:
                                    i = i5 + 1;
                                    bArr[i5] = 9;
                                    break;
                                case 117:
                                    int i11 = i4 + 2;
                                    i2 = i4 + 5;
                                    if (i2 < bArr2.length && b(bArr2[i11])) {
                                        int i12 = i4 + 3;
                                        if (b(q8.e[i12])) {
                                            int i13 = i4 + 4;
                                            if (b(q8.e[i13]) && b(q8.e[i2])) {
                                                char cA = (char) ((a(q8.e[i13]) << 4) | (a(q8.e[i12]) << 8) | (a(q8.e[i11]) << 12) | a(q8.e[i2]));
                                                if (Character.isSurrogate(cA)) {
                                                    throw new Og0("Invalid escape sequence: '\\u' refers to a surrogate");
                                                }
                                                byte[] bytes3 = Character.toString(cA).getBytes(StandardCharsets.UTF_8);
                                                System.arraycopy(bytes3, 0, bArr, i5, bytes3.length);
                                                length = bytes3.length;
                                                break;
                                            }
                                        }
                                    }
                                    throw new Og0("Invalid escape sequence: '\\u' with too few hex chars");
                                case 118:
                                    i = i5 + 1;
                                    bArr[i5] = 11;
                                    break;
                                default:
                                    throw new Og0("Invalid escape sequence: '\\" + ((char) b4) + '\'');
                            }
                        } else {
                            i = i5 + 1;
                            bArr[i5] = 8;
                        }
                        i5 += length;
                        i4 = i2;
                    } else {
                        i = i5 + 1;
                        bArr[i5] = 63;
                    }
                    i5 = i;
                    i4 = i6;
                } else {
                    int iA5 = a(b4);
                    int i14 = i4 + 2;
                    byte[] bArr4 = q8.e;
                    if (i14 < bArr4.length && 48 <= (b2 = bArr4[i14]) && b2 <= 55) {
                        iA5 = (iA5 * 8) + a(b2);
                        i6 = i14;
                    }
                    i4 = i6 + 1;
                    byte[] bArr5 = q8.e;
                    if (i4 >= bArr5.length || 48 > (b = bArr5[i4]) || b > 55) {
                        i4 = i6;
                    } else {
                        iA5 = (iA5 * 8) + a(b);
                    }
                    i3 = i5 + 1;
                    bArr[i5] = (byte) iA5;
                }
                i5 = i3;
            } else {
                bArr[i5] = b3;
                i5++;
            }
            i4++;
        }
    }

    public static boolean b(byte b) {
        if (48 <= b && b <= 57) {
            return true;
        }
        if (97 > b || b > 102) {
            return 65 <= b && b <= 70;
        }
        return true;
    }

    public static int a(byte b) {
        if (48 > b || b > 57) {
            return (97 > b || b > 122) ? b - 55 : b - 87;
        }
        return b - 48;
    }

    public static long a(String str, boolean z, boolean z2) {
        int i;
        int i2 = 0;
        if (str.startsWith("-", 0)) {
            if (!z) {
                throw new NumberFormatException("Number must be positive: ".concat(str));
            }
            i2 = 1;
        }
        int i3 = i2;
        if (str.startsWith("0x", i2)) {
            i2 += 2;
            i = 16;
        } else {
            i = str.startsWith("0", i2) ? 8 : 10;
        }
        String strSubstring = str.substring(i2);
        if (strSubstring.length() < 16) {
            long j = Long.parseLong(strSubstring, i);
            if (i3 != 0) {
                j = -j;
            }
            if (!z2) {
                if (z) {
                    if (j > 2147483647L || j < -2147483648L) {
                        throw new NumberFormatException("Number out of range for 32-bit signed integer: ".concat(str));
                    }
                } else if (j >= 4294967296L || j < 0) {
                    throw new NumberFormatException("Number out of range for 32-bit unsigned integer: ".concat(str));
                }
            }
            return j;
        }
        BigInteger bigInteger = new BigInteger(strSubstring, i);
        if (i3 != 0) {
            bigInteger = bigInteger.negate();
        }
        if (z2) {
            if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: ".concat(str));
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: ".concat(str));
            }
        } else if (z) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: ".concat(str));
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: ".concat(str));
        }
        return bigInteger.longValue();
    }
}
