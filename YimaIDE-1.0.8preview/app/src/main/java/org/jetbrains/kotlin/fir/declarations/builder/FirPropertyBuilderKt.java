package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirPropertyBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildPropertyCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyBuilderKt {
    public static final FirProperty buildProperty(Function1<? super FirPropertyBuilder, Unit> function1) {
        function1.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        function1.invoke(firPropertyBuilder);
        return firPropertyBuilder.mo288build();
    }

    public static final FirProperty buildPropertyCopy(FirProperty firProperty, Function1<? super FirPropertyBuilder, Unit> function1) {
        firProperty.getClass();
        function1.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setSource(firProperty.getSource());
        firPropertyBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firProperty));
        firPropertyBuilder.setModuleData(firProperty.getModuleData());
        firPropertyBuilder.setOrigin(firProperty.getOrigin());
        firPropertyBuilder.setAttributes(firProperty.getAttributes().copy());
        firPropertyBuilder.setStatus(firProperty.getStatus());
        firPropertyBuilder.setLocal(firProperty.getIsLocal());
        firPropertyBuilder.setReturnTypeRef(firProperty.getReturnTypeRef());
        firPropertyBuilder.setReceiverParameter(firProperty.getReceiverParameter());
        firPropertyBuilder.setDeprecationsProvider(firProperty.getDeprecationsProvider());
        firPropertyBuilder.setContainerSource(firProperty.getContainerSource());
        firPropertyBuilder.setDispatchReceiverType(firProperty.getDispatchReceiverType());
        firPropertyBuilder.getContextParameters().addAll(firProperty.getContextParameters());
        firPropertyBuilder.setName(firProperty.getName());
        firPropertyBuilder.setInitializer(firProperty.getInitializer());
        firPropertyBuilder.setDelegate(firProperty.getDelegate());
        firPropertyBuilder.setVar(firProperty.getIsVar());
        firPropertyBuilder.setGetter(firProperty.getGetter());
        firPropertyBuilder.setSetter(firProperty.getSetter());
        firPropertyBuilder.setBackingField(firProperty.getBackingField());
        firPropertyBuilder.getAnnotations().addAll(firProperty.getAnnotations());
        firPropertyBuilder.setDelegateFieldSymbol(firProperty.getDelegateFieldSymbol());
        firPropertyBuilder.setBodyResolveState(firProperty.getBodyResolveState());
        firPropertyBuilder.getTypeParameters().addAll(firProperty.getTypeParameters());
        function1.invoke(firPropertyBuilder);
        return firPropertyBuilder.mo288build();
    }
}
