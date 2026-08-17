package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B/\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJA\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0002\u0010\u0012\"\u0004\b\u0003\u0010\u00132\u001e\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00152\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u0017R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;", "Type", "Diagnostic", "Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "argumentsCondition", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "returnsEffect", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;)V", "getArgumentsCondition", "()Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "getReturnsEffect", "()Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "erroneous", Argument.Delimiters.none, "getErroneous", "()Z", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtConditionalReturnsDeclaration<Type, Diagnostic> extends KtEffectDeclaration<Type, Diagnostic> {
    private final KtBooleanExpression<Type, Diagnostic> argumentsCondition;
    private final KtEffectDeclaration<Type, Diagnostic> returnsEffect;

    public KtConditionalReturnsDeclaration(KtBooleanExpression<Type, Diagnostic> ktBooleanExpression, KtEffectDeclaration<Type, Diagnostic> ktEffectDeclaration) {
        ktBooleanExpression.getClass();
        ktEffectDeclaration.getClass();
        this.argumentsCondition = ktBooleanExpression;
        this.returnsEffect = ktEffectDeclaration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.KtEffectDeclaration, org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public <R, D> R accept(KtContractDescriptionVisitor<? extends R, ? super D, Type, Diagnostic> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitConditionalReturnsDeclaration(this, data);
    }

    public final KtBooleanExpression<Type, Diagnostic> getArgumentsCondition() {
        return this.argumentsCondition;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public boolean getErroneous() {
        return this.argumentsCondition.getErroneous() || this.returnsEffect.getErroneous();
    }

    public final KtEffectDeclaration<Type, Diagnostic> getReturnsEffect() {
        return this.returnsEffect;
    }
}
