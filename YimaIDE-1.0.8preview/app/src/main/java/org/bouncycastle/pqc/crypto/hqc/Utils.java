package org.bouncycastle.pqc.crypto.hqc;

import io.github.rosemoe.sora.langs.textmate.folding.IndentRange;
import kotlin.UShort;
import kotlin.jvm.internal.LongCompanionObject;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class Utils {
    public static long bitMask(long j, long j2) {
        return (1 << ((int) (j % j2))) - 1;
    }

    public static void copyBytes(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        System.arraycopy(iArr, i, iArr2, i2, i3 / 2);
    }

    public static void fromByte16ArrayToLongArray(long[] jArr, int[] iArr) {
        for (int i = 0; i != iArr.length; i += 4) {
            int i2 = i / 4;
            long j = ((long) iArr[i]) & 65535;
            jArr[i2] = j;
            long j2 = j | (((long) iArr[i + 1]) << 16);
            jArr[i2] = j2;
            long j3 = j2 | (((long) iArr[i + 2]) << 32);
            jArr[i2] = j3;
            jArr[i2] = j3 | (((long) iArr[i + 3]) << 48);
        }
    }

    public static void fromByte32ArrayToLongArray(long[] jArr, int[] iArr) {
        for (int i = 0; i != iArr.length; i += 2) {
            int i2 = i / 2;
            long j = ((long) iArr[i]) & 4294967295L;
            jArr[i2] = j;
            jArr[i2] = j | (((long) iArr[i + 1]) << 32);
        }
    }

    public static void fromByteArrayToByte16Array(int[] iArr, byte[] bArr) {
        if (bArr.length % 2 != 0) {
            byte[] bArr2 = new byte[((bArr.length + 1) / 2) * 2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
        }
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = Pack.littleEndianToShort(bArr, i) & UShort.MAX_VALUE;
            i += 2;
        }
    }

    public static void fromByteArrayToLongArray(long[] jArr, byte[] bArr) {
        if (bArr.length % 8 != 0) {
            byte[] bArr2 = new byte[((bArr.length + 7) / 8) * 8];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
        }
        int i = 0;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = Pack.littleEndianToLong(bArr, i);
            i += 8;
        }
    }

    public static void fromLongArrayToByte32Array(int[] iArr, long[] jArr) {
        for (int i = 0; i != jArr.length; i++) {
            int i2 = i * 2;
            long j = jArr[i];
            iArr[i2] = (int) j;
            iArr[i2 + 1] = (int) (j >> 32);
        }
    }

    public static void fromLongArrayToByteArray(byte[] bArr, long[] jArr) {
        int length = bArr.length / 8;
        int i = 0;
        for (int i2 = 0; i2 != length; i2++) {
            Pack.longToLittleEndian(jArr[i2], bArr, i2 * 8);
        }
        if (bArr.length % 8 != 0) {
            int i3 = length * 8;
            while (i3 < bArr.length) {
                bArr[i3] = (byte) (jArr[length] >>> (i * 8));
                i3++;
                i++;
            }
        }
    }

    public static int getByte64SizeFromBitSize(int i) {
        return (i + 63) / 64;
    }

    public static int getByteSizeFromBitSize(int i) {
        return (i + 7) / 8;
    }

    public static void resizeArray(long[] jArr, int i, long[] jArr2, int i2, int i3, int i4) {
        if (i >= i2) {
            System.arraycopy(jArr2, 0, jArr, 0, (i2 + 7) / 8);
            return;
        }
        int i5 = i % 64;
        int i6 = i5 != 0 ? 64 - i5 : 0;
        System.arraycopy(jArr2, 0, jArr, 0, i3);
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i4 - 1;
            jArr[i8] = jArr[i8] & (LongCompanionObject.MAX_VALUE >> i7);
        }
    }

    public static int toUnsigned16Bits(int i) {
        return i & IndentRange.MAX_FOLDING_REGIONS;
    }

    public static int toUnsigned8bits(int i) {
        return i & 255;
    }

    public static void xorLongToByte16Array(int[] iArr, long j, int i) {
        iArr[i] = iArr[i] ^ (((int) j) & IndentRange.MAX_FOLDING_REGIONS);
        int i2 = i + 1;
        iArr[i2] = iArr[i2] ^ (((int) (j >>> 16)) & IndentRange.MAX_FOLDING_REGIONS);
        int i3 = i + 2;
        iArr[i3] = iArr[i3] ^ (((int) (j >>> 32)) & IndentRange.MAX_FOLDING_REGIONS);
        int i4 = i + 3;
        iArr[i4] = (((int) (j >>> 48)) & IndentRange.MAX_FOLDING_REGIONS) ^ iArr[i4];
    }
}
