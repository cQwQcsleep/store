package one.util.streamex;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface IntCollector<A, R> extends MergingCollector<Integer, A, R> {
    @Override // java.util.stream.Collector
    default BiConsumer<A, Integer> accumulator() {
        final ObjIntConsumer<A> objIntConsumerIntAccumulator = intAccumulator();
        Objects.requireNonNull(objIntConsumerIntAccumulator);
        return new BiConsumer() { // from class: yr6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                objIntConsumerIntAccumulator.accept(obj, ((Integer) obj2).intValue());
            }
        };
    }

    ObjIntConsumer<A> intAccumulator();
}
