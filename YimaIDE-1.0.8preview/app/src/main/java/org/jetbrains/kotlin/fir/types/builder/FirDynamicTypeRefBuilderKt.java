package org.jetbrains.kotlin.fir.types.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"buildDynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/builder/FirDynamicTypeRefBuilder;", "", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FirDynamicTypeRefBuilderKt {
    public static final FirDynamicTypeRef buildDynamicTypeRef(Function1<? super FirDynamicTypeRefBuilder, Unit> function1) {
        function1.getClass();
        FirDynamicTypeRefBuilder firDynamicTypeRefBuilder = new FirDynamicTypeRefBuilder();
        function1.invoke(firDynamicTypeRefBuilder);
        return firDynamicTypeRefBuilder.build();
    }
}
