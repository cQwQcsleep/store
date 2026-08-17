package org.fusesource.jansi.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class FastBufferedOutputStream extends FilterOutputStream {
    protected final byte[] buf;
    protected int count;

    public FastBufferedOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.buf = new byte[8192];
    }

    private void flushBuffer() throws IOException {
        int i = this.count;
        if (i > 0) {
            ((FilterOutputStream) this).out.write(this.buf, 0, i);
            this.count = 0;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        flushBuffer();
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.buf;
        if (i2 >= bArr2.length) {
            flushBuffer();
            ((FilterOutputStream) this).out.write(bArr, i, i2);
        } else {
            if (i2 > bArr2.length - this.count) {
                flushBuffer();
            }
            System.arraycopy(bArr, i, this.buf, this.count, i2);
            this.count += i2;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.count >= this.buf.length) {
            flushBuffer();
        }
        byte[] bArr = this.buf;
        int i2 = this.count;
        this.count = i2 + 1;
        bArr[i2] = (byte) i;
    }
}
