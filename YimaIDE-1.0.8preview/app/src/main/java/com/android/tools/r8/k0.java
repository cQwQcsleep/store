package com.android.tools.r8;

import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Wf0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k0 implements StringConsumer {
    public final C2752uB a;
    public StringBuilder b = new StringBuilder();

    public k0(C2752uB c2752uB) {
        this.a = c2752uB;
    }

    @Override // com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        this.b.append(str);
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.f = Wf0.f(this.b.toString());
        this.b = null;
    }
}
