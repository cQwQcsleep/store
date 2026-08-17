package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class SimpleDiagnostic<E extends PsiElement> extends AbstractDiagnostic<E> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "factory";
        } else if (i == 2) {
            objArr[0] = "severity";
        } else if (i != 3) {
            objArr[0] = "psiElement";
        } else {
            objArr[0] = "org/jetbrains/kotlin/diagnostics/SimpleDiagnostic";
        }
        if (i != 3) {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/SimpleDiagnostic";
        } else {
            objArr[1] = "getFactory";
        }
        if (i != 3) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleDiagnostic(E e, DiagnosticFactory0<E> diagnosticFactory0, Severity severity) {
        super(e, diagnosticFactory0, severity);
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        if (diagnosticFactory0 == null) {
            $$$reportNull$$$0(1);
        }
        if (severity == null) {
            $$$reportNull$$$0(2);
        }
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public DiagnosticFactory0<E> getFactory() {
        DiagnosticFactory0<E> diagnosticFactory0 = (DiagnosticFactory0) super.getFactory();
        if (diagnosticFactory0 == null) {
            $$$reportNull$$$0(3);
        }
        return diagnosticFactory0;
    }
}
