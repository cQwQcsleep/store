package com.android.tools.r8;

import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0163e extends StringConsumer.FileConsumer {
    public C0163e(Path path) {
        super(path);
    }

    @Override // com.android.tools.r8.StringConsumer.FileConsumer, com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        super.accept(str, diagnosticsHandler);
        super.accept(System.lineSeparator(), diagnosticsHandler);
    }
}
