package com.reandroid.archive.io;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface ReadOnlyStream extends RandomStream {
    InputStream getInputStream(long j, long j2) throws IOException;

    long getLength() throws IOException;
}
