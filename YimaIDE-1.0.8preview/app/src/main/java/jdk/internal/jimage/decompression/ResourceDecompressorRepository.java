package jdk.internal.jimage.decompression;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ResourceDecompressorRepository {
    private static final Map<String, ResourceDecompressorFactory> factories = new HashMap();

    static {
        registerReaderProvider(new ZipDecompressorFactory());
        registerReaderProvider(new StringSharingDecompressorFactory());
    }

    private ResourceDecompressorRepository() {
    }

    public static ResourceDecompressor newResourceDecompressor(String str) throws IOException {
        ResourceDecompressorFactory resourceDecompressorFactory = factories.get(str);
        if (resourceDecompressorFactory != null) {
            return resourceDecompressorFactory.newDecompressor();
        }
        return null;
    }

    private static void registerReaderProvider(ResourceDecompressorFactory resourceDecompressorFactory) {
        factories.put(resourceDecompressorFactory.getName(), resourceDecompressorFactory);
    }
}
