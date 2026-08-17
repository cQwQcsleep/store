package org.jetbrains.kotlin.fir.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirCorrespondingSupertypesCache;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J,\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0016\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\fH\u0002J2\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00160\u001e2\u0006\u0010\u0018\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u0006\u001a(\u0012\u0004\u0012\u00020\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\t\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirCorrespondingSupertypesCache;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "cache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "getCorrespondingSupertypes", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "supertypeConstructor", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "captureType", "typeSystemContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "computeSupertypesMap", Argument.Delimiters.none, "subtypeLookupTag", "state", "computeSupertypePolicyAndPutInMap", "Lorg/jetbrains/kotlin/types/TypeCheckerState$SupertypesPolicy;", "supertype", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "resultingMap", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCorrespondingSupertypesCache implements FirSessionComponent {
    private final FirCache<ConeClassLikeLookupTag, Map<ConeClassLikeLookupTag, List<ConeClassLikeType>>, TypeCheckerState> cache;
    private final FirSession session;

    public FirCorrespondingSupertypesCache(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.cache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(1000, 0.5f, new Function2() { // from class: g05
            public final Object invoke(Object obj, Object obj2) {
                return FirCorrespondingSupertypesCache.c(this.b, (ConeClassLikeLookupTag) obj, (TypeCheckerState) obj2);
            }
        });
    }

    public static List a(ConeClassLikeLookupTag coneClassLikeLookupTag) {
        coneClassLikeLookupTag.getClass();
        return new ArrayList();
    }

    public static List b(Function1 function1, Object obj) {
        return (List) function1.invoke(obj);
    }

    public static Map c(FirCorrespondingSupertypesCache firCorrespondingSupertypesCache, ConeClassLikeLookupTag coneClassLikeLookupTag, TypeCheckerState typeCheckerState) {
        coneClassLikeLookupTag.getClass();
        typeCheckerState.getClass();
        return firCorrespondingSupertypesCache.computeSupertypesMap(coneClassLikeLookupTag, typeCheckerState);
    }

    private final ConeClassLikeType captureType(ConeClassLikeType type, ConeTypeContext typeSystemContext) {
        ConeRigidType coneRigidTypeM672captureFromArguments = typeSystemContext.m672captureFromArguments((RigidTypeMarker) type, CaptureStatus.FOR_SUBTYPING);
        ConeRigidType coneRigidType = type;
        if (coneRigidTypeM672captureFromArguments != null) {
            coneRigidType = coneRigidTypeM672captureFromArguments;
        }
        coneRigidType.getClass();
        return (ConeClassLikeType) coneRigidType;
    }

    private final TypeCheckerState.SupertypesPolicy computeSupertypePolicyAndPutInMap(RigidTypeMarker supertype, Map<ConeClassLikeLookupTag, List<ConeClassLikeType>> resultingMap, TypeCheckerState state) {
        supertype.getClass();
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) supertype;
        ConeClassLikeLookupTag lookupTag = coneClassLikeType.getLookupTag();
        ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) state.getTypeSystemContext().captureFromArguments(supertype, CaptureStatus.FOR_SUBTYPING);
        if (coneClassLikeType2 != null) {
            coneClassLikeType = coneClassLikeType2;
        }
        final Function1 function1 = new Function1() { // from class: h05
            public final Object invoke(Object obj) {
                return FirCorrespondingSupertypesCache.a((ConeClassLikeLookupTag) obj);
            }
        };
        List<ConeClassLikeType> listComputeIfAbsent = resultingMap.computeIfAbsent(lookupTag, new Function() { // from class: i05
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FirCorrespondingSupertypesCache.b(function1, obj);
            }
        });
        listComputeIfAbsent.getClass();
        listComputeIfAbsent.add(coneClassLikeType);
        return state.getTypeSystemContext().argumentsCount(coneClassLikeType) == 0 ? TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE : state.getTypeSystemContext().substitutionSupertypePolicy(coneClassLikeType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Map<ConeClassLikeLookupTag, List<ConeClassLikeType>> computeSupertypesMap(ConeClassLikeLookupTag subtypeLookupTag, TypeCheckerState state) {
        FirClassLikeDeclaration firClassLikeDeclaration;
        ConeLookupTagBasedType[] coneLookupTagBasedTypeArr;
        HashMap map = new HashMap();
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(subtypeLookupTag, this.session);
        if (symbol != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) != null) {
            List<FirTypeParameterRef> typeParameters = firClassLikeDeclaration.getTypeParameters();
            if (typeParameters != null) {
                List<FirTypeParameterRef> list = typeParameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(TypeConstructionUtilsKt.constructType$default((ConeClassifierLookupTag) ((FirTypeParameterRef) it.next()).getSymbol().getLookupTag(), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null));
                }
                coneLookupTagBasedTypeArr = (ConeLookupTagBasedType[]) arrayList.toArray(new ConeLookupTagBasedType[0]);
            } else {
                coneLookupTagBasedTypeArr = null;
            }
            if (coneLookupTagBasedTypeArr == null) {
                coneLookupTagBasedTypeArr = new ConeLookupTagBasedType[0];
            }
            ConeClassLikeType coneClassLikeTypeConstructClassType$default = TypeConstructionUtilsKt.constructClassType$default(subtypeLookupTag, coneLookupTagBasedTypeArr, false, null, 6, null);
            if (coneClassLikeTypeConstructClassType$default != null && ToSymbolUtilsKt.toSymbol(coneClassLikeTypeConstructClassType$default.getLookupTag(), this.session) != null) {
                state.initialize();
                ArrayDeque supertypesDeque = state.getSupertypesDeque();
                supertypesDeque.getClass();
                Set supertypesSet = state.getSupertypesSet();
                supertypesSet.getClass();
                supertypesDeque.push(coneClassLikeTypeConstructClassType$default);
                while (!supertypesDeque.isEmpty()) {
                    RigidTypeMarker rigidTypeMarker = (RigidTypeMarker) supertypesDeque.pop();
                    rigidTypeMarker.getClass();
                    if (supertypesSet.add(rigidTypeMarker)) {
                        TypeCheckerState.SupertypesPolicy supertypesPolicyComputeSupertypePolicyAndPutInMap = computeSupertypePolicyAndPutInMap(rigidTypeMarker, map, state);
                        if (Intrinsics.areEqual(supertypesPolicyComputeSupertypePolicyAndPutInMap, TypeCheckerState.SupertypesPolicy.None.INSTANCE)) {
                            supertypesPolicyComputeSupertypePolicyAndPutInMap = null;
                        }
                        if (supertypesPolicyComputeSupertypePolicyAndPutInMap == null) {
                            continue;
                        } else {
                            TypeSystemContext typeSystemContext = state.getTypeSystemContext();
                            Iterator it2 = typeSystemContext.supertypes(typeSystemContext.typeConstructor(rigidTypeMarker)).iterator();
                            while (it2.hasNext()) {
                                RigidTypeMarker rigidTypeMarkerTransformType = supertypesPolicyComputeSupertypePolicyAndPutInMap.transformType(state, (KotlinTypeMarker) it2.next());
                                if (!(rigidTypeMarkerTransformType instanceof ConeClassLikeType) || ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) rigidTypeMarkerTransformType).getLookupTag(), this.session) == null) {
                                    state.clear();
                                } else {
                                    supertypesDeque.add(rigidTypeMarkerTransformType);
                                }
                            }
                        }
                    }
                }
                state.clear();
                map.remove(subtypeLookupTag);
                return map;
            }
        }
        return null;
    }

    public final List<ConeClassLikeType> getCorrespondingSupertypes(ConeKotlinType type, TypeConstructorMarker supertypeConstructor) {
        List<ConeClassLikeType> orDefault;
        type.getClass();
        supertypeConstructor.getClass();
        if ((type instanceof ConeClassLikeType) && (supertypeConstructor instanceof ConeClassLikeLookupTag)) {
            ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(this.session);
            TypeCheckerState typeCheckerStateNewTypeCheckerState$default = TypeCheckerProviderContext.newTypeCheckerState$default(typeContext, false, true, false, 4, (Object) null);
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) type;
            ConeClassLikeLookupTag lookupTag = coneClassLikeType.getLookupTag();
            if (Intrinsics.areEqual(lookupTag, supertypeConstructor)) {
                return CollectionsKt.listOf(captureType(coneClassLikeType, typeContext));
            }
            Map<ConeClassLikeLookupTag, List<ConeClassLikeType>> value = this.cache.getValue(lookupTag, typeCheckerStateNewTypeCheckerState$default);
            if (value != null && (orDefault = value.getOrDefault(supertypeConstructor, CollectionsKt.emptyList())) != null) {
                if (type.getTypeArguments().length == 0) {
                    return orDefault;
                }
                TypeCheckerState.SupertypesPolicy supertypesPolicySubstitutionSupertypePolicy = typeContext.substitutionSupertypePolicy(captureType(coneClassLikeType, typeContext));
                List<ConeClassLikeType> list = orDefault;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    RigidTypeMarker rigidTypeMarkerTransformType = supertypesPolicySubstitutionSupertypePolicy.transformType(typeCheckerStateNewTypeCheckerState$default, (ConeClassLikeType) it.next());
                    rigidTypeMarkerTransformType.getClass();
                    arrayList.add((ConeClassLikeType) rigidTypeMarkerTransformType);
                }
                return arrayList;
            }
        }
        return null;
    }
}
