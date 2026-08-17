package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\"I\u0010\u0007\u001a\n\u0012\u0004\u0012\u0002H\b\u0018\u00010\u0006\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"buildImportedVersion", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "importedClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "<set-?>", "Lorg/jetbrains/kotlin/fir/scopes/impl/ImportedFromObjectOrStaticData;", "importedFromObjectOrStaticData", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getImportedFromObjectOrStaticData", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/scopes/impl/ImportedFromObjectOrStaticData;", "setImportedFromObjectOrStaticData", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/scopes/impl/ImportedFromObjectOrStaticData;)V", "importedFromObjectOrStaticData$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAbstractImportingScopeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirAbstractImportingScopeKt.class, "importedFromObjectOrStaticData", "getImportedFromObjectOrStaticData(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/scopes/impl/ImportedFromObjectOrStaticData;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor importedFromObjectOrStaticData$delegate = FirDeclarationDataRegistry.INSTANCE.data(ImportedFromObjectOrStaticClassIdKey.INSTANCE);

    public static final FirVariable buildImportedVersion(FirVariable firVariable, ClassId classId) {
        firVariable.getClass();
        classId.getClass();
        if (!(firVariable instanceof FirProperty)) {
            if (!(firVariable instanceof FirField)) {
                if (firVariable instanceof FirEnumEntry) {
                    return firVariable;
                }
                co4.a("Unexpected variable in buildImportedCopy: ", UtilsKt.render(firVariable), " of type ", firVariable.getClass());
                return null;
            }
            FirField firField = (FirField) firVariable;
            FirFieldBuilder firFieldBuilder = new FirFieldBuilder();
            firFieldBuilder.setSource(firField.getSource());
            firFieldBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firField));
            firFieldBuilder.setModuleData(firField.getModuleData());
            firFieldBuilder.setOrigin(firField.getOrigin());
            firFieldBuilder.setAttributes(firField.getAttributes().copy());
            firFieldBuilder.setStatus(firField.getStatus());
            firFieldBuilder.setLocal(firField.getIsLocal());
            firFieldBuilder.setReturnTypeRef(firField.getReturnTypeRef());
            firFieldBuilder.setDeprecationsProvider(firField.getDeprecationsProvider());
            firFieldBuilder.setDispatchReceiverType(firField.getDispatchReceiverType());
            firFieldBuilder.setName(firField.getName());
            firFieldBuilder.setInitializer(firField.getInitializer());
            firFieldBuilder.setVar(firField.getIsVar());
            firFieldBuilder.getAnnotations().addAll(firField.getAnnotations());
            firFieldBuilder.setOrigin(FirDeclarationOrigin.ImportedFromObjectOrStatic.INSTANCE);
            firFieldBuilder.setSymbol(new FirFieldSymbol(new CallableId(classId, firFieldBuilder.getName())));
            FirField firFieldBuild = firFieldBuilder.mo288build();
            setImportedFromObjectOrStaticData(firFieldBuild, new ImportedFromObjectOrStaticData(classId, firVariable));
            return firFieldBuild;
        }
        FirProperty firProperty = (FirProperty) firVariable;
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
        firPropertyBuilder.setOrigin(FirDeclarationOrigin.ImportedFromObjectOrStatic.INSTANCE);
        firPropertyBuilder.setSymbol(new FirRegularPropertySymbol(new CallableId(classId, firPropertyBuilder.getName())));
        firPropertyBuilder.setDelegateFieldSymbol(null);
        FirProperty firPropertyBuild = firPropertyBuilder.mo288build();
        setImportedFromObjectOrStaticData(firPropertyBuild, new ImportedFromObjectOrStaticData(classId, firVariable));
        return firPropertyBuild;
    }

    public static final <D extends FirCallableDeclaration> ImportedFromObjectOrStaticData<D> getImportedFromObjectOrStaticData(D d) {
        d.getClass();
        return (ImportedFromObjectOrStaticData) importedFromObjectOrStaticData$delegate.getValue(d, $$delegatedProperties[0]);
    }

    public static final <D extends FirCallableDeclaration> void setImportedFromObjectOrStaticData(D d, ImportedFromObjectOrStaticData<D> importedFromObjectOrStaticData) {
        d.getClass();
        importedFromObjectOrStaticData$delegate.setValue(d, $$delegatedProperties[0], importedFromObjectOrStaticData);
    }

    public static final FirNamedFunction buildImportedVersion(FirNamedFunction firNamedFunction, ClassId classId) {
        firNamedFunction.getClass();
        classId.getClass();
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
        firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.ImportedFromObjectOrStatic.INSTANCE);
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(classId, firNamedFunctionBuilder.getName())));
        FirNamedFunction firNamedFunctionBuild = firNamedFunctionBuilder.mo288build();
        setImportedFromObjectOrStaticData(firNamedFunctionBuild, new ImportedFromObjectOrStaticData(classId, firNamedFunction));
        return firNamedFunctionBuild;
    }
}
