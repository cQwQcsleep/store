package org.bouncycastle.pqc.crypto.gemss;

import defpackage.qf1;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class GeMSSEngine {
    final int ACCESS_last_equations8;
    Pointer Buffer_NB_WORD_GFqn;
    Pointer Buffer_NB_WORD_MUL;
    final boolean ENABLED_REMOVE_ODD_DEGREE;
    final int HFEDELTA;
    final int HFEDeg;
    final int HFEDegI;
    final int HFEDegJ;
    final int HFENr8;
    final int HFENr8c;
    int HFE_odd_degree;
    final int HFEm;
    final int HFEmq;
    final int HFEmq8;
    final int HFEmr;
    final int HFEmr8;
    final int HFEn;
    int HFEn1h_rightmost;
    int HFEn_1rightmost;
    final int HFEnq;
    final int HFEnr;
    final int HFEnv;
    final int HFEnvq;
    final int HFEnvr;
    final int HFEnvr8;
    final int HFEv;
    final int HFEvq;
    final int HFEvr;
    int II;
    int KP;
    int KX;
    final int LOST_BITS;
    int LTRIANGULAR_NV_SIZE;
    final int LTRIANGULAR_N_SIZE;
    final long MASK_GF2m;
    final long MASK_GF2n;
    final int MATRIXn_SIZE;
    final int MATRIXnv_SIZE;
    final int MLv_GFqn_SIZE;
    int MQv_GFqn_SIZE;
    final int NB_BYTES_EQUATION;
    final int NB_BYTES_GFqm;
    final int NB_BYTES_GFqn;
    final int NB_BYTES_GFqnv;
    int NB_COEFS_HFEPOLY;
    final int NB_ITE;
    int NB_MONOMIAL_PK;
    int NB_MONOMIAL_VINEGAR;
    int NB_UINT_HFEVPOLY;
    int NB_WORD_GF2m;
    int NB_WORD_GF2nv;
    final int NB_WORD_GF2nvm;
    int NB_WORD_GFqn;
    final int NB_WORD_GFqv;
    int NB_WORD_MMUL;
    final int NB_WORD_MUL;
    final int NB_WORD_UNCOMP_EQ;
    int POW_II;
    final int SIZE_DIGEST;
    final int SIZE_DIGEST_UINT;
    final int SIZE_ROW;
    final int SIZE_SEED_SK;
    final int SIZE_SIGN_UNCOMPRESSED;
    final int Sha3BitStrength;
    final int ShakeBitStrength;
    final int VAL_BITS_M;
    private int buffer;
    Mul_GF2x mul;
    private SecureRandom random;
    Rem_GF2n rem;
    SHA3Digest sha3Digest;
    final int NB_BITS_UINT = 64;
    final int LEN_UNROLLED_64 = 4;

    /* JADX INFO: renamed from: org.bouncycastle.pqc.crypto.gemss.GeMSSEngine$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams;

        static {
            int[] iArr = new int[FunctionParams.values().length];
            $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams = iArr;
            try {
                iArr[FunctionParams.N.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[FunctionParams.NV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[FunctionParams.V.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[FunctionParams.M.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum FunctionParams {
        NV,
        V,
        N,
        M
    }

    /* JADX WARN: Code duplicated, block: B:101:0x021a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:102:0x021c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:103:0x021e  */
    /* JADX WARN: Code duplicated, block: B:104:0x022b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0232  */
    /* JADX WARN: Code duplicated, block: B:108:0x0239  */
    /* JADX WARN: Code duplicated, block: B:110:0x0240  */
    /* JADX WARN: Code duplicated, block: B:112:0x0250  */
    /* JADX WARN: Code duplicated, block: B:113:0x0252  */
    /* JADX WARN: Code duplicated, block: B:116:0x025b  */
    /* JADX WARN: Code duplicated, block: B:117:0x025d  */
    /* JADX WARN: Code duplicated, block: B:120:0x026f  */
    /* JADX WARN: Code duplicated, block: B:121:0x0279  */
    /* JADX WARN: Code duplicated, block: B:123:0x027d  */
    /* JADX WARN: Code duplicated, block: B:125:0x028b  */
    /* JADX WARN: Code duplicated, block: B:128:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:131:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:133:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:135:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:138:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:141:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:143:0x02e5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:146:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:147:0x0320  */
    /* JADX WARN: Code duplicated, block: B:149:0x032a  */
    /* JADX WARN: Code duplicated, block: B:155:0x0340  */
    /* JADX WARN: Code duplicated, block: B:157:0x0346  */
    /* JADX WARN: Code duplicated, block: B:158:0x0350  */
    /* JADX WARN: Code duplicated, block: B:160:0x0354  */
    /* JADX WARN: Code duplicated, block: B:161:0x035e  */
    /* JADX WARN: Code duplicated, block: B:163:0x0362  */
    /* JADX WARN: Code duplicated, block: B:164:0x036c  */
    /* JADX WARN: Code duplicated, block: B:166:0x0371  */
    /* JADX WARN: Code duplicated, block: B:168:0x0375  */
    /* JADX WARN: Code duplicated, block: B:171:0x037a  */
    /* JADX WARN: Code duplicated, block: B:172:0x0383  */
    /* JADX WARN: Code duplicated, block: B:173:0x038c  */
    /* JADX WARN: Code duplicated, block: B:177:0x03af A[LOOP:0: B:175:0x03a9->B:177:0x03af, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:181:0x03c2 A[LOOP:1: B:179:0x03ba->B:181:0x03c2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:184:0x03b4 A[EDGE_INSN: B:184:0x03b4->B:178:0x03b4 BREAK  A[LOOP:0: B:175:0x03a9->B:177:0x03af], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x03c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:24:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:25:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:28:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:30:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:33:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:37:0x010d  */
    /* JADX WARN: Code duplicated, block: B:39:0x0112  */
    /* JADX WARN: Code duplicated, block: B:42:0x012b  */
    /* JADX WARN: Code duplicated, block: B:43:0x012e  */
    /* JADX WARN: Code duplicated, block: B:46:0x017f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0183  */
    /* JADX WARN: Code duplicated, block: B:50:0x0187  */
    /* JADX WARN: Code duplicated, block: B:52:0x018b  */
    /* JADX WARN: Code duplicated, block: B:54:0x018f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0193  */
    /* JADX WARN: Code duplicated, block: B:58:0x0197 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:59:0x0199 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x019b  */
    /* JADX WARN: Code duplicated, block: B:62:0x019f  */
    /* JADX WARN: Code duplicated, block: B:64:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:66:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:68:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:70:0x01af  */
    /* JADX WARN: Code duplicated, block: B:72:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:74:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:77:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:79:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:80:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:81:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:82:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:83:0x01da  */
    /* JADX WARN: Code duplicated, block: B:84:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:85:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:86:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:87:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:88:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:89:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:90:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:91:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:92:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:93:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:95:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:96:0x0202  */
    /* JADX WARN: Code duplicated, block: B:99:0x020e  */
    public GeMSSEngine(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Mul_GF2x mul6;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        long jMaskUINT;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        Rem_GF2n rem192_specialized_trinomial_gf2x;
        int i33;
        int i34;
        int i35;
        int i36;
        int i37;
        this.HFEn = i2;
        this.HFEv = i3;
        this.HFEDELTA = i4;
        this.NB_ITE = i5;
        this.HFEDeg = i6;
        this.HFEDegI = i7;
        this.HFEDegJ = i8;
        this.NB_BYTES_GFqn = (i2 >>> 3) + ((i2 & 7) != 0 ? 1 : 0);
        int i38 = i7 + 1;
        this.SIZE_ROW = i38;
        int i39 = i2 + i3;
        this.HFEnv = i39;
        int i40 = i2 >>> 6;
        this.HFEnq = i40;
        int i41 = i2 & 63;
        this.HFEnr = i41;
        int i42 = i39 >>> 6;
        this.HFEnvq = i42;
        int i43 = i39 & 63;
        this.HFEnvr = i43;
        this.SIZE_SEED_SK = i >>> 3;
        int i44 = i2 - 1;
        int i45 = ((i44 << 1) >>> 6) + 1;
        this.NB_WORD_MUL = i45;
        if (i45 == 6) {
            mul6 = new Mul_GF2x.Mul6();
        } else if (i45 == 9) {
            mul6 = new Mul_GF2x.Mul9();
        } else if (i45 == 17) {
            mul6 = new Mul_GF2x.Mul17();
        } else if (i45 != 12) {
            if (i45 == 13) {
                mul6 = new Mul_GF2x.Mul13();
            }
            i9 = 64 - i41;
            int i46 = i2 - i4;
            this.HFEm = i46;
            int i47 = i46 >>> 6;
            this.HFEmq = i47;
            i10 = i46 & 63;
            this.HFEmr = i10;
            i11 = i3 >>> 6;
            this.HFEvq = i11;
            i12 = i3 & 63;
            this.HFEvr = i12;
            if (i12 != 0) {
                i13 = i11 + 1;
            } else {
                i13 = i11;
            }
            this.NB_WORD_GFqv = i13;
            int i48 = i46 >>> 3;
            this.HFEmq8 = i48;
            i14 = i46 & 7;
            this.HFEmr8 = i14;
            if (i14 != 0) {
                i15 = 1;
            } else {
                i15 = 0;
            }
            this.NB_BYTES_GFqm = i48 + i15;
            this.NB_WORD_UNCOMP_EQ = ((((i42 + 1) * i42) >>> 1) * 64) + ((i42 + 1) * i43);
            i16 = i39 & 7;
            this.HFEnvr8 = i16;
            int i49 = i39 >>> 3;
            if (i16 != 0) {
                i17 = 1;
            } else {
                i17 = 0;
            }
            this.NB_BYTES_GFqnv = i49 + i17;
            this.VAL_BITS_M = Math.min(i4 + i3, 8 - i14);
            this.MASK_GF2m = GeMSSUtils.maskUINT(i10);
            jMaskUINT = GeMSSUtils.maskUINT(i41);
            this.MASK_GF2n = jMaskUINT;
            if (i41 != 0) {
                i18 = 1;
            } else {
                i18 = 0;
            }
            int i50 = i40 + i18;
            this.NB_WORD_GFqn = i50;
            this.LTRIANGULAR_N_SIZE = (((i40 * (i40 + 1)) >>> 1) * 64) + (i50 * i41);
            this.MATRIXn_SIZE = i2 * i50;
            if (i43 != 0) {
                i19 = 1;
            } else {
                i19 = 0;
            }
            int i51 = i19 + i42;
            this.NB_WORD_GF2nv = i51;
            this.MATRIXnv_SIZE = i39 * i51;
            this.LTRIANGULAR_NV_SIZE = (((i42 * (i42 + 1)) >>> 1) * 64) + (i43 * i51);
            int i52 = i3 + 1;
            int i53 = ((i3 * i52) >>> 1) + 1;
            this.NB_MONOMIAL_VINEGAR = i53;
            int i54 = (i39 * (i39 + 1)) >>> 1;
            int i55 = i54 + 1;
            this.NB_MONOMIAL_PK = i55;
            this.MQv_GFqn_SIZE = i53 * i50;
            this.ACCESS_last_equations8 = i55 * i48;
            this.NB_BYTES_EQUATION = (i54 + 8) >>> 3;
            int i56 = i55 & 7;
            this.HFENr8 = i56;
            int i57 = (8 - i56) & 7;
            this.HFENr8c = i57;
            this.LOST_BITS = (i14 - 1) * i57;
            this.NB_WORD_MMUL = i45;
            if (i2 != 174) {
                i20 = 13;
            } else if (i2 != 175) {
                i20 = 16;
            } else if (i2 != 177) {
                i20 = 8;
            } else if (i2 != 178) {
                i20 = 31;
            } else if (i2 != 265) {
                i20 = 42;
            } else if (i2 != 266) {
                i20 = 47;
            } else if (i2 != 268) {
                i20 = 25;
            } else if (i2 != 354) {
                i20 = 99;
            } else if (i2 != 358) {
                i20 = 57;
            } else if (i2 != 364) {
                i20 = 9;
            } else if (i2 != 366) {
                i20 = 29;
            } else if (i2 != 402) {
                if (i2 != 537) {
                    i20 = 10;
                    i21 = 1;
                    i22 = 2;
                } else if (i2 != 544) {
                    i20 = 128;
                    i21 = 1;
                    i22 = 3;
                } else if (i2 != 270) {
                    i20 = 53;
                } else {
                    if (i2 == 271) {
                        qf1.a("error: need to add support for HFEn=", i2);
                        throw null;
                    }
                    i20 = 58;
                }
                if (i22 != 0) {
                    i23 = 64 - i21;
                    i24 = 64 - i22;
                } else {
                    i23 = 0;
                    i24 = 0;
                }
                i25 = 64 - (i20 & 63);
                i26 = i6 & 1;
                if (i26 == 0) {
                    this.ENABLED_REMOVE_ODD_DEGREE = true;
                    i37 = (1 << i7) + 1;
                    this.HFE_odd_degree = i37;
                    if (i26 == 0) {
                        w01.a("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                        throw null;
                    }
                    if (i37 <= i6) {
                        w01.a("It is useless to remove 0 term.");
                        throw null;
                    }
                    if (i37 > 1) {
                        w01.a("The case where the term X^3 is removing is not implemented.");
                        throw null;
                    }
                    this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
                    i27 = 0;
                } else {
                    i27 = 0;
                    this.ENABLED_REMOVE_ODD_DEGREE = false;
                    this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i38) >>> 1);
                }
                if (i10 != 0) {
                    i28 = 1;
                } else {
                    i28 = i27;
                }
                int i58 = i47 + i28;
                this.NB_WORD_GF2m = i58;
                int i59 = i51 - i58;
                if (i10 != 0) {
                    i29 = 1;
                } else {
                    i29 = i27;
                }
                int i60 = i59 + i29;
                this.NB_WORD_GF2nvm = i60;
                this.SIZE_SIGN_UNCOMPRESSED = i51 + ((i5 - 1) * i60);
                if (i <= 128) {
                    this.SIZE_DIGEST = 32;
                    this.SIZE_DIGEST_UINT = 4;
                    this.ShakeBitStrength = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                    this.Sha3BitStrength = 256;
                } else {
                    if (i <= 192) {
                        this.SIZE_DIGEST = 48;
                        this.SIZE_DIGEST_UINT = 6;
                        this.ShakeBitStrength = 256;
                        i30 = KyberEngine.KyberPolyBytes;
                    } else {
                        this.SIZE_DIGEST = 64;
                        this.SIZE_DIGEST_UINT = 8;
                        this.ShakeBitStrength = 256;
                        i30 = 512;
                    }
                    this.Sha3BitStrength = i30;
                }
                this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
                int i61 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i38 * i3);
                int i62 = this.NB_WORD_GFqn;
                this.NB_UINT_HFEVPOLY = i61 * i62;
                this.MLv_GFqn_SIZE = i52 * i62;
                if (i6 > 34 || (i2 > 196 && i6 < 256)) {
                    if (i6 == 17) {
                        i31 = 4;
                    } else {
                        i31 = 6;
                    }
                    this.II = i31;
                    int i63 = this.II;
                    int i64 = 1 << i63;
                    this.POW_II = i64;
                    int i65 = (i6 >>> i63) + (i6 % i64 != 0 ? 1 : i27);
                    this.KP = i65;
                    this.KX = i6 - i65;
                }
                if (i22 == 0) {
                    if (i2 == 544 || i20 != 128) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
                    } else {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_K3_IS_128_GF2X(i21, i22, i41, i9, i23, i24, jMaskUINT);
                    }
                } else if (i2 > 256 || i2 >= 289 || i20 <= 32 || i20 >= 64) {
                    i32 = i20;
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 != 9) {
                        if (i45 == 12) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                        }
                        this.Buffer_NB_WORD_MUL = new Pointer(i45);
                        this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
                        i33 = 31;
                        this.HFEn_1rightmost = 31;
                        while (true) {
                            i34 = this.HFEn_1rightmost;
                            if ((i44 >>> i34) == 0) {
                                break;
                            } else {
                                this.HFEn_1rightmost = i34 - 1;
                            }
                        }
                        i35 = (i2 + 1) >>> 1;
                        while (true) {
                            this.HFEn1h_rightmost = i33;
                            i36 = this.HFEn1h_rightmost;
                            if ((i35 >>> i36) == 0) {
                                this.HFEn1h_rightmost = i36 - 1;
                                return;
                            }
                            i33 = i36 - 1;
                        }
                    } else {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    }
                } else {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i20, i41, i9, i25, jMaskUINT);
                }
                this.rem = rem192_specialized_trinomial_gf2x;
                this.Buffer_NB_WORD_MUL = new Pointer(i45);
                this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
                i33 = 31;
                this.HFEn_1rightmost = 31;
                while (true) {
                    i34 = this.HFEn_1rightmost;
                    if ((i44 >>> i34) == 0) {
                        break;
                        break;
                    }
                    this.HFEn_1rightmost = i34 - 1;
                }
                i35 = (i2 + 1) >>> 1;
                while (true) {
                    this.HFEn1h_rightmost = i33;
                    i36 = this.HFEn1h_rightmost;
                    if ((i35 >>> i36) == 0) {
                        this.HFEn1h_rightmost = i36 - 1;
                        return;
                    }
                    i33 = i36 - 1;
                }
            } else {
                i20 = 171;
            }
            i22 = 0;
            i21 = 0;
            if (i22 != 0) {
                i23 = 64 - i21;
                i24 = 64 - i22;
            } else {
                i23 = 0;
                i24 = 0;
            }
            i25 = 64 - (i20 & 63);
            i26 = i6 & 1;
            if (i26 == 0) {
                this.ENABLED_REMOVE_ODD_DEGREE = true;
                i37 = (1 << i7) + 1;
                this.HFE_odd_degree = i37;
                if (i26 == 0) {
                    w01.a("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                    throw null;
                }
                if (i37 <= i6) {
                    w01.a("It is useless to remove 0 term.");
                    throw null;
                }
                if (i37 > 1) {
                    w01.a("The case where the term X^3 is removing is not implemented.");
                    throw null;
                }
                this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
                i27 = 0;
            } else {
                i27 = 0;
                this.ENABLED_REMOVE_ODD_DEGREE = false;
                this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i38) >>> 1);
            }
            if (i10 != 0) {
                i28 = 1;
            } else {
                i28 = i27;
            }
            int i510 = i47 + i28;
            this.NB_WORD_GF2m = i510;
            int i511 = i51 - i510;
            if (i10 != 0) {
                i29 = 1;
            } else {
                i29 = i27;
            }
            int i66 = i511 + i29;
            this.NB_WORD_GF2nvm = i66;
            this.SIZE_SIGN_UNCOMPRESSED = i51 + ((i5 - 1) * i66);
            if (i <= 128) {
                this.SIZE_DIGEST = 32;
                this.SIZE_DIGEST_UINT = 4;
                this.ShakeBitStrength = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                this.Sha3BitStrength = 256;
            } else {
                if (i <= 192) {
                    this.SIZE_DIGEST = 48;
                    this.SIZE_DIGEST_UINT = 6;
                    this.ShakeBitStrength = 256;
                    i30 = KyberEngine.KyberPolyBytes;
                } else {
                    this.SIZE_DIGEST = 64;
                    this.SIZE_DIGEST_UINT = 8;
                    this.ShakeBitStrength = 256;
                    i30 = 512;
                }
                this.Sha3BitStrength = i30;
            }
            this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
            int i67 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i38 * i3);
            int i68 = this.NB_WORD_GFqn;
            this.NB_UINT_HFEVPOLY = i67 * i68;
            this.MLv_GFqn_SIZE = i52 * i68;
            if (i6 > 34) {
                if (i6 == 17) {
                    i31 = 4;
                } else {
                    i31 = 6;
                }
                this.II = i31;
                int i69 = this.II;
                int i610 = 1 << i69;
                this.POW_II = i610;
                int i611 = (i6 >>> i69) + (i6 % i610 != 0 ? 1 : i27);
                this.KP = i611;
                this.KX = i6 - i611;
            } else {
                if (i6 == 17) {
                    i31 = 4;
                } else {
                    i31 = 6;
                }
                this.II = i31;
                int i612 = this.II;
                int i613 = 1 << i612;
                this.POW_II = i613;
                int i614 = (i6 >>> i612) + (i6 % i613 != 0 ? 1 : i27);
                this.KP = i614;
                this.KX = i6 - i614;
            }
            if (i22 == 0) {
                if (i2 > 256) {
                    i32 = i20;
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 != 9) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 12) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    }
                } else {
                    i32 = i20;
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 != 9) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 12) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    }
                }
                this.Buffer_NB_WORD_MUL = new Pointer(i45);
                this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
                i33 = 31;
                this.HFEn_1rightmost = 31;
                while (true) {
                    i34 = this.HFEn_1rightmost;
                    if ((i44 >>> i34) == 0) {
                        break;
                        break;
                    }
                    this.HFEn_1rightmost = i34 - 1;
                }
                i35 = (i2 + 1) >>> 1;
                while (true) {
                    this.HFEn1h_rightmost = i33;
                    i36 = this.HFEn1h_rightmost;
                    if ((i35 >>> i36) == 0) {
                        this.HFEn1h_rightmost = i36 - 1;
                        return;
                    }
                    i33 = i36 - 1;
                }
            } else if (i2 == 544) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
            } else {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
            }
            this.rem = rem192_specialized_trinomial_gf2x;
            this.Buffer_NB_WORD_MUL = new Pointer(i45);
            this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
            i33 = 31;
            this.HFEn_1rightmost = 31;
            while (true) {
                i34 = this.HFEn_1rightmost;
                if ((i44 >>> i34) == 0) {
                    break;
                    break;
                }
                this.HFEn_1rightmost = i34 - 1;
            }
            i35 = (i2 + 1) >>> 1;
            while (true) {
                this.HFEn1h_rightmost = i33;
                i36 = this.HFEn1h_rightmost;
                if ((i35 >>> i36) == 0) {
                    this.HFEn1h_rightmost = i36 - 1;
                    return;
                }
                i33 = i36 - 1;
            }
        } else {
            mul6 = new Mul_GF2x.Mul12();
        }
        this.mul = mul6;
        i9 = 64 - i41;
        int i410 = i2 - i4;
        this.HFEm = i410;
        int i411 = i410 >>> 6;
        this.HFEmq = i411;
        i10 = i410 & 63;
        this.HFEmr = i10;
        i11 = i3 >>> 6;
        this.HFEvq = i11;
        i12 = i3 & 63;
        this.HFEvr = i12;
        if (i12 != 0) {
            i13 = i11 + 1;
        } else {
            i13 = i11;
        }
        this.NB_WORD_GFqv = i13;
        int i412 = i410 >>> 3;
        this.HFEmq8 = i412;
        i14 = i410 & 7;
        this.HFEmr8 = i14;
        if (i14 != 0) {
            i15 = 1;
        } else {
            i15 = 0;
        }
        this.NB_BYTES_GFqm = i412 + i15;
        this.NB_WORD_UNCOMP_EQ = ((((i42 + 1) * i42) >>> 1) * 64) + ((i42 + 1) * i43);
        i16 = i39 & 7;
        this.HFEnvr8 = i16;
        int i413 = i39 >>> 3;
        if (i16 != 0) {
            i17 = 1;
        } else {
            i17 = 0;
        }
        this.NB_BYTES_GFqnv = i413 + i17;
        this.VAL_BITS_M = Math.min(i4 + i3, 8 - i14);
        this.MASK_GF2m = GeMSSUtils.maskUINT(i10);
        jMaskUINT = GeMSSUtils.maskUINT(i41);
        this.MASK_GF2n = jMaskUINT;
        if (i41 != 0) {
            i18 = 1;
        } else {
            i18 = 0;
        }
        int i512 = i40 + i18;
        this.NB_WORD_GFqn = i512;
        this.LTRIANGULAR_N_SIZE = (((i40 * (i40 + 1)) >>> 1) * 64) + (i512 * i41);
        this.MATRIXn_SIZE = i2 * i512;
        if (i43 != 0) {
            i19 = 1;
        } else {
            i19 = 0;
        }
        int i513 = i19 + i42;
        this.NB_WORD_GF2nv = i513;
        this.MATRIXnv_SIZE = i39 * i513;
        this.LTRIANGULAR_NV_SIZE = (((i42 * (i42 + 1)) >>> 1) * 64) + (i43 * i513);
        int i514 = i3 + 1;
        int i515 = ((i3 * i514) >>> 1) + 1;
        this.NB_MONOMIAL_VINEGAR = i515;
        int i516 = (i39 * (i39 + 1)) >>> 1;
        int i517 = i516 + 1;
        this.NB_MONOMIAL_PK = i517;
        this.MQv_GFqn_SIZE = i515 * i512;
        this.ACCESS_last_equations8 = i517 * i412;
        this.NB_BYTES_EQUATION = (i516 + 8) >>> 3;
        int i518 = i517 & 7;
        this.HFENr8 = i518;
        int i519 = (8 - i518) & 7;
        this.HFENr8c = i519;
        this.LOST_BITS = (i14 - 1) * i519;
        this.NB_WORD_MMUL = i45;
        if (i2 != 174) {
            i20 = 13;
        } else if (i2 != 175) {
            i20 = 16;
        } else if (i2 != 177) {
            i20 = 8;
        } else if (i2 != 178) {
            i20 = 31;
        } else if (i2 != 265) {
            i20 = 42;
        } else if (i2 != 266) {
            i20 = 47;
        } else if (i2 != 268) {
            i20 = 25;
        } else if (i2 != 354) {
            i20 = 99;
        } else if (i2 != 358) {
            i20 = 57;
        } else if (i2 != 364) {
            i20 = 9;
        } else if (i2 != 366) {
            i20 = 29;
        } else if (i2 != 402) {
            if (i2 != 537) {
                i20 = 10;
                i21 = 1;
                i22 = 2;
            } else if (i2 != 544) {
                i20 = 128;
                i21 = 1;
                i22 = 3;
            } else if (i2 != 270) {
                i20 = 53;
            } else {
                if (i2 == 271) {
                    qf1.a("error: need to add support for HFEn=", i2);
                    throw null;
                }
                i20 = 58;
            }
            if (i22 != 0) {
                i23 = 64 - i21;
                i24 = 64 - i22;
            } else {
                i23 = 0;
                i24 = 0;
            }
            i25 = 64 - (i20 & 63);
            i26 = i6 & 1;
            if (i26 == 0) {
                this.ENABLED_REMOVE_ODD_DEGREE = true;
                i37 = (1 << i7) + 1;
                this.HFE_odd_degree = i37;
                if (i26 == 0) {
                    w01.a("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                    throw null;
                }
                if (i37 <= i6) {
                    w01.a("It is useless to remove 0 term.");
                    throw null;
                }
                if (i37 > 1) {
                    w01.a("The case where the term X^3 is removing is not implemented.");
                    throw null;
                }
                this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
                i27 = 0;
            } else {
                i27 = 0;
                this.ENABLED_REMOVE_ODD_DEGREE = false;
                this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i38) >>> 1);
            }
            if (i10 != 0) {
                i28 = 1;
            } else {
                i28 = i27;
            }
            int i5110 = i411 + i28;
            this.NB_WORD_GF2m = i5110;
            int i5111 = i513 - i5110;
            if (i10 != 0) {
                i29 = 1;
            } else {
                i29 = i27;
            }
            int i615 = i5111 + i29;
            this.NB_WORD_GF2nvm = i615;
            this.SIZE_SIGN_UNCOMPRESSED = i513 + ((i5 - 1) * i615);
            if (i <= 128) {
                this.SIZE_DIGEST = 32;
                this.SIZE_DIGEST_UINT = 4;
                this.ShakeBitStrength = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                this.Sha3BitStrength = 256;
            } else {
                if (i <= 192) {
                    this.SIZE_DIGEST = 48;
                    this.SIZE_DIGEST_UINT = 6;
                    this.ShakeBitStrength = 256;
                    i30 = KyberEngine.KyberPolyBytes;
                } else {
                    this.SIZE_DIGEST = 64;
                    this.SIZE_DIGEST_UINT = 8;
                    this.ShakeBitStrength = 256;
                    i30 = 512;
                }
                this.Sha3BitStrength = i30;
            }
            this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
            int i616 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i38 * i3);
            int i617 = this.NB_WORD_GFqn;
            this.NB_UINT_HFEVPOLY = i616 * i617;
            this.MLv_GFqn_SIZE = i514 * i617;
            if (i6 > 34) {
                if (i6 == 17) {
                    i31 = 4;
                } else {
                    i31 = 6;
                }
                this.II = i31;
                int i618 = this.II;
                int i619 = 1 << i618;
                this.POW_II = i619;
                int i6110 = (i6 >>> i618) + (i6 % i619 != 0 ? 1 : i27);
                this.KP = i6110;
                this.KX = i6 - i6110;
            } else {
                if (i6 == 17) {
                    i31 = 4;
                } else {
                    i31 = 6;
                }
                this.II = i31;
                int i6111 = this.II;
                int i6112 = 1 << i6111;
                this.POW_II = i6112;
                int i6113 = (i6 >>> i6111) + (i6 % i6112 != 0 ? 1 : i27);
                this.KP = i6113;
                this.KX = i6 - i6113;
            }
            if (i22 == 0) {
                if (i2 > 256) {
                    i32 = i20;
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 != 9) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 12) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    }
                } else {
                    i32 = i20;
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 != 9) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    } else if (i45 == 12) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                    }
                }
                this.Buffer_NB_WORD_MUL = new Pointer(i45);
                this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
                i33 = 31;
                this.HFEn_1rightmost = 31;
                while (true) {
                    i34 = this.HFEn_1rightmost;
                    if ((i44 >>> i34) == 0) {
                        break;
                        break;
                    }
                    this.HFEn_1rightmost = i34 - 1;
                }
                i35 = (i2 + 1) >>> 1;
                while (true) {
                    this.HFEn1h_rightmost = i33;
                    i36 = this.HFEn1h_rightmost;
                    if ((i35 >>> i36) == 0) {
                        this.HFEn1h_rightmost = i36 - 1;
                        return;
                    }
                    i33 = i36 - 1;
                }
            } else if (i2 == 544) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
            } else {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
            }
            this.rem = rem192_specialized_trinomial_gf2x;
            this.Buffer_NB_WORD_MUL = new Pointer(i45);
            this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
            i33 = 31;
            this.HFEn_1rightmost = 31;
            while (true) {
                i34 = this.HFEn_1rightmost;
                if ((i44 >>> i34) == 0) {
                    break;
                    break;
                }
                this.HFEn_1rightmost = i34 - 1;
            }
            i35 = (i2 + 1) >>> 1;
            while (true) {
                this.HFEn1h_rightmost = i33;
                i36 = this.HFEn1h_rightmost;
                if ((i35 >>> i36) == 0) {
                    this.HFEn1h_rightmost = i36 - 1;
                    return;
                }
                i33 = i36 - 1;
            }
        } else {
            i20 = 171;
        }
        i22 = 0;
        i21 = 0;
        if (i22 != 0) {
            i23 = 64 - i21;
            i24 = 64 - i22;
        } else {
            i23 = 0;
            i24 = 0;
        }
        i25 = 64 - (i20 & 63);
        i26 = i6 & 1;
        if (i26 == 0) {
            this.ENABLED_REMOVE_ODD_DEGREE = true;
            i37 = (1 << i7) + 1;
            this.HFE_odd_degree = i37;
            if (i26 == 0) {
                w01.a("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                throw null;
            }
            if (i37 <= i6) {
                w01.a("It is useless to remove 0 term.");
                throw null;
            }
            if (i37 > 1) {
                w01.a("The case where the term X^3 is removing is not implemented.");
                throw null;
            }
            this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
            i27 = 0;
        } else {
            i27 = 0;
            this.ENABLED_REMOVE_ODD_DEGREE = false;
            this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i38) >>> 1);
        }
        if (i10 != 0) {
            i28 = 1;
        } else {
            i28 = i27;
        }
        int i5112 = i411 + i28;
        this.NB_WORD_GF2m = i5112;
        int i5113 = i513 - i5112;
        if (i10 != 0) {
            i29 = 1;
        } else {
            i29 = i27;
        }
        int i6114 = i5113 + i29;
        this.NB_WORD_GF2nvm = i6114;
        this.SIZE_SIGN_UNCOMPRESSED = i513 + ((i5 - 1) * i6114);
        if (i <= 128) {
            this.SIZE_DIGEST = 32;
            this.SIZE_DIGEST_UINT = 4;
            this.ShakeBitStrength = CodeEditor.FLAG_DRAW_SOFT_WRAP;
            this.Sha3BitStrength = 256;
        } else {
            if (i <= 192) {
                this.SIZE_DIGEST = 48;
                this.SIZE_DIGEST_UINT = 6;
                this.ShakeBitStrength = 256;
                i30 = KyberEngine.KyberPolyBytes;
            } else {
                this.SIZE_DIGEST = 64;
                this.SIZE_DIGEST_UINT = 8;
                this.ShakeBitStrength = 256;
                i30 = 512;
            }
            this.Sha3BitStrength = i30;
        }
        this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
        int i6115 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i38 * i3);
        int i6116 = this.NB_WORD_GFqn;
        this.NB_UINT_HFEVPOLY = i6115 * i6116;
        this.MLv_GFqn_SIZE = i514 * i6116;
        if (i6 > 34) {
            if (i6 == 17) {
                i31 = 4;
            } else {
                i31 = 6;
            }
            this.II = i31;
            int i6117 = this.II;
            int i6118 = 1 << i6117;
            this.POW_II = i6118;
            int i6119 = (i6 >>> i6117) + (i6 % i6118 != 0 ? 1 : i27);
            this.KP = i6119;
            this.KX = i6 - i6119;
        } else {
            if (i6 == 17) {
                i31 = 4;
            } else {
                i31 = 6;
            }
            this.II = i31;
            int i61110 = this.II;
            int i61111 = 1 << i61110;
            this.POW_II = i61111;
            int i61112 = (i6 >>> i61110) + (i6 % i61111 != 0 ? 1 : i27);
            this.KP = i61112;
            this.KX = i6 - i61112;
        }
        if (i22 == 0) {
            if (i2 > 256) {
                i32 = i20;
                if (i2 == 354) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i2 == 358) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i2 == 402) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i45 == 6) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i45 != 9) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i45 == 12) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                }
            } else {
                i32 = i20;
                if (i2 == 354) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i2 == 358) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i2 == 402) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i45 == 6) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i45 != 9) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                } else if (i45 == 12) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i32, i41, i9, i25, jMaskUINT);
                }
            }
            this.Buffer_NB_WORD_MUL = new Pointer(i45);
            this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
            i33 = 31;
            this.HFEn_1rightmost = 31;
            while (true) {
                i34 = this.HFEn_1rightmost;
                if ((i44 >>> i34) == 0) {
                    break;
                    break;
                }
                this.HFEn_1rightmost = i34 - 1;
            }
            i35 = (i2 + 1) >>> 1;
            while (true) {
                this.HFEn1h_rightmost = i33;
                i36 = this.HFEn1h_rightmost;
                if ((i35 >>> i36) == 0) {
                    this.HFEn1h_rightmost = i36 - 1;
                    return;
                }
                i33 = i36 - 1;
            }
        } else if (i2 == 544) {
            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
        } else {
            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i21, i22, i20, i41, i9, i23, i24, i25, jMaskUINT);
        }
        this.rem = rem192_specialized_trinomial_gf2x;
        this.Buffer_NB_WORD_MUL = new Pointer(i45);
        this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
        i33 = 31;
        this.HFEn_1rightmost = 31;
        while (true) {
            i34 = this.HFEn_1rightmost;
            if ((i44 >>> i34) == 0) {
                break;
                break;
            }
            this.HFEn_1rightmost = i34 - 1;
        }
        i35 = (i2 + 1) >>> 1;
        while (true) {
            this.HFEn1h_rightmost = i33;
            i36 = this.HFEn1h_rightmost;
            if ((i35 >>> i36) == 0) {
                this.HFEn1h_rightmost = i36 - 1;
                return;
            }
            i33 = i36 - 1;
        }
    }

    private void CMP_AND_SWAP_CST_TIME(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        long jCMP_LT_UINT = 0;
        long jORBITS_UINT = 0;
        long j = 0;
        for (int i = this.NB_WORD_GFqn - 1; i > 0; i--) {
            jORBITS_UINT |= GeMSSUtils.ORBITS_UINT(pointer2.get(i) ^ pointer.get(i));
            j += jORBITS_UINT;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.NB_WORD_GFqn;
            if (i2 >= i3) {
                pointer3.setRangeFromXorAndMask_xor(pointer, pointer2, -jCMP_LT_UINT, i3);
                return;
            } else {
                jCMP_LT_UINT |= (-GeMSSUtils.NORBITS_UINT(((long) i2) ^ j)) & GeMSSUtils.CMP_LT_UINT(pointer2.get(i2), pointer.get(i2));
                i2++;
            }
        }
    }

    private void LOOPIR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3, int i4, boolean z) {
        for (int i5 = 0; i5 < i; i5++) {
            Pointer pointer4 = new Pointer(pointer3);
            int i6 = 1;
            while (i6 <= i2) {
                LOOPJR(pointer, pointer2, pointer4, 64, i4, i6);
                i6++;
            }
            if (z) {
                LOOPJR(pointer, pointer2, pointer4, i3, i4, i6);
            }
            pointer2.move(i4);
        }
    }

    private void LOOPIR_INIT(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2) {
        while (i < i2) {
            pointer.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.changeIndex(pointer3);
            GeMSSEngine geMSSEngine = this;
            geMSSEngine.LOOPK_COMPLETE(pointer, pointer4, pointer2, 0, this.HFEnvq);
            pointer4.move(geMSSEngine.NB_WORD_GF2nv);
            i++;
            this = geMSSEngine;
        }
    }

    private void LOOPIR_LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        while (i < i2) {
            LOOPK_COMPLETE(pointer, pointer2, pointer3, 0, this.HFEnvq);
            i++;
        }
    }

    private void LOOPJR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3) {
        int iMin = Math.min(i2, i3);
        pointer.set(0L);
        for (int i4 = 0; i4 < i; i4++) {
            pointer.setXor(GeMSSUtils.XORBITS_UINT(pointer2.getDotProduct(0, pointer3, 0, iMin)) << i4);
            pointer3.move(i3);
        }
        pointer.moveIncremental();
    }

    private long LOOPJR_NOCST_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        while (i < i2) {
            if ((1 & j) != 0) {
                pointer.setXorRange(0, pointerUnion, 0, i4);
            }
            pointerUnion.moveNextBytes(i3);
            j >>>= 1;
            i++;
        }
        return j;
    }

    private void LOOPJR_UNROLLED_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        int i5 = i;
        long jLOOPJR_NOCST_64 = j;
        while (i5 < i2 - 3) {
            jLOOPJR_NOCST_64 = LOOPJR_NOCST_64(pointer, pointerUnion, 0, 4, jLOOPJR_NOCST_64, i3, i4);
            i5 += 4;
        }
        LOOPJR_NOCST_64(pointer, pointerUnion, i5, i2, jLOOPJR_NOCST_64, i3, i4);
    }

    private void LOOPKR(Pointer pointer, Pointer pointer2, long j, int i, int i2) {
        while (i < i2) {
            pointer2.setXorRangeAndMaskMove(pointer, this.NB_WORD_GFqn, -(1 & j));
            j >>>= 1;
            i++;
        }
    }

    private void LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            LOOPKR(pointer3, pointer, pointer2.get(i3), 0, 64);
        }
        if (this.HFEnvr != 0) {
            LOOPKR(pointer3, pointer, pointer2.get(i2), 0, this.HFEnvr);
        }
        pointer.move(this.NB_WORD_GFqn);
    }

    private int chooseRootHFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        GeMSSEngine geMSSEngine;
        SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar2;
        Pointer pointer3 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer4 = new Pointer(((this.HFEDeg << 1) - 1) * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer((this.HFEDeg + 1) * this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
        pointer6.setRangeFromXor(complete_sparse_monic_gf2nxVar.poly, pointer2, this.NB_WORD_GFqn);
        int i = this.HFEDeg;
        if (i <= 34 || (this.HFEn > 196 && i < 256)) {
            geMSSEngine = this;
            complete_sparse_monic_gf2nxVar2 = complete_sparse_monic_gf2nxVar;
            geMSSEngine.frobeniusMap_multisqr_HFE_gf2nx(pointer4, complete_sparse_monic_gf2nxVar2, pointer6);
        } else {
            int i2 = 2 << this.HFEDegI;
            pointer4.set(this.NB_WORD_GFqn * i2, 1L);
            geMSSEngine = this;
            geMSSEngine.divsqr_r_HFE_cstdeg_gf2nx(pointer4, i2, i2, this.HFEDeg, complete_sparse_monic_gf2nxVar, pointer6);
            complete_sparse_monic_gf2nxVar2 = complete_sparse_monic_gf2nxVar;
            geMSSEngine.for_sqr_divsqr(pointer4, geMSSEngine.HFEDegI + 1, geMSSEngine.HFEn, complete_sparse_monic_gf2nxVar2, pointer6);
        }
        pointer4.setXor(geMSSEngine.NB_WORD_GFqn, 1L);
        int index = pointer5.getIndex();
        pointer5.copyFrom(complete_sparse_monic_gf2nxVar2.poly, geMSSEngine.NB_WORD_GFqn);
        geMSSEngine.for_copy_move(pointer5, complete_sparse_monic_gf2nxVar2);
        pointer5.changeIndex(index);
        pointer5.set(geMSSEngine.HFEDeg * geMSSEngine.NB_WORD_GFqn, 1L);
        pointer5.setXorRange(pointer2, geMSSEngine.NB_WORD_GFqn);
        int iGcd_gf2nx = geMSSEngine.gcd_gf2nx(pointer5, geMSSEngine.HFEDeg, pointer4, pointer4.getD_for_not0_or_plus(geMSSEngine.NB_WORD_GFqn, geMSSEngine.HFEDeg - 1));
        if (geMSSEngine.buffer != 0) {
            pointer4.swap(pointer5);
        }
        if (pointer4.is0_gf2n(0, geMSSEngine.NB_WORD_GFqn) == 0) {
            return 0;
        }
        geMSSEngine.convMonic_gf2nx(pointer5, iGcd_gf2nx);
        Pointer pointer7 = new Pointer(geMSSEngine.NB_WORD_GFqn * iGcd_gf2nx);
        geMSSEngine.findRootsSplit_gf2nx(pointer7, pointer5, iGcd_gf2nx);
        if (iGcd_gf2nx == 1) {
            pointer.copyFrom(pointer7, geMSSEngine.NB_WORD_GFqn);
            return iGcd_gf2nx;
        }
        geMSSEngine.fast_sort_gf2n(pointer7, iGcd_gf2nx);
        GeMSSEngine geMSSEngine2 = geMSSEngine;
        geMSSEngine2.getSHA3Hash(pointer3, 0, geMSSEngine.Sha3BitStrength >>> 3, pointer2.toBytes(geMSSEngine.NB_BYTES_GFqn), 0, geMSSEngine.NB_BYTES_GFqn, new byte[geMSSEngine.Sha3BitStrength >>> 3]);
        int iRemainderUnsigned = (int) remainderUnsigned(pointer3.get(), iGcd_gf2nx);
        int i3 = geMSSEngine2.NB_WORD_GFqn;
        pointer.copyFrom(0, pointer7, iRemainderUnsigned * i3, i3);
        return iGcd_gf2nx;
    }

    private void choose_LOOPJR(Pointer pointer, PointerUnion pointerUnion, int i, long j, int i2, int i3) {
        int i4 = this.HFEnvr;
        if (i4 < 8) {
            LOOPJR_NOCST_64(pointer, pointerUnion, i, i4, j, i2, i3);
        } else {
            LOOPJR_UNROLLED_64(pointer, pointerUnion, i, i4, j, i2, i3);
        }
    }

    private long convMQ_last_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        GeMSSEngine geMSSEngine;
        Pointer pointer2;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i = this.HFEnv - 1;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int iFor_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, i2);
        if (i3 != 0) {
            int i4 = i3 + 1;
            geMSSEngine = this;
            pointer2 = pointer;
            iFor_setpk2_end_move_plus = geMSSEngine.setPk2Value(pointer2, pointerUnion2, iFor_setpk2_end_move_plus, i2, i4);
        } else {
            geMSSEngine = this;
            pointer2 = pointer;
        }
        int i5 = geMSSEngine.HFEnv;
        int i6 = geMSSEngine.LOST_BITS;
        int i7 = i5 - i6;
        int i8 = i7 >>> 6;
        int i9 = i7 & 63;
        if (i9 != 0) {
            int i10 = iFor_setpk2_end_move_plus & 63;
            int i11 = geMSSEngine.NB_MONOMIAL_PK;
            if (i10 != 0) {
                if (((((i11 - i6) + 7) >>> 3) & 7) != 0) {
                    int i12 = (i5 - ((64 - (((i11 - i6) - geMSSEngine.HFEnvr) & 63)) & 63)) >>> 6;
                    pointer2.setRangePointerUnion_Check(pointerUnion2, i12, iFor_setpk2_end_move_plus);
                    pointer2.set(i12, pointerUnion2.getWithCheck(i12) >>> i10);
                    if (i12 < i8) {
                        int i13 = i12 + 1;
                        long withCheck = pointerUnion2.getWithCheck(i13);
                        pointer2.setXor(i12, withCheck << (64 - i10));
                        pointer2.set(i13, withCheck >>> i10);
                    } else if (i9 + i10 > 64) {
                        pointer2.setXor(i12, pointerUnion2.getWithCheck(i12 + 1) << (64 - i10));
                    }
                } else {
                    pointer2.setRangePointerUnion(pointerUnion2, i8, i10);
                    pointer2.set(i8, pointerUnion2.get(i8) >>> i10);
                    if (i9 + i10 > 64) {
                        pointer2.setXor(i8, pointerUnion2.get(i8 + 1) << (64 - i10));
                    }
                }
            } else if (((((i11 - i6) + 7) >>> 3) & 7) != 0) {
                pointer2.setRangePointerUnion(pointerUnion2, i8);
                pointer2.set(i8, pointerUnion2.getWithCheck(i8));
            } else {
                i8++;
                pointer2.setRangePointerUnion(pointerUnion2, i8);
            }
        } else if (i8 != 0) {
            int i14 = iFor_setpk2_end_move_plus & 63;
            if (i14 == 0) {
                pointer2.setRangePointerUnion(pointerUnion2, i8);
            } else if (((((geMSSEngine.NB_MONOMIAL_PK - i6) + 7) >>> 3) & 7) != 0) {
                int i15 = i8 - 1;
                pointer2.setRangePointerUnion(pointerUnion2, i15, i14);
                pointer2.set(i15, pointerUnion2.get(i15) >>> i14);
                pointer2.setXor(i15, pointerUnion2.getWithCheck(i8) << (64 - i14));
            } else {
                pointer2.setRangePointerUnion(pointerUnion2, i8, i14);
            }
        }
        return pointerUnion.get() & 1;
    }

    private long convMQ_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int iFor_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, this.HFEnvq);
        int i = this.HFEnvr;
        if (i != 0) {
            setPk2Value(pointer, pointerUnion2, iFor_setpk2_end_move_plus, this.HFEnvq, i + 1);
        }
        return pointerUnion.get() & 1;
    }

    private void convMonic_gf2nx(Pointer pointer, int i) {
        Pointer pointer2 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        inv_gf2n(pointer2, pointer, 0);
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        while (true) {
            i--;
            if (i == -1) {
                pointer.changeIndex(index);
                return;
            } else {
                pointer.move(-this.NB_WORD_GFqn);
                mul_gf2n(pointer, pointer, pointer2);
            }
        }
    }

    private void copy_for_casct(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, Pointer pointer5, int i, int i2) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        while (i > 1) {
            pointer4.changeIndex(pointer3, (i2 + i) * this.NB_WORD_GFqn);
            CMP_AND_SWAP_CST_TIME(pointer, pointer4, pointer5);
            i >>>= 1;
        }
    }

    private void copy_move_matrix_move(Pointer pointer, Pointer pointer2, int i) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer2, this.NB_WORD_GFqn, i);
        pointer2.move(this.NB_WORD_GFqn * (this.HFEv + 1));
    }

    private void div_q_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            int iSearchDegree = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (iSearchDegree < i2) {
                return;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * iSearchDegree);
            int iMax = Math.max(0, (i2 << 1) - iSearchDegree);
            pointer4.changeIndex(pointer, ((iSearchDegree - i2) + iMax) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, iMax, i2);
            i = iSearchDegree - 1;
        }
    }

    private void div_r_monic_cst_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        while (i >= i2) {
            pointer3.changeIndex(pointer, (-i2) * this.NB_WORD_GFqn);
            GeMSSEngine geMSSEngine = this;
            geMSSEngine.for_mul_rem_xor_move(pointer3, pointer, pointer2, 0, i2);
            pointer.move(-geMSSEngine.NB_WORD_GFqn);
            i--;
            this = geMSSEngine;
        }
        pointer.changeIndex(index);
    }

    private int div_r_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * i);
            pointer4.changeIndex(pointer3, (-i2) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, 0, i2);
            i--;
        }
        if (i == -1) {
            i++;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    private void divsqr_r_HFE_cstdeg_gf2nx(Pointer pointer, int i, int i2, int i3, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer(pointer, i * this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer();
        while (i2 >= i3) {
            pointer4.changeIndex(pointer3, (-this.HFEDeg) * this.NB_WORD_GFqn);
            mul_rem_xorrange(pointer4, pointer3, pointer2);
            for (int i4 = 1; i4 < this.NB_COEFS_HFEPOLY; i4++) {
                pointer4.move(complete_sparse_monic_gf2nxVar.L[i4]);
                mul_rem_xorrange(pointer4, pointer3, complete_sparse_monic_gf2nxVar.poly, this.NB_WORD_GFqn * i4);
            }
            pointer3.move(-this.NB_WORD_GFqn);
            i2--;
        }
    }

    private void dotProduct_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        int index = pointer2.getIndex();
        int index2 = pointer3.getIndex();
        mul_move(pointer4, pointer2, pointer3);
        for_mul_xorrange_move(pointer4, pointer2, pointer3, i - 1);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
        pointer3.changeIndex(index2);
    }

    private void dotproduct_move_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        dotProduct_gf2n(pointer, pointer3, pointer2, i);
        pointer.move(this.NB_WORD_GFqn);
        pointer2.move((i + this.HFEv + 1) * this.NB_WORD_GFqn);
    }

    private void evalMQShybrid8_uncomp_nocst_gf2_m(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion2);
        evalMQSnocst8_quo_gf2(pointer, pointer2, pointerUnion);
        if (this.HFEmr < 8) {
            pointer.set(this.HFEmq, 0L);
        }
        for (int i = this.HFEmr - this.HFEmr8; i < this.HFEmr; i++) {
            pointer.setXor(this.HFEmq, evalMQnocst_unrolled_no_simd_gf2(pointer2, pointerUnion3) << i);
            pointerUnion3.move(this.NB_WORD_UNCOMP_EQ);
        }
    }

    private void evalMQSnocst8_quo_gf2(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        GeMSSEngine geMSSEngine = this;
        Pointer pointer3 = pointer;
        int i6 = geMSSEngine.HFEnv;
        int i7 = geMSSEngine.HFEm;
        if ((i7 >>> 3) != 0) {
            i7 = (i7 >>> 3) << 3;
        }
        int i8 = i7;
        int i9 = (i8 & 7) != 0 ? (i8 >>> 3) + 1 : i8 >>> 3;
        int i10 = (i9 >>> 3) + ((i9 & 7) != 0 ? 1 : 0);
        int i11 = i6;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        System.arraycopy(pointerUnion2.getArray(), 0, pointer3.getArray(), pointer3.getIndex(), i10);
        pointerUnion2.moveNextBytes(i9);
        int i12 = 0;
        while (true) {
            i = geMSSEngine.HFEnvq;
            if (i12 >= i) {
                break;
            }
            int i13 = i11;
            long j = pointer2.get(i12);
            int i14 = 0;
            while (i14 < 64) {
                if ((j & 1) != 0) {
                    pointer3.setXorRange(0, pointerUnion2, 0, i10);
                    pointerUnion2.moveNextBytes(i9);
                    i3 = i10;
                    i4 = i9;
                    int i15 = i14;
                    i2 = i15;
                    geMSSEngine.LOOPJR_UNROLLED_64(pointer3, pointerUnion2, i15 + 1, 64, j >>> 1, i4, i3);
                    int i16 = i12 + 1;
                    while (true) {
                        i5 = geMSSEngine.HFEnvq;
                        if (i16 >= i5) {
                            break;
                        }
                        geMSSEngine.LOOPJR_UNROLLED_64(pointer, pointerUnion2, 0, 64, pointer2.get(i16), i4, i3);
                        i16++;
                    }
                    if (geMSSEngine.HFEnvr != 0) {
                        pointer3 = pointer;
                        geMSSEngine.choose_LOOPJR(pointer3, pointerUnion2, 0, pointer2.get(i5), i4, i3);
                        i4 = i4;
                    } else {
                        pointer3 = pointer;
                    }
                } else {
                    i2 = i14;
                    i3 = i10;
                    i4 = i9;
                    pointerUnion2.moveNextBytes(i13 * i4);
                }
                j >>>= 1;
                i14 = i2 + 1;
                i13--;
                i9 = i4;
                i10 = i3;
            }
            i12++;
            i10 = i10;
            i11 = i13;
        }
        int i17 = i10;
        int i18 = i9;
        if (geMSSEngine.HFEnvr != 0) {
            int i19 = i11;
            long j2 = pointer2.get(i);
            int i20 = 0;
            while (i20 < geMSSEngine.HFEnvr) {
                if ((j2 & 1) != 0) {
                    pointer3.setXorRange(0, pointerUnion2, 0, i17);
                    pointerUnion2.moveNextBytes(i18);
                    int i21 = i18;
                    geMSSEngine.choose_LOOPJR(pointer3, pointerUnion2, i20 + 1, j2 >>> 1, i21, i17);
                    i18 = i21;
                } else {
                    pointerUnion2.moveNextBytes(i19 * i18);
                }
                j2 >>>= 1;
                i20++;
                i19--;
                geMSSEngine = this;
            }
        }
        int i22 = i8 & 63;
        if (i22 != 0) {
            pointer3.setAnd(i17 - 1, (1 << i22) - 1);
        }
    }

    private long evalMQnocst_unrolled_no_simd_gf2(Pointer pointer, PointerUnion pointerUnion) {
        int i;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        long j = pointer.get();
        long dotProduct = 0;
        for (int i2 = 0; i2 < 64; i2++) {
            if ((1 & (j >>> i2)) != 0) {
                dotProduct ^= pointerUnion2.get(i2) & j;
            }
        }
        pointerUnion2.move(64);
        int i3 = 1;
        while (true) {
            int i4 = this.NB_WORD_GF2nv;
            if (i3 >= i4) {
                return GeMSSUtils.XORBITS_UINT(dotProduct);
            }
            int i5 = i3 + 1;
            if (i4 != i5 || (i = this.HFEnvr) == 0) {
                i = 64;
            }
            long j2 = pointer.get(i3);
            for (int i6 = 0; i6 < i; i6++) {
                if (((j2 >>> i6) & 1) != 0) {
                    dotProduct ^= pointerUnion2.getDotProduct(0, pointer, 0, i5);
                }
                pointerUnion2.move(i5);
            }
            i3 = i5;
        }
    }

    private void findRootsSplit_gf2nx(Pointer pointer, Pointer pointer2, int i) {
        int i2;
        int iGcd_gf2nx;
        int i3;
        if (i == 1) {
            pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
            return;
        }
        if ((this.HFEn & 1) != 0 && i == 2) {
            findRootsSplit2_HT_gf2nx(pointer, pointer2);
            return;
        }
        int i4 = (i << 1) - 1;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn * i4);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn * i);
        int i5 = i + 1;
        Pointer pointer5 = new Pointer(this.NB_WORD_GFqn * i5);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
        while (true) {
            pointer3.setRangeClear(0, this.NB_WORD_GFqn * i4);
            pointer4.setRangeClear(0, this.NB_WORD_GFqn * i);
            do {
                pointer4.fillRandom(this.NB_WORD_GFqn, this.random, this.NB_BYTES_GFqn);
                pointer4.setAnd((this.NB_WORD_GFqn << 1) - 1, this.MASK_GF2n);
                i2 = this.NB_WORD_GFqn;
            } while (pointer4.is0_gf2n(i2, i2) != 0);
            pointer5.copyFrom(pointer2, this.NB_WORD_GFqn * i5);
            traceMap_gf2nx(pointer4, pointer3, pointer5, i);
            iGcd_gf2nx = gcd_gf2nx(pointer5, i, pointer4, pointer4.searchDegree(i - 1, 1, this.NB_WORD_GFqn));
            i3 = this.buffer;
            if (iGcd_gf2nx != 0 && iGcd_gf2nx != i) {
                break;
            }
        }
        if (i3 != 0) {
            pointer4.swap(pointer5);
        }
        inv_gf2n(pointer6, pointer5, this.NB_WORD_GFqn * iGcd_gf2nx);
        int i6 = this.NB_WORD_GFqn;
        pointer5.set1_gf2n(iGcd_gf2nx * i6, i6);
        for_mul(pointer5, pointer6, iGcd_gf2nx - 1);
        div_q_monic_gf2nx(pointer2, i, pointer5, iGcd_gf2nx);
        findRootsSplit_gf2nx(pointer, pointer5, iGcd_gf2nx);
        findRootsSplit_gf2nx(new Pointer(pointer, this.NB_WORD_GFqn * iGcd_gf2nx), new Pointer(pointer2, this.NB_WORD_GFqn * iGcd_gf2nx), i - iGcd_gf2nx);
    }

    private void for_and_xor_shift_incre_move(Pointer pointer, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            pointer.setAnd(j);
            pointer.setXor(1 << i3);
            j = (j << 1) + 1;
            pointer.move(i);
        }
    }

    private void for_casct_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i2;
        int i4 = 0;
        while (i4 < i) {
            CMP_AND_SWAP_CST_TIME(pointer, pointer2, pointer3);
            pointer.move(i3);
            pointer2.move(i3);
            i4 += i2;
        }
    }

    private void for_copy_move(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar) {
        for (int i = 1; i < this.NB_COEFS_HFEPOLY; i++) {
            pointer.move(complete_sparse_monic_gf2nxVar.L[i]);
            Pointer pointer2 = complete_sparse_monic_gf2nxVar.poly;
            int i2 = this.NB_WORD_GFqn;
            pointer.copyFrom(0, pointer2, i * i2, i2);
        }
    }

    private void for_mul(Pointer pointer, Pointer pointer2, int i) {
        Pointer pointer3 = new Pointer(pointer, this.NB_WORD_GFqn * i);
        while (i != -1) {
            mul_gf2n(pointer3, pointer3, pointer2);
            pointer3.move(-this.NB_WORD_GFqn);
            i--;
        }
    }

    private void for_mul_rem_xor_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i;
        while (i < i2) {
            mul_rem_xorrange(pointer, pointer2, pointer3, i3);
            pointer.move(this.NB_WORD_GFqn);
            i++;
            i3 += this.NB_WORD_GFqn;
        }
    }

    private int for_setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        bArr[i] = (byte) (bArr2[i2] & 3);
        int pk = 2;
        for (int i4 = 2; i4 < i3; i4++) {
            int i5 = this.HFEnv;
            pk = setPK(bArr, bArr2, i4, i, i2, pk, i5 - 1, i5 - i4);
        }
        return pk;
    }

    private int for_setpk2_end_move_plus(Pointer pointer, PointerUnion pointerUnion, int i) {
        int i2 = 1;
        int i3 = 0;
        while (i3 < i) {
            GeMSSEngine geMSSEngine = this;
            Pointer pointer2 = pointer;
            int pk2Value = geMSSEngine.setPk2Value(pointer2, pointerUnion, i2, i3, 64);
            geMSSEngine.setPk2_endValue(pointer2, pointerUnion, pk2Value, i3);
            i3++;
            pointerUnion.move(i3);
            pointer2.move(i3);
            i2 = pk2Value + (i3 << 6);
            this = geMSSEngine;
            pointer = pointer2;
        }
        return i2;
    }

    private void for_sqr_divsqr(Pointer pointer, int i, int i2, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        while (i < i2) {
            sqr_gf2nx(pointer, this.HFEDeg - 1);
            int i3 = this.HFEDeg;
            divsqr_r_HFE_cstdeg_gf2nx(pointer, (i3 - 1) << 1, (i3 - 1) << 1, i3, complete_sparse_monic_gf2nxVar, pointer2);
            i++;
        }
    }

    private void frobeniusMap_multisqr_HFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer(this.HFEDeg * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer(((this.KX * this.HFEDeg) + this.POW_II) * this.NB_WORD_GFqn);
        int i = (this.POW_II * this.KP) - this.HFEDeg;
        Pointer pointer7 = new Pointer(pointer6, this.NB_WORD_GFqn * i);
        pointer7.copyFrom(pointer2, this.NB_WORD_GFqn);
        for_copy_move(pointer7, complete_sparse_monic_gf2nxVar);
        int i2 = i - 1;
        divsqr_r_HFE_cstdeg_gf2nx(pointer6, this.HFEDeg + i2, i2, 0, complete_sparse_monic_gf2nxVar, pointer2);
        int i3 = this.KP + 1;
        while (true) {
            int i4 = this.HFEDeg;
            if (i3 >= i4) {
                break;
            }
            pointer7.changeIndex(pointer6, i4 * this.NB_WORD_GFqn);
            pointer7.setRangeClear(0, this.POW_II * this.NB_WORD_GFqn);
            int i5 = this.POW_II;
            int i6 = this.NB_WORD_GFqn;
            pointer7.copyFrom(i5 * i6, pointer6, 0, this.HFEDeg * i6);
            pointer6.changeIndex(pointer7);
            int i7 = this.POW_II;
            divsqr_r_HFE_cstdeg_gf2nx(pointer6, (i7 - 1) + this.HFEDeg, i7 - 1, 0, complete_sparse_monic_gf2nxVar, pointer2);
            i3++;
        }
        pointer6.indexReset();
        int i8 = (1 << this.HFEDegI) - this.KP;
        int i9 = this.HFEDeg;
        int i10 = this.NB_WORD_GFqn;
        pointer.copyFrom(0, pointer6, i8 * i9 * i10, i9 * i10);
        int i11 = 0;
        while (true) {
            int i12 = this.HFEn;
            int i13 = this.HFEDegI;
            int i14 = this.II;
            if (i11 >= ((i12 - i13) - i14) / i14) {
                for_sqr_divsqr(pointer, 0, (i12 - i13) % i14, complete_sparse_monic_gf2nxVar, pointer2);
                return;
            }
            loop_sqr(pointer4, pointer);
            for (int i15 = 1; i15 < this.II; i15++) {
                loop_sqr(pointer4, pointer4);
            }
            pointer5.changeIndex(pointer4, this.KP * this.NB_WORD_GFqn);
            pointer7.changeIndex(pointer6);
            pointer3.changeIndex(pointer);
            for (int i16 = 0; i16 < this.HFEDeg; i16++) {
                mul_gf2n(pointer3, pointer7, pointer5);
                pointer3.move(this.NB_WORD_GFqn);
                pointer7.move(this.NB_WORD_GFqn);
            }
            for (int i17 = this.KP + 1; i17 < this.HFEDeg; i17++) {
                pointer5.move(this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer);
                for (int i18 = 0; i18 < this.HFEDeg; i18++) {
                    mul_rem_xorrange(pointer3, pointer7, pointer5);
                    pointer3.move(this.NB_WORD_GFqn);
                    pointer7.move(this.NB_WORD_GFqn);
                }
            }
            for (int i19 = 0; i19 < this.KP; i19++) {
                int i20 = this.POW_II * i19;
                int i21 = this.NB_WORD_GFqn;
                pointer.setXorRange(i20 * i21, pointer4, i19 * i21, i21);
            }
            i11++;
        }
    }

    private int gcd_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        int iDiv_r_monic_gf2nx;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        this.buffer = 0;
        int i3 = i;
        Pointer pointer4 = pointer;
        Pointer pointer5 = pointer2;
        while (true) {
            int i4 = i3;
            if (i2 == 0) {
                return i4;
            }
            if ((i2 << 1) > i4) {
                iDiv_r_monic_gf2nx = div_r_gf2nx(pointer4, i4, pointer5, i2);
            } else {
                inv_gf2n(pointer3, pointer5, this.NB_WORD_GFqn * i2);
                int i5 = this.NB_WORD_GFqn;
                pointer5.set1_gf2n(i2 * i5, i5);
                for_mul(pointer5, pointer3, i2 - 1);
                iDiv_r_monic_gf2nx = div_r_monic_gf2nx(pointer4, i4, pointer5, i2);
            }
            this.buffer = 1 - this.buffer;
            Pointer pointer6 = pointer4;
            pointer4 = pointer5;
            pointer5 = pointer6;
            i3 = i2;
            i2 = iDiv_r_monic_gf2nx;
        }
    }

    private void getSHA3Hash(Pointer pointer, int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2) {
        this.sha3Digest.update(bArr, i3, i4);
        this.sha3Digest.doFinal(bArr2, 0);
        pointer.fill(i, bArr2, 0, i2);
    }

    private void initListDifferences_gf2nx(int[] iArr) {
        iArr[1] = this.NB_WORD_GFqn;
        int arrayL = 2;
        int i = 0;
        while (i < this.HFEDegI) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i) + 1 <= this.HFE_odd_degree) {
                iArr[arrayL] = this.NB_WORD_GFqn;
                arrayL = setArrayL(iArr, arrayL + 1, 0, i);
            } else {
                if (i != 0) {
                    iArr[arrayL] = this.NB_WORD_GFqn << 1;
                    arrayL++;
                }
                arrayL = setArrayL(iArr, arrayL, 1, i);
            }
            i++;
        }
        int i2 = this.HFEDegJ;
        if (i2 != 0) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i) + 1 <= this.HFE_odd_degree) {
                iArr[arrayL] = this.NB_WORD_GFqn;
                setArrayL(iArr, arrayL + 1, 0, i2 - 1);
            } else {
                iArr[arrayL] = this.NB_WORD_GFqn << 1;
                setArrayL(iArr, arrayL + 1, 1, i2 - 1);
            }
        }
    }

    private void inv_gf2n(Pointer pointer, Pointer pointer2, int i) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        for (int i2 = this.HFEn_1rightmost - 1; i2 != -1; i2--) {
            int i3 = (this.HFEn - 1) >>> (i2 + 1);
            sqr_gf2n(pointer3, pointer);
            for (int i4 = 1; i4 < i3; i4++) {
                sqr_gf2n(pointer3, pointer3);
            }
            mul_gf2n(pointer, pointer, pointer3);
            if ((((this.HFEn - 1) >>> i2) & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                mul_gf2n(pointer, pointer2, pointer3);
            }
        }
        sqr_gf2n(pointer, pointer);
        pointer2.changeIndex(index);
    }

    private void loop_sqr(Pointer pointer, Pointer pointer2) {
        for (int i = 0; i < this.HFEDeg; i++) {
            int i2 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i * i2, pointer2, i2 * i);
        }
    }

    private int loop_xor_loop_move_xorandmask_move(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        while (i6 < i3) {
            pointer.setXor(i2, 1 << i6);
            pointer2.changeIndex(pointer);
            pointer3.changeIndex(pointer4);
            for (int i7 = i; i7 < i4; i7++) {
                pointer2.move(i5);
                pointer3.move((i7 >>> 6) + 1);
                pointer2.setXorRangeAndMask(pointer, i2 + 1, -((pointer3.get() >>> i6) & 1));
            }
            pointer.move(i5);
            pointer4.move(i2 + 1);
            i6++;
            i++;
        }
        return i;
    }

    private void mulMatricesLU_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        int i3;
        boolean z;
        int index = pointer.getIndex();
        int i4 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        if (i4 == 1) {
            int i5 = this.HFEnq;
            i = this.HFEnr;
            i2 = i5;
            i3 = 1;
            z = true;
        } else {
            if (i4 != 2) {
                w01.a("Invalid parameter for MULMATRICESLU_GF2");
                return;
            }
            int i6 = this.HFEnvq;
            i = this.HFEnvr;
            i2 = i6;
            i3 = 1;
            z = i != 0;
        }
        int i7 = i;
        Pointer pointer4 = new Pointer(pointer2);
        int i8 = i3;
        while (true) {
            int i9 = i7;
            if (i8 > i2) {
                LOOPIR(pointer, pointer4, pointer3, i7, i2, i9, i8, z);
                pointer.changeIndex(index);
                return;
            } else {
                LOOPIR(pointer, pointer4, pointer3, 64, i2, i9, i8, z);
                i8++;
                i7 = i9;
            }
        }
    }

    private void precSignHFE(SecretKeyHFE secretKeyHFE, Pointer[] pointerArr, byte[] bArr) {
        precSignHFESeed(secretKeyHFE, bArr);
        initListDifferences_gf2nx(secretKeyHFE.F_struct.L);
        Pointer pointer = new Pointer(secretKeyHFE.F_HFEv);
        Pointer pointer2 = new Pointer(this.NB_COEFS_HFEPOLY * this.NB_WORD_GFqn);
        Pointer pointer3 = new Pointer(pointer, this.MQv_GFqn_SIZE);
        pointerArr[0] = pointer3;
        pointer.changeIndex(pointer3, this.MLv_GFqn_SIZE);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * 2);
        int i = 0;
        while (true) {
            int i2 = 1;
            if (i >= this.HFEDegI) {
                break;
            }
            if ((1 << i) + 1 <= this.HFE_odd_degree || !this.ENABLED_REMOVE_ODD_DEGREE) {
                i2 = 0;
            }
            int i3 = i - i2;
            pointer4.copyFrom(pointer, this.NB_WORD_GFqn * i3);
            pointer.move(this.NB_WORD_GFqn * i3);
            pointer4.move(i3 * this.NB_WORD_GFqn);
            i++;
            pointerArr[i] = new Pointer(pointer);
            pointer.move(this.MLv_GFqn_SIZE);
            pointer4.move(this.NB_WORD_GFqn);
        }
        int i4 = this.HFEDegJ;
        if (i4 != 0) {
            pointer4.copyFrom(pointer, (i4 - ((1 << i) + 1 > this.HFE_odd_degree ? 1 : 0)) * this.NB_WORD_GFqn);
        }
        secretKeyHFE.F_struct.poly = new Pointer(pointer2);
    }

    private void precSignHFESeed(SecretKeyHFE secretKeyHFE, byte[] bArr) {
        int i = this.NB_UINT_HFEVPOLY + ((this.LTRIANGULAR_NV_SIZE + this.LTRIANGULAR_N_SIZE) << 1);
        secretKeyHFE.sk_uncomp = new Pointer(this.MATRIXnv_SIZE + i + this.MATRIXn_SIZE);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(this.ShakeBitStrength);
        sHAKEDigest.update(bArr, 0, this.SIZE_SEED_SK);
        int i2 = i << 3;
        byte[] bArr2 = new byte[i2];
        sHAKEDigest.doFinal(bArr2, 0, i2);
        secretKeyHFE.sk_uncomp.fill(0, bArr2, 0, i2);
        Pointer pointer = new Pointer(secretKeyHFE.sk_uncomp, i);
        secretKeyHFE.S = pointer;
        secretKeyHFE.T = new Pointer(pointer, this.MATRIXnv_SIZE);
        Pointer pointer2 = new Pointer(secretKeyHFE.sk_uncomp);
        secretKeyHFE.F_HFEv = pointer2;
        cleanMonicHFEv_gf2nx(pointer2);
        Pointer pointer3 = new Pointer(secretKeyHFE.sk_uncomp, this.NB_UINT_HFEVPOLY);
        Pointer pointer4 = new Pointer(pointer3, this.LTRIANGULAR_NV_SIZE);
        FunctionParams functionParams = FunctionParams.NV;
        cleanLowerMatrix(pointer3, functionParams);
        cleanLowerMatrix(pointer4, functionParams);
        mulMatricesLU_gf2(secretKeyHFE.S, pointer3, pointer4, functionParams);
        pointer3.move(this.LTRIANGULAR_NV_SIZE << 1);
        pointer4.changeIndex(pointer3, this.LTRIANGULAR_N_SIZE);
        FunctionParams functionParams2 = FunctionParams.N;
        cleanLowerMatrix(pointer3, functionParams2);
        cleanLowerMatrix(pointer4, functionParams2);
        mulMatricesLU_gf2(secretKeyHFE.T, pointer3, pointer4, functionParams2);
    }

    private void rem_gf2n(Pointer pointer, int i, Pointer pointer2) {
        this.rem.rem_gf2n(pointer.array, i + pointer.getIndex(), pointer2.array);
    }

    private static long remainderUnsigned(long j, long j2) {
        return (j <= 0 || j2 <= 0) ? new BigInteger(1, Pack.longToBigEndian(j)).mod(new BigInteger(1, Pack.longToBigEndian(j2))).longValue() : j % j2;
    }

    private int setArrayL(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            iArr[i] = this.NB_WORD_GFqn << i2;
            i2++;
            i++;
        }
        return i;
    }

    private int setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        while (i5 >= i6) {
            int i7 = (i4 >>> 3) + i2;
            bArr[i7] = (byte) (bArr[i7] ^ (((bArr2[(i >>> 3) + i3] >>> (i & 7)) & 1) << (i4 & 7)));
            i += i5;
            i5--;
            i4++;
        }
        this.buffer = i;
        return i4;
    }

    private int setPk2Value(Pointer pointer, PointerUnion pointerUnion, int i, int i2, int i3) {
        for (int i4 = 1; i4 < i3; i4++) {
            int i5 = i & 63;
            if (i5 != 0) {
                pointer.setRangePointerUnion(pointerUnion, i2, i5);
                pointer.set(i2, pointerUnion.get(i2) >>> i5);
                int i6 = i5 + i4;
                if (i6 > 64) {
                    pointer.setXor(i2, pointerUnion.get(i2 + 1) << (64 - i5));
                }
                if (i6 >= 64) {
                    pointerUnion.moveIncremental();
                }
            } else {
                pointer.setRangePointerUnion(pointerUnion, i2 + 1);
            }
            pointerUnion.move(i2);
            pointer.setAnd(i2, (1 << i4) - 1);
            pointer.move(i2 + 1);
            i += (i2 << 6) + i4;
        }
        return i;
    }

    private void setPk2_endValue(Pointer pointer, PointerUnion pointerUnion, int i, int i2) {
        int i3 = i & 63;
        int i4 = i2 + 1;
        if (i3 != 0) {
            pointer.setRangePointerUnion(pointerUnion, i4, i3);
        } else {
            pointer.setRangePointerUnion(pointerUnion, i4);
        }
    }

    private void special_buffer(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        int i;
        int index = pointer2.getIndex();
        pointer2.move((this.NB_WORD_GFqn * (this.HFEv + 1)) << 1);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
        int i2 = 2;
        while (i2 < this.SIZE_ROW - 1) {
            copy_move_matrix_move(pointer, pointer4, i2 - 1);
            i2++;
        }
        if (this.ENABLED_REMOVE_ODD_DEGREE) {
            while (i2 < this.SIZE_ROW - 1) {
                copy_move_matrix_move(pointer, pointer4, i2 - 2);
                i2++;
            }
        }
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer4, this.NB_WORD_GFqn, this.HFEDegJ);
        for (int i3 = 0; i3 < this.HFEn - 1; i3++) {
            mul_gf2n(pointer, pointer3, pointer2);
            pointer.move(this.NB_WORD_GFqn);
            pointer4.changeIndex(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
            int i4 = 2;
            while (i4 < this.HFEDegI) {
                dotproduct_move_move(pointer, pointer4, pointer3, i4);
                i4++;
            }
            if (this.ENABLED_REMOVE_ODD_DEGREE) {
                pointer3.move(this.NB_WORD_GFqn);
                while (i4 < this.SIZE_ROW - 1) {
                    dotproduct_move_move(pointer, pointer4, pointer3, i4 - 1);
                    i4++;
                }
                pointer3.move(-this.NB_WORD_GFqn);
            }
            int i5 = this.HFEDegJ;
            if (i5 == 0) {
                pointer.copyFrom(pointer3, this.NB_WORD_GFqn);
                pointer.move(this.NB_WORD_GFqn);
                i = this.SIZE_ROW;
            } else {
                dotProduct_gf2n(pointer, pointer3, pointer4, i5);
                pointer3.move(this.HFEDegJ * this.NB_WORD_GFqn);
                pointer.setXorRange_SelfMove(pointer3, this.NB_WORD_GFqn);
                i = this.SIZE_ROW - this.HFEDegJ;
            }
            pointer3.move(i * this.NB_WORD_GFqn);
        }
        pointer.indexReset();
        pointer2.changeIndex(index);
        pointer3.indexReset();
    }

    private void sqr_gf2n(Pointer pointer, Pointer pointer2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, pointer2.cp);
        this.rem.rem_gf2n(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    private void sqr_gf2nx(Pointer pointer, int i) {
        int i2 = this.NB_WORD_GFqn * i;
        int index = pointer.getIndex();
        pointer.move(i2);
        Pointer pointer2 = new Pointer(pointer, i2);
        for (int i3 = 0; i3 < i; i3++) {
            sqr_gf2n(pointer2, pointer);
            pointer.move(-this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
            pointer2.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
        }
        sqr_gf2n(pointer, pointer);
        pointer.changeIndex(index);
    }

    private void traceMap_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int i2;
        int i3 = 1;
        while (true) {
            i2 = 1 << i3;
            if (i2 >= i) {
                break;
            }
            int i4 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i4 << i3, pointer, i4 << (i3 - 1));
            i3++;
        }
        if (i3 < this.HFEn) {
            int i5 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer2, i5 << i3, pointer, i5 << (i3 - 1));
            div_r_monic_cst_gf2nx(pointer2, i2, pointer3, i);
            pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            for (int i6 = i3 + 1; i6 < this.HFEn; i6++) {
                int i7 = i - 1;
                sqr_gf2nx(pointer2, i7);
                div_r_monic_cst_gf2nx(pointer2, i7 << 1, pointer3, i);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            }
        }
    }

    private void uncompress_signHFE(Pointer pointer, byte[] bArr) {
        PointerUnion pointerUnion = new PointerUnion(pointer);
        int i = (1 << this.HFEnvr8) - 1;
        pointerUnion.fillBytes(0, bArr, 0, this.NB_BYTES_GFqnv);
        if (this.HFEnvr8 != 0) {
            pointerUnion.setAndByte(this.NB_BYTES_GFqnv - 1, i);
        }
        int i2 = this.HFEnv;
        pointerUnion.moveNextBytes((this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7));
        for (int i3 = 1; i3 < this.NB_ITE; i3++) {
            int i4 = i2 & 7;
            int iMin = Math.min(this.HFEDELTA + this.HFEv, (8 - i4) & 7);
            if (i4 != 0) {
                pointerUnion.setXorByte(((bArr[i2 >>> 3] & UByte.MAX_VALUE) >>> i4) << this.HFEmr8);
                int i5 = iMin - this.VAL_BITS_M;
                if (i5 >= 0) {
                    pointerUnion.moveNextByte();
                }
                if (i5 > 0) {
                    int i6 = i2 + this.VAL_BITS_M;
                    pointerUnion.setXorByte((bArr[i6 >>> 3] & UByte.MAX_VALUE) >>> (i6 & 7));
                    i2 = i6 + i5;
                } else {
                    i2 += iMin;
                }
            }
            int i7 = (this.HFEDELTA + this.HFEv) - iMin;
            int i8 = (this.HFEm + iMin) & 7;
            if (i8 != 0) {
                for (int i9 = 0; i9 < ((i7 - 1) >>> 3); i9++) {
                    int i10 = i2 >>> 3;
                    pointerUnion.setXorByte((bArr[i10] & UByte.MAX_VALUE) << i8);
                    pointerUnion.moveNextByte();
                    pointerUnion.setXorByte((bArr[i10] & UByte.MAX_VALUE) >>> (8 - i8));
                    i2 += 8;
                }
                int i11 = i2 >>> 3;
                pointerUnion.setXorByte((bArr[i11] & UByte.MAX_VALUE) << i8);
                pointerUnion.moveNextByte();
                int i12 = ((i7 + 7) & 7) + 1;
                int i13 = 8 - i8;
                if (i12 > i13) {
                    pointerUnion.setByte((bArr[i11] & UByte.MAX_VALUE) >>> i13);
                    pointerUnion.moveNextByte();
                }
                i2 += i12;
            } else {
                for (int i14 = 0; i14 < ((i7 + 7) >>> 3); i14++) {
                    pointerUnion.setByte(bArr[i2 >>> 3]);
                    i2 += 8;
                    pointerUnion.moveNextByte();
                }
                i2 -= (8 - (i7 & 7)) & 7;
            }
            if (this.HFEnvr8 != 0) {
                pointerUnion.setAndByte(-1, i);
            }
            pointerUnion.moveNextBytes(((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7));
        }
    }

    private void vmpv_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        vecMatProduct(pointer, pointer2, new Pointer(pointer3, this.NB_WORD_GFqn), FunctionParams.V);
        pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
        pointer3.move(this.MLv_GFqn_SIZE);
    }

    public void changeVariablesMQS64_gf2(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer();
        int i = this.HFEnv;
        Pointer pointer4 = new Pointer(i * i * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer, this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(pointer4);
        Pointer pointer7 = new Pointer(pointer2);
        int i2 = 0;
        for (int i3 = 0; i3 < this.HFEnv; i3++) {
            pointer3.changeIndex(pointer5);
            for (int i4 = 0; i4 < this.HFEnvq; i4++) {
                for (int i5 = 0; i5 < 64; i5++) {
                    Pointer pointer8 = pointer6;
                    Pointer pointer9 = pointer3;
                    LOOPKR(pointer9, pointer8, pointer7.get() >>> i5, i5, 64);
                    pointer3 = pointer9;
                    pointer6 = pointer8;
                    LOOPK_COMPLETE(pointer6, pointer7, pointer3, 1, this.HFEnvq - i4);
                }
                pointer7.moveIncremental();
            }
            if (this.HFEnvr != 0) {
                for (int i6 = 0; i6 < this.HFEnvr; i6++) {
                    Pointer pointer10 = pointer6;
                    Pointer pointer11 = pointer3;
                    LOOPKR(pointer11, pointer10, pointer7.get() >>> i6, i6, this.HFEnvr);
                    pointer3 = pointer11;
                    pointer6 = pointer10;
                    pointer6.move(this.NB_WORD_GFqn);
                }
                pointer7.moveIncremental();
            }
        }
        pointer5.changeIndex(pointer4);
        pointer6.changeIndex(pointer, this.NB_WORD_GFqn);
        Pointer pointer12 = new Pointer(pointer2);
        int i7 = 0;
        while (i7 < this.HFEnvq) {
            int i8 = 0;
            while (i8 < 64) {
                pointer7.changeIndex(pointer12);
                Pointer pointer13 = pointer3;
                Pointer pointer14 = pointer5;
                Pointer pointer15 = pointer7;
                LOOPIR_INIT(pointer6, pointer13, pointer14, pointer15, i8, 64);
                Pointer pointer16 = pointer6;
                Pointer pointer17 = pointer14;
                pointer3 = pointer13;
                int i9 = i8;
                for (int i10 = i7 + 1; i10 < this.HFEnvq; i10++) {
                    Pointer pointer18 = pointer3;
                    Pointer pointer19 = pointer17;
                    Pointer pointer20 = pointer16;
                    LOOPIR_INIT(pointer20, pointer18, pointer19, pointer15, 0, 64);
                    pointer16 = pointer20;
                    pointer17 = pointer19;
                    pointer3 = pointer18;
                }
                int i11 = this.HFEnvr;
                if (i11 != 0) {
                    Pointer pointer21 = pointer3;
                    Pointer pointer22 = pointer17;
                    Pointer pointer23 = pointer16;
                    LOOPIR_INIT(pointer23, pointer21, pointer22, pointer15, 0, i11);
                    pointer16 = pointer23;
                    pointer17 = pointer22;
                    pointer3 = pointer21;
                }
                pointer17.changeIndex(pointer3);
                pointer12.move(this.NB_WORD_GF2nv);
                i8 = i9 + 1;
                pointer5 = pointer17;
                pointer6 = pointer16;
                pointer7 = pointer15;
            }
            i7++;
            pointer6 = pointer6;
        }
        Pointer pointer24 = pointer6;
        Pointer pointer25 = pointer5;
        Pointer pointer26 = pointer7;
        if (this.HFEnvr != 0) {
            int i12 = 0;
            while (i12 < this.HFEnvr) {
                pointer26.changeIndex(pointer12);
                pointer3.changeIndex(pointer25);
                Pointer pointer27 = pointer3;
                Pointer pointer28 = pointer25;
                Pointer pointer29 = pointer24;
                LOOPIR_INIT(pointer29, pointer27, pointer28, pointer26, i12, this.HFEnvr);
                pointer3 = pointer27;
                pointer28.changeIndex(pointer3);
                pointer12.move(this.NB_WORD_GF2nv);
                i12++;
                pointer24 = pointer29;
                pointer25 = pointer28;
            }
        }
        Pointer pointer30 = pointer25;
        Pointer pointer31 = pointer24;
        pointer30.changeIndex(pointer4);
        pointer31.changeIndex(pointer, this.NB_WORD_GFqn);
        pointer26.changeIndex(pointer2);
        for (int i13 = 0; i13 < this.HFEnvq; i13++) {
            int i14 = 0;
            while (i14 < 64) {
                pointer31.move(this.NB_WORD_GFqn);
                pointer30.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer30);
                int i15 = i14 + 1;
                Pointer pointer32 = pointer26;
                LOOPIR_LOOPK_COMPLETE(pointer31, pointer32, pointer3, i15, 64);
                pointer26 = pointer32;
                for (int i16 = i13 + 1; i16 < this.HFEnvq; i16++) {
                    Pointer pointer33 = pointer26;
                    LOOPIR_LOOPK_COMPLETE(pointer31, pointer33, pointer3, 0, 64);
                    pointer26 = pointer33;
                }
                int i17 = this.HFEnvr;
                if (i17 != 0) {
                    Pointer pointer34 = pointer26;
                    LOOPIR_LOOPK_COMPLETE(pointer31, pointer34, pointer3, 0, i17);
                    pointer26 = pointer34;
                }
                pointer26.move(this.NB_WORD_GF2nv);
                i14 = i15;
            }
        }
        if (this.HFEnvr != 0) {
            while (i2 < this.HFEnvr - 1) {
                pointer31.move(this.NB_WORD_GFqn);
                pointer30.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer30);
                i2++;
                Pointer pointer35 = pointer26;
                LOOPIR_LOOPK_COMPLETE(pointer31, pointer35, pointer3, i2, this.HFEnvr);
                pointer35.move(this.NB_WORD_GF2nv);
                pointer26 = pointer35;
            }
        }
        pointer.indexReset();
        pointer2.indexReset();
    }

    public void cleanLowerMatrix(Pointer pointer, FunctionParams functionParams) {
        int i;
        int i2;
        int i3 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        int i4 = 1;
        if (i3 == 1) {
            i = this.HFEnq;
            i2 = this.HFEnr;
        } else if (i3 != 2) {
            w01.a("");
            return;
        } else {
            i = this.HFEnvq;
            i2 = this.HFEnvr;
        }
        Pointer pointer2 = new Pointer(pointer);
        while (i4 <= i) {
            for_and_xor_shift_incre_move(pointer2, i4, 64);
            pointer2.moveIncremental();
            i4++;
        }
        for_and_xor_shift_incre_move(pointer2, i4, i2);
    }

    public void cleanMonicHFEv_gf2nx(Pointer pointer) {
        int i = this.NB_WORD_GFqn - 1;
        while (i < this.NB_UINT_HFEVPOLY) {
            pointer.setAnd(i, this.MASK_GF2n);
            i += this.NB_WORD_GFqn;
        }
    }

    public void compress_signHFE(byte[] bArr, Pointer pointer) {
        int i;
        byte[] bytes = pointer.toBytes(pointer.getLength() << 3);
        System.arraycopy(bytes, 0, bArr, 0, this.NB_BYTES_GFqnv);
        int i2 = this.HFEnv;
        int i3 = (this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7);
        for (int i4 = 1; i4 < this.NB_ITE; i4++) {
            int i5 = i2 & 7;
            int iMin = Math.min(this.HFEDELTA + this.HFEv, (8 - i5) & 7);
            if (i5 != 0) {
                int i6 = this.HFEmr8;
                if (i6 != 0) {
                    int i7 = i2 >>> 3;
                    bArr[i7] = (byte) ((((bytes[i3] & UByte.MAX_VALUE) >>> i6) << i5) ^ bArr[i7]);
                    int i8 = this.VAL_BITS_M;
                    int i9 = iMin - i8;
                    if (i9 >= 0) {
                        i3++;
                    }
                    if (i9 > 0) {
                        int i10 = i2 + i8;
                        int i11 = i10 >>> 3;
                        bArr[i11] = (byte) (bArr[i11] ^ ((bytes[i3] & UByte.MAX_VALUE) << (i10 & 7)));
                        i2 = i10 + i9;
                    }
                } else {
                    int i12 = i2 >>> 3;
                    bArr[i12] = (byte) (((bytes[i3] & UByte.MAX_VALUE) << i5) ^ bArr[i12]);
                }
                i2 += iMin;
            }
            int i13 = (this.HFEDELTA + this.HFEv) - iMin;
            int i14 = (this.HFEm + iMin) & 7;
            if (i14 != 0) {
                for (int i15 = 0; i15 < ((i13 - 1) >>> 3); i15++) {
                    int i16 = (bytes[i3] & UByte.MAX_VALUE) >>> i14;
                    i3++;
                    bArr[i2 >>> 3] = (byte) (i16 ^ ((bytes[i3] & UByte.MAX_VALUE) << (8 - i14)));
                    i2 += 8;
                }
                int i17 = i2 >>> 3;
                i = i3 + 1;
                byte b = (byte) ((bytes[i3] & UByte.MAX_VALUE) >>> i14);
                bArr[i17] = b;
                int i18 = ((i13 + 7) & 7) + 1;
                int i19 = 8 - i14;
                if (i18 > i19) {
                    bArr[i17] = (byte) (((byte) ((bytes[i] & UByte.MAX_VALUE) << i19)) ^ b);
                    i = i3 + 2;
                }
                i2 += i18;
            } else {
                int i20 = 0;
                while (i20 < ((i13 + 7) >>> 3)) {
                    bArr[i2 >>> 3] = bytes[i3];
                    i2 += 8;
                    i20++;
                    i3++;
                }
                i2 -= (8 - (i13 & 7)) & 7;
                i = i3;
            }
            i3 = ((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7) + i;
        }
    }

    public void convMQS_one_eq_to_hybrid_rep8_comp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        convMQ_UL_gf2(bArr, bArr2, this.HFEmr8);
        int bytesMove = 0;
        for (int i = 0; i < this.NB_MONOMIAL_PK; i++) {
            bytesMove = pointerUnion.toBytesMove(bArr, bytesMove, this.HFEmq8);
            if (this.HFEmr8 != 0) {
                pointerUnion.moveNextByte();
            }
        }
    }

    public void convMQS_one_eq_to_hybrid_rep8_uncomp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        int i = this.HFEmr8 - 1;
        convMQ_UL_gf2(bArr, bArr2, i);
        int i2 = this.ACCESS_last_equations8;
        int i3 = this.NB_BYTES_EQUATION;
        int i4 = i2 + (i * i3);
        int i5 = i3 * i;
        int iFor_setPK = for_setPK(bArr, bArr2, i4, i5, this.HFEnv);
        int i6 = this.HFEnv;
        setPK(bArr, bArr2, i6, i4, i5, iFor_setPK, i6 - 1, this.LOST_BITS);
        int i7 = this.buffer;
        long j = 0;
        for (int i8 = this.LOST_BITS - 1; i8 >= 0; i8--) {
            j ^= ((long) ((bArr2[(i7 >>> 3) + i5] >>> (i7 & 7)) & 1)) << ((this.LOST_BITS - 1) - i8);
            i7 += i8;
        }
        int i9 = this.ACCESS_last_equations8 - 1;
        for (int i10 = 0; i10 < this.HFEmr8 - 1; i10++) {
            i9 += this.NB_BYTES_EQUATION;
            bArr[i9] = (byte) (bArr[i9] ^ (((byte) (j >>> (this.HFENr8c * i10))) << this.HFENr8));
        }
        pointerUnion.indexReset();
        int bytesMove = 0;
        for (int i11 = 0; i11 < this.NB_MONOMIAL_PK; i11++) {
            bytesMove = pointerUnion.toBytesMove(bArr, bytesMove, this.HFEmq8);
            pointerUnion.moveNextByte();
        }
    }

    public void convMQS_one_to_last_mr8_equations_gf2(byte[] bArr, PointerUnion pointerUnion) {
        int i;
        pointerUnion.moveNextBytes(this.HFEmq8);
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i2 = this.NB_MONOMIAL_PK >>> 3;
        int i3 = 0;
        for (int i4 = 0; i4 < this.HFEmr8; i4++) {
            pointerUnion2.changeIndex(pointerUnion);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                int i6 = (pointerUnion2.getByte() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (int i7 = 1; i7 < 8; i7++) {
                    i6 ^= ((pointerUnion2.getByte() >>> i4) & 1) << i7;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) i6;
                i5++;
                i3++;
            }
            if (this.HFENr8 != 0) {
                long withCheck = (pointerUnion2.getWithCheck() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (i = 1; i < this.HFENr8; i++) {
                    withCheck ^= ((pointerUnion2.getWithCheck() >>> i4) & 1) << i;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) withCheck;
                i3++;
            }
        }
    }

    public void convMQ_UL_gf2(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.ACCESS_last_equations8;
            int i4 = this.NB_BYTES_EQUATION;
            for_setPK(bArr, bArr2, i3 + (i2 * i4), i2 * i4, this.HFEnv + 1);
        }
    }

    public int crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long j;
        int i;
        int i2;
        long j2;
        PointerUnion pointerUnion = new PointerUnion(bArr);
        int i3 = 0;
        long jConvMQ_uncompressL_gf2 = 0;
        if (this.HFENr8 == 0 || this.HFEmr8 <= 1) {
            j = 0;
        } else {
            PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
            pointerUnion2.moveNextBytes(this.ACCESS_last_equations8 - 1);
            j = 0;
            for (int i4 = 0; i4 < this.HFEmr8 - 1; i4++) {
                pointerUnion2.moveNextBytes(this.NB_BYTES_EQUATION);
                j ^= ((((long) pointerUnion2.getByte()) & 255) >>> this.HFENr8) << (this.HFENr8c * i4);
            }
        }
        int i5 = this.HFEmr8;
        if (i5 == 0) {
            Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
            Pointer pointer2 = new Pointer(new Pointer(this.NB_WORD_GF2nv));
            Pointer pointer3 = new Pointer(this.SIZE_DIGEST_UINT);
            pointer.fill(0, bArr3, 0, this.NB_BYTES_GFqnv);
            getSHA3Hash(pointer3, 0, 64, bArr2, 0, bArr2.length, new byte[64]);
            evalMQSnocst8_quo_gf2(pointer2, pointer, pointerUnion);
            return pointer2.isEqual_nocst_gf2(pointer3, this.NB_WORD_GF2m);
        }
        Pointer pointer4 = new Pointer((this.NB_WORD_UNCOMP_EQ * i5) + 1);
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion);
        while (true) {
            int i6 = this.HFEmr8 - 1;
            i = this.ACCESS_last_equations8;
            if (i3 >= i6) {
                break;
            }
            pointerUnion3.setByteIndex(i + (this.NB_BYTES_EQUATION * i3));
            jConvMQ_uncompressL_gf2 ^= convMQ_uncompressL_gf2(new Pointer(pointer4, (this.NB_WORD_UNCOMP_EQ * i3) + 1), pointerUnion3) << i3;
            i3++;
        }
        pointerUnion3.setByteIndex(i + (this.NB_BYTES_EQUATION * i3));
        long jConvMQ_last_uncompressL_gf2 = jConvMQ_uncompressL_gf2 ^ (convMQ_last_uncompressL_gf2(new Pointer(pointer4, (this.NB_WORD_UNCOMP_EQ * i3) + 1), pointerUnion3) << i3);
        if (this.HFENr8 != 0) {
            int i7 = this.HFEnvr;
            if (i7 == 0) {
                i2 = (i3 + 1) * this.NB_WORD_UNCOMP_EQ;
                j2 = j << (64 - this.LOST_BITS);
            } else {
                int i8 = this.LOST_BITS;
                if (i7 > i8) {
                    i2 = (i3 + 1) * this.NB_WORD_UNCOMP_EQ;
                    j2 = j << (i7 - i8);
                } else {
                    int i9 = this.NB_WORD_UNCOMP_EQ;
                    int i10 = i3 + 1;
                    if (i7 == i8) {
                        pointer4.set(i10 * i9, j);
                    } else {
                        pointer4.setXor((i9 * i10) - 1, j << (64 - (i8 - i7)));
                        pointer4.set(i10 * this.NB_WORD_UNCOMP_EQ, j >>> (this.LOST_BITS - this.HFEnvr));
                    }
                }
            }
            pointer4.setXor(i2, j2);
        }
        pointer4.set(jConvMQ_last_uncompressL_gf2 << (this.HFEmr - this.HFEmr8));
        return sign_openHFE_huncomp_pk(bArr2, bArr2.length, bArr3, pointerUnion, new PointerUnion(pointer4));
    }

    public int div_r_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer);
        inv_gf2n(pointer4, pointer2, this.NB_WORD_GFqn * i2);
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer5.changeIndex((i - i2) * this.NB_WORD_GFqn);
            mul_gf2n(pointer3, pointer, this.NB_WORD_GFqn * i, pointer4);
            for_mul_rem_xor_move(pointer5, pointer3, pointer2, 0, i2);
            i--;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    public void evalHFEv_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        int i;
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        Pointer pointer5 = new Pointer(this.NB_WORD_MUL);
        int i2 = 1;
        Pointer pointer6 = new Pointer((this.HFEDegI + 1) * this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer();
        int index = pointer2.getIndex();
        Pointer pointer8 = new Pointer(this.NB_WORD_GFqv);
        Pointer pointer9 = new Pointer(pointer6, this.NB_WORD_GFqn);
        pointer6.copyFrom(pointer3, this.NB_WORD_GFqn);
        pointer6.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
        int i3 = 1;
        while (true) {
            int i4 = this.HFEDegI;
            i = this.NB_WORD_GFqn;
            if (i3 > i4) {
                break;
            }
            sqr_gf2n(pointer9, 0, pointer9, -i);
            pointer9.move(this.NB_WORD_GFqn);
            i3++;
        }
        int i5 = this.NB_WORD_GFqv;
        if (i + i5 != this.NB_WORD_GF2nv) {
            i5--;
        }
        int i6 = i5;
        pointer8.setRangeRotate(0, pointer3, i - 1, i6, 64 - this.HFEnr);
        int i7 = this.NB_WORD_GFqn;
        if (this.NB_WORD_GFqv + i7 != this.NB_WORD_GF2nv) {
            pointer8.set(i6, pointer3.get((i7 - 1) + i6) >>> this.HFEnr);
        }
        evalMQSv_unrolled_gf2(pointer4, pointer8, pointer2);
        pointer2.move(this.MQv_GFqn_SIZE);
        vmpv_xorrange_move(pointer5, pointer8, pointer2);
        pointer9.changeIndex(pointer6);
        mul_xorrange(pointer4, pointer9, pointer5);
        while (true) {
            int i8 = this.HFEDegI;
            vmpv_xorrange_move(pointer5, pointer8, pointer2);
            if (i2 >= i8) {
                break;
            }
            int i9 = this.NB_WORD_GFqn;
            pointer5.setRangeClear(i9, this.NB_WORD_MMUL - i9);
            pointer7.changeIndex(pointer9);
            for_mul_xorrange_move(pointer5, pointer2, pointer7, i2);
            rem_gf2n(pointer5, 0, pointer5);
            mul_xorrange(pointer4, pointer7, pointer5);
            i2++;
        }
        pointer7.changeIndex(pointer9);
        int i10 = this.HFEDegJ;
        int i11 = this.NB_WORD_GFqn;
        if (i10 != 0) {
            pointer5.setRangeClear(i11, this.NB_WORD_MMUL - i11);
            for_mul_xorrange_move(pointer5, pointer2, pointer7, this.HFEDegJ);
            pointer5.setXorRange(pointer7, this.NB_WORD_GFqn);
            rem_gf2n(pointer5, 0, pointer5);
        } else {
            pointer5.setRangeFromXor(pointer5, pointer7, i11);
        }
        pointer9.move(this.HFEDegI * this.NB_WORD_GFqn);
        mul_xorrange(pointer4, pointer9, pointer5);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
    }

    public void evalMQSv_unrolled_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.HFEv);
        int i = this.HFEv;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int i4 = this.HFEn;
        int i5 = 0;
        int i6 = (i4 >>> 6) + ((i4 & 63) != 0 ? 1 : 0);
        int index = pointer3.getIndex();
        Pointer pointer5 = new Pointer(i6);
        int i7 = 0;
        int range_xi = 0;
        while (i7 < i2) {
            range_xi = pointer4.setRange_xi(pointer2.get(i7), range_xi, 64);
            i7++;
        }
        if (i3 != 0) {
            pointer4.setRange_xi(pointer2.get(i7), range_xi, i3);
        }
        pointer.copyFrom(pointer3, i6);
        pointer3.move(i6);
        while (i5 < this.HFEv) {
            pointer5.copyFrom(pointer3, i6);
            pointer3.move(i6);
            int i8 = i5 + 1;
            int i9 = i8;
            while (i9 < this.HFEv - 3) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9 + 1));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9 + 2));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9 + 3));
                i9 += 4;
            }
            while (i9 < this.HFEv) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9));
                i9++;
            }
            pointer.setXorRangeAndMask(pointer5, i6, pointer4.get(i5));
            i5 = i8;
        }
        pointer3.changeIndex(index);
    }

    public void fast_sort_gf2n(Pointer pointer, int i) {
        int i2;
        Pointer pointer2 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer();
        Pointer pointer5 = new Pointer();
        int i3 = i - 1;
        int iHighest_One = GeMSSUtils.Highest_One(i3);
        int i4 = iHighest_One;
        while (true) {
            i2 = 0;
            if (i4 <= 1) {
                break;
            }
            int i5 = i4 << 1;
            int i6 = i / i5;
            int iMax = Math.max(0, (i - (i5 * i6)) - i4);
            pointer4.changeIndex(pointer);
            pointer5.changeIndex(pointer, this.NB_WORD_GFqn * i4);
            int i7 = 0;
            while (i7 < i6) {
                GeMSSEngine geMSSEngine = this;
                geMSSEngine.for_casct_move(pointer4, pointer5, pointer3, i4, 1);
                int i8 = i4;
                pointer4.move(geMSSEngine.NB_WORD_GFqn * i8);
                pointer5.move(geMSSEngine.NB_WORD_GFqn * i8);
                i7++;
                i4 = i8;
                this = geMSSEngine;
            }
            GeMSSEngine geMSSEngine2 = this;
            int i9 = i4;
            geMSSEngine2.for_casct_move(pointer4, pointer5, pointer3, iMax, 1);
            int i10 = iHighest_One;
            while (i10 > i9) {
                int i11 = i2;
                while (i11 < i - i10) {
                    if ((i11 & i9) == 0) {
                        pointer5.changeIndex(pointer, (i11 + i9) * geMSSEngine2.NB_WORD_GFqn);
                        GeMSSEngine geMSSEngine3 = geMSSEngine2;
                        Pointer pointer6 = pointer5;
                        Pointer pointer7 = pointer4;
                        geMSSEngine3.copy_for_casct(pointer2, pointer6, pointer, pointer7, pointer3, i10, i11);
                        pointer4 = pointer7;
                        pointer5 = pointer6;
                        geMSSEngine2 = geMSSEngine3;
                        pointer5.copyFrom(pointer2, geMSSEngine2.NB_WORD_GFqn);
                    }
                    i11++;
                }
                i10 >>>= 1;
                i2 = i11;
            }
            i4 = i9 >>> 1;
            this = geMSSEngine2;
        }
        GeMSSEngine geMSSEngine4 = this;
        pointer4.changeIndex(pointer);
        pointer5.changeIndex(pointer, geMSSEngine4.NB_WORD_GFqn);
        geMSSEngine4.for_casct_move(pointer4, pointer5, pointer3, i3, 2);
        pointer5.changeIndex(pointer, geMSSEngine4.NB_WORD_GFqn);
        int i12 = iHighest_One;
        while (i12 > 1) {
            int i13 = i2;
            while (i13 < i - i12) {
                GeMSSEngine geMSSEngine5 = geMSSEngine4;
                Pointer pointer8 = pointer5;
                Pointer pointer9 = pointer4;
                Pointer pointer10 = pointer;
                geMSSEngine5.copy_for_casct(pointer2, pointer8, pointer10, pointer9, pointer3, i12, i13);
                geMSSEngine4 = geMSSEngine5;
                pointer8.copyFrom(pointer2, geMSSEngine4.NB_WORD_GFqn);
                pointer8.move(geMSSEngine4.NB_WORD_GFqn << 1);
                i13 += 2;
                pointer = pointer10;
                pointer4 = pointer9;
                pointer5 = pointer8;
            }
            i12 >>>= 1;
            pointer4 = pointer4;
            i2 = i13;
            pointer5 = pointer5;
        }
    }

    public void findRootsSplit2_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer2.getIndex();
        sqr_gf2n(pointer3, 0, pointer2, this.NB_WORD_GFqn);
        inv_gf2n(pointer, pointer3, 0);
        mul_gf2n(pointer3, pointer2, pointer);
        findRootsSplit_x2_x_c_HT_gf2nx(pointer4, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        mul_gf2n(pointer, pointer4, pointer2);
        int i = this.NB_WORD_GFqn;
        pointer.setRangeFromXor(i, pointer, 0, pointer2, 0, i);
        pointer2.changeIndex(index);
    }

    public void findRootsSplit_x2_x_c_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        int i = (this.HFEn + 1) >>> 1;
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        int i2 = 1;
        for (int i3 = this.HFEn1h_rightmost; i3 != -1; i3--) {
            int i4 = i2 << 1;
            sqr_gf2n(pointer3, pointer);
            for (int i5 = 1; i5 < i4; i5++) {
                sqr_gf2n(pointer3, pointer3);
            }
            pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
            i2 = i >>> i3;
            if ((i2 & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                sqr_gf2n(pointer, pointer3);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn);
            }
        }
    }

    public void for_mul_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
            pointer2.move(this.NB_WORD_GFqn);
            pointer3.move(this.NB_WORD_GFqn);
        }
    }

    public void genSecretMQS_gf2_opt(Pointer pointer, Pointer pointer2) {
        int i;
        int i2;
        int i3;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        int i4 = 1;
        Pointer pointer4 = new Pointer((this.HFEDegI + 1) * (this.HFEv + 1) * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer2, this.MQv_GFqn_SIZE);
        for (int i5 = 0; i5 <= this.HFEDegI; i5++) {
            for (int i6 = 0; i6 <= this.HFEv; i6++) {
                int i7 = ((this.HFEDegI + 1) * i6) + i5;
                int i8 = this.NB_WORD_GFqn;
                pointer4.copyFrom(i7 * i8, pointer5, 0, i8);
                pointer5.move(this.NB_WORD_GFqn);
            }
            pointer5.move(this.NB_WORD_GFqn * i5);
        }
        Pointer pointer6 = new Pointer(this.SIZE_ROW * (this.HFEn - 1) * this.NB_WORD_GFqn);
        for (int i9 = 1; i9 < this.HFEn; i9++) {
            pointer6.set(i9 >>> 6, 1 << (i9 & 63));
            int i10 = 0;
            while (true) {
                int i11 = this.HFEDegI;
                i3 = this.NB_WORD_GFqn;
                if (i10 < i11) {
                    sqr_gf2n(pointer6, i3, pointer6, 0);
                    pointer6.move(this.NB_WORD_GFqn);
                    i10++;
                }
            }
            pointer6.move(i3);
        }
        pointer6.indexReset();
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.MQv_GFqn_SIZE);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer(this.HFEDegI * this.HFEn * this.NB_WORD_GFqn);
        special_buffer(pointer7, pointer2, pointer6);
        Pointer pointer8 = new Pointer(pointer7);
        Pointer pointer9 = new Pointer(pointer7);
        pointer.copyFrom(pointer9, this.NB_WORD_GFqn);
        pointer9.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix_NoMove(pointer9, this.NB_WORD_GFqn, this.HFEDegI - 1);
        pointer5.changeIndex(pointer4);
        pointer.setXorMatrix(pointer5, this.NB_WORD_GFqn, this.HFEDegI + 1);
        Pointer pointer10 = new Pointer(pointer6, this.NB_WORD_GFqn);
        int i12 = 1;
        while (i12 < this.HFEn) {
            dotProduct_gf2n(pointer, pointer10, pointer8, this.HFEDegI);
            pointer10.move(this.SIZE_ROW * this.NB_WORD_GFqn);
            pointer.setXorMatrix(pointer9, this.NB_WORD_GFqn, this.HFEDegI);
            i12++;
        }
        while (true) {
            int i13 = this.HFEnv;
            i = this.NB_WORD_GFqn;
            if (i12 >= i13) {
                break;
            }
            pointer.copyFrom(pointer5, i);
            pointer5.move(this.NB_WORD_GFqn);
            pointer.setXorMatrix(pointer5, this.NB_WORD_GFqn, this.HFEDegI);
            i12++;
        }
        Pointer pointer11 = new Pointer(pointer6, i);
        Pointer pointer12 = new Pointer(this.NB_WORD_MUL);
        int i14 = 1;
        while (i14 < this.HFEn) {
            pointer8.move(this.HFEDegI * this.NB_WORD_GFqn);
            pointer10.changeIndex(pointer11);
            pointer9.changeIndex(pointer8);
            int i15 = i4;
            this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer4, new Pointer(pointer10, -this.NB_WORD_GFqn));
            int i16 = i15;
            while (true) {
                int i17 = this.HFEDegI;
                i2 = this.NB_WORD_GFqn;
                if (i16 > i17) {
                    break;
                }
                int i18 = i14;
                Pointer pointer13 = pointer9;
                pointer3.setRangeFromXor(0, pointer13, 0, pointer4, i16 * i2, i2);
                mul_xorrange(this.Buffer_NB_WORD_MUL, pointer3, pointer10);
                pointer13.move(this.NB_WORD_GFqn);
                pointer10.move(this.NB_WORD_GFqn);
                i16++;
                pointer9 = pointer13;
                pointer12 = pointer12;
                i14 = i18;
            }
            Pointer pointer14 = pointer12;
            int i19 = i14;
            Pointer pointer15 = pointer9;
            pointer10.move(i2);
            rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
            pointer.move(this.NB_WORD_GFqn);
            int i20 = i19 + 1;
            int i21 = i20;
            while (i21 < this.HFEn) {
                int index = pointer10.getIndex();
                int index2 = pointer8.getIndex();
                int index3 = pointer11.getIndex();
                int index4 = pointer15.getIndex();
                mul_move(pointer14, pointer10, pointer8);
                for_mul_xorrange_move(pointer14, pointer10, pointer8, this.HFEDegI - 1);
                for_mul_xorrange_move(pointer14, pointer11, pointer15, this.HFEDegI);
                rem_gf2n(pointer, 0, pointer14);
                pointer10.changeIndex(index + (this.NB_WORD_GFqn * this.SIZE_ROW));
                pointer8.changeIndex(index2);
                pointer11.changeIndex(index3);
                pointer15.changeIndex(index4 + (this.HFEDegI * this.NB_WORD_GFqn));
                pointer.move(this.NB_WORD_GFqn);
                i21++;
                pointer3 = pointer3;
            }
            Pointer pointer16 = pointer3;
            pointer5.changeIndex(pointer4);
            pointer11.move(-this.NB_WORD_GFqn);
            while (i21 < this.HFEnv) {
                pointer5.move((this.HFEDegI + 1) * this.NB_WORD_GFqn);
                dotProduct_gf2n(pointer, pointer11, pointer5, this.HFEDegI + 1);
                pointer.move(this.NB_WORD_GFqn);
                i21++;
            }
            int i22 = this.NB_WORD_GFqn;
            pointer11.move(i22 + (this.SIZE_ROW * i22));
            pointer9 = pointer15;
            i4 = i15;
            pointer3 = pointer16;
            i14 = i20;
            pointer12 = pointer14;
        }
        pointer2.move(this.NB_WORD_GFqn - this.MQv_GFqn_SIZE);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn * (this.NB_MONOMIAL_VINEGAR - 1));
        pointer.indexReset();
        pointer2.indexReset();
    }

    public int interpolateHFE_FS_ref(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        int i;
        Pointer pointer4 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer();
        Pointer pointer7 = new Pointer(this.HFEnv * this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(pointer3);
        Pointer pointer9 = new Pointer(pointer7);
        for (int i2 = 0; i2 < this.HFEnv; i2++) {
            evalHFEv_gf2nx(pointer9, pointer2, pointer8);
            pointer9.move(this.NB_WORD_GFqn);
            pointer8.move(this.NB_WORD_GF2nv);
        }
        pointer8.changeIndex(pointer3);
        pointer9.changeIndex(pointer7);
        int i3 = 0;
        while (i3 < this.HFEnv) {
            pointer.move(this.NB_WORD_GFqn);
            pointer9.setXorRange(pointer2, this.NB_WORD_GFqn);
            pointer.copyFrom(pointer9, this.NB_WORD_GFqn);
            pointer5.changeIndex(pointer9);
            pointer6.changeIndex(pointer8);
            i3++;
            int i4 = i3;
            while (true) {
                int i5 = this.HFEnv;
                i = this.NB_WORD_GFqn;
                if (i4 < i5) {
                    pointer.move(i);
                    pointer5.move(this.NB_WORD_GFqn);
                    pointer6.move(this.NB_WORD_GF2nv);
                    pointer4.setRangeFromXor(pointer8, pointer6, this.NB_WORD_GF2nv);
                    evalHFEv_gf2nx(pointer, pointer2, pointer4);
                    pointer.setXorRangeXor(0, pointer9, 0, pointer5, 0, this.NB_WORD_GFqn);
                    i4++;
                }
            }
            pointer9.move(i);
            pointer8.move(this.NB_WORD_GF2nv);
            pointer = pointer;
        }
        pointer.indexReset();
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0093  */
    /* JADX WARN: Code duplicated, block: B:25:0x00a6 A[LOOP:2: B:24:0x00a4->B:25:0x00a6, LOOP_END] */
    public void invMatrixLU_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        GeMSSEngine geMSSEngine = this;
        Pointer pointer4 = new Pointer(pointer2);
        Pointer pointer5 = new Pointer(pointer2);
        Pointer pointer6 = new Pointer(pointer3);
        int i7 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        if (i7 == 1) {
            pointer.setRangeClear(0, geMSSEngine.MATRIXn_SIZE);
            i = geMSSEngine.HFEnq;
            i2 = geMSSEngine.HFEn - 1;
            i3 = geMSSEngine.NB_WORD_GFqn;
            i4 = geMSSEngine.HFEnr;
            i5 = geMSSEngine.LTRIANGULAR_N_SIZE;
        } else {
            if (i7 != 2) {
                w01.a("Invalid Input");
                return;
            }
            i = geMSSEngine.HFEnvq;
            i2 = geMSSEngine.HFEnv - 1;
            i3 = geMSSEngine.NB_WORD_GF2nv;
            i4 = geMSSEngine.HFEnvr;
            i5 = geMSSEngine.LTRIANGULAR_NV_SIZE;
        }
        int i8 = i;
        int i9 = i2;
        int i10 = i3;
        int i11 = i4;
        Pointer pointer7 = new Pointer(pointer);
        Pointer pointer8 = new Pointer(pointer);
        int iLoop_xor_loop_move_xorandmask_move = 0;
        int i12 = 0;
        while (i12 < i8) {
            iLoop_xor_loop_move_xorandmask_move = geMSSEngine.loop_xor_loop_move_xorandmask_move(pointer7, pointer8, pointer4, pointer5, iLoop_xor_loop_move_xorandmask_move, i12, 64, i9, i10);
            pointer5.moveIncremental();
            i12++;
            geMSSEngine = this;
            i5 = i5;
        }
        int i13 = i5;
        if (i11 <= 1) {
            if (i11 == 1) {
                pointer7.set(i12, 1L);
            }
            pointer6.move(i13);
            while (i9 > 0) {
                pointer6.move((-1) - (i9 >>> 6));
                pointer7.move(-i10);
                pointer8.changeIndex(pointer);
                for (i6 = 0; i6 < i9; i6++) {
                    pointer8.setXorRangeAndMask(pointer7, i10, -((pointer6.get(i6 >>> 6) >>> (i6 & 63)) & 1));
                    pointer8.move(i10);
                }
                i9--;
            }
        }
        int i14 = i11 - 1;
        loop_xor_loop_move_xorandmask_move(pointer7, pointer8, pointer4, pointer5, iLoop_xor_loop_move_xorandmask_move, i12, i14, i9, i10);
        pointer7.setXor(i12, 1 << i14);
        pointer7.move(i10);
        pointer6.move(i13);
        while (i9 > 0) {
            pointer6.move((-1) - (i9 >>> 6));
            pointer7.move(-i10);
            pointer8.changeIndex(pointer);
            while (i6 < i9) {
                pointer8.setXorRangeAndMask(pointer7, i10, -((pointer6.get(i6 >>> 6) >>> (i6 & 63)) & 1));
                pointer8.move(i10);
            }
            i9--;
        }
    }

    public void mul_gf2n(Pointer pointer, Pointer pointer2, int i, Pointer pointer3) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        pointer2.changeIndex(index);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    public void mul_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(pointer, pointer2, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        pointer3.move(this.NB_WORD_GFqn);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int index = pointer3.getIndex();
        pointer3.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
        pointer3.changeIndex(index);
    }

    public void mul_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
    }

    public void signHFE_FeistelPatarin(SecureRandom secureRandom, byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3) {
        int i3;
        long j;
        PointerUnion pointerUnion;
        Pointer pointer;
        Pointer pointer2;
        SecureRandom secureRandom2 = secureRandom;
        this.random = secureRandom2;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer5 = new Pointer(new Pointer(this.SIZE_DIGEST_UINT));
        int i4 = this.HFEv;
        int i5 = i4 & 7;
        int i6 = (i4 >>> 3) + (i5 != 0 ? 1 : 0);
        long jMaskUINT = GeMSSUtils.maskUINT(this.HFEvr);
        SecretKeyHFE secretKeyHFE = new SecretKeyHFE(this);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqv);
        Pointer[] pointerArr = new Pointer[this.HFEDegI + 1];
        precSignHFE(secretKeyHFE, pointerArr, bArr3);
        Pointer pointer7 = new Pointer(secretKeyHFE.F_struct.poly);
        Pointer pointer8 = new Pointer(pointer4);
        int i7 = this.Sha3BitStrength >>> 3;
        byte[] bArr4 = new byte[i7];
        Pointer pointer9 = pointer5;
        Pointer pointer10 = pointer8;
        Pointer pointer11 = pointer7;
        long j2 = jMaskUINT;
        getSHA3Hash(pointer10, 0, i7, bArr2, i, i2, bArr4);
        Pointer pointer12 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer13 = new Pointer(this.NB_WORD_GF2nv);
        PointerUnion pointerUnion2 = new PointerUnion(pointer13);
        long j3 = 0;
        int i8 = 1;
        while (true) {
            i3 = this.NB_ITE;
            if (i8 > i3) {
                break;
            }
            pointer13.setRangeFromXor(pointer12, pointer10, this.NB_WORD_GF2m);
            if (this.HFEmr8 != 0) {
                pointer13.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
                j3 = pointerUnion2.getByte(this.HFEmq8);
            }
            long j4 = j3;
            while (true) {
                if (this.HFEmr8 != 0) {
                    pointerUnion2.fillRandomBytes(this.HFEmq8, secureRandom2, (this.NB_BYTES_GFqn - this.NB_BYTES_GFqm) + 1);
                    pointerUnion2.setAndThenXorByte(this.HFEmq8, -(1 << this.HFEmr8), j4);
                } else {
                    int i9 = this.NB_BYTES_GFqm;
                    pointerUnion2.fillRandomBytes(i9, secureRandom2, this.NB_BYTES_GFqn - i9);
                }
                if ((this.HFEn & 7) != 0) {
                    pointer13.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
                }
                vecMatProduct(pointer3, pointer13, secretKeyHFE.T, FunctionParams.N);
                pointer6.fillRandom(0, secureRandom2, i6);
                if (i5 != 0) {
                    j = j2;
                    pointer6.setAnd(this.NB_WORD_GFqv - 1, j);
                } else {
                    j = j2;
                }
                Pointer pointer14 = pointer11;
                evalMQSv_unrolled_gf2(pointer14, pointer6, secretKeyHFE.F_HFEv);
                pointerUnion = pointerUnion2;
                int i10 = 0;
                while (i10 <= this.HFEDegI) {
                    int i11 = i10;
                    Pointer pointer15 = pointer14;
                    vecMatProduct(this.Buffer_NB_WORD_GFqn, pointer6, new Pointer(pointerArr[i11], this.NB_WORD_GFqn), FunctionParams.V);
                    int i12 = this.NB_WORD_GFqn;
                    i10 = i11 + 1;
                    pointer15.setRangeFromXor(i12 * (((i11 * i10) >>> 1) + 1), pointerArr[i11], 0, this.Buffer_NB_WORD_GFqn, 0, i12);
                    pointer14 = pointer15;
                }
                pointer11 = pointer14;
                if (chooseRootHFE_gf2nx(pointer13, secretKeyHFE.F_struct, pointer3) != 0) {
                    break;
                }
                secureRandom2 = secureRandom;
                j4 = j4;
                pointerUnion2 = pointerUnion;
                j2 = j;
            }
            pointer13.setXor(this.NB_WORD_GFqn - 1, pointer6.get() << this.HFEnr);
            Pointer pointer16 = pointer13;
            pointer16.setRangeRotate(this.NB_WORD_GFqn, pointer6, 0, this.NB_WORD_GFqv - 1, 64 - this.HFEnr);
            int i13 = this.NB_WORD_GFqn;
            int i14 = this.NB_WORD_GFqv;
            if (i13 + i14 == this.NB_WORD_GF2nv) {
                pointer16.set((i13 + i14) - 1, pointer6.get(i14 - 1) >>> (64 - this.HFEnr));
            }
            vecMatProduct(pointer12, pointer16, secretKeyHFE.S, FunctionParams.NV);
            int i15 = this.NB_ITE;
            if (i8 != i15) {
                int i16 = this.NB_WORD_GF2nv;
                int i17 = this.NB_WORD_GF2nvm;
                int i18 = (((i15 - 1) - i8) * i17) + i16;
                pointer12.copyFrom(i18, pointer12, i16 - i17, i17);
                if (this.HFEmr != 0) {
                    pointer12.setAnd(i18, ~this.MASK_GF2m);
                }
                byte[] bytes = pointer10.toBytes(this.SIZE_DIGEST);
                pointer = pointer10;
                pointer2 = pointer9;
                getSHA3Hash(pointer2, 0, this.SIZE_DIGEST, bytes, 0, bytes.length, bytes);
                pointer2.swap(pointer);
            } else {
                pointer = pointer10;
                pointer2 = pointer9;
            }
            i8++;
            j3 = j4;
            pointer9 = pointer2;
            pointer10 = pointer;
            pointer12 = pointer12;
            pointer13 = pointer16;
            pointerUnion2 = pointerUnion;
            secureRandom2 = secureRandom;
            j2 = j;
            i5 = i5;
        }
        Pointer pointer17 = pointer12;
        if (i3 == 1) {
            System.arraycopy(pointer17.toBytes(pointer17.getLength() << 3), 0, bArr, 0, this.NB_BYTES_GFqnv);
        } else {
            compress_signHFE(bArr, pointer17);
        }
    }

    public int sign_openHFE_huncomp_pk(byte[] bArr, int i, byte[] bArr2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        int i2;
        Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer2 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer3 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer4 = new Pointer(pointer2);
        Pointer pointer5 = new Pointer(pointer3);
        byte[] bArr3 = new byte[64];
        Pointer pointer6 = new Pointer(this.NB_ITE * this.SIZE_DIGEST_UINT);
        long j = pointerUnion2.get();
        pointerUnion2.move(1);
        uncompress_signHFE(pointer, bArr2);
        getSHA3Hash(pointer6, 0, 64, bArr, 0, i, bArr3);
        int i3 = 1;
        while (true) {
            int i4 = this.NB_ITE;
            i2 = this.SIZE_DIGEST_UINT;
            if (i3 >= i4) {
                break;
            }
            int i5 = i3;
            getSHA3Hash(pointer6, i2 * i3, 64, bArr3, 0, this.SIZE_DIGEST, bArr3);
            pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i5 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
            i3 = i5 + 1;
        }
        pointer6.setAnd(((i2 * (i3 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
        evalMQShybrid8_uncomp_nocst_gf2_m(pointer4, pointer, pointerUnion, pointerUnion2);
        pointer4.setXor(this.HFEmq, j);
        for (int i6 = this.NB_ITE - 1; i6 > 0; i6--) {
            pointer4.setXorRange(pointer6, this.SIZE_DIGEST_UINT * i6, this.NB_WORD_GF2m);
            int i7 = this.NB_WORD_GF2nv + (((this.NB_ITE - 1) - i6) * this.NB_WORD_GF2nvm);
            pointer4.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
            pointer4.setXor(this.NB_WORD_GF2m - 1, pointer.get(i7));
            int i8 = this.NB_WORD_GF2nvm;
            if (i8 != 1) {
                pointer4.copyFrom(this.NB_WORD_GF2m, pointer, i7 + 1, i8 - 1);
            }
            evalMQShybrid8_uncomp_nocst_gf2_m(pointer5, pointer4, pointerUnion, pointerUnion2);
            pointer5.setXor(this.HFEmq, j);
            pointer5.swap(pointer4);
        }
        return pointer4.isEqual_nocst_gf2(pointer6, this.NB_WORD_GF2m);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0057  */
    /* JADX WARN: Code duplicated, block: B:18:0x0061 A[LOOP:1: B:16:0x005d->B:18:0x0061, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:22:0x007f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0081 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x0083 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x0086  */
    /* JADX WARN: Code duplicated, block: B:28:0x008a  */
    /* JADX WARN: Code duplicated, block: B:31:0x008f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0099  */
    /* JADX WARN: Code duplicated, block: B:36:0x009e  */
    /* JADX WARN: Code duplicated, block: B:37:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b2 A[LOOP:2: B:38:0x00b0->B:39:0x00b2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:52:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:? A[RETURN, SYNTHETIC] */
    public void vecMatProduct(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        int i3;
        int i4;
        long j;
        int i5;
        int i6;
        long j2;
        Pointer pointer4 = new Pointer(pointer3);
        int i7 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        int i8 = 0;
        if (i7 == 1) {
            pointer.setRangeClear(0, this.NB_WORD_GFqn);
            i = this.NB_WORD_GFqn;
            i2 = this.HFEnq;
        } else {
            if (i7 != 2) {
                if (i7 == 3) {
                    pointer.setRangeClear(0, this.NB_WORD_GFqn);
                    i = this.NB_WORD_GFqn;
                    i2 = this.HFEvq;
                } else {
                    if (i7 != 4) {
                        w01.a("Invalid input for vecMatProduct");
                        return;
                    }
                    pointer.setRangeClear(0, this.NB_WORD_GF2m);
                    i2 = this.HFEnq;
                    i = this.NB_WORD_GF2m;
                    i3 = this.NB_WORD_GFqn;
                }
                while (i8 < i2) {
                    j2 = pointer2.get(i8);
                    while (i8 < 64) {
                        pointer.setXorRangeAndMask(pointer4, i, -(j2 & 1));
                        pointer4.move(i3);
                        j2 >>>= 1;
                        i8++;
                    }
                    i8++;
                    i8 = 0;
                }
                i4 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
                if (i4 == 1) {
                    j = pointer2.get(this.HFEnq);
                    i5 = this.HFEnr;
                } else if (i4 != 2) {
                    if (this.HFEnvr == 0) {
                        return;
                    }
                    j = pointer2.get(this.HFEnvq);
                    i5 = this.HFEnvr;
                } else if (i4 != 3) {
                    if (i4 != 4) {
                        w01.a("Invalid input for vecMatProduct");
                        return;
                    }
                    j = pointer2.get(this.HFEnq);
                    i5 = this.HFEnr;
                } else {
                    if (this.HFEvr == 0) {
                        return;
                    }
                    j = pointer2.get(this.HFEvq);
                    i5 = this.HFEvr;
                }
                for (i6 = 0; i6 < i5; i6++) {
                    pointer.setXorRangeAndMask(pointer4, i, -(j & 1));
                    pointer4.move(i3);
                    j >>>= 1;
                }
                if (functionParams == FunctionParams.M || this.HFEmr == 0) {
                }
                pointer.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
                return;
            }
            pointer.setRangeClear(0, this.NB_WORD_GF2nv);
            i2 = this.HFEnvq;
            i = this.NB_WORD_GF2nv;
        }
        i3 = i;
        while (i8 < i2) {
            j2 = pointer2.get(i8);
            while (i8 < 64) {
                pointer.setXorRangeAndMask(pointer4, i, -(j2 & 1));
                pointer4.move(i3);
                j2 >>>= 1;
                i8++;
            }
            i8++;
            i8 = 0;
        }
        i4 = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$gemss$GeMSSEngine$FunctionParams[functionParams.ordinal()];
        if (i4 == 1) {
            j = pointer2.get(this.HFEnq);
            i5 = this.HFEnr;
        } else if (i4 != 2) {
            if (this.HFEnvr == 0) {
                return;
            }
            j = pointer2.get(this.HFEnvq);
            i5 = this.HFEnvr;
        } else if (i4 != 3) {
            if (i4 != 4) {
                w01.a("Invalid input for vecMatProduct");
                return;
            }
            j = pointer2.get(this.HFEnq);
            i5 = this.HFEnr;
        } else {
            if (this.HFEvr == 0) {
                return;
            }
            j = pointer2.get(this.HFEvq);
            i5 = this.HFEvr;
        }
        while (i6 < i5) {
            pointer.setXorRangeAndMask(pointer4, i, -(j & 1));
            pointer4.move(i3);
            j >>>= 1;
        }
        if (functionParams == FunctionParams.M) {
        }
    }

    public void mul_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    private void sqr_gf2n(Pointer pointer, int i, Pointer pointer2, int i2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, i2 + pointer2.cp);
        rem_gf2n(pointer, i, this.Buffer_NB_WORD_MUL);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }
}
