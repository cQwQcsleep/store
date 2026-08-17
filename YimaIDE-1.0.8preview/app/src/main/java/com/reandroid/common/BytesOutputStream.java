package com.reandroid.common;

import androidx.core.internal.view.SupportMenu;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BytesOutputStream extends ByteArrayOutputStream {
    private int mLastGrow;

    public BytesOutputStream(int i) {
        super(check(i));
    }

    private static int check(int i) {
        if (i >= 0) {
            return i;
        }
        qf1.a("Negative: ", i);
        return 0;
    }

    private void ensureCapacity(int i) {
        if (i - ((ByteArrayOutputStream) this).buf.length > 0) {
            grow(i);
        }
    }

    private void grow(int i) {
        if (this.mLastGrow == 0) {
            this.mLastGrow = 1 << 1;
        }
        int i2 = this.mLastGrow << 1;
        this.mLastGrow = i2;
        if (i2 > 65535) {
            this.mLastGrow = SupportMenu.USER_MASK;
        }
        byte[] bArr = ((ByteArrayOutputStream) this).buf;
        int length = bArr.length;
        int i3 = this.mLastGrow + i;
        if (i3 - i >= 0) {
            i = i3;
        }
        byte[] bArr2 = new byte[i];
        for (int i4 = 0; i4 < length; i4++) {
            bArr2[i4] = bArr[i4];
        }
        ((ByteArrayOutputStream) this).buf = bArr2;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        toByteArray();
    }

    public int position() {
        return size();
    }

    @Override // java.io.ByteArrayOutputStream
    public synchronized byte[] toByteArray() {
        int i = ((ByteArrayOutputStream) this).count;
        byte[] bArr = ((ByteArrayOutputStream) this).buf;
        if (i == bArr.length) {
            return bArr;
        }
        if (i == 0) {
            byte[] bArr2 = new byte[0];
            ((ByteArrayOutputStream) this).buf = bArr2;
            return bArr2;
        }
        byte[] bArr3 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr3[i2] = bArr[i2];
        }
        ((ByteArrayOutputStream) this).buf = bArr3;
        return bArr3;
    }

    @Override // java.io.ByteArrayOutputStream
    public String toString() {
        return "pos = " + size();
    }

    public void write(InputStream inputStream) throws IOException {
        if (inputStream instanceof BytesInputStream) {
            write((BytesInputStream) inputStream);
            return;
        }
        byte[] bArr = new byte[2048];
        while (true) {
            int i = inputStream.read(bArr, 0, bArr.length);
            if (i < 0) {
                inputStream.close();
                return;
            } else {
                write(bArr, 0, i);
                if (bArr.length < 81920) {
                    bArr = new byte[bArr.length + 500];
                }
            }
        }
    }

    public BytesOutputStream() {
        this(32);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        ensureCapacity(((ByteArrayOutputStream) this).count + i2);
        System.arraycopy(bArr, i, ((ByteArrayOutputStream) this).buf, ((ByteArrayOutputStream) this).count, i2);
        ((ByteArrayOutputStream) this).count += i2;
    }

    public void write(BytesInputStream bytesInputStream) throws IOException {
        byte[] byteArray = bytesInputStream.toByteArray();
        write(byteArray, 0, byteArray.length);
    }
}
