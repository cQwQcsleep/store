package com.android.tools.r8.internal;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E implements InterfaceC0990Ys {
    public abstract InterfaceC0990Ys a(int i, byte[] bArr);

    public final InterfaceC0990Ys a(String str, Charset charset) {
        return a(str.toString().getBytes(charset));
    }

    @Override // com.android.tools.r8.internal.InterfaceC0990Ys
    public InterfaceC0990Ys a(byte[] bArr) {
        return a(bArr.length, bArr);
    }
}
