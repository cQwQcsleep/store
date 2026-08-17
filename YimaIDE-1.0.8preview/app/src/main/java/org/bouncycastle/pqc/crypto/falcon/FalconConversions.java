package org.bouncycastle.pqc.crypto.falcon;

import kotlin.UByte;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class FalconConversions {
    private int toUnsignedInt(byte b) {
        return b & UByte.MAX_VALUE;
    }

    private long toUnsignedLong(byte b) {
        return ((long) b) & 255;
    }

    public int bytes_to_int(byte[] bArr, int i) {
        return (toUnsignedInt(bArr[i + 3]) << 24) | toUnsignedInt(bArr[i]) | (toUnsignedInt(bArr[i + 1]) << 8) | (toUnsignedInt(bArr[i + 2]) << 16);
    }

    public int[] bytes_to_int_array(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = bytes_to_int(bArr, (i3 * 4) + i);
        }
        return iArr;
    }

    public long bytes_to_long(byte[] bArr, int i) {
        return (toUnsignedLong(bArr[i + 7]) << 56) | toUnsignedLong(bArr[i]) | (toUnsignedLong(bArr[i + 1]) << 8) | (toUnsignedLong(bArr[i + 2]) << 16) | (toUnsignedLong(bArr[i + 3]) << 24) | (toUnsignedLong(bArr[i + 4]) << 32) | (toUnsignedLong(bArr[i + 5]) << 40) | (toUnsignedLong(bArr[i + 6]) << 48);
    }

    public byte[] int_to_bytes(int i) {
        return new byte[]{(byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    public byte[] long_to_bytes(long j) {
        return new byte[]{(byte) j, (byte) (j >>> 8), (byte) (j >>> 16), (byte) (j >>> 24), (byte) (j >>> 32), (byte) (j >>> 40), (byte) (j >>> 48), (byte) (j >>> 56)};
    }
}
