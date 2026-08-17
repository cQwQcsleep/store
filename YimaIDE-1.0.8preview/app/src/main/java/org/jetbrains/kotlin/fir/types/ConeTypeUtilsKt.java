package org.jetbrains.kotlin.fir.types;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.renderer.ConeFullyQualifiedIdRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeIdRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeIdShortRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForDebugging;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForReadability;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u001f\u001a\u00020 *\u00020\u00022\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020 0\"H\u0086\bø\u0001\u0000\u001a\u001e\u0010$\u001a\u00020\u0001*\u00020\u00022\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\"\u001a.\u0010$\u001a\u00020\u0001*\u00020\u00022\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00020'H\u0002\u001a\n\u0010(\u001a\u00020)*\u00020\u0002\u001a\n\u0010*\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010+\u001a\u00020\u0004*\u00020\u0002\u001a\u0012\u0010,\u001a\u00020-*\u00020-2\u0006\u0010.\u001a\u00020\u0002\u001a$\u0010/\u001a\u00020-*\u00020-2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\"H\u0086\bø\u0001\u0000\u001a\u001f\u00101\u001a\u00020\u0015*\u00020\u00152\u000e\u00102\u001a\n\u0012\u0006\b\u0001\u0012\u00020403¢\u0006\u0002\u00105\u001a\u0012\u00106\u001a\u000204*\u00020\u00022\u0006\u00107\u001a\u000208\u001a\u0012\u00106\u001a\u000204*\u00020\u00022\u0006\u00109\u001a\u00020:\u001a\f\u0010;\u001a\u0004\u0018\u00010)*\u00020)\u001a\n\u0010<\u001a\u00020\u0015*\u00020\u0015\u001a\n\u0010=\u001a\u00020>*\u00020\u0002\u001a\n\u0010?\u001a\u00020>*\u00020\u0002\u001a$\u0010@\u001a\u00020>*\u00020\u00022\u0018\b\u0002\u0010A\u001a\u0012\u0012\u0004\u0012\u00020C\u0012\u0006\u0012\u0004\u0018\u00010>\u0018\u00010B\u001a\n\u0010D\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010E\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010F\u001a\u00020G*\u00020\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"8\u0010\u0000\u001a\u00020\u0001*\u00020\u00048FX\u0087\u0004r\u0018\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0000\u0010\u0007\"\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0003\"\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0003\"\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0016\"\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006H"}, d2 = {"isMarkedOrFlexiblyNullable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "isMarkedOrFlexiblyNullable$annotations", "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;)V", "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;)Z", "Lkotlin/Deprecated;", "message", "`isMarkedOrFlexiblyNullable` on non-flexible types is the same as `isMarkedNullable`. Also consider using `canBeNull()`.", "level", "Lkotlin/DeprecationLevel;", "ERROR", "isMarkedNullable", "hasFlexibleMarkedNullability", "getHasFlexibleMarkedNullability", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/name/ClassId;", "lookupTagIfAny", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "getLookupTagIfAny", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "classLikeLookupTagIfAny", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getClassLikeLookupTagIfAny", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "forEachType", Argument.Delimiters.none, "prepareType", "Lkotlin/Function1;", "action", "contains", "predicate", "visited", "Lorg/jetbrains/kotlin/utils/SmartSet;", "unwrapLowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "upperBoundIfFlexible", "lowerBoundIfFlexible", "withUpperBound", "Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "upperBound", "mapTypes", "func", "withArguments", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "toTypeProjection", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "projectionKind", "Lorg/jetbrains/kotlin/fir/types/ProjectionKind;", "replaceArgumentsWithStarProjectionsOrNull", "replaceArgumentsWithStarProjections", "renderForDebugging", Argument.Delimiters.none, "renderReadable", "renderReadableWithFqNames", "preRenderedConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "hasError", "hasCapture", "getConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProjectionKind.values().length];
            try {
                iArr2[ProjectionKind.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ProjectionKind.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ProjectionKind.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static boolean a(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneKotlinType instanceof ConeErrorType;
    }

    public static ConeIdRenderer b() {
        return new ConeFullyQualifiedIdRenderer();
    }

    public static ConeIdRenderer c() {
        return new ConeIdShortRenderer();
    }

    private static final boolean contains(ConeKotlinType coneKotlinType, Function1<? super ConeKotlinType, Boolean> function1, SmartSet<ConeKotlinType> smartSet) {
        if (smartSet.contains(coneKotlinType)) {
            return false;
        }
        if (((Boolean) function1.invoke(coneKotlinType)).booleanValue()) {
            return true;
        }
        smartSet.add(coneKotlinType);
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            return contains(coneFlexibleType.getLowerBound(), function1, smartSet) || (!coneFlexibleType.getIsTrivial() && contains(coneFlexibleType.getUpperBound(), function1, smartSet));
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return contains(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), function1, smartSet);
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                return false;
            }
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                if (contains((ConeKotlinType) it.next(), function1, smartSet)) {
                    return true;
                }
            }
            return false;
        }
        for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
            if ((coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) && contains(coneKotlinTypeProjection.getType(), function1, smartSet)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneKotlinType instanceof ConeCapturedType;
    }

    public static final void forEachType(ConeKotlinType coneKotlinType, Function1<? super ConeKotlinType, ? extends ConeKotlinType> function1, Function1<? super ConeKotlinType, Unit> function2) {
        coneKotlinType.getClass();
        function1.getClass();
        function2.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{coneKotlinType});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType2 = (ConeKotlinType) function1.invoke(AddToStdlibKt.popLast(listMutableListOf));
            function2.invoke(coneKotlinType2);
            if (coneKotlinType2 instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType2;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
            } else if (coneKotlinType2 instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType2).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType2.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
    }

    public static /* synthetic */ void forEachType$default(ConeKotlinType coneKotlinType, Function1 function1, Function1 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<ConeKotlinType, ConeKotlinType>() { // from class: org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt.forEachType.1
                public final ConeKotlinType invoke(ConeKotlinType coneKotlinType2) {
                    coneKotlinType2.getClass();
                    return coneKotlinType2;
                }
            };
        }
        coneKotlinType.getClass();
        function1.getClass();
        function2.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{coneKotlinType});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType2 = (ConeKotlinType) function1.invoke(AddToStdlibKt.popLast(listMutableListOf));
            function2.invoke(coneKotlinType2);
            if (coneKotlinType2 instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType2;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
            } else if (coneKotlinType2 instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType2).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType2.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
    }

    public static final ClassId getClassId(ConeKotlinType coneKotlinType) {
        ConeClassLikeLookupTag lookupTag;
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = lowerBoundIfFlexible(coneKotlinType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) {
            return null;
        }
        return lookupTag.getClassId();
    }

    public static final ConeClassLikeLookupTag getClassLikeLookupTagIfAny(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = lowerBoundIfFlexible(coneKotlinType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType != null) {
            return coneClassLikeType.getLookupTag();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeTypeConstructorMarker getConstructor(ConeRigidType coneRigidType) {
        coneRigidType.getClass();
        if (coneRigidType instanceof ConeLookupTagBasedType) {
            return ((ConeLookupTagBasedType) coneRigidType).getLookupTag();
        }
        if (coneRigidType instanceof ConeCapturedType) {
            return ((ConeCapturedType) coneRigidType).getConstructor();
        }
        if (coneRigidType instanceof ConeTypeVariableType) {
            return ((ConeTypeVariableType) coneRigidType).getTypeConstructor();
        }
        if (coneRigidType instanceof ConeIntersectionType) {
            return (ConeTypeConstructorMarker) coneRigidType;
        }
        if (coneRigidType instanceof ConeStubType) {
            return ((ConeStubType) coneRigidType).getConstructor();
        }
        if (coneRigidType instanceof ConeDefinitelyNotNullType) {
            return getConstructor(((ConeDefinitelyNotNullType) coneRigidType).getOriginal());
        }
        if (coneRigidType instanceof ConeIntegerLiteralType) {
            return (ConeTypeConstructorMarker) coneRigidType;
        }
        bu8.a();
        return null;
    }

    public static final boolean getHasFlexibleMarkedNullability(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            return false;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
        return isMarkedNullable(coneFlexibleType.getLowerBound()) != isMarkedNullable(coneFlexibleType.getUpperBound());
    }

    public static final ConeClassifierLookupTag getLookupTagIfAny(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = lowerBoundIfFlexible(coneKotlinType);
        ConeLookupTagBasedType coneLookupTagBasedType = coneRigidTypeLowerBoundIfFlexible instanceof ConeLookupTagBasedType ? (ConeLookupTagBasedType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneLookupTagBasedType != null) {
            return coneLookupTagBasedType.getLookupTag();
        }
        return null;
    }

    public static final boolean hasCapture(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return contains(coneKotlinType, new Function1() { // from class: sq2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ConeTypeUtilsKt.d((ConeKotlinType) obj));
            }
        });
    }

    public static final boolean hasError(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return contains(coneKotlinType, new Function1() { // from class: qq2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ConeTypeUtilsKt.a((ConeKotlinType) obj));
            }
        });
    }

    public static final boolean isMarkedNullable(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            return ((ConeLookupTagBasedType) coneKotlinType).getIsMarkedNullable();
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            return isMarkedNullable(coneFlexibleType.getLowerBound()) && isMarkedNullable(coneFlexibleType.getUpperBound());
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            return ((ConeCapturedType) coneKotlinType).isMarkedNullable();
        }
        if (coneKotlinType instanceof ConeIntegerLiteralType) {
            return ((ConeIntegerLiteralType) coneKotlinType).getIsMarkedNullable();
        }
        if (coneKotlinType instanceof ConeTypeVariableType) {
            return ((ConeTypeVariableType) coneKotlinType).getIsMarkedNullable();
        }
        if ((coneKotlinType instanceof ConeDefinitelyNotNullType) || (coneKotlinType instanceof ConeIntersectionType)) {
            return false;
        }
        if (coneKotlinType instanceof ConeStubType) {
            return ((ConeStubType) coneKotlinType).getIsMarkedNullable();
        }
        bu8.a();
        return false;
    }

    public static final boolean isMarkedOrFlexiblyNullable(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (coneKotlinType instanceof ConeFlexibleType) {
            return isMarkedNullable(((ConeFlexibleType) coneKotlinType).getUpperBound());
        }
        if (coneKotlinType instanceof ConeRigidType) {
            return isMarkedNullable(coneKotlinType);
        }
        bu8.a();
        return false;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "`isMarkedOrFlexiblyNullable` on non-flexible types is the same as `isMarkedNullable`. Also consider using `canBeNull()`.")
    public static /* synthetic */ void isMarkedOrFlexiblyNullable$annotations(ConeRigidType coneRigidType) {
    }

    public static final ConeRigidType lowerBoundIfFlexible(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (coneKotlinType instanceof ConeSimpleKotlinType) {
            return (ConeRigidType) coneKotlinType;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return ((ConeFlexibleType) coneKotlinType).getLowerBound();
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return (ConeRigidType) coneKotlinType;
        }
        bu8.a();
        return null;
    }

    public static final ConeIntersectionType mapTypes(ConeIntersectionType coneIntersectionType, Function1<? super ConeKotlinType, ? extends ConeKotlinType> function1) {
        coneIntersectionType.getClass();
        function1.getClass();
        Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
        Iterator<T> it = intersectedTypes.iterator();
        while (it.hasNext()) {
            arrayList.add(function1.invoke(it.next()));
        }
        ConeKotlinType upperBoundForApproximation = coneIntersectionType.getUpperBoundForApproximation();
        return new ConeIntersectionType(arrayList, upperBoundForApproximation != null ? (ConeKotlinType) function1.invoke(upperBoundForApproximation) : null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static final String renderForDebugging(ConeKotlinType coneKotlinType) throws UninitializedPropertyAccessException {
        coneKotlinType.getClass();
        StringBuilder sb = new StringBuilder();
        ConeTypeRenderer.render$default(new ConeTypeRendererForDebugging(sb), coneKotlinType, null, 2, null);
        return sb.toString();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static final String renderReadable(ConeKotlinType coneKotlinType) throws UninitializedPropertyAccessException {
        coneKotlinType.getClass();
        StringBuilder sb = new StringBuilder();
        ConeTypeRenderer.render$default(new ConeTypeRendererForReadability(sb, null, new Function0() { // from class: tq2
            public final Object invoke() {
                return ConeTypeUtilsKt.c();
            }
        }, 2, null), coneKotlinType, null, 2, null);
        return sb.toString();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static final String renderReadableWithFqNames(ConeKotlinType coneKotlinType, Map<TypeConstructorMarker, String> map) throws UninitializedPropertyAccessException {
        coneKotlinType.getClass();
        StringBuilder sb = new StringBuilder();
        ConeTypeRenderer.render$default(new ConeTypeRendererForReadability(sb, map, new Function0() { // from class: rq2
            public final Object invoke() {
                return ConeTypeUtilsKt.b();
            }
        }), coneKotlinType, null, 2, null);
        return sb.toString();
    }

    public static /* synthetic */ String renderReadableWithFqNames$default(ConeKotlinType coneKotlinType, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = null;
        }
        return renderReadableWithFqNames(coneKotlinType, map);
    }

    public static final ConeClassLikeType replaceArgumentsWithStarProjections(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        if (coneClassLikeType.getTypeArguments().length == 0) {
            return coneClassLikeType;
        }
        int length = coneClassLikeType.getTypeArguments().length;
        ConeStarProjection[] coneStarProjectionArr = new ConeStarProjection[length];
        for (int i = 0; i < length; i++) {
            coneStarProjectionArr[i] = ConeStarProjection.INSTANCE;
        }
        return withArguments(coneClassLikeType, coneStarProjectionArr);
    }

    public static final ConeSimpleKotlinType replaceArgumentsWithStarProjectionsOrNull(ConeSimpleKotlinType coneSimpleKotlinType) {
        coneSimpleKotlinType.getClass();
        if (coneSimpleKotlinType instanceof ConeClassLikeType) {
            return replaceArgumentsWithStarProjections((ConeClassLikeType) coneSimpleKotlinType);
        }
        if (!(coneSimpleKotlinType instanceof ConeIntersectionType)) {
            return null;
        }
        ConeIntersectionType coneIntersectionType = (ConeIntersectionType) coneSimpleKotlinType;
        Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
        for (ConeKotlinType coneKotlinType : intersectedTypes) {
            coneKotlinType.getClass();
            arrayList.add(replaceArgumentsWithStarProjections((ConeClassLikeType) coneKotlinType));
        }
        ConeKotlinType upperBoundForApproximation = coneIntersectionType.getUpperBoundForApproximation();
        return new ConeIntersectionType(arrayList, upperBoundForApproximation != null ? replaceArgumentsWithStarProjections((ConeClassLikeType) upperBoundForApproximation) : null);
    }

    public static final ConeTypeProjection toTypeProjection(ConeKotlinType coneKotlinType, ProjectionKind projectionKind) {
        coneKotlinType.getClass();
        projectionKind.getClass();
        int i = WhenMappings.$EnumSwitchMapping$1[projectionKind.ordinal()];
        if (i == 1) {
            return coneKotlinType;
        }
        if (i == 2) {
            return new ConeKotlinTypeProjectionIn(coneKotlinType);
        }
        if (i == 3) {
            return new ConeKotlinTypeProjectionOut(coneKotlinType);
        }
        if (i == 4) {
            return ConeStarProjection.INSTANCE;
        }
        bu8.a();
        return null;
    }

    public static final ConeSimpleKotlinType unwrapLowerBound(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return unwrapLowerBound(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return unwrapLowerBound(((ConeFlexibleType) coneKotlinType).getLowerBound());
        }
        if (coneKotlinType instanceof ConeSimpleKotlinType) {
            return (ConeSimpleKotlinType) coneKotlinType;
        }
        bu8.a();
        return null;
    }

    public static final ConeRigidType upperBoundIfFlexible(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (coneKotlinType instanceof ConeSimpleKotlinType) {
            return (ConeRigidType) coneKotlinType;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return ((ConeFlexibleType) coneKotlinType).getUpperBound();
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return (ConeRigidType) coneKotlinType;
        }
        bu8.a();
        return null;
    }

    public static final ConeClassLikeType withArguments(ConeClassLikeType coneClassLikeType, ConeTypeProjection[] coneTypeProjectionArr) {
        coneClassLikeType.getClass();
        coneTypeProjectionArr.getClass();
        if (coneClassLikeType instanceof ConeClassLikeTypeImpl) {
            ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) coneClassLikeType;
            return new ConeClassLikeTypeImpl(coneClassLikeTypeImpl.getLookupTag(), coneTypeProjectionArr, coneClassLikeTypeImpl.getIsMarkedNullable(), coneClassLikeTypeImpl.getAttributes());
        }
        if (coneClassLikeType instanceof ConeErrorType) {
            return coneClassLikeType;
        }
        f2f.a("Unknown cone type: ", Reflection.getOrCreateKotlinClass(coneClassLikeType.getClass()));
        return null;
    }

    public static final ConeIntersectionType withUpperBound(ConeIntersectionType coneIntersectionType, ConeKotlinType coneKotlinType) {
        coneIntersectionType.getClass();
        coneKotlinType.getClass();
        return new ConeIntersectionType(coneIntersectionType.getIntersectedTypes(), coneKotlinType);
    }

    public static final ClassId getClassId(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return coneClassLikeType.getLookupTag().getClassId();
    }

    public static final boolean isMarkedOrFlexiblyNullable(ConeRigidType coneRigidType) {
        coneRigidType.getClass();
        return isMarkedNullable(coneRigidType);
    }

    public static final ConeTypeProjection toTypeProjection(ConeKotlinType coneKotlinType, Variance variance) {
        coneKotlinType.getClass();
        variance.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return coneKotlinType;
        }
        if (i == 2) {
            return new ConeKotlinTypeProjectionIn(coneKotlinType);
        }
        if (i == 3) {
            return new ConeKotlinTypeProjectionOut(coneKotlinType);
        }
        bu8.a();
        return null;
    }

    public static final boolean contains(ConeKotlinType coneKotlinType, Function1<? super ConeKotlinType, Boolean> function1) {
        coneKotlinType.getClass();
        function1.getClass();
        return contains(coneKotlinType, function1, SmartSet.Companion.create());
    }
}
