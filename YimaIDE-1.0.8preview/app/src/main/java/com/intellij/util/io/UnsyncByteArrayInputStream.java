package com.intellij.util.io;

import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class UnsyncByteArrayInputStream extends InputStream {
    protected final byte[] myBuffer;
    private final int myCount;
    private int myMarkedPosition;
    private int myPosition;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 2) {
            objArr[0] = "buf";
        } else {
            objArr[0] = "b";
        }
        objArr[1] = "com/intellij/util/io/UnsyncByteArrayInputStream";
        if (i != 2) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "read";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public UnsyncByteArrayInputStream(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            $$$reportNull$$$0(1);
        }
        this.myBuffer = bArr;
        this.myPosition = i;
        this.myCount = Math.min(i2 + i, bArr.length);
        this.myMarkedPosition = i;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.myCount - this.myPosition;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.myMarkedPosition = this.myPosition;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            $$$reportNull$$$0(2);
        }
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            qc6.a();
            return 0;
        }
        int i3 = this.myPosition;
        int i4 = this.myCount;
        if (i3 >= i4) {
            return -1;
        }
        if (i3 + i2 > i4) {
            i2 = i4 - i3;
        }
        if (i2 <= 0) {
            return 0;
        }
        System.arraycopy(this.myBuffer, i3, bArr, i, i2);
        this.myPosition += i2;
        return i2;
    }

    public int readShortLittleEndian() {
        int i = this.myPosition;
        if (i >= this.myCount - 1) {
            return -1;
        }
        byte[] bArr = this.myBuffer;
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        this.myPosition = i + 2;
        return (b & 255) | ((b2 << 8) & 65280);
    }

    @Override // java.io.InputStream
    public void reset() {
        this.myPosition = this.myMarkedPosition;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        int i = this.myPosition;
        long j2 = ((long) i) + j;
        int i2 = this.myCount;
        if (j2 > i2) {
            j = i2 - i;
        }
        if (j < 0) {
            return 0L;
        }
        this.myPosition = (int) (((long) i) + j);
        return j;
    }

    public String toString() {
        return getClass() + " (" + available() + " bytes available out of " + this.myCount + ")";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UnsyncByteArrayInputStream(byte[] bArr) {
        this(bArr, 0, bArr.length);
        if (bArr == null) {
            $$$reportNull$$$0(0);
        }
    }

    @Override // java.io.InputStream
    public int read() {
        int i = this.myPosition;
        if (i >= this.myCount) {
            return -1;
        }
        byte[] bArr = this.myBuffer;
        this.myPosition = i + 1;
        return bArr[i] & 255;
    }
}
