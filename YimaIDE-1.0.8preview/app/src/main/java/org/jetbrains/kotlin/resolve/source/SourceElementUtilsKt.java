package org.jetbrains.kotlin.resolve.source;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002\u001a\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¨\u0006\u0007"}, d2 = {"hasUnwrappableAsAssignmentLhs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "getAssignmentLhsIfUnwrappable", "Lcom/intellij/lang/LighterASTNode;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "org.jetbrains.kotlin:psi-frontend-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceElementUtilsKt {
    public static final LighterASTNode getAssignmentLhsIfUnwrappable(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        LighterASTNode lighterASTNode2 = Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.PREFIX_EXPRESSION) ? (LighterASTNode) CollectionsKt.lastOrNull(LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure)) : (LighterASTNode) CollectionsKt.firstOrNull(LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure));
        if (CollectionsKt.contains(PsiUtilsKt.getUNWRAPPABLE_TOKEN_TYPES(), lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null)) {
            return lighterASTNode2;
        }
        return null;
    }

    public static final boolean hasUnwrappableAsAssignmentLhs(KtSourceElement ktSourceElement) {
        PsiElement assignmentLhsIfUnwrappable;
        if (ktSourceElement == null) {
            return false;
        }
        PsiElement psi = KtSourceElementKt.getPsi(ktSourceElement);
        if (psi == null || (assignmentLhsIfUnwrappable = PsiUtilsKt.getAssignmentLhsIfUnwrappable(psi)) == null) {
            assignmentLhsIfUnwrappable = getAssignmentLhsIfUnwrappable(ktSourceElement.getLighterASTNode(), ktSourceElement.getTreeStructure());
        }
        return assignmentLhsIfUnwrappable != null;
    }
}
