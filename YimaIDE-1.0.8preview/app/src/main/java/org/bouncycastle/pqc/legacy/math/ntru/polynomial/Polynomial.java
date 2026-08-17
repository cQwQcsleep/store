package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Polynomial {
    BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial);

    IntegerPolynomial mult(IntegerPolynomial integerPolynomial);

    IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i);

    IntegerPolynomial toIntegerPolynomial();
}
