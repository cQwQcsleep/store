package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.naming.C3313b;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CM implements com.android.tools.r8.naming.Q {
    public final /* synthetic */ com.android.tools.r8.naming.Q a;
    public final /* synthetic */ com.android.tools.r8.naming.Q b;

    public CM(com.android.tools.r8.naming.Q q, com.android.tools.r8.naming.Q q2) {
        this.a = q;
        this.b = q2;
    }

    @Override // com.android.tools.r8.naming.Q
    public final void a(DiagnosticsHandler diagnosticsHandler, C3313b c3313b) {
        this.a.a(diagnosticsHandler, c3313b);
        this.b.a(diagnosticsHandler, c3313b);
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.finished(diagnosticsHandler);
        this.b.finished(diagnosticsHandler);
    }
}
