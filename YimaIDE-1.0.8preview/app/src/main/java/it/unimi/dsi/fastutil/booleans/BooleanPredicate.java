package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.booleans.BooleanPredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface BooleanPredicate extends Predicate<Boolean> {
    static /* synthetic */ boolean E(BooleanPredicate booleanPredicate, boolean z) {
        return !booleanPredicate.test(z);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Boolean> and(Predicate<? super Boolean> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    /* JADX INFO: renamed from: negate, reason: merged with bridge method [inline-methods] */
    default Predicate<Boolean> negate2() {
        return new BooleanPredicate() { // from class: qy0
            @Override // it.unimi.dsi.fastutil.booleans.BooleanPredicate
            public final boolean test(boolean z) {
                return BooleanPredicate.E(this.b, z);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Boolean> or(Predicate<? super Boolean> predicate) {
        return super.or(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Boolean bool) {
        return test(bool.booleanValue());
    }

    boolean test(boolean z);
}
