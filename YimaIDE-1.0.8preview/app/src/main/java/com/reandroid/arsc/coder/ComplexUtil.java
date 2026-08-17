package com.reandroid.arsc.coder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ComplexUtil {
    private static final int COMPLEX_MANTISSA_MASK = 16777215;
    private static final int COMPLEX_MANTISSA_SHIFT = 8;
    private static final int COMPLEX_RADIX_MASK = 3;
    private static final int COMPLEX_RADIX_SHIFT = 4;
    private static final int COMPLEX_UNIT_MASK = 15;
    private static final int COMPLEX_UNIT_SHIFT = 0;
    private static final float MANTISSA_MULT = 0.00390625f;

    public enum Radix {
        RADIX_23p0(0, 23, ComplexUtil.MANTISSA_MULT),
        RADIX_16p7(1, 16, 3.0517578E-5f),
        RADIX_8p15(2, 8, 1.1920929E-7f),
        RADIX_0p23(3, 0, 4.656613E-10f);

        private final int flag;
        private final float multiplier;
        private final int shift;

        Radix(int i, int i2, float f) {
            this.flag = i;
            this.shift = i2;
            this.multiplier = f;
        }

        public static Radix forFlag(int i) {
            if (i == 0) {
                return RADIX_23p0;
            }
            if (i == 1) {
                return RADIX_16p7;
            }
            if (i == 2) {
                return RADIX_8p15;
            }
            if (i == 3) {
                return RADIX_0p23;
            }
            bia.a("Unknown radix flag = ", i);
            return null;
        }

        public static Radix getRadix(long j) {
            if ((8388607 & j) == 0) {
                return RADIX_23p0;
            }
            if (((-8388608) & j) == 0) {
                return RADIX_0p23;
            }
            if (((-2147483648L) & j) == 0) {
                return RADIX_8p15;
            }
            if (((-549755813888L) & j) == 0) {
                return RADIX_16p7;
            }
            throw new NumberFormatException("Radix bits out of range bits = " + j);
        }

        public String formatFloat(boolean z, float f) {
            int i = 1;
            boolean z2 = f < 0.0f;
            if (z2) {
                f = -f;
            }
            for (int i2 = 0; i2 < this.flag * 2; i2++) {
                i *= 10;
            }
            float f2 = i;
            float f3 = f * f2;
            int i3 = (int) f3;
            if (f3 - i3 >= 0.5f) {
                i3++;
            }
            float f4 = i3 / f2;
            if (z2) {
                f4 = -f4;
            }
            if (z) {
                f4 *= 100.0f;
            }
            return Float.toString(f4);
        }

        public int getFlag() {
            return this.flag;
        }

        public float getMultiplier() {
            return this.multiplier;
        }

        public int getShift() {
            return this.shift;
        }
    }

    public static String decodeComplex(boolean z, int i) {
        Radix radixForFlag = Radix.forFlag((i >> 4) & 3);
        float multiplier = (i & (-256)) * radixForFlag.getMultiplier();
        int i2 = i & COMPLEX_UNIT_MASK;
        return radixForFlag.formatFloat(z, multiplier) + (z ? UnitFraction.valueOf(i2) : UnitDimension.valueOf(i2));
    }

    public static int encodeComplex(float f, ComplexUnit complexUnit) {
        boolean z = f < 0.0f;
        if (z) {
            f = -f;
        }
        if (complexUnit.isFraction()) {
            f /= 100.0f;
        }
        long j = (long) ((f * 8388608.0f) + 0.5f);
        Radix radix = Radix.getRadix(j);
        int shift = (int) ((j >> radix.getShift()) & 16777215);
        if (z) {
            shift = COMPLEX_MANTISSA_MASK & (-shift);
        }
        return (radix.getFlag() << 4) | (shift << 8) | complexUnit.getFlag();
    }
}
