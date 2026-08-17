package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.errors.CheckDiscardDiagnostic;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CheckDiscardDiagnostic implements Diagnostic {
    private final List b;

    private CheckDiscardDiagnostic(AbstractC0551Hu abstractC0551Hu) {
        this.b = abstractC0551Hu;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(StringBuilder sb, String str) {
        sb.append(System.lineSeparator());
        sb.append(str);
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        final StringBuilder sb = new StringBuilder("Discard checks failed.");
        if (this.b.size() > 0) {
            sb.append(System.lineSeparator());
            sb.append("The following items were not discarded");
            this.b.forEach(new Consumer() { // from class: fj1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CheckDiscardDiagnostic.a(sb, (String) obj);
                }
            });
        }
        return sb.toString();
    }

    public int getNumberOfFailures() {
        return this.b.size();
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
