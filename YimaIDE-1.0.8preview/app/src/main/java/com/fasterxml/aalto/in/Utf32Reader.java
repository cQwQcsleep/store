package com.fasterxml.aalto.in;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Utf32Reader extends Reader {
    protected final boolean mBigEndian;
    protected byte[] mBuffer;
    protected final ReaderConfig mConfig;
    protected InputStream mIn;
    protected int mLength;
    protected int mPtr;
    protected char mSurrogate = 0;
    protected int mCharCount = 0;
    protected int mByteCount = 0;
    char[] mTmpBuf = null;

    public Utf32Reader(ReaderConfig readerConfig, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        this.mConfig = readerConfig;
        this.mBigEndian = z;
    }

    private boolean loadMore(int i) throws IOException {
        this.mByteCount += this.mLength - i;
        if (i > 0) {
            if (this.mPtr > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    byte[] bArr = this.mBuffer;
                    bArr[i2] = bArr[this.mPtr + i2];
                }
                this.mPtr = 0;
            }
            this.mLength = i;
        } else {
            this.mPtr = 0;
            int i3 = this.mIn.read(this.mBuffer);
            if (i3 < 1) {
                this.mLength = 0;
                if (i3 < 0) {
                    freeBuffers();
                    return false;
                }
                reportStrangeStream();
            }
            this.mLength = i3;
        }
        while (true) {
            int i4 = this.mLength;
            if (i4 >= 4) {
                return true;
            }
            InputStream inputStream = this.mIn;
            byte[] bArr2 = this.mBuffer;
            int i5 = inputStream.read(bArr2, i4, bArr2.length - i4);
            if (i5 < 1) {
                if (i5 < 0) {
                    freeBuffers();
                    reportUnexpectedEOF(this.mLength, 4);
                }
                reportStrangeStream();
            }
            this.mLength += i5;
        }
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        int i3 = (this.mByteCount + this.mPtr) - 1;
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this.mCharCount + i2) + ", byte #" + i3 + ")");
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this.mByteCount + i;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + this.mCharCount + ", byte #" + i3 + ")");
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.mIn;
        if (inputStream != null) {
            this.mIn = null;
            freeBuffers();
            inputStream.close();
        }
    }

    public final void freeBuffers() {
        byte[] bArr = this.mBuffer;
        if (bArr != null) {
            this.mBuffer = null;
            ReaderConfig readerConfig = this.mConfig;
            if (readerConfig != null) {
                readerConfig.freeFullBBuffer(bArr);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00e6 A[LOOP:0: B:22:0x0033->B:48:0x00e6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:53:0x00e4 A[SYNTHETIC] */
    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        if (this.mBuffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i7 = i2 + i;
        char c = this.mSurrogate;
        if (c != 0) {
            i3 = i + 1;
            cArr[i] = c;
            this.mSurrogate = (char) 0;
        } else {
            int i8 = this.mLength - this.mPtr;
            if (i8 < 4 && !loadMore(i8)) {
                return -1;
            }
            i3 = i;
        }
        byte[] bArr = this.mBuffer;
        while (i3 < i7) {
            int i9 = this.mPtr;
            if (this.mBigEndian) {
                i4 = (bArr[i9] << 24) | ((bArr[i9 + 1] & 255) << 16) | ((bArr[i9 + 2] & 255) << 8);
                i5 = bArr[i9 + 3] & 255;
            } else {
                i4 = (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16);
                i5 = bArr[i9 + 3] << 24;
            }
            int i10 = i4 | i5;
            this.mPtr = i9 + 4;
            if (i10 < 55296) {
                i6 = i3 + 1;
                cArr[i3] = (char) i10;
                if (this.mPtr >= this.mLength) {
                    i3 = i6;
                    break;
                }
                i3 = i6;
            } else {
                if (i10 > 1114111) {
                    reportInvalid(i10, i3 - i, "(above " + Integer.toHexString(1114111) + ") ");
                }
                if (i10 > 65535) {
                    int i11 = i10 - 65536;
                    int i12 = i3 + 1;
                    cArr[i3] = (char) ((i11 >> 10) + 55296);
                    i10 = (i11 & 1023) | 56320;
                    if (i12 >= i7) {
                        this.mSurrogate = (char) i10;
                        i3 = i12;
                        break;
                    }
                    i3 = i12;
                } else if (i10 < 57344) {
                    reportInvalid(i10, i3 - i, "(a surrogate char) ");
                } else if (i10 >= 65534) {
                    reportInvalid(i10, i3 - i, XmlPullParser.NO_NAMESPACE);
                }
                i6 = i3 + 1;
                cArr[i3] = (char) i10;
                if (this.mPtr >= this.mLength) {
                    i3 = i6;
                    break;
                }
                i3 = i6;
            }
        }
        int i13 = i3 - i;
        this.mCharCount += i13;
        return i13;
    }

    public void reportBounds(char[] cArr, int i, int i2) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i + "," + i2 + "), cbuf[" + cArr.length + "]");
    }

    public void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        if (this.mTmpBuf == null) {
            this.mTmpBuf = new char[1];
        }
        if (read(this.mTmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this.mTmpBuf[0];
    }
}
