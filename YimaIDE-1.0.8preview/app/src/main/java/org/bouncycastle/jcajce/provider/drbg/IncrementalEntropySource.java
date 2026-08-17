package org.bouncycastle.jcajce.provider.drbg;

import org.bouncycastle.crypto.prng.EntropySource;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
interface IncrementalEntropySource extends EntropySource {
    byte[] getEntropy(long j) throws InterruptedException;
}
