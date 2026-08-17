package org.bouncycastle.est;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CTEChunkedInputStream extends InputStream {
    int chunkLen = 0;
    private InputStream src;

    public CTEChunkedInputStream(InputStream inputStream) {
        this.src = inputStream;
    }

    private String readEOL() throws IOException {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        do {
            i = this.src.read();
            if (i == -1) {
                if (byteArrayOutputStream.size() != 0) {
                    break;
                }
                return null;
            }
            byteArrayOutputStream.write(i & 255);
        } while (i != 10);
        return byteArrayOutputStream.toString().trim();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        String eol;
        int i = this.chunkLen;
        if (i == Integer.MIN_VALUE) {
            return -1;
        }
        if (i == 0) {
            do {
                eol = readEOL();
                if (eol == null) {
                    break;
                }
            } while (eol.length() == 0);
            if (eol == null) {
                return -1;
            }
            int i2 = Integer.parseInt(eol.trim(), 16);
            this.chunkLen = i2;
            if (i2 == 0) {
                readEOL();
                this.chunkLen = PKIFailureInfo.systemUnavail;
                return -1;
            }
        }
        int i3 = this.src.read();
        this.chunkLen--;
        return i3;
    }
}
