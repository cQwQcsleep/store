package com.sun.tools.javac.util;

import com.sun.org.apache.xpath.internal.XPath;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ByteBuffer {
    public byte[] elems;
    public int length;

    public static class UnderflowException extends Exception {
        private static final long serialVersionUID = 0;
        private final int length;

        public UnderflowException(int i) {
            this.length = i;
        }

        public int getLength() {
            return this.length;
        }
    }

    public ByteBuffer() {
        this(64);
    }

    public void appendByte(int i) {
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.elems, this.length);
        this.elems = bArrEnsureCapacity;
        int i2 = this.length;
        this.length = i2 + 1;
        bArrEnsureCapacity[i2] = (byte) i;
    }

    public void appendBytes(byte[] bArr, int i, int i2) {
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.elems, this.length + i2);
        this.elems = bArrEnsureCapacity;
        System.arraycopy(bArr, i, bArrEnsureCapacity, this.length, i2);
        this.length += i2;
    }

    public void appendChar(int i) {
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.elems, this.length + 1);
        this.elems = bArrEnsureCapacity;
        int i2 = this.length;
        bArrEnsureCapacity[i2] = (byte) ((i >> 8) & 255);
        bArrEnsureCapacity[i2 + 1] = (byte) (i & 255);
        this.length = i2 + 2;
    }

    public void appendDouble(double d) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
        try {
            new DataOutputStream(byteArrayOutputStream).writeDouble(d);
            appendBytes(byteArrayOutputStream.toByteArray(), 0, 8);
        } catch (IOException unused) {
            x01.a("write");
        }
    }

    public void appendFloat(float f) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
        try {
            new DataOutputStream(byteArrayOutputStream).writeFloat(f);
            appendBytes(byteArrayOutputStream.toByteArray(), 0, 4);
        } catch (IOException unused) {
            x01.a("write");
        }
    }

    public void appendInt(int i) {
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.elems, this.length + 3);
        this.elems = bArrEnsureCapacity;
        int i2 = this.length;
        bArrEnsureCapacity[i2] = (byte) ((i >> 24) & 255);
        bArrEnsureCapacity[i2 + 1] = (byte) ((i >> 16) & 255);
        bArrEnsureCapacity[i2 + 2] = (byte) ((i >> 8) & 255);
        bArrEnsureCapacity[i2 + 3] = (byte) (i & 255);
        this.length = i2 + 4;
    }

    public void appendLong(long j) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
        try {
            new DataOutputStream(byteArrayOutputStream).writeLong(j);
            appendBytes(byteArrayOutputStream.toByteArray(), 0, 8);
        } catch (IOException unused) {
            x01.a("write");
        }
    }

    public void appendName(Name name) {
        int utf8Length = name.getUtf8Length();
        byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.elems, this.length + utf8Length);
        this.elems = bArrEnsureCapacity;
        name.getUtf8Bytes(bArrEnsureCapacity, this.length);
        this.length += utf8Length;
    }

    public void appendStream(InputStream inputStream) throws IOException {
        while (true) {
            int iMax = Math.max(inputStream.available(), 64);
            byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.elems, this.length + iMax);
            this.elems = bArrEnsureCapacity;
            int i = inputStream.read(bArrEnsureCapacity, this.length, iMax);
            if (i == -1) {
                return;
            }
            this.length += i;
            if (i == iMax) {
                int i2 = inputStream.read();
                if (i2 == -1) {
                    return;
                }
                byte[] bArr = this.elems;
                int i3 = this.length;
                this.length = i3 + 1;
                bArr[i3] = (byte) i2;
            }
        }
    }

    public java.nio.ByteBuffer asByteBuffer() {
        return java.nio.ByteBuffer.wrap(this.elems, 0, this.length);
    }

    public byte getByte(int i) throws UnderflowException {
        verifyRange(i, 1);
        return this.elems[i];
    }

    public char getChar(int i) throws UnderflowException {
        verifyRange(i, 2);
        byte[] bArr = this.elems;
        return (char) (((bArr[i] & 255) << 8) + (bArr[i + 1] & 255));
    }

    public double getDouble(int i) throws UnderflowException {
        verifyRange(i, 8);
        try {
            return new DataInputStream(new ByteArrayInputStream(this.elems, i, 8)).readDouble();
        } catch (IOException e) {
            x01.a(e);
            return XPath.MATCH_SCORE_QNAME;
        }
    }

    public float getFloat(int i) throws UnderflowException {
        verifyRange(i, 4);
        try {
            return new DataInputStream(new ByteArrayInputStream(this.elems, i, 4)).readFloat();
        } catch (IOException e) {
            x01.a(e);
            return 0.0f;
        }
    }

    public int getInt(int i) throws UnderflowException {
        verifyRange(i, 4);
        byte[] bArr = this.elems;
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    public long getLong(int i) throws UnderflowException {
        verifyRange(i, 8);
        try {
            return new DataInputStream(new ByteArrayInputStream(this.elems, i, 8)).readLong();
        } catch (IOException e) {
            x01.a(e);
            return 0L;
        }
    }

    public void reset() {
        this.length = 0;
    }

    public Name toName(Names names) throws InvalidUtfException {
        return names.fromUtf(this.elems, 0, this.length, Convert.Validation.STRICT);
    }

    public void verifyRange(int i, int i2) throws UnderflowException {
        if (i < 0 || i2 < 0) {
            dn0.a("off=", i, ", len=", i2);
            return;
        }
        int i3 = i + i2;
        if (i3 < 0 || i3 > this.length) {
            throw new UnderflowException(this.length);
        }
    }

    public ByteBuffer(int i) {
        this(new byte[i]);
    }

    public ByteBuffer(byte[] bArr) {
        this.elems = bArr;
    }

    public void appendBytes(byte[] bArr) {
        appendBytes(bArr, 0, bArr.length);
    }
}
