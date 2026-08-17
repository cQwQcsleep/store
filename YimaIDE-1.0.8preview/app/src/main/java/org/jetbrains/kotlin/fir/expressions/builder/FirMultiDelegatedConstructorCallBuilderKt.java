package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"buildMultiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirMultiDelegatedConstructorCallBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMultiDelegatedConstructorCallBuilderKt {
    public static final FirMultiDelegatedConstructorCall buildMultiDelegatedConstructorCall(Function1<? super FirMultiDelegatedConstructorCallBuilder, Unit> function1) {
        function1.getClass();
        FirMultiDelegatedConstructorCallBuilder firMultiDelegatedConstructorCallBuilder = new FirMultiDelegatedConstructorCallBuilder();
        function1.invoke(firMultiDelegatedConstructorCallBuilder);
        return firMultiDelegatedConstructorCallBuilder.build();
    }

    public static /* synthetic */ FirMultiDelegatedConstructorCall buildMultiDelegatedConstructorCall$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<FirMultiDelegatedConstructorCallBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.expressions.builder.FirMultiDelegatedConstructorCallBuilderKt.buildMultiDelegatedConstructorCall.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirMultiDelegatedConstructorCallBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirMultiDelegatedConstructorCallBuilder firMultiDelegatedConstructorCallBuilder) {
                    firMultiDelegatedConstructorCallBuilder.getClass();
                }
            };
        }
        function1.getClass();
        FirMultiDelegatedConstructorCallBuilder firMultiDelegatedConstructorCallBuilder = new FirMultiDelegatedConstructorCallBuilder();
        function1.invoke(firMultiDelegatedConstructorCallBuilder);
        return firMultiDelegatedConstructorCallBuilder.build();
    }
}
