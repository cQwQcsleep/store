package com.android.tools.r8.internal;

import com.android.tools.r8.errors.DesugarDiagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1060aP implements DesugarDiagnostic {
    public final String b;

    public C1060aP(String str) {
        this.b = str;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final String getDiagnosticMessage() {
        return "Invalid build configuration. Attempt to create a global synthetic for '" + this.b + "' without a global-synthetics consumer.";
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Position getPosition() {
        return Position.UNKNOWN;
    }
}
