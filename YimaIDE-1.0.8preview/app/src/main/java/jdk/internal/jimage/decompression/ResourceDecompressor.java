package jdk.internal.jimage.decompression;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ResourceDecompressor {

    public interface StringsProvider {
        String getString(int i);
    }

    byte[] decompress(StringsProvider stringsProvider, byte[] bArr, int i, long j) throws Exception;

    String getName();
}
