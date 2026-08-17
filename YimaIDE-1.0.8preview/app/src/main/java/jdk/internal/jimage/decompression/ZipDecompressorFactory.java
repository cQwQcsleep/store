package jdk.internal.jimage.decompression;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ZipDecompressorFactory extends ResourceDecompressorFactory {
    public static final String NAME = "zip";

    public ZipDecompressorFactory() {
        super(NAME);
    }

    @Override // jdk.internal.jimage.decompression.ResourceDecompressorFactory
    public ResourceDecompressor newDecompressor() throws IOException {
        return new ZipDecompressor();
    }
}
