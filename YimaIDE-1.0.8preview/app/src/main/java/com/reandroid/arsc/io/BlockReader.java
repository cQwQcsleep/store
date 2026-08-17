package com.reandroid.arsc.io;

import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.header.SpecHeader;
import com.reandroid.arsc.header.TypeHeader;
import defpackage.lx0;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BlockReader extends InputStream {
    private byte[] BUFFER;
    private boolean mIsClosed;
    private final int mLength;
    private final Object mLock;
    private int mMark;
    private int mPosition;
    private final int mStart;

    public BlockReader(byte[] bArr, int i, int i2) {
        this.mLock = new Object();
        this.BUFFER = bArr;
        this.mStart = i;
        this.mLength = i2;
        this.mPosition = 0;
    }

    private static byte[] loadBuffer(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[40960];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
        if (inputStream instanceof FileInputStream) {
            inputStream.close();
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private void setPosition(int i) {
        synchronized (this.mLock) {
            this.mPosition = i;
        }
    }

    private int toInt(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
    }

    private short toShort(byte[] bArr) {
        return (short) ((bArr[0] & 255) | ((bArr[1] & 255) << 8));
    }

    @Override // java.io.InputStream
    public int available() {
        return this.mLength - this.mPosition;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mIsClosed = true;
        this.BUFFER = null;
        this.mMark = 0;
    }

    public BlockReader create(int i, int i2) {
        int i3 = i + i2;
        if (i2 < 0 || i3 > this.mLength) {
            i2 = this.mLength - i;
        }
        return new BlockReader(this.BUFFER, i + this.mStart, i2);
    }

    public int getActualPosition() {
        return this.mStart + this.mPosition;
    }

    public byte[] getBuffer() {
        return this.BUFFER;
    }

    public byte[] getBytes() {
        int length = length();
        byte[] bArr = this.BUFFER;
        if (bArr.length == length) {
            return bArr;
        }
        byte[] bArr2 = new byte[length];
        if (length == 0) {
            return bArr2;
        }
        System.arraycopy(bArr, this.mStart, bArr2, 0, length);
        return bArr2;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean isAvailable() {
        return !this.mIsClosed && available() > 0;
    }

    public int length() {
        return this.mLength;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.mMark = i;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public void offset(int i) {
        seek(getPosition() + i);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i;
        if (this.mIsClosed) {
            a16.a("Stream is closed");
            return 0;
        }
        int i2 = this.mPosition;
        if (i2 >= this.mLength) {
            throw new EOFException("Finished reading: " + i2);
        }
        synchronized (this.mLock) {
            i = this.BUFFER[this.mStart + i2] & 255;
            this.mPosition++;
        }
        return i;
    }

    public byte[] readBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        if (i != 0) {
            int i2 = read(bArr);
            if (i2 < 0) {
                throw new EOFException("Finished reading: " + this.mPosition);
            }
            if (i != i2) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, 0, bArr2, 0, i2);
                return bArr2;
            }
        }
        return bArr;
    }

    public int readFully(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (this.mIsClosed) {
            a16.a("Stream is closed");
            return 0;
        }
        if (this.mPosition >= this.mLength) {
            throw new EOFException("Finished reading: " + this.mPosition);
        }
        if (i2 > bArr.length) {
            i2 = bArr.length;
        }
        synchronized (this.mLock) {
            try {
                int i4 = this.mStart + this.mPosition;
                while (i3 < i2) {
                    bArr[i + i3] = this.BUFFER[i4 + i3];
                    int i5 = this.mPosition + 1;
                    this.mPosition = i5;
                    if (i5 >= this.mLength) {
                        i3++;
                        break;
                    }
                    i3++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i3;
    }

    public InfoHeader readHeaderBlock() throws IOException {
        return InfoHeader.read(this);
    }

    public int readInteger() throws IOException {
        int position = getPosition();
        byte[] bArr = new byte[4];
        readFully(bArr);
        seek(position);
        return toInt(bArr);
    }

    public short readShort() throws IOException {
        int position = getPosition();
        byte[] bArr = new byte[2];
        readFully(bArr);
        seek(position);
        return toShort(bArr);
    }

    @Deprecated
    public SpecHeader readSpecHeader() throws IOException {
        return SpecHeader.read(this);
    }

    @Deprecated
    public TypeHeader readTypeHeader() throws IOException {
        return TypeHeader.read(this);
    }

    public int readUnsignedShort() throws IOException {
        return readShort() & 65535;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (this.mIsClosed) {
            a16.a("Can not reset stream is closed");
        } else {
            this.mPosition = this.mMark;
        }
    }

    public void seek(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > length()) {
            i = length();
        }
        setPosition(i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(": ");
        if (this.mIsClosed) {
            sb.append("Closed");
        } else {
            int iAvailable = available();
            if (iAvailable == 0) {
                sb.append("Finished: ");
                sb.append(getPosition());
            } else {
                if (this.mStart > 0) {
                    sb.append("START=");
                    sb.append(this.mStart);
                    sb.append(", ACTUAL=");
                    sb.append(getActualPosition());
                    sb.append(", ");
                }
                sb.append("POS=");
                sb.append(getPosition());
                sb.append(", available=");
                sb.append(iAvailable);
            }
        }
        return sb.toString();
    }

    public static InfoHeader readHeaderBlock(File file) throws IOException {
        return InfoHeader.readHeaderBlock(file);
    }

    public static InfoHeader readHeaderBlock(InputStream inputStream) throws IOException {
        return InfoHeader.readHeaderBlock(inputStream);
    }

    public static InfoHeader readHeaderBlock(byte[] bArr) throws IOException {
        return InfoHeader.readHeaderBlock(bArr);
    }

    public BlockReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public BlockReader(InputStream inputStream) throws IOException {
        this(loadBuffer(inputStream));
    }

    public BlockReader(InputStream inputStream, int i) throws IOException {
        this(loadBuffer(inputStream, i));
    }

    public BlockReader create(int i) {
        return create(getPosition(), i);
    }

    public BlockReader(File file) throws IOException {
        this(loadBuffer(file));
    }

    private static byte[] loadBuffer(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArrLoadBuffer = loadBuffer(fileInputStream);
        fileInputStream.close();
        return bArrLoadBuffer;
    }

    private static byte[] loadBuffer(InputStream inputStream, int i) throws IOException {
        int i2;
        byte[] bArr = new byte[i];
        if (i == 0 || (i2 = inputStream.read(bArr, 0, i)) >= i) {
            return bArr;
        }
        lx0.a("Read length is less than expected: length=", i, ", read=", i2);
        return null;
    }

    public int readFully(byte[] bArr, int i) throws IOException {
        if (i == 0) {
            return 0;
        }
        return readFully(bArr, 0, i);
    }

    public int readFully(byte[] bArr) throws IOException {
        return readFully(bArr, 0, bArr.length);
    }
}
