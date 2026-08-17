package com.android.tools.r8;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J extends StringConsumer.ForwardingConsumer {
    public final /* synthetic */ List b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(StringConsumer stringConsumer, List list) {
        super(stringConsumer);
        this.b = list;
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        this.b.add(str);
        super.accept(str, diagnosticsHandler);
    }
}
