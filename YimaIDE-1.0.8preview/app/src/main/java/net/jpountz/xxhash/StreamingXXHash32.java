package net.jpountz.xxhash;

import java.io.Closeable;
import java.util.zip.Checksum;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class StreamingXXHash32 implements Closeable {
    final int seed;

    public interface Factory {
        StreamingXXHash32 newStreamingHash(int i);
    }

    public final Checksum asChecksum() {
        return new Checksum() { // from class: net.jpountz.xxhash.StreamingXXHash32.1
            @Override // java.util.zip.Checksum
            public long getValue() {
                return ((long) StreamingXXHash32.this.getValue()) & 268435455;
            }

            @Override // java.util.zip.Checksum
            public void reset() {
                StreamingXXHash32.this.reset();
            }

            public String toString() {
                return StreamingXXHash32.this.toString();
            }

            @Override // java.util.zip.Checksum
            public void update(int i) {
                StreamingXXHash32.this.update(new byte[]{(byte) i}, 0, 1);
            }

            @Override // java.util.zip.Checksum
            public void update(byte[] bArr, int i, int i2) {
                StreamingXXHash32.this.update(bArr, i, i2);
            }
        };
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public abstract int getValue();

    public abstract void reset();

    public String toString() {
        return getClass().getSimpleName() + "(seed=" + this.seed + ")";
    }

    public abstract void update(byte[] bArr, int i, int i2);
}
