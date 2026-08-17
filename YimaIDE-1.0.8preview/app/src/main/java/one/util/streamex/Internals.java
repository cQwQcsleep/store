package one.util.streamex;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Internals {
    public static final Set<Collector.Characteristics> ID_CHARACTERISTICS;
    public static final Set<Collector.Characteristics> UNORDERED_CHARACTERISTICS;
    public static final Set<Collector.Characteristics> UNORDERED_ID_CHARACTERISTICS;
    public static final Function<int[], Integer> UNBOX_INT = new Function() { // from class: one.util.streamex.b
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Integer.valueOf(((int[]) obj)[0]);
        }
    };
    public static final Function<long[], Long> UNBOX_LONG = new Function() { // from class: one.util.streamex.c
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Long.valueOf(((long[]) obj)[0]);
        }
    };
    public static final Function<double[], Double> UNBOX_DOUBLE = new Function() { // from class: one.util.streamex.d
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Double.valueOf(((double[]) obj)[0]);
        }
    };
    public static final Object NONE = new Object();
    public static final Set<Collector.Characteristics> NO_CHARACTERISTICS = EnumSet.noneOf(Collector.Characteristics.class);

    public static class ArrayCollection extends AbstractCollection<Object> {
        private final Object[] arr;

        public ArrayCollection(Object[] objArr) {
            this.arr = objArr;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return Arrays.asList(this.arr).iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.arr.length;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return this.arr;
        }
    }

    public static class Box<A> implements Consumer<A> {
        A a;

        public Box(A a) {
            this.a = a;
        }

        @Override // java.util.function.Consumer
        public void accept(A a) {
            this.a = a;
        }
    }

    public static class CancelException extends Error {
        public CancelException() {
            super(null, null, false, false);
        }
    }

    public static final class CancellableCollectorImpl<T, A, R> extends CancellableCollector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Set<Collector.Characteristics> characteristics;
        private final BinaryOperator<A> combiner;
        private final Predicate<A> finished;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        public CancellableCollectorImpl(Supplier<A> supplier, BiConsumer<A, T> biConsumer, BinaryOperator<A> binaryOperator, Function<A, R> function, Predicate<A> predicate, Set<Collector.Characteristics> set) {
            this.supplier = supplier;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.finisher = function;
            this.finished = predicate;
            this.characteristics = set;
        }

        @Override // java.util.stream.Collector
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return this.characteristics;
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<A> combiner() {
            return this.combiner;
        }

        @Override // one.util.streamex.CancellableCollector
        public Predicate<A> finished() {
            return this.finished;
        }

        @Override // java.util.stream.Collector
        public Function<A, R> finisher() {
            return this.finisher;
        }

        @Override // java.util.stream.Collector
        public Supplier<A> supplier() {
            return this.supplier;
        }
    }

    public static abstract class CloneableSpliterator<T, S extends CloneableSpliterator<T, ?>> implements Cloneable, Spliterator<T> {
        public S doClone() {
            try {
                return (S) clone();
            } catch (CloneNotSupportedException unused) {
                uj0.a();
                return null;
            }
        }
    }

    public static final class PairBox<A, B> extends Box<A> {
        B b;

        public PairBox(A a, B b) {
            super(a);
            this.b = b;
        }

        public boolean equals(Object obj) {
            return obj != null && obj.getClass() == PairBox.class && Objects.equals(this.b, ((PairBox) obj).b);
        }

        public int hashCode() {
            B b = this.b;
            if (b == null) {
                return 0;
            }
            return b.hashCode();
        }
    }

    public interface TailSpliterator<T> extends Spliterator<T> {
        static <T> void forEachWithTail(Spliterator<T> spliterator, Consumer<? super T> consumer) {
            while (spliterator instanceof TailSpliterator) {
                spliterator = ((TailSpliterator) spliterator).forEachOrTail(consumer);
                if (spliterator == null) {
                    return;
                }
            }
            spliterator.forEachRemaining(consumer);
        }

        static <T> Spliterator<T> tryAdvanceWithTail(Spliterator<T> spliterator, Consumer<? super T> consumer) {
            while (spliterator instanceof TailSpliterator) {
                Spliterator<T> spliteratorTryAdvanceOrTail = ((TailSpliterator) spliterator).tryAdvanceOrTail(consumer);
                if (spliteratorTryAdvanceOrTail == null || spliteratorTryAdvanceOrTail == spliterator) {
                    return spliteratorTryAdvanceOrTail;
                }
                spliterator = spliteratorTryAdvanceOrTail;
            }
            if (spliterator.tryAdvance(consumer)) {
                return spliterator;
            }
            return null;
        }

        Spliterator<T> forEachOrTail(Consumer<? super T> consumer);

        Spliterator<T> tryAdvanceOrTail(Consumer<? super T> consumer);
    }

    static {
        Collector.Characteristics characteristics = Collector.Characteristics.UNORDERED;
        UNORDERED_CHARACTERISTICS = EnumSet.of(characteristics);
        Collector.Characteristics characteristics2 = Collector.Characteristics.IDENTITY_FINISH;
        UNORDERED_ID_CHARACTERISTICS = EnumSet.of(characteristics, characteristics2);
        ID_CHARACTERISTICS = EnumSet.of(characteristics2);
    }

    static /* synthetic */ boolean a(Object obj) {
        return true;
    }

    static <T> Predicate<T> alwaysTrue() {
        return new Predicate() { // from class: one.util.streamex.a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Internals.a(obj);
            }
        };
    }

    static <A> Predicate<A> finished(Collector<?, A, ?> collector) {
        if (collector instanceof CancellableCollector) {
            return ((CancellableCollector) collector).finished();
        }
        return null;
    }
}
