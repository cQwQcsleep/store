package org.jetbrains.kotlin.fir.plugin;

import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructedClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.plugin.ConstructorBuildingContext;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u0002H\u0016J[\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000f0\u0016¢\u0006\u0002\b\u0018H\u0017b\u0018\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\n\b\u001c\u0012\u0006\b\n0\u001d8\u001eJ*\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0017b\u0018\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\n\b\u001c\u0012\u0006\b\n0\u001d8\u001eJ<\u0010\u001f\u001a\u00020\u000f2\u0018\u0010\"\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#\u0012\u0004\u0012\u00020!0\u0016H\u0017b\u0018\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\n\b\u001c\u0012\u0006\b\n0\u001d8\u001eR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/ConstructorBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/FunctionBuildingContext;", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "isPrimary", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Z)V", "build", "typeParameter", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "isReified", "config", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext$TypeParameterBuildingContext;", "Lkotlin/ExtensionFunctionType;", "Lkotlin/Deprecated;", "message", "This function does nothing and should not be called", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "contextReceiver", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeProvider", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstructorBuildingContext extends FunctionBuildingContext<FirConstructor> {
    private final boolean isPrimary;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstructorBuildingContext(FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol<?> firClassSymbol, boolean z) {
        super(FqNamesUtilKt.callableIdForConstructor(firClassSymbol.getClassId()), firSession, generatedDeclarationKey, firClassSymbol, null);
        firSession.getClass();
        generatedDeclarationKey.getClass();
        firClassSymbol.getClass();
        this.isPrimary = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit d(ConstructorBuildingContext constructorBuildingContext, FirAbstractConstructorBuilder firAbstractConstructorBuilder) {
        FirClassSymbol<?> classSymbol;
        firAbstractConstructorBuilder.getClass();
        firAbstractConstructorBuilder.setSymbol(new FirConstructorSymbol(constructorBuildingContext.getOwner().getClassId()));
        firAbstractConstructorBuilder.setSource(constructorBuildingContext.getSourceForFirDeclaration());
        firAbstractConstructorBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firAbstractConstructorBuilder.setModuleData(FirModuleDataKt.getModuleData(constructorBuildingContext.getSession()));
        firAbstractConstructorBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(constructorBuildingContext.getKey()));
        List<FirTypeParameterSymbol> typeParameterSymbols = constructorBuildingContext.getOwner().getTypeParameterSymbols();
        List<FirTypeParameterRef> typeParameters = firAbstractConstructorBuilder.getTypeParameters();
        for (FirTypeParameterSymbol firTypeParameterSymbol : typeParameterSymbols) {
            FirConstructedClassTypeParameterRefBuilder firConstructedClassTypeParameterRefBuilder = new FirConstructedClassTypeParameterRefBuilder();
            firConstructedClassTypeParameterRefBuilder.setSymbol(firTypeParameterSymbol);
            typeParameters.add(firConstructedClassTypeParameterRefBuilder.build());
        }
        firAbstractConstructorBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(ScopeUtilsKt.defaultType(constructorBuildingContext.getOwner()), null, null, 3, null));
        firAbstractConstructorBuilder.setStatus(constructorBuildingContext.generateStatus());
        firAbstractConstructorBuilder.setLocal(((FirClassLikeDeclaration) constructorBuildingContext.getOwner().getFir()).getIsLocal());
        if (constructorBuildingContext.getOwner().getRawStatus().isInner()) {
            ConeClassLikeLookupTag containingClassLookupTag = ClassMembersKt.getContainingClassLookupTag(constructorBuildingContext.getOwner());
            if (containingClassLookupTag == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(containingClassLookupTag, constructorBuildingContext.getSession())) == null) {
                b88.a("Symbol for parent of ", constructorBuildingContext.getOwner(), " not found");
                return null;
            }
            firAbstractConstructorBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(classSymbol));
        }
        List<FunctionBuildingContext.ValueParameterData> valueParameters = constructorBuildingContext.getValueParameters();
        List<FirValueParameter> valueParameters2 = firAbstractConstructorBuilder.getValueParameters();
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            valueParameters2.add(constructorBuildingContext.generateValueParameter((FunctionBuildingContext.ValueParameterData) it.next(), firAbstractConstructorBuilder.getSymbol(), firAbstractConstructorBuilder.getTypeParameters()));
        }
        if (constructorBuildingContext.getOwner() instanceof FirRegularClassSymbol) {
            List<FirValueParameter> resolvedContextParameters = ((FirRegularClassSymbol) constructorBuildingContext.getOwner()).getResolvedContextParameters();
            List<FirValueParameter> contextParameters = firAbstractConstructorBuilder.getContextParameters();
            for (FirValueParameter firValueParameter : resolvedContextParameters) {
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
                firValueParameterBuilder.setContainingDeclarationSymbol(constructorBuildingContext.getOwner());
                contextParameters.add(firValueParameterBuilder.mo288build());
            }
        }
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    public FirConstructor build() {
        FirConstructor firConstructorMo288build;
        if (getOwner() == null) {
            w01.a("Required value was null.");
            return null;
        }
        Function1 function1 = new Function1() { // from class: et2
            public final Object invoke(Object obj) {
                return ConstructorBuildingContext.d(this.b, (FirAbstractConstructorBuilder) obj);
            }
        };
        if (this.isPrimary) {
            FirPrimaryConstructorBuilder firPrimaryConstructorBuilder = new FirPrimaryConstructorBuilder();
            function1.invoke(firPrimaryConstructorBuilder);
            firConstructorMo288build = firPrimaryConstructorBuilder.mo288build();
        } else {
            FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
            function1.invoke(firConstructorBuilder);
            firConstructorMo288build = firConstructorBuilder.mo288build();
        }
        ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorMo288build, getOwner().getLookupTag());
        return firConstructorMo288build;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This function does nothing and should not be called")
    public /* synthetic */ void contextReceiver(ConeKotlinType type) throws KotlinNothingValueException {
        type.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This function does nothing and should not be called")
    public /* synthetic */ void typeParameter(Name name, Variance variance, boolean isReified, GeneratedDeclarationKey key, Function1 config) throws KotlinNothingValueException {
        name.getClass();
        variance.getClass();
        key.getClass();
        config.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This function does nothing and should not be called")
    public /* synthetic */ void contextReceiver(Function1 typeProvider) throws KotlinNothingValueException {
        typeProvider.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }
}
