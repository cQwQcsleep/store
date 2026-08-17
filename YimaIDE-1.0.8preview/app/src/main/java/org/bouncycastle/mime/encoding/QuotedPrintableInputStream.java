package org.bouncycastle.mime.encoding;

import defpackage.k2d;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class QuotedPrintableInputStream extends FilterInputStream {
    public QuotedPrintableInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i;
        int i2 = ((FilterInputStream) this).in.read();
        if (i2 == -1) {
            return -1;
        }
        while (i2 == 61) {
            int i3 = ((FilterInputStream) this).in.read();
            if (i3 == -1) {
                k2d.a("Quoted '=' at end of stream");
                return 0;
            }
            if (i3 == 13) {
                i2 = ((FilterInputStream) this).in.read();
                if (i2 == 10) {
                }
            } else if (i3 != 10) {
                if (i3 >= 48 && i3 <= 57) {
                    i = i3 - 48;
                } else {
                    if (i3 < 65 || i3 > 70) {
                        k2d.a("Expecting '0123456789ABCDEF after quote that was not immediately followed by LF or CRLF");
                        return 0;
                    }
                    i = i3 - 55;
                }
                int i4 = i << 4;
                int i5 = ((FilterInputStream) this).in.read();
                if (i5 >= 48 && i5 <= 57) {
                    return (i5 - 48) | i4;
                }
                if (i5 >= 65 && i5 <= 70) {
                    return (i5 - 55) | i4;
                }
                k2d.a("Expecting second '0123456789ABCDEF after quote that was not immediately followed by LF or CRLF");
                return 0;
            }
            i2 = ((FilterInputStream) this).in.read();
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 != i2) {
            int i4 = read();
            if (i4 < 0) {
                break;
            }
            bArr[i3 + i] = (byte) i4;
            i3++;
        }
        if (i3 == 0) {
            return -1;
        }
        return i3;
    }
}
