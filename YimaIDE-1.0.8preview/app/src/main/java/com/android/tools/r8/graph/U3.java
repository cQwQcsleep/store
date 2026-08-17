package com.android.tools.r8.graph;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U3 implements Diagnostic {
    public final Origin b;
    public final Position c;
    public final String d;

    public U3(Origin origin, Position position, String str) {
        this.b = origin;
        this.c = position;
        this.d = str;
    }

    public static U3 a(String str, String str2, String str3, Origin origin, L3.a aVar) {
        return new U3(origin, Position.UNKNOWN, "Invalid signature '" + str + "' for " + str2 + " " + str3 + "." + System.lineSeparator() + "Validation error: " + aVar.a() + "." + System.lineSeparator() + "Signature is ignored and will not be present in the output.");
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
