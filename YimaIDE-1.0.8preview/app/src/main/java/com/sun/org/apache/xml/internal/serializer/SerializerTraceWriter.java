package com.sun.org.apache.xml.internal.serializer;

import com.sun.jna.platform.win32.WinError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SerializerTraceWriter extends Writer implements WriterChain {
    private byte[] buf;
    private int buf_length;
    private int count;
    private final SerializerTrace m_tracer;
    private final Writer m_writer;

    public SerializerTraceWriter(Writer writer, SerializerTrace serializerTrace) {
        this.m_writer = writer;
        this.m_tracer = serializerTrace;
        setBufferSize(1024);
    }

    private void flushBuffer() throws IOException {
        int i = this.count;
        if (i > 0) {
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < this.count; i2++) {
                cArr[i2] = (char) this.buf[i2];
            }
            SerializerTrace serializerTrace = this.m_tracer;
            if (serializerTrace != null) {
                serializerTrace.fireGenerateEvent(12, cArr, 0, i);
            }
            this.count = 0;
        }
    }

    private void setBufferSize(int i) {
        this.buf = new byte[i + 3];
        this.buf_length = i;
        this.count = 0;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void close() throws IOException {
        Writer writer = this.m_writer;
        if (writer != null) {
            writer.close();
        }
        flushBuffer();
    }

    @Override // java.io.Writer, java.io.Flushable, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void flush() throws IOException {
        Writer writer = this.m_writer;
        if (writer != null) {
            writer.flush();
        }
        flushBuffer();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.WriterChain
    public OutputStream getOutputStream() {
        Appendable appendable = this.m_writer;
        if (appendable instanceof WriterChain) {
            return ((WriterChain) appendable).getOutputStream();
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.WriterChain
    public Writer getWriter() {
        return this.m_writer;
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(String str) throws IOException {
        Writer writer = this.m_writer;
        if (writer != null) {
            writer.write(str);
        }
        int length = str.length();
        int i = (length << 1) + length;
        if (i >= this.buf_length) {
            flushBuffer();
            setBufferSize(i * 2);
        }
        if (i > this.buf_length - this.count) {
            flushBuffer();
        }
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128) {
                byte[] bArr = this.buf;
                int i3 = this.count;
                this.count = i3 + 1;
                bArr[i3] = (byte) cCharAt;
            } else {
                byte[] bArr2 = this.buf;
                if (cCharAt < 2048) {
                    int i4 = this.count;
                    int i5 = i4 + 1;
                    this.count = i5;
                    bArr2[i4] = (byte) ((cCharAt >> 6) + 192);
                    this.count = i4 + 2;
                    bArr2[i5] = (byte) ((cCharAt & '?') + 128);
                } else {
                    int i6 = this.count;
                    int i7 = i6 + 1;
                    this.count = i7;
                    bArr2[i6] = (byte) ((cCharAt >> '\f') + WinError.ERROR_FORMS_AUTH_REQUIRED);
                    int i8 = i6 + 2;
                    this.count = i8;
                    bArr2[i7] = (byte) (((cCharAt >> 6) & 63) + 128);
                    this.count = i6 + 3;
                    bArr2[i8] = (byte) ((cCharAt & '?') + 128);
                }
            }
        }
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(char[] cArr, int i, int i2) throws IOException {
        Writer writer = this.m_writer;
        if (writer != null) {
            writer.write(cArr, i, i2);
        }
        int i3 = (i2 << 1) + i2;
        if (i3 >= this.buf_length) {
            flushBuffer();
            setBufferSize(i3 * 2);
        }
        if (i3 > this.buf_length - this.count) {
            flushBuffer();
        }
        int i4 = i2 + i;
        while (i < i4) {
            char c = cArr[i];
            if (c < 128) {
                byte[] bArr = this.buf;
                int i5 = this.count;
                this.count = i5 + 1;
                bArr[i5] = (byte) c;
            } else {
                byte[] bArr2 = this.buf;
                if (c < 2048) {
                    int i6 = this.count;
                    int i7 = i6 + 1;
                    this.count = i7;
                    bArr2[i6] = (byte) ((c >> 6) + 192);
                    this.count = i6 + 2;
                    bArr2[i7] = (byte) ((c & '?') + 128);
                } else {
                    int i8 = this.count;
                    int i9 = i8 + 1;
                    this.count = i9;
                    bArr2[i8] = (byte) ((c >> '\f') + WinError.ERROR_FORMS_AUTH_REQUIRED);
                    int i10 = i8 + 2;
                    this.count = i10;
                    bArr2[i9] = (byte) (((c >> 6) & 63) + 128);
                    this.count = i8 + 3;
                    bArr2[i10] = (byte) ((c & '?') + 128);
                }
            }
            i++;
        }
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(int i) throws IOException {
        Writer writer = this.m_writer;
        if (writer != null) {
            writer.write(i);
        }
        if (this.count >= this.buf_length) {
            flushBuffer();
        }
        if (i < 128) {
            byte[] bArr = this.buf;
            int i2 = this.count;
            this.count = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        byte[] bArr2 = this.buf;
        if (i < 2048) {
            int i3 = this.count;
            int i4 = i3 + 1;
            this.count = i4;
            bArr2[i3] = (byte) ((i >> 6) + 192);
            this.count = i3 + 2;
            bArr2[i4] = (byte) ((i & 63) + 128);
            return;
        }
        int i5 = this.count;
        int i6 = i5 + 1;
        this.count = i6;
        bArr2[i5] = (byte) ((i >> 12) + WinError.ERROR_FORMS_AUTH_REQUIRED);
        int i7 = i5 + 2;
        this.count = i7;
        bArr2[i6] = (byte) (((i >> 6) & 63) + 128);
        this.count = i5 + 3;
        bArr2[i7] = (byte) ((i & 63) + 128);
    }
}
