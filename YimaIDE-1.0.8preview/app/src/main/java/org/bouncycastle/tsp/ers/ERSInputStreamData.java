package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.bouncycastle.operator.DigestCalculator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ERSInputStreamData extends ERSCachingData {
    private final InputStream content;

    public ERSInputStreamData(File file) throws FileNotFoundException {
        if (file.isDirectory()) {
            w01.a("directory not allowed");
            throw null;
        }
        this.content = new FileInputStream(file);
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    public byte[] calculateHash(DigestCalculator digestCalculator, byte[] bArr) {
        byte[] bArrCalculateDigest = ERSUtil.calculateDigest(digestCalculator, this.content);
        return bArr != null ? ERSUtil.concatPreviousHashes(digestCalculator, bArr, bArrCalculateDigest) : bArrCalculateDigest;
    }

    public ERSInputStreamData(InputStream inputStream) {
        this.content = inputStream;
    }
}
