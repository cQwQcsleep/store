package com.android.tools.r8;

import com.android.tools.r8.errors.DexFileOverflowDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N implements DiagnosticsHandler {
    @Override // com.android.tools.r8.DiagnosticsHandler
    public final void error(Diagnostic diagnostic) {
        if (!(diagnostic instanceof DexFileOverflowDiagnostic)) {
            super.error(diagnostic);
            return;
        }
        super.error(new StringDiagnostic(((DexFileOverflowDiagnostic) diagnostic).getDiagnosticMessage() + ". Library too large. GlobalSyntheticsGenerator can only produce a single .dex file"));
    }
}
