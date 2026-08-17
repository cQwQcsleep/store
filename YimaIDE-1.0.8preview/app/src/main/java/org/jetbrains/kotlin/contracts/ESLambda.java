package org.jetbrains.kotlin.contracts;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.structure.AbstractESValue;
import org.jetbrains.kotlin.psi.KtLambdaExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0016¢\u0006\u0002\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/ESLambda;", "Lorg/jetbrains/kotlin/contracts/model/structure/AbstractESValue;", "lambda", "Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "<init>", "(Lorg/jetbrains/kotlin/psi/KtLambdaExpression;)V", "getLambda", "()Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "accept", "T", "visitor", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "(Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;)Ljava/lang/Object;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESLambda extends AbstractESValue {
    private final KtLambdaExpression lambda;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESLambda(KtLambdaExpression ktLambdaExpression) {
        super(null);
        ktLambdaExpression.getClass();
        this.lambda = ktLambdaExpression;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpression
    public <T> T accept(ESExpressionVisitor<? extends T> visitor) {
        visitor.getClass();
        return visitor.visitLambda(this);
    }

    public final KtLambdaExpression getLambda() {
        return this.lambda;
    }
}
