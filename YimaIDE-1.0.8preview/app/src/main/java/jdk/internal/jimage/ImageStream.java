package jdk.internal.jimage;

import defpackage.b1e;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Objects;
import kotlin.UByte;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ImageStream {
    private ByteBuffer buffer;

    public ImageStream(byte[] bArr, ByteOrder byteOrder) {
        Objects.requireNonNull(bArr);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.buffer = byteBufferWrap;
        Objects.requireNonNull(byteOrder);
        byteBufferWrap.order(byteOrder);
    }

    public ImageStream align(int i) {
        int size = ((1 << i) - 1) & (getSize() - 1);
        for (int i2 = 0; i2 < size; i2++) {
            put((byte) 0);
        }
        return this;
    }

    public void ensure(int i) {
        if (i < 0) {
            b1e.a("Bad value: ", i);
            return;
        }
        if (i > this.buffer.remaining()) {
            byte[] bArrArray = this.buffer.array();
            ByteOrder byteOrderOrder = this.buffer.order();
            int iPosition = this.buffer.position();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i <= bArrArray.length ? bArrArray.length << 1 : i + iPosition);
            this.buffer = byteBufferAllocate;
            byteBufferAllocate.order(byteOrderOrder);
            this.buffer.put(bArrArray, 0, iPosition);
        }
    }

    public int get() {
        return this.buffer.get() & UByte.MAX_VALUE;
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public byte[] getBytes() {
        return this.buffer.array();
    }

    public int getInt() {
        return this.buffer.getInt();
    }

    public long getLong() {
        return this.buffer.getLong();
    }

    public int getPosition() {
        return this.buffer.position();
    }

    public int getShort() {
        return this.buffer.getShort();
    }

    public int getSize() {
        return this.buffer.position();
    }

    public boolean hasByte() {
        return this.buffer.remaining() != 0;
    }

    public boolean hasBytes(int i) {
        return i <= this.buffer.remaining();
    }

    public ImageStream put(ImageStream imageStream) {
        put(imageStream.buffer.array(), 0, imageStream.buffer.position());
        return this;
    }

    public ImageStream putInt(int i) {
        ensure(4);
        this.buffer.putInt(i);
        return this;
    }

    public ImageStream putLong(long j) {
        ensure(8);
        this.buffer.putLong(j);
        return this;
    }

    public ImageStream putShort(short s) {
        ensure(2);
        this.buffer.putShort(s);
        return this;
    }

    public void setPosition(int i) {
        this.buffer.position(i);
    }

    public void skip(int i) {
        if (i < 0) {
            b1e.a("skip value = ", i);
        } else {
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + i);
        }
    }

    public byte[] toArray() {
        return Arrays.copyOf(this.buffer.array(), this.buffer.position());
    }

    public void get(byte[] bArr, int i, int i2) {
        this.buffer.get(bArr, i, i2);
    }

    public ImageStream putShort(int i) {
        return putShort((short) i);
    }

    public ImageStream put(int i) {
        return put((byte) i);
    }

    public ImageStream put(byte[] bArr, int i, int i2) {
        ensure(i2);
        this.buffer.put(bArr, i, i2);
        return this;
    }

    public ImageStream put(byte b) {
        ensure(1);
        this.buffer.put(b);
        return this;
    }

    public ImageStream(int i) {
        this(i, ByteOrder.nativeOrder());
    }

    public ImageStream(byte[] bArr) {
        this(bArr, ByteOrder.nativeOrder());
    }

    public ImageStream(ByteOrder byteOrder) {
        this(NewHope.POLY_SIZE, byteOrder);
    }

    public ImageStream(int i, ByteOrder byteOrder) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        this.buffer = byteBufferAllocate;
        Objects.requireNonNull(byteOrder);
        byteBufferAllocate.order(byteOrder);
    }

    public ImageStream() {
        this(NewHope.POLY_SIZE, ByteOrder.nativeOrder());
    }

    public ImageStream(ByteBuffer byteBuffer) {
        Objects.requireNonNull(byteBuffer);
        this.buffer = byteBuffer;
    }
}
