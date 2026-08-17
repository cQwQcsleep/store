package com.android.tools.r8.internal;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1556gB {
    public static final Charset a = Charset.forName("US-ASCII");
    public static final Charset b = Charset.forName("UTF-8");
    public static final Charset c = Charset.forName("ISO-8859-1");
    public static final byte[] d;

    static {
        byte[] bArr = new byte[0];
        d = bArr;
        ByteBuffer.wrap(bArr);
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    public static String a(byte[] bArr) {
        return new String(bArr, b);
    }
}
