package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B7\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJA\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0002\u0010\u0014\"\u0004\b\u0003\u0010\u00152\u001e\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;", "Type", "Diagnostic", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "left", "right", "kind", "Lorg/jetbrains/kotlin/contracts/description/LogicOperationKind;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;Lorg/jetbrains/kotlin/contracts/description/LogicOperationKind;)V", "getLeft", "()Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "getRight", "getKind", "()Lorg/jetbrains/kotlin/contracts/description/LogicOperationKind;", "erroneous", Argument.Delimiters.none, "getErroneous", "()Z", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtBinaryLogicExpression<Type, Diagnostic> implements KtBooleanExpression<Type, Diagnostic> {
    private final LogicOperationKind kind;
    private final KtBooleanExpression<Type, Diagnostic> left;
    private final KtBooleanExpression<Type, Diagnostic> right;

    public KtBinaryLogicExpression(KtBooleanExpression<Type, Diagnostic> ktBooleanExpression, KtBooleanExpression<Type, Diagnostic> ktBooleanExpression2, LogicOperationKind logicOperationKind) {
        ktBooleanExpression.getClass();
        ktBooleanExpression2.getClass();
        logicOperationKind.getClass();
        this.left = ktBooleanExpression;
        this.right = ktBooleanExpression2;
        this.kind = logicOperationKind;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.KtBooleanExpression, org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public <R, D> R accept(KtContractDescriptionVisitor<? extends R, ? super D, Type, Diagnostic> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitLogicalBinaryOperationContractExpression(this, data);
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public boolean getErroneous() {
        return this.left.getErroneous() || this.right.getErroneous();
    }

    public final LogicOperationKind getKind() {
        return this.kind;
    }

    public final KtBooleanExpression<Type, Diagnostic> getLeft() {
        return this.left;
    }

    public final KtBooleanExpression<Type, Diagnostic> getRight() {
        return this.right;
    }
}
