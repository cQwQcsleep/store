package com.android.tools.r8.retrace;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.position.TextPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceInvalidStackTraceLineDiagnostics implements Diagnostic {
    private final int b;
    private final String c = "The stack trace line is <null>";

    private RetraceInvalidStackTraceLineDiagnostics(int i) {
        this.b = i;
    }

    public static RetraceInvalidStackTraceLineDiagnostics createNull(int i) {
        return new RetraceInvalidStackTraceLineDiagnostics(i);
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return new TextPosition(0L, this.b, -1);
    }
}
