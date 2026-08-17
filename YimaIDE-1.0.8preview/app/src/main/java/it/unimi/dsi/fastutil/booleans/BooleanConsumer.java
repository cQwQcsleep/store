package it.unimi.dsi.fastutil.booleans;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface BooleanConsumer extends Consumer<Boolean> {
    @Override // java.util.function.Consumer
    @Deprecated
    default void accept(Boolean bool) {
        accept(bool.booleanValue());
    }

    void accept(boolean z);

    @Override // java.util.function.Consumer
    @Deprecated
    default Consumer<Boolean> andThen(Consumer<? super Boolean> consumer) {
        return super.andThen(consumer);
    }
}
