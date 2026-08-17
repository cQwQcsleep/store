package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructedClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e0\u0010H\u0016J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0017b\u0002\b\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorsSubstitutingScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "typeAliasSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "delegatingScope", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/FirSession;)V", "aliasedTypeExpansionGloballyEnabled", Argument.Delimiters.none, "typealiasConstructorStorage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypealiasConstructorStorage;", "processDeclaredConstructors", Argument.Delimiters.none, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "createTypealiasConstructor", "originalConstructorSymbol", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeAliasConstructorsSubstitutingScope extends FirScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean aliasedTypeExpansionGloballyEnabled;
    private final FirScope delegatingScope;
    private final FirSession session;
    private final FirTypeAliasSymbol typeAliasSymbol;
    private final FirTypealiasConstructorStorage typealiasConstructorStorage;

    private TypeAliasConstructorsSubstitutingScope(FirTypeAliasSymbol firTypeAliasSymbol, FirScope firScope, FirSession firSession) {
        this.typeAliasSymbol = firTypeAliasSymbol;
        this.delegatingScope = firScope;
        this.session = firSession;
        this.aliasedTypeExpansionGloballyEnabled = ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(AnalysisFlags.getExpandTypeAliasesInTypeResolution())).booleanValue();
        this.typealiasConstructorStorage = TypeAliasConstructorsSubstitutingScopeKt.getTypealiasConstructorsStorage(firSession);
    }

    public static Unit b(TypeAliasConstructorsSubstitutingScope typeAliasConstructorsSubstitutingScope, Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        function1.invoke(typeAliasConstructorsSubstitutingScope.typealiasConstructorStorage.getCachedConstructors().getValue(TuplesKt.to(typeAliasConstructorsSubstitutingScope.typeAliasSymbol, firConstructorSymbol), typeAliasConstructorsSubstitutingScope));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:55:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:57:0x02ea  */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirConstructorSymbol createTypealiasConstructor(FirConstructorSymbol originalConstructorSymbol) {
        ConeClassLikeType coneClassLikeTypeConstructType$default;
        boolean z;
        FirClassLikeSymbol<?> containingClass;
        int i;
        FirRegularClass firRegularClass;
        List<FirTypeParameterRef> typeParameters;
        originalConstructorSymbol.getClass();
        FirConstructor firConstructor = (FirConstructor) originalConstructorSymbol.getFir();
        FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(originalConstructorSymbol.getCallableId());
        FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
        firConstructorBuilder.setSymbol(firConstructorSymbol);
        firConstructorBuilder.setSource(this.typeAliasSymbol.getSource());
        firConstructorBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firConstructor));
        firConstructorBuilder.setModuleData(this.typeAliasSymbol.getModuleData());
        firConstructorBuilder.setOrigin(FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE);
        firConstructorBuilder.setAttributes(firConstructor.getAttributes().copy());
        List<FirTypeParameterRef> typeParameters2 = ((FirTypeAlias) this.typeAliasSymbol.getFir()).getTypeParameters();
        List<FirTypeParameterRef> typeParameters3 = firConstructorBuilder.getTypeParameters();
        for (FirTypeParameterRef firTypeParameterRef : typeParameters2) {
            FirConstructedClassTypeParameterRefBuilder firConstructedClassTypeParameterRefBuilder = new FirConstructedClassTypeParameterRefBuilder();
            firConstructedClassTypeParameterRefBuilder.setSymbol(firTypeParameterRef.getSymbol());
            typeParameters3.add(firConstructedClassTypeParameterRefBuilder.build());
        }
        firConstructorBuilder.setStatus(firConstructor.getStatus());
        firConstructorBuilder.setLocal(firConstructor.getIsLocal());
        firConstructorBuilder.setDeprecationsProvider(firConstructor.getDeprecationsProvider());
        firConstructorBuilder.setContainerSource(firConstructor.getContainerSource());
        List<FirValueParameter> contextParameters = firConstructor.getContextParameters();
        List<FirValueParameter> contextParameters2 = firConstructorBuilder.getContextParameters();
        for (FirValueParameter firValueParameter : contextParameters) {
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
            firValueParameterBuilder.setModuleData(this.typeAliasSymbol.getModuleData());
            firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE);
            firValueParameterBuilder.setContainingDeclarationSymbol(firConstructorSymbol);
            contextParameters2.add(firValueParameterBuilder.mo288build());
        }
        List<FirValueParameter> valueParameters = firConstructor.getValueParameters();
        List<FirValueParameter> valueParameters2 = firConstructorBuilder.getValueParameters();
        for (FirValueParameter firValueParameter2 : valueParameters) {
            FirValueParameterBuilder firValueParameterBuilder2 = new FirValueParameterBuilder();
            firValueParameterBuilder2.setSource(firValueParameter2.getSource());
            firValueParameterBuilder2.setResolvePhase(FirResolveStateKt.getResolvePhase(firValueParameter2));
            firValueParameterBuilder2.setModuleData(firValueParameter2.getModuleData());
            firValueParameterBuilder2.setOrigin(firValueParameter2.getOrigin());
            firValueParameterBuilder2.setAttributes(firValueParameter2.getAttributes().copy());
            firValueParameterBuilder2.setReturnTypeRef(firValueParameter2.getReturnTypeRef());
            firValueParameterBuilder2.setName(firValueParameter2.getName());
            firValueParameterBuilder2.getAnnotations().addAll(firValueParameter2.getAnnotations());
            firValueParameterBuilder2.setDefaultValue(firValueParameter2.getDefaultValue());
            firValueParameterBuilder2.setContainingDeclarationSymbol(firValueParameter2.getContainingDeclarationSymbol());
            firValueParameterBuilder2.setCrossinline(firValueParameter2.getIsCrossinline());
            firValueParameterBuilder2.setNoinline(firValueParameter2.getIsNoinline());
            firValueParameterBuilder2.setVararg(firValueParameter2.getIsVararg());
            firValueParameterBuilder2.setValueParameterKind(firValueParameter2.getValueParameterKind());
            firValueParameterBuilder2.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder2.setModuleData(this.typeAliasSymbol.getModuleData());
            firValueParameterBuilder2.setOrigin(FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE);
            firValueParameterBuilder2.setContainingDeclarationSymbol(firConstructorSymbol);
            valueParameters2.add(firValueParameterBuilder2.mo288build());
        }
        FirResolvedTypeRef returnTypeRef = firConstructor.getReturnTypeRef();
        if (this.aliasedTypeExpansionGloballyEnabled) {
            returnTypeRef = TypeUtilsKt.withReplacedConeType$default(returnTypeRef, TypeUtilsKt.withAbbreviation(FirTypeUtilsKt.getConeType(returnTypeRef), new AbbreviatedTypeAttribute(ScopeUtilsKt.defaultType(this.typeAliasSymbol))), null, 2, null);
        }
        firConstructorBuilder.setReturnTypeRef(returnTypeRef);
        firConstructorBuilder.setContractDescription(firConstructor.getContractDescription());
        firConstructorBuilder.getAnnotations().addAll(firConstructor.getAnnotations());
        firConstructorBuilder.setDelegatedConstructor(firConstructor.getDelegatedConstructor());
        firConstructorBuilder.setBody(firConstructor.getBody());
        ConeKotlinType coneType = this.typeAliasSymbol.getResolvedExpandedTypeRef().getConeType();
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType != null) {
            FirSession firSession = this.session;
            ConeClassLikeType coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null);
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeTypeFullyExpandedType$default.getLookupTag(), firSession);
            if (symbol != null && ((!((z = symbol instanceof FirRegularClassSymbol)) || ((FirMemberDeclaration) ((FirRegularClassSymbol) symbol).getFir()).getStatus().isInner()) && (containingClass = FirProviderKt.getFirProvider(this.session).getContainingClass(symbol)) != null)) {
                FirRegularClassSymbol firRegularClassSymbol = z ? (FirRegularClassSymbol) symbol : null;
                if (firRegularClassSymbol == null || (firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir()) == null || (typeParameters = firRegularClass.getTypeParameters()) == null) {
                    i = 0;
                } else {
                    List<FirTypeParameterRef> list = typeParameters;
                    if ((list instanceof Collection) && list.isEmpty()) {
                        i = 0;
                    } else {
                        Iterator<T> it = list.iterator();
                        i = 0;
                        while (it.hasNext()) {
                            if ((((FirTypeParameterRef) it.next()) instanceof FirTypeParameter) && (i = i + 1) < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                }
                coneClassLikeTypeConstructType$default = TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) containingClass, (ConeTypeProjection[]) ArraysKt.drop(coneClassLikeTypeFullyExpandedType$default.getTypeArguments(), i).toArray(new ConeTypeProjection[0]), false, (ConeAttributes) null, 6, (Object) null);
            } else {
                coneClassLikeTypeConstructType$default = null;
            }
        } else {
            coneClassLikeTypeConstructType$default = null;
        }
        if (coneClassLikeTypeConstructType$default != null) {
            FirTypeRef firTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(((FirConstructor) originalConstructorSymbol.getFir()).getReturnTypeRef(), coneClassLikeTypeConstructType$default, null, 2, null);
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setTypeRef(firTypeRefWithReplacedConeType$default);
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuilder.setModuleData(this.typeAliasSymbol.getModuleData());
            firReceiverParameterBuilder.setOrigin(FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE);
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firConstructorSymbol);
            firConstructorBuilder.setReceiverParameter(firReceiverParameterBuilder.mo288build());
        }
        firConstructorBuilder.setDispatchReceiverType(null);
        FirConstructor firConstructorMo288build = firConstructorBuilder.mo288build();
        FirTypeAliasSymbol firTypeAliasSymbol = this.typeAliasSymbol;
        FirScope firScope = this.delegatingScope;
        FirClassSubstitutionScope firClassSubstitutionScope = firScope instanceof FirClassSubstitutionScope ? (FirClassSubstitutionScope) firScope : null;
        TypeAliasConstructorsSubstitutingScopeKt.setTypeAliasConstructorInfo(firConstructorMo288build, new TypeAliasConstructorInfo(firConstructor, firTypeAliasSymbol, firClassSubstitutionScope != null ? firClassSubstitutionScope.getSubstitutor() : null));
        return firConstructorSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.delegatingScope.processDeclaredConstructors(new Function1() { // from class: rse
            public final Object invoke(Object obj) {
                return TypeAliasConstructorsSubstitutingScope.b(this.b, processor, (FirConstructorSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public TypeAliasConstructorsSubstitutingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirScope firScopeWithReplacedSessionOrNull = this.delegatingScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firScopeWithReplacedSessionOrNull != null) {
            return new TypeAliasConstructorsSubstitutingScope(this.typeAliasSymbol, firScopeWithReplacedSessionOrNull, this.session);
        }
        return null;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007b\u0002\b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorsSubstitutingScope$Companion;", Argument.Delimiters.none, "<init>", "()V", "initialize", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "typeAliasSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @FirImplementationDetail
        public final FirScope initialize(FirTypeAliasSymbol typeAliasSymbol, FirSession session, ScopeSession scopeSession) {
            typeAliasSymbol.getClass();
            session.getClass();
            scopeSession.getClass();
            DefaultConstructorMarker defaultConstructorMarker = null;
            FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(TypeExpansionUtilsKt.fullyExpandedType$default(typeAliasSymbol.getResolvedExpandedTypeRef().getConeType(), session, (Function1) null, 2, (Object) null), session, scopeSession, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
            return firTypeScopeScope == null ? FirTypeScope.Empty.INSTANCE : new TypeAliasConstructorsSubstitutingScope(typeAliasSymbol, firTypeScopeScope, session, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ TypeAliasConstructorsSubstitutingScope(FirTypeAliasSymbol firTypeAliasSymbol, FirScope firScope, FirSession firSession, DefaultConstructorMarker defaultConstructorMarker) {
        this(firTypeAliasSymbol, firScope, firSession);
    }
}
