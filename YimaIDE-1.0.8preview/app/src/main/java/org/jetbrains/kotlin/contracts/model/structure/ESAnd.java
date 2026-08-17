package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.ESOperator;
import org.jetbrains.kotlin.contracts.model.functors.AndFunctor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESAnd;", "Lorg/jetbrains/kotlin/contracts/model/ESOperator;", "left", "Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "right", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESExpression;Lorg/jetbrains/kotlin/contracts/model/ESExpression;)V", "getLeft", "()Lorg/jetbrains/kotlin/contracts/model/ESExpression;", "getRight", "functor", "Lorg/jetbrains/kotlin/contracts/model/functors/AndFunctor;", "getFunctor", "()Lorg/jetbrains/kotlin/contracts/model/functors/AndFunctor;", "accept", "T", "visitor", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "(Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESAnd implements ESOperator {
    private final AndFunctor functor;
    private final ESExpression left;
    private final ESExpression right;

    public ESAnd(ESExpression eSExpression, ESExpression eSExpression2) {
        eSExpression.getClass();
        eSExpression2.getClass();
        this.left = eSExpression;
        this.right = eSExpression2;
        this.functor = new AndFunctor();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpression
    public <T> T accept(ESExpressionVisitor<? extends T> visitor) {
        visitor.getClass();
        return visitor.visitAnd(this);
    }

    public final ESExpression getLeft() {
        return this.left;
    }

    public final ESExpression getRight() {
        return this.right;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESOperator
    public AndFunctor getFunctor() {
        return this.functor;
    }
}
