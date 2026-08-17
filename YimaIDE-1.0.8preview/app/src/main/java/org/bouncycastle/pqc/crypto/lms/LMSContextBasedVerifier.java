package org.bouncycastle.pqc.crypto.lms;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LMSContextBasedVerifier {
    LMSContext generateLMSContext(byte[] bArr);

    boolean verify(LMSContext lMSContext);
}
