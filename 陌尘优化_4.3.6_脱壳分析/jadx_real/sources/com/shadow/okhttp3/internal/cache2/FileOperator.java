package com.shadow.okhttp3.internal.cache2;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Buffer;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class FileOperator {
    private final FileChannel fileChannel;

    public FileOperator(FileChannel fileChannel) {
        CloseableKt.checkNotNullParameter(fileChannel, "fileChannel");
        this.fileChannel = fileChannel;
    }

    public final void read(long j, Buffer buffer, long j2) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferTo = this.fileChannel.transferTo(j, j2, buffer);
            j += jTransferTo;
            j2 -= jTransferTo;
        }
    }

    public final void write(long j, Buffer buffer, long j2) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        if (j2 < 0 || j2 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        long j3 = j;
        long j4 = j2;
        while (j4 > 0) {
            long jTransferFrom = this.fileChannel.transferFrom(buffer, j3, j4);
            j3 += jTransferFrom;
            j4 -= jTransferFrom;
        }
    }
}
