package org.jetbrains.kotlin.fir.references.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.references.FirThisReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"buildExplicitThisReference", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/references/builder/FirExplicitThisReferenceBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExplicitThisReferenceBuilderKt {
    public static final FirThisReference buildExplicitThisReference(Function1<? super FirExplicitThisReferenceBuilder, Unit> function1) {
        function1.getClass();
        FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
        function1.invoke(firExplicitThisReferenceBuilder);
        return firExplicitThisReferenceBuilder.build();
    }

    public static /* synthetic */ FirThisReference buildExplicitThisReference$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<FirExplicitThisReferenceBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.references.builder.FirExplicitThisReferenceBuilderKt.buildExplicitThisReference.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirExplicitThisReferenceBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder) {
                    firExplicitThisReferenceBuilder.getClass();
                }
            };
        }
        function1.getClass();
        FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
        function1.invoke(firExplicitThisReferenceBuilder);
        return firExplicitThisReferenceBuilder.build();
    }
}
