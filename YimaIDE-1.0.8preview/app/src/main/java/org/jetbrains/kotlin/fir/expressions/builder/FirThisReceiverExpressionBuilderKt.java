package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildThisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirThisReceiverExpressionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildThisReceiverExpressionCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirThisReceiverExpressionBuilderKt {
    public static final FirThisReceiverExpression buildThisReceiverExpression(Function1<? super FirThisReceiverExpressionBuilder, Unit> function1) {
        function1.getClass();
        FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
        function1.invoke(firThisReceiverExpressionBuilder);
        return firThisReceiverExpressionBuilder.mo288build();
    }

    public static final FirThisReceiverExpression buildThisReceiverExpressionCopy(FirThisReceiverExpression firThisReceiverExpression, Function1<? super FirThisReceiverExpressionBuilder, Unit> function1) {
        firThisReceiverExpression.getClass();
        function1.getClass();
        FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
        firThisReceiverExpressionBuilder.setConeTypeOrNull(firThisReceiverExpression.getConeTypeOrNull());
        firThisReceiverExpressionBuilder.getAnnotations().addAll(firThisReceiverExpression.getAnnotations());
        firThisReceiverExpressionBuilder.getTypeArguments().addAll(firThisReceiverExpression.getTypeArguments());
        firThisReceiverExpressionBuilder.setSource(firThisReceiverExpression.getSource());
        firThisReceiverExpressionBuilder.getNonFatalDiagnostics().addAll(firThisReceiverExpression.getNonFatalDiagnostics());
        firThisReceiverExpressionBuilder.setCalleeReference(firThisReceiverExpression.getCalleeReference());
        firThisReceiverExpressionBuilder.setImplicit(firThisReceiverExpression.getIsImplicit());
        function1.invoke(firThisReceiverExpressionBuilder);
        return firThisReceiverExpressionBuilder.mo288build();
    }
}
