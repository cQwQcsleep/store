package com.android.tools.r8;

import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface TextInputStream {
    Charset getCharset();

    InputStream getInputStream();
}
