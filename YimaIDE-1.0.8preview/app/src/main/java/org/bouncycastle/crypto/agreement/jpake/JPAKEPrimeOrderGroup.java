package org.bouncycastle.crypto.agreement.jpake;

import defpackage.w01;
import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class JPAKEPrimeOrderGroup {
    private final BigInteger g;
    private final BigInteger p;
    private final BigInteger q;

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, boolean z) {
        JPAKEUtil.validateNotNull(bigInteger, "p");
        JPAKEUtil.validateNotNull(bigInteger2, "q");
        JPAKEUtil.validateNotNull(bigInteger3, "g");
        if (!z) {
            BigInteger bigInteger4 = JPAKEUtil.ONE;
            if (!bigInteger.subtract(bigInteger4).mod(bigInteger2).equals(JPAKEUtil.ZERO)) {
                w01.a("p-1 must be evenly divisible by q");
                throw null;
            }
            if (bigInteger3.compareTo(BigInteger.valueOf(2L)) == -1 || bigInteger3.compareTo(bigInteger.subtract(bigInteger4)) == 1) {
                w01.a("g must be in [2, p-1]");
                throw null;
            }
            if (!bigInteger3.modPow(bigInteger2, bigInteger).equals(bigInteger4)) {
                w01.a("g^q mod p must equal 1");
                throw null;
            }
            if (!bigInteger.isProbablePrime(20)) {
                w01.a("p must be prime");
                throw null;
            }
            if (!bigInteger2.isProbablePrime(20)) {
                w01.a("q must be prime");
                throw null;
            }
        }
        this.p = bigInteger;
        this.q = bigInteger2;
        this.g = bigInteger3;
    }

    public BigInteger getG() {
        return this.g;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, false);
    }
}
