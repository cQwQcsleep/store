package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiWhiteSpace;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\n\u001a\u00020\u0002\u001a\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004¨\u0006\u0013"}, d2 = {"markElement", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "Lcom/intellij/psi/PsiElement;", "markSingleElement", "markNode", "node", "Lcom/intellij/lang/ASTNode;", "markRange", "range", "from", "to", "getStartOffset", Argument.Delimiters.none, "getEndOffset", "hasSyntaxErrors", Argument.Delimiters.none, "psiElement", "org.jetbrains.kotlin:frontend.common-psi"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PositioningStrategyKt {
    private static final int getEndOffset(PsiElement psiElement) {
        PsiElement lastChild = psiElement.getLastChild();
        if (lastChild != null) {
            while (true) {
                if (!(lastChild instanceof PsiComment) && !(lastChild instanceof PsiWhiteSpace)) {
                    break;
                }
                lastChild = lastChild.getPrevSibling();
            }
            if (lastChild != null) {
                return getEndOffset(lastChild);
            }
        }
        return PsiUtilsKt.getEndOffset(psiElement);
    }

    private static final int getStartOffset(PsiElement psiElement) {
        PsiElement firstChild = psiElement.getFirstChild();
        if (firstChild != null) {
            while (true) {
                if (!(firstChild instanceof PsiComment) && !(firstChild instanceof PsiWhiteSpace)) {
                    break;
                }
                firstChild = firstChild.getNextSibling();
            }
            if (firstChild != null) {
                return getStartOffset(firstChild);
            }
        }
        return PsiUtilsKt.getStartOffset(psiElement);
    }

    public static final boolean hasSyntaxErrors(PsiElement psiElement) {
        psiElement.getClass();
        if (psiElement instanceof PsiErrorElement) {
            return true;
        }
        PsiElement[] children = psiElement.getChildren();
        children.getClass();
        if (!(children.length == 0)) {
            Object objLast = ArraysKt.last(children);
            objLast.getClass();
            if (hasSyntaxErrors((PsiElement) objLast)) {
                return true;
            }
        }
        return false;
    }

    public static final List<TextRange> markElement(PsiElement psiElement) {
        psiElement.getClass();
        return CollectionsKt.listOf(new TextRange(getStartOffset(psiElement), getEndOffset(psiElement)));
    }

    public static final List<TextRange> markNode(ASTNode aSTNode) {
        aSTNode.getClass();
        PsiElement psi = aSTNode.getPsi();
        psi.getClass();
        return markElement(psi);
    }

    public static final List<TextRange> markRange(PsiElement psiElement, PsiElement psiElement2) {
        psiElement.getClass();
        psiElement2.getClass();
        return markRange(new TextRange(getStartOffset(psiElement), getEndOffset(psiElement2)));
    }

    public static final TextRange markSingleElement(PsiElement psiElement) {
        psiElement.getClass();
        return new TextRange(getStartOffset(psiElement), getEndOffset(psiElement));
    }

    public static final List<TextRange> markRange(TextRange textRange) {
        textRange.getClass();
        return CollectionsKt.listOf(textRange);
    }
}
