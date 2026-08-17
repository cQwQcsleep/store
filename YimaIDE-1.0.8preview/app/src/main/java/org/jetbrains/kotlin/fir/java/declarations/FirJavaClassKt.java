package org.jetbrains.kotlin.fir.java.declarations;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"buildJavaClass", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClassBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaClassKt {
    public static final FirJavaClass buildJavaClass(Function1<? super FirJavaClassBuilder, Unit> function1) {
        function1.getClass();
        FirJavaClassBuilder firJavaClassBuilder = new FirJavaClassBuilder();
        function1.invoke(firJavaClassBuilder);
        return firJavaClassBuilder.mo289build();
    }
}
