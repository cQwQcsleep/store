package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a4\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a<\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirArgumentListBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildArgumentListCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirArgumentListBuilderKt {
    public static final FirArgumentList buildArgumentList(Function1<? super FirArgumentListBuilder, Unit> function1) {
        function1.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        function1.invoke(firArgumentListBuilder);
        return firArgumentListBuilder.build();
    }

    public static /* synthetic */ FirArgumentList buildArgumentList$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<FirArgumentListBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilderKt.buildArgumentList.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirArgumentListBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirArgumentListBuilder firArgumentListBuilder) {
                    firArgumentListBuilder.getClass();
                }
            };
        }
        function1.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        function1.invoke(firArgumentListBuilder);
        return firArgumentListBuilder.build();
    }

    public static final FirArgumentList buildArgumentListCopy(FirArgumentList firArgumentList, Function1<? super FirArgumentListBuilder, Unit> function1) {
        firArgumentList.getClass();
        function1.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.setSource(firArgumentList.getSource());
        firArgumentListBuilder.getArguments().addAll(firArgumentList.getArguments());
        function1.invoke(firArgumentListBuilder);
        return firArgumentListBuilder.build();
    }

    public static /* synthetic */ FirArgumentList buildArgumentListCopy$default(FirArgumentList firArgumentList, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<FirArgumentListBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilderKt.buildArgumentListCopy.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirArgumentListBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirArgumentListBuilder firArgumentListBuilder) {
                    firArgumentListBuilder.getClass();
                }
            };
        }
        firArgumentList.getClass();
        function1.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.setSource(firArgumentList.getSource());
        firArgumentListBuilder.getArguments().addAll(firArgumentList.getArguments());
        function1.invoke(firArgumentListBuilder);
        return firArgumentListBuilder.build();
    }
}
