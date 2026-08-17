package com.android.tools.r8.graph;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.lang.reflect.GenericSignatureFormatError;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N3 implements Diagnostic {
    public final Origin b;
    public final Position c;
    public final String d;

    public N3(Origin origin, Position position, String str) {
        this.b = origin;
        this.c = position;
        this.d = str;
    }

    public static N3 a(String str, String str2, String str3, Origin origin, GenericSignatureFormatError genericSignatureFormatError) {
        return new N3(origin, Position.UNKNOWN, "Invalid signature '" + str + "' for " + str2 + " " + str3 + "." + System.lineSeparator() + "Signature is ignored and will not be present in the output." + System.lineSeparator() + "Parser error: " + genericSignatureFormatError.getMessage());
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
