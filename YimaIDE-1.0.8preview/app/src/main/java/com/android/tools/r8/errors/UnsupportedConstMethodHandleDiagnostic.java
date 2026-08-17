package com.android.tools.r8.errors;

import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnsupportedConstMethodHandleDiagnostic extends UnsupportedFeatureDiagnostic {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedConstMethodHandleDiagnostic(Origin origin, Position position) {
        super("const-method-handle", EnumC3077y2.D, origin, position);
        boolean z = C2752uB.V1;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        boolean z = C2752uB.V1;
        return UnsupportedFeatureDiagnostic.makeMessage(EnumC3077y2.D, "const-method-handle", getPosition().toString());
    }
}
