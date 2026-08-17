package com.reandroid.archive.io;

import com.reandroid.common.BytesOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipByteOutput extends ZipOutput {
    private final BytesOutputStream bis = new BytesOutputStream();

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.bis.close();
    }

    @Override // com.reandroid.archive.io.WriteOnlyStream
    public OutputStream getOutputStream() {
        return this.bis;
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // com.reandroid.archive.io.RandomStream
    public long position() throws IOException {
        return this.bis.position();
    }

    public byte[] toByteArray() {
        return this.bis.toByteArray();
    }

    @Override // com.reandroid.archive.io.WriteOnlyStream
    public void write(InputStream inputStream) throws IOException {
        this.bis.write(inputStream);
    }

    public void write(byte[] bArr) throws IOException {
        this.bis.write(bArr);
    }

    @Override // com.reandroid.archive.io.RandomStream
    public void position(long j) throws IOException {
        throw new IOException("Not used");
    }
}
