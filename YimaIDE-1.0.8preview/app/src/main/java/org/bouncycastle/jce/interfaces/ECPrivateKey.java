package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ECPrivateKey extends ECKey, PrivateKey {
    BigInteger getD();
}
