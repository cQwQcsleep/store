package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.CharsKt;
import core.pro.android.notify.h;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField;
    public boolean closed;
    public final Source source;

    public RealBufferedSource(Source source) {
        CloseableKt.checkNotNullParameter(source, "source");
        this.source = source;
        this.bufferField = new Buffer();
    }

    public static /* synthetic */ void getBuffer$annotations() {
    }

    @Override // com.shadow.okio.BufferedSource, com.shadow.okio.BufferedSink
    public Buffer buffer() {
        return this.bufferField;
    }

    @Override // com.shadow.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.bufferField.clear();
    }

    @Override // com.shadow.okio.BufferedSource
    public boolean exhausted() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.bufferField.exhausted() && this.source.read(this.bufferField, 8192L) == -1;
    }

    @Override // com.shadow.okio.BufferedSource, com.shadow.okio.BufferedSink
    public Buffer getBuffer() {
        return this.bufferField;
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "targetBytes");
        return indexOfElement(byteString, 0L);
    }

    @Override // com.shadow.okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: com.shadow.okio.RealBufferedSource.inputStream.1
            @Override // java.io.InputStream
            public int available() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.closed) {
                    throw new IOException("closed");
                }
                return (int) Math.min(realBufferedSource.bufferField.size(), Integer.MAX_VALUE);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.closed) {
                    throw new IOException("closed");
                }
                if (realBufferedSource.bufferField.size() == 0) {
                    RealBufferedSource realBufferedSource2 = RealBufferedSource.this;
                    if (realBufferedSource2.source.read(realBufferedSource2.bufferField, 8192L) == -1) {
                        return -1;
                    }
                }
                return RealBufferedSource.this.bufferField.readByte() & 255;
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                CloseableKt.checkNotNullParameter(bArr, "data");
                if (!RealBufferedSource.this.closed) {
                    SegmentedByteString.checkOffsetAndCount(bArr.length, i, i2);
                    if (RealBufferedSource.this.bufferField.size() == 0) {
                        RealBufferedSource realBufferedSource = RealBufferedSource.this;
                        if (realBufferedSource.source.read(realBufferedSource.bufferField, 8192L) == -1) {
                            return -1;
                        }
                    }
                    return RealBufferedSource.this.bufferField.read(bArr, i, i2);
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // com.shadow.okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // com.shadow.okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    @Override // com.shadow.okio.BufferedSource
    public int read(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "sink");
        return read(bArr, 0, bArr.length);
    }

    @Override // com.shadow.okio.BufferedSource
    public long readAll(Sink sink) throws IOException {
        CloseableKt.checkNotNullParameter(sink, "sink");
        long j = 0;
        while (this.source.read(this.bufferField, 8192L) != -1) {
            long jCompleteSegmentByteCount = this.bufferField.completeSegmentByteCount();
            if (jCompleteSegmentByteCount > 0) {
                j += jCompleteSegmentByteCount;
                sink.write(this.bufferField, jCompleteSegmentByteCount);
            }
        }
        if (this.bufferField.size() <= 0) {
            return j;
        }
        long size = j + this.bufferField.size();
        Buffer buffer = this.bufferField;
        sink.write(buffer, buffer.size());
        return size;
    }

    @Override // com.shadow.okio.BufferedSource
    public byte readByte() throws EOFException {
        require(1L);
        return this.bufferField.readByte();
    }

    @Override // com.shadow.okio.BufferedSource
    public byte[] readByteArray() throws IOException {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteArray();
    }

    @Override // com.shadow.okio.BufferedSource
    public ByteString readByteString() throws IOException {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteString();
    }

    @Override // com.shadow.okio.BufferedSource
    public long readDecimalLong() throws EOFException {
        byte b;
        require(1L);
        long j = 0;
        while (true) {
            long j2 = j + 1;
            if (!request(j2)) {
                break;
            }
            b = this.bufferField.getByte(j);
            if ((b < 48 || b > 57) && !(j == 0 && b == 45)) {
                break;
            }
            j = j2;
        }
        if (j == 0) {
            CharsKt.a(16);
            String string = Integer.toString(b, 16);
            CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
            throw new NumberFormatException("Expected a digit or '-' but was 0x".concat(string));
        }
        return this.bufferField.readDecimalLong();
    }

    @Override // com.shadow.okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        CloseableKt.checkNotNullParameter(bArr, "sink");
        try {
            require(bArr.length);
            this.bufferField.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.bufferField.size() > 0) {
                Buffer buffer = this.bufferField;
                int i2 = buffer.read(bArr, i, (int) buffer.size());
                if (i2 == -1) {
                    throw new AssertionError();
                }
                i += i2;
            }
            throw e;
        }
    }

    @Override // com.shadow.okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws EOFException {
        byte b;
        require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            b = this.bufferField.getByte(i);
            if ((b < 48 || b > 57) && ((b < 97 || b > 102) && (b < 65 || b > 70))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            CharsKt.a(16);
            String string = Integer.toString(b, 16);
            CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(string));
        }
        return this.bufferField.readHexadecimalUnsignedLong();
    }

    @Override // com.shadow.okio.BufferedSource
    public int readInt() throws EOFException {
        require(4L);
        return this.bufferField.readInt();
    }

    @Override // com.shadow.okio.BufferedSource
    public int readIntLe() throws EOFException {
        require(4L);
        return this.bufferField.readIntLe();
    }

    @Override // com.shadow.okio.BufferedSource
    public long readLong() throws EOFException {
        require(8L);
        return this.bufferField.readLong();
    }

    @Override // com.shadow.okio.BufferedSource
    public long readLongLe() throws EOFException {
        require(8L);
        return this.bufferField.readLongLe();
    }

    @Override // com.shadow.okio.BufferedSource
    public short readShort() throws EOFException {
        require(2L);
        return this.bufferField.readShort();
    }

    @Override // com.shadow.okio.BufferedSource
    public short readShortLe() throws EOFException {
        require(2L);
        return this.bufferField.readShortLe();
    }

    @Override // com.shadow.okio.BufferedSource
    public String readString(long j, Charset charset) throws EOFException {
        CloseableKt.checkNotNullParameter(charset, "charset");
        require(j);
        return this.bufferField.readString(j, charset);
    }

    @Override // com.shadow.okio.BufferedSource
    public String readUtf8() throws IOException {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readUtf8();
    }

    @Override // com.shadow.okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        require(1L);
        byte b = this.bufferField.getByte(0L);
        if ((b & 224) == 192) {
            require(2L);
        } else if ((b & 240) == 224) {
            require(3L);
        } else if ((b & 248) == 240) {
            require(4L);
        }
        return this.bufferField.readUtf8CodePoint();
    }

    @Override // com.shadow.okio.BufferedSource
    public String readUtf8Line() {
        long jIndexOf = indexOf((byte) 10);
        if (jIndexOf != -1) {
            return com.shadow.okio.internal.Buffer.readUtf8Line(this.bufferField, jIndexOf);
        }
        if (this.bufferField.size() != 0) {
            return readUtf8(this.bufferField.size());
        }
        return null;
    }

    @Override // com.shadow.okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // com.shadow.okio.BufferedSource
    public boolean request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(h.c("byteCount < 0: ", j).toString());
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (this.bufferField.size() < j) {
            if (this.source.read(this.bufferField, 8192L) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // com.shadow.okio.BufferedSource
    public void require(long j) throws EOFException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // com.shadow.okio.BufferedSource
    public int select(Options options) throws EOFException {
        CloseableKt.checkNotNullParameter(options, "options");
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            int iSelectPrefix = com.shadow.okio.internal.Buffer.selectPrefix(this.bufferField, options, true);
            if (iSelectPrefix != -2) {
                if (iSelectPrefix != -1) {
                    this.bufferField.skip(options.getByteStrings$okio()[iSelectPrefix].size());
                    return iSelectPrefix;
                }
            } else if (this.source.read(this.bufferField, 8192L) == -1) {
                break;
            }
        }
        return -1;
    }

    @Override // com.shadow.okio.BufferedSource
    public void skip(long j) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.bufferField.size());
            this.bufferField.skip(jMin);
            j -= jMin;
        }
    }

    @Override // com.shadow.okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ')';
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOf(byte b, long j) {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j) {
        CloseableKt.checkNotNullParameter(byteString, "targetBytes");
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOfElement = this.bufferField.indexOfElement(byteString, j);
            if (jIndexOfElement != -1) {
                return jIndexOfElement;
            }
            long size = this.bufferField.size();
            if (this.source.read(this.bufferField, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, size);
        }
    }

    @Override // com.shadow.okio.Source
    public long read(Buffer buffer, long j) {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(h.c("byteCount < 0: ", j).toString());
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192L) == -1) {
            return -1L;
        }
        return this.bufferField.read(buffer, Math.min(j, this.bufferField.size()));
    }

    @Override // com.shadow.okio.BufferedSource
    public String readUtf8LineStrict(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException(h.c("limit < 0: ", j).toString());
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jIndexOf = indexOf((byte) 10, 0L, j2);
        if (jIndexOf != -1) {
            return com.shadow.okio.internal.Buffer.readUtf8Line(this.bufferField, jIndexOf);
        }
        if (j2 < Long.MAX_VALUE && request(j2) && this.bufferField.getByte(j2 - 1) == 13 && request(1 + j2) && this.bufferField.getByte(j2) == 10) {
            return com.shadow.okio.internal.Buffer.readUtf8Line(this.bufferField, j2);
        }
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.bufferField;
        buffer2.copyTo(buffer, 0L, Math.min(32, buffer2.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.bufferField.size(), j) + " content=" + buffer.readByteString().hex() + (char) 8230);
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOf(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        return indexOf(byteString, 0L);
    }

    @Override // com.shadow.okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        if (!this.closed) {
            if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = i3 + j;
                if (!request(1 + j2) || this.bufferField.getByte(j2) != byteString.getByte(i + i3)) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOf(byte b, long j, long j2) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (0 > j || j > j2) {
            throw new IllegalArgumentException(("fromIndex=" + j + " toIndex=" + j2).toString());
        }
        while (j < j2) {
            long jIndexOf = this.bufferField.indexOf(b, j, j2);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            long size = this.bufferField.size();
            if (size >= j2 || this.source.read(this.bufferField, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, size);
        }
        return -1L;
    }

    @Override // com.shadow.okio.BufferedSource
    public String readString(Charset charset) throws IOException {
        CloseableKt.checkNotNullParameter(charset, "charset");
        this.bufferField.writeAll(this.source);
        return this.bufferField.readString(charset);
    }

    @Override // com.shadow.okio.BufferedSource
    public byte[] readByteArray(long j) throws EOFException {
        require(j);
        return this.bufferField.readByteArray(j);
    }

    @Override // com.shadow.okio.BufferedSource
    public ByteString readByteString(long j) throws EOFException {
        require(j);
        return this.bufferField.readByteString(j);
    }

    @Override // com.shadow.okio.BufferedSource
    public String readUtf8(long j) throws EOFException {
        require(j);
        return this.bufferField.readUtf8(j);
    }

    @Override // com.shadow.okio.BufferedSource
    public void readFully(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        try {
            require(j);
            this.bufferField.readFully(buffer, j);
        } catch (EOFException e) {
            buffer.writeAll(this.bufferField);
            throw e;
        }
    }

    @Override // com.shadow.okio.BufferedSource
    public <T> T select(TypedOptions<T> typedOptions) throws IOException {
        CloseableKt.checkNotNullParameter(typedOptions, "options");
        int iSelect = select(typedOptions.getOptions$okio());
        if (iSelect == -1) {
            return null;
        }
        return typedOptions.get(iSelect);
    }

    @Override // com.shadow.okio.BufferedSource
    public long indexOf(ByteString byteString, long j) throws IOException {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOf = this.bufferField.indexOf(byteString, j);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            long size = this.bufferField.size();
            if (this.source.read(this.bufferField, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, (size - byteString.size()) + 1);
        }
    }

    @Override // com.shadow.okio.BufferedSource
    public int read(byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "sink");
        long j = i2;
        SegmentedByteString.checkOffsetAndCount(bArr.length, i, j);
        if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192L) == -1) {
            return -1;
        }
        return this.bufferField.read(bArr, i, (int) Math.min(j, this.bufferField.size()));
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        CloseableKt.checkNotNullParameter(byteBuffer, "sink");
        if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192L) == -1) {
            return -1;
        }
        return this.bufferField.read(byteBuffer);
    }
}
