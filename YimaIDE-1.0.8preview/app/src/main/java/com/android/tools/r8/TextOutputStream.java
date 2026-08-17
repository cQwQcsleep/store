package com.android.tools.r8;

import java.io.OutputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface TextOutputStream {
    Charset getCharset();

    OutputStream getOutputStream();
}
