package one.util.streamex;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.ObjLongConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface LongCollector<A, R> extends MergingCollector<Long, A, R> {
    @Override // java.util.stream.Collector
    default BiConsumer<A, Long> accumulator() {
        final ObjLongConsumer<A> objLongConsumerLongAccumulator = longAccumulator();
        Objects.requireNonNull(objLongConsumerLongAccumulator);
        return new BiConsumer() { // from class: ph9
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                objLongConsumerLongAccumulator.accept(obj, ((Long) obj2).longValue());
            }
        };
    }

    ObjLongConsumer<A> longAccumulator();
}
