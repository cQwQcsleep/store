package com.reandroid.archive;

import com.reandroid.common.BytesInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ByteInputSource extends InputSource {
    private byte[] array;

    public ByteInputSource(byte[] bArr, String str) {
        super(str);
        this.array = bArr;
    }

    @Override // com.reandroid.archive.InputSource
    public void disposeInputSource() {
        this.array = new byte[0];
    }

    public byte[] getBytes() {
        return this.array;
    }

    @Override // com.reandroid.archive.InputSource
    public InputStream openStream() throws IOException {
        return new BytesInputStream(getBytes());
    }

    @Override // com.reandroid.archive.InputSource
    public long write(OutputStream outputStream) throws IOException {
        byte[] bytes = getBytes();
        outputStream.write(bytes);
        return bytes.length;
    }
}
