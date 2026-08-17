package com.android.tools.r8.profile.art.diagnostic;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class HumanReadableArtProfileParserErrorDiagnostic implements Diagnostic {
    private final String b;
    private final int c;
    private final Origin d;

    public HumanReadableArtProfileParserErrorDiagnostic(String str, int i, Origin origin) {
        this.b = str;
        this.c = i;
        this.d = origin;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Unable to parse rule at line " + this.c + " from ART profile: " + this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
