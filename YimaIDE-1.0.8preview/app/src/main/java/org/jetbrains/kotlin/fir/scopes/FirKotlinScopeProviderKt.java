package org.jetbrains.kotlin.fir.scopes;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeRawScopeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassSubstitutionScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirScopeWithCallableCopyReturnTypeUpdater;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a-\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\r\u001a0\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a1\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000f\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a9\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u0016\u001a\u001a\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a.\u0010\u001a\u001a\u0004\u0018\u00010\u0001*\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a\u001a\u0010\u001d\u001a\u00020\u0012*\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 \u001a \u0010\u0011\u001a\u00020\u00122\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\\\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002\"&\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00180(X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010+\u001a\u00020,*\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b-\u0010.\"\u001f\u00101\u001a\u000202*\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b5\u00100\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"unsubstitutedScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "withForcedTypeCalculator", Argument.Delimiters.none, "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirClass;ZLorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;ZLorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "scopeForClass", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "memberOwnerClass", "memberOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "scopeForTypeAlias", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "scopeForSupertype", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "derivedClass", "substitutorForSuperType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "classTypeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "symbol", ModuleXmlParser.TYPE, "scopeForClassImpl", "skipPrivateMembers", "classFirDispatchReceiver", "isFromExpectClass", "TYPEALIAS_CONSTRUCTOR", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "getKotlinScopeProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "kotlinScopeProvider$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "substitutionScopeKeyFactory", "Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory;", "getSubstitutionScopeKeyFactory", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory;", "substitutionScopeKeyFactory$delegate", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKotlinScopeProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirKotlinScopeProviderKt.class, "kotlinScopeProvider", "getKotlinScopeProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", 1), new PropertyReference1Impl<>(FirKotlinScopeProviderKt.class, "substitutionScopeKeyFactory", "getSubstitutionScopeKeyFactory(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/SubstitutionScopeKeyFactory;", 1)};
    private static final ScopeSessionKey<Pair<FirSession, FirTypeAliasSymbol>, FirScope> TYPEALIAS_CONSTRUCTOR = new ScopeSessionKey<Pair<? extends FirSession, ? extends FirTypeAliasSymbol>, FirScope>() { // from class: org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt$special$$inlined$scopeSessionKey$1
    };
    private static final ArrayMapAccessor kotlinScopeProvider$delegate;
    private static final ArrayMapAccessor substitutionScopeKeyFactory$delegate;

    static {
        FirSession.Companion companion = FirSession.INSTANCE;
        kotlinScopeProvider$delegate = TypeRegistry.generateAccessor$default(companion, Reflection.getOrCreateKotlinClass(FirKotlinScopeProvider.class), (Object) null, 2, (Object) null);
        substitutionScopeKeyFactory$delegate = TypeRegistry.generateAccessor$default(companion, Reflection.getOrCreateKotlinClass(SubstitutionScopeKeyFactory.class), (Object) null, 2, (Object) null);
    }

    public static final FirKotlinScopeProvider getKotlinScopeProvider(FirSession firSession) {
        firSession.getClass();
        return (FirKotlinScopeProvider) kotlinScopeProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final SubstitutionScopeKeyFactory getSubstitutionScopeKeyFactory(FirSession firSession) {
        firSession.getClass();
        return (SubstitutionScopeKeyFactory) substitutionScopeKeyFactory$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final FirTypeScope scopeForClass(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirClass firClass, ConeSubstitutor coneSubstitutor, FirClassSymbol<?> firClassSymbol, FirResolvePhase firResolvePhase) {
        sessionAndScopeSessionHolder.getClass();
        firClass.getClass();
        coneSubstitutor.getClass();
        firClassSymbol.getClass();
        return scopeForClass(firClass, coneSubstitutor, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession(), firClassSymbol, firClassSymbol.getLookupTag(), firResolvePhase);
    }

    private static final FirTypeScope scopeForClassImpl(FirClass firClass, ConeSubstitutor coneSubstitutor, FirSession firSession, ScopeSession scopeSession, boolean z, FirClass firClass2, boolean z2, ConeClassLikeLookupTag coneClassLikeLookupTag, FirClassSymbol<?> firClassSymbol, FirResolvePhase firResolvePhase) {
        FirTypeScope firTypeScopeUnsubstitutedScope = unsubstitutedScope(firClass, firSession, scopeSession, false, firResolvePhase);
        if (Intrinsics.areEqual(coneSubstitutor, ConeSubstitutor.Empty.INSTANCE)) {
            return firTypeScopeUnsubstitutedScope;
        }
        ConeSubstitutionScopeKey coneSubstitutionScopeKeyCreateKey = getSubstitutionScopeKeyFactory(firClass.getModuleData().getSession()).createKey(coneSubstitutor, firClass2.getSymbol().getLookupTag(), coneClassLikeLookupTag, firClassSymbol, z2);
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(firClass);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(firClass, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object obj = map2.get(coneSubstitutionScopeKeyCreateKey);
        if (obj == null) {
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneSubstitutor.substituteOrSelf(ScopeUtilsKt.defaultType(firClass2)));
            coneRigidTypeLowerBoundIfFlexible.getClass();
            FirClassSubstitutionScope firClassSubstitutionScope = new FirClassSubstitutionScope(firSession, firTypeScopeUnsubstitutedScope, coneSubstitutionScopeKeyCreateKey, coneSubstitutor, (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible, z, z2, coneClassLikeLookupTag, !Intrinsics.areEqual(firClass2, firClass) ? FirDeclarationOrigin.SubstitutionOverride.DeclarationSite.INSTANCE : FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE);
            map2.put(coneSubstitutionScopeKeyCreateKey, firClassSubstitutionScope);
            obj = firClassSubstitutionScope;
        }
        return (FirClassSubstitutionScope) obj;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0045  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirTypeScope scopeForSupertype(ConeKotlinType coneKotlinType, FirSession firSession, ScopeSession scopeSession, FirClass firClass, FirResolvePhase firResolvePhase) {
        boolean z;
        coneKotlinType.getClass();
        firSession.getClass();
        scopeSession.getClass();
        firClass.getClass();
        if (!(coneKotlinType instanceof ConeClassLikeType) || (coneKotlinType instanceof ConeErrorType)) {
            return null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeType.getLookupTag(), firSession);
        if (regularClassSymbol == null) {
            return null;
        }
        ConeSubstitutor coneSubstitutorSubstitutorForSuperType = substitutorForSuperType(coneClassLikeType, firSession, regularClassSymbol);
        FirRegularClass firRegularClass = (FirRegularClass) regularClassSymbol.getFir();
        FirRegularClass firRegularClass2 = firClass instanceof FirRegularClass ? (FirRegularClass) firClass : null;
        if (firRegularClass2 != null) {
            z = firRegularClass2.getStatus().isExpect();
        }
        return scopeForClassImpl(firRegularClass, coneSubstitutorSubstitutorForSuperType, firSession, scopeSession, true, firClass, z, firClass.getSymbol().getLookupTag(), firClass.getSymbol(), firResolvePhase);
    }

    public static final FirScope scopeForTypeAlias(FirTypeAlias firTypeAlias, FirSession firSession, ScopeSession scopeSession) {
        firTypeAlias.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return firTypeAlias.getScopeProvider().getTypealiasConstructorScope(firTypeAlias, firSession, scopeSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeSubstitutor substitutor(FirRegularClassSymbol firRegularClassSymbol, ConeClassLikeType coneClassLikeType, FirSession firSession) {
        return coneClassLikeType.getTypeArguments().length == 0 ? ConeSubstitutor.Empty.INSTANCE : ConeSubstitutorByMapKt.substitutorByMap$default(SupertypeUtilsKt.createSubstitutionForScope(((FirRegularClass) firRegularClassSymbol.getFir()).getTypeParameters(), coneClassLikeType, firSession), firSession, false, 4, null);
    }

    public static final ConeSubstitutor substitutorForSuperType(ConeClassLikeType coneClassLikeType, FirSession firSession, FirRegularClassSymbol firRegularClassSymbol) {
        coneClassLikeType.getClass();
        firSession.getClass();
        firRegularClassSymbol.getClass();
        return coneClassLikeType.getAttributes().contains((ConeAttribute<?>) CompilerConeAttributes.RawType.INSTANCE) ? new ConeRawScopeSubstitutor(firSession) : substitutor(firRegularClassSymbol, coneClassLikeType, firSession);
    }

    public static final FirTypeScope unsubstitutedScope(FirClass firClass, FirSession firSession, ScopeSession scopeSession, boolean z, FirResolvePhase firResolvePhase) {
        firClass.getClass();
        firSession.getClass();
        scopeSession.getClass();
        FirTypeScope useSiteMemberScope = firClass.getScopeProvider().getUseSiteMemberScope(firClass, firSession, scopeSession, firResolvePhase);
        return z ? new FirScopeWithCallableCopyReturnTypeUpdater(useSiteMemberScope, CallableCopyTypeCalculator.CalculateDeferredForceLazyResolution.INSTANCE) : useSiteMemberScope;
    }

    public static final FirTypeScope unsubstitutedScope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirClass firClass, boolean z, FirResolvePhase firResolvePhase) {
        sessionAndScopeSessionHolder.getClass();
        firClass.getClass();
        return unsubstitutedScope(firClass, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession(), z, firResolvePhase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirTypeScope unsubstitutedScope(FirClassSymbol<?> firClassSymbol, FirSession firSession, ScopeSession scopeSession, boolean z, FirResolvePhase firResolvePhase) {
        firClassSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return unsubstitutedScope((FirClass) firClassSymbol.getFir(), firSession, scopeSession, z, firResolvePhase);
    }

    public static final FirTypeScope unsubstitutedScope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirClassSymbol<?> firClassSymbol, boolean z, FirResolvePhase firResolvePhase) {
        sessionAndScopeSessionHolder.getClass();
        firClassSymbol.getClass();
        return unsubstitutedScope(firClassSymbol, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession(), z, firResolvePhase);
    }

    public static final FirTypeScope scopeForClass(FirClass firClass, ConeSubstitutor coneSubstitutor, FirSession firSession, ScopeSession scopeSession, FirClassSymbol<?> firClassSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, FirResolvePhase firResolvePhase) {
        firClass.getClass();
        coneSubstitutor.getClass();
        firSession.getClass();
        scopeSession.getClass();
        coneClassLikeLookupTag.getClass();
        return scopeForClassImpl(firClass, coneSubstitutor, firSession, scopeSession, false, firClass, false, coneClassLikeLookupTag, firClassSymbol, firResolvePhase);
    }
}
