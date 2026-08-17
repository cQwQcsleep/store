package org.codehaus.stax2.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Stax2ByteArraySource extends Stax2BlockSource {
    private static final String DEFAULT_ENCODING = "UTF-8";
    final byte[] mBuffer;
    final int mLength;
    final int mStart;

    public Stax2ByteArraySource(byte[] bArr, int i, int i2) {
        this.mBuffer = bArr;
        this.mStart = i;
        this.mLength = i2;
    }

    @Override // org.codehaus.stax2.io.Stax2BlockSource, org.codehaus.stax2.io.Stax2Source
    public InputStream constructInputStream() throws IOException {
        return new ByteArrayInputStream(this.mBuffer, this.mStart, this.mLength);
    }

    @Override // org.codehaus.stax2.io.Stax2BlockSource, org.codehaus.stax2.io.Stax2Source
    public Reader constructReader() throws IOException {
        String encoding = getEncoding();
        InputStream inputStreamConstructInputStream = constructInputStream();
        if (encoding == null || encoding.length() == 0) {
            encoding = "UTF-8";
        }
        return new InputStreamReader(inputStreamConstructInputStream, encoding);
    }

    public byte[] getBuffer() {
        return this.mBuffer;
    }

    public int getBufferEnd() {
        int i = this.mStart;
        int i2 = this.mLength;
        return i2 > 0 ? i + i2 : i;
    }

    public int getBufferLength() {
        return this.mLength;
    }

    public int getBufferStart() {
        return this.mStart;
    }
}
