package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class NioFileSystemFileHandle extends FileHandle {
    private final FileChannel fileChannel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NioFileSystemFileHandle(boolean z, FileChannel fileChannel) {
        super(z);
        CloseableKt.checkNotNullParameter(fileChannel, "fileChannel");
        this.fileChannel = fileChannel;
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedClose() {
        this.fileChannel.close();
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedFlush() {
        this.fileChannel.force(true);
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized int protectedRead(long j, byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "array");
        this.fileChannel.position(j);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, i2);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int i4 = this.fileChannel.read(byteBufferWrap);
            if (i4 != -1) {
                i3 += i4;
            } else if (i3 == 0) {
                return -1;
            }
        }
        return i3;
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedResize(long j) {
        try {
            long size = size();
            long j2 = j - size;
            if (j2 > 0) {
                int i = (int) j2;
                protectedWrite(size, new byte[i], 0, i);
            } else {
                this.fileChannel.truncate(j);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized long protectedSize() {
        return this.fileChannel.size();
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedWrite(long j, byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "array");
        this.fileChannel.position(j);
        this.fileChannel.write(ByteBuffer.wrap(bArr, i, i2));
    }
}
