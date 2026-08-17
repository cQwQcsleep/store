package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "psiElement", "Lcom/intellij/psi/PsiElement;", "getPsiElement", "()Lcom/intellij/psi/PsiElement;", "psiFile", "Lcom/intellij/psi/PsiFile;", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "factoryName", Argument.Delimiters.none, "getFactoryName", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface Diagnostic extends DiagnosticMarker, UnboundDiagnostic {
    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    default String getFactoryName() {
        return getFactory().getName();
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker
    PsiElement getPsiElement();

    PsiFile getPsiFile();
}
