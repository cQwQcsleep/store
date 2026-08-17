package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Functions;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class Functions {
    private static final Function.Mono<?> ID = new Function.Mono<Object>() { // from class: com.intellij.util.Functions.1
        @Override // com.intellij.util.Function
        public Object fun(Object obj) {
            return obj;
        }

        public String toString() {
            return "Functions.ID";
        }
    };
    private static final Function<?, String> TO_STRING = new Function<Object, String>() { // from class: com.intellij.util.Functions.2
        public String toString() {
            return "Functions.TO_STRING";
        }

        @Override // com.intellij.util.Function
        public String fun(Object obj) {
            return String.valueOf(obj);
        }
    };
    private static final Function<Pair<?, ?>, Object> PAIR_FIRST = new Function() { // from class: ns5
        @Override // com.intellij.util.Function
        public final Object fun(Object obj) {
            return Pair.getFirst((Pair) obj);
        }
    };
    private static final Function<Pair<?, ?>, Object> PAIR_SECOND = new Function() { // from class: os5
        @Override // com.intellij.util.Function
        public final Object fun(Object obj) {
            return Pair.getSecond((Pair) obj);
        }
    };
    private static final Function<Object[], Iterable<Object>> WRAP_ARRAY = new Function() { // from class: ps5
        @Override // com.intellij.util.Function
        public final Object fun(Object obj) {
            return Functions.b((Object[]) obj);
        }
    };

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 8) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 8) ? 3 : 2];
        if (i == 2) {
            objArr[0] = "f1";
        } else if (i == 3) {
            objArr[0] = "f2";
        } else if (i != 8) {
            objArr[0] = "com/intellij/util/Functions";
        } else {
            objArr[0] = "map";
        }
        switch (i) {
            case 1:
                objArr[1] = "identity";
                break;
            case 2:
            case 3:
            case 8:
                objArr[1] = "com/intellij/util/Functions";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "compose";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "TO_STRING";
                break;
            case 9:
                objArr[1] = "fromMap";
                break;
            case 10:
                objArr[1] = "pairFirst";
                break;
            case 11:
                objArr[1] = "pairSecond";
                break;
            case 12:
                objArr[1] = "wrapArray";
                break;
            default:
                objArr[1] = "constant";
                break;
        }
        if (i == 2 || i == 3) {
            objArr[2] = "compose";
        } else if (i == 8) {
            objArr[2] = "fromMap";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 8) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    public static /* synthetic */ Iterable b(Object[] objArr) {
        return objArr == null ? Collections.EMPTY_LIST : Arrays.asList(objArr);
    }

    public static /* synthetic */ Object c(Object obj, Object obj2) {
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <A, B, C> Function<A, C> compose(final Function<? super A, ? extends B> function, final Function<? super B, ? extends C> function2) {
        if (function == 0) {
            $$$reportNull$$$0(2);
        }
        if (function2 == 0) {
            $$$reportNull$$$0(3);
        }
        if (function != identity() && function2 != identity()) {
            return new Function() { // from class: qs5
                @Override // com.intellij.util.Function
                public final Object fun(Object obj) {
                    return function2.fun(function.fun(obj));
                }
            };
        }
        if (function == function2) {
            return identity();
        }
        if (function == identity()) {
            if (function2 == 0) {
                $$$reportNull$$$0(4);
            }
            return function2;
        }
        if (function == 0) {
            $$$reportNull$$$0(5);
        }
        return function;
    }

    public static <A, B> Function<A, B> constant(final B b) {
        return new Function() { // from class: rs5
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return Functions.c(b, obj);
            }
        };
    }

    public static <A> Function.Mono<A> id() {
        return (Function.Mono) identity();
    }

    public static <A, B> Function<A, B> identity() {
        Function.Mono<?> mono = ID;
        if (mono == null) {
            $$$reportNull$$$0(1);
        }
        return mono;
    }
}
