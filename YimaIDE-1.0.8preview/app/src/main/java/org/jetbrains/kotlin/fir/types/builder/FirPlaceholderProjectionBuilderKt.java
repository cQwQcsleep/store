package org.jetbrains.kotlin.fir.types.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"buildPlaceholderProjection", "Lorg/jetbrains/kotlin/fir/types/FirPlaceholderProjection;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/builder/FirPlaceholderProjectionBuilder;", "", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FirPlaceholderProjectionBuilderKt {
    public static final FirPlaceholderProjection buildPlaceholderProjection(Function1<? super FirPlaceholderProjectionBuilder, Unit> function1) {
        function1.getClass();
        FirPlaceholderProjectionBuilder firPlaceholderProjectionBuilder = new FirPlaceholderProjectionBuilder();
        function1.invoke(firPlaceholderProjectionBuilder);
        return firPlaceholderProjectionBuilder.build();
    }

    public static /* synthetic */ FirPlaceholderProjection buildPlaceholderProjection$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<FirPlaceholderProjectionBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.types.builder.FirPlaceholderProjectionBuilderKt.buildPlaceholderProjection.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirPlaceholderProjectionBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirPlaceholderProjectionBuilder firPlaceholderProjectionBuilder) {
                    firPlaceholderProjectionBuilder.getClass();
                }
            };
        }
        function1.getClass();
        FirPlaceholderProjectionBuilder firPlaceholderProjectionBuilder = new FirPlaceholderProjectionBuilder();
        function1.invoke(firPlaceholderProjectionBuilder);
        return firPlaceholderProjectionBuilder.build();
    }
}
