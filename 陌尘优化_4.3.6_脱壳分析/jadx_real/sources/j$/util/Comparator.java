package j$.util;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface Comparator<T> {
    java.util.Comparator<T> reversed();

    java.util.Comparator<T> thenComparing(java.util.Comparator<? super T> comparator);

    <U extends Comparable<? super U>> java.util.Comparator<T> thenComparing(Function<? super T, ? extends U> function);

    <U> java.util.Comparator<T> thenComparing(Function<? super T, ? extends U> function, java.util.Comparator<? super U> comparator);

    java.util.Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> toDoubleFunction);

    java.util.Comparator<T> thenComparingInt(ToIntFunction<? super T> toIntFunction);

    java.util.Comparator<T> thenComparingLong(ToLongFunction<? super T> toLongFunction);

    /* renamed from: j$.util.Comparator$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static java.util.Comparator $default$thenComparing(java.util.Comparator comparator, java.util.Comparator comparator2) {
            Objects.requireNonNull(comparator2);
            return new C0081e(comparator, comparator2, 0);
        }

        public static java.util.Comparator a() {
            return EnumC0082f.INSTANCE;
        }

        public static java.util.Comparator $default$thenComparing(java.util.Comparator comparator, Function function, java.util.Comparator comparator2) {
            Objects.requireNonNull(function);
            Objects.requireNonNull(comparator2);
            return AbstractC0078b.s(comparator, new C0081e(comparator2, function, 1));
        }

        public static java.util.Comparator $default$thenComparing(java.util.Comparator comparator, Function function) {
            Objects.requireNonNull(function);
            return AbstractC0078b.s(comparator, new C0080d(function, 2));
        }

        public static java.util.Comparator $default$thenComparingInt(java.util.Comparator comparator, ToIntFunction toIntFunction) {
            Objects.requireNonNull(toIntFunction);
            return AbstractC0078b.s(comparator, new C0080d(toIntFunction, 0));
        }

        public static java.util.Comparator $default$thenComparingLong(java.util.Comparator comparator, ToLongFunction toLongFunction) {
            Objects.requireNonNull(toLongFunction);
            return AbstractC0078b.s(comparator, new C0080d(toLongFunction, 3));
        }

        public static java.util.Comparator $default$thenComparingDouble(java.util.Comparator comparator, ToDoubleFunction toDoubleFunction) {
            Objects.requireNonNull(toDoubleFunction);
            return AbstractC0078b.s(comparator, new C0080d(toDoubleFunction, 1));
        }
    }
}
