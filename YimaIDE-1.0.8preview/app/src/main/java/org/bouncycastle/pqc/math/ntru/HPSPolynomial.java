package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class HPSPolynomial extends Polynomial {
    public HPSPolynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        short[] sArr = this.coeffs;
        System.arraycopy(polynomial.coeffs, 0, sArr, 0, sArr.length);
        z3ToZq();
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            short[] sArr = this.coeffs;
            int i2 = i * 8;
            int i3 = i * 11;
            int i4 = bArr[i3] & UByte.MAX_VALUE;
            byte b = bArr[i3 + 1];
            sArr[i2] = (short) (i4 | ((((short) (b & UByte.MAX_VALUE)) & 7) << 8));
            int i5 = (b & UByte.MAX_VALUE) >>> 3;
            byte b2 = bArr[i3 + 2];
            sArr[i2 + 1] = (short) (i5 | ((((short) (b2 & UByte.MAX_VALUE)) & 63) << 5));
            int i6 = ((b2 & UByte.MAX_VALUE) >>> 6) | ((((short) (bArr[i3 + 3] & UByte.MAX_VALUE)) & 255) << 2);
            byte b3 = bArr[i3 + 4];
            sArr[i2 + 2] = (short) (i6 | ((((short) (b3 & UByte.MAX_VALUE)) & 1) << 10));
            int i7 = (b3 & UByte.MAX_VALUE) >>> 1;
            byte b4 = bArr[i3 + 5];
            sArr[i2 + 3] = (short) (i7 | ((((short) (b4 & UByte.MAX_VALUE)) & 15) << 7));
            int i8 = (b4 & UByte.MAX_VALUE) >>> 4;
            byte b5 = bArr[i3 + 6];
            sArr[i2 + 4] = (short) (((((short) (b5 & UByte.MAX_VALUE)) & 127) << 4) | i8);
            int i9 = ((b5 & UByte.MAX_VALUE) >>> 7) | ((((short) (bArr[i3 + 7] & UByte.MAX_VALUE)) & 255) << 1);
            byte b6 = bArr[i3 + 8];
            sArr[i2 + 5] = (short) (i9 | ((((short) (b6 & UByte.MAX_VALUE)) & 3) << 9));
            int i10 = (b6 & UByte.MAX_VALUE) >>> 2;
            byte b7 = bArr[i3 + 9];
            sArr[i2 + 6] = (short) (i10 | ((((short) (b7 & UByte.MAX_VALUE)) & 31) << 6));
            sArr[i2 + 7] = (short) (((b7 & UByte.MAX_VALUE) >>> 5) | ((((short) (bArr[i3 + 10] & UByte.MAX_VALUE)) & 255) << 3));
            i++;
        }
        int iPackDegree = this.params.packDegree() & 7;
        if (iPackDegree == 2) {
            short[] sArr2 = this.coeffs;
            int i11 = i * 8;
            int i12 = i * 11;
            int i13 = bArr[i12] & UByte.MAX_VALUE;
            byte b8 = bArr[i12 + 1];
            sArr2[i11] = (short) (i13 | ((((short) (b8 & UByte.MAX_VALUE)) & 7) << 8));
            sArr2[i11 + 1] = (short) (((((short) (bArr[i12 + 2] & UByte.MAX_VALUE)) & 63) << 5) | ((b8 & UByte.MAX_VALUE) >>> 3));
        } else if (iPackDegree == 4) {
            short[] sArr3 = this.coeffs;
            int i14 = i * 8;
            int i15 = i * 11;
            int i16 = bArr[i15] & UByte.MAX_VALUE;
            byte b9 = bArr[i15 + 1];
            sArr3[i14] = (short) (i16 | ((((short) (b9 & UByte.MAX_VALUE)) & 7) << 8));
            int i17 = (b9 & UByte.MAX_VALUE) >>> 3;
            byte b10 = bArr[i15 + 2];
            sArr3[i14 + 1] = (short) (i17 | ((((short) (b10 & UByte.MAX_VALUE)) & 63) << 5));
            int i18 = ((((short) (bArr[i15 + 3] & UByte.MAX_VALUE)) & 255) << 2) | ((b10 & UByte.MAX_VALUE) >>> 6);
            byte b11 = bArr[i15 + 4];
            sArr3[i14 + 2] = (short) (i18 | ((((short) (b11 & UByte.MAX_VALUE)) & 1) << 10));
            sArr3[i14 + 3] = (short) (((((short) (bArr[i15 + 5] & UByte.MAX_VALUE)) & 15) << 7) | ((b11 & UByte.MAX_VALUE) >>> 1));
        }
        this.coeffs[length - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        short[] sArr = new short[8];
        int i2 = 0;
        while (i2 < this.params.packDegree() / 8) {
            for (int i3 = 0; i3 < 8; i3++) {
                sArr[i3] = (short) Polynomial.modQ(this.coeffs[(i2 * 8) + i3] & UShort.MAX_VALUE, this.params.q());
            }
            int i4 = i2 * 11;
            short s = sArr[0];
            bArr[i4] = (byte) (s & 255);
            short s2 = sArr[1];
            bArr[i4 + 1] = (byte) ((s >>> 8) | ((s2 & 31) << 3));
            int i5 = s2 >>> 5;
            short s3 = sArr[2];
            bArr[i4 + 2] = (byte) (i5 | ((s3 & 3) << 6));
            bArr[i4 + 3] = (byte) ((s3 >>> 2) & 255);
            int i6 = s3 >>> 10;
            short s4 = sArr[3];
            bArr[i4 + 4] = (byte) (i6 | ((s4 & 127) << 1));
            short s5 = sArr[4];
            bArr[i4 + 5] = (byte) ((s4 >>> 7) | ((s5 & 15) << 4));
            short s6 = sArr[5];
            bArr[i4 + 6] = (byte) ((s5 >>> 4) | ((s6 & 1) << 7));
            bArr[i4 + 7] = (byte) ((s6 >>> 1) & 255);
            int i7 = s6 >>> 9;
            short s7 = sArr[6];
            bArr[i4 + 8] = (byte) (i7 | ((s7 & 63) << 2));
            short s8 = sArr[7];
            bArr[i4 + 9] = (byte) ((s7 >>> 6) | ((s8 & 7) << 5));
            bArr[i4 + 10] = (byte) (s8 >>> 3);
            i2++;
        }
        int i8 = 0;
        while (true) {
            int i9 = i2 * 8;
            if (i8 >= this.params.packDegree() - i9) {
                break;
            }
            sArr[i8] = (short) Polynomial.modQ(this.coeffs[i9 + i8] & UShort.MAX_VALUE, this.params.q());
            i8++;
        }
        while (i8 < 8) {
            sArr[i8] = 0;
            i8++;
        }
        int iPackDegree = this.params.packDegree() & 7;
        if (iPackDegree == 2) {
            int i10 = i2 * 11;
            short s9 = sArr[0];
            bArr[i10] = (byte) (s9 & 255);
            short s10 = sArr[1];
            bArr[i10 + 1] = (byte) ((s9 >>> 8) | ((s10 & 31) << 3));
            bArr[i10 + 2] = (byte) ((s10 >>> 5) | ((sArr[2] & 3) << 6));
            return bArr;
        }
        if (iPackDegree != 4) {
            return bArr;
        }
        int i11 = i2 * 11;
        short s11 = sArr[0];
        bArr[i11] = (byte) (s11 & 255);
        short s12 = sArr[1];
        bArr[i11 + 1] = (byte) ((s11 >>> 8) | ((s12 & 31) << 3));
        short s13 = sArr[2];
        bArr[i11 + 2] = (byte) ((s12 >>> 5) | ((s13 & 3) << 6));
        bArr[i11 + 3] = (byte) ((s13 >>> 2) & 255);
        int i12 = s13 >>> 10;
        short s14 = sArr[3];
        bArr[i11 + 4] = (byte) (i12 | ((s14 & 127) << 1));
        bArr[i11 + 5] = (byte) ((s14 >>> 7) | ((sArr[4] & 15) << 4));
        return bArr;
    }
}
