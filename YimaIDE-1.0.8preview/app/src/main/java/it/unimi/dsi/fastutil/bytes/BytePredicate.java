package it.unimi.dsi.fastutil.bytes;

import defpackage.a41;
import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.bytes.BytePredicate;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface BytePredicate extends IntPredicate, Predicate<Byte> {
    static /* synthetic */ boolean M(BytePredicate bytePredicate, BytePredicate bytePredicate2, byte b) {
        return bytePredicate.test(b) || bytePredicate2.test(b);
    }

    static /* synthetic */ boolean S0(BytePredicate bytePredicate, byte b) {
        return !bytePredicate.test(b);
    }

    static /* synthetic */ boolean b(BytePredicate bytePredicate, BytePredicate bytePredicate2, byte b) {
        return bytePredicate.test(b) && bytePredicate2.test(b);
    }

    @Override // java.util.function.IntPredicate
    default BytePredicate and(IntPredicate intPredicate) {
        BytePredicate a41Var;
        if (intPredicate instanceof BytePredicate) {
            a41Var = (BytePredicate) intPredicate;
        } else {
            Objects.requireNonNull(intPredicate);
            a41Var = new a41(intPredicate);
        }
        return and(a41Var);
    }

    @Override // java.util.function.IntPredicate, java.util.function.Predicate
    default BytePredicate negate() {
        return new BytePredicate() { // from class: z31
            @Override // it.unimi.dsi.fastutil.bytes.BytePredicate
            public final boolean test(byte b) {
                return BytePredicate.S0(this.b, b);
            }
        };
    }

    @Override // java.util.function.IntPredicate
    default BytePredicate or(IntPredicate intPredicate) {
        BytePredicate a41Var;
        if (intPredicate instanceof BytePredicate) {
            a41Var = (BytePredicate) intPredicate;
        } else {
            Objects.requireNonNull(intPredicate);
            a41Var = new a41(intPredicate);
        }
        return or(a41Var);
    }

    boolean test(byte b);

    @Override // java.util.function.IntPredicate
    @Deprecated
    default boolean test(int i) {
        return test(SafeMath.safeIntToByte(i));
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Byte b) {
        return test(b.byteValue());
    }

    default BytePredicate and(final BytePredicate bytePredicate) {
        Objects.requireNonNull(bytePredicate);
        return new BytePredicate() { // from class: b41
            @Override // it.unimi.dsi.fastutil.bytes.BytePredicate
            public final boolean test(byte b) {
                return BytePredicate.b(this.b, bytePredicate, b);
            }
        };
    }

    default BytePredicate or(final BytePredicate bytePredicate) {
        Objects.requireNonNull(bytePredicate);
        return new BytePredicate() { // from class: y31
            @Override // it.unimi.dsi.fastutil.bytes.BytePredicate
            public final boolean test(byte b) {
                return BytePredicate.M(this.b, bytePredicate, b);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Byte> and(Predicate<? super Byte> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Byte> or(Predicate<? super Byte> predicate) {
        return super.or(predicate);
    }
}
