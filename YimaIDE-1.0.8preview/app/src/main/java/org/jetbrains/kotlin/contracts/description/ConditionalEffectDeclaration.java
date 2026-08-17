package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J5\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0010\u001a\u0002H\rH\u0016¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/ConditionalEffectDeclaration;", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "effect", "condition", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;)V", "getEffect", "()Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "getCondition", "()Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConditionalEffectDeclaration implements EffectDeclaration {
    private final BooleanExpression condition;
    private final EffectDeclaration effect;

    public ConditionalEffectDeclaration(EffectDeclaration effectDeclaration, BooleanExpression booleanExpression) {
        effectDeclaration.getClass();
        booleanExpression.getClass();
        this.effect = effectDeclaration;
        this.condition = booleanExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.EffectDeclaration, org.jetbrains.kotlin.contracts.description.ContractDescriptionElement
    public <R, D> R accept(ContractDescriptionVisitor<? extends R, ? super D> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitConditionalEffectDeclaration(this, data);
    }

    public final BooleanExpression getCondition() {
        return this.condition;
    }

    public final EffectDeclaration getEffect() {
        return this.effect;
    }
}
