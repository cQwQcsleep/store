package it.unimi.dsi.fastutil.floats;

import defpackage.gi5;
import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.floats.FloatPredicate;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface FloatPredicate extends DoublePredicate, Predicate<Float> {
    static /* synthetic */ boolean I0(FloatPredicate floatPredicate, float f) {
        return !floatPredicate.test(f);
    }

    static /* synthetic */ boolean Q(FloatPredicate floatPredicate, FloatPredicate floatPredicate2, float f) {
        return floatPredicate.test(f) && floatPredicate2.test(f);
    }

    static /* synthetic */ boolean l0(FloatPredicate floatPredicate, FloatPredicate floatPredicate2, float f) {
        return floatPredicate.test(f) || floatPredicate2.test(f);
    }

    @Override // java.util.function.DoublePredicate
    default FloatPredicate and(DoublePredicate doublePredicate) {
        FloatPredicate gi5Var;
        if (doublePredicate instanceof FloatPredicate) {
            gi5Var = (FloatPredicate) doublePredicate;
        } else {
            Objects.requireNonNull(doublePredicate);
            gi5Var = new gi5(doublePredicate);
        }
        return and(gi5Var);
    }

    @Override // java.util.function.DoublePredicate, java.util.function.Predicate
    default FloatPredicate negate() {
        return new FloatPredicate() { // from class: fi5
            @Override // it.unimi.dsi.fastutil.floats.FloatPredicate
            public final boolean test(float f) {
                return FloatPredicate.I0(this.b, f);
            }
        };
    }

    @Override // java.util.function.DoublePredicate
    default FloatPredicate or(DoublePredicate doublePredicate) {
        FloatPredicate gi5Var;
        if (doublePredicate instanceof FloatPredicate) {
            gi5Var = (FloatPredicate) doublePredicate;
        } else {
            Objects.requireNonNull(doublePredicate);
            gi5Var = new gi5(doublePredicate);
        }
        return or(gi5Var);
    }

    @Override // java.util.function.DoublePredicate
    @Deprecated
    default boolean test(double d) {
        return test(SafeMath.safeDoubleToFloat(d));
    }

    boolean test(float f);

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Float f) {
        return test(f.floatValue());
    }

    default FloatPredicate and(final FloatPredicate floatPredicate) {
        Objects.requireNonNull(floatPredicate);
        return new FloatPredicate() { // from class: ei5
            @Override // it.unimi.dsi.fastutil.floats.FloatPredicate
            public final boolean test(float f) {
                return FloatPredicate.Q(this.b, floatPredicate, f);
            }
        };
    }

    default FloatPredicate or(final FloatPredicate floatPredicate) {
        Objects.requireNonNull(floatPredicate);
        return new FloatPredicate() { // from class: hi5
            @Override // it.unimi.dsi.fastutil.floats.FloatPredicate
            public final boolean test(float f) {
                return FloatPredicate.l0(this.b, floatPredicate, f);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Float> and(Predicate<? super Float> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Float> or(Predicate<? super Float> predicate) {
        return super.or(predicate);
    }
}
