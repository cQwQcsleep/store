package org.jetbrains.kotlin.fir.plugin;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"copyFirFunctionWithResolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "original", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "firResolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "extraInit", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirNamedFunctionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:plugin-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctionCopyUtilsKt {
    public static final FirNamedFunction copyFirFunctionWithResolvePhase(FirNamedFunction firNamedFunction, CallableId callableId, GeneratedDeclarationKey generatedDeclarationKey, FirResolvePhase firResolvePhase, Function1<? super FirNamedFunctionBuilder, Unit> function1) {
        FirReceiverParameter firReceiverParameterMo288build;
        firNamedFunction.getClass();
        callableId.getClass();
        generatedDeclarationKey.getClass();
        firResolvePhase.getClass();
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
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(callableId));
        firNamedFunctionBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(generatedDeclarationKey));
        firNamedFunctionBuilder.setResolvePhase(firResolvePhase);
        FirReceiverParameter receiverParameter = firNamedFunction.getReceiverParameter();
        if (receiverParameter != null) {
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setSource(receiverParameter.getSource());
            firReceiverParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(receiverParameter));
            firReceiverParameterBuilder.setModuleData(receiverParameter.getModuleData());
            firReceiverParameterBuilder.setOrigin(receiverParameter.getOrigin());
            firReceiverParameterBuilder.setAttributes(receiverParameter.getAttributes().copy());
            firReceiverParameterBuilder.setTypeRef(receiverParameter.getTypeRef());
            firReceiverParameterBuilder.setContainingDeclarationSymbol(receiverParameter.getContainingDeclarationSymbol());
            firReceiverParameterBuilder.getAnnotations().addAll(receiverParameter.getAnnotations());
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
            firReceiverParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(generatedDeclarationKey));
            firReceiverParameterBuilder.setResolvePhase(firResolvePhase);
            firReceiverParameterMo288build = firReceiverParameterBuilder.mo288build();
        } else {
            firReceiverParameterMo288build = null;
        }
        firNamedFunctionBuilder.setReceiverParameter(firReceiverParameterMo288build);
        copyFirFunctionWithResolvePhase$lambda$0$copyFrom(firNamedFunctionBuilder.getContextParameters(), firNamedFunctionBuilder, generatedDeclarationKey, firResolvePhase, firNamedFunction.getContextParameters());
        copyFirFunctionWithResolvePhase$lambda$0$copyFrom(firNamedFunctionBuilder.getValueParameters(), firNamedFunctionBuilder, generatedDeclarationKey, firResolvePhase, firNamedFunction.getValueParameters());
        firNamedFunctionBuilder.getTypeParameters().clear();
        List<FirTypeParameter> typeParameters = firNamedFunction.getTypeParameters();
        List<FirTypeParameter> typeParameters2 = firNamedFunctionBuilder.getTypeParameters();
        for (FirTypeParameter firTypeParameter : typeParameters) {
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
            firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
            firTypeParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
            firTypeParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(generatedDeclarationKey));
            firTypeParameterBuilder.setResolvePhase(firResolvePhase);
            typeParameters2.add(firTypeParameterBuilder.mo288build());
        }
        function1.invoke(firNamedFunctionBuilder);
        return firNamedFunctionBuilder.mo288build();
    }

    private static final void copyFirFunctionWithResolvePhase$lambda$0$copyFrom(List<FirValueParameter> list, FirNamedFunctionBuilder firNamedFunctionBuilder, GeneratedDeclarationKey generatedDeclarationKey, FirResolvePhase firResolvePhase, List<? extends FirValueParameter> list2) {
        list.clear();
        List<FirValueParameter> list3 = list;
        for (FirValueParameter firValueParameter : list2) {
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setSource(firValueParameter.getSource());
            firValueParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firValueParameter));
            firValueParameterBuilder.setModuleData(firValueParameter.getModuleData());
            firValueParameterBuilder.setOrigin(firValueParameter.getOrigin());
            firValueParameterBuilder.setAttributes(firValueParameter.getAttributes().copy());
            firValueParameterBuilder.setReturnTypeRef(firValueParameter.getReturnTypeRef());
            firValueParameterBuilder.setName(firValueParameter.getName());
            firValueParameterBuilder.getAnnotations().addAll(firValueParameter.getAnnotations());
            firValueParameterBuilder.setDefaultValue(firValueParameter.getDefaultValue());
            firValueParameterBuilder.setContainingDeclarationSymbol(firValueParameter.getContainingDeclarationSymbol());
            firValueParameterBuilder.setCrossinline(firValueParameter.getIsCrossinline());
            firValueParameterBuilder.setNoinline(firValueParameter.getIsNoinline());
            firValueParameterBuilder.setVararg(firValueParameter.getIsVararg());
            firValueParameterBuilder.setValueParameterKind(firValueParameter.getValueParameterKind());
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
            firValueParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(generatedDeclarationKey));
            firValueParameterBuilder.setResolvePhase(firResolvePhase);
            list3.add(firValueParameterBuilder.mo288build());
        }
    }
}
