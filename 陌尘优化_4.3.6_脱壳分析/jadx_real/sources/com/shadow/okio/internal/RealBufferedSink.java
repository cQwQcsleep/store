package com.shadow.okio.internal;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Buffer;
import com.shadow.okio.BufferedSink;
import com.shadow.okio.ByteString;
import com.shadow.okio.Sink;
import com.shadow.okio.Source;
import com.shadow.okio.Timeout;
import java.io.EOFException;
import java.io.IOException;

/* renamed from: com.shadow.okio.internal.-RealBufferedSink, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class RealBufferedSink {
    public static final void commonClose(com.shadow.okio.RealBufferedSink realBufferedSink) throws Throwable {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            return;
        }
        try {
            if (realBufferedSink.bufferField.size() > 0) {
                Sink sink = realBufferedSink.sink;
                Buffer buffer = realBufferedSink.bufferField;
                sink.write(buffer, buffer.size());
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            realBufferedSink.sink.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        realBufferedSink.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public static final BufferedSink commonEmit(com.shadow.okio.RealBufferedSink realBufferedSink) throws IOException {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        long size = realBufferedSink.bufferField.size();
        if (size > 0) {
            realBufferedSink.sink.write(realBufferedSink.bufferField, size);
        }
        return realBufferedSink;
    }

    public static final BufferedSink commonEmitCompleteSegments(com.shadow.okio.RealBufferedSink realBufferedSink) throws IOException {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        long jCompleteSegmentByteCount = realBufferedSink.bufferField.completeSegmentByteCount();
        if (jCompleteSegmentByteCount > 0) {
            realBufferedSink.sink.write(realBufferedSink.bufferField, jCompleteSegmentByteCount);
        }
        return realBufferedSink;
    }

    public static final void commonFlush(com.shadow.okio.RealBufferedSink realBufferedSink) throws IOException {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        if (realBufferedSink.bufferField.size() > 0) {
            Sink sink = realBufferedSink.sink;
            Buffer buffer = realBufferedSink.bufferField;
            sink.write(buffer, buffer.size());
        }
        realBufferedSink.sink.flush();
    }

    public static final Timeout commonTimeout(com.shadow.okio.RealBufferedSink realBufferedSink) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        return realBufferedSink.sink.timeout();
    }

    public static final String commonToString(com.shadow.okio.RealBufferedSink realBufferedSink) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        return "buffer(" + realBufferedSink.sink + ')';
    }

    public static final void commonWrite(com.shadow.okio.RealBufferedSink realBufferedSink, Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(buffer, "source");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.write(buffer, j);
        realBufferedSink.emitCompleteSegments();
    }

    public static final long commonWriteAll(com.shadow.okio.RealBufferedSink realBufferedSink, Source source) throws IOException {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long j2 = source.read(realBufferedSink.bufferField, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            realBufferedSink.emitCompleteSegments();
        }
    }

    public static final BufferedSink commonWriteByte(com.shadow.okio.RealBufferedSink realBufferedSink, int i) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeByte(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteDecimalLong(com.shadow.okio.RealBufferedSink realBufferedSink, long j) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeDecimalLong(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteHexadecimalUnsignedLong(com.shadow.okio.RealBufferedSink realBufferedSink, long j) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeHexadecimalUnsignedLong(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteInt(com.shadow.okio.RealBufferedSink realBufferedSink, int i) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeInt(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteIntLe(com.shadow.okio.RealBufferedSink realBufferedSink, int i) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeIntLe(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLong(com.shadow.okio.RealBufferedSink realBufferedSink, long j) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeLong(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteLongLe(com.shadow.okio.RealBufferedSink realBufferedSink, long j) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeLongLe(j);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShort(com.shadow.okio.RealBufferedSink realBufferedSink, int i) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeShort(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteShortLe(com.shadow.okio.RealBufferedSink realBufferedSink, int i) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeShortLe(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8(com.shadow.okio.RealBufferedSink realBufferedSink, String str) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(str, "string");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeUtf8(str);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWriteUtf8CodePoint(com.shadow.okio.RealBufferedSink realBufferedSink, int i) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        if (realBufferedSink.closed) {
            throw new IllegalStateException("closed");
        }
        realBufferedSink.bufferField.writeUtf8CodePoint(i);
        return realBufferedSink.emitCompleteSegments();
    }

    public static final BufferedSink commonWrite(com.shadow.okio.RealBufferedSink realBufferedSink, ByteString byteString) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(byteString, "byteString");
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.write(byteString);
            return realBufferedSink.emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public static final BufferedSink commonWriteUtf8(com.shadow.okio.RealBufferedSink realBufferedSink, String str, int i, int i2) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(str, "string");
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.writeUtf8(str, i, i2);
            return realBufferedSink.emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public static final BufferedSink commonWrite(com.shadow.okio.RealBufferedSink realBufferedSink, ByteString byteString, int i, int i2) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(byteString, "byteString");
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.write(byteString, i, i2);
            return realBufferedSink.emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public static final BufferedSink commonWrite(com.shadow.okio.RealBufferedSink realBufferedSink, byte[] bArr) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "source");
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.write(bArr);
            return realBufferedSink.emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public static final BufferedSink commonWrite(com.shadow.okio.RealBufferedSink realBufferedSink, byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "source");
        if (!realBufferedSink.closed) {
            realBufferedSink.bufferField.write(bArr, i, i2);
            return realBufferedSink.emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    public static final BufferedSink commonWrite(com.shadow.okio.RealBufferedSink realBufferedSink, Source source, long j) throws IOException {
        CloseableKt.checkNotNullParameter(realBufferedSink, "<this>");
        CloseableKt.checkNotNullParameter(source, "source");
        while (j > 0) {
            long j2 = source.read(realBufferedSink.bufferField, j);
            if (j2 != -1) {
                j -= j2;
                realBufferedSink.emitCompleteSegments();
            } else {
                throw new EOFException();
            }
        }
        return realBufferedSink;
    }
}
