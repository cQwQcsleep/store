package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSSParameterSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class HRSS1373Polynomial extends HRSSPolynomial {
    private static final int K = 86;
    private static final int L = 1376;
    private static final int M = 344;

    public HRSS1373Polynomial(NTRUHRSSParameterSet nTRUHRSSParameterSet) {
        super(nTRUHRSSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.HRSSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.params.packDegree() / 4) {
            short[] sArr = this.coeffs;
            int i2 = i * 4;
            int i3 = i * 7;
            int i4 = bArr[i3] & UByte.MAX_VALUE;
            byte b = bArr[i3 + 1];
            sArr[i2] = (short) (i4 | ((((short) (b & UByte.MAX_VALUE)) & 63) << 8));
            int i5 = ((b & UByte.MAX_VALUE) >>> 6) | (((short) (bArr[i3 + 2] & UByte.MAX_VALUE)) << 2);
            byte b2 = bArr[i3 + 3];
            sArr[i2 + 1] = (short) (i5 | (((short) (b2 & 15)) << 10));
            int i6 = ((b2 & UByte.MAX_VALUE) >>> 4) | ((((short) (bArr[i3 + 4] & UByte.MAX_VALUE)) & 255) << 4);
            byte b3 = bArr[i3 + 5];
            sArr[i2 + 2] = (short) (i6 | (((short) (b3 & 3)) << 12));
            sArr[i2 + 3] = (short) (((b3 & UByte.MAX_VALUE) >>> 2) | (((short) (bArr[i3 + 6] & UByte.MAX_VALUE)) << 6));
            i++;
        }
        if (this.params.packDegree() % 4 == 2) {
            short[] sArr2 = this.coeffs;
            int i7 = i * 4;
            int i8 = i * 7;
            byte b4 = bArr[i8];
            byte b5 = bArr[i8 + 1];
            sArr2[i7] = (short) (b4 | ((b5 & 63) << 8));
            sArr2[i7 + 1] = (short) (((bArr[i8 + 3] & 15) << 10) | (bArr[i8 + 2] << 2) | (b5 >>> 6));
        }
        this.coeffs[this.params.n() - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.HRSSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        short[] sArr = new short[4];
        int i2 = 0;
        while (i2 < this.params.packDegree() / 4) {
            for (int i3 = 0; i3 < 4; i3++) {
                sArr[i3] = (short) Polynomial.modQ(this.coeffs[(i2 * 4) + i3] & UShort.MAX_VALUE, this.params.q());
            }
            int i4 = i2 * 7;
            short s = sArr[0];
            bArr[i4] = (byte) (s & 255);
            short s2 = sArr[1];
            bArr[i4 + 1] = (byte) ((s >>> 8) | ((s2 & 3) << 6));
            bArr[i4 + 2] = (byte) ((s2 >>> 2) & 255);
            short s3 = sArr[2];
            bArr[i4 + 3] = (byte) ((s2 >>> 10) | ((s3 & 15) << 4));
            bArr[i4 + 4] = (byte) ((s3 >>> 4) & 255);
            short s4 = sArr[3];
            bArr[i4 + 5] = (byte) ((s3 >>> 12) | ((s4 & 63) << 2));
            bArr[i4 + 6] = (byte) (s4 >>> 6);
            i2++;
        }
        if (this.params.packDegree() % 4 == 2) {
            sArr[0] = (short) Polynomial.modQ(this.coeffs[this.params.packDegree() - 2] & UShort.MAX_VALUE, this.params.q());
            short sModQ = (short) Polynomial.modQ(this.coeffs[this.params.packDegree() - 1] & UShort.MAX_VALUE, this.params.q());
            sArr[1] = sModQ;
            int i5 = i2 * 7;
            short s5 = sArr[0];
            bArr[i5] = (byte) (s5 & 255);
            bArr[i5 + 1] = (byte) ((s5 >>> 8) | ((sModQ & 3) << 6));
            bArr[i5 + 2] = (byte) ((sModQ >>> 2) & 255);
            bArr[i5 + 3] = (byte) (sModQ >>> 10);
        }
        return bArr;
    }
}
