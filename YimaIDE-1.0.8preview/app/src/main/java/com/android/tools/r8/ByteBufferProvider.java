package com.android.tools.r8;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ByteBufferProvider {
    default ByteBuffer acquireByteBuffer(int i) {
        return ByteBuffer.allocate(i);
    }

    default void releaseByteBuffer(ByteBuffer byteBuffer) {
    }
}
