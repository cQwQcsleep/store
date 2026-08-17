package org.jetbrains.kotlin.fir.lightTree.fir;

import com.intellij.lang.LighterASTNode;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.ConversionUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001BQ\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010\u001e\u001a\u00020\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0019R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/WhenEntry;", Argument.Delimiters.none, "conditions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "guard", "firBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "node", "Lcom/intellij/lang/LighterASTNode;", "isElse", Argument.Delimiters.none, "shouldBindSubject", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Lcom/intellij/lang/LighterASTNode;ZZLcom/intellij/util/diff/FlyweightCapableTreeStructure;)V", "getConditions", "()Ljava/util/List;", "getGuard", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getFirBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getNode", "()Lcom/intellij/lang/LighterASTNode;", "()Z", "getShouldBindSubject", "getTree", "()Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "toFirWhenCondition", "toFirWhenConditionWithoutSubject", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WhenEntry {
    private final List<FirExpression> conditions;
    private final FirBlock firBlock;
    private final FirExpression guard;
    private final boolean isElse;
    private final LighterASTNode node;
    private final boolean shouldBindSubject;
    private final FlyweightCapableTreeStructure<LighterASTNode> tree;

    /* JADX WARN: Multi-variable type inference failed */
    public WhenEntry(List<? extends FirExpression> list, FirExpression firExpression, FirBlock firBlock, LighterASTNode lighterASTNode, boolean z, boolean z2, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        list.getClass();
        firBlock.getClass();
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        this.conditions = list;
        this.guard = firExpression;
        this.firBlock = firBlock;
        this.node = lighterASTNode;
        this.isElse = z;
        this.shouldBindSubject = z2;
        this.tree = flyweightCapableTreeStructure;
    }

    public final List<FirExpression> getConditions() {
        return this.conditions;
    }

    public final FirBlock getFirBlock() {
        return this.firBlock;
    }

    public final FirExpression getGuard() {
        return this.guard;
    }

    public final LighterASTNode getNode() {
        return this.node;
    }

    public final boolean getShouldBindSubject() {
        return this.shouldBindSubject;
    }

    public final FlyweightCapableTreeStructure<LighterASTNode> getTree() {
        return this.tree;
    }

    /* JADX INFO: renamed from: isElse, reason: from getter */
    public final boolean getIsElse() {
        return this.isElse;
    }

    public final FirExpression toFirWhenCondition() {
        if (!this.conditions.isEmpty()) {
            return ConversionUtilsKt.buildBalancedOrExpressionTree$default(this.conditions, 0, 0, 6, null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    public final FirExpression toFirWhenConditionWithoutSubject() {
        if (this.conditions.size() != 0) {
            return ConversionUtilsKt.buildBalancedOrExpressionTree$default(this.conditions, 0, 0, 6, null);
        }
        LighterASTNode lighterASTNode = this.node;
        return FirExpressionUtilKt.buildErrorExpression$default(new KtLightSourceElement(lighterASTNode, lighterASTNode.getStartOffset(), lighterASTNode.getEndOffset(), this.tree, KtRealSourceElementKind.INSTANCE), new ConeSyntaxDiagnostic("No expression in condition with expression"), null, 4, null);
    }

    public /* synthetic */ WhenEntry(List list, FirExpression firExpression, FirBlock firBlock, LighterASTNode lighterASTNode, boolean z, boolean z2, FlyweightCapableTreeStructure flyweightCapableTreeStructure, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, firExpression, firBlock, lighterASTNode, (i & 16) != 0 ? false : z, (i & 32) != 0 ? false : z2, flyweightCapableTreeStructure);
    }
}
