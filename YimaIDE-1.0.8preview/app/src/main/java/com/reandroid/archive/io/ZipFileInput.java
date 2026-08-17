package com.reandroid.archive.io;

import com.reandroid.common.FileChannelInputStream;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipFileInput extends ZipInput {
    private final File file;
    private FileChannel fileChannel;
    private InputStream mCurrentInputStream;

    public ZipFileInput(File file) {
        this.file = file;
    }

    private void closeChannel() throws IOException {
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel == null) {
            return;
        }
        synchronized (this) {
            fileChannel.close();
            this.fileChannel = null;
        }
    }

    private void closeCurrentInputStream() throws IOException {
        InputStream inputStream = this.mCurrentInputStream;
        if (inputStream == null) {
            return;
        }
        inputStream.close();
        this.mCurrentInputStream = null;
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        closeCurrentInputStream();
        closeChannel();
    }

    public File getFile() {
        return this.file;
    }

    public FileChannel getFileChannel() throws IOException {
        FileChannel fileChannelOpenReadChannel;
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            return fileChannel;
        }
        synchronized (this) {
            fileChannelOpenReadChannel = FileUtil.openReadChannel(this.file);
            this.fileChannel = fileChannelOpenReadChannel;
        }
        return fileChannelOpenReadChannel;
    }

    @Override // com.reandroid.archive.io.ZipInput
    public byte[] getFooter(int i) throws IOException {
        long length = getLength();
        if (i > length) {
            i = (int) length;
        }
        FileChannel fileChannel = getFileChannel();
        fileChannel.position(length - ((long) i));
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        fileChannel.read(byteBufferAllocate);
        return byteBufferAllocate.array();
    }

    @Override // com.reandroid.archive.io.ReadOnlyStream
    public InputStream getInputStream(long j, long j2) throws IOException {
        closeCurrentInputStream();
        FileChannel fileChannel = getFileChannel();
        fileChannel.position(j);
        FileChannelInputStream fileChannelInputStream = new FileChannelInputStream(fileChannel, j2);
        this.mCurrentInputStream = fileChannelInputStream;
        return fileChannelInputStream;
    }

    @Override // com.reandroid.archive.io.ReadOnlyStream
    public long getLength() {
        return this.file.length();
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel
    public boolean isOpen() {
        boolean zIsOpen;
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel == null) {
            return false;
        }
        synchronized (this) {
            zIsOpen = fileChannel.isOpen();
        }
        return zIsOpen;
    }

    @Override // com.reandroid.archive.io.RandomStream
    public long position() throws IOException {
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            return fileChannel.position();
        }
        return 0L;
    }

    public String toString() {
        return "File: " + this.file;
    }

    @Override // com.reandroid.archive.io.RandomStream
    public void position(long j) throws IOException {
        getFileChannel().position(j);
    }
}
