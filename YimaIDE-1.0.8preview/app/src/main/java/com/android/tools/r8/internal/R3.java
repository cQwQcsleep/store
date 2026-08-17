package com.android.tools.r8.internal;

import com.android.tools.r8.internal.R3;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class R3 {
    public static final /* synthetic */ boolean a = true;

    public static <T> T[] a(Class<T[]> cls, T[] tArr, Map<Integer, T> map) {
        T[] tArrCast = cls.cast(Array.newInstance(cls.getComponentType(), tArr.length));
        int i = 0;
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            System.arraycopy(tArr, i, tArrCast, i, iIntValue - i);
            tArrCast[iIntValue] = entry.getValue();
            i = iIntValue + 1;
        }
        if (i < tArr.length) {
            System.arraycopy(tArr, i, tArrCast, i, tArr.length - i);
        }
        return tArrCast;
    }

    public static Object[] b(Object[] objArr, Object obj) {
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + 1);
        objArrCopyOf[objArr.length] = obj;
        return objArrCopyOf;
    }

    public static <T> Optional<T>[] c(T[] tArr) {
        Optional<T>[] optionalArr = new Optional[tArr.length + 1];
        for (int i = 0; i < tArr.length; i++) {
            optionalArr[i] = Optional.of(tArr[i]);
        }
        optionalArr[tArr.length] = Optional.empty();
        return optionalArr;
    }

    public static <T> T b(T[] tArr) {
        return tArr[tArr.length - 1];
    }

    public static boolean c(Object[] objArr, Object obj) {
        for (Object obj2 : objArr) {
            if (Objects.equals(obj2, obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Object[] objArr, Predicate predicate) {
        for (Object obj : objArr) {
            if (predicate.test(obj)) {
                return true;
            }
        }
        return false;
    }

    public static int[] a(int[] iArr, IntUnaryOperator intUnaryOperator) {
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = intUnaryOperator.applyAsInt(i);
        }
        return iArr;
    }

    public static Object[] a(Object[] objArr, IntFunction intFunction) {
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = intFunction.apply(i);
        }
        return objArr;
    }

    public static boolean a(Object[] objArr) {
        return objArr.length == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <S, T> T[] a(S[] sArr, Function<S, T> function, T[] tArr) {
        ArrayList arrayList = null;
        for (int i = 0; i < sArr.length; i++) {
            S s = sArr[i];
            T tApply = function.apply(s);
            if (tApply != s) {
                if (arrayList == null) {
                    arrayList = new ArrayList(sArr.length);
                    for (int i2 = 0; i2 < i; i2++) {
                        arrayList.add(sArr[i2]);
                    }
                }
                if (tApply != null) {
                    arrayList.add(tApply);
                }
            } else if (arrayList != null) {
                arrayList.add(s);
            }
        }
        return arrayList != null ? (T[]) arrayList.toArray(tArr) : sArr;
    }

    public static String[] a(Object[] objArr, Function function) {
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            strArr[i] = (String) function.apply(objArr[i]);
        }
        return strArr;
    }

    public static <T> T[] a(T[] tArr, final Predicate<T> predicate, T[] tArr2) {
        return (T[]) a((Object[]) tArr, new Function() { // from class: c3c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return R3.a(predicate, obj);
            }
        }, (Object[]) tArr2);
    }

    public static /* synthetic */ Object a(Predicate predicate, Object obj) {
        if (predicate.test(obj)) {
            return obj;
        }
        return null;
    }

    public static Object[] a(Object[] objArr, Predicate predicate, Object[] objArr2, int i) {
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), i);
        int i2 = 0;
        for (Object obj : objArr) {
            if (predicate.test(obj)) {
                objArr3[i2] = obj;
                i2++;
            }
        }
        if (a || i2 == i) {
            return objArr3;
        }
        x1f.a();
        return null;
    }

    public static boolean a(Object[] objArr, Object obj) {
        for (Object obj2 : objArr) {
            if (!Objects.equals(obj2, obj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Object[] objArr, Function function, Object obj) {
        for (Object obj2 : objArr) {
            if (function.apply(obj2).equals(obj)) {
                return true;
            }
        }
        return false;
    }
}
