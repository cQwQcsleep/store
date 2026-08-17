package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ParametrizedDiagnostic<E extends PsiElement> extends Diagnostic {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/diagnostics/ParametrizedDiagnostic", "getFactoryName"));
    }

    @Override // org.jetbrains.kotlin.diagnostics.Diagnostic, org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    default String getFactoryName() {
        String name = getFactory().getName();
        if (name == null) {
            $$$reportNull$$$0(0);
        }
        return name;
    }

    @Override // org.jetbrains.kotlin.diagnostics.Diagnostic, org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    E getPsiElement();
}
