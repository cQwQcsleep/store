package com.android.tools.r8.errors;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.Y2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnusedProguardKeepRuleDiagnostic implements ProguardKeepRuleDiagnostic {
    private final Y2 b;

    public UnusedProguardKeepRuleDiagnostic(Y2 y2) {
        this.b = y2;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Proguard configuration rule does not match anything: `" + this.b + "`";
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.b.i();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.b.b;
    }
}
