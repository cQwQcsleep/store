package one.util.streamex;

import java.util.function.Predicate;
import java.util.stream.Collector;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
abstract class CancellableCollector<T, A, R> implements Collector<T, A, R> {
    public abstract Predicate<A> finished();
}
