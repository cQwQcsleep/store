package com.shadow.okio.internal;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Buffer;
import com.shadow.okio.ForwardingSource;
import com.shadow.okio.Source;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class FixedLengthSource extends ForwardingSource {
    private long bytesReceived;
    private final long size;
    private final boolean truncate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedLengthSource(Source source, long j, boolean z) {
        super(source);
        CloseableKt.checkNotNullParameter(source, "delegate");
        this.size = j;
        this.truncate = z;
    }

    private final void truncateToSize(Buffer buffer, long j) throws IOException {
        Buffer buffer2 = new Buffer();
        buffer2.writeAll(buffer);
        buffer.write(buffer2, j);
        buffer2.clear();
    }

    @Override // com.shadow.okio.ForwardingSource, com.shadow.okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        long j2 = this.bytesReceived;
        long j3 = this.size;
        if (j2 > j3) {
            j = 0;
        } else if (this.truncate) {
            long j4 = j3 - j2;
            if (j4 == 0) {
                return -1L;
            }
            j = Math.min(j, j4);
        }
        long j5 = super.read(buffer, j);
        if (j5 != -1) {
            this.bytesReceived += j5;
        }
        long j6 = this.bytesReceived;
        long j7 = this.size;
        if ((j6 >= j7 || j5 != -1) && j6 <= j7) {
            return j5;
        }
        if (j5 > 0 && j6 > j7) {
            truncateToSize(buffer, buffer.size() - (this.bytesReceived - this.size));
        }
        throw new IOException("expected " + this.size + " bytes but got " + this.bytesReceived);
    }
}
