package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1302dD extends StringConsumer.ForwardingConsumer {
    public final String b;
    public final StringConsumer c;
    public final StringBuilder d;

    public C1302dD(StringConsumer stringConsumer) {
        super(stringConsumer);
        this.d = new StringBuilder();
        this.c = stringConsumer;
        this.b = "\n";
    }

    public StringConsumer a() {
        return this.c;
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        if (this.d.length() > 0) {
            this.d.append(this.b);
        }
        this.d.append(str);
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        super.accept(this.d.toString(), diagnosticsHandler);
        super.finished(diagnosticsHandler);
    }
}
