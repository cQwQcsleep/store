package com.android.tools.r8;

import com.android.tools.r8.errors.DexFileOverflowDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;

/* JADX INFO: renamed from: com.android.tools.r8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3359o implements DiagnosticsHandler {
    @Override // com.android.tools.r8.DiagnosticsHandler
    public final void error(Diagnostic diagnostic) {
        if (diagnostic instanceof DexFileOverflowDiagnostic) {
            DexFileOverflowDiagnostic dexFileOverflowDiagnostic = (DexFileOverflowDiagnostic) diagnostic;
            if (!dexFileOverflowDiagnostic.hasMainDexSpecification()) {
                super.error(new StringDiagnostic(dexFileOverflowDiagnostic.getDiagnosticMessage() + ". Try supplying a main-dex list"));
                return;
            }
        }
        super.error(diagnostic);
    }
}
