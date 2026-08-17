package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00028\u0000H\u0017b\u0002\b\u0011¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "E", "Lcom/intellij/psi/PsiElement;", Argument.Delimiters.none, "<init>", "()V", "markDiagnostic", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "mark", "element", "(Lcom/intellij/psi/PsiElement;)Ljava/util/List;", "isValid", Argument.Delimiters.none, "(Lcom/intellij/psi/PsiElement;)Z", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticLossRisk;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class PositioningStrategy<E extends PsiElement> {
    @DiagnosticLossRisk
    public boolean isValid(E element) {
        element.getClass();
        return !PositioningStrategyKt.hasSyntaxErrors(element);
    }

    public List<TextRange> mark(E element) {
        element.getClass();
        return PositioningStrategyKt.markElement(element);
    }

    public List<TextRange> markDiagnostic(DiagnosticMarker diagnostic) {
        diagnostic.getClass();
        PsiElement psiElement = diagnostic.getPsiElement();
        psiElement.getClass();
        return mark(psiElement);
    }
}
