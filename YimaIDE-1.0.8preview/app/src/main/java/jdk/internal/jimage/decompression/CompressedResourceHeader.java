package jdk.internal.jimage.decompression;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CompressedResourceHeader {
    private static final int COMPRESSED_OFFSET = 4;
    private static final int DECOMPRESSOR_NAME_OFFSET = 20;
    private static final int IS_TERMINAL_OFFSET = 28;
    public static final int MAGIC = -889259270;
    private static final int MAGIC_OFFSET = 0;
    private static final int SIZE = 29;
    private static final int UNCOMPRESSED_OFFSET = 12;
    private final long compressedSize;
    private final int decompressorNameOffset;
    private final boolean isTerminal;
    private final long uncompressedSize;

    public CompressedResourceHeader(long j, long j2, int i, boolean z) {
        this.compressedSize = j;
        this.uncompressedSize = j2;
        this.decompressorNameOffset = i;
        this.isTerminal = z;
    }

    public static int getSize() {
        return 29;
    }

    public static CompressedResourceHeader readFromResource(ByteOrder byteOrder, byte[] bArr) {
        Objects.requireNonNull(byteOrder);
        Objects.requireNonNull(bArr);
        if (bArr.length < getSize()) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 0, 29);
        byteBufferWrap.order(byteOrder);
        if (byteBufferWrap.getInt(0) != -889259270) {
            return null;
        }
        return new CompressedResourceHeader(byteBufferWrap.getLong(4), byteBufferWrap.getLong(12), byteBufferWrap.getInt(20), byteBufferWrap.get(28) == 1);
    }

    public byte[] getBytes(ByteOrder byteOrder) {
        Objects.requireNonNull(byteOrder);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(29);
        byteBufferAllocate.order(byteOrder);
        byteBufferAllocate.putInt(MAGIC);
        byteBufferAllocate.putLong(this.compressedSize);
        byteBufferAllocate.putLong(this.uncompressedSize);
        byteBufferAllocate.putInt(this.decompressorNameOffset);
        byteBufferAllocate.putInt(-1);
        byteBufferAllocate.put(this.isTerminal ? (byte) 1 : (byte) 0);
        return byteBufferAllocate.array();
    }

    public int getDecompressorNameOffset() {
        return this.decompressorNameOffset;
    }

    public long getResourceSize() {
        return this.compressedSize;
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public boolean isTerminal() {
        return this.isTerminal;
    }
}
