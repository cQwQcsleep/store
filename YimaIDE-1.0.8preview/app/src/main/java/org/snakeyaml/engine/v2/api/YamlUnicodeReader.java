package org.snakeyaml.engine.v2.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class YamlUnicodeReader extends Reader {
    private static final int BOM_SIZE = 4;
    PushbackInputStream internalIn;
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final Charset UTF16BE = StandardCharsets.UTF_16BE;
    private static final Charset UTF16LE = StandardCharsets.UTF_16LE;
    private static final Charset UTF32BE = Charset.forName("UTF-32BE");
    private static final Charset UTF32LE = Charset.forName("UTF-32LE");
    InputStreamReader internalIn2 = null;
    Charset encoding = UTF8;

    public YamlUnicodeReader(InputStream inputStream) {
        this.internalIn = new PushbackInputStream(inputStream, 4);
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        init();
        this.internalIn2.close();
    }

    public Charset getEncoding() {
        return this.encoding;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0074  */
    public void init() throws IOException {
        int i;
        if (this.internalIn2 != null) {
            return;
        }
        byte[] bArr = new byte[4];
        int i2 = this.internalIn.read(bArr, 0, 4);
        byte b = bArr[0];
        if (b != 0 || bArr[1] != 0 || bArr[2] != -2 || bArr[3] != -1) {
            if (b == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0) {
                this.encoding = UTF32LE;
            } else if (b == -17 && bArr[1] == -69 && bArr[2] == -65) {
                this.encoding = UTF8;
                i = i2 - 3;
            } else {
                if (b == -2 && bArr[1] == -1) {
                    this.encoding = UTF16BE;
                } else if (b == -1 && bArr[1] == -2) {
                    this.encoding = UTF16LE;
                } else {
                    this.encoding = UTF8;
                    i = i2;
                }
                i = i2 - 2;
            }
            if (i > 0) {
                this.internalIn.unread(bArr, i2 - i, i);
            }
            this.internalIn2 = new InputStreamReader(this.internalIn, this.encoding.newDecoder().onUnmappableCharacter(CodingErrorAction.REPORT));
        }
        this.encoding = UTF32BE;
        i = i2 - 4;
        if (i > 0) {
            this.internalIn.unread(bArr, i2 - i, i);
        }
        this.internalIn2 = new InputStreamReader(this.internalIn, this.encoding.newDecoder().onUnmappableCharacter(CodingErrorAction.REPORT));
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        init();
        return this.internalIn2.read(cArr, i, i2);
    }
}
