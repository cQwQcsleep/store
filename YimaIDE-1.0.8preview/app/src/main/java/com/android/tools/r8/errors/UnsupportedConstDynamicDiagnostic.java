package com.android.tools.r8.errors;

import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnsupportedConstDynamicDiagnostic extends UnsupportedFeatureDiagnostic {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedConstDynamicDiagnostic(Origin origin, Position position) {
        super("const-dynamic", null, origin, position);
        boolean z = C2752uB.V1;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        boolean z = C2752uB.V1;
        return UnsupportedFeatureDiagnostic.makeMessage(null, "const-dynamic", getPosition().toString());
    }
}
