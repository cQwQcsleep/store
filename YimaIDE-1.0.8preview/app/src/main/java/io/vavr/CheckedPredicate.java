package io.vavr;

import io.vavr.CheckedPredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CheckedPredicate<T> {
    static /* synthetic */ boolean a(CheckedPredicate checkedPredicate, Object obj) {
        checkedPredicate.getClass();
        try {
            return checkedPredicate.test(obj);
        } catch (Throwable th) {
            return ((Boolean) CheckedPredicateModule.sneakyThrow(th)).booleanValue();
        }
    }

    static /* synthetic */ boolean b(CheckedPredicate checkedPredicate, Object obj) {
        return !checkedPredicate.test(obj);
    }

    static <T> CheckedPredicate<T> of(CheckedPredicate<T> checkedPredicate) {
        return checkedPredicate;
    }

    default CheckedPredicate<T> negate() {
        return new CheckedPredicate() { // from class: kr1
            @Override // io.vavr.CheckedPredicate
            public final boolean test(Object obj) {
                return CheckedPredicate.b(this.a, obj);
            }
        };
    }

    boolean test(T t) throws Throwable;

    default Predicate<T> unchecked() {
        return new Predicate() { // from class: jr1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CheckedPredicate.a(this.b, obj);
            }
        };
    }
}
