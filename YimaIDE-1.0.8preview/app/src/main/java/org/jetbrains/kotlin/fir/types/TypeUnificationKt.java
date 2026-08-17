package org.jetbrains.kotlin.fir.types;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a<\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\n\u001a\u0014\u0010\u000b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\u0002H\u0002\u001a\u0014\u0010\r\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"doUnify", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirSession;", "originalTypeProjection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "typeWithParametersProjection", "targetTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "removeQuestionMark", "session", "replaceType", "newType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeUnificationKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProjectionKind.values().length];
            try {
                iArr[ProjectionKind.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProjectionKind.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ProjectionKind.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean doUnify(FirSession firSession, ConeTypeProjection coneTypeProjection, ConeTypeProjection coneTypeProjection2, Set<FirTypeParameterSymbol> set, Map<FirTypeParameterSymbol, ConeTypeProjection> map) {
        ConeTypeParameterLookupTag lookupTag;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible2;
        firSession.getClass();
        coneTypeProjection.getClass();
        coneTypeProjection2.getClass();
        set.getClass();
        map.getClass();
        ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
        ConeRigidType coneRigidTypeFullyExpandedType$default = (type == null || (coneRigidTypeLowerBoundIfFlexible2 = ConeTypeUtilsKt.lowerBoundIfFlexible(type)) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType$default(coneRigidTypeLowerBoundIfFlexible2, firSession, (Function1) null, 2, (Object) null);
        ConeKotlinType type2 = ConeTypeProjectionKt.getType(coneTypeProjection2);
        ConeRigidType coneRigidTypeFullyExpandedType$default2 = (type2 == null || (coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(type2)) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType$default(coneRigidTypeLowerBoundIfFlexible, firSession, (Function1) null, 2, (Object) null);
        if (coneRigidTypeFullyExpandedType$default2 instanceof ConeErrorType) {
            return true;
        }
        if (coneRigidTypeFullyExpandedType$default instanceof ConeIntersectionType) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (ConeKotlinType coneKotlinType : ((ConeIntersectionType) coneRigidTypeFullyExpandedType$default).getIntersectedTypes()) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                if (!doUnify(firSession, coneKotlinType, coneTypeProjection2, set, linkedHashMap2)) {
                    return false;
                }
                for (Map.Entry entry : linkedHashMap2.entrySet()) {
                    FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) entry.getKey();
                    KotlinTypeMarker kotlinTypeMarker = (ConeTypeProjection) entry.getValue();
                    KotlinTypeMarker kotlinTypeMarker2 = (ConeTypeProjection) linkedHashMap.get(firTypeParameterSymbol);
                    if (kotlinTypeMarker2 == null || ((kotlinTypeMarker instanceof KotlinTypeMarker) && (kotlinTypeMarker2 instanceof KotlinTypeMarker) && AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(firSession), kotlinTypeMarker, kotlinTypeMarker2, false, 8, (Object) null))) {
                        linkedHashMap.put(firTypeParameterSymbol, kotlinTypeMarker);
                    }
                }
            }
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                map.put((FirTypeParameterSymbol) entry2.getKey(), (ConeTypeProjection) entry2.getValue());
            }
            return true;
        }
        if (coneTypeProjection.getKind() == coneTypeProjection2.getKind() && coneTypeProjection.getKind() != ProjectionKind.INVARIANT && coneTypeProjection.getKind() != ProjectionKind.STAR) {
            coneRigidTypeFullyExpandedType$default.getClass();
            coneRigidTypeFullyExpandedType$default2.getClass();
            return doUnify(firSession, coneRigidTypeFullyExpandedType$default, coneRigidTypeFullyExpandedType$default2, set, map);
        }
        if (coneRigidTypeFullyExpandedType$default != null && ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeFullyExpandedType$default) && coneRigidTypeFullyExpandedType$default2 != null && ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeFullyExpandedType$default2)) {
            return doUnify(firSession, removeQuestionMark(coneTypeProjection, firSession), removeQuestionMark(coneTypeProjection2, firSession), set, map);
        }
        if (coneTypeProjection.getKind() != coneTypeProjection2.getKind() && coneTypeProjection2.getKind() != ProjectionKind.INVARIANT) {
            return true;
        }
        if (coneRigidTypeFullyExpandedType$default2 instanceof ConeDefinitelyNotNullType) {
            return doUnify(firSession, coneTypeProjection, replaceType(coneTypeProjection2, ((ConeDefinitelyNotNullType) coneRigidTypeFullyExpandedType$default2).getOriginal()), set, map);
        }
        if (!(coneTypeProjection instanceof ConeStarProjection) && ((coneRigidTypeFullyExpandedType$default == null || !ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeFullyExpandedType$default)) && coneRigidTypeFullyExpandedType$default2 != null && ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeFullyExpandedType$default2))) {
            return true;
        }
        ConeTypeParameterType coneTypeParameterType = coneRigidTypeFullyExpandedType$default2 instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneRigidTypeFullyExpandedType$default2 : null;
        FirTypeParameterSymbol typeParameterSymbol = (coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null) ? null : lookupTag.getTypeParameterSymbol();
        if (typeParameterSymbol != null && set.contains(typeParameterSymbol)) {
            if (map.containsKey(typeParameterSymbol) && !Intrinsics.areEqual(map.get(typeParameterSymbol), coneTypeProjection)) {
                return false;
            }
            map.put(typeParameterSymbol, coneTypeProjection);
            return true;
        }
        if (!Intrinsics.areEqual(coneRigidTypeFullyExpandedType$default != null ? Boolean.valueOf(ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeFullyExpandedType$default)) : null, coneRigidTypeFullyExpandedType$default2 != null ? Boolean.valueOf(ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeFullyExpandedType$default2)) : null) || coneTypeProjection.getKind() != coneTypeProjection2.getKind()) {
            return true;
        }
        if (!Intrinsics.areEqual(coneRigidTypeFullyExpandedType$default != null ? ConeTypeUtilsKt.getLookupTagIfAny(coneRigidTypeFullyExpandedType$default) : null, coneRigidTypeFullyExpandedType$default2 != null ? ConeTypeUtilsKt.getLookupTagIfAny(coneRigidTypeFullyExpandedType$default2) : null) || coneRigidTypeFullyExpandedType$default == null || coneRigidTypeFullyExpandedType$default2 == null || coneRigidTypeFullyExpandedType$default.getTypeArguments().length != coneRigidTypeFullyExpandedType$default2.getTypeArguments().length || coneRigidTypeFullyExpandedType$default.getTypeArguments().length == 0) {
            return true;
        }
        for (Pair pair : ArraysKt.zip(coneRigidTypeFullyExpandedType$default.getTypeArguments(), coneRigidTypeFullyExpandedType$default2.getTypeArguments())) {
            if (!doUnify(firSession, (ConeTypeProjection) pair.component1(), (ConeTypeProjection) pair.component2(), set, map)) {
                return false;
            }
        }
        return true;
    }

    private static final ConeTypeProjection removeQuestionMark(ConeTypeProjection coneTypeProjection, FirSession firSession) {
        ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = type != null ? TypeExpansionUtilsKt.fullyExpandedType$default(type, firSession, (Function1) null, 2, (Object) null) : null;
        if (coneKotlinTypeFullyExpandedType$default != null && ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType$default)) {
            return replaceType(coneTypeProjection, TypeUtilsKt.withNullability$default(coneKotlinTypeFullyExpandedType$default, false, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null));
        }
        dt1.a("Expected nullable type, got ", coneKotlinTypeFullyExpandedType$default);
        return null;
    }

    private static final ConeTypeProjection replaceType(ConeTypeProjection coneTypeProjection, ConeKotlinType coneKotlinType) {
        int i = WhenMappings.$EnumSwitchMapping$0[coneTypeProjection.getKind().ordinal()];
        if (i == 1) {
            return coneKotlinType;
        }
        if (i == 2) {
            return new ConeKotlinTypeProjectionIn(coneKotlinType);
        }
        if (i == 3) {
            return new ConeKotlinTypeProjectionOut(coneKotlinType);
        }
        if (i != 4) {
            bu8.a();
            return null;
        }
        k2d.a("Should not be a star projection");
        return null;
    }
}
