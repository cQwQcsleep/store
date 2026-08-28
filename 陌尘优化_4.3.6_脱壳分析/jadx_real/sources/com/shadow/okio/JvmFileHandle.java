package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.RandomAccessFile;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class JvmFileHandle extends FileHandle {
    private final RandomAccessFile randomAccessFile;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmFileHandle(boolean z, RandomAccessFile randomAccessFile) {
        super(z);
        CloseableKt.checkNotNullParameter(randomAccessFile, "randomAccessFile");
        this.randomAccessFile = randomAccessFile;
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedClose() {
        this.randomAccessFile.close();
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedFlush() {
        this.randomAccessFile.getFD().sync();
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized int protectedRead(long j, byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "array");
        this.randomAccessFile.seek(j);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int i4 = this.randomAccessFile.read(bArr, i, i2 - i3);
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
                this.randomAccessFile.setLength(j);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized long protectedSize() {
        return this.randomAccessFile.length();
    }

    @Override // com.shadow.okio.FileHandle
    public synchronized void protectedWrite(long j, byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "array");
        this.randomAccessFile.seek(j);
        this.randomAccessFile.write(bArr, i, i2);
    }
}
