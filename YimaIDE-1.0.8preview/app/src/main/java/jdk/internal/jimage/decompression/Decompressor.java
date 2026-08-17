package jdk.internal.jimage.decompression;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Decompressor {
    private final Map<Integer, ResourceDecompressor> pluginsCache = new HashMap();

    public byte[] decompressResource(ByteOrder byteOrder, ResourceDecompressor.StringsProvider stringsProvider, byte[] bArr) throws Exception {
        ResourceDecompressor.StringsProvider stringsProvider2;
        Objects.requireNonNull(byteOrder);
        Objects.requireNonNull(stringsProvider);
        Objects.requireNonNull(bArr);
        byte[] bArrDecompress = bArr;
        while (true) {
            CompressedResourceHeader fromResource = CompressedResourceHeader.readFromResource(byteOrder, bArrDecompress);
            if (fromResource != null) {
                ResourceDecompressor resourceDecompressor = this.pluginsCache.get(Integer.valueOf(fromResource.getDecompressorNameOffset()));
                if (resourceDecompressor == null) {
                    String string = stringsProvider.getString(fromResource.getDecompressorNameOffset());
                    if (string == null) {
                        a16.a("Plugin name not found");
                        return null;
                    }
                    ResourceDecompressor resourceDecompressorNewResourceDecompressor = ResourceDecompressorRepository.newResourceDecompressor(string);
                    if (resourceDecompressorNewResourceDecompressor == null) {
                        a16.a("Plugin not found: ".concat(string));
                        return null;
                    }
                    this.pluginsCache.put(Integer.valueOf(fromResource.getDecompressorNameOffset()), resourceDecompressorNewResourceDecompressor);
                    resourceDecompressor = resourceDecompressorNewResourceDecompressor;
                }
                try {
                    stringsProvider2 = stringsProvider;
                    bArrDecompress = resourceDecompressor.decompress(stringsProvider2, bArrDecompress, CompressedResourceHeader.getSize(), fromResource.getUncompressedSize());
                } catch (Exception e) {
                    throw new IOException(e);
                }
            } else {
                stringsProvider2 = stringsProvider;
            }
            if (fromResource == null) {
                return bArrDecompress;
            }
            stringsProvider = stringsProvider2;
        }
    }
}
