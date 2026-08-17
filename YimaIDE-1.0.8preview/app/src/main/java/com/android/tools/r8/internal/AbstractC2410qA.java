package com.android.tools.r8.internal;

import defpackage.pv9;
import java.math.RoundingMode;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2410qA {
    public static final int[] a = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        if (((r8 == java.math.RoundingMode.HALF_EVEN) & ((r1 & 1) != 0)) != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0043, code lost:
    
        if (r2 > 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0046, code lost:
    
        if (r6 > 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0049, code lost:
    
        if (r6 < 0) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int a(int i, int i2, RoundingMode roundingMode) {
        roundingMode.getClass();
        if (i2 == 0) {
            pv9.a("/ by zero");
            return 0;
        }
        int i3 = i / i2;
        int i4 = i - (i2 * i3);
        if (i4 != 0) {
            int i5 = ((i ^ i2) >> 31) | 1;
            switch (AbstractC2324pA.a[roundingMode.ordinal()]) {
                case 1:
                    if (i4 != 0) {
                        pv9.a("mode was UNNECESSARY, but rounding was necessary");
                        return 0;
                    }
                case 2:
                    return i3;
                case XmlPullParser.END_TAG /* 3 */:
                    break;
                case 4:
                    return i3 + i5;
                case XmlPullParser.CDSECT /* 5 */:
                    break;
                case XmlPullParser.ENTITY_REF /* 6 */:
                case 7:
                case 8:
                    int iAbs = Math.abs(i4);
                    int iAbs2 = iAbs - (Math.abs(i2) - iAbs);
                    if (iAbs2 == 0) {
                        if (roundingMode != RoundingMode.HALF_UP) {
                            break;
                        }
                        return i3 + i5;
                    }
                    break;
                default:
                    x1f.a();
                    return 0;
            }
        }
        return i3;
    }

    public static int a(int i, RoundingMode roundingMode) {
        if (i > 0) {
            switch (AbstractC2324pA.a[roundingMode.ordinal()]) {
                case 1:
                    if (!((i > 0) & (((i + (-1)) & i) == 0))) {
                        pv9.a("mode was UNNECESSARY, but rounding was necessary");
                        return 0;
                    }
                    break;
                case 2:
                case XmlPullParser.END_TAG /* 3 */:
                    break;
                case 4:
                case XmlPullParser.CDSECT /* 5 */:
                    return 32 - Integer.numberOfLeadingZeros(i - 1);
                case XmlPullParser.ENTITY_REF /* 6 */:
                case 7:
                case 8:
                    int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                    return ((~(~(((-1257966797) >>> iNumberOfLeadingZeros) - i))) >>> 31) + (31 - iNumberOfLeadingZeros);
                default:
                    x1f.a();
                    return 0;
            }
            return 31 - Integer.numberOfLeadingZeros(i);
        }
        w01.a(AbstractC1784iv.a(i, "x (", ") must be > 0"));
        return 0;
    }
}
