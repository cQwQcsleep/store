package com.android.tools.r8.retrace;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.Version;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceUnknownMapVersionDiagnostic implements Diagnostic {
    private final String b;

    private RetraceUnknownMapVersionDiagnostic(String str) {
        this.b = str;
    }

    public static RetraceUnknownMapVersionDiagnostic create(String str) {
        return new RetraceUnknownMapVersionDiagnostic(str);
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Map version '" + this.b + "' is unknown or introduced later than retrace version '" + Version.getVersionString() + "'.";
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
