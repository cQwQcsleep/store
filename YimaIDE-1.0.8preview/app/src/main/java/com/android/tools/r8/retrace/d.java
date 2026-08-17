package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.I;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.internal.InterfaceC1326db;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class d implements InterfaceC1326db, I {
    public final StringConsumer a;
    public final DiagnosticsHandler b;

    public d(StringConsumer stringConsumer, DiagnosticsHandler diagnosticsHandler) {
        this.a = stringConsumer;
        this.b = diagnosticsHandler;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1326db
    public final InterfaceC1326db a(String str) {
        this.a.accept(str, this.b);
        return this;
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.finished(diagnosticsHandler);
    }
}
