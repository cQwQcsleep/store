package io.vavr.control;

import io.vavr.CheckedFunction1;
import io.vavr.Function1;
import io.vavr.PartialFunction;
import io.vavr.Value;
import io.vavr.collection.Iterator;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Option<T> extends Value<T>, Serializable {
    public static final long serialVersionUID = 1;

    public static final class None<T> implements Option<T>, Serializable {
        private static final None<?> INSTANCE = new None<>();
        private static final long serialVersionUID = 1;

        private None() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public boolean equals(Object obj) {
            return obj == this;
        }

        @Override // io.vavr.control.Option, io.vavr.Value, java.util.function.Supplier
        public T get() {
            throw new NoSuchElementException("No value present");
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public int hashCode() {
            return 1;
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public boolean isEmpty() {
            return true;
        }

        @Override // io.vavr.Value
        public String stringPrefix() {
            return "None";
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public String toString() {
            return stringPrefix();
        }
    }

    public static final class Some<T> implements Option<T>, Serializable {
        private static final long serialVersionUID = 1;
        private final T value;

        private Some(T t) {
            this.value = t;
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public boolean equals(Object obj) {
            if (obj != this) {
                return (obj instanceof Some) && Objects.equals(this.value, ((Some) obj).value);
            }
            return true;
        }

        @Override // io.vavr.control.Option, io.vavr.Value, java.util.function.Supplier
        public T get() {
            return this.value;
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public int hashCode() {
            return Objects.hashCode(this.value);
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public boolean isEmpty() {
            return false;
        }

        @Override // io.vavr.Value
        public String stringPrefix() {
            return "Some";
        }

        @Override // io.vavr.control.Option, io.vavr.Value
        public String toString() {
            return stringPrefix() + "(" + this.value + ")";
        }
    }

    static /* synthetic */ Object J6(Object obj, Object obj2) {
        return obj;
    }

    static /* synthetic */ Void W3(Object obj) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> Option<T> narrow(Option<? extends T> option) {
        return option;
    }

    static <T> Option<T> none() {
        return None.INSTANCE;
    }

    static <T> Option<T> of(T t) {
        return t == null ? none() : some(t);
    }

    static <T> Option<T> ofOptional(Optional<? extends T> optional) {
        Objects.requireNonNull(optional, "optional is null");
        return (Option) optional.map(new Function() { // from class: mqa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Option.of(obj);
            }
        }).orElseGet(new Supplier() { // from class: oqa
            @Override // java.util.function.Supplier
            public final Object get() {
                return Option.none();
            }
        });
    }

    static <T> Option<Seq<T>> sequence(Iterable<? extends Option<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "values is null");
        Vector vectorEmpty = Vector.empty();
        for (Option<? extends T> option : iterable) {
            if (option.isEmpty()) {
                return none();
            }
            vectorEmpty = vectorEmpty.append((Object) option.get());
        }
        return some(vectorEmpty);
    }

    static <T> Option<T> some(T t) {
        return new Some(t);
    }

    static <T, U> Option<Seq<U>> traverse(Iterable<? extends T> iterable, Function<? super T, ? extends Option<? extends U>> function) {
        Objects.requireNonNull(iterable, "values is null");
        Objects.requireNonNull(function, "mapper is null");
        return sequence(Iterator.ofAll(iterable).map((Function) function));
    }

    static <T> Option<T> when(boolean z, Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return z ? some(supplier.get()) : none();
    }

    default <R> Option<R> collect(PartialFunction<? super T, ? extends R> partialFunction) {
        Objects.requireNonNull(partialFunction, "partialFunction is null");
        final Function1<? super T, Option<? extends R>> function1Lift = partialFunction.lift();
        Objects.requireNonNull(function1Lift);
        return (Option<R>) flatMap(new Function() { // from class: sqa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (Option) function1Lift.apply(obj);
            }
        });
    }

    @Override // io.vavr.Value
    boolean equals(Object obj);

    default Option<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return (isEmpty() || predicate.test(get())) ? this : none();
    }

    default <U> Option<U> flatMap(Function<? super T, ? extends Option<? extends U>> function) {
        Objects.requireNonNull(function, "mapper is null");
        return isEmpty() ? none() : function.apply(get());
    }

    default <U> U fold(Supplier<? extends U> supplier, Function<? super T, ? extends U> function) {
        return map((Function) function).getOrElse(supplier);
    }

    @Override // io.vavr.Value, java.util.function.Supplier
    T get();

    @Override // io.vavr.Value
    default T getOrElse(Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return isEmpty() ? supplier.get() : get();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    @Override // io.vavr.Value
    default <X extends Throwable> T getOrElseThrow(Supplier<X> supplier) throws Throwable {
        Objects.requireNonNull(supplier, "exceptionSupplier is null");
        if (isEmpty()) {
            throw supplier.get();
        }
        return get();
    }

    @Override // io.vavr.Value
    int hashCode();

    @Override // io.vavr.Value
    default boolean isAsync() {
        return false;
    }

    default boolean isDefined() {
        return !isEmpty();
    }

    @Override // io.vavr.Value
    boolean isEmpty();

    @Override // io.vavr.Value
    default boolean isLazy() {
        return false;
    }

    @Override // io.vavr.Value
    default boolean isSingleValued() {
        return true;
    }

    @Override // io.vavr.Value, java.lang.Iterable
    default Iterator<T> iterator() {
        return isEmpty() ? Iterator.empty() : Iterator.of(get());
    }

    @Override // io.vavr.Value
    default <U> Option<U> map(Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function, "mapper is null");
        return isEmpty() ? none() : some(function.apply(get()));
    }

    @Override // io.vavr.Value
    default <U> Option<U> mapTo(final U u) {
        return map((Function) new Function() { // from class: qqa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Option.J6(u, obj);
            }
        });
    }

    @Override // io.vavr.Value
    default Option<Void> mapToVoid() {
        return map((Function) new Function() { // from class: kqa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Option.W3(obj);
            }
        });
    }

    default <U> Try<U> mapTry(CheckedFunction1<? super T, ? extends U> checkedFunction1) {
        return toTry().mapTry(checkedFunction1);
    }

    default Option<T> onEmpty(Runnable runnable) {
        Objects.requireNonNull(runnable, "action is null");
        if (isEmpty()) {
            runnable.run();
        }
        return this;
    }

    default Option<T> orElse(Supplier<? extends Option<? extends T>> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return isEmpty() ? supplier.get() : this;
    }

    @Override // io.vavr.Value
    default Option<T> peek(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "action is null");
        if (isDefined()) {
            consumer.accept(get());
        }
        return this;
    }

    @Override // io.vavr.Value
    String toString();

    default <U> U transform(Function<? super Option<T>, ? extends U> function) {
        Objects.requireNonNull(function, "f is null");
        return function.apply(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default Option<T> orElse(Option<? extends T> option) {
        Objects.requireNonNull(option, "other is null");
        return isEmpty() ? option : this;
    }

    static <T> Option<T> when(boolean z, T t) {
        return z ? some(t) : none();
    }

    @Override // io.vavr.Value
    default T getOrElse(T t) {
        return isEmpty() ? t : get();
    }
}
