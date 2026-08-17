package com.android.tools.r8.internal;

import defpackage.md6;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1602gl implements InterfaceC1517fl {
    public final BufferedReader a;

    public C1602gl(BufferedReader bufferedReader) {
        this.a = bufferedReader;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1517fl
    public final void a(String str) throws IOException {
        String strA = C1858jl.a(str);
        String line = this.a.readLine();
        if (line.equals(strA)) {
            return;
        }
        Charset charset = StandardCharsets.UTF_8;
        String str2 = new String(strA.getBytes(charset), charset);
        if (str2.equals(line)) {
            return;
        }
        md6.a("\nMismatch for line: ", str2, "\n    and dump-line: ", line);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
    }
}
