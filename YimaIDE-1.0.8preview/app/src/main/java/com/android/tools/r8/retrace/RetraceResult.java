package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.RetraceElement;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceResult<E extends RetraceElement<?>> {
    default <T> Stream<T> flatMap(Function<? super E, Stream<T>> function) {
        return (Stream<T>) stream().flatMap(function);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void forEach(Consumer<E> consumer) {
        stream().forEach(consumer);
    }

    default boolean isAmbiguous() {
        return stream().findFirst().isPresent() && stream().skip(1L).findFirst().isPresent();
    }

    boolean isEmpty();

    Stream<E> stream();
}
