package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class ChaCha20Util {
    static final int BLOCK_SIZE_IN_BYTES = 64;
    static final int BLOCK_SIZE_IN_INTS = 16;
    static final int KEY_SIZE_IN_BYTES = 32;
    static final int KEY_SIZE_IN_INTS = 8;
    private static final int[] SIGMA = toIntArray(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107});

    private ChaCha20Util() {
    }

    public static void quarterRound(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = iArr[i] + iArr[i2];
        iArr[i] = i5;
        int iRotateLeft = rotateLeft(i5 ^ iArr[i4], 16);
        iArr[i4] = iRotateLeft;
        int i6 = iArr[i3] + iRotateLeft;
        iArr[i3] = i6;
        int iRotateLeft2 = rotateLeft(iArr[i2] ^ i6, 12);
        iArr[i2] = iRotateLeft2;
        int i7 = iArr[i] + iRotateLeft2;
        iArr[i] = i7;
        int iRotateLeft3 = rotateLeft(iArr[i4] ^ i7, 8);
        iArr[i4] = iRotateLeft3;
        int i8 = iArr[i3] + iRotateLeft3;
        iArr[i3] = i8;
        iArr[i2] = rotateLeft(iArr[i2] ^ i8, 7);
    }

    private static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public static void setSigmaAndKey(int[] iArr, int[] iArr2) {
        int[] iArr3 = SIGMA;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, iArr3.length, 8);
    }

    public static void shuffleState(int[] iArr) {
        for (int i = 0; i < 10; i++) {
            quarterRound(iArr, 0, 4, 8, 12);
            quarterRound(iArr, 1, 5, 9, 13);
            quarterRound(iArr, 2, 6, 10, 14);
            quarterRound(iArr, 3, 7, 11, 15);
            quarterRound(iArr, 0, 5, 10, 15);
            quarterRound(iArr, 1, 6, 11, 12);
            quarterRound(iArr, 2, 7, 8, 13);
            quarterRound(iArr, 3, 4, 9, 14);
        }
    }

    public static int[] toIntArray(byte[] bArr) {
        IntBuffer intBufferAsIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[intBufferAsIntBuffer.remaining()];
        intBufferAsIntBuffer.get(iArr);
        return iArr;
    }
}
