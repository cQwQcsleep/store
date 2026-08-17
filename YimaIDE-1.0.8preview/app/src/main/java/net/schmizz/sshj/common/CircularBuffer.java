package net.schmizz.sshj.common;

import defpackage.drd;
import net.schmizz.sshj.common.CircularBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CircularBuffer<T extends CircularBuffer<T>> {
    private byte[] data;
    private final int maxSize;
    private int rpos;
    private int wpos;

    public static class CircularBufferException extends SSHException {
        public CircularBufferException(String str) {
            super(str);
        }
    }

    public static final class PlainCircularBuffer extends CircularBuffer<PlainCircularBuffer> {
        public PlainCircularBuffer(int i, int i2) {
            super(i, i2);
        }
    }

    public CircularBuffer(int i, int i2) {
        this.maxSize = i2;
        if (i > i2) {
            drd.a("Initial requested size %d larger than maximum size %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
            throw null;
        }
        this.data = new byte[getNextSize(i)];
        this.rpos = 0;
        this.wpos = 0;
    }

    private void ensureAvailable(int i) throws CircularBufferException {
        if (available() < i) {
            throw new CircularBufferException("Underflow");
        }
    }

    private int getNextSize(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
            if (i2 <= 0) {
                return this.maxSize;
            }
        }
        return Math.min(i2, this.maxSize);
    }

    public int available() {
        int i = this.wpos - this.rpos;
        return i >= 0 ? i : i + this.data.length;
    }

    public void ensureCapacity(int i) throws CircularBufferException {
        int iAvailable = available();
        if (this.data.length - iAvailable <= i) {
            int i2 = i + iAvailable + 1;
            int nextSize = getNextSize(i2);
            if (nextSize < i2) {
                throw new CircularBufferException("Attempted overflow");
            }
            byte[] bArr = new byte[nextSize];
            int i3 = this.wpos;
            int i4 = this.rpos;
            byte[] bArr2 = this.data;
            if (i3 >= i4) {
                System.arraycopy(bArr2, i4, bArr, 0, iAvailable);
                this.wpos -= this.rpos;
            } else {
                int length = bArr2.length - i4;
                System.arraycopy(bArr2, i4, bArr, 0, length);
                System.arraycopy(this.data, 0, bArr, length, this.wpos);
                this.wpos += length;
            }
            this.rpos = 0;
            this.data = bArr;
        }
    }

    public int length() {
        return this.data.length;
    }

    public int maxPossibleRemainingCapacity() {
        int length = (this.rpos - this.wpos) - 1;
        if (length < 0) {
            length += this.data.length;
        }
        return (length + this.maxSize) - this.data.length;
    }

    public T putRawBytes(byte[] bArr, int i, int i2) throws CircularBufferException {
        ensureCapacity(i2);
        int i3 = this.wpos;
        int i4 = i3 + i2;
        byte[] bArr2 = this.data;
        if (i4 <= bArr2.length) {
            System.arraycopy(bArr, i, bArr2, i3, i2);
        } else {
            int length = bArr2.length - i3;
            System.arraycopy(bArr, i, bArr2, i3, length);
            int i5 = i2 - length;
            System.arraycopy(bArr, i + length, this.data, 0, i5);
            i4 = i5;
        }
        this.wpos = i4;
        return this;
    }

    public void readRawBytes(byte[] bArr, int i, int i2) throws CircularBufferException {
        ensureAvailable(i2);
        int i3 = this.rpos;
        int i4 = i3 + i2;
        byte[] bArr2 = this.data;
        if (i4 <= bArr2.length) {
            System.arraycopy(bArr2, i3, bArr, i, i2);
        } else {
            int length = bArr2.length - i3;
            System.arraycopy(bArr2, i3, bArr, i, length);
            int i5 = i2 - length;
            System.arraycopy(this.data, 0, bArr, i + length, i5);
            i4 = i5;
        }
        this.rpos = i4;
    }

    public String toString() {
        return "CircularBuffer [rpos=" + this.rpos + ", wpos=" + this.wpos + ", size=" + this.data.length + "]";
    }
}
