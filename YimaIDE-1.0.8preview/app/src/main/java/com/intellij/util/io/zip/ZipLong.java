package com.intellij.util.io.zip;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ZipLong implements Cloneable {
    private final long value;

    public static byte[] getBytes(long j) {
        return new byte[]{(byte) (255 & j), (byte) ((65280 & j) >> 8), (byte) ((16711680 & j) >> 16), (byte) ((j & 4278190080L) >> 24)};
    }

    public static long getValue(byte[] bArr, int i) {
        return (((long) (bArr[i + 3] << 24)) & 4278190080L) + ((long) ((bArr[i + 2] << 16) & 16711680)) + ((long) ((bArr[i + 1] << 8) & 65280)) + ((long) (bArr[i] & 255));
    }

    public boolean equals(Object obj) {
        return (obj instanceof ZipLong) && this.value == ((ZipLong) obj).getValue();
    }

    public int hashCode() {
        return (int) this.value;
    }

    public long getValue() {
        return this.value;
    }

    public static long getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }
}
