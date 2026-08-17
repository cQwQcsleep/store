package org.jetbrains.kotlin.resolve.checkers;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression;
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression;
import org.jetbrains.kotlin.psi.KtImportDirective;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.KtUserType;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isUsageAsAnnotationOrImport", "", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class UsageCheckerUtilsKt {
    public static final boolean isUsageAsAnnotationOrImport(PsiElement psiElement) {
        psiElement.getClass();
        KtUserType parent = psiElement.getParent();
        if (!(parent instanceof KtUserType)) {
            return (parent instanceof KtDotQualifiedExpression) && (((KtDotQualifiedExpression) parent).getParent() instanceof KtImportDirective);
        }
        KtUserType ktUserType = parent;
        return ((ktUserType.getParent() instanceof KtUserType) && isUsageAsAnnotationOrImport(parent)) || ((ktUserType.getParent() instanceof KtTypeReference) && (ktUserType.getParent().getParent() instanceof KtConstructorCalleeExpression) && (ktUserType.getParent().getParent().getParent() instanceof KtAnnotationEntry));
    }
}
