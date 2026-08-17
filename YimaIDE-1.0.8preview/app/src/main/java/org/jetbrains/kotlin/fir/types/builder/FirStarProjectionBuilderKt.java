package org.jetbrains.kotlin.fir.types.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.fir.types.FirStarProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"buildStarProjection", "Lorg/jetbrains/kotlin/fir/types/FirStarProjection;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/builder/FirStarProjectionBuilder;", "", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FirStarProjectionBuilderKt {
    public static final FirStarProjection buildStarProjection(Function1<? super FirStarProjectionBuilder, Unit> function1) {
        function1.getClass();
        FirStarProjectionBuilder firStarProjectionBuilder = new FirStarProjectionBuilder();
        function1.invoke(firStarProjectionBuilder);
        return firStarProjectionBuilder.build();
    }

    public static /* synthetic */ FirStarProjection buildStarProjection$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<FirStarProjectionBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.types.builder.FirStarProjectionBuilderKt.buildStarProjection.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirStarProjectionBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirStarProjectionBuilder firStarProjectionBuilder) {
                    firStarProjectionBuilder.getClass();
                }
            };
        }
        function1.getClass();
        FirStarProjectionBuilder firStarProjectionBuilder = new FirStarProjectionBuilder();
        function1.invoke(firStarProjectionBuilder);
        return firStarProjectionBuilder.build();
    }
}
