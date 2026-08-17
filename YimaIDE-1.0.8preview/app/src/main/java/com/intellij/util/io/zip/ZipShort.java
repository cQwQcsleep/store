package com.intellij.util.io.zip;

import androidx.compose.foundation.text.input.internal.PartialGapBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ZipShort implements Cloneable {
    private final int value;

    public ZipShort(int i) {
        this.value = i;
    }

    public static int getValue(byte[] bArr, int i) {
        return ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ZipShort) && this.value == ((ZipShort) obj).getValue();
    }

    public byte[] getBytes() {
        int i = this.value;
        return new byte[]{(byte) (i & PartialGapBuffer.BUF_SIZE), (byte) ((i & 65280) >> 8)};
    }

    public int hashCode() {
        return this.value;
    }

    public int getValue() {
        return this.value;
    }

    public static byte[] getBytes(int i) {
        return new byte[]{(byte) (i & PartialGapBuffer.BUF_SIZE), (byte) ((i & 65280) >> 8)};
    }
}
