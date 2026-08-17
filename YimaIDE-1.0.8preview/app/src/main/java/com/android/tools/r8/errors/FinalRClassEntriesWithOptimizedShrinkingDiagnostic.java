package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FinalRClassEntriesWithOptimizedShrinkingDiagnostic implements Diagnostic {
    private final Origin b;
    private final C0245l1 c;

    public FinalRClassEntriesWithOptimizedShrinkingDiagnostic(Origin origin, C0245l1 c0245l1) {
        this.b = origin;
        this.c = c0245l1;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Running optimized resource shrinking with final R class ids is not supported and can lead to missing resources and code necessary for program execution. Field " + this.c + " is final";
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
