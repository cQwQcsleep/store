package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.ESOperator;
import org.jetbrains.kotlin.contracts.model.functors.NotFunctor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESNot;", "Lorg/jetbrains/kotlin/contracts/model/ESOperator;", "arg", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESExpression;)V", "getArg", "()Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "functor", "Lorg/jetbrains/kotlin/contracts/model/functors/NotFunctor;", "getFunctor", "()Lorg/jetbrains/kotlin/contracts/model/functors/NotFunctor;", "accept", "T", "visitor", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "(Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESNot implements ESOperator {
    private final ESExpression arg;
    private final NotFunctor functor;

    public ESNot(ESExpression eSExpression) {
        eSExpression.getClass();
        this.arg = eSExpression;
        this.functor = new NotFunctor();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpression
    public <T> T accept(ESExpressionVisitor<? extends T> visitor) {
        visitor.getClass();
        return visitor.visitNot(this);
    }

    public final ESExpression getArg() {
        return this.arg;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESOperator
    public NotFunctor getFunctor() {
        return this.functor;
    }
}
