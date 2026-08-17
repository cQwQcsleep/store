package com.intellij.util.io;

import com.intellij.util.io.DirectBufferWrapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DirectBufferWrapper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final AtomicIntegerFieldUpdater<DirectBufferWrapper> REF_UPDATER = AtomicIntegerFieldUpdater.newUpdater(DirectBufferWrapper.class, "myReferences");
    private volatile ByteBuffer myBuffer;
    private volatile int myBufferDataEndPos;
    private volatile boolean myDirty;
    private final PagedFileStorage myFile;
    private final long myPosition;
    private volatile int myReferences;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "data";
        } else if (i != 2) {
            objArr[0] = "file";
        } else {
            objArr[0] = "com/intellij/util/io/DirectBufferWrapper";
        }
        if (i != 2) {
            objArr[1] = "com/intellij/util/io/DirectBufferWrapper";
        } else {
            objArr[1] = "getFile";
        }
        if (i == 1) {
            objArr[2] = "putFromBuffer";
        } else if (i != 2) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    public DirectBufferWrapper(PagedFileStorage pagedFileStorage, long j) throws IOException {
        if (pagedFileStorage == null) {
            $$$reportNull$$$0(0);
        }
        this.myReferences = 0;
        pagedFileStorage.getStorageLockContext().assertUnderSegmentAllocationLock();
        this.myFile = pagedFileStorage;
        this.myPosition = j;
        this.myBuffer = allocateAndLoadFileContent();
    }

    public static /* synthetic */ ByteBuffer a(DirectBufferWrapper directBufferWrapper, ByteBuffer byteBuffer, int i, FileChannel fileChannel) throws IOException {
        int i2 = fileChannel.read(byteBuffer, directBufferWrapper.myPosition);
        if (i2 < i) {
            for (int iMax = Math.max(0, i2); iMax < i; iMax++) {
                byteBuffer.put(iMax, (byte) 0);
            }
        }
        return byteBuffer;
    }

    private ByteBuffer allocateAndLoadFileContent() throws IOException {
        final int pageSize = this.myFile.getPageSize();
        final ByteBuffer byteBufferAllocate = DirectByteBufferAllocator.ALLOCATOR.allocate(pageSize);
        byteBufferAllocate.order(this.myFile.isNativeBytesOrder() ? ByteOrder.nativeOrder() : ByteOrder.BIG_ENDIAN);
        return (ByteBuffer) this.myFile.executeIdempotentOp(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: pt3
            @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
            public final Object execute(FileChannel fileChannel) {
                return DirectBufferWrapper.a(this.a, byteBufferAllocate, pageSize, fileChannel);
            }
        }, this.myFile.isReadOnly());
    }

    public static /* synthetic */ Object b(DirectBufferWrapper directBufferWrapper, ByteBuffer byteBuffer, FileChannel fileChannel) throws IOException {
        fileChannel.write(byteBuffer, directBufferWrapper.myPosition);
        return null;
    }

    public static /* synthetic */ int c(int i) {
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public static /* synthetic */ int d(int i) {
        return i >= 0 ? i + 1 : i;
    }

    public ByteBuffer copy() {
        ByteBuffer byteBufferDuplicate = this.myBuffer.duplicate();
        byteBufferDuplicate.order(this.myBuffer.order());
        return byteBufferDuplicate;
    }

    public void fileSizeMayChanged(int i) {
        if (i > this.myBufferDataEndPos) {
            this.myBufferDataEndPos = i;
            this.myFile.ensureCachedSizeAtLeast(this.myPosition + ((long) this.myBufferDataEndPos));
        }
    }

    public void force() throws IOException {
        this.myFile.getStorageLockContext().assertUnderSegmentAllocationLock();
        if (this.myFile.isReadOnly()) {
            qu7.a("Can't flush .readOnly page: ", this);
            return;
        }
        if (isDirty()) {
            final ByteBuffer byteBufferDuplicate = this.myBuffer.duplicate();
            byteBufferDuplicate.rewind();
            byteBufferDuplicate.limit(this.myBufferDataEndPos);
            this.myFile.executeIdempotentOp(new FileChannelInterruptsRetryer.FileChannelIdempotentOperation() { // from class: qt3
                @Override // com.intellij.util.io.FileChannelInterruptsRetryer.FileChannelIdempotentOperation
                public final Object execute(FileChannel fileChannel) {
                    return DirectBufferWrapper.b(this.a, byteBufferDuplicate, fileChannel);
                }
            }, false);
            this.myDirty = false;
        }
    }

    public byte get(int i, boolean z) {
        if (z) {
            this.myFile.getStorageLockContext().checkReadAccess();
        }
        return this.myBuffer.get(i);
    }

    public PagedFileStorage getFile() {
        PagedFileStorage pagedFileStorage = this.myFile;
        if (pagedFileStorage == null) {
            $$$reportNull$$$0(2);
        }
        return pagedFileStorage;
    }

    public int getInt(int i) {
        this.myFile.getStorageLockContext().checkReadAccess();
        return this.myBuffer.getInt(i);
    }

    public int getLength() {
        return this.myFile.getPageSize();
    }

    public long getLong(int i) {
        this.myFile.getStorageLockContext().checkReadAccess();
        return this.myBuffer.getLong(i);
    }

    public boolean isDirty() {
        return this.myDirty;
    }

    public boolean isReleased() {
        return this.myReferences == -1;
    }

    public void markDirty() throws IOException {
        if (this.myDirty) {
            return;
        }
        if (this.myFile.isReadOnly()) {
            u8g.a("Read-only byte buffer can't be modified. File: ", this.myFile);
        } else {
            this.myDirty = true;
            this.myFile.markDirty();
        }
    }

    public void position(int i) {
        this.myFile.getStorageLockContext().checkWriteAccess();
        this.myBuffer.position(i);
    }

    public void put(ByteBuffer byteBuffer) throws IOException {
        this.myFile.getStorageLockContext().checkWriteAccess();
        markDirty();
        this.myBuffer.put(byteBuffer);
        fileSizeMayChanged(this.myBuffer.position());
    }

    public void putFromArray(byte[] bArr, int i, int i2, int i3) throws IOException, IllegalArgumentException {
        this.myFile.getStorageLockContext().checkWriteAccess();
        markDirty();
        ByteBuffer byteBufferDuplicate = this.myBuffer.duplicate();
        byteBufferDuplicate.position(i2);
        byteBufferDuplicate.put(bArr, i, i3);
        fileSizeMayChanged(byteBufferDuplicate.position());
    }

    public void putInt(int i, int i2) throws IOException {
        this.myFile.getStorageLockContext().checkWriteAccess();
        markDirty();
        this.myBuffer.putInt(i, i2);
        fileSizeMayChanged(i + 4);
    }

    public void putLong(int i, long j) throws IOException {
        this.myFile.getStorageLockContext().checkWriteAccess();
        markDirty();
        this.myBuffer.putLong(i, j);
        fileSizeMayChanged(i + 8);
    }

    public void readToArray(byte[] bArr, int i, int i2, int i3, boolean z) throws IllegalArgumentException {
        if (z) {
            this.myFile.getStorageLockContext().checkReadAccess();
        }
        ByteBufferUtil.copyMemory(this.myBuffer, i2, bArr, i, i3);
    }

    public String toString() {
        return "Buffer for " + this.myFile + ", offset:" + this.myPosition + ", size: " + this.myFile.getPageSize();
    }

    public boolean tryLock() {
        return REF_UPDATER.updateAndGet(this, new IntUnaryOperator() { // from class: st3
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return DirectBufferWrapper.d(i);
            }
        }) >= 0;
    }

    public boolean tryRelease(boolean z) throws IOException {
        boolean z2 = REF_UPDATER.updateAndGet(this, new IntUnaryOperator() { // from class: rt3
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return DirectBufferWrapper.c(i);
            }
        }) == -1;
        if (!z2 && !z) {
            return false;
        }
        this.myFile.getStorageLockContext().assertUnderSegmentAllocationLock();
        if (isDirty()) {
            force();
        }
        if (this.myBuffer != null) {
            DirectByteBufferAllocator.ALLOCATOR.release(this.myBuffer);
            this.myBuffer = null;
        }
        if (z && !z2) {
            PagedFileStorage.LOG.error("Page buffer is referenced but was forcibly released for file " + this.myFile.getFile());
        }
        return true;
    }

    public void unlock() {
        REF_UPDATER.decrementAndGet(this);
    }
}
