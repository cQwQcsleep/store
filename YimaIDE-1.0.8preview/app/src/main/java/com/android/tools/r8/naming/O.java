package com.android.tools.r8.naming;

import java.io.BufferedReader;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O implements P {
    public final BufferedReader a;

    public O(BufferedReader bufferedReader) {
        this.a = bufferedReader;
    }

    @Override // com.android.tools.r8.naming.P
    public final String a() {
        return this.a.readLine();
    }

    @Override // com.android.tools.r8.naming.P
    public final void close() throws IOException {
        this.a.close();
    }
}
