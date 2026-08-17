package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CheckEnumUnboxedDiagnostic implements Diagnostic {
    private final List b;

    public CheckEnumUnboxedDiagnostic(AbstractC0551Hu abstractC0551Hu) {
        this.b = abstractC0551Hu;
    }

    public static d builder() {
        return new d();
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder("Enum unboxing checks failed.");
        for (String str : this.b) {
            sb.append(System.lineSeparator());
            sb.append(str);
        }
        return sb.toString();
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
