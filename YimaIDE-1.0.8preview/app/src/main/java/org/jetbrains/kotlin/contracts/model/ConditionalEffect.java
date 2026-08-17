package org.jetbrains.kotlin.contracts.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0001H\u0016¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/ConditionalEffect;", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "condition", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "simpleEffect", "Lorg/jetbrains/kotlin/contracts/model/SimpleEffect;", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESExpression;Lorg/jetbrains/kotlin/contracts/model/SimpleEffect;)V", "getCondition", "()Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "getSimpleEffect", "()Lorg/jetbrains/kotlin/contracts/model/SimpleEffect;", "isImplies", Argument.Delimiters.none, "other", "(Lorg/jetbrains/kotlin/contracts/model/ESEffect;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConditionalEffect extends ESEffect {
    private final ESExpression condition;
    private final SimpleEffect simpleEffect;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConditionalEffect(ESExpression eSExpression, SimpleEffect simpleEffect) {
        super(null);
        eSExpression.getClass();
        simpleEffect.getClass();
        this.condition = eSExpression;
        this.simpleEffect = simpleEffect;
    }

    public final ESExpression getCondition() {
        return this.condition;
    }

    public final SimpleEffect getSimpleEffect() {
        return this.simpleEffect;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESEffect
    public Boolean isImplies(ESEffect other) {
        other.getClass();
        return null;
    }
}
