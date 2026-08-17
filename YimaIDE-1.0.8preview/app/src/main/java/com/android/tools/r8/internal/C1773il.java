package com.android.tools.r8.internal;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.il, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1773il implements InterfaceC1517fl {
    public final Writer a;

    public C1773il(BufferedWriter bufferedWriter) {
        this.a = bufferedWriter;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1517fl
    public final void a(String str) throws IOException {
        this.a.write(str.replace("\r", "<CR>"));
        this.a.write(10);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
    }
}
