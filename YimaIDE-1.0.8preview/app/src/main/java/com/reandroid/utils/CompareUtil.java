package com.reandroid.utils;

import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;
import java.util.Comparator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CompareUtil {
    public static final Comparator<String> STRING_COMPARATOR = new Comparator() { // from class: f92
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CompareUtil.compare((String) obj, (String) obj2);
        }
    };
    private static final Comparator<?> TO_STRING_COMPARATOR = new Comparator() { // from class: g92
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return StringsUtil.compareToString(obj, obj2);
        }
    };
    private static final Comparator<Comparable<?>> COMPARATOR = new Comparator<Comparable<?>>() { // from class: com.reandroid.utils.CompareUtil.1
        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return compare2((Comparable) comparable, (Comparable) comparable2);
        }

        /* JADX INFO: renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Comparable comparable, Comparable comparable2) {
            return CompareUtil.compare(comparable, comparable2);
        }
    };
    private static final Comparator<Comparable<?>> INVERSE_COMPARATOR = new Comparator<Comparable<?>>() { // from class: com.reandroid.utils.CompareUtil.2
        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return compare2((Comparable) comparable, (Comparable) comparable2);
        }

        /* JADX INFO: renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Comparable comparable, Comparable comparable2) {
            return CompareUtil.compare(comparable2, comparable);
        }
    };

    public static <T extends Comparable<? super T>> int compare(T[] tArr, T[] tArr2) {
        if (tArr == tArr2) {
            return 0;
        }
        boolean zIsEmpty = isEmpty(tArr);
        boolean zIsEmpty2 = isEmpty(tArr2);
        if (zIsEmpty && zIsEmpty2) {
            return 0;
        }
        if (zIsEmpty) {
            return -1;
        }
        if (zIsEmpty2) {
            return 1;
        }
        int length = tArr.length;
        int length2 = tArr2.length;
        int i = length > length2 ? length2 : length;
        for (int i2 = 0; i2 < i; i2++) {
            int iCompare = compare(tArr[i2], tArr2[i2]);
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(length, length2);
    }

    public static int compareUnsigned(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return (((long) i) & 4294967295L) > (((long) i2) & 4294967295L) ? 1 : -1;
    }

    public static <T, E extends Comparable<E>> Comparator<T> computeComparator(final Function<? super T, E> function) {
        return new Comparator() { // from class: e92
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                Function function2 = function;
                return CompareUtil.compare((Comparable) function2.apply(obj), (Comparable) function2.apply(obj2));
            }
        };
    }

    public static <E, T extends Comparable<E>> Comparator<T> getComparableComparator() {
        return (Comparator<T>) COMPARATOR;
    }

    public static <T> Comparator<T> getComparatorUnchecked() {
        return (Comparator<T>) COMPARATOR;
    }

    public static <E, T extends Comparable<E>> Comparator<T> getInverseComparator() {
        return (Comparator<T>) INVERSE_COMPARATOR;
    }

    public static <T1> Comparator<T1> getToStringComparator() {
        return (Comparator<T1>) TO_STRING_COMPARATOR;
    }

    private static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static int compare(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return i > i2 ? 1 : -1;
    }

    public static <T extends Comparable<? super T>> int compare(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return -1;
        }
        if (t2 == null) {
            return 1;
        }
        int iCompareTo = t.compareTo(t2);
        if (iCompareTo == 0) {
            return 0;
        }
        return iCompareTo > 0 ? 1 : -1;
    }
}
