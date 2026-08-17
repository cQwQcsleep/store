package org.bouncycastle.pqc.legacy.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.legacy.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.legacy.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private short[][] A1;
    private short[][] A1inv;
    private short[][] A2;
    private short[][] A2inv;
    private short[] b1;
    private short[] b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;
    private SecureRandom sr;
    private int[] vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance((Class<?>) Short.TYPE, length, ((length2 + 1) * length2) / 2);
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < length2; i3++) {
                for (int i4 = i3; i4 < length2; i4++) {
                    short[][] sArr2 = this.pub_quadratic;
                    if (i4 == i3) {
                        sArr2[i][i2] = sArr[i][i3][i4];
                    } else {
                        short[] sArr3 = sArr2[i];
                        short[][] sArr4 = sArr[i];
                        sArr3[i2] = GF2Field.addElem(sArr4[i3][i4], sArr4[i4][i3]);
                    }
                    i2++;
                }
            }
        }
    }

    private void computePublicKey() {
        int i;
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.vi;
        boolean z = true;
        int i2 = 0;
        int i3 = iArr[iArr.length - 1] - iArr[0];
        int i4 = iArr[iArr.length - 1];
        int i5 = 3;
        int i6 = 2;
        Class cls = Short.TYPE;
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) cls, i3, i4, i4);
        this.pub_singular = (short[][]) Array.newInstance((Class<?>) cls, i3, i4);
        this.pub_scalar = new short[i3];
        short[] sArr2 = new short[i4];
        int i7 = 0;
        int i8 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i7 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i7].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i7].getCoeffBeta();
            short[][] coeffGamma = this.layers[i7].getCoeffGamma();
            short[] coeffEta = this.layers[i7].getCoeffEta();
            boolean z2 = z;
            int length = coeffAlpha[i2].length;
            int i9 = i2;
            int length2 = coeffBeta[i9].length;
            int i10 = i6;
            int i11 = i9;
            while (i11 < length) {
                int i12 = i9;
                while (true) {
                    i = i4;
                    if (i12 >= length) {
                        break;
                    }
                    int i13 = i9;
                    while (i13 < length2) {
                        int i14 = i13;
                        int i15 = length;
                        int i16 = i12 + length2;
                        short[] sArrMultVect = computeInField.multVect(coeffAlpha[i11][i12][i14], this.A2[i16]);
                        int i17 = i8 + i11;
                        int i18 = i12;
                        sArr[i17] = computeInField.addSquareMatrix(sArr[i17], computeInField.multVects(sArrMultVect, this.A2[i14]));
                        short[] sArrMultVect2 = computeInField.multVect(this.b2[i14], sArrMultVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[i17] = computeInField.addVect(sArrMultVect2, sArr3[i17]);
                        short[] sArrMultVect3 = computeInField.multVect(this.b2[i16], computeInField.multVect(coeffAlpha[i11][i18][i14], this.A2[i14]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i17] = computeInField.addVect(sArrMultVect3, sArr4[i17]);
                        short sMultElem = GF2Field.multElem(coeffAlpha[i11][i18][i14], this.b2[i16]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[i17] = GF2Field.addElem(sArr5[i17], GF2Field.multElem(sMultElem, this.b2[i14]));
                        i13 = i14 + 1;
                        length = i15;
                        i12 = i18;
                    }
                    i12++;
                    i4 = i;
                }
                int i19 = length;
                int i20 = i9;
                while (i20 < length2) {
                    int i21 = i9;
                    while (i21 < length2) {
                        int i22 = i20;
                        short[] sArrMultVect4 = computeInField.multVect(coeffBeta[i11][i20][i21], this.A2[i22]);
                        int i23 = i8 + i11;
                        int i24 = i21;
                        sArr[i23] = computeInField.addSquareMatrix(sArr[i23], computeInField.multVects(sArrMultVect4, this.A2[i24]));
                        short[] sArrMultVect5 = computeInField.multVect(this.b2[i24], sArrMultVect4);
                        short[][] sArr6 = this.pub_singular;
                        sArr6[i23] = computeInField.addVect(sArrMultVect5, sArr6[i23]);
                        short[] sArrMultVect6 = computeInField.multVect(this.b2[i22], computeInField.multVect(coeffBeta[i11][i22][i24], this.A2[i24]));
                        short[][] sArr7 = this.pub_singular;
                        sArr7[i23] = computeInField.addVect(sArrMultVect6, sArr7[i23]);
                        short sMultElem2 = GF2Field.multElem(coeffBeta[i11][i22][i24], this.b2[i22]);
                        short[] sArr8 = this.pub_scalar;
                        sArr8[i23] = GF2Field.addElem(sArr8[i23], GF2Field.multElem(sMultElem2, this.b2[i24]));
                        i21 = i24 + 1;
                        i20 = i22;
                        length2 = length2;
                    }
                    i20++;
                }
                int i25 = length2;
                int i26 = i9;
                while (i26 < i25 + i19) {
                    short[] sArrMultVect7 = computeInField.multVect(coeffGamma[i11][i26], this.A2[i26]);
                    short[][] sArr9 = this.pub_singular;
                    int i27 = i8 + i11;
                    int i28 = i26;
                    sArr9[i27] = computeInField.addVect(sArrMultVect7, sArr9[i27]);
                    short[] sArr10 = this.pub_scalar;
                    sArr10[i27] = GF2Field.addElem(sArr10[i27], GF2Field.multElem(coeffGamma[i11][i28], this.b2[i28]));
                    i26 = i28 + 1;
                }
                short[] sArr11 = this.pub_scalar;
                int i29 = i8 + i11;
                sArr11[i29] = GF2Field.addElem(sArr11[i29], coeffEta[i11]);
                i11++;
                i4 = i;
                length = i19;
                length2 = i25;
            }
            i8 += length;
            i7++;
            z = z2;
            i2 = i9;
            i6 = i10;
            i5 = 3;
        }
        int i30 = i4;
        boolean z3 = z;
        int i31 = i2;
        int i32 = i6;
        int[] iArr2 = new int[i5];
        iArr2[i32] = i30;
        iArr2[z3 ? 1 : 0] = i30;
        iArr2[i31] = i3;
        short[][][] sArr12 = (short[][][]) Array.newInstance((Class<?>) cls, iArr2);
        int[] iArr3 = new int[i32];
        iArr3[z3 ? 1 : 0] = i30;
        iArr3[i31] = i3;
        short[][] sArr13 = (short[][]) Array.newInstance((Class<?>) cls, iArr3);
        short[] sArr14 = new short[i3];
        for (int i33 = i31; i33 < i3; i33++) {
            int i34 = i31;
            while (true) {
                short[][] sArr15 = this.A1;
                if (i34 < sArr15.length) {
                    sArr12[i33] = computeInField.addSquareMatrix(sArr12[i33], computeInField.multMatrix(sArr15[i33][i34], sArr[i34]));
                    sArr13[i33] = computeInField.addVect(sArr13[i33], computeInField.multVect(this.A1[i33][i34], this.pub_singular[i34]));
                    sArr14[i33] = GF2Field.addElem(sArr14[i33], GF2Field.multElem(this.A1[i33][i34], this.pub_scalar[i34]));
                    i34++;
                }
            }
            sArr14[i33] = GF2Field.addElem(sArr14[i33], this.b1[i33]);
        }
        this.pub_singular = sArr13;
        this.pub_scalar = sArr14;
        compactPublicKey(sArr12);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i = 0;
        while (i < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.vi;
            int i2 = i + 1;
            layerArr[i] = new Layer(iArr[i], iArr[i2], this.sr);
            i = i2;
        }
    }

    private void generateL1() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1] - iArr[0];
        this.A1 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i, i);
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A1[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.A1);
        }
        this.b1 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b1[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int[] iArr = this.vi;
        int i = iArr[iArr.length - 1];
        this.A2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i, i);
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A2inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.A2[i2][i3] = (short) (this.sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.A2);
        }
        this.b2 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.b2[i4] = (short) (this.sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        int[] iArr = this.vi;
        return new AsymmetricCipherKeyPair(new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), rainbowPrivateKeyParameters);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.rainbowParams = rainbowKeyGenerationParameters;
        this.sr = rainbowKeyGenerationParameters.getRandom();
        this.vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}
