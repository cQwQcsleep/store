package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.List;
import java.util.Objects;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public abstract class AbstractDiagnostic<E extends PsiElement> implements ParametrizedDiagnostic<E> {
    private final DiagnosticFactoryWithPsiElement<E, ?> factory;
    private final E psiElement;
    private final Severity severity;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "factory";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                objArr[0] = "severity";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                objArr[0] = "org/jetbrains/kotlin/diagnostics/AbstractDiagnostic";
                break;
            default:
                objArr[0] = "psiElement";
                break;
        }
        if (i == 3) {
            objArr[1] = "getFactory";
        } else if (i == 4) {
            objArr[1] = "getPsiFile";
        } else if (i == 5) {
            objArr[1] = "getSeverity";
        } else if (i == 6) {
            objArr[1] = "getPsiElement";
        } else if (i != 7) {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/AbstractDiagnostic";
        } else {
            objArr[1] = "getTextRanges";
        }
        if (i != 3 && i != 4 && i != 5 && i != 6 && i != 7) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 5 && i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public AbstractDiagnostic(E e, DiagnosticFactoryWithPsiElement<E, ?> diagnosticFactoryWithPsiElement, Severity severity) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        if (diagnosticFactoryWithPsiElement == null) {
            $$$reportNull$$$0(1);
        }
        if (severity == null) {
            $$$reportNull$$$0(2);
        }
        this.psiElement = e;
        this.factory = diagnosticFactoryWithPsiElement;
        this.severity = severity;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AbstractDiagnostic abstractDiagnostic = (AbstractDiagnostic) obj;
            if (Objects.equals(this.psiElement, abstractDiagnostic.psiElement) && Objects.equals(this.factory, abstractDiagnostic.factory) && this.severity == abstractDiagnostic.severity) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public DiagnosticFactoryWithPsiElement<E, ?> getFactory() {
        DiagnosticFactoryWithPsiElement<E, ?> diagnosticFactoryWithPsiElement = this.factory;
        if (diagnosticFactoryWithPsiElement == null) {
            $$$reportNull$$$0(3);
        }
        return diagnosticFactoryWithPsiElement;
    }

    @Override // org.jetbrains.kotlin.diagnostics.ParametrizedDiagnostic, org.jetbrains.kotlin.diagnostics.Diagnostic, org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    public E getPsiElement() {
        E e = this.psiElement;
        if (e == null) {
            $$$reportNull$$$0(6);
        }
        return e;
    }

    @Override // org.jetbrains.kotlin.diagnostics.Diagnostic
    public PsiFile getPsiFile() {
        PsiFile containingFile = this.psiElement.getContainingFile();
        if (containingFile == null) {
            $$$reportNull$$$0(4);
        }
        return containingFile;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public Severity getSeverity() {
        Severity severity = this.severity;
        if (severity == null) {
            $$$reportNull$$$0(5);
        }
        return severity;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public List<TextRange> getTextRanges() {
        List<TextRange> textRanges = getFactory().getTextRanges(this);
        if (textRanges == null) {
            $$$reportNull$$$0(7);
        }
        return textRanges;
    }

    public int hashCode() {
        return Objects.hash(this.psiElement, this.factory, this.severity);
    }

    public boolean isValid() {
        return getFactory().isValid(this);
    }
}
