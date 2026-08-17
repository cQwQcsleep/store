package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnsupportedMainDexListUsageDiagnostic implements Diagnostic {
    private final Origin b;

    public UnsupportedMainDexListUsageDiagnostic(Origin origin) {
        this.b = origin;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Unsupported usage of main-dex list. The usage of main-dex-list content for the compilation of non-DEX inputs is deprecated. See issue https://issuetracker.google.com/181858113 for context.";
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
