package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import org.jetbrains.kotlin.diagnostics.Diagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public abstract class DiagnosticFactoryWithPsiElement<E extends PsiElement, D extends Diagnostic> extends DiagnosticFactory<D> {
    protected final PositioningStrategy<? super E> positioningStrategy;

    public DiagnosticFactoryWithPsiElement(Severity severity, PositioningStrategy<? super E> positioningStrategy) {
        super(severity);
        this.positioningStrategy = positioningStrategy;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use `cast` from the superclass.")
    @Deprecated
    public D cast(Diagnostic diagnostic) {
        return (D) super.cast((UnboundDiagnostic) diagnostic);
    }

    public PositioningStrategy<? super E> getPositioningStrategy() {
        return this.positioningStrategy;
    }

    public List<TextRange> getTextRanges(ParametrizedDiagnostic<E> parametrizedDiagnostic) {
        return this.positioningStrategy.markDiagnostic(parametrizedDiagnostic);
    }

    public boolean isValid(ParametrizedDiagnostic<E> parametrizedDiagnostic) {
        return this.positioningStrategy.isValid(parametrizedDiagnostic.getPsiElement());
    }
}
