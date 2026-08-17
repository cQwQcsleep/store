package org.bouncycastle.pqc.crypto.lms;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LMSContextBasedSigner {
    LMSContext generateLMSContext();

    byte[] generateSignature(LMSContext lMSContext);

    long getUsagesRemaining();
}
