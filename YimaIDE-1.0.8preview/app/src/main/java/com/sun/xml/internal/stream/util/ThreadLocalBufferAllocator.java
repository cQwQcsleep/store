package com.sun.xml.internal.stream.util;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ThreadLocalBufferAllocator {
    private static final ThreadLocal<SoftReference<BufferAllocator>> TL = new ThreadLocal<>();

    public static BufferAllocator getBufferAllocator() {
        ThreadLocal<SoftReference<BufferAllocator>> threadLocal = TL;
        SoftReference<BufferAllocator> softReference = threadLocal.get();
        BufferAllocator bufferAllocator = softReference != null ? softReference.get() : null;
        if (bufferAllocator != null) {
            return bufferAllocator;
        }
        BufferAllocator bufferAllocator2 = new BufferAllocator();
        threadLocal.set(new SoftReference<>(bufferAllocator2));
        return bufferAllocator2;
    }
}
