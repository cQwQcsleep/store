package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext;
import org.jetbrains.kotlin.backend.jvm.JvmIrAttributesKt;
import org.jetbrains.kotlin.backend.jvm.metadata.MetadataSerializer;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassBuilderMode;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JvmDefaultModeKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyAccessorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.fir.serialization.TypeApproximatorForMetadataSerializer;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.util.MetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u001aR\u0010\u0011\u001a\u00020\u00012\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\u001aF\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010!\u001a\u00020\"H\u0000\u001a\u0014\u0010#\u001a\u00020$*\u00020%2\u0006\u0010\u001f\u001a\u00020 H\u0002\u001a\u001c\u0010&\u001a\u00020'*\u00020'2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0002\u001a\u0014\u0010*\u001a\u00020+*\u00020+2\u0006\u0010\u001f\u001a\u00020 H\u0000\u001a*\u0010,\u001a\u00020-*\u00020-2\u0006\u0010\u001f\u001a\u00020 2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000202H\u0000\u001a\u001a\u00103\u001a\u000204*\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u0002000/H\u0002¨\u00067"}, d2 = {"makeFirMetadataSerializerForIrClass", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirMetadataSerializer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "context", "Lorg/jetbrains/kotlin/backend/jvm/JvmBackendContext;", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "serializationBindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "parent", "Lorg/jetbrains/kotlin/backend/jvm/metadata/MetadataSerializer;", "actualizedExpectDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "makeLocalFirMetadataSerializerForMetadataSource", "metadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "globalSerializationBindings", "targetId", "Lorg/jetbrains/kotlin/modules/TargetId;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "makeElementSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serializerExtension", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmSerializerExtension;", "approximator", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "copyToFreeAnonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "copyToFreeAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "newPropertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "copyToFreeProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "approximated", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "typeParameterSet", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "toSuper", Argument.Delimiters.none, "collectTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "c", "org.jetbrains.kotlin:jvm-backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMetadataSerializerKt {
    public static final FirTypeRef approximated(FirTypeRef firTypeRef, AbstractTypeApproximator abstractTypeApproximator, Collection<FirTypeParameter> collection, boolean z) {
        firTypeRef.getClass();
        abstractTypeApproximator.getClass();
        collection.getClass();
        KotlinTypeMarker kotlinTypeMarkerApproximateToSuperType$default = z ? AbstractTypeApproximator.approximateToSuperType$default(abstractTypeApproximator, FirTypeUtilsKt.getConeType(firTypeRef), TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE, (Map) null, 4, (Object) null) : AbstractTypeApproximator.approximateToSubType$default(abstractTypeApproximator, FirTypeUtilsKt.getConeType(firTypeRef), TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE, (Map) null, 4, (Object) null);
        FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firTypeRef, kotlinTypeMarkerApproximateToSuperType$default instanceof ConeKotlinType ? (ConeKotlinType) kotlinTypeMarkerApproximateToSuperType$default : null, null, 2, null);
        collectTypeParameters(firResolvedTypeRefWithReplacedConeType$default.getConeType(), collection);
        return firResolvedTypeRefWithReplacedConeType$default;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void collectTypeParameters(ConeKotlinType coneKotlinType, Collection<FirTypeParameter> collection) {
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            collectTypeParameters(coneFlexibleType.getLowerBound(), collection);
            collectTypeParameters(coneFlexibleType.getUpperBound(), collection);
        } else {
            if (!(coneKotlinType instanceof ConeClassLikeType)) {
                if (coneKotlinType instanceof ConeTypeParameterType) {
                    collection.add(((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getFir());
                    return;
                }
                return;
            }
            for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                    collectTypeParameters(coneKotlinTypeProjection.getType(), collection);
                }
            }
        }
    }

    private static final FirPropertyAccessor copyToFreeAccessor(FirPropertyAccessor firPropertyAccessor, AbstractTypeApproximator abstractTypeApproximator, FirPropertySymbol firPropertySymbol) {
        FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
        Set mutableSet = CollectionsKt.toMutableSet(firPropertyAccessor.getTypeParameters());
        firPropertyAccessorBuilder.setModuleData(firPropertyAccessor.getModuleData());
        firPropertyAccessorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firPropertyAccessorBuilder.setSource(firPropertyAccessor.getSource());
        Set set = mutableSet;
        firPropertyAccessorBuilder.setReturnTypeRef(approximated(firPropertyAccessor.getReturnTypeRef(), abstractTypeApproximator, set, true));
        firPropertyAccessorBuilder.setSymbol(new FirPropertyAccessorSymbol());
        firPropertyAccessorBuilder.setPropertySymbol(firPropertySymbol);
        firPropertyAccessorBuilder.setGetter(firPropertyAccessor.getIsGetter());
        firPropertyAccessorBuilder.setStatus(firPropertyAccessor.getStatus());
        List<FirValueParameter> valueParameters = firPropertyAccessor.getValueParameters();
        List<FirValueParameter> valueParameters2 = firPropertyAccessorBuilder.getValueParameters();
        for (FirValueParameter firValueParameter : valueParameters) {
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
            firValueParameterBuilder.setReturnTypeRef(approximated(firValueParameter.getReturnTypeRef(), abstractTypeApproximator, set, false));
            valueParameters2.add(firValueParameterBuilder.mo288build());
        }
        CollectionsKt.addAll(firPropertyAccessorBuilder.getAnnotations(), firPropertyAccessor.getAnnotations());
        return firPropertyAccessorBuilder.mo288build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirAnonymousFunction copyToFreeAnonymousFunction(FirFunction firFunction, AbstractTypeApproximator abstractTypeApproximator) {
        FirReceiverParameter firReceiverParameterMo288build;
        FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
        List<FirTypeParameterRef> typeParameters = firFunction.getTypeParameters();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : typeParameters) {
            if (obj instanceof FirTypeParameter) {
                linkedHashSet.add(obj);
            }
        }
        CollectionsKt.addAll(firAnonymousFunctionBuilder.getAnnotations(), firFunction.getAnnotations());
        firAnonymousFunctionBuilder.setModuleData(firFunction.getModuleData());
        firAnonymousFunctionBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firAnonymousFunctionBuilder.setSource(firFunction.getSource());
        firAnonymousFunctionBuilder.setSymbol(new FirAnonymousFunctionSymbol());
        firAnonymousFunctionBuilder.setReturnTypeRef(approximated(firFunction.getReturnTypeRef(), abstractTypeApproximator, linkedHashSet, true));
        FirReceiverParameter receiverParameter = firFunction.getReceiverParameter();
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
            firReceiverParameterBuilder.setTypeRef(approximated(receiverParameter.getTypeRef(), abstractTypeApproximator, linkedHashSet, false));
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterMo288build = firReceiverParameterBuilder.mo288build();
        } else {
            firReceiverParameterMo288build = null;
        }
        firAnonymousFunctionBuilder.setReceiverParameter(firReceiverParameterMo288build);
        boolean z = firFunction instanceof FirAnonymousFunction;
        FirAnonymousFunction firAnonymousFunction = z ? (FirAnonymousFunction) firFunction : null;
        firAnonymousFunctionBuilder.setLambda(firAnonymousFunction != null && firAnonymousFunction.getIsLambda());
        FirAnonymousFunction firAnonymousFunction2 = z ? (FirAnonymousFunction) firFunction : null;
        firAnonymousFunctionBuilder.setHasExplicitParameterList(firAnonymousFunction2 != null && firAnonymousFunction2.getHasExplicitParameterList());
        List<FirValueParameter> valueParameters = firAnonymousFunctionBuilder.getValueParameters();
        List<FirValueParameter> valueParameters2 = firFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters2, 10));
        for (FirValueParameter firValueParameter : valueParameters2) {
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
            firValueParameterBuilder.setReturnTypeRef(approximated(firValueParameter.getReturnTypeRef(), abstractTypeApproximator, linkedHashSet, false));
            arrayList.add(firValueParameterBuilder.mo288build());
        }
        valueParameters.addAll(arrayList);
        CollectionsKt.addAll(firAnonymousFunctionBuilder.getTypeParameters(), linkedHashSet);
        firAnonymousFunctionBuilder.setStatus(firFunction.getStatus());
        return firAnonymousFunctionBuilder.mo288build();
    }

    public static final FirProperty copyToFreeProperty(FirProperty firProperty, AbstractTypeApproximator abstractTypeApproximator) {
        FirReceiverParameter firReceiverParameterMo288build;
        firProperty.getClass();
        abstractTypeApproximator.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        Set mutableSet = CollectionsKt.toMutableSet(firProperty.getTypeParameters());
        firPropertyBuilder.setModuleData(firProperty.getModuleData());
        firPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        CallableId callableId = firProperty.getSymbol().getCallableId();
        FirPropertySymbol firRegularPropertySymbol = callableId != null ? new FirRegularPropertySymbol(callableId) : new FirLocalPropertySymbol();
        firPropertyBuilder.setSymbol(firRegularPropertySymbol);
        Set set = mutableSet;
        firPropertyBuilder.setReturnTypeRef(approximated(firProperty.getReturnTypeRef(), abstractTypeApproximator, set, true));
        FirReceiverParameter receiverParameter = firProperty.getReceiverParameter();
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
            firReceiverParameterBuilder.setTypeRef(approximated(receiverParameter.getTypeRef(), abstractTypeApproximator, set, false));
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterMo288build = firReceiverParameterBuilder.mo288build();
        } else {
            firReceiverParameterMo288build = null;
        }
        firPropertyBuilder.setReceiverParameter(firReceiverParameterMo288build);
        firPropertyBuilder.setName(firProperty.getName());
        firPropertyBuilder.setInitializer(firProperty.getInitializer());
        firPropertyBuilder.setDelegate(firProperty.getDelegate());
        firPropertyBuilder.setDelegateFieldSymbol(firProperty.getDelegateFieldSymbol() != null ? new FirDelegateFieldSymbol(firRegularPropertySymbol) : null);
        firPropertyBuilder.setSource(firProperty.getSource());
        FirPropertyAccessor getter = firProperty.getGetter();
        firPropertyBuilder.setGetter(getter != null ? copyToFreeAccessor(getter, abstractTypeApproximator, firRegularPropertySymbol) : null);
        FirPropertyAccessor setter = firProperty.getSetter();
        firPropertyBuilder.setSetter(setter != null ? copyToFreeAccessor(setter, abstractTypeApproximator, firRegularPropertySymbol) : null);
        firPropertyBuilder.setVar(firProperty.getIsVar());
        firPropertyBuilder.setStatus(firProperty.getStatus());
        firPropertyBuilder.setLocal(firProperty.getIsLocal());
        firPropertyBuilder.setDispatchReceiverType(firProperty.getDispatchReceiverType());
        firPropertyBuilder.setAttributes(firProperty.getAttributes().copy());
        CollectionsKt.addAll(firPropertyBuilder.getAnnotations(), firProperty.getAnnotations());
        CollectionsKt.addAll(firPropertyBuilder.getTypeParameters(), mutableSet);
        FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
        FirDelegateFieldSymbol delegateFieldSymbol = firPropertyMo288build.getDelegateFieldSymbol();
        if (delegateFieldSymbol != null) {
            delegateFieldSymbol.bind(firPropertyMo288build);
        }
        return firPropertyMo288build;
    }

    public static final FirElementSerializer makeElementSerializer(MetadataSource metadataSource, FirSession firSession, ScopeSession scopeSession, FirJvmSerializerExtension firJvmSerializerExtension, AbstractTypeApproximator abstractTypeApproximator, MetadataSerializer metadataSerializer, LanguageVersionSettings languageVersionSettings) {
        firSession.getClass();
        scopeSession.getClass();
        firJvmSerializerExtension.getClass();
        abstractTypeApproximator.getClass();
        languageVersionSettings.getClass();
        if (metadataSource instanceof FirMetadataSource.Class) {
            FirElementSerializer.Companion companion = FirElementSerializer.INSTANCE;
            FirClass fir = ((FirMetadataSource.Class) metadataSource).getFir();
            FirMetadataSerializer firMetadataSerializer = metadataSerializer instanceof FirMetadataSerializer ? (FirMetadataSerializer) metadataSerializer : null;
            return companion.create(firSession, scopeSession, fir, firJvmSerializerExtension, firMetadataSerializer != null ? firMetadataSerializer.getSerializer() : null, abstractTypeApproximator, languageVersionSettings, (128 & 128) != 0 ? false : false);
        }
        if (metadataSource instanceof FirMetadataSource.File) {
            return FirElementSerializer.Companion.createTopLevel$default(FirElementSerializer.INSTANCE, firSession, scopeSession, firJvmSerializerExtension, abstractTypeApproximator, languageVersionSettings, false, 32, null);
        }
        if (metadataSource instanceof FirMetadataSource.Function) {
            return FirElementSerializer.INSTANCE.createForLambda(firSession, scopeSession, firJvmSerializerExtension, abstractTypeApproximator, languageVersionSettings);
        }
        if (metadataSource instanceof FirMetadataSource.Script) {
            return FirElementSerializer.INSTANCE.createForScript(firSession, scopeSession, ((FirMetadataSource.Script) metadataSource).getFir(), firJvmSerializerExtension, abstractTypeApproximator, languageVersionSettings, (64 & 64) != 0 ? false : false);
        }
        if (metadataSource instanceof FirMetadataSource.ReplSnippet) {
            return FirElementSerializer.INSTANCE.createForSnippet(firSession, scopeSession, ((FirMetadataSource.ReplSnippet) metadataSource).getFir(), firJvmSerializerExtension, abstractTypeApproximator, languageVersionSettings, (64 & 64) != 0 ? false : false);
        }
        return null;
    }

    public static final FirMetadataSerializer makeFirMetadataSerializerForIrClass(FirSession firSession, JvmBackendContext jvmBackendContext, IrClass irClass, JvmSerializationBindings jvmSerializationBindings, Fir2IrComponents fir2IrComponents, MetadataSerializer metadataSerializer, Set<? extends FirDeclaration> set) {
        List listEmptyList;
        FirProperty fir;
        firSession.getClass();
        jvmBackendContext.getClass();
        irClass.getClass();
        jvmSerializationBindings.getClass();
        fir2IrComponents.getClass();
        TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer = new TypeApproximatorForMetadataSerializer(firSession);
        List localDelegatedProperties = JvmIrAttributesKt.getLocalDelegatedProperties(irClass);
        if (localDelegatedProperties != null) {
            listEmptyList = new ArrayList();
            Iterator it = localDelegatedProperties.iterator();
            while (it.hasNext()) {
                MetadataSource metadata = ((IrLocalDelegatedPropertySymbol) it.next()).getOwner().getMetadata();
                FirProperty firPropertyCopyToFreeProperty = null;
                FirMetadataSource.Property property = metadata instanceof FirMetadataSource.Property ? (FirMetadataSource.Property) metadata : null;
                if (property != null && (fir = property.getFir()) != null) {
                    firPropertyCopyToFreeProperty = copyToFreeProperty(fir, typeApproximatorForMetadataSerializer);
                }
                if (firPropertyCopyToFreeProperty != null) {
                    listEmptyList.add(firPropertyCopyToFreeProperty);
                }
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        return new FirMetadataSerializer(jvmBackendContext.getState().getGlobalSerializationBindings(), jvmSerializationBindings, typeApproximatorForMetadataSerializer, makeElementSerializer(irClass.getMetadata(), fir2IrComponents.getSession(), fir2IrComponents.getScopeSession(), new FirJvmSerializerExtension(firSession, jvmSerializationBindings, jvmBackendContext.getState(), listEmptyList, typeApproximatorForMetadataSerializer, fir2IrComponents, new FirJvmElementAwareStringTable(jvmBackendContext.getDefaultTypeMapper(), fir2IrComponents, null, 4, null)), typeApproximatorForMetadataSerializer, metadataSerializer, jvmBackendContext.getConfig().getLanguageVersionSettings()), set);
    }

    public static final FirMetadataSerializer makeLocalFirMetadataSerializerForMetadataSource(MetadataSource metadataSource, FirSession firSession, ScopeSession scopeSession, JvmSerializationBindings jvmSerializationBindings, MetadataSerializer metadataSerializer, TargetId targetId, CompilerConfiguration compilerConfiguration, Set<? extends FirDeclaration> set) {
        firSession.getClass();
        scopeSession.getClass();
        jvmSerializationBindings.getClass();
        targetId.getClass();
        compilerConfiguration.getClass();
        JvmSerializationBindings jvmSerializationBindings2 = new JvmSerializationBindings();
        TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer = new TypeApproximatorForMetadataSerializer(firSession);
        return new FirMetadataSerializer(jvmSerializationBindings, jvmSerializationBindings2, typeApproximatorForMetadataSerializer, makeElementSerializer(metadataSource, firSession, scopeSession, new FirJvmSerializerExtension(firSession, jvmSerializationBindings2, CollectionsKt.emptyList(), scopeSession, jvmSerializationBindings, compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_TYPE_TABLE), targetId.getName(), ClassBuilderMode.FULL, compilerConfiguration.getBoolean(JVMConfigurationKeys.DISABLE_PARAM_ASSERTIONS), FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getApiVersion().compareTo(ApiVersion.KOTLIN_1_4) >= 0 && !compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_UNIFIED_NULL_CHECKS), MetadataHelpersKt.jvmMetadataVersion(compilerConfiguration, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getLanguageVersion()), JvmDefaultModeKt.getJvmDefaultMode(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession)), new FirMetadataSerializerKt$makeLocalFirMetadataSerializerForMetadataSource$stringTable$1(firSession), null), typeApproximatorForMetadataSerializer, metadataSerializer, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration)), set);
    }
}
