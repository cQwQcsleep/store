package com.intellij.util.io.keyStorage;

import androidx.collection.SieveCacheKt;
import com.intellij.util.io.ResizeableMappedFile;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class MappedFileInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean checkAccess;
    private final long limit;
    private long position;
    private final ResizeableMappedFile raf;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "raf";
        } else {
            objArr[0] = "buffer";
        }
        objArr[1] = "com/intellij/util/io/keyStorage/MappedFileInputStream";
        if (i != 1) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "read";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public MappedFileInputStream(ResizeableMappedFile resizeableMappedFile, long j, long j2, boolean z) {
        if (resizeableMappedFile == null) {
            $$$reportNull$$$0(0);
        }
        long j3 = j2 - j;
        if (j3 > SieveCacheKt.NodeLinkMask) {
            throw new IllegalArgumentException("limit(=" + j2 + ")-position(=" + j + ") = " + j3 + " > MAX_INT");
        }
        long length = resizeableMappedFile.length();
        if (j2 <= length) {
            this.raf = resizeableMappedFile;
            this.position = (int) j;
            this.limit = j2;
            this.checkAccess = z;
            return;
        }
        throw new IllegalArgumentException("limit(=" + j2 + ") > file.length(=" + length + ")");
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) (this.limit - this.position);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            $$$reportNull$$$0(1);
        }
        if (i2 == 0) {
            return 0;
        }
        int iAvailable = available();
        if (iAvailable == 0) {
            return -1;
        }
        int i3 = i2 > iAvailable ? iAvailable : i2;
        this.raf.get(this.position, bArr, i, i3, this.checkAccess);
        this.position += (long) i3;
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        long jMin = Math.min(j, available());
        this.position += jMin;
        return jMin;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        long j = this.position;
        if (j >= this.limit) {
            return -1;
        }
        byte b = this.raf.get(j, this.checkAccess);
        this.position++;
        return b & 255;
    }
}
