package jdk.internal.jimage;

import io.github.rosemoe.sora.langs.textmate.folding.IndentRange;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ImageHeader {
    private static final int HEADER_SLOTS = 7;
    public static final int MAGIC = -889267494;
    public static final int MAJOR_VERSION = 1;
    public static final int MINOR_VERSION = 0;
    private final int flags;
    private final int locationsSize;
    private final int magic;
    private final int majorVersion;
    private final int minorVersion;
    private final int resourceCount;
    private final int stringsSize;
    private final int tableLength;

    public ImageHeader(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.magic = i;
        this.majorVersion = i2;
        this.minorVersion = i3;
        this.flags = i4;
        this.resourceCount = i5;
        this.tableLength = i6;
        this.locationsSize = i7;
        this.stringsSize = i8;
    }

    public static int getHeaderSize() {
        return 28;
    }

    public static ImageHeader readFrom(IntBuffer intBuffer) {
        Objects.requireNonNull(intBuffer);
        if (intBuffer.capacity() == 7) {
            int i = intBuffer.get(0);
            int i2 = intBuffer.get(1);
            return new ImageHeader(i, i2 >>> 16, i2 & IndentRange.MAX_FOLDING_REGIONS, intBuffer.get(2), intBuffer.get(3), intBuffer.get(4), intBuffer.get(5), intBuffer.get(6));
        }
        throw new InternalError("jimage header not the correct size: " + intBuffer.capacity());
    }

    public int getFlags() {
        return this.flags;
    }

    public int getIndexSize() {
        return getHeaderSize() + getRedirectSize() + getOffsetsSize() + getLocationsSize() + getStringsSize();
    }

    public int getLocationsOffset() {
        return getOffsetsOffset() + getOffsetsSize();
    }

    public int getLocationsSize() {
        return this.locationsSize;
    }

    public int getMagic() {
        return this.magic;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public int getOffsetsOffset() {
        return getRedirectOffset() + getRedirectSize();
    }

    public int getOffsetsSize() {
        return this.tableLength * 4;
    }

    public int getRedirectOffset() {
        return getHeaderSize();
    }

    public int getRedirectSize() {
        return this.tableLength * 4;
    }

    public int getResourceCount() {
        return this.resourceCount;
    }

    public int getStringsOffset() {
        return getLocationsOffset() + getLocationsSize();
    }

    public int getStringsSize() {
        return this.stringsSize;
    }

    public int getTableLength() {
        return this.tableLength;
    }

    public void writeTo(ByteBuffer byteBuffer) {
        Objects.requireNonNull(byteBuffer);
        byteBuffer.putInt(this.magic);
        byteBuffer.putInt((this.majorVersion << 16) | this.minorVersion);
        byteBuffer.putInt(this.flags);
        byteBuffer.putInt(this.resourceCount);
        byteBuffer.putInt(this.tableLength);
        byteBuffer.putInt(this.locationsSize);
        byteBuffer.putInt(this.stringsSize);
    }

    public ImageHeader(int i, int i2, int i3, int i4) {
        this(MAGIC, 1, 0, 0, i, i2, i3, i4);
    }

    public void writeTo(ImageStream imageStream) {
        Objects.requireNonNull(imageStream);
        imageStream.ensure(getHeaderSize());
        writeTo(imageStream.getBuffer());
    }
}
