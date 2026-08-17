package org.bouncycastle.pqc.crypto.cmce;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class GF {
    public abstract short gf_frac(short s, short s2);

    public abstract short gf_inv(short s);

    public final short gf_iszero(short s) {
        return (short) ((s - 1) >> 31);
    }

    public abstract short gf_mul(short s, short s2);

    public abstract int gf_mul_ext(short s, short s2);

    public abstract void gf_mul_poly(int i, int[] iArr, short[] sArr, short[] sArr2, short[] sArr3, int[] iArr2);

    public abstract short gf_reduce(int i);

    public abstract short gf_sq(short s);

    public abstract int gf_sq_ext(short s);

    public abstract void gf_sqr_poly(int i, int[] iArr, short[] sArr, short[] sArr2, int[] iArr2);
}
