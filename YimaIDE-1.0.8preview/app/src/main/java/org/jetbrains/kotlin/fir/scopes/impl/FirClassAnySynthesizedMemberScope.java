package org.jetbrains.kotlin.fir.scopes.impl;

import defpackage.f2f;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassAnySynthesizedMemberScope;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBooleanTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitIntTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNullableAnyTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitStringTypeRef;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u00020\u0001:\u0001:B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ.\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001a0\u001eH\u0016J\u001c\u0010!\u001a\u00020\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001a0\"H\u0016J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0%H\u0016J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001c0%H\u0016J(\u0010'\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\u0010\u001d\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020\u001a0\"H\u0016J$\u0010)\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001a0\"H\u0016J\u0014\u0010+\u001a\u00020,*\u00020*2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0015\u0010-\u001a\u00020*2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b.J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0002J\b\u00102\u001a\u000200H\u0002J\u001e\u00103\u001a\u00020\u001a*\u0002042\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u00105\u001a\u00020,H\u0002J\u001e\u00106\u001a\u0004\u0018\u00010\u00002\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\bH\u0017b\u0002\b9R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassAnySynthesizedMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "declaredMemberScope", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "originForFunctions", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "baseModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "synthesizedCache", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirSynthesizedStorage$SynthesizedCache;", "synthesizedSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "superKlassScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processDeclaredConstructors", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "matchesSomeAnyMember", Argument.Delimiters.none, "generateSyntheticFunctionByName", "generateSyntheticFunctionByName$org_jetbrains_kotlin_providers", "generateEqualsFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "generateHashCodeFunction", "generateToStringFunction", "generateSyntheticFunction", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirNamedFunctionBuilder;", "isOperator", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirClassAnySynthesizedMemberScope extends FirContainingNamesAwareScope {
    private final FirModuleData baseModuleData;
    private final FirContainingNamesAwareScope declaredMemberScope;
    private final ConeClassLikeType dispatchReceiverType;
    private final FirRegularClass klass;
    private final ConeClassLikeLookupTag lookupTag;
    private final FirDeclarationOrigin.Synthetic originForFunctions;
    private final FirSession session;
    private final FirTypeScope superKlassScope;
    private final FirSynthesizedStorage.SynthesizedCache synthesizedCache;
    private final KtSourceElement synthesizedSource;
    private static final HashSet<Name> ANY_MEMBER_NAMES = SetsKt.hashSetOf(new Name[]{OperatorNameConventions.HASH_CODE, OperatorNameConventions.EQUALS, OperatorNameConventions.TO_STRING});

    public FirClassAnySynthesizedMemberScope(FirSession firSession, FirContainingNamesAwareScope firContainingNamesAwareScope, FirRegularClass firRegularClass, ScopeSession scopeSession) {
        FirDeclarationOrigin.Synthetic synthetic;
        firSession.getClass();
        firContainingNamesAwareScope.getClass();
        firRegularClass.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.declaredMemberScope = firContainingNamesAwareScope;
        this.klass = firRegularClass;
        if (firRegularClass.getStatus().isData()) {
            synthetic = FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE;
        } else {
            if (!firRegularClass.getStatus().isInline() && !firRegularClass.getStatus().isValue()) {
                f2f.a("This scope should not be created for non-data and non-value class. ", UtilsKt.render(firRegularClass));
                throw null;
            }
            synthetic = FirDeclarationOrigin.Synthetic.ValueClassMember.INSTANCE;
        }
        this.originForFunctions = synthetic;
        ConeClassLikeLookupTag lookupTag = firRegularClass.getSymbol().getLookupTag();
        this.lookupTag = lookupTag;
        this.baseModuleData = firRegularClass.getModuleData();
        this.dispatchReceiverType = ScopeUtilsKt.defaultType(firRegularClass);
        this.synthesizedCache = (FirSynthesizedStorage.SynthesizedCache) FirClassAnySynthesizedMemberScopeKt.getSynthesizedStorage(firSession).getSynthesizedCacheByScope().getValue(lookupTag, null);
        KtSourceElement source = firRegularClass.getSource();
        this.synthesizedSource = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE, null, 2, null) : null;
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) CollectionsKt.firstOrNull(SupertypeUtilsKt.lookupSuperTypes$default(firRegularClass, false, false, firSession, true, null, 32, null));
        this.superKlassScope = coneClassLikeType != null ? FirKotlinScopeProviderKt.scopeForSupertype(coneClassLikeType, firSession, scopeSession, firRegularClass, FirResolvePhase.TYPES) : null;
    }

    public static Unit b(Ref.BooleanRef booleanRef, FirClassAnySynthesizedMemberScope firClassAnySynthesizedMemberScope, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (booleanRef.element && firNamedFunctionSymbol.getRawStatus().getModality() == Modality.FINAL && firClassAnySynthesizedMemberScope.matchesSomeAnyMember(firNamedFunctionSymbol, name)) {
            booleanRef.element = false;
        }
        return Unit.INSTANCE;
    }

    public static Unit c(FirClassAnySynthesizedMemberScope firClassAnySynthesizedMemberScope, Name name, Ref.BooleanRef booleanRef, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (firClassAnySynthesizedMemberScope.matchesSomeAnyMember(firNamedFunctionSymbol, name)) {
            booleanRef.element = false;
        }
        function1.invoke(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    private final FirNamedFunction generateEqualsFunction() {
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        generateSyntheticFunction(firNamedFunctionBuilder, OperatorNameConventions.EQUALS, true);
        firNamedFunctionBuilder.setReturnTypeRef(new FirImplicitBooleanTypeRef(firNamedFunctionBuilder.getSource()));
        List<FirValueParameter> valueParameters = firNamedFunctionBuilder.getValueParameters();
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        Name nameIdentifier = Name.identifier("other");
        nameIdentifier.getClass();
        firValueParameterBuilder.setName(nameIdentifier);
        firValueParameterBuilder.setOrigin(this.originForFunctions);
        firValueParameterBuilder.setModuleData(this.baseModuleData);
        firValueParameterBuilder.setReturnTypeRef(new FirImplicitNullableAnyTypeRef((KtSourceElement) null));
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
        firValueParameterBuilder.setCrossinline(false);
        firValueParameterBuilder.setNoinline(false);
        firValueParameterBuilder.setVararg(false);
        valueParameters.add(firValueParameterBuilder.mo288build());
        return firNamedFunctionBuilder.mo288build();
    }

    private final FirNamedFunction generateHashCodeFunction() {
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        generateSyntheticFunction$default(this, firNamedFunctionBuilder, OperatorNameConventions.HASH_CODE, false, 2, null);
        firNamedFunctionBuilder.setReturnTypeRef(new FirImplicitIntTypeRef(firNamedFunctionBuilder.getSource()));
        return firNamedFunctionBuilder.mo288build();
    }

    private final void generateSyntheticFunction(FirNamedFunctionBuilder firNamedFunctionBuilder, Name name, boolean z) {
        firNamedFunctionBuilder.setSource(this.synthesizedSource);
        firNamedFunctionBuilder.setModuleData(this.baseModuleData);
        firNamedFunctionBuilder.setOrigin(this.originForFunctions);
        firNamedFunctionBuilder.setName(name);
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.OPEN, EffectiveVisibility.Public.INSTANCE);
        firResolvedDeclarationStatusImpl.setOperator(z);
        firResolvedDeclarationStatusImpl.setReturnValueStatus(ReturnValueStatus.MustUse);
        firNamedFunctionBuilder.setStatus(firResolvedDeclarationStatusImpl);
        firNamedFunctionBuilder.setLocal(this.klass.getIsLocal());
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(this.lookupTag.getClassId(), name)));
        firNamedFunctionBuilder.setDispatchReceiverType(this.dispatchReceiverType);
    }

    public static /* synthetic */ void generateSyntheticFunction$default(FirClassAnySynthesizedMemberScope firClassAnySynthesizedMemberScope, FirNamedFunctionBuilder firNamedFunctionBuilder, Name name, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        firClassAnySynthesizedMemberScope.generateSyntheticFunction(firNamedFunctionBuilder, name, z);
    }

    private final FirNamedFunction generateToStringFunction() {
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        generateSyntheticFunction$default(this, firNamedFunctionBuilder, OperatorNameConventions.TO_STRING, false, 2, null);
        firNamedFunctionBuilder.setReturnTypeRef(new FirImplicitStringTypeRef(firNamedFunctionBuilder.getSource()));
        return firNamedFunctionBuilder.mo288build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean matchesSomeAnyMember(FirNamedFunctionSymbol firNamedFunctionSymbol, Name name) {
        if (Intrinsics.areEqual(name, OperatorNameConventions.HASH_CODE) || Intrinsics.areEqual(name, OperatorNameConventions.TO_STRING)) {
            return firNamedFunctionSymbol.getValueParameterSymbols().isEmpty() && !FirSymbolStatusUtilsKt.isExtension(firNamedFunctionSymbol) && ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getContextParameters().isEmpty();
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firNamedFunctionSymbol, FirResolvePhase.TYPES);
        return DeclarationUtilsKt.isEquals(firNamedFunctionSymbol, this.session);
    }

    public final FirNamedFunctionSymbol generateSyntheticFunctionByName$org_jetbrains_kotlin_providers(Name name) {
        FirNamedFunction firNamedFunctionGenerateToStringFunction;
        name.getClass();
        if (Intrinsics.areEqual(name, OperatorNameConventions.EQUALS)) {
            firNamedFunctionGenerateToStringFunction = generateEqualsFunction();
        } else if (Intrinsics.areEqual(name, OperatorNameConventions.HASH_CODE)) {
            firNamedFunctionGenerateToStringFunction = generateHashCodeFunction();
        } else {
            if (!Intrinsics.areEqual(name, OperatorNameConventions.TO_STRING)) {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
            firNamedFunctionGenerateToStringFunction = generateToStringFunction();
        }
        return firNamedFunctionGenerateToStringFunction.getSymbol();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return this.declaredMemberScope.getCallableNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return this.declaredMemberScope.getClassifierNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processClassifiersByNameWithSubstitution(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.declaredMemberScope.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(final Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (!ANY_MEMBER_NAMES.contains(name)) {
            this.declaredMemberScope.processFunctionsByName(name, processor);
            return;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        this.declaredMemberScope.processFunctionsByName(name, new Function1() { // from class: fz4
            public final Object invoke(Object obj) {
                return FirClassAnySynthesizedMemberScope.c(this.b, name, booleanRef, processor, (FirNamedFunctionSymbol) obj);
            }
        });
        if (booleanRef.element) {
            FirTypeScope firTypeScope = this.superKlassScope;
            if (firTypeScope != null) {
                firTypeScope.processFunctionsByName(name, new Function1() { // from class: gz4
                    public final Object invoke(Object obj) {
                        return FirClassAnySynthesizedMemberScope.b(booleanRef, this, name, (FirNamedFunctionSymbol) obj);
                    }
                });
            }
            if (booleanRef.element) {
                processor.invoke(this.synthesizedCache.getSynthesizedFunction().getValue(name, this));
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processPropertiesByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirClassAnySynthesizedMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope;
        }
        return new FirClassAnySynthesizedMemberScope(newSession, firContainingNamesAwareScopeWithReplacedSessionOrNull, this.klass, newScopeSession);
    }
}
