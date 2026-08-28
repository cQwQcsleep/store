package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import core.pro.android.notify.h;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class FileHandle implements Closeable {
    private boolean closed;
    private final ReentrantLock lock = _JvmPlatformKt.newLock();
    private int openStreamCount;
    private final boolean readWrite;

    public static final class FileHandleSink implements Sink {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSink(FileHandle fileHandle, long j) {
            CloseableKt.checkNotNullParameter(fileHandle, "fileHandle");
            this.fileHandle = fileHandle;
            this.position = j;
        }

        @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            ReentrantLock lock = this.fileHandle.getLock();
            lock.lock();
            try {
                FileHandle fileHandle = this.fileHandle;
                fileHandle.openStreamCount--;
                if (this.fileHandle.openStreamCount == 0) {
                    if (this.fileHandle.closed) {
                        lock.unlock();
                        this.fileHandle.protectedClose();
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        @Override // com.shadow.okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            this.fileHandle.protectedFlush();
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final FileHandle getFileHandle() {
            return this.fileHandle;
        }

        public final long getPosition() {
            return this.position;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public final void setPosition(long j) {
            this.position = j;
        }

        @Override // com.shadow.okio.Sink
        public Timeout timeout() {
            return Timeout.NONE;
        }

        @Override // com.shadow.okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            CloseableKt.checkNotNullParameter(buffer, "source");
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            this.fileHandle.writeNoCloseCheck(this.position, buffer, j);
            this.position += j;
        }
    }

    public static final class FileHandleSource implements Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle fileHandle, long j) {
            CloseableKt.checkNotNullParameter(fileHandle, "fileHandle");
            this.fileHandle = fileHandle;
            this.position = j;
        }

        @Override // com.shadow.okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            ReentrantLock lock = this.fileHandle.getLock();
            lock.lock();
            try {
                FileHandle fileHandle = this.fileHandle;
                fileHandle.openStreamCount--;
                if (this.fileHandle.openStreamCount == 0) {
                    if (this.fileHandle.closed) {
                        lock.unlock();
                        this.fileHandle.protectedClose();
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final FileHandle getFileHandle() {
            return this.fileHandle;
        }

        public final long getPosition() {
            return this.position;
        }

        @Override // com.shadow.okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            CloseableKt.checkNotNullParameter(buffer, "sink");
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            long noCloseCheck = this.fileHandle.readNoCloseCheck(this.position, buffer, j);
            if (noCloseCheck != -1) {
                this.position += noCloseCheck;
            }
            return noCloseCheck;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public final void setPosition(long j) {
            this.position = j;
        }

        @Override // com.shadow.okio.Source
        public Timeout timeout() {
            return Timeout.NONE;
        }
    }

    public FileHandle(boolean z) {
        this.readWrite = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long readNoCloseCheck(long j, Buffer buffer, long j2) throws IOException {
        if (j2 < 0) {
            throw new IllegalArgumentException(h.c("byteCount < 0: ", j2).toString());
        }
        long j3 = j2 + j;
        long j4 = j;
        while (true) {
            if (j4 >= j3) {
                break;
            }
            Segment segmentWritableSegment$okio = buffer.writableSegment$okio(1);
            int iProtectedRead = protectedRead(j4, segmentWritableSegment$okio.data, segmentWritableSegment$okio.limit, (int) Math.min(j3 - j4, 8192 - r7));
            if (iProtectedRead == -1) {
                if (segmentWritableSegment$okio.pos == segmentWritableSegment$okio.limit) {
                    buffer.head = segmentWritableSegment$okio.pop();
                    SegmentPool.recycle(segmentWritableSegment$okio);
                }
                if (j == j4) {
                    return -1L;
                }
            } else {
                segmentWritableSegment$okio.limit += iProtectedRead;
                long j5 = iProtectedRead;
                j4 += j5;
                buffer.setSize$okio(buffer.size() + j5);
            }
        }
        return j4 - j;
    }

    public static /* synthetic */ Sink sink$default(FileHandle fileHandle, long j, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
        }
        if ((i & 1) != 0) {
            j = 0;
        }
        return fileHandle.sink(j);
    }

    public static /* synthetic */ Source source$default(FileHandle fileHandle, long j, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: source");
        }
        if ((i & 1) != 0) {
            j = 0;
        }
        return fileHandle.source(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeNoCloseCheck(long j, Buffer buffer, long j2) throws IOException {
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0L, j2);
        long j3 = j2 + j;
        while (j < j3) {
            Segment segment = buffer.head;
            CloseableKt.checkNotNull(segment);
            int iMin = (int) Math.min(j3 - j, segment.limit - segment.pos);
            protectedWrite(j, segment.data, segment.pos, iMin);
            segment.pos += iMin;
            long j4 = iMin;
            j += j4;
            buffer.setSize$okio(buffer.size() - j4);
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    public final Sink appendingSink() throws IOException {
        return sink(size());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.openStreamCount != 0) {
                return;
            }
            reentrantLock.unlock();
            protectedClose();
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void flush() throws IOException {
        if (!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            reentrantLock.unlock();
            protectedFlush();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    public final boolean getReadWrite() {
        return this.readWrite;
    }

    public final long position(Source source) throws IOException {
        long size;
        CloseableKt.checkNotNullParameter(source, "source");
        if (source instanceof RealBufferedSource) {
            RealBufferedSource realBufferedSource = (RealBufferedSource) source;
            size = realBufferedSource.bufferField.size();
            source = realBufferedSource.source;
        } else {
            size = 0;
        }
        if (!(source instanceof FileHandleSource) || ((FileHandleSource) source).getFileHandle() != this) {
            throw new IllegalArgumentException("source was not created by this FileHandle");
        }
        FileHandleSource fileHandleSource = (FileHandleSource) source;
        if (fileHandleSource.getClosed()) {
            throw new IllegalStateException("closed");
        }
        return fileHandleSource.getPosition() - size;
    }

    public abstract void protectedClose() throws IOException;

    public abstract void protectedFlush() throws IOException;

    public abstract int protectedRead(long j, byte[] bArr, int i, int i2) throws IOException;

    public abstract void protectedResize(long j) throws IOException;

    public abstract long protectedSize() throws IOException;

    public abstract void protectedWrite(long j, byte[] bArr, int i, int i2) throws IOException;

    public final int read(long j, byte[] bArr, int i, int i2) throws IOException {
        CloseableKt.checkNotNullParameter(bArr, "array");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            reentrantLock.unlock();
            return protectedRead(j, bArr, i, i2);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void reposition(Source source, long j) throws IOException {
        CloseableKt.checkNotNullParameter(source, "source");
        if (!(source instanceof RealBufferedSource)) {
            if (!(source instanceof FileHandleSource) || ((FileHandleSource) source).getFileHandle() != this) {
                throw new IllegalArgumentException("source was not created by this FileHandle");
            }
            FileHandleSource fileHandleSource = (FileHandleSource) source;
            if (fileHandleSource.getClosed()) {
                throw new IllegalStateException("closed");
            }
            fileHandleSource.setPosition(j);
            return;
        }
        RealBufferedSource realBufferedSource = (RealBufferedSource) source;
        Source source2 = realBufferedSource.source;
        if (!(source2 instanceof FileHandleSource) || ((FileHandleSource) source2).getFileHandle() != this) {
            throw new IllegalArgumentException("source was not created by this FileHandle");
        }
        FileHandleSource fileHandleSource2 = (FileHandleSource) source2;
        if (fileHandleSource2.getClosed()) {
            throw new IllegalStateException("closed");
        }
        long size = realBufferedSource.bufferField.size();
        long position = j - (fileHandleSource2.getPosition() - size);
        if (0 <= position && position < size) {
            realBufferedSource.skip(position);
        } else {
            realBufferedSource.bufferField.clear();
            fileHandleSource2.setPosition(j);
        }
    }

    public final void resize(long j) throws IOException {
        if (!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            reentrantLock.unlock();
            protectedResize(j);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final Sink sink(long j) throws IOException {
        if (!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            this.openStreamCount++;
            reentrantLock.unlock();
            return new FileHandleSink(this, j);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final long size() throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            reentrantLock.unlock();
            return protectedSize();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final Source source(long j) throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            this.openStreamCount++;
            reentrantLock.unlock();
            return new FileHandleSource(this, j);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void write(long j, byte[] bArr, int i, int i2) throws IOException {
        CloseableKt.checkNotNullParameter(bArr, "array");
        if (!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            reentrantLock.unlock();
            protectedWrite(j, bArr, i, i2);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final long read(long j, Buffer buffer, long j2) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.closed) {
                reentrantLock.unlock();
                return readNoCloseCheck(j, buffer, j2);
            }
            throw new IllegalStateException("closed");
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void write(long j, Buffer buffer, long j2) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        if (this.readWrite) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                if (!this.closed) {
                    reentrantLock.unlock();
                    writeNoCloseCheck(j, buffer, j2);
                    return;
                }
                throw new IllegalStateException("closed");
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        throw new IllegalStateException("file handle is read-only");
    }

    public final long position(Sink sink) throws IOException {
        long size;
        CloseableKt.checkNotNullParameter(sink, "sink");
        if (sink instanceof RealBufferedSink) {
            RealBufferedSink realBufferedSink = (RealBufferedSink) sink;
            size = realBufferedSink.bufferField.size();
            sink = realBufferedSink.sink;
        } else {
            size = 0;
        }
        if ((sink instanceof FileHandleSink) && ((FileHandleSink) sink).getFileHandle() == this) {
            FileHandleSink fileHandleSink = (FileHandleSink) sink;
            if (!fileHandleSink.getClosed()) {
                return fileHandleSink.getPosition() + size;
            }
            throw new IllegalStateException("closed");
        }
        throw new IllegalArgumentException("sink was not created by this FileHandle");
    }

    public final void reposition(Sink sink, long j) throws IOException {
        CloseableKt.checkNotNullParameter(sink, "sink");
        if (sink instanceof RealBufferedSink) {
            RealBufferedSink realBufferedSink = (RealBufferedSink) sink;
            Sink sink2 = realBufferedSink.sink;
            if ((sink2 instanceof FileHandleSink) && ((FileHandleSink) sink2).getFileHandle() == this) {
                FileHandleSink fileHandleSink = (FileHandleSink) sink2;
                if (!fileHandleSink.getClosed()) {
                    realBufferedSink.emit();
                    fileHandleSink.setPosition(j);
                    return;
                }
                throw new IllegalStateException("closed");
            }
            throw new IllegalArgumentException("sink was not created by this FileHandle");
        }
        if ((sink instanceof FileHandleSink) && ((FileHandleSink) sink).getFileHandle() == this) {
            FileHandleSink fileHandleSink2 = (FileHandleSink) sink;
            if (!fileHandleSink2.getClosed()) {
                fileHandleSink2.setPosition(j);
                return;
            }
            throw new IllegalStateException("closed");
        }
        throw new IllegalArgumentException("sink was not created by this FileHandle");
    }
}
