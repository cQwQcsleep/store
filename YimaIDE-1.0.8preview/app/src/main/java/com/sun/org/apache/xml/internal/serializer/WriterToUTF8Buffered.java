package com.sun.org.apache.xml.internal.serializer;

import com.sun.jna.platform.win32.Ddeml;
import com.sun.jna.platform.win32.WinError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class WriterToUTF8Buffered extends Writer implements WriterChain {
    private static final int BYTES_MAX = 16384;
    private static final int CHARS_MAX = 5461;
    private final OutputStream m_os;
    private final byte[] m_outputBytes = new byte[Ddeml.DMLERR_DLL_NOT_INITIALIZED];
    private final char[] m_inputChars = new char[5463];
    private int count = 0;

    public WriterToUTF8Buffered(OutputStream outputStream) throws UnsupportedEncodingException {
        this.m_os = outputStream;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void close() throws IOException {
        flushBuffer();
        this.m_os.close();
    }

    @Override // java.io.Writer, java.io.Flushable, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void flush() throws IOException {
        flushBuffer();
        this.m_os.flush();
    }

    public void flushBuffer() throws IOException {
        int i = this.count;
        if (i > 0) {
            this.m_os.write(this.m_outputBytes, 0, i);
            this.count = 0;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.WriterChain
    public OutputStream getOutputStream() {
        return this.m_os;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.WriterChain
    public Writer getWriter() {
        return null;
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(String str) throws IOException {
        char c;
        int length = str.length();
        int i = length * 3;
        int i2 = 0;
        int i3 = 1;
        if (i >= 16384 - this.count) {
            flushBuffer();
            if (i > 16384) {
                int i4 = length / CHARS_MAX;
                if (length % CHARS_MAX > 0) {
                    i4++;
                }
                int i5 = 0;
                while (i3 <= i4) {
                    int i6 = (int) ((((long) length) * ((long) i3)) / ((long) i4));
                    str.getChars(i5, i6, this.m_inputChars, 0);
                    int i7 = i6 - i5;
                    char[] cArr = this.m_inputChars;
                    char c2 = cArr[i7 - 1];
                    if (c2 >= 55296 && c2 <= 56319) {
                        i6--;
                        i7--;
                    }
                    write(cArr, 0, i7);
                    i3++;
                    i5 = i6;
                }
                return;
            }
        }
        str.getChars(0, length, this.m_inputChars, 0);
        char[] cArr2 = this.m_inputChars;
        byte[] bArr = this.m_outputBytes;
        int i8 = this.count;
        while (i2 < length && (c = cArr2[i2]) < 128) {
            bArr[i8] = (byte) c;
            i2++;
            i8++;
        }
        while (i2 < length) {
            char c3 = cArr2[i2];
            if (c3 < 128) {
                bArr[i8] = (byte) c3;
                i8++;
            } else if (c3 < 2048) {
                int i9 = i8 + 1;
                bArr[i8] = (byte) ((c3 >> 6) + 192);
                i8 += 2;
                bArr[i9] = (byte) ((c3 & '?') + 128);
            } else if (c3 < 55296 || c3 > 56319) {
                bArr[i8] = (byte) ((c3 >> '\f') + WinError.ERROR_FORMS_AUTH_REQUIRED);
                int i10 = i8 + 2;
                bArr[i8 + 1] = (byte) (((c3 >> 6) & 63) + 128);
                i8 += 3;
                bArr[i10] = (byte) ((c3 & '?') + 128);
            } else {
                i2++;
                char c4 = cArr2[i2];
                bArr[i8] = -16;
                bArr[i8 + 1] = (byte) ((((c3 + '@') >> 2) & 63) | 128);
                int i11 = i8 + 3;
                bArr[i8 + 2] = (byte) ((((c4 >> 6) & 15) + ((c3 << 4) & 48)) | 128);
                i8 += 4;
                bArr[i11] = (byte) ((c4 & '?') | 128);
            }
            i2++;
        }
        this.count = i8;
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(char[] cArr, int i, int i2) throws IOException {
        char c;
        int i3 = i2 * 3;
        int i4 = 1;
        if (i3 >= 16384 - this.count) {
            flushBuffer();
            if (i3 > 16384) {
                int i5 = i2 / CHARS_MAX;
                if (i2 % CHARS_MAX > 0) {
                    i5++;
                }
                int i6 = i;
                while (i4 <= i5) {
                    int i7 = ((int) ((((long) i2) * ((long) i4)) / ((long) i5))) + i;
                    char c2 = cArr[i7 - 1];
                    if (c2 >= 55296 && c2 <= 56319) {
                        i7 = i7 < i + i2 ? i7 + 1 : i7 - 1;
                    }
                    write(cArr, i6, i7 - i6);
                    i4++;
                    i6 = i7;
                }
                return;
            }
        }
        int i8 = i2 + i;
        byte[] bArr = this.m_outputBytes;
        int i9 = this.count;
        while (i < i8 && (c = cArr[i]) < 128) {
            bArr[i9] = (byte) c;
            i++;
            i9++;
        }
        while (i < i8) {
            char c3 = cArr[i];
            if (c3 < 128) {
                bArr[i9] = (byte) c3;
                i9++;
            } else if (c3 < 2048) {
                int i10 = i9 + 1;
                bArr[i9] = (byte) ((c3 >> 6) + 192);
                i9 += 2;
                bArr[i10] = (byte) ((c3 & '?') + 128);
            } else if (c3 >= 55296 && c3 <= 56319) {
                i++;
                char c4 = cArr[i];
                bArr[i9] = -16;
                bArr[i9 + 1] = (byte) ((((c3 + '@') >> 2) & 63) | 128);
                int i11 = i9 + 3;
                bArr[i9 + 2] = (byte) ((((c4 >> 6) & 15) + ((c3 << 4) & 48)) | 128);
                i9 += 4;
                bArr[i11] = (byte) ((c4 & '?') | 128);
            } else {
                bArr[i9] = (byte) ((c3 >> '\f') + WinError.ERROR_FORMS_AUTH_REQUIRED);
                int i12 = i9 + 2;
                bArr[i9 + 1] = (byte) (((c3 >> 6) & 63) + 128);
                i9 += 3;
                bArr[i12] = (byte) ((c3 & '?') + 128);
            }
            i++;
        }
        this.count = i9;
    }

    @Override // java.io.Writer, com.sun.org.apache.xml.internal.serializer.WriterChain
    public void write(int i) throws IOException {
        if (this.count >= 16384) {
            flushBuffer();
        }
        if (i < 128) {
            byte[] bArr = this.m_outputBytes;
            int i2 = this.count;
            this.count = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        if (i < 2048) {
            byte[] bArr2 = this.m_outputBytes;
            int i3 = this.count;
            int i4 = i3 + 1;
            this.count = i4;
            bArr2[i3] = (byte) ((i >> 6) + 192);
            this.count = i3 + 2;
            bArr2[i4] = (byte) ((i & 63) + 128);
            return;
        }
        byte[] bArr3 = this.m_outputBytes;
        if (i < 65536) {
            int i5 = this.count;
            int i6 = i5 + 1;
            this.count = i6;
            bArr3[i5] = (byte) ((i >> 12) + WinError.ERROR_FORMS_AUTH_REQUIRED);
            int i7 = i5 + 2;
            this.count = i7;
            bArr3[i6] = (byte) (((i >> 6) & 63) + 128);
            this.count = i5 + 3;
            bArr3[i7] = (byte) ((i & 63) + 128);
            return;
        }
        int i8 = this.count;
        int i9 = i8 + 1;
        this.count = i9;
        bArr3[i8] = (byte) ((i >> 18) + 240);
        int i10 = i8 + 2;
        this.count = i10;
        bArr3[i9] = (byte) (((i >> 12) & 63) + 128);
        int i11 = i8 + 3;
        this.count = i11;
        bArr3[i10] = (byte) (((i >> 6) & 63) + 128);
        this.count = i8 + 4;
        bArr3[i11] = (byte) ((i & 63) + 128);
    }
}
