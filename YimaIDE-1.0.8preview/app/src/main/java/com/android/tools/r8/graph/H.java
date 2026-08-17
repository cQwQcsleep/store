package com.android.tools.r8.graph;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H implements Diagnostic {
    public final Origin b;
    public final MethodPosition c;
    public final String d;

    public H(B5 b5, String str) {
        this.b = b5.b.d;
        this.c = new MethodPosition(b5.A());
        this.d = str;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final String getDiagnosticMessage() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Origin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Position getPosition() {
        return this.c;
    }
}
