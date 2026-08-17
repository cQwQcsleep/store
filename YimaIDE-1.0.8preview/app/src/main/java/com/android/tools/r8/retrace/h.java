package com.android.tools.r8.retrace;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class h implements DiagnosticsHandler {
    public final DiagnosticsHandler a;
    public final boolean b;

    public h(f fVar, boolean z) {
        this.a = fVar;
        this.b = z;
    }

    @Override // com.android.tools.r8.DiagnosticsHandler
    public final void error(Diagnostic diagnostic) {
        this.a.error(diagnostic);
    }

    @Override // com.android.tools.r8.DiagnosticsHandler
    public final void info(Diagnostic diagnostic) {
        if (this.b) {
            this.a.info(diagnostic);
        }
    }

    @Override // com.android.tools.r8.DiagnosticsHandler
    public final void warning(Diagnostic diagnostic) {
        this.a.warning(diagnostic);
    }
}
