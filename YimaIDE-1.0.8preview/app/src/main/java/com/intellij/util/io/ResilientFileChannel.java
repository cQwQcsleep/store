package com.intellij.util.io;

import com.intellij.util.io.ResilientFileChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ResilientFileChannel extends FileChannel {
    private final FileChannelInterruptsRetryer fileChannelHandle;
    private long position;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "openOptions";
        } else if (i != 3) {
            objArr[0] = "path";
        } else {
            objArr[0] = "operation";
        }
        objArr[1] = "com/intellij/util/io/ResilientFileChannel";
        if (i != 3) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "executeOperation";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public ResilientFileChannel(Path path, OpenOption... openOptionArr) throws IOException {
        Set set;
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        if (openOptionArr == null) {
            $$$reportNull$$$0(1);
        }
        this.position = 0L;
        if (openOptionArr.length == 0) {
            set = Collections.EMPTY_SET;
        } else {
            HashSet hashSet = new HashSet();
            Collections.addAll(hashSet, openOptionArr);
            set = hashSet;
        }
        this.fileChannelHandle = new FileChannelInterruptsRetryer(path, set);
    }

    public static /* synthetic */ Integer e(ByteBuffer byteBuffer, int i, long j, FileChannel fileChannel) {
        byteBuffer.position(i);
        return Integer.valueOf(fileChannel.write(byteBuffer, j));
    }

    public static /* synthetic */ Integer f(ByteBuffer byteBuffer, int i, long j, FileChannel fileChannel) {
        byteBuffer.position(i);
        return Integer.valueOf(fileChannel.read(byteBuffer, j));
    }

    public static /* synthetic */ Object g(boolean z, FileChannel fileChannel) throws IOException {
        fileChannel.force(z);
        return null;
    }

    public <T> T executeOperation(FileChannelInterruptsRetryer.FileChannelIdempotentOperation<T> fileChannelIdempotentOperation) throws IOException {
        if (fileChannelIdempotentOperation == null) {
            $$$reportNull$$$0(3);
        }
        return (T) this.fileChannelHandle.retryIfInterrupted(fileChannelIdempotentOperation);
    }

    @Override // java.nio.channels.FileChannel
    public void force(final boolean z) throws IOException {
        this.fileChannelHandle.retryIfInterrupted(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: ufc
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return ResilientFileChannel.g(z, fileChannel);
            }
        });
    }

    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public void implCloseChannel() throws Throwable {
        this.fileChannelHandle.close();
    }

    @Override // java.nio.channels.FileChannel
    @Deprecated
    public FileLock lock(long j, long j2, boolean z) throws IOException {
        throw new UnsupportedOperationException("Method not implemented yet: no use");
    }

    @Override // java.nio.channels.FileChannel
    public MappedByteBuffer map(final FileChannel.MapMode mapMode, final long j, final long j2) throws IOException {
        return (MappedByteBuffer) this.fileChannelHandle.retryIfInterrupted(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: tfc
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return fileChannel.map(mapMode, j, j2);
            }
        });
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public synchronized FileChannel position(long j) throws IOException {
        this.position = j;
        return this;
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        int i;
        i = read(byteBuffer, this.position);
        this.position += (long) Math.max(0, i);
        return i;
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public long size() throws IOException {
        return ((Long) this.fileChannelHandle.retryIfInterrupted(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: sfc
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return Long.valueOf(fileChannel.size());
            }
        })).longValue();
    }

    @Override // java.nio.channels.FileChannel
    @Deprecated
    public long transferFrom(ReadableByteChannel readableByteChannel, long j, long j2) throws IOException {
        throw new UnsupportedOperationException("Method not implemented yet: no use");
    }

    @Override // java.nio.channels.FileChannel
    @Deprecated
    public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        throw new UnsupportedOperationException("Method not implemented yet: no use");
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public FileChannel truncate(final long j) throws IOException {
        synchronized (this) {
            this.position = Math.min(this.position, j);
        }
        return (FileChannel) this.fileChannelHandle.retryIfInterrupted(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: xfc
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return fileChannel.truncate(j);
            }
        });
    }

    @Override // java.nio.channels.FileChannel
    @Deprecated
    public FileLock tryLock(long j, long j2, boolean z) throws IOException {
        throw new UnsupportedOperationException("Method not implemented yet: no use");
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer byteBuffer) throws IOException {
        int iWrite;
        iWrite = write(byteBuffer, this.position);
        this.position += (long) Math.max(0, iWrite);
        return iWrite;
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public synchronized long position() {
        return this.position;
    }

    @Override // java.nio.channels.FileChannel
    public int read(final ByteBuffer byteBuffer, final long j) throws IOException {
        final int iPosition = byteBuffer.position();
        return ((Integer) this.fileChannelHandle.retryIfInterrupted(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: vfc
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return ResilientFileChannel.f(byteBuffer, iPosition, j, fileChannel);
            }
        })).intValue();
    }

    @Override // java.nio.channels.FileChannel
    public int write(final ByteBuffer byteBuffer, final long j) throws IOException {
        final int iPosition = byteBuffer.position();
        return ((Integer) this.fileChannelHandle.retryIfInterrupted(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: wfc
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return ResilientFileChannel.e(byteBuffer, iPosition, j, fileChannel);
            }
        })).intValue();
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.ScatteringByteChannel
    @Deprecated
    public long read(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException("Method not implemented yet: no use");
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
    @Deprecated
    public long write(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException("Method not implemented yet: no use");
    }
}
