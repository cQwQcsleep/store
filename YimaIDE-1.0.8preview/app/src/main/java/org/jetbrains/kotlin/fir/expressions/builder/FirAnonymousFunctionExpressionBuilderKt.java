package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildAnonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirAnonymousFunctionExpressionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildAnonymousFunctionExpressionCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionExpressionBuilderKt {
    public static final FirAnonymousFunctionExpression buildAnonymousFunctionExpression(Function1<? super FirAnonymousFunctionExpressionBuilder, Unit> function1) {
        function1.getClass();
        FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
        function1.invoke(firAnonymousFunctionExpressionBuilder);
        return firAnonymousFunctionExpressionBuilder.mo288build();
    }

    public static final FirAnonymousFunctionExpression buildAnonymousFunctionExpressionCopy(FirAnonymousFunctionExpression firAnonymousFunctionExpression, Function1<? super FirAnonymousFunctionExpressionBuilder, Unit> function1) {
        firAnonymousFunctionExpression.getClass();
        function1.getClass();
        FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
        firAnonymousFunctionExpressionBuilder.setSource(firAnonymousFunctionExpression.getSource());
        firAnonymousFunctionExpressionBuilder.setAnonymousFunction(firAnonymousFunctionExpression.getAnonymousFunction());
        firAnonymousFunctionExpressionBuilder.setTrailingLambda(firAnonymousFunctionExpression.getIsTrailingLambda());
        function1.invoke(firAnonymousFunctionExpressionBuilder);
        return firAnonymousFunctionExpressionBuilder.mo288build();
    }
}
