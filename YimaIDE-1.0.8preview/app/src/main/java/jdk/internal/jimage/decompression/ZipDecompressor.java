package jdk.internal.jimage.decompression;

import java.util.zip.Inflater;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ZipDecompressor implements ResourceDecompressor {
    public static byte[] decompress(byte[] bArr, int i, long j) throws Exception {
        if (j > 2147483647L) {
            throw new OutOfMemoryError("Required array size too large");
        }
        int i2 = (int) j;
        byte[] bArr2 = new byte[i2];
        Inflater inflater = new Inflater();
        try {
            inflater.setInput(bArr, i, bArr.length - i);
            int iInflate = 0;
            while (!inflater.finished() && iInflate < j) {
                iInflate += inflater.inflate(bArr2, iInflate, i2 - iInflate);
            }
            inflater.end();
            if (iInflate == j) {
                return bArr2;
            }
            a16.a("Resource content size mismatch");
            return null;
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    @Override // jdk.internal.jimage.decompression.ResourceDecompressor
    public String getName() {
        return ZipDecompressorFactory.NAME;
    }

    @Override // jdk.internal.jimage.decompression.ResourceDecompressor
    public byte[] decompress(ResourceDecompressor.StringsProvider stringsProvider, byte[] bArr, int i, long j) throws Exception {
        return decompress(bArr, i, j);
    }
}
