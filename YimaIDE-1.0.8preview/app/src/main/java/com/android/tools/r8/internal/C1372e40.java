package com.android.tools.r8.internal;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1372e40 extends YI implements InterfaceC1270cr {
    public final /* synthetic */ byte[] c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1372e40(byte[] bArr) {
        super(0);
        this.c = bArr;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1270cr
    public final Object a() {
        byte[] bArr = this.c;
        Charset charset = StandardCharsets.UTF_8;
        KB.b(charset, "UTF_8");
        return new String(bArr, charset);
    }
}
