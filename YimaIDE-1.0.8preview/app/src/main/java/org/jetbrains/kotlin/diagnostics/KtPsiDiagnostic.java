package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtPsiDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "element", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "getElement", "()Lorg/jetbrains/kotlin/KtPsiSourceElement;", "textRanges", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "getTextRanges", "()Ljava/util/List;", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "psiElement", "Lcom/intellij/psi/PsiElement;", "getPsiElement", "()Lcom/intellij/psi/PsiElement;", "psiFile", "Lcom/intellij/psi/PsiFile;", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface KtPsiDiagnostic extends DiagnosticMarker {
    /* JADX INFO: renamed from: getElement */
    KtPsiSourceElement mo192getElement();

    KtDiagnosticFactoryN getFactory();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    default PsiElement getPsiElement() {
        return mo192getElement().getPsi();
    }

    default PsiFile getPsiFile() {
        PsiFile containingFile = getPsiElement().getContainingFile();
        containingFile.getClass();
        return containingFile;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    Severity getSeverity();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    List<TextRange> getTextRanges();
}
