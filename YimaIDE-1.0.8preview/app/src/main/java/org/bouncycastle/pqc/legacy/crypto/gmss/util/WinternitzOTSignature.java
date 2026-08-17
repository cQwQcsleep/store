package org.bouncycastle.pqc.legacy.crypto.gmss.util;

import kotlin.UByte;
import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class WinternitzOTSignature {
    private int checksumsize;
    private GMSSRandom gmssRandom;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[][] privateKeyOTS;
    private int w;

    public WinternitzOTSignature(byte[] bArr, Digest digest, int i) {
        this.w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        int i2 = (((digestSize << 3) + i) - 1) / i;
        this.messagesize = i2;
        int log = getLog((i2 << i) + 1);
        this.checksumsize = log;
        int i3 = this.messagesize + (((log + i) - 1) / i);
        this.keysize = i3;
        this.privateKeyOTS = new byte[i3][];
        int i4 = this.mdsize;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        for (int i5 = 0; i5 < this.keysize; i5++) {
            this.privateKeyOTS[i5] = this.gmssRandom.nextSeed(bArr2);
        }
    }

    private void hashPrivateKeyBlock(int i, int i2, byte[] bArr, int i3) {
        if (i2 < 1) {
            System.arraycopy(this.privateKeyOTS[i], 0, bArr, i3, this.mdsize);
            return;
        }
        this.messDigestOTS.update(this.privateKeyOTS[i], 0, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr, i3);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                this.messDigestOTS.update(bArr, i3, this.mdsize);
            }
        }
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

    public byte[][] getPrivateKey() {
        return this.privateKeyOTS;
    }

    public byte[] getPublicKey() {
        int i = this.keysize * this.mdsize;
        byte[] bArr = new byte[i];
        int i2 = (1 << this.w) - 1;
        int i3 = 0;
        for (int i4 = 0; i4 < this.keysize; i4++) {
            hashPrivateKeyBlock(i4, i2, bArr, i3);
            i3 += this.mdsize;
        }
        this.messDigestOTS.update(bArr, 0, i);
        byte[] bArr2 = new byte[this.mdsize];
        this.messDigestOTS.doFinal(bArr2, 0);
        return bArr2;
    }

    public byte[] getSignature(byte[] bArr) {
        int i;
        int i2 = this.keysize;
        int i3 = this.mdsize;
        byte[] bArr2 = new byte[i2 * i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        this.messDigestOTS.doFinal(bArr3, 0);
        int i5 = this.w;
        char c = '\b';
        boolean z = true;
        if (8 % i5 == 0) {
            int i6 = 8 / i5;
            int i7 = (1 << i5) - 1;
            int i8 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < i3; i10++) {
                for (int i11 = 0; i11 < i6; i11++) {
                    int i12 = bArr3[i10] & i7;
                    i8 += i12;
                    hashPrivateKeyBlock(i9, i12, bArr2, this.mdsize * i9);
                    bArr3[i10] = (byte) (bArr3[i10] >>> this.w);
                    i9++;
                }
            }
            int i13 = (this.messagesize << this.w) - i8;
            while (i4 < this.checksumsize) {
                hashPrivateKeyBlock(i9, i13 & i7, bArr2, this.mdsize * i9);
                int i14 = this.w;
                i13 >>>= i14;
                i9++;
                i4 += i14;
            }
        } else if (i5 < 8) {
            int i15 = this.mdsize / i5;
            int i16 = (1 << i5) - 1;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            while (i17 < i15) {
                boolean z2 = z;
                long j = 0;
                for (int i21 = 0; i21 < this.w; i21++) {
                    j ^= (long) ((bArr3[i18] & UByte.MAX_VALUE) << (i21 << 3));
                    i18++;
                }
                long j2 = j;
                for (int i22 = 0; i22 < 8; i22++) {
                    int i23 = ((int) j2) & i16;
                    i20 += i23;
                    hashPrivateKeyBlock(i19, i23, bArr2, this.mdsize * i19);
                    j2 >>>= this.w;
                    i19++;
                }
                i17++;
                z = z2;
            }
            int i24 = this.mdsize % this.w;
            long j3 = 0;
            for (int i25 = 0; i25 < i24; i25++) {
                j3 ^= (long) ((bArr3[i18] & UByte.MAX_VALUE) << (i25 << 3));
                i18++;
            }
            int i26 = i24 << 3;
            int i27 = 0;
            while (i27 < i26) {
                int i28 = ((int) j3) & i16;
                i20 += i28;
                hashPrivateKeyBlock(i19, i28, bArr2, this.mdsize * i19);
                int i29 = this.w;
                j3 >>>= i29;
                i19++;
                i27 += i29;
            }
            int i30 = (this.messagesize << this.w) - i20;
            while (i4 < this.checksumsize) {
                hashPrivateKeyBlock(i19, i30 & i16, bArr2, this.mdsize * i19);
                int i31 = this.w;
                i30 >>>= i31;
                i19++;
                i4 += i31;
            }
        } else if (i5 < 57) {
            int i32 = this.mdsize;
            int i33 = (i32 << 3) - i5;
            int i34 = (1 << i5) - 1;
            byte[] bArr4 = new byte[i32];
            int i35 = 0;
            int i36 = 0;
            int i37 = 0;
            while (i35 <= i33) {
                int i38 = i35 % 8;
                char c2 = c;
                i35 += this.w;
                int i39 = (i35 + 7) >>> 3;
                int i40 = 0;
                long j4 = 0;
                for (int i41 = i35 >>> 3; i41 < i39; i41++) {
                    j4 ^= (long) ((bArr3[i41] & UByte.MAX_VALUE) << (i40 << 3));
                    i40++;
                }
                long j5 = (j4 >>> i38) & ((long) i34);
                i37 = (int) (((long) i37) + j5);
                System.arraycopy(this.privateKeyOTS[i36], 0, bArr4, 0, this.mdsize);
                while (j5 > 0) {
                    this.messDigestOTS.update(bArr4, 0, i32);
                    this.messDigestOTS.doFinal(bArr4, 0);
                    j5--;
                }
                int i42 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, i36 * i42, i42);
                i36++;
                c = c2;
            }
            int i43 = i35 >>> 3;
            if (i43 < this.mdsize) {
                int i44 = i35 % 8;
                int i45 = 0;
                long j6 = 0;
                while (true) {
                    i = this.mdsize;
                    if (i43 >= i) {
                        break;
                    }
                    j6 ^= (long) ((bArr3[i43] & UByte.MAX_VALUE) << (i45 << 3));
                    i45++;
                    i43++;
                }
                long j7 = (j6 >>> i44) & ((long) i34);
                i37 = (int) (((long) i37) + j7);
                System.arraycopy(this.privateKeyOTS[i36], 0, bArr4, 0, i);
                while (j7 > 0) {
                    this.messDigestOTS.update(bArr4, 0, i32);
                    this.messDigestOTS.doFinal(bArr4, 0);
                    j7--;
                }
                int i46 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, i36 * i46, i46);
                i36++;
            }
            int i47 = (this.messagesize << this.w) - i37;
            int i48 = 0;
            while (i48 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[i36], 0, bArr4, 0, this.mdsize);
                for (long j8 = i47 & i34; j8 > 0; j8--) {
                    this.messDigestOTS.update(bArr4, 0, i32);
                    this.messDigestOTS.doFinal(bArr4, 0);
                }
                int i49 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, i36 * i49, i49);
                int i50 = this.w;
                i47 >>>= i50;
                i36++;
                i48 += i50;
            }
        }
        return bArr2;
    }
}
