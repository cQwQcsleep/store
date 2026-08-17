package org.bouncycastle.pqc.crypto.falcon;

import kotlin.UByte;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class FalconRNG {
    byte[] bd = new byte[512];
    long bdummy_u64 = 0;
    int ptr = 0;
    byte[] sd = new byte[256];
    long sdummy_u64 = 0;
    int type = 0;
    FalconConversions convertor = new FalconConversions();

    private void QROUND(int i, int i2, int i3, int i4, int[] iArr) {
        int i5 = iArr[i] + iArr[i2];
        iArr[i] = i5;
        int i6 = i5 ^ iArr[i4];
        iArr[i4] = i6;
        int i7 = (i6 >>> 16) | (i6 << 16);
        iArr[i4] = i7;
        int i8 = iArr[i3] + i7;
        iArr[i3] = i8;
        int i9 = iArr[i2] ^ i8;
        iArr[i2] = i9;
        int i10 = (i9 >>> 20) | (i9 << 12);
        iArr[i2] = i10;
        int i11 = iArr[i] + i10;
        iArr[i] = i11;
        int i12 = iArr[i4] ^ i11;
        iArr[i4] = i12;
        int i13 = (i12 >>> 24) | (i12 << 8);
        iArr[i4] = i13;
        int i14 = iArr[i3] + i13;
        iArr[i3] = i14;
        int i15 = iArr[i2] ^ i14;
        iArr[i2] = i15;
        iArr[i2] = (i15 >>> 25) | (i15 << 7);
    }

    public void prng_get_bytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            byte[] bArr2 = this.bd;
            int length = bArr2.length - this.ptr;
            if (length > i2) {
                length = i2;
            }
            System.arraycopy(bArr2, 0, bArr, i, length);
            i += length;
            i2 -= length;
            int i3 = this.ptr + length;
            this.ptr = i3;
            if (i3 == this.bd.length) {
                prng_refill();
            }
        }
    }

    public long prng_get_u64() {
        int i = this.ptr;
        if (i >= this.bd.length - 9) {
            prng_refill();
            i = 0;
        }
        this.ptr = i + 8;
        byte[] bArr = this.bd;
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((255 & ((long) bArr[i + 7])) << 56);
    }

    public byte prng_get_u8() {
        byte[] bArr = this.bd;
        int i = this.ptr;
        int i2 = i + 1;
        this.ptr = i2;
        byte b = bArr[i];
        if (i2 == bArr.length) {
            prng_refill();
        }
        return b;
    }

    public void prng_init(SHAKE256 shake256) {
        byte[] bArr = new byte[56];
        shake256.inner_shake256_extract(bArr, 0, 56);
        for (int i = 0; i < 14; i++) {
            int i2 = i << 2;
            System.arraycopy(this.convertor.int_to_bytes(((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i2] & UByte.MAX_VALUE) | ((bArr[i2 + 2] & UByte.MAX_VALUE) << 16) | ((bArr[i2 + 3] & UByte.MAX_VALUE) << 24)), 0, this.sd, i2, 4);
        }
        System.arraycopy(this.convertor.long_to_bytes((((long) this.convertor.bytes_to_int(this.sd, 48)) & 4294967295L) + ((4294967295L & ((long) this.convertor.bytes_to_int(this.sd, 52))) << 32)), 0, this.sd, 48, 8);
        prng_refill();
    }

    public void prng_refill() {
        FalconRNG falconRNG = this;
        int[] iArr = {1634760805, 857760878, 2036477234, 1797285236};
        long jBytes_to_long = falconRNG.convertor.bytes_to_long(falconRNG.sd, 48);
        for (int i = 0; i < 8; i++) {
            int[] iArr2 = new int[16];
            System.arraycopy(iArr, 0, iArr2, 0, 4);
            System.arraycopy(falconRNG.convertor.bytes_to_int_array(falconRNG.sd, 0, 12), 0, iArr2, 4, 12);
            int i2 = (int) jBytes_to_long;
            iArr2[14] = iArr2[14] ^ i2;
            int i3 = (int) (jBytes_to_long >>> 32);
            iArr2[15] = iArr2[15] ^ i3;
            int i4 = 0;
            while (i4 < 10) {
                falconRNG.QROUND(0, 4, 8, 12, iArr2);
                falconRNG = this;
                falconRNG.QROUND(1, 5, 9, 13, iArr2);
                falconRNG.QROUND(2, 6, 10, 14, iArr2);
                falconRNG.QROUND(3, 7, 11, 15, iArr2);
                falconRNG.QROUND(0, 5, 10, 15, iArr2);
                falconRNG.QROUND(1, 6, 11, 12, iArr2);
                falconRNG.QROUND(2, 7, 8, 13, iArr2);
                falconRNG.QROUND(3, 4, 9, 14, iArr2);
                i4++;
                i3 = i3;
            }
            int i5 = i3;
            for (int i6 = 0; i6 < 4; i6++) {
                iArr2[i6] = iArr2[i6] + iArr[i6];
            }
            for (int i7 = 4; i7 < 14; i7++) {
                iArr2[i7] = iArr2[i7] + falconRNG.convertor.bytes_to_int(falconRNG.sd, (i7 * 4) - 16);
            }
            iArr2[14] = iArr2[14] + (falconRNG.convertor.bytes_to_int(falconRNG.sd, 40) ^ i2);
            iArr2[15] = iArr2[15] + (falconRNG.convertor.bytes_to_int(falconRNG.sd, 44) ^ i5);
            jBytes_to_long++;
            for (int i8 = 0; i8 < 16; i8++) {
                byte[] bArr = falconRNG.bd;
                int i9 = (i << 2) + (i8 << 5);
                int i10 = iArr2[i8];
                bArr[i9] = (byte) i10;
                bArr[i9 + 1] = (byte) (i10 >>> 8);
                bArr[i9 + 2] = (byte) (i10 >>> 16);
                bArr[i9 + 3] = (byte) (i10 >>> 24);
            }
        }
        System.arraycopy(falconRNG.convertor.long_to_bytes(jBytes_to_long), 0, falconRNG.sd, 48, 8);
        falconRNG.ptr = 0;
    }
}
