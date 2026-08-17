package com.android.tools.r8.internal;

import java.nio.charset.Charset;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3120yb {
    public static final Charset a;

    static {
        Charset.forName("US-ASCII");
        Charset.forName("ISO-8859-1");
        a = Charset.forName("UTF-8");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
        Charset.forName("UTF-16");
    }
}
