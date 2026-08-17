package com.sun.xml.internal.stream.writers;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class UTF8OutputStreamWriter extends Writer {
    int lastUTF16CodePoint = 0;
    OutputStream out;

    public UTF8OutputStreamWriter(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.lastUTF16CodePoint == 0) {
            this.out.close();
        } else {
            k2d.a("Attempting to close a UTF8OutputStreamWriter while awaiting for a UTF-16 code unit");
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    public String getEncoding() {
        return "UTF-8";
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        int i2 = this.lastUTF16CodePoint;
        if (i2 != 0) {
            int i3 = ((i & 1023) | ((i2 & 1023) << 10)) + 65536;
            if (i3 < 0 || i3 >= 2097152) {
                lc6.a("Atttempting to write invalid Unicode code point '", i3, "'");
                return;
            }
            this.out.write((i3 >> 18) | 240);
            this.out.write(((i3 >> 12) & 63) | 128);
            this.out.write(((i3 >> 6) & 63) | 128);
            this.out.write((i3 & 63) | 128);
            this.lastUTF16CodePoint = 0;
            return;
        }
        if (i < 128) {
            this.out.write(i);
            return;
        }
        if (i < 2048) {
            this.out.write((i >> 6) | 192);
            this.out.write((i & 63) | 128);
        } else if (i <= 65535) {
            if (XMLChar.isHighSurrogate(i) || XMLChar.isLowSurrogate(i)) {
                this.lastUTF16CodePoint = i;
                return;
            }
            this.out.write((i >> 12) | WinError.ERROR_FORMS_AUTH_REQUIRED);
            this.out.write(((i >> 6) & 63) | 128);
            this.out.write((i & 63) | 128);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        for (char c : cArr) {
            write(c);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            write(cArr[i + i3]);
        }
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            write(str.charAt(i));
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            write(str.charAt(i + i3));
        }
    }
}
