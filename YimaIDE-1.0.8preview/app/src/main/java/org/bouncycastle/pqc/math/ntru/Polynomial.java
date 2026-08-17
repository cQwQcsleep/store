package org.bouncycastle.pqc.math.ntru;

import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class Polynomial {
    public short[] coeffs;
    protected NTRUParameterSet params;

    public Polynomial(NTRUParameterSet nTRUParameterSet) {
        this.coeffs = new short[nTRUParameterSet.n()];
        this.params = nTRUParameterSet;
    }

    public static short bothNegativeMask(short s, short s2) {
        return (short) ((s & s2) >>> 15);
    }

    public static short mod3(short s) {
        return (short) ((s & UShort.MAX_VALUE) % 3);
    }

    public static int modQ(int i, int i2) {
        return i % i2;
    }

    private void r2InvToRqInv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            polynomial3.coeffs[i] = (short) (-polynomial2.coeffs[i]);
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.coeffs[i2] = polynomial.coeffs[i2];
        }
        polynomial4.rqMul(this, polynomial3);
        short[] sArr = polynomial4.coeffs;
        sArr[0] = (short) (sArr[0] + 2);
        polynomial5.rqMul(polynomial4, this);
        polynomial4.rqMul(polynomial5, polynomial3);
        short[] sArr2 = polynomial4.coeffs;
        sArr2[0] = (short) (sArr2[0] + 2);
        rqMul(polynomial4, polynomial5);
        polynomial4.rqMul(this, polynomial3);
        short[] sArr3 = polynomial4.coeffs;
        sArr3[0] = (short) (sArr3[0] + 2);
        polynomial5.rqMul(polynomial4, this);
        polynomial4.rqMul(polynomial5, polynomial3);
        short[] sArr4 = polynomial4.coeffs;
        sArr4[0] = (short) (sArr4[0] + 2);
        rqMul(polynomial4, polynomial5);
    }

    public abstract void lift(Polynomial polynomial);

    public void mod3PhiN() {
        int iN = this.params.n();
        for (int i = 0; i < iN; i++) {
            short[] sArr = this.coeffs;
            sArr[i] = mod3((short) (sArr[i] + (sArr[iN - 1] * 2)));
        }
    }

    public void modQPhiN() {
        int iN = this.params.n();
        for (int i = 0; i < iN; i++) {
            short[] sArr = this.coeffs;
            sArr[i] = (short) (sArr[i] - sArr[iN - 1]);
        }
    }

    public void r2Inv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        int i;
        short[] sArr;
        int length = this.coeffs.length;
        short s = 0;
        polynomial5.coeffs[0] = 1;
        for (int i2 = 0; i2 < length; i2++) {
            polynomial2.coeffs[i2] = 1;
        }
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            short[] sArr2 = polynomial.coeffs;
            polynomial3.coeffs[(length - 2) - i3] = (short) ((sArr2[i] ^ sArr2[i3]) & 1);
            i3++;
        }
        polynomial3.coeffs[i] = 0;
        int i4 = 0;
        short s2 = 1;
        for (short s3 = 1; i4 < (i * 2) - s3; s3 = 1) {
            for (int i5 = i; i5 > 0; i5--) {
                short[] sArr3 = polynomial4.coeffs;
                sArr3[i5] = sArr3[i5 - 1];
            }
            polynomial4.coeffs[s] = s;
            short s4 = polynomial3.coeffs[s];
            short s5 = (short) (polynomial2.coeffs[s] & s4);
            int i6 = -s2;
            short sBothNegativeMask = bothNegativeMask((short) i6, (short) (-s4));
            s2 = (short) (((short) (s2 ^ ((i6 ^ s2) & sBothNegativeMask))) + s3);
            int i7 = s;
            short s6 = s;
            while (i7 < length) {
                short[] sArr4 = polynomial2.coeffs;
                short s7 = sArr4[i7];
                short s8 = s6;
                short[] sArr5 = polynomial3.coeffs;
                short s9 = (short) (sBothNegativeMask & (s7 ^ sArr5[i7]));
                sArr4[i7] = (short) (s7 ^ s9);
                sArr5[i7] = (short) (s9 ^ sArr5[i7]);
                short[] sArr6 = polynomial4.coeffs;
                short s10 = sArr6[i7];
                short[] sArr7 = polynomial5.coeffs;
                short s11 = (short) ((sArr7[i7] ^ s10) & sBothNegativeMask);
                sArr6[i7] = (short) (s10 ^ s11);
                sArr7[i7] = (short) (sArr7[i7] ^ s11);
                i7++;
                s6 = s8 == true ? 1 : 0;
            }
            short s12 = s6;
            for (int i8 = s6; i8 < length; i8++) {
                short[] sArr8 = polynomial3.coeffs;
                sArr8[i8] = (short) (sArr8[i8] ^ (polynomial2.coeffs[i8] & s5));
            }
            for (int i9 = s12; i9 < length; i9++) {
                short[] sArr9 = polynomial5.coeffs;
                sArr9[i9] = (short) (sArr9[i9] ^ (polynomial4.coeffs[i9] & s5));
            }
            int i10 = s12;
            while (true) {
                sArr = polynomial3.coeffs;
                if (i10 < i) {
                    int i11 = i10 + 1;
                    sArr[i10] = sArr[i11];
                    i10 = i11;
                }
            }
            sArr[i] = s12;
            i4++;
            s = s12;
        }
        short s13 = s;
        int i12 = s13;
        while (true) {
            short[] sArr10 = this.coeffs;
            if (i12 >= i) {
                sArr10[i] = s13;
                return;
            } else {
                sArr10[i12] = polynomial4.coeffs[(length - 2) - i12];
                i12++;
            }
        }
    }

    public void rqInv(Polynomial polynomial) {
        rqInv(polynomial, this.params.createPolynomial(), this.params.createPolynomial(), this.params.createPolynomial(), this.params.createPolynomial());
    }

    public void rqMul(Polynomial polynomial, Polynomial polynomial2) {
        int i;
        int length = this.coeffs.length;
        int i2 = 0;
        while (i2 < length) {
            this.coeffs[i2] = 0;
            for (int i3 = 1; i3 < length - i2; i3++) {
                short[] sArr = this.coeffs;
                sArr[i2] = (short) (sArr[i2] + (polynomial.coeffs[i2 + i3] * polynomial2.coeffs[length - i3]));
            }
            int i4 = 0;
            while (true) {
                i = i2 + 1;
                if (i4 < i) {
                    short[] sArr2 = this.coeffs;
                    sArr2[i2] = (short) (sArr2[i2] + (polynomial.coeffs[i2 - i4] * polynomial2.coeffs[i4]));
                    i4++;
                }
            }
            i2 = i;
        }
    }

    public void rqSumZeroFromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        sqFromBytes(bArr);
        int i = length - 1;
        this.coeffs[i] = 0;
        for (int i2 = 0; i2 < this.params.packDegree(); i2++) {
            short[] sArr = this.coeffs;
            sArr[i] = (short) (sArr[i] - sArr[i2]);
        }
    }

    public byte[] rqSumZeroToBytes(int i) {
        return sqToBytes(i);
    }

    public void rqToS3(Polynomial polynomial) {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            this.coeffs[i] = (short) modQ(polynomial.coeffs[i] & UShort.MAX_VALUE, this.params.q());
            short sLogQ = (short) (this.coeffs[i] >>> (this.params.logQ() - 1));
            short[] sArr = this.coeffs;
            sArr[i] = (short) (sArr[i] + (sLogQ << (1 - (this.params.logQ() & 1))));
        }
        mod3PhiN();
    }

    public void s3FromBytes(byte[] bArr) {
        int length = this.coeffs.length;
        for (int i = 0; i < this.params.packDegree() / 5; i++) {
            byte b = bArr[i];
            short[] sArr = this.coeffs;
            int i2 = i * 5;
            sArr[i2] = b;
            int i3 = b & UByte.MAX_VALUE;
            sArr[i2 + 1] = (short) ((i3 * 171) >>> 9);
            sArr[i2 + 2] = (short) ((i3 * 57) >>> 9);
            sArr[i2 + 3] = (short) ((i3 * 19) >>> 9);
            sArr[i2 + 4] = (short) ((i3 * 203) >>> 14);
        }
        if (this.params.packDegree() > (this.params.packDegree() / 5) * 5) {
            int iPackDegree = this.params.packDegree() / 5;
            byte b2 = bArr[iPackDegree];
            int i4 = 0;
            while (true) {
                int i5 = (iPackDegree * 5) + i4;
                if (i5 >= this.params.packDegree()) {
                    break;
                }
                this.coeffs[i5] = b2;
                b2 = (byte) (((b2 & UByte.MAX_VALUE) * 171) >> 9);
                i4++;
            }
        }
        this.coeffs[length - 1] = 0;
        mod3PhiN();
    }

    public void s3Inv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        int i;
        short[] sArr;
        int length = this.coeffs.length;
        short s = 0;
        polynomial5.coeffs[0] = 1;
        for (int i2 = 0; i2 < length; i2++) {
            polynomial2.coeffs[i2] = 1;
        }
        int i3 = 0;
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            short[] sArr2 = polynomial.coeffs;
            polynomial3.coeffs[(length - 2) - i3] = mod3((short) ((sArr2[i3] & 3) + ((sArr2[i] & 3) * 2)));
            i3++;
        }
        polynomial3.coeffs[i] = 0;
        int i4 = 0;
        short s2 = 1;
        for (short s3 = 1; i4 < (i * 2) - s3; s3 = 1) {
            for (int i5 = i; i5 > 0; i5--) {
                short[] sArr3 = polynomial4.coeffs;
                sArr3[i5] = sArr3[i5 - 1];
            }
            polynomial4.coeffs[s] = s;
            short sMod3 = mod3((byte) (polynomial3.coeffs[s] * 2 * polynomial2.coeffs[s]));
            int i6 = -s2;
            short sBothNegativeMask = bothNegativeMask((short) i6, (short) (-polynomial3.coeffs[s]));
            s2 = (short) (((short) (s2 ^ ((i6 ^ s2) & sBothNegativeMask))) + s3);
            int i7 = s;
            short s4 = s;
            while (i7 < length) {
                short[] sArr4 = polynomial2.coeffs;
                short s5 = sArr4[i7];
                short s6 = s4;
                short[] sArr5 = polynomial3.coeffs;
                short s7 = (short) (sBothNegativeMask & (s5 ^ sArr5[i7]));
                sArr4[i7] = (short) (s5 ^ s7);
                sArr5[i7] = (short) (s7 ^ sArr5[i7]);
                short[] sArr6 = polynomial4.coeffs;
                short s8 = sArr6[i7];
                short[] sArr7 = polynomial5.coeffs;
                short s9 = (short) ((sArr7[i7] ^ s8) & sBothNegativeMask);
                sArr6[i7] = (short) (s8 ^ s9);
                sArr7[i7] = (short) (sArr7[i7] ^ s9);
                i7++;
                s4 = s6 == true ? 1 : 0;
            }
            short s10 = s4;
            for (int i8 = s4; i8 < length; i8++) {
                short[] sArr8 = polynomial3.coeffs;
                sArr8[i8] = mod3((byte) (sArr8[i8] + (polynomial2.coeffs[i8] * sMod3)));
            }
            for (int i9 = s10; i9 < length; i9++) {
                short[] sArr9 = polynomial5.coeffs;
                sArr9[i9] = mod3((byte) (sArr9[i9] + (polynomial4.coeffs[i9] * sMod3)));
            }
            int i10 = s10;
            while (true) {
                sArr = polynomial3.coeffs;
                if (i10 < i) {
                    int i11 = i10 + 1;
                    sArr[i10] = sArr[i11];
                    i10 = i11;
                }
            }
            sArr[i] = s10;
            i4++;
            s = s10;
        }
        short s11 = s;
        short s12 = polynomial2.coeffs[s11];
        int i12 = s11;
        while (true) {
            short[] sArr10 = this.coeffs;
            if (i12 >= i) {
                sArr10[i] = s11;
                return;
            } else {
                sArr10[i12] = mod3((byte) (polynomial4.coeffs[(length - 2) - i12] * s12));
                i12++;
            }
        }
    }

    public void s3Mul(Polynomial polynomial, Polynomial polynomial2) {
        rqMul(polynomial, polynomial2);
        mod3PhiN();
    }

    public byte[] s3ToBytes(int i) {
        byte[] bArr = new byte[i];
        byte b = 0;
        for (int i2 = 0; i2 < this.params.packDegree() / 5; i2++) {
            short[] sArr = this.coeffs;
            int i3 = i2 * 5;
            bArr[i2] = (byte) (((((byte) (((((byte) (((((byte) (((((byte) (sArr[i3 + 4] & 255)) * 3) + sArr[i3 + 3]) & 255)) * 3) + sArr[i3 + 2]) & 255)) * 3) + sArr[i3 + 1]) & 255)) * 3) + sArr[i3]) & 255);
        }
        if (this.params.packDegree() > (this.params.packDegree() / 5) * 5) {
            int iPackDegree = this.params.packDegree() / 5;
            int i4 = iPackDegree * 5;
            for (int iPackDegree2 = (this.params.packDegree() - i4) - 1; iPackDegree2 >= 0; iPackDegree2--) {
                b = (byte) (((b * 3) + this.coeffs[i4 + iPackDegree2]) & 255);
            }
            bArr[iPackDegree] = b;
        }
        return bArr;
    }

    public abstract void sqFromBytes(byte[] bArr);

    public void sqMul(Polynomial polynomial, Polynomial polynomial2) {
        rqMul(polynomial, polynomial2);
        modQPhiN();
    }

    public abstract byte[] sqToBytes(int i);

    public void trinaryZqToZ3() {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            short[] sArr = this.coeffs;
            sArr[i] = (short) modQ(sArr[i] & UShort.MAX_VALUE, this.params.q());
            short[] sArr2 = this.coeffs;
            short s = sArr2[i];
            sArr2[i] = (short) ((s ^ (s >>> (this.params.logQ() - 1))) & 3);
        }
    }

    public void z3ToZq() {
        int length = this.coeffs.length;
        for (int i = 0; i < length; i++) {
            short[] sArr = this.coeffs;
            short s = sArr[i];
            sArr[i] = (short) (s | ((-(s >>> 1)) & (this.params.q() - 1)));
        }
    }

    public static byte mod3(byte b) {
        return (byte) ((b & UByte.MAX_VALUE) % 3);
    }

    public void rqInv(Polynomial polynomial, Polynomial polynomial2, Polynomial polynomial3, Polynomial polynomial4, Polynomial polynomial5) {
        polynomial2.r2Inv(polynomial);
        r2InvToRqInv(polynomial2, polynomial, polynomial3, polynomial4, polynomial5);
    }

    public void r2Inv(Polynomial polynomial) {
        r2Inv(polynomial, this.params.createPolynomial(), this.params.createPolynomial(), this.params.createPolynomial(), this.params.createPolynomial());
    }

    public void s3Inv(Polynomial polynomial) {
        s3Inv(polynomial, this.params.createPolynomial(), this.params.createPolynomial(), this.params.createPolynomial(), this.params.createPolynomial());
    }
}
