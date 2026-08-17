package org.bouncycastle.est;

import defpackage.a16;
import defpackage.r8g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.Base64;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class CTEBase64InputStream extends InputStream {
    protected final byte[] data;
    protected final OutputStream dataOutputStream;
    protected boolean end;
    protected final Long max;
    protected final byte[] rawBuf;
    protected long read;
    protected int rp;
    protected final InputStream src;
    protected int wp;

    public CTEBase64InputStream(InputStream inputStream, Long l) {
        this.rawBuf = new byte[1024];
        this.data = new byte[768];
        this.src = inputStream;
        this.dataOutputStream = new OutputStream() { // from class: org.bouncycastle.est.CTEBase64InputStream.1
            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                CTEBase64InputStream cTEBase64InputStream = CTEBase64InputStream.this;
                byte[] bArr = cTEBase64InputStream.data;
                int i2 = cTEBase64InputStream.wp;
                cTEBase64InputStream.wp = i2 + 1;
                bArr[i2] = (byte) i;
            }
        };
        this.max = l;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.src.close();
    }

    public int pullFromSrc() throws IOException {
        int i;
        int i2 = 0;
        do {
            Long l = this.max;
            if (l != null && this.read > l.longValue()) {
                return -1;
            }
            i = this.src.read();
            if (i >= 33 || i == 13 || i == 10) {
                byte[] bArr = this.rawBuf;
                if (i2 >= bArr.length) {
                    a16.a("Content Transfer Encoding, base64 line length > 1024");
                    return 0;
                }
                bArr[i2] = (byte) i;
                this.read++;
                i2++;
            } else if (i >= 0) {
                this.read++;
            }
            if (i <= -1 || i2 >= this.rawBuf.length) {
                break;
            }
        } while (i != 10);
        if (i2 > 0) {
            try {
                Base64.decode(this.rawBuf, 0, i2, this.dataOutputStream);
            } catch (Exception e) {
                r8g.a("Decode Base64 Content-Transfer-Encoding: ", e);
                return 0;
            }
        } else if (i == -1) {
            return -1;
        }
        return this.wp;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.rp == this.wp) {
            this.rp = 0;
            this.wp = 0;
            int iPullFromSrc = pullFromSrc();
            if (iPullFromSrc == -1) {
                return iPullFromSrc;
            }
        }
        byte[] bArr = this.data;
        int i = this.rp;
        this.rp = i + 1;
        return bArr[i] & 255;
    }

    public CTEBase64InputStream(InputStream inputStream) {
        this(inputStream, null);
    }
}
