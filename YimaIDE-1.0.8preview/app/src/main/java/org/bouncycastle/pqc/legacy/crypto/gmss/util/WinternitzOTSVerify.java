package org.bouncycastle.pqc.legacy.crypto.gmss.util;

import kotlin.UByte;
import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class WinternitzOTSVerify {
    private int mdsize;
    private Digest messDigestOTS;
    private int w;

    public WinternitzOTSVerify(Digest digest, int i) {
        this.w = i;
        this.messDigestOTS = digest;
        this.mdsize = digest.getDigestSize();
    }

    private void hashSignatureBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 < 1) {
            System.arraycopy(bArr, i, bArr2, i3, this.mdsize);
            return;
        }
        this.messDigestOTS.update(bArr, i, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr2, i3);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                this.messDigestOTS.update(bArr2, i3, this.mdsize);
            }
        }
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int i3;
        int i4 = this.mdsize;
        byte[] bArr3 = new byte[i4];
        int i5 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        this.messDigestOTS.doFinal(bArr3, 0);
        int i6 = this.mdsize << 3;
        int i7 = this.w;
        int i8 = (i6 + (i7 - 1)) / i7;
        boolean z = true;
        int log = getLog((i8 << i7) + 1);
        int i9 = this.w;
        int i10 = this.mdsize;
        int i11 = i10 * ((((log + i9) - 1) / i9) + i8);
        if (i11 != bArr2.length) {
            return null;
        }
        byte[] bArr4 = new byte[i11];
        char c = '\b';
        if (8 % i9 == 0) {
            int i12 = 8 / i9;
            int i13 = (1 << i9) - 1;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            while (i16 < i4) {
                int i17 = i15;
                int i18 = 0;
                while (i18 < i12) {
                    int i19 = bArr3[i16] & i13;
                    int i20 = i14 + i19;
                    int i21 = this.mdsize;
                    hashSignatureBlock(bArr2, i17 * i21, i13 - i19, bArr4, i21 * i17);
                    bArr3[i16] = (byte) (bArr3[i16] >>> this.w);
                    i17++;
                    i18++;
                    i14 = i20;
                }
                i16++;
                i15 = i17;
            }
            int i22 = (i8 << this.w) - i14;
            int i23 = i15;
            int i24 = 0;
            while (i24 < log) {
                int i25 = this.mdsize;
                hashSignatureBlock(bArr2, i23 * i25, i13 - (i22 & i13), bArr4, i23 * i25);
                int i26 = this.w;
                i22 >>>= i26;
                i23++;
                i24 += i26;
            }
        } else {
            if (i9 >= 8) {
                if (i9 < 57) {
                    int i27 = (i10 << 3) - i9;
                    int i28 = (1 << i9) - 1;
                    byte[] bArr5 = new byte[i10];
                    int i29 = 0;
                    int i30 = 0;
                    int i31 = 0;
                    while (i29 <= i27) {
                        int i32 = i29 >>> 3;
                        int i33 = i29 % 8;
                        char c2 = c;
                        i29 += this.w;
                        int i34 = (i29 + 7) >>> 3;
                        int i35 = i5;
                        int i36 = i32;
                        long j = 0;
                        while (true) {
                            i3 = i27;
                            if (i36 >= i34) {
                                break;
                            }
                            j ^= (long) ((bArr3[i36] & UByte.MAX_VALUE) << (i35 << 3));
                            i35++;
                            i36++;
                            i27 = i3;
                            bArr3 = bArr3;
                        }
                        byte[] bArr6 = bArr3;
                        long j2 = i28;
                        long j3 = (j >>> i33) & j2;
                        i30 = (int) (((long) i30) + j3);
                        int i37 = this.mdsize;
                        System.arraycopy(bArr2, i31 * i37, bArr5, 0, i37);
                        while (j3 < j2) {
                            this.messDigestOTS.update(bArr5, 0, i10);
                            this.messDigestOTS.doFinal(bArr5, 0);
                            j3++;
                        }
                        int i38 = this.mdsize;
                        System.arraycopy(bArr5, 0, bArr4, i31 * i38, i38);
                        i31++;
                        c = c2;
                        i27 = i3;
                        bArr3 = bArr6;
                        i5 = 0;
                    }
                    byte[] bArr7 = bArr3;
                    int i39 = i29 >>> 3;
                    if (i39 < this.mdsize) {
                        int i40 = i29 % 8;
                        int i41 = 0;
                        long j4 = 0;
                        while (true) {
                            i2 = this.mdsize;
                            if (i39 >= i2) {
                                break;
                            }
                            j4 ^= (long) ((bArr7[i39] & UByte.MAX_VALUE) << (i41 << 3));
                            i41++;
                            i39++;
                        }
                        long j5 = i28;
                        long j6 = (j4 >>> i40) & j5;
                        i30 = (int) (((long) i30) + j6);
                        System.arraycopy(bArr2, i31 * i2, bArr5, 0, i2);
                        while (j6 < j5) {
                            this.messDigestOTS.update(bArr5, 0, i10);
                            this.messDigestOTS.doFinal(bArr5, 0);
                            j6++;
                        }
                        int i42 = this.mdsize;
                        System.arraycopy(bArr5, 0, bArr4, i31 * i42, i42);
                        i31++;
                    }
                    int i43 = (i8 << this.w) - i30;
                    int i44 = 0;
                    while (i44 < log) {
                        int i45 = this.mdsize;
                        System.arraycopy(bArr2, i31 * i45, bArr5, 0, i45);
                        int i46 = i31;
                        for (long j7 = i43 & i28; j7 < i28; j7++) {
                            this.messDigestOTS.update(bArr5, 0, i10);
                            this.messDigestOTS.doFinal(bArr5, 0);
                        }
                        int i47 = this.mdsize;
                        System.arraycopy(bArr5, 0, bArr4, i46 * i47, i47);
                        int i48 = this.w;
                        i43 >>>= i48;
                        i31 = i46 + 1;
                        i44 += i48;
                    }
                    i = 0;
                }
                this.messDigestOTS.update(bArr4, i, i11);
                byte[] bArr8 = new byte[this.mdsize];
                this.messDigestOTS.doFinal(bArr8, i);
                return bArr8;
            }
            int i49 = i10 / i9;
            int i50 = (1 << i9) - 1;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            while (i51 < i49) {
                int i55 = i52;
                boolean z2 = z;
                long j8 = 0;
                for (int i56 = 0; i56 < this.w; i56++) {
                    j8 ^= (long) ((bArr3[i55] & UByte.MAX_VALUE) << (i56 << 3));
                    i55++;
                }
                int i57 = 0;
                while (i57 < 8) {
                    int i58 = (int) (j8 & ((long) i50));
                    int i59 = i53 + i58;
                    int i60 = this.mdsize;
                    hashSignatureBlock(bArr2, i54 * i60, i50 - i58, bArr4, i60 * i54);
                    j8 >>>= this.w;
                    i54++;
                    i57++;
                    i53 = i59;
                    i50 = i50;
                    i51 = i51;
                }
                i51++;
                z = z2;
                i52 = i55;
            }
            int i61 = i50;
            int i62 = this.mdsize % this.w;
            long j9 = 0;
            for (int i63 = 0; i63 < i62; i63++) {
                j9 ^= (long) ((bArr3[i52] & UByte.MAX_VALUE) << (i63 << 3));
                i52++;
            }
            int i64 = i62 << 3;
            int i65 = 0;
            while (i65 < i64) {
                int i66 = (int) (j9 & ((long) i61));
                int i67 = i53 + i66;
                int i68 = this.mdsize;
                hashSignatureBlock(bArr2, i54 * i68, i61 - i66, bArr4, i54 * i68);
                int i69 = this.w;
                j9 >>>= i69;
                i54++;
                i65 += i69;
                i53 = i67;
            }
            int i70 = (i8 << this.w) - i53;
            int i71 = 0;
            while (i71 < log) {
                int i72 = this.mdsize;
                hashSignatureBlock(bArr2, i54 * i72, i61 - (i70 & i61), bArr4, i54 * i72);
                int i73 = this.w;
                i70 >>>= i73;
                i54++;
                i71 += i73;
            }
        }
        i = 0;
        this.messDigestOTS.update(bArr4, i, i11);
        byte[] bArr9 = new byte[this.mdsize];
        this.messDigestOTS.doFinal(bArr9, i);
        return bArr9;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i = this.w;
        int i2 = ((digestSize << 3) + (i - 1)) / i;
        int log = getLog((i2 << i) + 1);
        int i3 = this.w;
        return digestSize * (i2 + (((log + i3) - 1) / i3));
    }
}
