package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DiagnosticFactory1<E extends PsiElement, A> extends DiagnosticFactoryWithPsiElement<E, DiagnosticWithParameters1<E, A>> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "element";
        } else {
            objArr[0] = "argument";
        }
        objArr[1] = "org/jetbrains/kotlin/diagnostics/DiagnosticFactory1";
        objArr[2] = "on";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public DiagnosticFactory1(Severity severity, PositioningStrategy<? super E> positioningStrategy) {
        super(severity, positioningStrategy);
    }

    public static <T extends PsiElement, A> DiagnosticFactory1<T, A> create(Severity severity) {
        return create(severity, PositioningStrategies.DEFAULT);
    }

    public ParametrizedDiagnostic<E> on(E e, A a) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        if (a == null) {
            $$$reportNull$$$0(1);
        }
        return new DiagnosticWithParameters1(e, a, this, getSeverity());
    }

    public static <T extends PsiElement, A> DiagnosticFactory1<T, A> create(Severity severity, PositioningStrategy<? super T> positioningStrategy) {
        return new DiagnosticFactory1<>(severity, positioningStrategy);
    }
}
