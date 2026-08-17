package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Resultant {
    public BigInteger res;
    public BigIntPolynomial rho;

    public Resultant(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger) {
        this.rho = bigIntPolynomial;
        this.res = bigInteger;
    }
}
