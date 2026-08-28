package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.internal._ZlibJvmKt;
import core.pro.android.notify.h;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class GzipSink implements Sink {
    private boolean closed;
    private final CRC32 crc;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final RealBufferedSink sink;

    public GzipSink(Sink sink) {
        CloseableKt.checkNotNullParameter(sink, "sink");
        RealBufferedSink realBufferedSink = new RealBufferedSink(sink);
        this.sink = realBufferedSink;
        Deflater deflater = new Deflater(_ZlibJvmKt.getDEFAULT_COMPRESSION(), true);
        this.deflater = deflater;
        this.deflaterSink = new DeflaterSink((BufferedSink) realBufferedSink, deflater);
        this.crc = new CRC32();
        Buffer buffer = realBufferedSink.bufferField;
        buffer.writeShort(8075);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }

    private final void updateCrc(Buffer buffer, long j) {
        Segment segment = buffer.head;
        CloseableKt.checkNotNull(segment);
        while (j > 0) {
            int iMin = (int) Math.min(j, segment.limit - segment.pos);
            this.crc.update(segment.data, segment.pos, iMin);
            j -= iMin;
            segment = segment.next;
            CloseableKt.checkNotNull(segment);
        }
    }

    private final void writeFooter() {
        this.sink.writeIntLe((int) this.crc.getValue());
        this.sink.writeIntLe((int) this.deflater.getBytesRead());
    }

    /* renamed from: -deprecated_deflater, reason: not valid java name */
    public final Deflater m174deprecated_deflater() {
        return this.deflater;
    }

    @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        try {
            this.deflaterSink.finishDeflate$okio();
            writeFooter();
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.deflater.end();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        try {
            this.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public final Deflater deflater() {
        return this.deflater;
    }

    @Override // com.shadow.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    @Override // com.shadow.okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override // com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        if (j < 0) {
            throw new IllegalArgumentException(h.c("byteCount < 0: ", j).toString());
        }
        if (j == 0) {
            return;
        }
        updateCrc(buffer, j);
        this.deflaterSink.write(buffer, j);
    }
}
