package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ClassFileOverflowDiagnostic implements Diagnostic {
    private final Origin b;

    public ClassFileOverflowDiagnostic(Origin origin) {
        this.b = origin;
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
