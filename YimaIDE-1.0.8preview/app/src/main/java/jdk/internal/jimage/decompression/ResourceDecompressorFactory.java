package jdk.internal.jimage.decompression;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class ResourceDecompressorFactory {
    private final String name;

    public ResourceDecompressorFactory(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public abstract ResourceDecompressor newDecompressor() throws IOException;
}
