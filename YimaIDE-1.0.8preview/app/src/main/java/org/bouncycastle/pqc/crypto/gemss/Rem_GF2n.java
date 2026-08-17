package org.bouncycastle.pqc.crypto.gemss;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class Rem_GF2n {
    protected int ki;
    protected int ki64;
    protected long mask;

    public static class REM192_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int ki_k3;

        public REM192_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.ki_k3 = i2 - i;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[2];
            int i2 = this.ki;
            long j2 = jArr2[3];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[4];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = (j4 >>> i2) ^ (jArr2[5] << i3);
            long j7 = jArr2[1] ^ j5;
            int i4 = this.k364;
            int i5 = this.k3;
            jArr[i + 1] = (j7 ^ (j3 >>> i4)) ^ (j5 << i5);
            jArr[i + 2] = (((j5 >>> i4) ^ (jArr2[2] ^ j6)) ^ (j6 << i5)) & this.mask;
            long j8 = j3 ^ (j6 >>> this.ki_k3);
            jArr[i] = (j8 << i5) ^ (jArr2[0] ^ j8);
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[2];
            int i2 = this.ki;
            long j2 = jArr2[3];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[4];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = (j4 >>> i2) ^ (jArr2[5] << i3);
            int i4 = i + 1;
            long j7 = jArr[i4];
            long j8 = jArr2[1] ^ j5;
            int i5 = this.k364;
            int i6 = this.k3;
            jArr[i4] = j7 ^ ((j8 ^ (j3 >>> i5)) ^ (j5 << i6));
            int i7 = i + 2;
            jArr[i7] = ((((j5 >>> i5) ^ (jArr2[2] ^ j6)) ^ (j6 << i6)) & this.mask) ^ jArr[i7];
            long j9 = (j6 >>> this.ki_k3) ^ j3;
            jArr[i] = ((j9 << i6) ^ (jArr2[0] ^ j9)) ^ jArr[i];
        }
    }

    public static class REM288_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int k364ki;
        private final int k3_ki;

        public REM288_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.k364ki = i4 + i2;
            this.k3_ki = i - i2;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[5];
            int i2 = this.ki;
            long j2 = jArr2[6];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = (j2 >>> i2) ^ (jArr2[7] << i3);
            long j5 = jArr2[2] ^ j4;
            int i4 = this.k364;
            int i5 = this.k3;
            jArr[i + 2] = (j5 ^ (j3 >>> i4)) ^ (j4 << i5);
            long j6 = (jArr2[7] >>> i2) ^ (jArr2[8] << i3);
            jArr[i + 3] = ((j4 >>> i4) ^ (jArr2[3] ^ j6)) ^ (j6 << i5);
            long j7 = jArr2[8] >>> i2;
            long j8 = jArr2[4];
            long j9 = (((j8 >>> i2) ^ (jArr2[5] << i3)) ^ (j6 >>> this.k364ki)) ^ (j7 << this.k3_ki);
            long j10 = j7 << i5;
            jArr[i + 4] = (j10 ^ ((j6 >>> i4) ^ (j8 ^ j7))) & this.mask;
            jArr[i] = (jArr2[0] ^ j9) ^ (j9 << i5);
            jArr[i + 1] = ((j3 << i5) ^ (jArr2[1] ^ j3)) ^ (j9 >>> i4);
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[5];
            int i2 = this.ki;
            long j2 = jArr2[6];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = (j2 >>> i2) ^ (jArr2[7] << i3);
            int i4 = i + 2;
            long j5 = jArr[i4];
            long j6 = jArr2[2] ^ j4;
            int i5 = this.k364;
            int i6 = this.k3;
            jArr[i4] = j5 ^ ((j6 ^ (j3 >>> i5)) ^ (j4 << i6));
            long j7 = (jArr2[7] >>> i2) ^ (jArr2[8] << i3);
            int i7 = i + 3;
            jArr[i7] = (((jArr2[3] ^ j7) ^ (j4 >>> i5)) ^ (j7 << i6)) ^ jArr[i7];
            long j8 = jArr2[8] >>> i2;
            int i8 = i + 4;
            jArr[i8] = ((((jArr2[4] ^ j8) ^ (j7 >>> i5)) ^ (j8 << i6)) & this.mask) ^ jArr[i8];
            long j9 = (((jArr2[4] >>> i2) ^ (jArr2[5] << i3)) ^ (j7 >>> this.k364ki)) ^ (j8 << this.k3_ki);
            jArr[i] = jArr[i] ^ ((jArr2[0] ^ j9) ^ (j9 << i6));
            int i9 = i + 1;
            jArr[i9] = ((j9 >>> i5) ^ ((jArr2[1] ^ j3) ^ (j3 << i6))) ^ jArr[i9];
        }
    }

    public static class REM384_SPECIALIZED358_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int k364ki;
        private final int k3_ki;

        public REM384_SPECIALIZED358_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.k364ki = i4 + i2;
            this.k3_ki = i - i2;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[6];
            int i2 = this.ki;
            long j2 = jArr2[7];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = (j2 >>> i2) ^ (jArr2[8] << i3);
            long j5 = jArr2[2] ^ j4;
            int i4 = this.k364;
            int i5 = this.k3;
            jArr[i + 2] = (j5 ^ (j3 >>> i4)) ^ (j4 << i5);
            long j6 = (jArr2[8] >>> i2) ^ (jArr2[9] << i3);
            jArr[i + 3] = ((j4 >>> i4) ^ (jArr2[3] ^ j6)) ^ (j6 << i5);
            long j7 = (jArr2[9] >>> i2) ^ (jArr2[10] << i3);
            jArr[i + 4] = ((j6 >>> i4) ^ (jArr2[4] ^ j7)) ^ (j7 << i5);
            long j8 = (jArr2[10] >>> i2) ^ (jArr2[11] << i3);
            long j9 = jArr2[5];
            long j10 = (((j9 >>> i2) ^ (jArr2[6] << i3)) ^ (j7 >>> this.k364ki)) ^ (j8 << this.k3_ki);
            jArr[i + 5] = ((j7 >>> i4) ^ (j9 ^ j8)) & this.mask;
            jArr[i] = (jArr2[0] ^ j10) ^ (j10 << i5);
            jArr[i + 1] = (j3 << i5) ^ ((jArr2[1] ^ j3) ^ (j10 >>> i4));
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[6];
            int i2 = this.ki;
            long j2 = jArr2[7];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = (j2 >>> i2) ^ (jArr2[8] << i3);
            int i4 = i + 2;
            long j5 = jArr[i4];
            long j6 = jArr2[2] ^ j4;
            int i5 = this.k364;
            int i6 = this.k3;
            jArr[i4] = j5 ^ ((j6 ^ (j3 >>> i5)) ^ (j4 << i6));
            long j7 = (jArr2[8] >>> i2) ^ (jArr2[9] << i3);
            int i7 = i + 3;
            jArr[i7] = (((jArr2[3] ^ j7) ^ (j4 >>> i5)) ^ (j7 << i6)) ^ jArr[i7];
            long j8 = (jArr2[9] >>> i2) ^ (jArr2[10] << i3);
            int i8 = i + 4;
            jArr[i8] = (((jArr2[4] ^ j8) ^ (j7 >>> i5)) ^ (j8 << i6)) ^ jArr[i8];
            long j9 = (jArr2[10] >>> i2) ^ (jArr2[11] << i3);
            int i9 = i + 5;
            jArr[i9] = (((jArr2[5] ^ j9) ^ (j8 >>> i5)) & this.mask) ^ jArr[i9];
            long j10 = (((jArr2[5] >>> i2) ^ (jArr2[6] << i3)) ^ (j8 >>> this.k364ki)) ^ (j9 << this.k3_ki);
            jArr[i] = jArr[i] ^ ((jArr2[0] ^ j10) ^ (j10 << i6));
            int i10 = i + 1;
            jArr[i10] = (((j10 >>> i5) ^ (jArr2[1] ^ j3)) ^ (j3 << i6)) ^ jArr[i10];
        }
    }

    public static class REM384_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int k364ki;
        private final int k3_ki;

        public REM384_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.k364ki = i4 + i2;
            this.k3_ki = i - i2;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[7];
            int i2 = this.ki;
            long j2 = jArr2[8];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[9];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = jArr2[10];
            long j7 = (j4 >>> i2) ^ (j6 << i3);
            long j8 = (j6 >>> i2) ^ (jArr2[11] << i3);
            long j9 = jArr2[5] >>> i2;
            long j10 = jArr2[6];
            int i4 = this.k364ki;
            int i5 = this.k3_ki;
            long j11 = ((j9 ^ (j10 << i3)) ^ (j5 >>> i4)) ^ (j7 << i5);
            long j12 = (((j10 >>> i2) ^ (j << i3)) ^ (j7 >>> i4)) ^ (j8 << i5);
            jArr[i] = jArr2[0] ^ j11;
            long j13 = jArr2[1] ^ j12;
            int i6 = this.k3;
            jArr[i + 1] = j13 ^ (j11 << i6);
            long j14 = jArr2[2] ^ j3;
            int i7 = this.k364;
            jArr[i + 2] = (j14 ^ (j11 >>> i7)) ^ (j12 << i6);
            jArr[i + 3] = ((j12 >>> i7) ^ (jArr2[3] ^ j5)) ^ (j3 << i6);
            jArr[i + 4] = ((jArr2[4] ^ j7) ^ (j3 >>> i7)) ^ (j5 << i6);
            jArr[i + 5] = ((jArr2[5] ^ j8) ^ (j5 >>> i7)) & this.mask;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[7];
            int i2 = this.ki;
            long j2 = jArr2[8];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[9];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = jArr2[10];
            long j7 = (j4 >>> i2) ^ (j6 << i3);
            long j8 = (j6 >>> i2) ^ (jArr2[11] << i3);
            long j9 = jArr2[5] >>> i2;
            long j10 = jArr2[6];
            int i4 = this.k364ki;
            int i5 = this.k3_ki;
            long j11 = ((j9 ^ (j10 << i3)) ^ (j5 >>> i4)) ^ (j7 << i5);
            long j12 = (((j10 >>> i2) ^ (j << i3)) ^ (j7 >>> i4)) ^ (j8 << i5);
            jArr[i] = jArr[i] ^ (jArr2[0] ^ j11);
            int i6 = i + 1;
            long j13 = jArr[i6];
            long j14 = jArr2[1] ^ j12;
            int i7 = this.k3;
            jArr[i6] = j13 ^ (j14 ^ (j11 << i7));
            int i8 = i + 2;
            long j15 = jArr[i8];
            long j16 = jArr2[2] ^ j3;
            int i9 = this.k364;
            jArr[i8] = j15 ^ ((j16 ^ (j11 >>> i9)) ^ (j12 << i7));
            int i10 = i + 3;
            jArr[i10] = (((jArr2[3] ^ j5) ^ (j12 >>> i9)) ^ (j3 << i7)) ^ jArr[i10];
            int i11 = i + 4;
            jArr[i11] = jArr[i11] ^ (((j3 >>> i9) ^ (j7 ^ jArr2[4])) ^ (j5 << i7));
            int i12 = i + 5;
            jArr[i12] = jArr[i12] ^ (((jArr2[5] ^ j8) ^ (j5 >>> i9)) & this.mask);
        }
    }

    public static class REM384_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;
        private final int ki_k3;

        public REM384_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
            this.ki_k3 = i2 - i;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[5];
            int i2 = this.ki;
            long j2 = jArr2[6];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[7];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = jArr2[8];
            long j7 = (j4 >>> i2) ^ (j6 << i3);
            long j8 = jArr2[9];
            long j9 = (j6 >>> i2) ^ (j8 << i3);
            long j10 = jArr2[10];
            long j11 = (j8 >>> i2) ^ (j10 << i3);
            long j12 = (j10 >>> i2) ^ (jArr2[11] << i3);
            long j13 = j3 ^ (j12 >>> this.ki_k3);
            long j14 = jArr2[0] ^ j13;
            int i4 = this.k3;
            jArr[i] = j14 ^ (j13 << i4);
            long j15 = jArr2[1] ^ j5;
            int i5 = this.k364;
            jArr[i + 1] = (j15 ^ (j3 >>> i5)) ^ (j5 << i4);
            jArr[i + 2] = ((jArr2[2] ^ j7) ^ (j5 >>> i5)) ^ (j7 << i4);
            jArr[i + 3] = ((jArr2[3] ^ j9) ^ (j7 >>> i5)) ^ (j9 << i4);
            jArr[i + 4] = ((jArr2[4] ^ j11) ^ (j9 >>> i5)) ^ (j11 << i4);
            jArr[i + 5] = this.mask & ((j12 << i4) ^ ((jArr2[5] ^ j12) ^ (j11 >>> i5)));
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[5];
            int i2 = this.ki;
            long j2 = jArr2[6];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[7];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = jArr2[8];
            long j7 = (j4 >>> i2) ^ (j6 << i3);
            long j8 = jArr2[9];
            long j9 = (j6 >>> i2) ^ (j8 << i3);
            long j10 = jArr2[10];
            long j11 = (j8 >>> i2) ^ (j10 << i3);
            long j12 = (j10 >>> i2) ^ (jArr2[11] << i3);
            long j13 = j3 ^ (j12 >>> this.ki_k3);
            long j14 = jArr[i];
            long j15 = jArr2[0] ^ j13;
            int i4 = this.k3;
            jArr[i] = j14 ^ (j15 ^ (j13 << i4));
            int i5 = i + 1;
            long j16 = jArr[i5];
            long j17 = jArr2[1] ^ j5;
            int i6 = this.k364;
            jArr[i5] = j16 ^ ((j17 ^ (j3 >>> i6)) ^ (j5 << i4));
            int i7 = i + 2;
            jArr[i7] = jArr[i7] ^ (((jArr2[2] ^ j7) ^ (j5 >>> i6)) ^ (j7 << i4));
            int i8 = i + 3;
            jArr[i8] = jArr[i8] ^ (((jArr2[3] ^ j9) ^ (j7 >>> i6)) ^ (j9 << i4));
            int i9 = i + 4;
            jArr[i9] = jArr[i9] ^ (((jArr2[4] ^ j11) ^ (j9 >>> i6)) ^ (j11 << i4));
            int i10 = i + 5;
            jArr[i10] = (this.mask & ((j12 << i4) ^ ((jArr2[5] ^ j12) ^ (j11 >>> i6)))) ^ jArr[i10];
        }
    }

    public static class REM402_SPECIALIZED_TRINOMIAL_GF2X extends Rem_GF2n {
        private final int k3;
        private final int k364;

        public REM402_SPECIALIZED_TRINOMIAL_GF2X(int i, int i2, int i3, int i4, long j) {
            this.k3 = i;
            this.ki = i2;
            this.ki64 = i3;
            this.k364 = i4;
            this.mask = j;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[9];
            int i2 = this.ki;
            long j2 = jArr2[10];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[11];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = jArr2[12];
            long j7 = (j4 >>> i2) ^ (j6 << i3);
            long j8 = j6 >>> i2;
            long j9 = ((j3 >>> 39) ^ (j5 << 25)) ^ (jArr2[6] >>> i2);
            long j10 = jArr2[7];
            long j11 = j9 ^ (j10 << i3);
            long j12 = jArr2[8];
            long j13 = (((j5 >>> 39) ^ (j7 << 25)) ^ (j10 >>> i2)) ^ (j12 << i3);
            long j14 = (((j7 >>> 39) ^ (j8 << 25)) ^ (j12 >>> i2)) ^ (j << i3);
            jArr[i] = jArr2[0] ^ j11;
            jArr[i + 1] = jArr2[1] ^ j13;
            long j15 = jArr2[2] ^ j14;
            int i4 = this.k3;
            jArr[i + 2] = j15 ^ (j11 << i4);
            long j16 = jArr2[3] ^ j3;
            int i5 = this.k364;
            jArr[i + 3] = (j16 ^ (j11 >>> i5)) ^ (j13 << i4);
            jArr[i + 4] = ((j5 ^ jArr2[4]) ^ (j13 >>> i5)) ^ (j14 << i4);
            jArr[i + 5] = ((j14 >>> i5) ^ (jArr2[5] ^ j7)) ^ (j3 << i4);
            jArr[i + 6] = ((jArr2[6] ^ j8) ^ (j3 >>> i5)) & this.mask;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[9];
            int i2 = this.ki;
            long j2 = jArr2[10];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[11];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = jArr2[12];
            long j7 = (j4 >>> i2) ^ (j6 << i3);
            long j8 = j6 >>> i2;
            long j9 = ((j3 >>> 39) ^ (j5 << 25)) ^ (jArr2[6] >>> i2);
            long j10 = jArr2[7];
            long j11 = j9 ^ (j10 << i3);
            long j12 = jArr2[8];
            long j13 = (((j5 >>> 39) ^ (j7 << 25)) ^ (j10 >>> i2)) ^ (j12 << i3);
            long j14 = (((j7 >>> 39) ^ (j8 << 25)) ^ (j12 >>> i2)) ^ (j << i3);
            jArr[i] = jArr[i] ^ (jArr2[0] ^ j11);
            int i4 = i + 1;
            jArr[i4] = jArr[i4] ^ (jArr2[1] ^ j13);
            int i5 = i + 2;
            long j15 = jArr[i5];
            long j16 = jArr2[2] ^ j14;
            int i6 = this.k3;
            jArr[i5] = j15 ^ (j16 ^ (j11 << i6));
            int i7 = i + 3;
            long j17 = jArr[i7];
            long j18 = jArr2[3] ^ j3;
            int i8 = this.k364;
            jArr[i7] = j17 ^ ((j18 ^ (j11 >>> i8)) ^ (j13 << i6));
            int i9 = i + 4;
            jArr[i9] = (((jArr2[4] ^ j5) ^ (j13 >>> i8)) ^ (j14 << i6)) ^ jArr[i9];
            int i10 = i + 5;
            jArr[i10] = (((j14 >>> i8) ^ (j7 ^ jArr2[5])) ^ (j3 << i6)) ^ jArr[i10];
            int i11 = i + 6;
            jArr[i11] = jArr[i11] ^ (((j3 >>> i8) ^ (jArr2[6] ^ j8)) & this.mask);
        }
    }

    public static class REM544_PENTANOMIAL_GF2X extends Rem_GF2n {
        private final int k1;
        private final int k164;
        private final int k2;
        private final int k264;
        private final int k3;
        private final int k364;
        private final int ki_k1;
        private final int ki_k2;
        private final int ki_k3;

        public REM544_PENTANOMIAL_GF2X(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
            this.k1 = i;
            this.k2 = i2;
            this.k3 = i3;
            this.ki = i4;
            this.ki64 = i5;
            this.k164 = i6;
            this.k264 = i7;
            this.k364 = i8;
            this.mask = j;
            this.ki_k3 = i4 - i3;
            this.ki_k2 = i4 - i2;
            this.ki_k1 = i4 - i;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[16];
            int i2 = this.ki;
            long j2 = j >>> i2;
            long j3 = jArr2[8] >>> i2;
            long j4 = jArr2[9];
            int i3 = this.ki64;
            long j5 = j3 ^ (j4 << i3);
            long j6 = (j4 >>> i2) ^ (jArr2[10] << i3);
            long j7 = jArr2[1] ^ j6;
            int i4 = this.k164;
            int i5 = this.k1;
            int i6 = this.k264;
            int i7 = this.k2;
            long j8 = (((j7 ^ (j5 >>> i4)) ^ (j6 << i5)) ^ (j5 >>> i6)) ^ (j6 << i7);
            int i8 = this.k364;
            long j9 = j8 ^ (j5 >>> i8);
            int i9 = this.k3;
            jArr[i + 1] = j9 ^ (j6 << i9);
            long j10 = j5 ^ (((j2 >>> this.ki_k3) ^ (j2 >>> this.ki_k2)) ^ (j2 >>> this.ki_k1));
            jArr[i] = (j10 << i9) ^ (((jArr2[0] ^ j10) ^ (j10 << i5)) ^ (j10 << i7));
            long j11 = (jArr2[10] >>> i2) ^ (jArr2[11] << i3);
            jArr[i + 2] = ((((((jArr2[2] ^ j11) ^ (j6 >>> i4)) ^ (j11 << i5)) ^ (j6 >>> i6)) ^ (j11 << i7)) ^ (j6 >>> i8)) ^ (j11 << i9);
            long j12 = (jArr2[11] >>> i2) ^ (jArr2[12] << i3);
            jArr[i + 3] = ((((((jArr2[3] ^ j12) ^ (j11 >>> i4)) ^ (j12 << i5)) ^ (j11 >>> i6)) ^ (j12 << i7)) ^ (j11 >>> i8)) ^ (j12 << i9);
            long j13 = (jArr2[12] >>> i2) ^ (jArr2[13] << i3);
            jArr[i + 4] = ((((((jArr2[4] ^ j13) ^ (j12 >>> i4)) ^ (j13 << i5)) ^ (j12 >>> i6)) ^ (j13 << i7)) ^ (j12 >>> i8)) ^ (j13 << i9);
            long j14 = (jArr2[13] >>> i2) ^ (jArr2[14] << i3);
            jArr[i + 5] = ((((((jArr2[5] ^ j14) ^ (j13 >>> i4)) ^ (j14 << i5)) ^ (j13 >>> i6)) ^ (j14 << i7)) ^ (j13 >>> i8)) ^ (j14 << i9);
            long j15 = (jArr2[14] >>> i2) ^ (jArr2[15] << i3);
            jArr[i + 6] = ((((((jArr2[6] ^ j15) ^ (j14 >>> i4)) ^ (j15 << i5)) ^ (j14 >>> i6)) ^ (j15 << i7)) ^ (j14 >>> i8)) ^ (j15 << i9);
            long j16 = (jArr2[15] >>> i2) ^ (jArr2[16] << i3);
            jArr[i + 7] = ((j15 >>> i8) ^ (((((jArr2[7] ^ j16) ^ (j15 >>> i4)) ^ (j16 << i5)) ^ (j15 >>> i6)) ^ (j16 << i7))) ^ (j16 << i9);
            jArr[i + 8] = ((j2 << i9) ^ ((((((jArr2[8] ^ j2) ^ (j16 >>> i4)) ^ (j2 << i5)) ^ (j16 >>> i6)) ^ (j2 << i7)) ^ (j16 >>> i8))) & this.mask;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[16];
            int i2 = this.ki;
            long j2 = j >>> i2;
            long j3 = jArr2[8] >>> i2;
            long j4 = jArr2[9];
            int i3 = this.ki64;
            long j5 = j3 ^ (j4 << i3);
            long j6 = (j4 >>> i2) ^ (jArr2[10] << i3);
            int i4 = i + 1;
            long j7 = jArr[i4];
            long j8 = jArr2[1] ^ j6;
            int i5 = this.k164;
            int i6 = this.k1;
            int i7 = this.k264;
            long j9 = ((j8 ^ (j5 >>> i5)) ^ (j6 << i6)) ^ (j5 >>> i7);
            int i8 = this.k2;
            long j10 = j9 ^ (j6 << i8);
            int i9 = this.k364;
            long j11 = j10 ^ (j5 >>> i9);
            int i10 = this.k3;
            jArr[i4] = j7 ^ (j11 ^ (j6 << i10));
            long j12 = j5 ^ (((j2 >>> this.ki_k3) ^ (j2 >>> this.ki_k2)) ^ (j2 >>> this.ki_k1));
            jArr[i] = ((j12 << i10) ^ (((jArr2[0] ^ j12) ^ (j12 << i6)) ^ (j12 << i8))) ^ jArr[i];
            long j13 = (jArr2[10] >>> i2) ^ (jArr2[11] << i3);
            int i11 = i + 2;
            jArr[i11] = (((((((jArr2[2] ^ j13) ^ (j6 >>> i5)) ^ (j13 << i6)) ^ (j6 >>> i7)) ^ (j13 << i8)) ^ (j6 >>> i9)) ^ (j13 << i10)) ^ jArr[i11];
            long j14 = (jArr2[11] >>> i2) ^ (jArr2[12] << i3);
            int i12 = i + 3;
            jArr[i12] = (((((((jArr2[3] ^ j14) ^ (j13 >>> i5)) ^ (j14 << i6)) ^ (j13 >>> i7)) ^ (j14 << i8)) ^ (j13 >>> i9)) ^ (j14 << i10)) ^ jArr[i12];
            long j15 = (jArr2[12] >>> i2) ^ (jArr2[13] << i3);
            int i13 = i + 4;
            jArr[i13] = (((((((jArr2[4] ^ j15) ^ (j14 >>> i5)) ^ (j15 << i6)) ^ (j14 >>> i7)) ^ (j15 << i8)) ^ (j14 >>> i9)) ^ (j15 << i10)) ^ jArr[i13];
            long j16 = (jArr2[13] >>> i2) ^ (jArr2[14] << i3);
            int i14 = i + 5;
            jArr[i14] = (((((((jArr2[5] ^ j16) ^ (j15 >>> i5)) ^ (j16 << i6)) ^ (j15 >>> i7)) ^ (j16 << i8)) ^ (j15 >>> i9)) ^ (j16 << i10)) ^ jArr[i14];
            long j17 = (jArr2[14] >>> i2) ^ (jArr2[15] << i3);
            int i15 = i + 6;
            jArr[i15] = (((((((jArr2[6] ^ j17) ^ (j16 >>> i5)) ^ (j17 << i6)) ^ (j16 >>> i7)) ^ (j17 << i8)) ^ (j16 >>> i9)) ^ (j17 << i10)) ^ jArr[i15];
            long j18 = (jArr2[15] >>> i2) ^ (jArr2[16] << i3);
            int i16 = i + 7;
            jArr[i16] = (((j17 >>> i9) ^ (((((jArr2[7] ^ j18) ^ (j17 >>> i5)) ^ (j18 << i6)) ^ (j17 >>> i7)) ^ (j18 << i8))) ^ (j18 << i10)) ^ jArr[i16];
            int i17 = i + 8;
            jArr[i17] = (((j2 << i10) ^ ((j18 >>> i9) ^ (((j18 >>> i7) ^ (((jArr2[8] ^ j2) ^ (j18 >>> i5)) ^ (j2 << i6))) ^ (j2 << i8)))) & this.mask) ^ jArr[i17];
        }
    }

    public static class REM544_PENTANOMIAL_K3_IS_128_GF2X extends Rem_GF2n {
        private final int k1;
        private final int k164;
        private final int k2;
        private final int k264;

        public REM544_PENTANOMIAL_K3_IS_128_GF2X(int i, int i2, int i3, int i4, int i5, int i6, long j) {
            this.k1 = i;
            this.k2 = i2;
            this.ki = i3;
            this.ki64 = i4;
            this.k164 = i5;
            this.k264 = i6;
            this.mask = j;
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[10];
            int i2 = this.ki;
            long j2 = jArr2[11];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[12];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = (j4 >>> i2) ^ (jArr2[13] << i3);
            long j7 = (jArr2[4] ^ j6) ^ j3;
            int i4 = this.k164;
            int i5 = this.k1;
            int i6 = this.k264;
            int i7 = this.k2;
            jArr[i + 4] = (((j7 ^ (j5 >>> i4)) ^ (j6 << i5)) ^ (j5 >>> i6)) ^ (j6 << i7);
            long j8 = (jArr2[13] >>> i2) ^ (jArr2[14] << i3);
            jArr[i + 5] = (((((jArr2[5] ^ j8) ^ j5) ^ (j6 >>> i4)) ^ (j8 << i5)) ^ (j6 >>> i6)) ^ (j8 << i7);
            long j9 = (jArr2[14] >>> i2) ^ (jArr2[15] << i3);
            jArr[i + 6] = (((((jArr2[6] ^ j9) ^ j6) ^ (j8 >>> i4)) ^ (j9 << i5)) ^ (j8 >>> i6)) ^ (j9 << i7);
            long j10 = (jArr2[15] >>> i2) ^ (jArr2[16] << i3);
            jArr[i + 7] = (((((jArr2[7] ^ j10) ^ j8) ^ (j9 >>> i4)) ^ (j10 << i5)) ^ (j9 >>> i6)) ^ (j10 << i7);
            long j11 = jArr2[16] >>> i2;
            jArr[i + 8] = ((((((jArr2[8] ^ j11) ^ j9) ^ (j10 >>> i4)) ^ (j11 << i5)) ^ (j10 >>> i6)) ^ (j11 << i7)) & this.mask;
            long j12 = (jArr2[8] ^ j9) >>> i2;
            long j13 = jArr2[9];
            long j14 = (j12 ^ ((j13 ^ j10) << i3)) ^ (jArr2[16] >>> i6);
            long j15 = ((j13 ^ j10) >>> i2) ^ ((jArr2[10] ^ j11) << i3);
            jArr[i] = ((jArr2[0] ^ j14) ^ (j14 << i5)) ^ (j14 << i7);
            jArr[i + 1] = ((((jArr2[1] ^ j15) ^ (j14 >>> i4)) ^ (j15 << i5)) ^ (j14 >>> i6)) ^ (j15 << i7);
            jArr[i + 2] = (((((jArr2[2] ^ j3) ^ j14) ^ (j15 >>> i4)) ^ (j3 << i5)) ^ (j15 >>> i6)) ^ (j3 << i7);
            jArr[i + 3] = ((j3 >>> i6) ^ (((j15 ^ (jArr2[3] ^ j5)) ^ (j3 >>> i4)) ^ (j5 << i5))) ^ (j5 << i7);
        }

        @Override // org.bouncycastle.pqc.crypto.gemss.Rem_GF2n
        public void rem_gf2n_xor(long[] jArr, int i, long[] jArr2) {
            long j = jArr2[10];
            int i2 = this.ki;
            long j2 = jArr2[11];
            int i3 = this.ki64;
            long j3 = (j >>> i2) ^ (j2 << i3);
            long j4 = jArr2[12];
            long j5 = (j2 >>> i2) ^ (j4 << i3);
            long j6 = (j4 >>> i2) ^ (jArr2[13] << i3);
            int i4 = i + 4;
            long j7 = jArr[i4];
            long j8 = (jArr2[4] ^ j6) ^ j3;
            int i5 = this.k164;
            int i6 = this.k1;
            long j9 = (j8 ^ (j5 >>> i5)) ^ (j6 << i6);
            int i7 = this.k264;
            long j10 = j9 ^ (j5 >>> i7);
            int i8 = this.k2;
            jArr[i4] = j7 ^ (j10 ^ (j6 << i8));
            long j11 = (jArr2[13] >>> i2) ^ (jArr2[14] << i3);
            int i9 = i + 5;
            jArr[i9] = jArr[i9] ^ ((((((jArr2[5] ^ j11) ^ j5) ^ (j6 >>> i5)) ^ (j11 << i6)) ^ (j6 >>> i7)) ^ (j11 << i8));
            long j12 = (jArr2[14] >>> i2) ^ (jArr2[15] << i3);
            int i10 = i + 6;
            jArr[i10] = jArr[i10] ^ ((((((jArr2[6] ^ j12) ^ j6) ^ (j11 >>> i5)) ^ (j12 << i6)) ^ (j11 >>> i7)) ^ (j12 << i8));
            long j13 = (jArr2[15] >>> i2) ^ (jArr2[16] << i3);
            int i11 = i + 7;
            jArr[i11] = jArr[i11] ^ ((((((jArr2[7] ^ j13) ^ j11) ^ (j12 >>> i5)) ^ (j13 << i6)) ^ (j12 >>> i7)) ^ (j13 << i8));
            long j14 = jArr2[16] >>> i2;
            int i12 = i + 8;
            jArr[i12] = jArr[i12] ^ (((((((jArr2[8] ^ j14) ^ j12) ^ (j13 >>> i5)) ^ (j14 << i6)) ^ (j13 >>> i7)) ^ (j14 << i8)) & this.mask);
            long j15 = (jArr2[8] ^ j12) >>> i2;
            long j16 = jArr2[9];
            long j17 = (j15 ^ ((j16 ^ j13) << i3)) ^ (jArr2[16] >>> i7);
            long j18 = ((j13 ^ j16) >>> i2) ^ ((j14 ^ jArr2[10]) << i3);
            jArr[i] = jArr[i] ^ (((jArr2[0] ^ j17) ^ (j17 << i6)) ^ (j17 << i8));
            int i13 = i + 1;
            jArr[i13] = jArr[i13] ^ (((((jArr2[1] ^ j18) ^ (j17 >>> i5)) ^ (j18 << i6)) ^ (j17 >>> i7)) ^ (j18 << i8));
            int i14 = i + 2;
            jArr[i14] = (((((j17 ^ (jArr2[2] ^ j3)) ^ (j18 >>> i5)) ^ (j3 << i6)) ^ (j18 >>> i7)) ^ (j3 << i8)) ^ jArr[i14];
            int i15 = i + 3;
            jArr[i15] = (((j3 >>> i7) ^ (((j18 ^ (jArr2[3] ^ j5)) ^ (j3 >>> i5)) ^ (j5 << i6))) ^ (j5 << i8)) ^ jArr[i15];
        }
    }

    public abstract void rem_gf2n(long[] jArr, int i, long[] jArr2);

    public abstract void rem_gf2n_xor(long[] jArr, int i, long[] jArr2);
}
