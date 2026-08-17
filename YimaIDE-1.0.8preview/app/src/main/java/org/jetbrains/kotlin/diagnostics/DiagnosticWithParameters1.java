package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DiagnosticWithParameters1<E extends PsiElement, A> extends AbstractDiagnostic<E> implements DiagnosticWithParameters1Marker<A> {
    private final A a;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 5) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "a";
        } else if (i == 2) {
            objArr[0] = "factory";
        } else if (i == 3) {
            objArr[0] = "severity";
        } else if (i == 4 || i == 5) {
            objArr[0] = "org/jetbrains/kotlin/diagnostics/DiagnosticWithParameters1";
        } else {
            objArr[0] = "psiElement";
        }
        if (i == 4) {
            objArr[1] = "getFactory";
        } else if (i != 5) {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/DiagnosticWithParameters1";
        } else {
            objArr[1] = "getA";
        }
        if (i != 4 && i != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticWithParameters1(E e, A a, DiagnosticFactory1<E, A> diagnosticFactory1, Severity severity) {
        super(e, diagnosticFactory1, severity);
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        if (a == null) {
            $$$reportNull$$$0(1);
        }
        if (diagnosticFactory1 == null) {
            $$$reportNull$$$0(2);
        }
        if (severity == null) {
            $$$reportNull$$$0(3);
        }
        this.a = a;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
            return Objects.equals(this.a, ((DiagnosticWithParameters1) obj).a);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1Marker
    public A getA() {
        A a = this.a;
        if (a == null) {
            $$$reportNull$$$0(5);
        }
        return a;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public DiagnosticFactory1<E, A> getFactory() {
        DiagnosticFactory1<E, A> diagnosticFactory1 = (DiagnosticFactory1) super.getFactory();
        if (diagnosticFactory1 == null) {
            $$$reportNull$$$0(4);
        }
        return diagnosticFactory1;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.a);
    }

    public String toString() {
        return getFactory() + "(a = " + this.a + ")";
    }
}
