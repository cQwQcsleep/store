package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import java.util.Objects;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DiagnosticWithParameters2<E extends PsiElement, A, B> extends AbstractDiagnostic<E> implements DiagnosticWithParameters2Marker<A, B> {
    private final A a;
    private final B b;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "a";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                objArr[0] = "b";
                break;
            case 3:
                objArr[0] = "factory";
                break;
            case 4:
                objArr[0] = "severity";
                break;
            case 5:
            case 6:
            case 7:
                objArr[0] = "org/jetbrains/kotlin/diagnostics/DiagnosticWithParameters2";
                break;
            default:
                objArr[0] = "psiElement";
                break;
        }
        if (i == 5) {
            objArr[1] = "getFactory";
        } else if (i == 6) {
            objArr[1] = "getA";
        } else if (i != 7) {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/DiagnosticWithParameters2";
        } else {
            objArr[1] = "getB";
        }
        if (i != 5 && i != 6 && i != 7) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticWithParameters2(E e, A a, B b, DiagnosticFactory2<E, A, B> diagnosticFactory2, Severity severity) {
        super(e, diagnosticFactory2, severity);
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        if (a == null) {
            $$$reportNull$$$0(1);
        }
        if (b == null) {
            $$$reportNull$$$0(2);
        }
        if (diagnosticFactory2 == null) {
            $$$reportNull$$$0(3);
        }
        if (severity == null) {
            $$$reportNull$$$0(4);
        }
        this.a = a;
        this.b = b;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        DiagnosticWithParameters2 diagnosticWithParameters2 = (DiagnosticWithParameters2) obj;
        return Objects.equals(this.a, diagnosticWithParameters2.a) && Objects.equals(this.b, diagnosticWithParameters2.b);
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2Marker
    public A getA() {
        A a = this.a;
        if (a == null) {
            $$$reportNull$$$0(6);
        }
        return a;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2Marker
    public B getB() {
        B b = this.b;
        if (b == null) {
            $$$reportNull$$$0(7);
        }
        return b;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public DiagnosticFactory2<E, A, B> getFactory() {
        DiagnosticFactory2<E, A, B> diagnosticFactory2 = (DiagnosticFactory2) super.getFactory();
        if (diagnosticFactory2 == null) {
            $$$reportNull$$$0(5);
        }
        return diagnosticFactory2;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.a, this.b);
    }

    public String toString() {
        return getFactory() + "(a = " + this.a + ", b = " + this.b + ")";
    }
}
