package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Wc0 extends StringConsumer.ForwardingConsumer {
    public final ArrayList b;

    public Wc0(com.android.tools.r8.J j) {
        super(j);
        this.b = new ArrayList();
    }

    public final /* synthetic */ void a(DiagnosticsHandler diagnosticsHandler, String str) {
        super.accept(str, diagnosticsHandler);
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        this.b.add(str);
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.I
    public final void finished(final DiagnosticsHandler diagnosticsHandler) {
        Collections.sort(this.b);
        this.b.forEach(new Consumer() { // from class: vkf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(diagnosticsHandler, (String) obj);
            }
        });
        super.finished(diagnosticsHandler);
    }
}
