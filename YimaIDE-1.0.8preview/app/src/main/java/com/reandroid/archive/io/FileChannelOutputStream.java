package com.reandroid.archive.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FileChannelOutputStream extends OutputStream {
    private final FileChannel fileChannel;

    public FileChannelOutputStream(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.fileChannel.position(this.fileChannel.position() + ((long) this.fileChannel.write(ByteBuffer.wrap(bArr, i, i2))));
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) (i & 255)});
    }
}
