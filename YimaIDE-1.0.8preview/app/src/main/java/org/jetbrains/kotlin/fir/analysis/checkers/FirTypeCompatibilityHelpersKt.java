package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.PrimitivesKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirPlatformClassMapperKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0014\u0010\r\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0014\u0010\f\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0012\u0010\u000f\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\b\u001a\u00020\t\u001a\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0011*\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u001d\u0010\u0019\u001a\u00020\u001a*\u00020\u0014H\u0000R\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010\u001d\u001a\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u000e*\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a%\u0010\u001f\u001a\u00020\u0001*\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0002R\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010!\u001a)\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0000R\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010!\u001a)\u0010%\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0000R\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010!\u001a)\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0000R\u00020\u001bj\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010!\"\u001c\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\"\u0018\u0010\n\u001a\u00020\u0001*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006\"\u001c\u0010\u000b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0003\"\u001c\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0003\"\u001e\u0010\u0013\u001a\u00020\u000e*\u00020\u00148BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"isBuiltin", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "isNullableEnum", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;)Z", "isIdentityLess", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isNotNullPrimitive", "isFinalClass", "isClass", "isEnum", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toTypeInfo", "toKotlinTypeIfPlatform", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "toPlatformTypeIfKotlin", "mostOriginalTypeIfSmartCast", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getMostOriginalTypeIfSmartCast$annotations", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getMostOriginalTypeIfSmartCast", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toArgumentInfo", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;", "getCounterpartRelativelyToPlatform", "isSubtypeOf", "other", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;)Z", "areUnrelated", "a", "b", "areRelated", "shouldReportAsPerRules1", "l", "r", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeCompatibilityHelpersKt {
    public static final boolean areRelated(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        checkerContext.getClass();
        typeInfo.getClass();
        typeInfo2.getClass();
        return !areUnrelated(checkerContext, typeInfo, typeInfo2);
    }

    public static final boolean areUnrelated(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        checkerContext.getClass();
        typeInfo.getClass();
        typeInfo2.getClass();
        return (isSubtypeOf(checkerContext, typeInfo, typeInfo2) || isSubtypeOf(checkerContext, typeInfo2, typeInfo)) ? false : true;
    }

    private static final ConeKotlinType getCounterpartRelativelyToPlatform(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeClassLikeType kotlinTypeIfPlatform = toKotlinTypeIfPlatform(coneKotlinType, firSession);
        return kotlinTypeIfPlatform != null ? kotlinTypeIfPlatform : toPlatformTypeIfKotlin(coneKotlinType, firSession);
    }

    private static final ConeKotlinType getMostOriginalTypeIfSmartCast(FirExpression firExpression) {
        return firExpression instanceof FirSmartCastExpression ? getMostOriginalTypeIfSmartCast(((FirSmartCastExpression) firExpression).getOriginalExpression()) : FirTypeUtilsKt.getResolvedType(firExpression);
    }

    private static final boolean isBuiltin(FirClassSymbol<?> firClassSymbol) {
        return PrimitivesKt.isPrimitiveType(firClassSymbol) || Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getString()) || firClassSymbol.getClassKind() == ClassKind.ENUM_CLASS;
    }

    public static final boolean isClass(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, firSession) != null;
    }

    public static final boolean isEnum(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, firSession);
        return regularClassSymbol != null && regularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS;
    }

    private static final boolean isFinalClass(FirClassSymbol<?> firClassSymbol) {
        return isClass(firClassSymbol) && firClassSymbol.getResolvedStatus().getModality() == Modality.FINAL;
    }

    public static final boolean isIdentityLess(TypeInfo typeInfo, FirSession firSession) {
        typeInfo.getClass();
        firSession.getClass();
        return FirIdentityLessPlatformDeterminerKt.getIdentityLessPlatformDeterminer(firSession).isIdentityLess(typeInfo) || typeInfo.getIsValueClass();
    }

    public static final boolean isNotNullPrimitive(TypeInfo typeInfo) {
        typeInfo.getClass();
        return typeInfo.getIsPrimitive() && !ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(typeInfo.getType());
    }

    public static final boolean isNullableEnum(TypeInfo typeInfo) {
        typeInfo.getClass();
        return typeInfo.getIsEnumClass() && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(typeInfo.getType());
    }

    private static final boolean isSubtypeOf(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        boolean zIsSubtypeOf$default = TypeUtilsKt.isSubtypeOf$default(typeInfo.getNotNullType(), typeInfo2.getNotNullType(), checkerContext.getSession(), false, 4, null);
        ConeKotlinType counterpartRelativelyToPlatform = getCounterpartRelativelyToPlatform(typeInfo2.getNotNullType(), checkerContext.getSession());
        return zIsSubtypeOf$default || (counterpartRelativelyToPlatform != null && TypeUtilsKt.isSubtypeOf$default(typeInfo.getNotNullType(), counterpartRelativelyToPlatform, checkerContext.getSession(), false, 4, null));
    }

    public static final boolean shouldReportAsPerRules1(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        checkerContext.getClass();
        typeInfo.getClass();
        typeInfo2.getClass();
        boolean z = typeInfo.getIsFinal() || typeInfo2.getIsFinal();
        boolean z2 = (typeInfo.getType() instanceof ConeClassLikeType) && (typeInfo2.getType() instanceof ConeClassLikeType);
        boolean z3 = typeInfo.getIsClass() && typeInfo2.getIsClass();
        if (z) {
            return areUnrelated(checkerContext, typeInfo, typeInfo2);
        }
        if (z2 && z3) {
            return areUnrelated(checkerContext, typeInfo, typeInfo2);
        }
        return false;
    }

    public static final ArgumentInfo toArgumentInfo(CheckerContext checkerContext, FirExpression firExpression) {
        checkerContext.getClass();
        firExpression.getClass();
        return new ArgumentInfo(firExpression, FirHelpersKt.finalApproximationOrSelf(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression)), FirHelpersKt.finalApproximationOrSelf(checkerContext, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, getMostOriginalTypeIfSmartCast(firExpression))), checkerContext.getSession());
    }

    public static final ConeClassLikeType toKotlinTypeIfPlatform(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ClassId correspondingKotlinClass = FirPlatformClassMapperKt.getPlatformClassMapper(firSession).getCorrespondingKotlinClass(ConeTypeUtilsKt.getClassId(coneKotlinType));
        if (correspondingKotlinClass != null) {
            return TypeConstructionUtilsKt.constructClassLikeType(correspondingKotlinClass, coneKotlinType.getTypeArguments(), ConeTypeUtilsKt.isMarkedNullable(coneKotlinType), coneKotlinType.getAttributes());
        }
        return null;
    }

    public static final ConeClassLikeType toPlatformTypeIfKotlin(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ClassId correspondingPlatformClass = FirPlatformClassMapperKt.getPlatformClassMapper(firSession).getCorrespondingPlatformClass(ConeTypeUtilsKt.getClassId(coneKotlinType));
        if (correspondingPlatformClass != null) {
            return TypeConstructionUtilsKt.constructClassLikeType(correspondingPlatformClass, coneKotlinType.getTypeArguments(), ConeTypeUtilsKt.isMarkedNullable(coneKotlinType), coneKotlinType.getAttributes());
        }
        return null;
    }

    public static final TypeInfo toTypeInfo(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeKotlinType coneType;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        coneKotlinType.getClass();
        firSession.getClass();
        Set<ConeClassLikeType> setCollectUpperBounds = org.jetbrains.kotlin.fir.TypeUtilsKt.collectUpperBounds(coneKotlinType, TypeComponentsKt.getTypeContext(firSession));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setCollectUpperBounds, 10));
        Iterator<T> it = setCollectUpperBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(ConeTypeUtilsKt.replaceArgumentsWithStarProjections((ConeClassLikeType) it.next()));
        }
        ConeKotlinType coneKotlinTypeIntersectTypes = !arrayList.isEmpty() ? ConeTypeIntersector.INSTANCE.intersectTypes(TypeComponentsKt.getTypeContext(firSession), arrayList) : null;
        if (coneKotlinTypeIntersectTypes == null || (coneType = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinTypeIntersectTypes, firSession, (Function1) null, 2, (Object) null)) == null) {
            coneType = firSession.getBuiltinTypes().getNullableAnyType().getConeType();
        }
        ConeKotlinType coneKotlinType2 = coneType;
        ConeKotlinType coneKotlinTypeWithNullability$default = TypeUtilsKt.withNullability$default(coneKotlinType2, false, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null);
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol((ConeClassLikeType) it2.next(), firSession);
            if (classSymbol != null) {
                arrayList2.add(classSymbol);
            }
        }
        if (!arrayList2.isEmpty()) {
            Iterator it3 = arrayList2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z = false;
                    break;
                }
                if (((FirClassSymbol) it3.next()).getClassKind() == ClassKind.ENUM_CLASS) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        if (!arrayList.isEmpty()) {
            Iterator it4 = arrayList.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    z2 = false;
                    break;
                }
                if (ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive((ConeClassLikeType) it4.next())) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
            break;
        }
        if (!arrayList2.isEmpty()) {
            Iterator it5 = arrayList2.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    z3 = false;
                    break;
                }
                if (isBuiltin((FirClassSymbol) it5.next())) {
                    z3 = true;
                    break;
                }
            }
        } else {
            z3 = false;
            break;
        }
        if (arrayList2.isEmpty()) {
            z4 = false;
        } else {
            Iterator it6 = arrayList2.iterator();
            while (true) {
                if (it6.hasNext()) {
                    FirClassSymbol firClassSymbol = (FirClassSymbol) it6.next();
                    if (firClassSymbol.getRawStatus().isInline() || firClassSymbol.getRawStatus().isValue()) {
                        z4 = true;
                    }
                } else {
                    z4 = false;
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            Iterator it7 = arrayList2.iterator();
            while (true) {
                if (!it7.hasNext()) {
                    z5 = false;
                    break;
                }
                if (isFinalClass((FirClassSymbol) it7.next())) {
                    z5 = true;
                    break;
                }
            }
        } else {
            z5 = false;
            break;
        }
        if (arrayList2.isEmpty()) {
            z6 = false;
        } else {
            Iterator it8 = arrayList2.iterator();
            while (it8.hasNext()) {
                if (isClass((FirClassSymbol) it8.next())) {
                    z6 = true;
                }
            }
            z6 = false;
        }
        return new TypeInfo(coneKotlinType2, coneKotlinTypeWithNullability$default, coneKotlinType, z, z2, z3, z4, z5, z6, TypeUtilsKt.canHaveSubtypesAccordingToK1(TypeUtilsKt.withNullability$default(coneKotlinType, false, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null), firSession));
    }

    private static final boolean isClass(FirClassSymbol<?> firClassSymbol) {
        return !(firClassSymbol.getClassKind() == ClassKind.INTERFACE);
    }
}
