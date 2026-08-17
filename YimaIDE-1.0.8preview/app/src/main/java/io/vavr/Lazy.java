package io.vavr;

import io.vavr.Lazy;
import io.vavr.collection.Iterator;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Lazy<T> implements Value<T>, Supplier<T>, Serializable {
    private static final long serialVersionUID = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private volatile transient Supplier<? extends T> supplier;
    private T value;

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static /* synthetic */ Void D(Object obj) {
        return null;
    }

    public static /* synthetic */ Object b(Object obj, Object obj2) {
        return obj;
    }

    private T computeValue() {
        this.lock.lock();
        try {
            Supplier<? extends T> supplier = this.supplier;
            if (supplier != null) {
                this.value = supplier.get();
                this.supplier = null;
            }
            return this.value;
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Lazy<T> narrow(Lazy<? extends T> lazy) {
        return lazy;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return supplier instanceof Lazy ? (Lazy) supplier : new Lazy<>(supplier);
    }

    public static <T> Lazy<Seq<T>> sequence(final Iterable<? extends Lazy<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "values is null");
        return of(new Supplier() { // from class: zo8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Vector.ofAll(iterable).map(new Function() { // from class: ep8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((Lazy) obj).get();
                    }
                });
            }
        });
    }

    public static <T> T val(Supplier<? extends T> supplier, Class<T> cls) {
        Objects.requireNonNull(supplier, "supplier is null");
        Objects.requireNonNull(cls, "type is null");
        if (!cls.isInterface()) {
            w01.a("type has to be an interface");
            return null;
        }
        final Lazy lazyOf = of(supplier);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: cp8
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return method.invoke(this.b.get(), objArr);
            }
        });
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        get();
        objectOutputStream.defaultWriteObject();
    }

    @Override // io.vavr.Value
    public boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof Lazy) && Objects.equals(((Lazy) obj).get(), get());
        }
        return true;
    }

    public Option<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        T t = get();
        return predicate.test(t) ? Option.some(t) : Option.none();
    }

    @Override // io.vavr.Value, java.util.function.Supplier
    public T get() {
        return this.supplier == null ? this.value : computeValue();
    }

    @Override // io.vavr.Value
    public int hashCode() {
        return Objects.hashCode(get());
    }

    @Override // io.vavr.Value
    public boolean isAsync() {
        return false;
    }

    @Override // io.vavr.Value
    public boolean isEmpty() {
        return false;
    }

    public boolean isEvaluated() {
        return this.supplier == null;
    }

    @Override // io.vavr.Value
    public boolean isLazy() {
        return true;
    }

    @Override // io.vavr.Value
    public boolean isSingleValued() {
        return true;
    }

    @Override // io.vavr.Value, java.lang.Iterable
    public Iterator<T> iterator() {
        return Iterator.of(get());
    }

    @Override // io.vavr.Value
    public <U> Lazy<U> map(final Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function, "mapper is null");
        return of(new Supplier() { // from class: ap8
            @Override // java.util.function.Supplier
            public final Object get() {
                return function.apply(this.b.get());
            }
        });
    }

    @Override // io.vavr.Value
    public <U> Lazy<U> mapTo(final U u) {
        return map((Function) new Function() { // from class: bp8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Lazy.b(u, obj);
            }
        });
    }

    @Override // io.vavr.Value
    public Lazy<Void> mapToVoid() {
        return map((Function) new Function() { // from class: dp8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Lazy.D(obj);
            }
        });
    }

    @Override // io.vavr.Value
    public Lazy<T> peek(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "action is null");
        consumer.accept(get());
        return this;
    }

    @Override // io.vavr.Value
    public String stringPrefix() {
        return "Lazy";
    }

    @Override // io.vavr.Value
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(stringPrefix());
        sb.append("(");
        sb.append(!isEvaluated() ? "?" : this.value);
        sb.append(")");
        return sb.toString();
    }

    public <U> U transform(Function<? super Lazy<T>, ? extends U> function) {
        Objects.requireNonNull(function, "f is null");
        return function.apply(this);
    }
}
