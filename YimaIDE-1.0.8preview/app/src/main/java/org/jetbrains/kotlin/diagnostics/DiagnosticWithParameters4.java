package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import java.util.Objects;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DiagnosticWithParameters4<E extends PsiElement, A, B, C, D> extends AbstractDiagnostic<E> implements DiagnosticWithParameters4Marker<A, B, C, D> {
    private final A a;
    private final B b;
    private final C c;
    private final D d;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
                objArr[0] = "a";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                objArr[0] = "b";
                break;
            case 3:
                objArr[0] = "c";
                break;
            case 4:
                objArr[0] = "d";
                break;
            case 5:
                objArr[0] = "factory";
                break;
            case 6:
                objArr[0] = "severity";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[0] = "org/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4";
                break;
            default:
                objArr[0] = "psiElement";
                break;
        }
        switch (i) {
            case 7:
                objArr[1] = "getFactory";
                break;
            case 8:
                objArr[1] = "getA";
                break;
            case 9:
                objArr[1] = "getB";
                break;
            case 10:
                objArr[1] = "getC";
                break;
            case 11:
                objArr[1] = "getD";
                break;
            default:
                objArr[1] = "org/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4";
                break;
        }
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticWithParameters4(E e, A a, B b, C c, D d, DiagnosticFactory4<E, A, B, C, D> diagnosticFactory4, Severity severity) {
        super(e, diagnosticFactory4, severity);
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        if (a == null) {
            $$$reportNull$$$0(1);
        }
        if (b == null) {
            $$$reportNull$$$0(2);
        }
        if (c == null) {
            $$$reportNull$$$0(3);
        }
        if (d == null) {
            $$$reportNull$$$0(4);
        }
        if (diagnosticFactory4 == null) {
            $$$reportNull$$$0(5);
        }
        if (severity == null) {
            $$$reportNull$$$0(6);
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        DiagnosticWithParameters4 diagnosticWithParameters4 = (DiagnosticWithParameters4) obj;
        return Objects.equals(this.a, diagnosticWithParameters4.a) && Objects.equals(this.b, diagnosticWithParameters4.b) && Objects.equals(this.c, diagnosticWithParameters4.c) && Objects.equals(this.d, diagnosticWithParameters4.d);
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public A getA() {
        A a = this.a;
        if (a == null) {
            $$$reportNull$$$0(8);
        }
        return a;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public B getB() {
        B b = this.b;
        if (b == null) {
            $$$reportNull$$$0(9);
        }
        return b;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public C getC() {
        C c = this.c;
        if (c == null) {
            $$$reportNull$$$0(10);
        }
        return c;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public D getD() {
        D d = this.d;
        if (d == null) {
            $$$reportNull$$$0(11);
        }
        return d;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public DiagnosticFactory4<E, A, B, C, D> getFactory() {
        DiagnosticFactory4<E, A, B, C, D> diagnosticFactory4 = (DiagnosticFactory4) super.getFactory();
        if (diagnosticFactory4 == null) {
            $$$reportNull$$$0(7);
        }
        return diagnosticFactory4;
    }

    @Override // org.jetbrains.kotlin.diagnostics.AbstractDiagnostic
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.a, this.b, this.c, this.d);
    }

    public String toString() {
        return getFactory() + "(a = " + this.a + ", b = " + this.b + ", c = " + this.c + ", d = " + this.d + ")";
    }
}
