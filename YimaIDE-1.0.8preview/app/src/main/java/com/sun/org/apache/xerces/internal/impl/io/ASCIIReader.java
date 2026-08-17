package com.sun.org.apache.xerces.internal.impl.io;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ASCIIReader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected byte[] fBuffer;
    private MessageFormatter fFormatter;
    protected InputStream fInputStream;
    private Locale fLocale;

    public ASCIIReader(InputStream inputStream, int i, MessageFormatter messageFormatter, Locale locale) {
        this.fFormatter = null;
        this.fLocale = null;
        this.fInputStream = inputStream;
        byte[] byteBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getByteBuffer(i);
        this.fBuffer = byteBuffer;
        if (byteBuffer == null) {
            this.fBuffer = new byte[i];
        }
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
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
        byte[] bArr = this.fBuffer;
        if (i2 > bArr.length) {
            i2 = bArr.length;
        }
        int i3 = this.fInputStream.read(bArr, 0, i2);
        for (int i4 = 0; i4 < i3; i4++) {
            byte b = this.fBuffer[i4];
            if (b < 0) {
                throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidASCII", new Object[]{Integer.toString(b & 255)});
            }
            cArr[i + i4] = (char) b;
        }
        return i3;
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
        return this.fInputStream.skip(j);
    }

    public ASCIIReader(InputStream inputStream, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 2048, messageFormatter, locale);
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i = this.fInputStream.read();
        if (i < 128) {
            return i;
        }
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidASCII", new Object[]{Integer.toString(i)});
    }
}
