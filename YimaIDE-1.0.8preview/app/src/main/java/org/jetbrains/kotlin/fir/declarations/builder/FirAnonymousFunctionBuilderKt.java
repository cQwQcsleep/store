package org.jetbrains.kotlin.fir.declarations.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a:\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"buildAnonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirAnonymousFunctionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "buildAnonymousFunctionCopy", "original", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionBuilderKt {
    public static final FirAnonymousFunction buildAnonymousFunction(Function1<? super FirAnonymousFunctionBuilder, Unit> function1) {
        function1.getClass();
        FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
        function1.invoke(firAnonymousFunctionBuilder);
        return firAnonymousFunctionBuilder.mo288build();
    }

    public static final FirAnonymousFunction buildAnonymousFunctionCopy(FirAnonymousFunction firAnonymousFunction, Function1<? super FirAnonymousFunctionBuilder, Unit> function1) {
        firAnonymousFunction.getClass();
        function1.getClass();
        FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
        firAnonymousFunctionBuilder.setSource(firAnonymousFunction.getSource());
        firAnonymousFunctionBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firAnonymousFunction));
        firAnonymousFunctionBuilder.getAnnotations().addAll(firAnonymousFunction.getAnnotations());
        firAnonymousFunctionBuilder.setModuleData(firAnonymousFunction.getModuleData());
        firAnonymousFunctionBuilder.setOrigin(firAnonymousFunction.getOrigin());
        firAnonymousFunctionBuilder.setAttributes(firAnonymousFunction.getAttributes().copy());
        firAnonymousFunctionBuilder.setStatus(firAnonymousFunction.getStatus());
        firAnonymousFunctionBuilder.setReturnTypeRef(firAnonymousFunction.getReturnTypeRef());
        firAnonymousFunctionBuilder.setReceiverParameter(firAnonymousFunction.getReceiverParameter());
        firAnonymousFunctionBuilder.setDeprecationsProvider(firAnonymousFunction.getDeprecationsProvider());
        firAnonymousFunctionBuilder.setDispatchReceiverType(firAnonymousFunction.getDispatchReceiverType());
        firAnonymousFunctionBuilder.getContextParameters().addAll(firAnonymousFunction.getContextParameters());
        firAnonymousFunctionBuilder.setControlFlowGraphReference(firAnonymousFunction.getControlFlowGraphReference());
        firAnonymousFunctionBuilder.getValueParameters().addAll(firAnonymousFunction.getValueParameters());
        firAnonymousFunctionBuilder.setBody(firAnonymousFunction.getBody());
        firAnonymousFunctionBuilder.setContractDescription(firAnonymousFunction.getContractDescription());
        firAnonymousFunctionBuilder.setLabel(firAnonymousFunction.getLabel());
        firAnonymousFunctionBuilder.setInvocationKind(firAnonymousFunction.getInvocationKind());
        firAnonymousFunctionBuilder.setInlineStatus(firAnonymousFunction.getInlineStatus());
        firAnonymousFunctionBuilder.setLambda(firAnonymousFunction.getIsLambda());
        firAnonymousFunctionBuilder.setHasExplicitParameterList(firAnonymousFunction.getHasExplicitParameterList());
        firAnonymousFunctionBuilder.getTypeParameters().addAll(firAnonymousFunction.getTypeParameters());
        firAnonymousFunctionBuilder.setTypeRef(firAnonymousFunction.getTypeRef());
        function1.invoke(firAnonymousFunctionBuilder);
        return firAnonymousFunctionBuilder.mo288build();
    }
}
