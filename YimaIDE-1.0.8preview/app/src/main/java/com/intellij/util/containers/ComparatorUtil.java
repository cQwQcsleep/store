package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import java.util.Comparator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ComparatorUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 5 || i == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 5 || i == 8) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "comparator";
                break;
            case 2:
            case 5:
            case 8:
                objArr[0] = "com/intellij/util/containers/ComparatorUtil";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "o1";
                break;
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "o2";
                break;
            default:
                objArr[0] = "aspect";
                break;
        }
        if (i == 2) {
            objArr[1] = "compareBy";
        } else if (i == 5) {
            objArr[1] = "max";
        } else if (i != 8) {
            objArr[1] = "com/intellij/util/containers/ComparatorUtil";
        } else {
            objArr[1] = "min";
        }
        switch (i) {
            case 2:
            case 5:
            case 8:
                break;
            case 3:
            case 4:
                objArr[2] = "max";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "min";
                break;
            default:
                objArr[2] = "compareBy";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 5 && i != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private ComparatorUtil() {
    }

    public static <Type, Aspect> Comparator<Type> compareBy(final Function<? super Type, ? extends Aspect> function, final Comparator<? super Aspect> comparator) {
        if (function == null) {
            $$$reportNull$$$0(0);
        }
        if (comparator == null) {
            $$$reportNull$$$0(1);
        }
        return new Comparator() { // from class: x82
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                Comparator comparator2 = comparator;
                Function function2 = function;
                return comparator2.compare(function2.apply(obj), function2.apply(obj2));
            }
        };
    }

    public static <T> boolean equalsNullable(T t, T t2) {
        if (t == null) {
            return t2 == null;
        }
        return t.equals(t2);
    }

    public static <T extends Comparable<? super T>> T max(T t, T t2) {
        if (t == null) {
            $$$reportNull$$$0(3);
        }
        if (t2 == null) {
            $$$reportNull$$$0(4);
        }
        if (t.compareTo(t2) < 0) {
            t = t2;
        }
        if (t == null) {
            $$$reportNull$$$0(5);
        }
        return t;
    }

    public static <T extends Comparable<? super T>> T min(T t, T t2) {
        if (t == null) {
            $$$reportNull$$$0(6);
        }
        if (t2 == null) {
            $$$reportNull$$$0(7);
        }
        if (t.compareTo(t2) >= 0) {
            t = t2;
        }
        if (t == null) {
            $$$reportNull$$$0(8);
        }
        return t;
    }
}
