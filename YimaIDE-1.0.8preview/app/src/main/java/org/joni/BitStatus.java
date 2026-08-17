package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
final class BitStatus {
    public static final int BIT_STATUS_BITS_NUM = 32;

    public static int bsAll() {
        return -1;
    }

    public static boolean bsAt(int i, int i2) {
        return (i2 < 32 ? i & (1 << i2) : i & 1) != 0;
    }

    public static int bsClear() {
        return 0;
    }

    public static int bsOnAt(int i, int i2) {
        return i2 < 32 ? i | (1 << i2) : i | 1;
    }

    public static int bsOnAtSimple(int i, int i2) {
        return i2 < 32 ? i | (1 << i2) : i;
    }

    public static int bsOnOff(int i, int i2, boolean z) {
        return z ? i & (~i2) : i | i2;
    }
}
