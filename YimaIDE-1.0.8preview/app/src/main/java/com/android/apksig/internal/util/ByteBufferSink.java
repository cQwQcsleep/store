package com.android.apksig.internal.util;

import com.android.apksig.util.DataSink;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ByteBufferSink implements DataSink {
    private final ByteBuffer mBuffer;

    public ByteBufferSink(ByteBuffer byteBuffer) {
        this.mBuffer = byteBuffer;
    }

    @Override // com.android.apksig.util.DataSink
    public void consume(ByteBuffer byteBuffer) throws IOException {
        int iRemaining = byteBuffer.remaining();
        try {
            this.mBuffer.put(byteBuffer);
        } catch (BufferOverflowException e) {
            throw new IOException("Insufficient space in output buffer for " + iRemaining + " bytes", e);
        }
    }

    public ByteBuffer getBuffer() {
        return this.mBuffer;
    }

    @Override // com.android.apksig.util.DataSink
    public void consume(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.mBuffer.put(bArr, i, i2);
        } catch (BufferOverflowException e) {
            throw new IOException("Insufficient space in output buffer for " + i2 + " bytes", e);
        }
    }
}
