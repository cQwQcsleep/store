package com.android.tools.r8.utils;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class j implements StringConsumer {
    public StringBuilder a = null;
    public final /* synthetic */ r b;

    public j(r rVar) {
        this.b = rVar;
    }

    @Override // com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        if (this.a == null) {
            this.a = new StringBuilder();
        }
        this.a.append(str);
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        StringBuilder sb = this.a;
        if (sb != null) {
            this.b.a.a(sb.toString());
        }
    }
}
