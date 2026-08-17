package org.eclipse.jdt.internal.compiler.util;

import org.eclipse.jdt.internal.compiler.lookup.TagBits;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class FloatUtil {
    private static final int DOUBLE_EXPONENT_BIAS = 1023;
    private static final int DOUBLE_EXPONENT_SHIFT = 52;
    private static final int DOUBLE_FRACTION_WIDTH = 52;
    private static final int DOUBLE_PRECISION = 53;
    private static final int MAX_DOUBLE_EXPONENT = 1023;
    private static final int MAX_SINGLE_EXPONENT = 127;
    private static final int MIN_NORMALIZED_DOUBLE_EXPONENT = -1022;
    private static final int MIN_NORMALIZED_SINGLE_EXPONENT = -126;
    private static final int MIN_UNNORMALIZED_DOUBLE_EXPONENT = -1075;
    private static final int MIN_UNNORMALIZED_SINGLE_EXPONENT = -150;
    private static final int SINGLE_EXPONENT_BIAS = 127;
    private static final int SINGLE_EXPONENT_SHIFT = 23;
    private static final int SINGLE_FRACTION_WIDTH = 23;
    private static final int SINGLE_PRECISION = 24;

    /* JADX WARN: Code duplicated, block: B:52:0x0082  */
    private static long convertHexFloatingPointLiteralToBits(char[] cArr) {
        int i;
        int i2;
        int i3;
        int i4;
        long j;
        int i5;
        long j2;
        int i6;
        char c;
        int length = cArr.length;
        int i7 = 0;
        if (cArr[0] != '0') {
            throw new NumberFormatException();
        }
        int i8 = 1;
        char c2 = cArr[1];
        if (c2 != 'X' && c2 != 'x') {
            throw new NumberFormatException();
        }
        int i9 = 2;
        int i10 = -1;
        while (true) {
            char c3 = cArr[i9];
            if (c3 == '.') {
                i10 = i9;
                i9++;
            } else {
                if (c3 != '0') {
                    break;
                }
                i9++;
            }
            i7 = 0;
        }
        int i11 = i7;
        int i12 = -1;
        long j3 = 0;
        while (true) {
            char c4 = cArr[i9];
            if (c4 != '.') {
                switch (c4) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        i = c4 - '0';
                        break;
                    default:
                        switch (c4) {
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                i = c4 - '7';
                                break;
                            default:
                                switch (c4) {
                                    case TerminalTokens.TokenNameDIVIDE_EQUAL /* 97 */:
                                    case TerminalTokens.TokenNameAND_EQUAL /* 98 */:
                                    case 'c':
                                    case 'd':
                                    case TerminalTokens.TokenNameREMAINDER_EQUAL /* 101 */:
                                    case TerminalTokens.TokenNameLEFT_SHIFT_EQUAL /* 102 */:
                                        i = c4 - 'W';
                                        break;
                                    default:
                                        if (i10 < 0) {
                                            i10 = i9;
                                        }
                                        if (c4 != 'P' && c4 != 'p') {
                                            throw new NumberFormatException();
                                        }
                                        int i13 = i9 + i8;
                                        int i14 = i7;
                                        int i15 = i8;
                                        while (true) {
                                            if (i13 >= length) {
                                                i2 = i8;
                                            } else {
                                                char c5 = cArr[i13];
                                                i2 = i8;
                                                if (c5 != '+') {
                                                    if (c5 != '-') {
                                                        switch (c5) {
                                                            case '0':
                                                            case '1':
                                                            case '2':
                                                            case '3':
                                                            case '4':
                                                            case '5':
                                                            case '6':
                                                            case '7':
                                                            case '8':
                                                            case '9':
                                                                i14 = (i14 * 10) + (c5 - '0');
                                                                i13++;
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    } else {
                                                        i13++;
                                                        i15 = -1;
                                                    }
                                                    i8 = i2;
                                                } else {
                                                    i13++;
                                                    i8 = i2;
                                                    i15 = i8;
                                                }
                                            }
                                        }
                                        if (i13 >= length || (c = cArr[i13]) == 'D') {
                                            i3 = i2;
                                        } else {
                                            if (c != 'F') {
                                                if (c == 'd') {
                                                    i3 = i2;
                                                } else if (c != 'f') {
                                                    throw new NumberFormatException();
                                                }
                                            }
                                            i3 = 0;
                                        }
                                        if (j3 == 0) {
                                            return 0L;
                                        }
                                        long j4 = j3 >>> (i11 - 4);
                                        if ((8 & j4) == 0) {
                                            int i16 = i11 - 1;
                                            if ((4 & j4) == 0) {
                                                int i17 = i11 - 2;
                                                if ((j4 & 2) == 0) {
                                                    i11 -= 3;
                                                    i4 = 3;
                                                } else {
                                                    i11 = i17;
                                                    i4 = 2;
                                                }
                                            } else {
                                                i11 = i16;
                                                i4 = i2;
                                            }
                                        } else {
                                            i4 = 0;
                                        }
                                        if (i3 != 0) {
                                            if (i11 > 53) {
                                                long j5 = j3 >>> (i11 - 54);
                                                long j6 = j5 + (1 & j5);
                                                j2 = j6 >>> i2;
                                                if ((9007199254740992L & j2) != 0) {
                                                    j2 = j6 >>> 2;
                                                    i4--;
                                                }
                                            } else {
                                                j2 = j3 << (53 - i11);
                                            }
                                            if (i11 > 0) {
                                                i6 = i12 < i10 ? ((i10 - i12) * 4) - i4 : (((i12 - i10) - 1) * (-4)) - i4;
                                            } else {
                                                i6 = 0;
                                            }
                                            int i18 = (i15 * i14) + i6;
                                            int i19 = i18 - 1;
                                            if (i19 > 1023) {
                                                return Double.doubleToLongBits(Double.POSITIVE_INFINITY);
                                            }
                                            if (i19 >= MIN_NORMALIZED_DOUBLE_EXPONENT) {
                                                return (((long) (i18 - MIN_NORMALIZED_DOUBLE_EXPONENT)) << 52) | (j2 & (-4503599627370497L));
                                            }
                                            return i19 > MIN_UNNORMALIZED_DOUBLE_EXPONENT ? j2 >>> ((-1021) - i18) : Double.doubleToLongBits(Double.NaN);
                                        }
                                        if (i11 > 24) {
                                            long j7 = j3 >>> (i11 - 25);
                                            long j8 = j7 + (1 & j7);
                                            j = j8 >>> i2;
                                            if ((TagBits.HasUnresolvedTypeVariables & j) != 0) {
                                                j = j8 >>> 2;
                                                i4--;
                                            }
                                        } else {
                                            j = j3 << (24 - i11);
                                        }
                                        if (i11 > 0) {
                                            i5 = i12 < i10 ? ((i10 - i12) * 4) - i4 : (((i12 - i10) - 1) * (-4)) - i4;
                                        } else {
                                            i5 = 0;
                                        }
                                        int i20 = (i15 * i14) + i5;
                                        int i21 = i20 - 1;
                                        if (i21 > 127) {
                                            return Float.floatToIntBits(Float.POSITIVE_INFINITY);
                                        }
                                        if (i21 >= MIN_NORMALIZED_SINGLE_EXPONENT) {
                                            return (((long) (i20 - MIN_NORMALIZED_SINGLE_EXPONENT)) << 23) | (j & (-8388609));
                                        }
                                        return i21 > MIN_UNNORMALIZED_SINGLE_EXPONENT ? j >>> ((-125) - i20) : Float.floatToIntBits(Float.NaN);
                                }
                                break;
                        }
                        break;
                }
                if (i11 == 0) {
                    j3 = i;
                    i12 = i9;
                    i11 = 4;
                } else if (i11 < 60) {
                    i11 += 4;
                    j3 = (j3 << 4) | ((long) i);
                }
                i9++;
                i8 = i8;
            } else {
                i10 = i9;
                i9++;
            }
            i7 = 0;
        }
    }

    public static double valueOfHexDoubleLiteral(char[] cArr) {
        return Double.longBitsToDouble(convertHexFloatingPointLiteralToBits(cArr));
    }

    public static float valueOfHexFloatLiteral(char[] cArr) {
        return Float.intBitsToFloat((int) convertHexFloatingPointLiteralToBits(cArr));
    }
}
