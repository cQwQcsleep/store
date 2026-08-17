package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeRawScopeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirIntersectionScopeOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirUnstableSmartcastTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDynamicScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirScopeWithCallableCopyReturnTypeUpdater;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScope;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralConstantType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007\u001a3\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\r\u001a.\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a/\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0016\u001a(\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002\u001a>\u0010\u0017\u001a\u0004\u0018\u00010\u0001*\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bH\u0002\u001a\u000e\u0010\u001b\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u001c\u001a\n\u0010\u001b\u001a\u00020\t*\u00020\u001d\u001a\u0018\u0010\u001b\u001a\u00020\t*\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 \"\u001d\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"smartcastScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "requiredMembersPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "delegatingConstructorScope", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "derivedClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "outerType", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "scope", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "callableCopyTypeCalculator", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "classScope", "memberOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "memberOwnerClass", "defaultType", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/name/ClassId;", "parameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "TYPE_PARAMETER_SCOPE_KEY", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "getTYPE_PARAMETER_SCOPE_KEY", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopeUtilsKt {
    private static final ScopeSessionKey<FirTypeParameterSymbol, FirTypeScope> TYPE_PARAMETER_SCOPE_KEY = new ScopeSessionKey<FirTypeParameterSymbol, FirTypeScope>() { // from class: org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt$special$$inlined$scopeSessionKey$1
    };

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirTypeScope classScope(ConeClassLikeType coneClassLikeType, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase, ConeClassLikeLookupTag coneClassLikeLookupTag, FirClassSymbol<?> firClassSymbol) {
        FirClass firClass;
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null);
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(coneClassLikeTypeFullyExpandedType$default.getLookupTag(), firSession);
        if (classSymbol == null || (firClass = (FirClass) classSymbol.getFir()) == null) {
            return null;
        }
        return FirKotlinScopeProviderKt.scopeForClass(firClass, coneClassLikeType.getAttributes().contains((ConeAttribute<?>) CompilerConeAttributes.RawType.INSTANCE) ? new ConeRawScopeSubstitutor(firSession) : ConeSubstitutorByMapKt.substitutorByMap$default(SupertypeUtilsKt.createSubstitutionForScope(firClass.getTypeParameters(), coneClassLikeTypeFullyExpandedType$default, firSession), firSession, false, 4, null), firSession, scopeSession, firClassSymbol, coneClassLikeLookupTag, firResolvePhase);
    }

    public static final ConeClassLikeType defaultType(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        ConeClassLikeLookupTag lookupTag = firClassLikeDeclaration.getSymbol().getLookupTag();
        List<FirTypeParameterRef> typeParameters = firClassLikeDeclaration.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConeTypeParameterTypeImpl(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag(), false, null, 4, null));
        }
        return new ConeClassLikeTypeImpl(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeParameterTypeImpl[0]), false, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirTypeScope delegatingConstructorScope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ConeClassLikeType coneClassLikeType, FirClassSymbol<?> firClassSymbol, ConeClassLikeType coneClassLikeType2) {
        FirClass firClass;
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default;
        FirClass firClass2;
        sessionAndScopeSessionHolder.getClass();
        coneClassLikeType.getClass();
        firClassSymbol.getClass();
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(sessionAndScopeSessionHolder, TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) sessionAndScopeSessionHolder, coneClassLikeType).getLookupTag());
        if (classSymbol == null || (firClass = (FirClass) classSymbol.getFir()) == null) {
            return null;
        }
        if (coneClassLikeType2 != null) {
            FirClassSymbol<?> classSymbol2 = ToSymbolUtilsKt.toClassSymbol(sessionAndScopeSessionHolder, coneClassLikeType2.getLookupTag());
            if (classSymbol2 == null || (firClass2 = (FirClass) classSymbol2.getFir()) == null) {
                return null;
            }
            coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(SupertypeUtilsKt.createSubstitutionForScope(firClass2.getTypeParameters(), coneClassLikeType2, sessionAndScopeSessionHolder.getSession()), sessionAndScopeSessionHolder.getSession(), false, 4, null);
        } else {
            coneSubstitutorSubstitutorByMap$default = ConeSubstitutor.Empty.INSTANCE;
        }
        return FirKotlinScopeProviderKt.scopeForClass(sessionAndScopeSessionHolder, firClass, coneSubstitutorSubstitutorByMap$default, firClassSymbol, FirResolvePhase.INSTANCE.getDECLARATIONS());
    }

    public static final ScopeSessionKey<FirTypeParameterSymbol, FirTypeScope> getTYPE_PARAMETER_SCOPE_KEY() {
        return TYPE_PARAMETER_SCOPE_KEY;
    }

    /* JADX WARN: Code duplicated, block: B:69:0x0130  */
    private static final FirTypeScope scope(ConeKotlinType coneKotlinType, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
        List<ConeKotlinType> listListOf;
        if (coneKotlinType instanceof ConeErrorType) {
            return null;
        }
        if (coneKotlinType instanceof ConeClassLikeType) {
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
            return classScope(coneClassLikeType, firSession, scopeSession, firResolvePhase, coneClassLikeType.getLookupTag(), null);
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            FirTypeParameterSymbol symbol = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getSymbol();
            ScopeSessionKey<FirTypeParameterSymbol, FirTypeScope> scopeSessionKey = TYPE_PARAMETER_SCOPE_KEY;
            HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
            HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(symbol);
            if (map == null) {
                map = new HashMap<>();
                mapScopes.put(symbol, map);
            }
            HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
            Object obj = map2.get(scopeSessionKey);
            if (obj == null) {
                ConeTypeIntersector coneTypeIntersector = ConeTypeIntersector.INSTANCE;
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
                List<FirResolvedTypeRef> resolvedBounds = symbol.getResolvedBounds();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
                Iterator<T> it = resolvedBounds.iterator();
                while (it.hasNext()) {
                    arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
                }
                Object objScope = scope(coneTypeIntersector.intersectTypes(typeContext, arrayList), firSession, scopeSession, firResolvePhase);
                if (objScope == null) {
                    objScope = FirTypeScope.Empty.INSTANCE;
                }
                obj = objScope;
                map2.put(scopeSessionKey, obj);
            }
            if (obj != null) {
                return (FirTypeScope) obj;
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.scopes.FirTypeScope");
            return null;
        }
        if (coneKotlinType instanceof ConeRawType) {
            return scope(((ConeRawType) coneKotlinType).getLowerBound(), firSession, scopeSession, firResolvePhase);
        }
        if (coneKotlinType instanceof ConeDynamicType) {
            return FirDynamicScopeKt.getDynamicMembersStorage(firSession).getDynamicScopeFor(scopeSession);
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return scope(((ConeFlexibleType) coneKotlinType).getLowerBound(), firSession, scopeSession, firResolvePhase);
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            FirTypeIntersectionScope.Companion companion = FirTypeIntersectionScope.INSTANCE;
            FirIntersectionScopeOverrideChecker firIntersectionScopeOverrideChecker = new FirIntersectionScopeOverrideChecker(firSession);
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it2 = intersectedTypes.iterator();
            while (it2.hasNext()) {
                FirTypeScope firTypeScopeScope = scope((ConeKotlinType) it2.next(), firSession, scopeSession, firResolvePhase);
                if (firTypeScopeScope != null) {
                    arrayList2.add(firTypeScopeScope);
                }
            }
            return companion.prepareIntersectionScope(firSession, firIntersectionScopeOverrideChecker, arrayList2, (ConeSimpleKotlinType) coneKotlinType);
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return scope(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), firSession, scopeSession, firResolvePhase);
        }
        if (coneKotlinType instanceof ConeIntegerConstantOperatorType) {
            return FirIntegerConstantOperatorScopeKt.getOrBuildScopeForIntegerConstantOperatorType(scopeSession, firSession, (ConeIntegerConstantOperatorType) coneKotlinType);
        }
        if (coneKotlinType instanceof ConeIntegerLiteralConstantType) {
            k2d.a("ILT should not be in receiver position");
            return null;
        }
        if (!(coneKotlinType instanceof ConeCapturedType)) {
            return null;
        }
        List<ConeKotlinType> supertypes = ((ConeCapturedType) coneKotlinType).getConstructor().getSupertypes();
        if (supertypes == null) {
            listListOf = CollectionsKt.listOf(firSession.getBuiltinTypes().getAnyType().getConeType());
        } else {
            listListOf = supertypes.isEmpty() ? null : supertypes;
            if (listListOf == null) {
                listListOf = CollectionsKt.listOf(firSession.getBuiltinTypes().getAnyType().getConeType());
            }
        }
        return scope(TypeComponentsKt.getTypeContext(firSession).m684intersectTypes((Collection) listListOf), firSession, scopeSession, firResolvePhase);
    }

    public static final FirTypeScope smartcastScope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirSmartCastExpression firSmartCastExpression, FirResolvePhase firResolvePhase) {
        ConeKotlinType coneType;
        FirTypeScope firTypeScopeScope;
        sessionAndScopeSessionHolder.getClass();
        firSmartCastExpression.getClass();
        FirTypeRef smartcastTypeWithoutNullableNothing = firSmartCastExpression.getSmartcastTypeWithoutNullableNothing();
        if (smartcastTypeWithoutNullableNothing == null || (coneType = FirTypeUtilsKt.getConeType(smartcastTypeWithoutNullableNothing)) == null) {
            coneType = FirTypeUtilsKt.getConeType(firSmartCastExpression.getSmartcastType());
        }
        CallableCopyTypeCalculator.DoNothing doNothing = CallableCopyTypeCalculator.DoNothing.INSTANCE;
        FirTypeScope firTypeScopeScope2 = scope(sessionAndScopeSessionHolder, coneType, doNothing, firResolvePhase);
        if (firSmartCastExpression.isStable() || (firTypeScopeScope = scope(sessionAndScopeSessionHolder, FirTypeUtilsKt.getResolvedType(firSmartCastExpression.getOriginalExpression()), doNothing, firResolvePhase)) == null) {
            return firTypeScopeScope2;
        }
        return firTypeScopeScope2 == null ? firTypeScopeScope : new FirUnstableSmartcastTypeScope(firTypeScopeScope2, firTypeScopeScope);
    }

    public static /* synthetic */ FirTypeScope smartcastScope$default(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirSmartCastExpression firSmartCastExpression, FirResolvePhase firResolvePhase, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = null;
        }
        return smartcastScope(sessionAndScopeSessionHolder, firSmartCastExpression, firResolvePhase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeClassLikeType defaultType(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return defaultType((FirClassLikeDeclaration) firClassLikeSymbol.getFir());
    }

    public static final ConeClassLikeType defaultType(ClassId classId, List<FirTypeParameterSymbol> list) {
        classId.getClass();
        list.getClass();
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(classId);
        List<FirTypeParameterSymbol> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConeTypeParameterTypeImpl(((FirTypeParameterSymbol) it.next()).getLookupTag(), false, null, 4, null));
        }
        return new ConeClassLikeTypeImpl(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeParameterTypeImpl[0]), false, null, 8, null);
    }

    public static final FirTypeScope scope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ConeKotlinType coneKotlinType, CallableCopyTypeCalculator callableCopyTypeCalculator, FirResolvePhase firResolvePhase) {
        sessionAndScopeSessionHolder.getClass();
        coneKotlinType.getClass();
        callableCopyTypeCalculator.getClass();
        return scope(coneKotlinType, sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession(), callableCopyTypeCalculator, firResolvePhase);
    }

    public static final FirTypeScope scope(ConeKotlinType coneKotlinType, FirSession firSession, ScopeSession scopeSession, CallableCopyTypeCalculator callableCopyTypeCalculator, FirResolvePhase firResolvePhase) {
        coneKotlinType.getClass();
        firSession.getClass();
        scopeSession.getClass();
        callableCopyTypeCalculator.getClass();
        FirTypeScope firTypeScopeScope = scope(coneKotlinType, firSession, scopeSession, firResolvePhase);
        if (firTypeScopeScope == null) {
            return null;
        }
        return Intrinsics.areEqual(callableCopyTypeCalculator, CallableCopyTypeCalculator.DoNothing.INSTANCE) ? firTypeScopeScope : new FirScopeWithCallableCopyReturnTypeUpdater(firTypeScopeScope, callableCopyTypeCalculator);
    }
}
