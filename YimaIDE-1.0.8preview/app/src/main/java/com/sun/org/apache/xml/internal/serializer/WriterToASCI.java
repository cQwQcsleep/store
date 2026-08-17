package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class WriterToASCI extends Writer implements WriterChain {
    private final OutputStream m_os;

    public WriterToASCI(OutputStream outputStream) {
        this.m_os = outputStream;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void close() throws IOException {
        this.m_os.close();
    }

    @Override // java.io.Writer, java.io.Flushable, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void flush() throws IOException {
        this.m_os.flush();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.WriterChain
    public OutputStream getOutputStream() {
        return this.m_os;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.WriterChain
    public Writer getWriter() {
        return null;
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            this.m_os.write(str.charAt(i));
        }
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(int i) throws IOException {
        this.m_os.write(i);
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(char[] cArr, int i, int i2) throws IOException {
        int i3 = i2 + i;
        while (i < i3) {
            this.m_os.write(cArr[i]);
            i++;
        }
    }
}
