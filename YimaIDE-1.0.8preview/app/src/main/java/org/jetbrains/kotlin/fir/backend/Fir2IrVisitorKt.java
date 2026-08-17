package org.jetbrains.kotlin.fir.backend;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import defpackage.f2f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtForExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"isChildOfForLoop", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/KtSourceElement;)Z", "operationToken", "Lcom/intellij/psi/tree/IElementType;", "getOperationToken", "(Lorg/jetbrains/kotlin/KtSourceElement;)Lcom/intellij/psi/tree/IElementType;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrVisitorKt {
    public static final IElementType getOperationToken(KtSourceElement ktSourceElement) {
        IElementType tokenType;
        ktSourceElement.getClass();
        Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.BINARY_EXPRESSION);
        if (ktSourceElement instanceof KtPsiSourceElement) {
            KtBinaryExpression psi = ((KtPsiSourceElement) ktSourceElement).getPsi();
            KtBinaryExpression ktBinaryExpression = psi instanceof KtBinaryExpression ? psi : null;
            if (ktBinaryExpression != null) {
                return ktBinaryExpression.getOperationToken();
            }
            return null;
        }
        FlyweightCapableTreeStructure treeStructure = ktSourceElement.getTreeStructure();
        LighterASTNode lighterASTNode = ktSourceElement.getLighterASTNode();
        IElementType iElementType = KtNodeTypes.OPERATION_REFERENCE;
        iElementType.getClass();
        LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType((FlyweightCapableTreeStructure<LighterASTNode>) treeStructure, lighterASTNode, iElementType);
        if (lighterASTNodeFindChildByType != null && (tokenType = lighterASTNodeFindChildByType.getTokenType()) != null) {
            return tokenType;
        }
        f2f.a("No operation reference for binary expression: ", ktSourceElement.getLighterASTNode());
        return null;
    }

    public static final boolean isChildOfForLoop(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        if (ktSourceElement instanceof KtPsiSourceElement) {
            return ((KtPsiSourceElement) ktSourceElement).getPsi().getParent() instanceof KtForExpression;
        }
        LighterASTNode lighterASTNode = (LighterASTNode) ktSourceElement.getTreeStructure().getParent(ktSourceElement.getLighterASTNode());
        return Intrinsics.areEqual(lighterASTNode != null ? lighterASTNode.getTokenType() : null, KtNodeTypes.FOR);
    }
}
