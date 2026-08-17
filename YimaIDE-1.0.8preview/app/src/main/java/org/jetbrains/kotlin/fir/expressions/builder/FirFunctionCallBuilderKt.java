package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildFunctionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirFunctionCallBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildFunctionCallCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFunctionCallBuilderKt {
    public static final FirFunctionCall buildFunctionCall(Function1<? super FirFunctionCallBuilder, Unit> function1) {
        function1.getClass();
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        function1.invoke(firFunctionCallBuilder);
        return firFunctionCallBuilder.mo288build();
    }

    public static final FirFunctionCall buildFunctionCallCopy(FirFunctionCall firFunctionCall, Function1<? super FirFunctionCallBuilder, Unit> function1) {
        firFunctionCall.getClass();
        function1.getClass();
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setConeTypeOrNull(firFunctionCall.getConeTypeOrNull());
        firFunctionCallBuilder.getAnnotations().addAll(firFunctionCall.getAnnotations());
        firFunctionCallBuilder.getContextArguments().addAll(firFunctionCall.getContextArguments());
        firFunctionCallBuilder.getTypeArguments().addAll(firFunctionCall.getTypeArguments());
        firFunctionCallBuilder.setExplicitReceiver(firFunctionCall.getExplicitReceiver());
        firFunctionCallBuilder.setDispatchReceiver(firFunctionCall.getDispatchReceiver());
        firFunctionCallBuilder.setExtensionReceiver(firFunctionCall.getExtensionReceiver());
        firFunctionCallBuilder.setSource(firFunctionCall.getSource());
        firFunctionCallBuilder.getNonFatalDiagnostics().addAll(firFunctionCall.getNonFatalDiagnostics());
        firFunctionCallBuilder.setArgumentList(firFunctionCall.getArgumentList());
        firFunctionCallBuilder.setCalleeReference(firFunctionCall.getCalleeReference());
        firFunctionCallBuilder.setOrigin(firFunctionCall.getOrigin());
        function1.invoke(firFunctionCallBuilder);
        return firFunctionCallBuilder.mo288build();
    }
}
