package it.unimi.dsi.fastutil.chars;

import defpackage.bg1;
import it.unimi.dsi.fastutil.SafeMath;
import it.unimi.dsi.fastutil.chars.CharPredicate;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CharPredicate extends IntPredicate, Predicate<Character> {
    static /* synthetic */ boolean K1(CharPredicate charPredicate, CharPredicate charPredicate2, char c) {
        return charPredicate.test(c) || charPredicate2.test(c);
    }

    static /* synthetic */ boolean m(CharPredicate charPredicate, CharPredicate charPredicate2, char c) {
        return charPredicate.test(c) && charPredicate2.test(c);
    }

    static /* synthetic */ boolean r(CharPredicate charPredicate, char c) {
        return !charPredicate.test(c);
    }

    @Override // java.util.function.IntPredicate
    default CharPredicate and(IntPredicate intPredicate) {
        CharPredicate bg1Var;
        if (intPredicate instanceof CharPredicate) {
            bg1Var = (CharPredicate) intPredicate;
        } else {
            Objects.requireNonNull(intPredicate);
            bg1Var = new bg1(intPredicate);
        }
        return and(bg1Var);
    }

    @Override // java.util.function.IntPredicate, java.util.function.Predicate
    default CharPredicate negate() {
        return new CharPredicate() { // from class: cg1
            @Override // it.unimi.dsi.fastutil.chars.CharPredicate
            public final boolean test(char c) {
                return CharPredicate.r(this.b, c);
            }
        };
    }

    @Override // java.util.function.IntPredicate
    default CharPredicate or(IntPredicate intPredicate) {
        CharPredicate bg1Var;
        if (intPredicate instanceof CharPredicate) {
            bg1Var = (CharPredicate) intPredicate;
        } else {
            Objects.requireNonNull(intPredicate);
            bg1Var = new bg1(intPredicate);
        }
        return or(bg1Var);
    }

    boolean test(char c);

    @Override // java.util.function.IntPredicate
    @Deprecated
    default boolean test(int i) {
        return test(SafeMath.safeIntToChar(i));
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default boolean test(Character ch) {
        return test(ch.charValue());
    }

    default CharPredicate and(final CharPredicate charPredicate) {
        Objects.requireNonNull(charPredicate);
        return new CharPredicate() { // from class: dg1
            @Override // it.unimi.dsi.fastutil.chars.CharPredicate
            public final boolean test(char c) {
                return CharPredicate.m(this.b, charPredicate, c);
            }
        };
    }

    default CharPredicate or(final CharPredicate charPredicate) {
        Objects.requireNonNull(charPredicate);
        return new CharPredicate() { // from class: eg1
            @Override // it.unimi.dsi.fastutil.chars.CharPredicate
            public final boolean test(char c) {
                return CharPredicate.K1(this.b, charPredicate, c);
            }
        };
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Character> and(Predicate<? super Character> predicate) {
        return super.and(predicate);
    }

    @Override // java.util.function.Predicate
    @Deprecated
    default Predicate<Character> or(Predicate<? super Character> predicate) {
        return super.or(predicate);
    }
}
