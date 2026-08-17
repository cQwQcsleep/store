package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirNamedFunctionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildNamedFunctionCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNamedFunctionBuilderKt {
    public static final FirNamedFunction buildNamedFunction(Function1<? super FirNamedFunctionBuilder, Unit> function1) {
        function1.getClass();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        function1.invoke(firNamedFunctionBuilder);
        return firNamedFunctionBuilder.mo288build();
    }

    public static final FirNamedFunction buildNamedFunctionCopy(FirNamedFunction firNamedFunction, Function1<? super FirNamedFunctionBuilder, Unit> function1) {
        firNamedFunction.getClass();
        function1.getClass();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setSource(firNamedFunction.getSource());
        firNamedFunctionBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firNamedFunction));
        firNamedFunctionBuilder.setModuleData(firNamedFunction.getModuleData());
        firNamedFunctionBuilder.setOrigin(firNamedFunction.getOrigin());
        firNamedFunctionBuilder.setAttributes(firNamedFunction.getAttributes().copy());
        firNamedFunctionBuilder.setStatus(firNamedFunction.getStatus());
        firNamedFunctionBuilder.setLocal(firNamedFunction.getIsLocal());
        firNamedFunctionBuilder.setReturnTypeRef(firNamedFunction.getReturnTypeRef());
        firNamedFunctionBuilder.setReceiverParameter(firNamedFunction.getReceiverParameter());
        firNamedFunctionBuilder.setDeprecationsProvider(firNamedFunction.getDeprecationsProvider());
        firNamedFunctionBuilder.setContainerSource(firNamedFunction.getContainerSource());
        firNamedFunctionBuilder.setDispatchReceiverType(firNamedFunction.getDispatchReceiverType());
        firNamedFunctionBuilder.getContextParameters().addAll(firNamedFunction.getContextParameters());
        firNamedFunctionBuilder.getValueParameters().addAll(firNamedFunction.getValueParameters());
        firNamedFunctionBuilder.setBody(firNamedFunction.getBody());
        firNamedFunctionBuilder.setContractDescription(firNamedFunction.getContractDescription());
        firNamedFunctionBuilder.setName(firNamedFunction.getName());
        firNamedFunctionBuilder.getAnnotations().addAll(firNamedFunction.getAnnotations());
        firNamedFunctionBuilder.getTypeParameters().addAll(firNamedFunction.getTypeParameters());
        function1.invoke(firNamedFunctionBuilder);
        return firNamedFunctionBuilder.mo288build();
    }
}
