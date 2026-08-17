package com.sun.xml.internal.stream.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BufferAllocator {
    private static final int LARGE_SIZE_LIMIT = 8192;
    private static final int MEDIUM_SIZE_LIMIT = 2048;
    private static final int SMALL_SIZE_LIMIT = 128;
    byte[] largeByteBuffer;
    char[] largeCharBuffer;
    byte[] mediumByteBuffer;
    char[] mediumCharBuffer;
    byte[] smallByteBuffer;
    char[] smallCharBuffer;

    public byte[] getByteBuffer(int i) {
        if (i <= 128) {
            byte[] bArr = this.smallByteBuffer;
            this.smallByteBuffer = null;
            return bArr;
        }
        if (i <= 2048) {
            byte[] bArr2 = this.mediumByteBuffer;
            this.mediumByteBuffer = null;
            return bArr2;
        }
        if (i > 8192) {
            return null;
        }
        byte[] bArr3 = this.largeByteBuffer;
        this.largeByteBuffer = null;
        return bArr3;
    }

    public char[] getCharBuffer(int i) {
        if (i <= 128) {
            char[] cArr = this.smallCharBuffer;
            this.smallCharBuffer = null;
            return cArr;
        }
        if (i <= 2048) {
            char[] cArr2 = this.mediumCharBuffer;
            this.mediumCharBuffer = null;
            return cArr2;
        }
        if (i > 8192) {
            return null;
        }
        char[] cArr3 = this.largeCharBuffer;
        this.largeCharBuffer = null;
        return cArr3;
    }

    public void returnByteBuffer(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (bArr.length <= 128) {
            this.smallByteBuffer = bArr;
        } else if (bArr.length <= 2048) {
            this.mediumByteBuffer = bArr;
        } else if (bArr.length <= 8192) {
            this.largeByteBuffer = bArr;
        }
    }

    public void returnCharBuffer(char[] cArr) {
        if (cArr == null) {
            return;
        }
        if (cArr.length <= 128) {
            this.smallCharBuffer = cArr;
        } else if (cArr.length <= 2048) {
            this.mediumCharBuffer = cArr;
        } else if (cArr.length <= 8192) {
            this.largeCharBuffer = cArr;
        }
    }
}
