package com.reandroid.archive.io;

import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipFileOutput extends ZipOutput {
    private final File file;
    private FileChannel fileChannel;
    private FileChannelOutputStream outputStream;

    public ZipFileOutput(File file) throws IOException {
        initFile(file);
        this.file = file;
    }

    private FileChannel getFileChannel() throws IOException {
        FileChannel fileChannelOpenWriteChannel;
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            return fileChannel;
        }
        synchronized (this) {
            fileChannelOpenWriteChannel = FileUtil.openWriteChannel(this.file);
            this.fileChannel = fileChannelOpenWriteChannel;
        }
        return fileChannelOpenWriteChannel;
    }

    private static void initFile(File file) throws IOException {
        if (file.isDirectory()) {
            r8g.a("Not file: ", file);
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            fileChannel.close();
        }
    }

    public File getFile() {
        return this.file;
    }

    @Override // com.reandroid.archive.io.WriteOnlyStream
    public FileChannelOutputStream getOutputStream() throws IOException {
        FileChannelOutputStream fileChannelOutputStream = this.outputStream;
        if (fileChannelOutputStream != null) {
            return fileChannelOutputStream;
        }
        FileChannelOutputStream fileChannelOutputStream2 = new FileChannelOutputStream(getFileChannel());
        this.outputStream = fileChannelOutputStream2;
        return fileChannelOutputStream2;
    }

    @Override // com.reandroid.archive.io.RandomStream, java.nio.channels.Channel
    public boolean isOpen() {
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            return fileChannel.isOpen();
        }
        return false;
    }

    @Override // com.reandroid.archive.io.RandomStream
    public long position() throws IOException {
        return getFileChannel().position();
    }

    @Override // com.reandroid.archive.io.WriteOnlyStream
    public void write(InputStream inputStream) throws IOException {
        FileChannel fileChannel = getFileChannel();
        long jPosition = fileChannel.position();
        byte[] bArr = new byte[10240000];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr, 0, 10240000);
            if (i <= 0) {
                inputStream.close();
                fileChannel.position(jPosition + j);
                return;
            } else {
                fileChannel.write(ByteBuffer.wrap(bArr, 0, i));
                j += (long) i;
            }
        }
    }

    @Override // com.reandroid.archive.io.RandomStream
    public void position(long j) throws IOException {
        getFileChannel().position(j);
    }

    public void write(FileChannel fileChannel, long j) throws IOException {
        FileChannel fileChannel2 = getFileChannel();
        long jPosition = fileChannel2.position();
        long j2 = j;
        long j3 = 0;
        while (j2 > 0) {
            FileChannel fileChannel3 = fileChannel;
            long jTransferFrom = fileChannel2.transferFrom(fileChannel3, jPosition + j3, j2);
            j3 += jTransferFrom;
            j2 -= jTransferFrom;
            fileChannel = fileChannel3;
        }
        fileChannel2.position(jPosition + j3);
    }
}
