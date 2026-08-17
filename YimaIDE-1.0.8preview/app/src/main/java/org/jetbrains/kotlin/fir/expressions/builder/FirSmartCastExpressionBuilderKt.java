package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildSmartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirSmartCastExpressionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildSmartCastExpressionCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSmartCastExpressionBuilderKt {
    public static final FirSmartCastExpression buildSmartCastExpression(Function1<? super FirSmartCastExpressionBuilder, Unit> function1) {
        function1.getClass();
        FirSmartCastExpressionBuilder firSmartCastExpressionBuilder = new FirSmartCastExpressionBuilder();
        function1.invoke(firSmartCastExpressionBuilder);
        return firSmartCastExpressionBuilder.mo288build();
    }

    public static final FirSmartCastExpression buildSmartCastExpressionCopy(FirSmartCastExpression firSmartCastExpression, Function1<? super FirSmartCastExpressionBuilder, Unit> function1) {
        firSmartCastExpression.getClass();
        function1.getClass();
        FirSmartCastExpressionBuilder firSmartCastExpressionBuilder = new FirSmartCastExpressionBuilder();
        firSmartCastExpressionBuilder.setConeTypeOrNull(firSmartCastExpression.getConeTypeOrNull());
        firSmartCastExpressionBuilder.getAnnotations().addAll(firSmartCastExpression.getAnnotations());
        firSmartCastExpressionBuilder.setOriginalExpression(firSmartCastExpression.getOriginalExpression());
        firSmartCastExpressionBuilder.setUpperTypesFromSmartCast(firSmartCastExpression.getUpperTypesFromSmartCast());
        firSmartCastExpressionBuilder.setLowerTypesFromSmartCast(firSmartCastExpression.getLowerTypesFromSmartCast());
        firSmartCastExpressionBuilder.setSmartcastType(firSmartCastExpression.getSmartcastType());
        firSmartCastExpressionBuilder.setSmartcastTypeWithoutNullableNothing(firSmartCastExpression.getSmartcastTypeWithoutNullableNothing());
        firSmartCastExpressionBuilder.setSmartcastStability(firSmartCastExpression.getSmartcastStability());
        function1.invoke(firSmartCastExpressionBuilder);
        return firSmartCastExpressionBuilder.mo288build();
    }
}
