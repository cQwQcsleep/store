package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialRingGF2;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class McElieceParameters implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private Digest digest;
    private int fieldPoly;
    private int m;
    private int n;
    private int t;

    public McElieceParameters(int i, int i2, int i3, Digest digest) {
        this.m = i;
        if (i < 1) {
            w01.a("m must be positive");
            throw null;
        }
        if (i > 32) {
            w01.a(" m is too large");
            throw null;
        }
        int i4 = 1 << i;
        this.n = i4;
        this.t = i2;
        if (i2 < 0) {
            w01.a("t must be positive");
            throw null;
        }
        if (i2 > i4) {
            w01.a("t must be less than n = 2^m");
            throw null;
        }
        if (PolynomialRingGF2.degree(i3) != i || !PolynomialRingGF2.isIrreducible(i3)) {
            w01.a("polynomial is not a field polynomial for GF(2^m)");
            throw null;
        }
        this.fieldPoly = i3;
        this.digest = digest;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public int getT() {
        return this.t;
    }

    public McElieceParameters(int i) {
        this(i, (Digest) null);
    }

    public McElieceParameters(int i, int i2) {
        this(i, i2, (Digest) null);
    }

    public McElieceParameters(int i, int i2, int i3) {
        this(i, i2, i3, null);
    }

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(int i, int i2, Digest digest) {
        if (i < 1) {
            w01.a("m must be positive");
            throw null;
        }
        if (i > 32) {
            w01.a("m is too large");
            throw null;
        }
        this.m = i;
        int i3 = 1 << i;
        this.n = i3;
        if (i2 < 0) {
            w01.a("t must be positive");
            throw null;
        }
        if (i2 > i3) {
            w01.a("t must be less than n = 2^m");
            throw null;
        }
        this.t = i2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i);
        this.digest = digest;
    }

    public McElieceParameters(int i, Digest digest) {
        if (i < 1) {
            w01.a("key size must be positive");
            throw null;
        }
        this.m = 0;
        this.n = 1;
        while (true) {
            int i2 = this.n;
            if (i2 >= i) {
                int i3 = i2 >>> 1;
                this.t = i3;
                int i4 = this.m;
                this.t = i3 / i4;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i4);
                this.digest = digest;
                return;
            }
            this.n = i2 << 1;
            this.m++;
        }
    }

    public McElieceParameters(Digest digest) {
        this(11, 50, digest);
    }
}
