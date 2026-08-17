package one.util.streamex;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
interface MergingCollector<T, A, R> extends Collector<T, A, R> {
    static /* synthetic */ Object a(BiConsumer biConsumer, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        return obj;
    }

    @Override // java.util.stream.Collector
    default BinaryOperator<A> combiner() {
        final BiConsumer<A, A> biConsumerMerger = merger();
        return new BinaryOperator() { // from class: one.util.streamex.e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return MergingCollector.a(biConsumerMerger, obj, obj2);
            }
        };
    }

    BiConsumer<A, A> merger();
}
