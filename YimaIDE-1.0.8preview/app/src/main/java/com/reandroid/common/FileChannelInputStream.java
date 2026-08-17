package com.reandroid.common;

import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FileChannelInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_SIZE = 102400;
    private final byte[] buffer;
    private int bufferLength;
    private int bufferPosition;
    private final FileChannel fileChannel;
    private boolean mAutoClosable;
    private boolean mIsClosed;
    private long position;
    private long startOffset;
    private final long totalLength;

    public FileChannelInputStream(FileChannel fileChannel, long j, int i) throws IOException {
        this.fileChannel = fileChannel;
        this.totalLength = j;
        i = i <= 0 ? 8 : i;
        i = j < ((long) i) ? (int) j : i;
        this.buffer = new byte[i];
        this.bufferLength = i;
        this.bufferPosition = i;
        this.startOffset = fileChannel.position();
    }

    private void closeAuto() throws IOException {
        if (!this.mAutoClosable || this.mIsClosed) {
            return;
        }
        this.mIsClosed = true;
        this.fileChannel.close();
    }

    private boolean isFinished() throws IOException {
        boolean z = this.position >= this.totalLength;
        if (z) {
            closeAuto();
        }
        return z;
    }

    private void loadBuffer() throws IOException {
        boolean z;
        byte[] bArr = this.buffer;
        if (this.bufferPosition < this.bufferLength) {
            return;
        }
        int length = bArr.length;
        long j = this.totalLength - this.position;
        if (length > j) {
            length = (int) j;
            z = true;
        } else {
            z = false;
        }
        this.bufferLength = this.fileChannel.read(ByteBuffer.wrap(bArr, 0, length));
        this.bufferPosition = 0;
        if (z) {
            closeAuto();
        }
    }

    private int readBuffer(byte[] bArr, int i, int i2) {
        int i3 = this.bufferLength;
        int i4 = this.bufferPosition;
        int i5 = i3 - i4;
        if (i5 == 0) {
            return 0;
        }
        if (i2 > i5) {
            i2 = i5;
        }
        System.arraycopy(this.buffer, i4, bArr, i, i2);
        this.bufferPosition += i2;
        this.position += (long) i2;
        return i2;
    }

    private int skipBuffer(long j) {
        int i = this.bufferLength;
        int i2 = this.bufferPosition;
        int i3 = i - i2;
        if (i3 > j) {
            i3 = (int) j;
        }
        this.bufferPosition = i2 + i3;
        this.position += (long) i3;
        return i3;
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) (this.totalLength - this.position);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        closeAuto();
    }

    public FileChannel getFileChannel() {
        return this.fileChannel;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        if (i < 0) {
            i = 0;
        }
        this.startOffset = i;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (isFinished()) {
            return -1;
        }
        if (i2 == 0) {
            return 0;
        }
        loadBuffer();
        int buffer = readBuffer(bArr, i, i2);
        int i3 = i2 - buffer;
        int i4 = i + buffer;
        while (i3 > 0 && !isFinished()) {
            loadBuffer();
            int buffer2 = readBuffer(bArr, i4, i3);
            buffer += buffer2;
            i3 -= buffer2;
            i4 += buffer2;
        }
        return buffer;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.position = 0L;
        this.bufferPosition = this.bufferLength;
        this.fileChannel.position(this.startOffset);
    }

    public void setAutoClosable(boolean z) {
        this.mAutoClosable = z;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j > 0) {
            long jSkipBuffer = j - ((long) skipBuffer(j));
            if (jSkipBuffer != 0) {
                long j2 = this.totalLength;
                long j3 = this.position;
                long j4 = j2 - j3;
                if (j4 > jSkipBuffer) {
                    j4 = jSkipBuffer;
                }
                this.position = j3 + j4;
                long j5 = j - (jSkipBuffer - j4);
                FileChannel fileChannel = this.fileChannel;
                fileChannel.position(fileChannel.position() + j4);
                return j5;
            }
        }
        return j;
    }

    public String toString() {
        return this.position + " / " + this.totalLength;
    }

    @Override // java.io.InputStream
    public long transferTo(OutputStream outputStream) throws IOException {
        long j = 0;
        if (isFinished()) {
            return 0L;
        }
        while (!isFinished()) {
            loadBuffer();
            int i = this.bufferPosition;
            int i2 = this.bufferLength - i;
            if (i2 <= 0) {
                break;
            }
            outputStream.write(this.buffer, i, i2);
            this.bufferPosition += i2;
            long j2 = i2;
            this.position += j2;
            j += j2;
        }
        return j;
    }

    public FileChannelInputStream(FileChannel fileChannel, byte[] bArr, long j) throws IOException {
        this.fileChannel = fileChannel;
        this.totalLength = j;
        int length = bArr.length;
        this.buffer = bArr;
        this.bufferLength = length;
        this.bufferPosition = length;
        this.startOffset = fileChannel.position();
    }

    public FileChannelInputStream(FileChannel fileChannel, long j) throws IOException {
        this(fileChannel, j, DEFAULT_BUFFER_SIZE);
    }

    public FileChannelInputStream(File file, long j, int i) throws IOException {
        this(FileUtil.openReadChannel(file), j, i);
        this.mAutoClosable = true;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int i = read(bArr);
        return i < 0 ? i : bArr[0] & 255;
    }

    public FileChannelInputStream(File file, byte[] bArr, int i) throws IOException {
        this(FileUtil.openReadChannel(file), bArr, i);
        this.mAutoClosable = true;
    }

    public FileChannelInputStream(File file) throws IOException {
        this(FileUtil.openReadChannel(file), file.length());
        this.mAutoClosable = true;
    }

    public static byte[] read(File file, int i) throws IOException {
        FileChannelInputStream fileChannelInputStream = new FileChannelInputStream(file, i, i);
        fileChannelInputStream.loadBuffer();
        fileChannelInputStream.closeAuto();
        return fileChannelInputStream.buffer;
    }

    public static void read(File file, byte[] bArr, int i) throws IOException {
        FileChannelInputStream fileChannelInputStream = new FileChannelInputStream(file, bArr, i);
        fileChannelInputStream.loadBuffer();
        fileChannelInputStream.closeAuto();
    }
}
