package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnsupportedDesugaredLibraryConfigurationVersionDiagnostic implements Diagnostic {
    private final Origin b;

    public UnsupportedDesugaredLibraryConfigurationVersionDiagnostic(Origin origin) {
        this.b = origin;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return "Unsupported desugared library configuration version, please upgrade the D8/R8 compiler. See https://developer.android.com/studio/build/library-desugaring-versions. To learn more about library desugaring read https://developer.android.com/studio/build/library-desugaring.";
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
