package org.bouncycastle.pqc.jcajce.provider.lms;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DigestUtil {
    public static byte[] getDigestResult(Digest digest) {
        int digestSize = digest.getDigestSize();
        byte[] bArr = new byte[digestSize];
        if (digest instanceof Xof) {
            ((Xof) digest).doFinal(bArr, 0, digestSize);
            return bArr;
        }
        digest.doFinal(bArr, 0);
        return bArr;
    }
}
