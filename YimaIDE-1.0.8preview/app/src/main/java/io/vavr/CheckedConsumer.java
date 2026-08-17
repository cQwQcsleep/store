package io.vavr;

import io.vavr.CheckedConsumer;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CheckedConsumer<T> {
    static /* synthetic */ void a(CheckedConsumer checkedConsumer, CheckedConsumer checkedConsumer2, Object obj) throws Throwable {
        checkedConsumer.accept(obj);
        checkedConsumer2.accept(obj);
    }

    static /* synthetic */ void b(CheckedConsumer checkedConsumer, Object obj) throws Throwable {
        checkedConsumer.getClass();
        try {
            checkedConsumer.accept(obj);
        } catch (Throwable th) {
            CheckedConsumerModule.sneakyThrow(th);
        }
    }

    static <T> CheckedConsumer<T> of(CheckedConsumer<T> checkedConsumer) {
        return checkedConsumer;
    }

    void accept(T t) throws Throwable;

    default CheckedConsumer<T> andThen(final CheckedConsumer<? super T> checkedConsumer) {
        Objects.requireNonNull(checkedConsumer, "after is null");
        return new CheckedConsumer() { // from class: qj1
            @Override // io.vavr.CheckedConsumer
            public final void accept(Object obj) throws Throwable {
                CheckedConsumer.a(this.a, checkedConsumer, obj);
            }
        };
    }

    default Consumer<T> unchecked() {
        return new Consumer() { // from class: pj1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) throws Throwable {
                CheckedConsumer.b(this.b, obj);
            }
        };
    }
}
