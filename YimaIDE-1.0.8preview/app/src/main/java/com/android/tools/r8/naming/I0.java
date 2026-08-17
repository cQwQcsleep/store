package com.android.tools.r8.naming;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.internal.InterfaceC1326db;
import com.android.tools.r8.internal.Wf0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class I0 implements Q, InterfaceC1326db {
    public static final /* synthetic */ boolean c = true;
    public final StringConsumer a;
    public DiagnosticsHandler b;

    public I0(StringConsumer stringConsumer) {
        if (c || stringConsumer != null) {
            this.a = stringConsumer;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1326db
    public final InterfaceC1326db a(String str) {
        if (c || this.b != null) {
            this.a.accept(str, this.b);
            return this;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        this.a.finished(diagnosticsHandler);
    }

    @Override // com.android.tools.r8.naming.Q
    public final void a(DiagnosticsHandler diagnosticsHandler, C3313b c3313b) {
        this.b = diagnosticsHandler;
        a(Wf0.a("\n", c3313b.f));
        c3313b.a(this);
    }

    public StringConsumer a() {
        return this.a;
    }
}
