package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DiagnosticFactory0<E extends PsiElement> extends DiagnosticFactoryWithPsiElement<E, SimpleDiagnostic<E>> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "org/jetbrains/kotlin/diagnostics/DiagnosticFactory0", "on"));
    }

    public DiagnosticFactory0(Severity severity, PositioningStrategy<? super E> positioningStrategy) {
        super(severity, positioningStrategy);
    }

    public static <T extends PsiElement> DiagnosticFactory0<T> create(Severity severity) {
        return create(severity, PositioningStrategies.DEFAULT);
    }

    public SimpleDiagnostic<E> on(E e) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        return new SimpleDiagnostic<>(e, this, getSeverity());
    }

    public static <T extends PsiElement> DiagnosticFactory0<T> create(Severity severity, PositioningStrategy<? super T> positioningStrategy) {
        return new DiagnosticFactory0<>(severity, positioningStrategy);
    }
}
