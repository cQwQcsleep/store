package org.eclipse.jdt.annotation;

import defpackage.x0e;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Checks {
    public static <T, U> U applyIfNonNull(T t, Function<? super T, ? extends U> function) {
        if (t != null) {
            return function.apply(t);
        }
        return null;
    }

    public static <T, U> U applyIfNonNullElse(T t, Function<? super T, ? extends U> function, U u) {
        return t != null ? function.apply(t) : u;
    }

    public static <T, U> U applyIfNonNullElseGet(T t, Function<? super T, ? extends U> function, Supplier<? extends U> supplier) {
        return t != null ? function.apply(t) : supplier.get();
    }

    public static <T> T asNullable(Optional<T> optional) {
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @SafeVarargs
    public static <T> void assertNonNull(T... tArr) {
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] == null) {
                throw new NullPointerException("Value in position " + i + " must not be null");
            }
        }
    }

    public static <T> void assertNonNullElements(Iterable<T> iterable) {
        Iterator<T> it2 = iterable.iterator();
        int i = 0;
        while (it2.hasNext()) {
            if (it2.next() == null) {
                throw new NullPointerException("Value in position " + i + " must not be null");
            }
            i++;
        }
    }

    @SafeVarargs
    public static <T> void assertNonNullWithMessage(String str, T... tArr) {
        for (T t : tArr) {
            if (t == null) {
                x0e.a(str);
                return;
            }
        }
    }

    public static boolean containsNull(Iterable<?> iterable) {
        Iterator<?> it2 = iterable.iterator();
        while (it2.hasNext()) {
            if (it2.next() == null) {
                return true;
            }
        }
        return false;
    }

    public static <T> void ifNonNull(T t, Consumer<? super T> consumer) {
        if (t != null) {
            consumer.accept(t);
        }
    }

    @SafeVarargs
    public static <T> boolean isAnyNull(T... tArr) {
        for (T t : tArr) {
            if (t == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static <T> T nonNullElse(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static <T> T nonNullElseGet(T t, Supplier<? extends T> supplier) {
        return t == null ? supplier.get() : t;
    }

    public static String requireNonEmpty(String str, String str2) {
        if (str == null) {
            x0e.a(str2);
            return null;
        }
        if (!str.isEmpty()) {
            return str;
        }
        w01.a(str2);
        return null;
    }

    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        x0e.a(str);
        return null;
    }

    public static boolean unboxElse(Boolean bool, boolean z) {
        return bool == null ? z : bool.booleanValue();
    }

    public static <T> T requireNonNull(T t) {
        t.getClass();
        return t;
    }

    public static byte unboxElse(Byte b, byte b2) {
        return b == null ? b2 : b.byteValue();
    }

    public static char unboxElse(Character ch, char c) {
        return ch == null ? c : ch.charValue();
    }

    public static int unboxElse(Integer num, int i) {
        return num == null ? i : num.intValue();
    }

    public static long unboxElse(Long l, long j) {
        return l == null ? j : l.longValue();
    }

    public static short unboxElse(Short sh, short s) {
        return sh == null ? s : sh.shortValue();
    }

    public static float unboxElse(Float f, float f2) {
        return f == null ? f2 : f.floatValue();
    }

    public static double unboxElse(Double d, double d2) {
        return d == null ? d2 : d.doubleValue();
    }

    public static String requireNonEmpty(String str) {
        str.getClass();
        if (!str.isEmpty()) {
            return str;
        }
        j2d.a();
        return null;
    }

    public static <C extends Collection<?>> C requireNonEmpty(C c) {
        c.getClass();
        if (!c.isEmpty()) {
            return c;
        }
        j2d.a();
        return null;
    }

    public static <C extends Collection<?>> C requireNonEmpty(C c, String str) {
        if (c != null) {
            if (!c.isEmpty()) {
                return c;
            }
            w01.a(str);
            return null;
        }
        x0e.a(str);
        return null;
    }

    public static <T> void assertNonNullElements(Iterable<T> iterable, String str) {
        Iterator<T> it2 = iterable.iterator();
        while (it2.hasNext()) {
            if (it2.next() == null) {
                x0e.a(str);
                return;
            }
        }
    }
}
