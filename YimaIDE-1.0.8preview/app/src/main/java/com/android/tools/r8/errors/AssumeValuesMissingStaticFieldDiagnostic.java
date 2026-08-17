package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AssumeValuesMissingStaticFieldDiagnostic implements Diagnostic {
    private final I2 b;
    private final H2 c;
    private final Origin d;
    private final Position e;

    private AssumeValuesMissingStaticFieldDiagnostic(I2 i2, H2 h2, Origin origin, Position position) {
        this.b = i2;
        this.c = h2;
        this.d = origin;
        this.e = position;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "The field " + this.b.H0() + "." + this.c + " is used as the return value in an -assumenosideeffects or -assumevalues rule, but no such static field exists.";
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.e;
    }
}
