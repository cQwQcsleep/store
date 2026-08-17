package com.android.tools.r8.androidapi;

import com.android.tools.r8.graph.F2;
import com.android.tools.r8.internal.AbstractC2820v2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AndroidApiUnknownReferenceDiagnostic extends AbstractC2820v2 {
    private final F2 b;

    public AndroidApiUnknownReferenceDiagnostic(F2 f2) {
        this.b = f2;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return this.b.m0() + " cannot be found in the api database.";
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
