package com.sun.org.apache.xerces.internal.impl.io;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class UTF16Reader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    protected final byte[] fBuffer;
    private final MessageFormatter fFormatter;
    protected final InputStream fInputStream;
    protected final boolean fIsBigEndian;
    private final Locale fLocale;

    public UTF16Reader(InputStream inputStream, boolean z) {
        this(inputStream, 4096, z, new XMLMessageFormatter(), Locale.getDefault());
    }

    private void expectedTwoBytes() throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "ExpectedByte", new Object[]{TlbConst.TYPELIB_MAJOR_VERSION_OFFICE, TlbConst.TYPELIB_MAJOR_VERSION_OFFICE});
    }

    private void processBE(char[] cArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            byte[] bArr = this.fBuffer;
            int i5 = i4 + 1;
            int i6 = bArr[i4] & 255;
            i4 += 2;
            cArr[i] = (char) ((bArr[i5] & 255) | (i6 << 8));
            i3++;
            i++;
        }
    }

    private void processLE(char[] cArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            byte[] bArr = this.fBuffer;
            int i5 = i4 + 1;
            int i6 = bArr[i4] & 255;
            i4 += 2;
            cArr[i] = (char) (((bArr[i5] & 255) << 8) | i6);
            i3++;
            i++;
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.fInputStream.close();
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[]{"mark()", XMLEntityManager.EncodingInfo.STR_UTF16}));
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int length = i2 << 1;
        byte[] bArr = this.fBuffer;
        if (length > bArr.length) {
            length = bArr.length;
        }
        int i3 = this.fInputStream.read(bArr, 0, length);
        if (i3 == -1) {
            return -1;
        }
        if ((i3 & 1) != 0) {
            int i4 = this.fInputStream.read();
            if (i4 == -1) {
                expectedTwoBytes();
            }
            this.fBuffer[i3] = (byte) i4;
            i3++;
        }
        int i5 = i3 >> 1;
        if (this.fIsBigEndian) {
            processBE(cArr, i, i5);
            return i5;
        }
        processLE(cArr, i, i5);
        return i5;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return false;
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        long jSkip = this.fInputStream.skip(j << 1);
        if ((jSkip & 1) != 0) {
            if (this.fInputStream.read() == -1) {
                expectedTwoBytes();
            }
            jSkip++;
        }
        return jSkip >> 1;
    }

    public UTF16Reader(InputStream inputStream, boolean z, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 4096, z, messageFormatter, locale);
    }

    public UTF16Reader(InputStream inputStream, int i, boolean z, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, new byte[i], z, messageFormatter, locale);
    }

    public UTF16Reader(InputStream inputStream, byte[] bArr, boolean z, MessageFormatter messageFormatter, Locale locale) {
        this.fInputStream = inputStream;
        this.fBuffer = bArr;
        this.fIsBigEndian = z;
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i = this.fInputStream.read();
        if (i == -1) {
            return -1;
        }
        int i2 = this.fInputStream.read();
        if (i2 == -1) {
            expectedTwoBytes();
        }
        return this.fIsBigEndian ? (i << 8) | i2 : (i2 << 8) | i;
    }
}
