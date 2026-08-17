package org.jetbrains.kotlin.fir.plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveState;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.plugin.PropertyBuildingContext;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0018\u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000f0\f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fJ \u0010\u001e\u001a\u00020\u001d2\u0018\u0010 \u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\r\u0012\u0004\u0012\u00020\u000f0\fJ\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u0018J\b\u0010#\u001a\u00020\u0002H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0019\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\r\u0012\u0004\u0012\u00020\u000f\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/PropertyBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "returnTypeProvider", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isVal", Argument.Delimiters.none, "hasBackingField", "containingFileName", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/name/CallableId;Lkotlin/jvm/functions/Function1;ZZLjava/lang/String;)V", "setterVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "extensionReceiverTypeProvider", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "generateDefaultInitializer", "withGeneratedDefaultInitializer", Argument.Delimiters.none, "extensionReceiverType", ModuleXmlParser.TYPE, "typeProvider", "setter", "visibility", "build", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertyBuildingContext extends DeclarationBuildingContext<FirProperty> {
    private final CallableId callableId;
    private final String containingFileName;
    private Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> extensionReceiverTypeProvider;
    private boolean generateDefaultInitializer;
    private final boolean hasBackingField;
    private final boolean isVal;
    private final Function1<List<? extends FirTypeParameterRef>, ConeKotlinType> returnTypeProvider;
    private Visibility setterVisibility;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PropertyBuildingContext(FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol<?> firClassSymbol, CallableId callableId, Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> function1, boolean z, boolean z2, String str) {
        super(firSession, generatedDeclarationKey, firClassSymbol, null);
        firSession.getClass();
        generatedDeclarationKey.getClass();
        callableId.getClass();
        function1.getClass();
        this.callableId = callableId;
        this.returnTypeProvider = function1;
        this.isVal = z;
        this.hasBackingField = z2;
        this.containingFileName = str;
    }

    public static ConeKotlinType c(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    public FirProperty build() throws UninitializedPropertyAccessException {
        ConeKotlinType coneKotlinType;
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firPropertyBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firPropertyBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(getKey()));
        firPropertyBuilder.setSource(getSourceForFirDeclaration());
        firPropertyBuilder.setSymbol(new FirRegularPropertySymbol(this.callableId));
        firPropertyBuilder.setName(this.callableId.getCallableName());
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImplGenerateStatus = generateStatus();
        firPropertyBuilder.setStatus(firResolvedDeclarationStatusImplGenerateStatus);
        FirClassSymbol<?> owner = getOwner();
        boolean z = false;
        if (owner != null && ((FirClassLikeDeclaration) owner.getFir()).getIsLocal()) {
            z = true;
        }
        firPropertyBuilder.setLocal(z);
        FirClassSymbol<?> owner2 = getOwner();
        firPropertyBuilder.setDispatchReceiverType(owner2 != null ? ScopeUtilsKt.defaultType(owner2) : null);
        List<DeclarationBuildingContext.TypeParameterData> typeParameters = getTypeParameters();
        List<FirTypeParameter> typeParameters2 = firPropertyBuilder.getTypeParameters();
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            typeParameters2.add(generateTypeParameter((DeclarationBuildingContext.TypeParameterData) it.next(), firPropertyBuilder.getSymbol()));
        }
        initTypeParameterBounds(firPropertyBuilder.getTypeParameters(), firPropertyBuilder.getTypeParameters());
        firPropertyBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default((ConeKotlinType) this.returnTypeProvider.invoke(firPropertyBuilder.getTypeParameters()), null, null, 3, null));
        Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> function1 = this.extensionReceiverTypeProvider;
        if (function1 != null && (coneKotlinType = (ConeKotlinType) function1.invoke(firPropertyBuilder.getTypeParameters())) != null) {
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, null, null, 3, null));
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
            firReceiverParameterBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(getKey()));
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firPropertyBuilder.getSymbol());
            firPropertyBuilder.setReceiverParameter(firReceiverParameterBuilder.mo288build());
        }
        produceContextReceiversTo(firPropertyBuilder.getContextParameters(), firPropertyBuilder.getTypeParameters(), firPropertyBuilder.getOrigin(), firPropertyBuilder.getSymbol());
        firPropertyBuilder.setVar(!this.isVal);
        FirModuleData moduleData = FirModuleDataKt.getModuleData(getSession());
        FirDeclarationOrigin origin = FirDeclarationOriginKt.getOrigin(getKey());
        FirTypeRef returnTypeRef = firPropertyBuilder.getReturnTypeRef();
        Visibility visibility = firPropertyBuilder.getStatus().getVisibility();
        FirPropertySymbol symbol = firPropertyBuilder.getSymbol();
        Modality modality = firResolvedDeclarationStatusImplGenerateStatus.getModality();
        EffectiveVisibility effectiveVisibility = firResolvedDeclarationStatusImplGenerateStatus.getEffectiveVisibility();
        FirResolvePhase firResolvePhase = FirResolvePhase.BODY_RESOLVE;
        firPropertyBuilder.setGetter(new FirDefaultPropertyGetter(null, moduleData, origin, returnTypeRef, visibility, symbol, modality, effectiveVisibility, false, false, null, firResolvePhase, null, 5888, null));
        if (firPropertyBuilder.isVar()) {
            FirModuleData moduleData2 = FirModuleDataKt.getModuleData(getSession());
            FirDeclarationOrigin origin2 = FirDeclarationOriginKt.getOrigin(getKey());
            FirTypeRef returnTypeRef2 = firPropertyBuilder.getReturnTypeRef();
            Visibility visibility2 = this.setterVisibility;
            if (visibility2 == null) {
                visibility2 = firPropertyBuilder.getStatus().getVisibility();
            }
            FirDefaultPropertySetter firDefaultPropertySetter = new FirDefaultPropertySetter(null, moduleData2, origin2, returnTypeRef2, visibility2, firPropertyBuilder.getSymbol(), firResolvedDeclarationStatusImplGenerateStatus.getModality(), firResolvedDeclarationStatusImplGenerateStatus.getEffectiveVisibility(), false, false, null, null, null, firResolvePhase, null, 24320, null);
            firResolvePhase = firResolvePhase;
            firPropertyBuilder.setSetter(firDefaultPropertySetter);
        } else if (this.setterVisibility != null) {
            w01.a("isVar = false but setterVisibility is specified. Did you forget to set isVar = true?");
            return null;
        }
        if (this.hasBackingField) {
            firPropertyBuilder.setBackingField(new FirDefaultPropertyBackingField(FirModuleDataKt.getModuleData(getSession()), FirDeclarationOriginKt.getOrigin(getKey()), null, new ArrayList(), firPropertyBuilder.getReturnTypeRef(), firPropertyBuilder.isVar(), firPropertyBuilder.getSymbol(), firPropertyBuilder.getStatus(), firResolvePhase));
        }
        if (this.generateDefaultInitializer && this.hasBackingField && getModality() != Modality.ABSTRACT) {
            firPropertyBuilder.setInitializer(generateExpressionStub());
        }
        firPropertyBuilder.setBodyResolveState(FirPropertyBodyResolveState.ALL_BODIES_RESOLVED);
        FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
        if (this.containingFileName == null || this.callableId.getClassId() == null) {
            DeclarationAttributesKt.setFileNameForPluginGeneratedCallable(firPropertyMo288build, this.containingFileName);
            return firPropertyMo288build;
        }
        wec.a("containingFileName could be set only for top-level declarations, but ", this.callableId, " is a member");
        return null;
    }

    public final void extensionReceiverType(final ConeKotlinType type) {
        type.getClass();
        extensionReceiverType(new Function1() { // from class: cgb
            public final Object invoke(Object obj) {
                return PropertyBuildingContext.c(type, (List) obj);
            }
        });
    }

    public final void setter(Visibility visibility) {
        visibility.getClass();
        this.setterVisibility = visibility;
    }

    public final void withGeneratedDefaultInitializer() {
        this.generateDefaultInitializer = true;
    }

    public final void extensionReceiverType(Function1<? super List<? extends FirTypeParameter>, ? extends ConeKotlinType> typeProvider) {
        typeProvider.getClass();
        this.extensionReceiverTypeProvider = typeProvider;
    }
}
