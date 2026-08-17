package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ERSData {
    byte[] getHash(DigestCalculator digestCalculator, byte[] bArr);
}
