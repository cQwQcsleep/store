package org.jetbrains.kotlin.fir.types;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J-\u0010\n\u001a\u00020\u00072\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000e2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeComparator;", "Ljava/util/Comparator;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lkotlin/Comparator;", "<init>", "()V", "priority", Argument.Delimiters.none, "getPriority", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)I", "compare", "a", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "b", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)I", "compareNullability", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeKotlinTypeComparator implements Comparator<ConeKotlinType> {
    public static final ConeKotlinTypeComparator INSTANCE = new ConeKotlinTypeComparator();

    private ConeKotlinTypeComparator() {
    }

    private final int compareNullability(ConeKotlinType a, ConeKotlinType b) {
        return Boolean.compare(ConeTypeUtilsKt.isMarkedNullable(b), ConeTypeUtilsKt.isMarkedNullable(a));
    }

    private final int getPriority(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeErrorType) {
            return 9;
        }
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            return 8;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return 7;
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            return 6;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return 5;
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            return 4;
        }
        if (coneKotlinType instanceof ConeStubType) {
            return 3;
        }
        if (coneKotlinType instanceof ConeIntegerLiteralConstantType) {
            return 2;
        }
        return coneKotlinType instanceof ConeIntegerConstantOperatorType ? 1 : 0;
    }

    @Override // java.util.Comparator
    public int compare(ConeKotlinType a, ConeKotlinType b) {
        a.getClass();
        b.getClass();
        int priority = getPriority(a) - getPriority(b);
        if (priority != 0) {
            return priority;
        }
        if (a instanceof ConeErrorType) {
            if (b instanceof ConeErrorType) {
                return ((ConeErrorType) a).hashCode() - ((ConeErrorType) b).hashCode();
            }
            rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
            return 0;
        }
        if (a instanceof ConeLookupTagBasedType) {
            if (!(b instanceof ConeLookupTagBasedType)) {
                rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
                return 0;
            }
            int iCompareTo = ((ConeLookupTagBasedType) a).getLookupTag().getName().compareTo(((ConeLookupTagBasedType) b).getLookupTag().getName());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int iCompareNullability = compareNullability(a, b);
            return iCompareNullability != 0 ? iCompareNullability : compare(a.getTypeArguments(), b.getTypeArguments());
        }
        if (a instanceof ConeFlexibleType) {
            if (!(b instanceof ConeFlexibleType)) {
                rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
                return 0;
            }
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) a;
            ConeFlexibleType coneFlexibleType2 = (ConeFlexibleType) b;
            int iCompare = compare((ConeKotlinType) coneFlexibleType.getLowerBound(), (ConeKotlinType) coneFlexibleType2.getLowerBound());
            return iCompare != 0 ? iCompare : compare((ConeKotlinType) coneFlexibleType.getUpperBound(), (ConeKotlinType) coneFlexibleType2.getUpperBound());
        }
        if (a instanceof ConeCapturedType) {
            if (!(b instanceof ConeCapturedType)) {
                rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
                return 0;
            }
            ConeCapturedTypeConstructor constructor = ((ConeCapturedType) a).getConstructor();
            int i = constructor.getLowerType() != null ? 1 : 0;
            ConeCapturedTypeConstructor constructor2 = ((ConeCapturedType) b).getConstructor();
            int i2 = i - (constructor2.getLowerType() == null ? 0 : 1);
            if (i2 != 0) {
                return i2;
            }
            if (constructor.getLowerType() != null) {
                if (constructor2.getLowerType() == null) {
                    k2d.a("Check failed.");
                    return 0;
                }
                ConeKotlinType lowerType = constructor.getLowerType();
                lowerType.getClass();
                ConeKotlinType lowerType2 = constructor2.getLowerType();
                lowerType2.getClass();
                int iCompare2 = compare(lowerType, lowerType2);
                if (iCompare2 != 0) {
                    return iCompare2;
                }
            }
            int iCompareNullability2 = compareNullability(a, b);
            return iCompareNullability2 != 0 ? iCompareNullability2 : constructor.hashCode() - constructor2.hashCode();
        }
        if (a instanceof ConeDefinitelyNotNullType) {
            if (b instanceof ConeDefinitelyNotNullType) {
                return compare((ConeKotlinType) ((ConeDefinitelyNotNullType) a).getOriginal(), (ConeKotlinType) ((ConeDefinitelyNotNullType) b).getOriginal());
            }
            rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
            return 0;
        }
        if (a instanceof ConeIntersectionType) {
            if (!(b instanceof ConeIntersectionType)) {
                rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
                return 0;
            }
            ConeIntersectionType coneIntersectionType = (ConeIntersectionType) a;
            ConeIntersectionType coneIntersectionType2 = (ConeIntersectionType) b;
            int size = coneIntersectionType.getIntersectedTypes().size() - coneIntersectionType2.getIntersectedTypes().size();
            return size != 0 ? size : coneIntersectionType.hashCode() - coneIntersectionType2.hashCode();
        }
        if (a instanceof ConeStubType) {
            if (b instanceof ConeStubType) {
                int iCompareTo2 = ((ConeStubType) a).getConstructor().getVariable().getTypeConstructor().getName().compareTo(((ConeStubType) b).getConstructor().getVariable().getTypeConstructor().getName());
                return iCompareTo2 != 0 ? iCompareTo2 : compareNullability(a, b);
            }
            rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
            return 0;
        }
        if (!(a instanceof ConeIntegerLiteralConstantType)) {
            if (a instanceof ConeIntegerConstantOperatorType) {
                return compareNullability(a, b);
            }
            s0g.a("Unsupported type comparison: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
            return 0;
        }
        if (!(b instanceof ConeIntegerLiteralConstantType)) {
            rza.a("priority is inconsistent: ", ConeTypeUtilsKt.renderForDebugging(a), " v.s. ", ConeTypeUtilsKt.renderForDebugging(b));
            return 0;
        }
        ConeIntegerLiteralConstantType coneIntegerLiteralConstantType = (ConeIntegerLiteralConstantType) a;
        ConeIntegerLiteralConstantType coneIntegerLiteralConstantType2 = (ConeIntegerLiteralConstantType) b;
        long value = coneIntegerLiteralConstantType.getValue() - coneIntegerLiteralConstantType2.getValue();
        if (value != 0) {
            return (int) value;
        }
        int iCompareNullability3 = compareNullability(a, b);
        return iCompareNullability3 != 0 ? iCompareNullability3 : coneIntegerLiteralConstantType.hashCode() - coneIntegerLiteralConstantType2.hashCode();
    }

    private final int compare(ConeTypeProjection a, ConeTypeProjection b) {
        int iOrdinal = a.getKind().ordinal() - b.getKind().ordinal();
        if (iOrdinal != 0) {
            return iOrdinal;
        }
        if (a instanceof ConeStarProjection) {
            return 0;
        }
        if (a instanceof ConeKotlinTypeProjectionIn) {
            if (b instanceof ConeKotlinTypeProjectionIn) {
                return compare(((ConeKotlinTypeProjectionIn) a).getType(), ((ConeKotlinTypeProjectionIn) b).getType());
            }
            rza.a("ordinal is inconsistent: ", a, " v.s. ", b);
            return 0;
        }
        if (a instanceof ConeKotlinTypeProjectionOut) {
            if (b instanceof ConeKotlinTypeProjectionOut) {
                return compare(((ConeKotlinTypeProjectionOut) a).getType(), ((ConeKotlinTypeProjectionOut) b).getType());
            }
            rza.a("ordinal is inconsistent: ", a, " v.s. ", b);
            return 0;
        }
        if (a instanceof ConeKotlinType) {
            boolean z = b instanceof ConeKotlinType;
        }
        return compare((ConeKotlinType) a, (ConeKotlinType) b);
    }

    private final int compare(ConeTypeProjection[] a, ConeTypeProjection[] b) {
        int length = a.length - b.length;
        if (length != 0) {
            return length;
        }
        for (Pair pair : ArraysKt.zip(a, b)) {
            int iCompare = compare((ConeTypeProjection) pair.component1(), (ConeTypeProjection) pair.component2());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return 0;
    }
}
