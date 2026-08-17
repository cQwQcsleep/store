package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xe0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3043xe0 {
    public static byte[] a(InputStream inputStream) throws IOException {
        byte[] bArrA = K7.a(inputStream);
        inputStream.close();
        return bArrA;
    }
}
