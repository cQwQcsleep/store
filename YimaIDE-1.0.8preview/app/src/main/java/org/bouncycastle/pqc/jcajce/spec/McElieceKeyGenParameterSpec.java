package org.bouncycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialRingGF2;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;
    private int m;
    private int n;
    private int t;

    public McElieceKeyGenParameterSpec(int i, int i2, int i3) {
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
        if (PolynomialRingGF2.degree(i3) == i && PolynomialRingGF2.isIrreducible(i3)) {
            this.fieldPoly = i3;
        } else {
            w01.a("polynomial is not a field polynomial for GF(2^m)");
            throw null;
        }
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

    public McElieceKeyGenParameterSpec(int i) {
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
                return;
            }
            this.n = i2 << 1;
            this.m++;
        }
    }

    public McElieceKeyGenParameterSpec(int i, int i2) throws InvalidParameterException {
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
    }

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }
}
