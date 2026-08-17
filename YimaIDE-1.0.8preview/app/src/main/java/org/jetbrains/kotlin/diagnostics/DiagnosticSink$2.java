package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import java.util.List;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DiagnosticSink$2 implements DiagnosticSink {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "diagnostic", "org/jetbrains/kotlin/diagnostics/DiagnosticSink$2", "report"));
    }

    public void report(Diagnostic diagnostic) {
        if (diagnostic == null) {
            $$$reportNull$$$0(0);
        }
        if (diagnostic.getSeverity() != Severity.ERROR) {
            return;
        }
        PsiFile psiFile = diagnostic.getPsiFile();
        List<TextRange> textRanges = diagnostic.getTextRanges();
        String strRender = DefaultErrorMessages.render(diagnostic);
        throw new IllegalStateException(diagnostic.getFactory().getName() + ": " + strRender + Argument.Delimiters.space + PsiDiagnosticUtils.atLocation(psiFile, textRanges.get(0)));
    }

    public boolean wantsDiagnostics() {
        return true;
    }
}
