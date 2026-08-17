package com.reandroid.archive.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipStreamOutput extends ZipOutput {
    private final CountingOutputStream<OutputStream> countingStream;

    public ZipStreamOutput(OutputStream outputStream) {
        this.countingStream = new CountingOutputStream<>(outputStream, true);
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.countingStream.close();
    }

    @Override // com.reandroid.archive.io.WriteOnlyStream
    public OutputStream getOutputStream() {
        return this.countingStream;
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel
    public boolean isOpen() {
        return this.countingStream.isOpen();
    }

    @Override // com.reandroid.archive.io.RandomStream
    public void position(long j) throws IOException {
        throw new IOException("Can not move position of ZipStreamOutput");
    }

    @Override // com.reandroid.archive.io.WriteOnlyStream
    public void write(InputStream inputStream) throws IOException {
        this.countingStream.write(inputStream);
    }

    public void write(byte[] bArr) throws IOException {
        this.countingStream.write(bArr);
    }

    @Override // com.reandroid.archive.io.RandomStream
    public long position() throws IOException {
        return this.countingStream.getSize();
    }
}
