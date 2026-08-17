package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l0 extends StringConsumer.ForwardingConsumer {
    public l0(StringConsumer stringConsumer) {
        super(stringConsumer);
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        super.accept(str, diagnosticsHandler);
        System.out.print(str);
    }
}
