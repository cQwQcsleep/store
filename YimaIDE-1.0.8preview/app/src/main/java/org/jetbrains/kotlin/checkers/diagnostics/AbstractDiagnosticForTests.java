package org.jetbrains.kotlin.checkers.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.Severity;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/checkers/diagnostics/AbstractDiagnosticForTests;", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "psiElement", "Lcom/intellij/psi/PsiElement;", "factory", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;)V", "getPsiElement", "()Lcom/intellij/psi/PsiElement;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "textRanges", "", "Lcom/intellij/openapi/util/TextRange;", "getTextRanges", "()Ljava/util/List;", "psiFile", "Lcom/intellij/psi/PsiFile;", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "isValid", "", "()Z", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class AbstractDiagnosticForTests implements Diagnostic {
    private final DiagnosticFactory<?> factory;
    private final PsiElement psiElement;

    public AbstractDiagnosticForTests(PsiElement psiElement, DiagnosticFactory<?> diagnosticFactory) {
        psiElement.getClass();
        diagnosticFactory.getClass();
        this.psiElement = psiElement;
        this.factory = diagnosticFactory;
    }

    public DiagnosticFactory<?> getFactory() {
        return this.factory;
    }

    public PsiElement getPsiElement() {
        return this.psiElement;
    }

    public PsiFile getPsiFile() {
        PsiFile containingFile = getPsiElement().getContainingFile();
        containingFile.getClass();
        return containingFile;
    }

    public Severity getSeverity() {
        return Severity.ERROR;
    }

    public List<TextRange> getTextRanges() {
        return CollectionsKt.listOf(getPsiElement().getTextRange());
    }

    public boolean isValid() {
        return true;
    }
}
