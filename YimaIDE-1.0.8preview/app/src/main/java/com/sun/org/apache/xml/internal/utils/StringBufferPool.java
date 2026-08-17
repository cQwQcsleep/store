package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringBufferPool {
    private static ObjectPool m_stringBufPool = new ObjectPool((Class<?>) FastStringBuffer.class);

    public static synchronized void free(FastStringBuffer fastStringBuffer) {
        fastStringBuffer.setLength(0);
        m_stringBufPool.freeInstance(fastStringBuffer);
    }

    public static synchronized FastStringBuffer get() {
        return (FastStringBuffer) m_stringBufPool.getInstance();
    }
}
