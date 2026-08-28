package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.BaseStream;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* renamed from: j$.util.stream.d3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0114d3 implements java.util.stream.Stream {
    public final /* synthetic */ Stream a;

    private /* synthetic */ C0114d3(Stream stream) {
        this.a = stream;
    }

    public static /* synthetic */ java.util.stream.Stream k(Stream stream) {
        if (stream == null) {
            return null;
        }
        return stream instanceof C0109c3 ? ((C0109c3) stream).a : new C0114d3(stream);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ boolean allMatch(Predicate predicate) {
        return this.a.allMatch(predicate);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ boolean anyMatch(Predicate predicate) {
        return this.a.anyMatch(predicate);
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() throws Exception {
        this.a.close();
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        return this.a.collect(supplier, biConsumer, biConsumer2);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Object collect(Collector collector) {
        return this.a.h(C0140j.a(collector));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream distinct() {
        return k(this.a.distinct());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream dropWhile(Predicate predicate) {
        return k(this.a.dropWhile(predicate));
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Stream stream = this.a;
        if (obj instanceof C0114d3) {
            obj = ((C0114d3) obj).a;
        }
        return stream.equals(obj);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream filter(Predicate predicate) {
        return k(this.a.filter(predicate));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Optional findAny() {
        return AbstractC0078b.o(this.a.findAny());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Optional findFirst() {
        return AbstractC0078b.o(this.a.findFirst());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream flatMap(Function function) {
        return k(this.a.e(A0.R(function)));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ DoubleStream flatMapToDouble(Function function) {
        return D.k(this.a.z(A0.R(function)));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ IntStream flatMapToInt(Function function) {
        return C0111d0.k(this.a.t(A0.R(function)));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ LongStream flatMapToLong(Function function) {
        return C0166o0.k(this.a.o(A0.R(function)));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ void forEach(Consumer consumer) {
        this.a.forEach(consumer);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ void forEachOrdered(Consumer consumer) {
        this.a.forEachOrdered(consumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream limit(long j) {
        return k(this.a.limit(j));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream map(Function function) {
        return k(this.a.map(function));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
        return D.k(this.a.mapToDouble(toDoubleFunction));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ IntStream mapToInt(ToIntFunction toIntFunction) {
        return C0111d0.k(this.a.mapToInt(toIntFunction));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ LongStream mapToLong(ToLongFunction toLongFunction) {
        return C0166o0.k(this.a.mapToLong(toLongFunction));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Optional max(Comparator comparator) {
        return AbstractC0078b.o(this.a.max(comparator));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Optional min(Comparator comparator) {
        return AbstractC0078b.o(this.a.min(comparator));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ boolean noneMatch(Predicate predicate) {
        return this.a.noneMatch(predicate);
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream onClose(Runnable runnable) {
        return C0125g.k(this.a.onClose(runnable));
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream parallel() {
        return C0125g.k(this.a.parallel());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream peek(Consumer consumer) {
        return k(this.a.peek(consumer));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        return this.a.reduce(obj, biFunction, binaryOperator);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
        return this.a.reduce(obj, binaryOperator);
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Optional reduce(BinaryOperator binaryOperator) {
        return AbstractC0078b.o(this.a.reduce(binaryOperator));
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream sequential() {
        return C0125g.k(this.a.sequential());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream skip(long j) {
        return k(this.a.skip(j));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream sorted() {
        return k(this.a.sorted());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream sorted(Comparator comparator) {
        return k(this.a.sorted(comparator));
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ Spliterator spliterator() {
        return j$.util.T.a(this.a.spliterator());
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ java.util.stream.Stream takeWhile(Predicate predicate) {
        return k(this.a.takeWhile(predicate));
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Object[] toArray() {
        return this.a.toArray();
    }

    @Override // java.util.stream.Stream
    public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return this.a.toArray(intFunction);
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream unordered() {
        return C0125g.k(this.a.unordered());
    }
}
