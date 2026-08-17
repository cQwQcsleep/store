package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildPropertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirPropertyAccessorBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildPropertyAccessorCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyAccessorBuilderKt {
    public static final FirPropertyAccessor buildPropertyAccessor(Function1<? super FirPropertyAccessorBuilder, Unit> function1) {
        function1.getClass();
        FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
        function1.invoke(firPropertyAccessorBuilder);
        return firPropertyAccessorBuilder.mo289build();
    }

    public static final FirPropertyAccessor buildPropertyAccessorCopy(FirPropertyAccessor firPropertyAccessor, Function1<? super FirPropertyAccessorBuilder, Unit> function1) {
        firPropertyAccessor.getClass();
        function1.getClass();
        FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
        firPropertyAccessorBuilder.setSource(firPropertyAccessor.getSource());
        firPropertyAccessorBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firPropertyAccessor));
        firPropertyAccessorBuilder.setModuleData(firPropertyAccessor.getModuleData());
        firPropertyAccessorBuilder.setOrigin(firPropertyAccessor.getOrigin());
        firPropertyAccessorBuilder.setAttributes(firPropertyAccessor.getAttributes().copy());
        firPropertyAccessorBuilder.setStatus(firPropertyAccessor.getStatus());
        firPropertyAccessorBuilder.setReturnTypeRef(firPropertyAccessor.getReturnTypeRef());
        firPropertyAccessorBuilder.setDeprecationsProvider(firPropertyAccessor.getDeprecationsProvider());
        firPropertyAccessorBuilder.setDispatchReceiverType(firPropertyAccessor.getDispatchReceiverType());
        firPropertyAccessorBuilder.getValueParameters().addAll(firPropertyAccessor.getValueParameters());
        firPropertyAccessorBuilder.setBody(firPropertyAccessor.getBody());
        firPropertyAccessorBuilder.setContractDescription(firPropertyAccessor.getContractDescription());
        firPropertyAccessorBuilder.setPropertySymbol(firPropertyAccessor.getPropertySymbol());
        firPropertyAccessorBuilder.setGetter(firPropertyAccessor.getIsGetter());
        firPropertyAccessorBuilder.getAnnotations().addAll(firPropertyAccessor.getAnnotations());
        function1.invoke(firPropertyAccessorBuilder);
        return firPropertyAccessorBuilder.mo289build();
    }
}
