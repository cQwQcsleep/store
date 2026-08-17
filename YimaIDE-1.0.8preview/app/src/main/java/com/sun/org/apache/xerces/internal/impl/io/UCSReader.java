package com.sun.org.apache.xerces.internal.impl.io;

import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UCSReader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final short UCS2BE = 2;
    public static final short UCS2LE = 1;
    public static final short UCS4BE = 8;
    public static final short UCS4LE = 4;
    protected byte[] fBuffer;
    protected short fEncoding;
    protected InputStream fInputStream;

    public UCSReader(InputStream inputStream, int i, short s) {
        this.fInputStream = inputStream;
        byte[] byteBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getByteBuffer(i);
        this.fBuffer = byteBuffer;
        if (byteBuffer == null) {
            this.fBuffer = new byte[i];
        }
        this.fEncoding = s;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ThreadLocalBufferAllocator.getBufferAllocator().returnByteBuffer(this.fBuffer);
        this.fBuffer = null;
        this.fInputStream.close();
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        this.fInputStream.mark(i);
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int length = i2 << (this.fEncoding >= 4 ? 2 : 1);
        byte[] bArr = this.fBuffer;
        if (length > bArr.length) {
            length = bArr.length;
        }
        int i3 = this.fInputStream.read(bArr, 0, length);
        if (i3 == -1) {
            return -1;
        }
        if (this.fEncoding >= 4) {
            int i4 = (4 - (i3 & 3)) & 3;
            int i5 = 0;
            while (i5 < i4) {
                int i6 = this.fInputStream.read();
                if (i6 == -1) {
                    while (i5 < i4) {
                        this.fBuffer[i3 + i5] = 0;
                        i5++;
                    }
                    break;
                }
                this.fBuffer[i3 + i5] = (byte) i6;
                i5++;
            }
            i3 += i4;
        } else if ((i3 & 1) != 0) {
            i3++;
            int i7 = this.fInputStream.read();
            byte[] bArr2 = this.fBuffer;
            if (i7 == -1) {
                bArr2[i3] = 0;
            } else {
                bArr2[i3] = (byte) i7;
            }
        }
        int i8 = i3 >> (this.fEncoding >= 4 ? 2 : 1);
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            byte[] bArr3 = this.fBuffer;
            int i11 = bArr3[i9] & 255;
            int i12 = i9 + 2;
            int i13 = bArr3[i9 + 1] & 255;
            short s = this.fEncoding;
            if (s >= 4) {
                int i14 = i9 + 3;
                int i15 = bArr3[i12] & 255;
                i9 += 4;
                int i16 = bArr3[i14] & 255;
                if (s == 8) {
                    cArr[i + i10] = (char) ((i11 << 24) + (i13 << 16) + (i15 << 8) + i16);
                } else {
                    cArr[i + i10] = (char) ((i16 << 24) + (i15 << 16) + (i13 << 8) + i11);
                }
            } else {
                if (s == 2) {
                    cArr[i + i10] = (char) ((i11 << 8) + i13);
                } else {
                    cArr[i + i10] = (char) ((i13 << 8) + i11);
                }
                i9 = i12;
            }
        }
        return i8;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return false;
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.fInputStream.reset();
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        int i = this.fEncoding >= 4 ? 2 : 1;
        long jSkip = this.fInputStream.skip(j << i);
        return (((long) (i | 1)) & jSkip) == 0 ? jSkip >> i : (jSkip >> i) + 1;
    }

    public UCSReader(InputStream inputStream, short s) {
        this(inputStream, 8192, s);
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = this.fInputStream.read();
        int i6 = i5 & 255;
        if (i6 == 255 || (i2 = (i = this.fInputStream.read()) & 255) == 255) {
            return -1;
        }
        short s = this.fEncoding;
        if (s < 4) {
            return s == 2 ? (i6 << 8) + i2 : (i2 << 8) + i6;
        }
        int i7 = this.fInputStream.read();
        int i8 = i7 & 255;
        if (i8 == 255 || (i4 = (i3 = this.fInputStream.read()) & 255) == 255) {
            return -1;
        }
        System.err.println("b0 is " + (i5 & 255) + " b1 " + (i & 255) + " b2 " + (i7 & 255) + " b3 " + (i3 & 255));
        return this.fEncoding == 8 ? (i6 << 24) + (i2 << 16) + (i8 << 8) + i4 : (i4 << 24) + (i8 << 16) + (i2 << 8) + i6;
    }
}
