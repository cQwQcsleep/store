package com.android.tools.r8.ir.desugar.desugaredlibrary.lint;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class c implements StringConsumer {
    @Override // com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        System.out.println(str);
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }
}
