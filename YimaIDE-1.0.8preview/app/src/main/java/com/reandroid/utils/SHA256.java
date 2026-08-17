package com.reandroid.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SHA256 extends ByteDigest {
    private static final byte[] PADDING;
    private static final int[] ROUND_CONSTS = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private int bufferOffset;
    private long bytesProcessed;
    private final int digestLength = 32;
    private final int blockSize = 64;
    private final int[] state = new int[8];
    private final int[] WORD = new int[64];
    private final byte[] buffer = new byte[64];

    static {
        byte[] bArr = new byte[136];
        PADDING = bArr;
        bArr[0] = -128;
    }

    public SHA256() {
        reset();
    }

    private int T2(int i, int i2, int i3) {
        return ((((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19))) ^ ((i >>> 22) | (i << 10))) + (((i & i3) ^ (i & i2)) ^ (i2 & i3));
    }

    private static void b2iBig64(byte[] bArr, int i, int[] iArr) {
        int i2 = 0;
        if ((i & 3) == 0) {
            while (i2 < 16) {
                iArr[i2] = ByteDigest.getBigEndianInteger(bArr, (i2 * 4) + i);
                i2++;
            }
        } else {
            int i3 = i + 64;
            while (i < i3) {
                iArr[i2] = ByteDigest.getBigEndianInteger(bArr, i);
                i += 4;
                i2++;
            }
        }
    }

    private void compress(byte[] bArr, int i) {
        compressCheck(bArr, i);
        compressWord();
    }

    private void compressCheck(byte[] bArr, int i) {
        b2iBig64(bArr, i, this.WORD);
    }

    private int compressMultiBlock(byte[] bArr, int i, int i2) {
        while (i <= i2) {
            compress(bArr, i);
            i += this.blockSize;
        }
        return i;
    }

    private void compressWord() {
        int[] iArr = this.WORD;
        int[] iArr2 = this.state;
        for (int i = 16; i < 64; i++) {
            int i2 = iArr[i - 2];
            int i3 = iArr[i - 15];
            iArr[i] = ((i3 >>> 3) ^ (((i3 >>> 7) | (i3 << 25)) ^ ((i3 >>> 18) | (i3 << 14)))) + ((i2 >>> 10) ^ (((i2 >>> 17) | (i2 << 15)) ^ ((i2 >>> 19) | (i2 << 13)))) + iArr[i - 7] + iArr[i - 16];
        }
        int i4 = iArr2[0];
        int i5 = iArr2[1];
        int i6 = iArr2[2];
        int i7 = iArr2[3];
        int i8 = iArr2[4];
        int i9 = iArr2[5];
        int i10 = iArr2[6];
        int i11 = iArr2[7];
        int i12 = 0;
        int i13 = i10;
        while (i12 < 64) {
            int iSigmaCh = i11 + sigmaCh(i8, i9, i13) + ROUND_CONSTS[i12] + iArr[i12];
            int i14 = i7 + iSigmaCh;
            int iT2 = iSigmaCh + T2(i4, i5, i6);
            i12++;
            i11 = i13;
            i13 = i9;
            i9 = i8;
            i8 = i14;
            i7 = i6;
            i6 = i5;
            i5 = i4;
            i4 = iT2;
        }
        iArr2[0] = iArr2[0] + i4;
        iArr2[1] = iArr2[1] + i5;
        iArr2[2] = iArr2[2] + i6;
        iArr2[3] = iArr2[3] + i7;
        iArr2[4] = iArr2[4] + i8;
        iArr2[5] = iArr2[5] + i9;
        iArr2[6] = iArr2[6] + i13;
        iArr2[7] = iArr2[7] + i11;
    }

    private static void i2bBig(int[] iArr, byte[] bArr, int i, int i2) {
        int i3 = 0;
        if ((i & 3) == 0) {
            int i4 = i2 + i;
            while (i < i4) {
                ByteDigest.putBigEndianInteger(bArr, i, iArr[i3]);
                i += 4;
                i3++;
            }
            return;
        }
        int i5 = i2 + i;
        while (i < i5) {
            int i6 = i3 + 1;
            int i7 = iArr[i3];
            bArr[i] = (byte) (i7 >> 24);
            bArr[i + 1] = (byte) (i7 >> 16);
            int i8 = i + 3;
            bArr[i + 2] = (byte) (i7 >> 8);
            i += 4;
            bArr[i8] = (byte) i7;
            i3 = i6;
        }
    }

    private void restart() {
        if (this.bytesProcessed != 0) {
            reset();
            this.bufferOffset = 0;
            this.bytesProcessed = 0L;
            ByteDigest.fillZero(this.buffer);
        }
    }

    private int sigmaCh(int i, int i2, int i3) {
        return ((((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21))) ^ ((i >>> 25) | (i << 7))) + (((~i) & i3) ^ (i2 & i));
    }

    @Override // com.reandroid.utils.ByteDigest
    public void digest(byte[] bArr, int i) {
        if (this.bytesProcessed < 0) {
            restart();
        }
        long j = this.bytesProcessed;
        long j2 = j << 3;
        int i2 = ((int) j) & 63;
        update(PADDING, 0, i2 < 56 ? 56 - i2 : 120 - i2);
        ByteDigest.putBigEndianInteger(this.buffer, 56, (int) (j2 >>> 32));
        ByteDigest.putBigEndianInteger(this.buffer, 60, (int) j2);
        compress(this.buffer, 0);
        i2bBig(this.state, bArr, i, getDigestLength());
        this.bytesProcessed = -1L;
    }

    @Override // com.reandroid.utils.ByteDigest
    public int getDigestLength() {
        return this.digestLength;
    }

    @Override // com.reandroid.utils.ByteDigest
    public void reset() {
        int[] iArr = this.state;
        iArr[0] = 1779033703;
        iArr[1] = -1150833019;
        iArr[2] = 1013904242;
        iArr[3] = -1521486534;
        iArr[4] = 1359893119;
        iArr[5] = -1694144372;
        iArr[6] = 528734635;
        iArr[7] = 1541459225;
        ByteDigest.fillZero(this.WORD);
    }

    @Override // com.reandroid.utils.ByteDigest
    public void update(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            if (this.bytesProcessed < 0) {
                restart();
            }
            this.bytesProcessed += (long) i2;
            int i3 = this.bufferOffset;
            if (i3 != 0) {
                int iMin = Math.min(i2, this.blockSize - i3);
                System.arraycopy(bArr, i, this.buffer, this.bufferOffset, iMin);
                int i4 = this.bufferOffset + iMin;
                this.bufferOffset = i4;
                i += iMin;
                i2 -= iMin;
                if (i4 >= this.blockSize) {
                    compress(this.buffer, 0);
                    this.bufferOffset = 0;
                }
            }
            int i5 = this.blockSize;
            if (i2 >= i5) {
                int i6 = i2 + i;
                i = compressMultiBlock(bArr, i, i6 - i5);
                i2 = i6 - i;
            }
            if (i2 > 0) {
                System.arraycopy(bArr, i, this.buffer, 0, i2);
                this.bufferOffset = i2;
            }
        }
    }
}
