package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParameterBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildTypeParameterCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeParameterBuilderKt {
    public static final FirTypeParameter buildTypeParameter(Function1<? super FirTypeParameterBuilder, Unit> function1) {
        function1.getClass();
        FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
        function1.invoke(firTypeParameterBuilder);
        return firTypeParameterBuilder.mo288build();
    }

    public static final FirTypeParameter buildTypeParameterCopy(FirTypeParameter firTypeParameter, Function1<? super FirTypeParameterBuilder, Unit> function1) {
        firTypeParameter.getClass();
        function1.getClass();
        FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
        firTypeParameterBuilder.setSource(firTypeParameter.getSource());
        firTypeParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firTypeParameter));
        firTypeParameterBuilder.setModuleData(firTypeParameter.getModuleData());
        firTypeParameterBuilder.setOrigin(firTypeParameter.getOrigin());
        firTypeParameterBuilder.setAttributes(firTypeParameter.getAttributes().copy());
        firTypeParameterBuilder.setName(firTypeParameter.getName());
        firTypeParameterBuilder.setContainingDeclarationSymbol(firTypeParameter.getContainingDeclarationSymbol());
        firTypeParameterBuilder.setVariance(firTypeParameter.getVariance());
        firTypeParameterBuilder.setReified(firTypeParameter.getIsReified());
        firTypeParameterBuilder.getBounds().addAll(firTypeParameter.getBounds());
        firTypeParameterBuilder.getAnnotations().addAll(firTypeParameter.getAnnotations());
        function1.invoke(firTypeParameterBuilder);
        return firTypeParameterBuilder.mo288build();
    }
}
