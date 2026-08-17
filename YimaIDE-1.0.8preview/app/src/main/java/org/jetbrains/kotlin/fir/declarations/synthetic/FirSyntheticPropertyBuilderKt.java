package org.jetbrains.kotlin.fir.declarations.synthetic;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"buildSyntheticProperty", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "f", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticPropertyBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticPropertyBuilderKt {
    public static final FirSyntheticProperty buildSyntheticProperty(Function1<? super FirSyntheticPropertyBuilder, Unit> function1) {
        function1.getClass();
        FirSyntheticPropertyBuilder firSyntheticPropertyBuilder = new FirSyntheticPropertyBuilder();
        function1.invoke(firSyntheticPropertyBuilder);
        return firSyntheticPropertyBuilder.build();
    }
}
