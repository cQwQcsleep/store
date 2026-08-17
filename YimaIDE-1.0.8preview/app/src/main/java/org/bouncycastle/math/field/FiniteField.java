package org.bouncycastle.math.field;

import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface FiniteField {
    BigInteger getCharacteristic();

    int getDimension();
}
