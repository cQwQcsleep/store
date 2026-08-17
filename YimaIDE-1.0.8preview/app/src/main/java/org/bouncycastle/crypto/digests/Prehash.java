package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.io.LimitedBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Prehash implements Digest {
    private final String algorithmName;
    private final LimitedBuffer buf;

    private Prehash(Digest digest) {
        this.algorithmName = digest.getAlgorithmName();
        this.buf = new LimitedBuffer(digest.getDigestSize());
    }

    public static Prehash forDigest(Digest digest) {
        return new Prehash(digest);
    }

    public int doFinal(byte[] bArr, int i) {
        try {
            if (getDigestSize() != this.buf.size()) {
                throw new IllegalStateException("Incorrect prehash size");
            }
            int iCopyTo = this.buf.copyTo(bArr, i);
            reset();
            return iCopyTo;
        } catch (Throwable th) {
            reset();
            throw th;
        }
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public int getDigestSize() {
        return this.buf.limit();
    }

    public void reset() {
        this.buf.reset();
    }

    public void update(byte b) {
        this.buf.write(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.buf.write(bArr, i, i2);
    }
}
