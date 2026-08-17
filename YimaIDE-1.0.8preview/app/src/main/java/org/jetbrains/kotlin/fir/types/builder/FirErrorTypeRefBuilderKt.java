package org.jetbrains.kotlin.fir.types.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildErrorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/builder/FirErrorTypeRefBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildErrorTypeRefCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorTypeRefBuilderKt {
    public static final FirErrorTypeRef buildErrorTypeRef(Function1<? super FirErrorTypeRefBuilder, Unit> function1) {
        function1.getClass();
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        function1.invoke(firErrorTypeRefBuilder);
        return firErrorTypeRefBuilder.build();
    }

    public static final FirErrorTypeRef buildErrorTypeRefCopy(FirErrorTypeRef firErrorTypeRef, Function1<? super FirErrorTypeRefBuilder, Unit> function1) {
        firErrorTypeRef.getClass();
        function1.getClass();
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setSource(firErrorTypeRef.getSource());
        firErrorTypeRefBuilder.setConeType(firErrorTypeRef.getConeType());
        firErrorTypeRefBuilder.setAnnotations(CollectionsKt.toMutableList(firErrorTypeRef.getAnnotations()));
        firErrorTypeRefBuilder.setDelegatedTypeRef(firErrorTypeRef.getDelegatedTypeRef());
        firErrorTypeRefBuilder.setDiagnostic(firErrorTypeRef.getDiagnostic());
        firErrorTypeRefBuilder.setPartiallyResolvedTypeRef(firErrorTypeRef.getPartiallyResolvedTypeRef());
        function1.invoke(firErrorTypeRefBuilder);
        return firErrorTypeRefBuilder.build();
    }
}
