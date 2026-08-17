package com.intellij.util.io.pagecache.impl;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface IMemoryManager {
    boolean hasOverflow();

    long heapBytesUsed();

    long nativeBytesUsed();

    long nativeCapacityBytes();

    void releaseBuffer(int i, ByteBuffer byteBuffer);

    default long totalMemoryUsed() {
        return nativeBytesUsed() + heapBytesUsed();
    }
}
