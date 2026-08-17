package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class HPS4096Polynomial extends HPSPolynomial {
    public HPS4096Polynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int i = 0;
        while (true) {
            int iPackDegree = this.params.packDegree() / 2;
            short[] sArr = this.coeffs;
            if (i >= iPackDegree) {
                sArr[this.params.n() - 1] = 0;
                return;
            }
            int i2 = i * 2;
            int i3 = i * 3;
            int i4 = bArr[i3] & UByte.MAX_VALUE;
            byte b = bArr[i3 + 1];
            sArr[i2] = (short) (i4 | ((((short) (b & UByte.MAX_VALUE)) & 15) << 8));
            sArr[i2 + 1] = (short) (((((short) (bArr[i3 + 2] & UByte.MAX_VALUE)) & 255) << 4) | ((b & UByte.MAX_VALUE) >>> 4));
            i++;
        }
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        int iQ = this.params.q();
        for (int i2 = 0; i2 < this.params.packDegree() / 2; i2++) {
            int i3 = i2 * 3;
            int i4 = i2 * 2;
            bArr[i3] = (byte) (Polynomial.modQ(this.coeffs[i4] & UShort.MAX_VALUE, iQ) & 255);
            int iModQ = Polynomial.modQ(this.coeffs[i4] & UShort.MAX_VALUE, iQ) >>> 8;
            int i5 = i4 + 1;
            bArr[i3 + 1] = (byte) (iModQ | ((Polynomial.modQ(this.coeffs[i5] & UShort.MAX_VALUE, iQ) & 15) << 4));
            bArr[i3 + 2] = (byte) (Polynomial.modQ(this.coeffs[i5] & UShort.MAX_VALUE, iQ) >>> 4);
        }
        return bArr;
    }
}
