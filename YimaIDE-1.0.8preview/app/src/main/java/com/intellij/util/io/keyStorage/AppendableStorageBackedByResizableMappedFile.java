package com.intellij.util.io.keyStorage;

import com.intellij.openapi.util.ThrowableNotNullFunction;
import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.DataOutputStream;
import com.intellij.util.io.DirectBufferWrapper;
import com.intellij.util.io.IOCancellationCallbackHolder;
import com.intellij.util.io.LimitedInputStream;
import com.intellij.util.io.PagedFileStorage;
import com.intellij.util.io.ResizeableMappedFile;
import com.intellij.util.io.StorageLockContext;
import com.intellij.util.io.UnsyncByteArrayInputStream;
import com.intellij.util.io.keyStorage.AppendableStorageBackedByResizableMappedFile;
import defpackage.u0b;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class AppendableStorageBackedByResizableMappedFile<Data> implements AppendableObjectStorage<Data> {
    private static final ThreadLocal<MyDataIS> TLOCAL_READ_STREAMS = ThreadLocal.withInitial(new Supplier() { // from class: qd0
        @Override // java.util.function.Supplier
        public final Object get() {
            return AppendableStorageBackedByResizableMappedFile.c();
        }
    });
    private static final InputStream TOMBSTONE = new InputStream() { // from class: com.intellij.util.io.keyStorage.AppendableStorageBackedByResizableMappedFile.4
        @Override // java.io.InputStream
        public int read() {
            throw new IllegalStateException("should not happen");
        }
    };
    private volatile AppendMemoryBuffer appendBuffer;
    private final DataExternalizer<Data> dataDescriptor;
    private volatile int fileLength;
    private final ResizeableMappedFile storage;

    public static abstract class CheckerOutputStream extends OutputStream {
        boolean same;

        private CheckerOutputStream() {
            this.same = true;
        }
    }

    public static final class MyBufferedIS extends BufferedInputStream {
        public MyBufferedIS() {
            super(AppendableStorageBackedByResizableMappedFile.TOMBSTONE, 512);
        }

        public void setup(ResizeableMappedFile resizeableMappedFile, long j, long j2, boolean z) {
            ((BufferedInputStream) this).pos = 0;
            ((BufferedInputStream) this).count = 0;
            ((BufferedInputStream) this).in = new MappedFileInputStream(resizeableMappedFile, j, j2, z);
        }
    }

    public static final class MyDataIS extends DataInputStream {
        private MyDataIS() {
            super(new MyBufferedIS());
        }

        public void setup(ResizeableMappedFile resizeableMappedFile, long j, long j2, boolean z) {
            ((MyBufferedIS) ((DataInputStream) this).in).setup(resizeableMappedFile, j, j2, z);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "dataDescriptor";
        } else if (i != 2) {
            objArr[0] = "file";
        } else {
            objArr[0] = "processor";
        }
        objArr[1] = "com/intellij/util/io/keyStorage/AppendableStorageBackedByResizableMappedFile";
        if (i != 2) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "processAll";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public AppendableStorageBackedByResizableMappedFile(Path path, int i, StorageLockContext storageLockContext, int i2, boolean z, DataExternalizer<Data> dataExternalizer) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(1);
        }
        ResizeableMappedFile resizeableMappedFile = new ResizeableMappedFile(path, i, storageLockContext, i2, z);
        this.storage = resizeableMappedFile;
        this.dataDescriptor = dataExternalizer;
        this.fileLength = Math.toIntExact(resizeableMappedFile.length());
    }

    private CheckerOutputStream buildOldComparerStream(int i) throws IOException {
        return this.fileLength <= i ? new CheckerOutputStream(i) { // from class: com.intellij.util.io.keyStorage.AppendableStorageBackedByResizableMappedFile.2
            private int address;
            final /* synthetic */ int val$startingOffsetInFile;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$startingOffsetInFile = i;
                this.address = i - AppendableStorageBackedByResizableMappedFile.this.fileLength;
            }

            /* JADX WARN: Code duplicated, block: B:9:0x0029  */
            @Override // java.io.OutputStream
            public void write(int i2) {
                boolean z;
                if (this.same) {
                    if (this.address < AppendMemoryBuffer.getBufferPosition(AppendableStorageBackedByResizableMappedFile.this.appendBuffer)) {
                        byte[] appendBuffer = AppendableStorageBackedByResizableMappedFile.this.appendBuffer.getAppendBuffer();
                        int i3 = this.address;
                        this.address = i3 + 1;
                        if (appendBuffer[i3] == ((byte) i2)) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                    this.same = z;
                }
            }
        } : new CheckerOutputStream(i, this.storage.getPagedFileStorage()) { // from class: com.intellij.util.io.keyStorage.AppendableStorageBackedByResizableMappedFile.3
            private DirectBufferWrapper buffer;
            private int offsetInFile;
            private int offsetInPage;
            private final int pageSize;
            final /* synthetic */ int val$startingOffsetInFile;
            final /* synthetic */ PagedFileStorage val$storage;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$startingOffsetInFile = i;
                this.val$storage = pagedFileStorage;
                this.offsetInFile = i;
                this.offsetInPage = pagedFileStorage.getOffsetInPage(i);
                this.buffer = pagedFileStorage.getByteBuffer(i, false);
                this.pageSize = pagedFileStorage.getPageSize();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                this.buffer.unlock();
            }

            @Override // java.io.OutputStream
            public void write(int i2) throws IOException {
                if (this.same) {
                    int i3 = this.offsetInPage;
                    int i4 = this.pageSize;
                    if (i3 == i4) {
                        int i5 = ((this.offsetInFile / i4) + 1) * i4;
                        this.offsetInFile = i5;
                        if (i5 >= AppendableStorageBackedByResizableMappedFile.this.fileLength) {
                            this.same = false;
                            return;
                        } else {
                            this.buffer.unlock();
                            this.buffer = this.val$storage.getByteBuffer(this.offsetInFile, false);
                            this.offsetInPage = 0;
                        }
                    }
                    this.same = this.buffer.get(this.offsetInPage, true) == ((byte) i2);
                    this.offsetInPage++;
                }
            }
        };
    }

    public static /* synthetic */ MyDataIS c() {
        return new MyDataIS();
    }

    public static /* synthetic */ Boolean d(AppendableStorageBackedByResizableMappedFile appendableStorageBackedByResizableMappedFile, int i, AppendableObjectStorage.StorageObjectProcessor storageObjectProcessor, InputStream inputStream) {
        appendableStorageBackedByResizableMappedFile.getClass();
        LimitedInputStream limitedInputStream = new LimitedInputStream(new BufferedInputStream(inputStream), i) { // from class: com.intellij.util.io.keyStorage.AppendableStorageBackedByResizableMappedFile.1
            public int available() {
                return remainingLimit();
            }
        };
        do {
            try {
            } catch (EOFException unused) {
                return Boolean.TRUE;
            }
        } while (storageObjectProcessor.process(limitedInputStream.getBytesRead(), appendableStorageBackedByResizableMappedFile.dataDescriptor.read(new DataInputStream(limitedInputStream))));
        return Boolean.FALSE;
    }

    public static /* synthetic */ IOException e(AppendableStorageBackedByResizableMappedFile appendableStorageBackedByResizableMappedFile) {
        return new IOException("Can't .close() appendable storage [" + appendableStorageBackedByResizableMappedFile.storage.getPagedFileStorage().getFile() + "]");
    }

    private void flushAppendBuffer() throws IOException {
        if (AppendMemoryBuffer.hasChanges(this.appendBuffer)) {
            int bufferPosition = this.appendBuffer.getBufferPosition();
            this.storage.put(this.fileLength, this.appendBuffer.getAppendBuffer(), 0, bufferPosition);
            this.fileLength += bufferPosition;
            this.appendBuffer = this.appendBuffer.rewind(this.fileLength);
        }
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public int append(Data data) throws IOException {
        BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream();
        this.dataDescriptor.save(new DataOutputStream(bufferExposingByteArrayOutputStream), data);
        int size = bufferExposingByteArrayOutputStream.size();
        byte[] internalBuffer = bufferExposingByteArrayOutputStream.getInternalBuffer();
        int currentLength = getCurrentLength();
        if (size > 4096) {
            flushAppendBuffer();
            this.storage.put(currentLength, internalBuffer, 0, size);
            this.fileLength += size;
            if (this.appendBuffer != null) {
                this.appendBuffer = this.appendBuffer.rewind(this.fileLength);
            }
            return currentLength;
        }
        if (size > 4096 - AppendMemoryBuffer.getBufferPosition(this.appendBuffer)) {
            flushAppendBuffer();
        }
        if (this.appendBuffer == null) {
            this.appendBuffer = new AppendMemoryBuffer(this.fileLength);
        }
        this.appendBuffer.append(internalBuffer, size);
        return currentLength;
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public boolean checkBytesAreTheSame(int i, Data data) throws IOException {
        CheckerOutputStream checkerOutputStreamBuildOldComparerStream = buildOldComparerStream(i);
        try {
            this.dataDescriptor.save(new DataOutputStream(checkerOutputStreamBuildOldComparerStream), data);
            boolean z = checkerOutputStreamBuildOldComparerStream.same;
            checkerOutputStreamBuildOldComparerStream.close();
            return z;
        } catch (Throwable th) {
            if (checkerOutputStreamBuildOldComparerStream != null) {
                try {
                    checkerOutputStreamBuildOldComparerStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public void clear() throws IOException {
        this.storage.clear();
        this.fileLength = 0;
        this.appendBuffer = null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Exception {
        Supplier supplier = new Supplier() { // from class: rd0
            @Override // java.util.function.Supplier
            public final Object get() {
                return AppendableStorageBackedByResizableMappedFile.e(this.b);
            }
        };
        ThrowableRunnable throwableRunnable = new ThrowableRunnable() { // from class: sd0
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() throws IOException {
                this.a.force();
            }
        };
        ResizeableMappedFile resizeableMappedFile = this.storage;
        Objects.requireNonNull(resizeableMappedFile);
        ExceptionUtil.runAllAndRethrowAllExceptions(IOException.class, supplier, throwableRunnable, new u0b(resizeableMappedFile));
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws IOException {
        flushAppendBuffer();
        this.storage.force();
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public int getCurrentLength() {
        return AppendMemoryBuffer.getBufferPosition(this.appendBuffer) + this.fileLength;
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return AppendMemoryBuffer.hasChanges(this.appendBuffer) || this.storage.isDirty();
    }

    public void lockWrite() {
        this.storage.lockWrite();
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public boolean processAll(final AppendableObjectStorage.StorageObjectProcessor<? super Data> storageObjectProcessor) throws IOException {
        if (storageObjectProcessor == null) {
            $$$reportNull$$$0(2);
        }
        this.storage.getStorageLockContext().checkReadLockNotHeld();
        lockWrite();
        try {
            force();
            final int i = this.fileLength;
            if (i == 0) {
                unlockWrite();
                return true;
            }
            unlockWrite();
            IOCancellationCallbackHolder.checkCancelled();
            return ((Boolean) this.storage.readInputStream(new ThrowableNotNullFunction() { // from class: td0
                @Override // com.intellij.openapi.util.ThrowableNotNullFunction
                public final Object fun(Object obj) {
                    return AppendableStorageBackedByResizableMappedFile.d(this.a, i, storageObjectProcessor, (InputStream) obj);
                }
            })).booleanValue();
        } catch (Throwable th) {
            unlockWrite();
            throw th;
        }
    }

    @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage
    public Data read(int i, boolean z) throws IOException {
        AppendMemoryBuffer appendMemoryBuffer = this.appendBuffer;
        if (appendMemoryBuffer == null || i < appendMemoryBuffer.startingOffsetInFile) {
            if (i < this.fileLength) {
                MyDataIS myDataIS = TLOCAL_READ_STREAMS.get();
                myDataIS.setup(this.storage, i, this.fileLength, z);
                return this.dataDescriptor.read(myDataIS);
            }
            throw new NoDataException("Requested address(=" + i + ") points to un-existed data (file length: " + this.fileLength + ")");
        }
        AppendMemoryBuffer appendMemoryBufferCopy = appendMemoryBuffer.copy();
        int i2 = i - appendMemoryBufferCopy.startingOffsetInFile;
        if (i2 <= appendMemoryBufferCopy.bufferPosition) {
            return this.dataDescriptor.read(new DataInputStream(new UnsyncByteArrayInputStream(appendMemoryBufferCopy.getAppendBuffer(), i2, appendMemoryBufferCopy.getBufferPosition())));
        }
        throw new NoDataException("Requested address(=" + i + ") points to un-existed data: " + this.appendBuffer);
    }

    public void unlockWrite() {
        this.storage.unlockWrite();
    }

    public static final class AppendMemoryBuffer {
        private final byte[] buffer;
        private int bufferPosition;
        private final int startingOffsetInFile;

        private AppendMemoryBuffer(byte[] bArr, int i, int i2) {
            this.buffer = bArr;
            this.startingOffsetInFile = i2;
            this.bufferPosition = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized byte[] getAppendBuffer() {
            return this.buffer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized int getBufferPosition() {
            return this.bufferPosition;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean hasChanges(AppendMemoryBuffer appendMemoryBuffer) {
            return appendMemoryBuffer != null && appendMemoryBuffer.getBufferPosition() > 0;
        }

        public synchronized void append(byte[] bArr, int i) {
            System.arraycopy(bArr, 0, this.buffer, this.bufferPosition, i);
            this.bufferPosition += i;
        }

        public synchronized AppendMemoryBuffer copy() {
            return new AppendMemoryBuffer((byte[]) this.buffer.clone(), this.bufferPosition, this.startingOffsetInFile);
        }

        public synchronized AppendMemoryBuffer rewind(int i) {
            return new AppendMemoryBuffer(this.buffer, 0, i);
        }

        public String toString() {
            return "AppendMemoryBuffer[" + this.startingOffsetInFile + ".." + (this.startingOffsetInFile + this.bufferPosition) + "]";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getBufferPosition(AppendMemoryBuffer appendMemoryBuffer) {
            if (appendMemoryBuffer != null) {
                return appendMemoryBuffer.bufferPosition;
            }
            return 0;
        }

        private AppendMemoryBuffer(int i) {
            this(new byte[4096], 0, i);
        }
    }
}
