package org.jcodings.transcode;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class TranscodeTableSupport implements TranscodingInstruction {
    public static final int WORDINDEX_SHIFT_BITS = 2;

    public static int INFO2WORDINDEX(int i) {
        return i >>> 2;
    }

    public static int WORDINDEX2INFO(int i) {
        return i << 2;
    }

    public static int funsio(int i) {
        return (i << 8) & 19;
    }

    public static int g4(int i, int i2, int i3, int i4) {
        return (i << 8) | (i3 << 16) | ((i2 & 15) << 24) | ((i4 & 15) << 28) | 18;
    }

    public static int getBT0(int i) {
        return ((i >>> 5) & 7) | EConvFlags.UNDEF_MASK;
    }

    public static int getBT1(int i) {
        return i >>> 8;
    }

    public static int getBT2(int i) {
        return i >>> 16;
    }

    public static int getBT3(int i) {
        return i >>> 24;
    }

    public static int makeSTR1(int i) {
        return (i << 6) | 17;
    }

    public static byte makeSTR1LEN(int i) {
        return (byte) (i - 4);
    }

    public static int o1(int i) {
        return (i << 8) | 2;
    }

    public static int o2(int i, int i2) {
        return (i << 8) | (i2 << 16) | 3;
    }

    public static int o2FUNii(int i, int i2) {
        return (i << 8) | (i2 << 16) | 11;
    }

    public static int o3(int i, int i2, int i3) {
        return (i << 8) | (i2 << 16) | (i3 << 24) | 5;
    }

    public static int o4(int i, int i2, int i3, int i4) {
        return ((i & 7) << 5) | (i2 << 8) | (i3 << 16) | (i4 << 24) | 6;
    }
}
