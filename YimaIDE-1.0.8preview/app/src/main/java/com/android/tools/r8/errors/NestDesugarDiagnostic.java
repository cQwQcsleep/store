package com.android.tools.r8.errors;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class NestDesugarDiagnostic implements DesugarDiagnostic {
    private final Origin b;
    private final Position c;
    private final String d;

    public NestDesugarDiagnostic(Origin origin, Position position, String str) {
        this.b = origin;
        this.c = position;
        this.d = str;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.c;
    }
}
