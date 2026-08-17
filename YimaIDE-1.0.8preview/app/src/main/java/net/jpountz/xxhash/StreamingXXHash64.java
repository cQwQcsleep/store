package net.jpountz.xxhash;

import java.io.Closeable;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class StreamingXXHash64 implements Closeable {
    final long seed;

    public interface Factory {
        StreamingXXHash64 newStreamingHash(long j);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public abstract long getValue();

    public String toString() {
        return getClass().getSimpleName() + "(seed=" + this.seed + ")";
    }

    public abstract void update(byte[] bArr, int i, int i2);
}
