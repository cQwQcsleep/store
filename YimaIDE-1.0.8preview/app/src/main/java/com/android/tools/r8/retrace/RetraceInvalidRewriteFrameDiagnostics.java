package com.android.tools.r8.retrace;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceInvalidRewriteFrameDiagnostics implements Diagnostic {
    private final int b;
    private final String c;

    private RetraceInvalidRewriteFrameDiagnostics(int i, String str) {
        this.b = i;
        this.c = str;
    }

    public static RetraceInvalidRewriteFrameDiagnostics create(int i, String str) {
        return new RetraceInvalidRewriteFrameDiagnostics(i, str);
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Cannot remove " + this.b + " frames from the retraced output of " + this.c + " because it exceeds the number of retraced frames";
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
