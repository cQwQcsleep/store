package androidx.core.util;

import androidx.core.util.Predicate;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface Predicate<T> {
    static /* synthetic */ boolean a(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    static /* synthetic */ boolean b(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) && predicate2.test(obj);
    }

    static /* synthetic */ boolean d(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) || predicate2.test(obj);
    }

    static <T> Predicate<T> isEqual(final Object obj) {
        return obj == null ? new Predicate() { // from class: y5b
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj2) {
                return Objects.isNull(obj2);
            }
        } : new Predicate() { // from class: z5b
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj2) {
                return obj.equals(obj2);
            }
        };
    }

    static <T> Predicate<T> not(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return predicate.negate();
    }

    default Predicate<T> and(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() { // from class: a6b
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return Predicate.b(this.a, predicate, obj);
            }
        };
    }

    default Predicate<T> negate() {
        return new Predicate() { // from class: b6b
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return Predicate.a(this.a, obj);
            }
        };
    }

    default Predicate<T> or(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() { // from class: x5b
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return Predicate.d(this.a, predicate, obj);
            }
        };
    }

    boolean test(T t);
}
