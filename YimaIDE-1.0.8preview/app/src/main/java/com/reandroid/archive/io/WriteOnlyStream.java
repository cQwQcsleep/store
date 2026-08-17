package com.reandroid.archive.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface WriteOnlyStream extends RandomStream {
    OutputStream getOutputStream() throws IOException;

    void write(InputStream inputStream) throws IOException;
}
