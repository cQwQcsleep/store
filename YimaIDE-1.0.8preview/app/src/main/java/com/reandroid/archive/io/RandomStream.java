package com.reandroid.archive.io;

import java.io.IOException;
import java.nio.channels.Channel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface RandomStream extends Channel {
    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    @Override // java.nio.channels.Channel
    boolean isOpen();

    long position() throws IOException;

    void position(long j) throws IOException;
}
