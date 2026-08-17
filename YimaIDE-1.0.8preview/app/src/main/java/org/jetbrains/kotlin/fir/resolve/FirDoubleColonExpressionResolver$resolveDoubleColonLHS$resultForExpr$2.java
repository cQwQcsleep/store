package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForExpr$2 extends FunctionReferenceImpl implements Function1<FirExpression, DoubleColonLHS.Expression> {
    public FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForExpr$2(Object obj) {
        super(1, obj, FirDoubleColonExpressionResolver.class, "resolveExpressionOnLHS", "resolveExpressionOnLHS(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Expression;", 0);
    }

    public final DoubleColonLHS.Expression invoke(FirExpression firExpression) {
        firExpression.getClass();
        return ((FirDoubleColonExpressionResolver) ((CallableReference) this).receiver).resolveExpressionOnLHS(firExpression);
    }
}
