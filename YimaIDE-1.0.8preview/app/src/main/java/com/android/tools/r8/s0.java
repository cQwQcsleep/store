package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class s0 extends StringConsumer.ForwardingConsumer {
    public final Origin b;
    public final Writer c;

    public s0(PathOrigin pathOrigin, Writer writer) {
        super(null);
        this.b = pathOrigin;
        this.c = writer;
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
    public final void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        super.accept(str, diagnosticsHandler);
        try {
            this.c.write(str);
        } catch (IOException e) {
            diagnosticsHandler.error(new ExceptionDiagnostic(e, this.b));
        }
    }

    @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.I
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
        super.finished(diagnosticsHandler);
        try {
            this.c.close();
        } catch (IOException e) {
            diagnosticsHandler.error(new ExceptionDiagnostic(e, this.b));
        }
    }
}
