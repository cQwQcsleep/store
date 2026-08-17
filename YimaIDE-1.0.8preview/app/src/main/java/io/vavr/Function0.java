package io.vavr;

import defpackage.rk5;
import defpackage.sk5;
import defpackage.tk5;
import defpackage.uk5;
import defpackage.vk5;
import defpackage.wk5;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface Function0<R> extends Serializable, Supplier<R> {
    public static final long serialVersionUID = 1;

    static /* synthetic */ Option F(Supplier supplier) {
        Objects.requireNonNull(supplier);
        return Try.of(new tk5(supplier)).toOption();
    }

    static /* synthetic */ Object Ha(Object obj) {
        return obj;
    }

    static /* synthetic */ Try N8(Supplier supplier) {
        Objects.requireNonNull(supplier);
        return Try.of(new tk5(supplier));
    }

    static <R> Function0<R> constant(R r) {
        return new vk5(r);
    }

    static <R> Function0<Option<R>> lift(Supplier<? extends R> supplier) {
        return new wk5(supplier);
    }

    static <R> Function0<Try<R>> liftTry(Supplier<? extends R> supplier) {
        return new sk5(supplier);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <R> Function0<R> narrow(Function0<? extends R> function0) {
        return function0;
    }

    static <R> Function0<R> of(Function0<R> function0) {
        return function0;
    }

    default <V> Function0<V> andThen(Function<? super R, ? extends V> function) {
        Objects.requireNonNull(function, "after is null");
        return new uk5(this, function);
    }

    R apply();

    default int arity() {
        return 0;
    }

    default Function0<R> curried() {
        return this;
    }

    @Override // java.util.function.Supplier
    default R get() {
        return apply();
    }

    default boolean isMemoized() {
        return this instanceof Memoized;
    }

    default Function0<R> memoized() {
        if (isMemoized()) {
            return this;
        }
        Lazy lazyOf = Lazy.of(this);
        Objects.requireNonNull(lazyOf);
        return new j(lazyOf);
    }

    default Function0<R> reversed() {
        return this;
    }

    default Function1<Tuple0, R> tupled() {
        return new rk5(this);
    }
}
