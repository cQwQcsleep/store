package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.C0090n;
import java.util.Comparator;
import java.util.Iterator;
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

/* renamed from: j$.util.stream.c3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0109c3 implements Stream {
    public final /* synthetic */ java.util.stream.Stream a;

    private /* synthetic */ C0109c3(java.util.stream.Stream stream) {
        this.a = stream;
    }

    public static /* synthetic */ Stream k(java.util.stream.Stream stream) {
        if (stream == null) {
            return null;
        }
        return stream instanceof C0114d3 ? ((C0114d3) stream).a : new C0109c3(stream);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ boolean allMatch(Predicate predicate) {
        return this.a.allMatch(predicate);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ boolean anyMatch(Predicate predicate) {
        return this.a.anyMatch(predicate);
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        return this.a.collect(supplier, biConsumer, biConsumer2);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream distinct() {
        return k(this.a.distinct());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream dropWhile(Predicate predicate) {
        return k(this.a.dropWhile(predicate));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream e(C0095a c0095a) {
        return k(this.a.flatMap(A0.R(c0095a)));
    }

    public final /* synthetic */ boolean equals(Object obj) {
        java.util.stream.Stream stream = this.a;
        if (obj instanceof C0109c3) {
            obj = ((C0109c3) obj).a;
        }
        return stream.equals(obj);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream filter(Predicate predicate) {
        return k(this.a.filter(predicate));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ C0090n findAny() {
        return AbstractC0078b.k(this.a.findAny());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ C0090n findFirst() {
        return AbstractC0078b.k(this.a.findFirst());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ void forEach(Consumer consumer) {
        this.a.forEach(consumer);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ void forEachOrdered(Consumer consumer) {
        this.a.forEachOrdered(consumer);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object h(C0140j c0140j) {
        return this.a.collect(c0140j == null ? null : c0140j.a);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream limit(long j) {
        return k(this.a.limit(j));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream map(Function function) {
        return k(this.a.map(function));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ E mapToDouble(ToDoubleFunction toDoubleFunction) {
        return C.k(this.a.mapToDouble(toDoubleFunction));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ InterfaceC0116e0 mapToInt(ToIntFunction toIntFunction) {
        return C0106c0.k(this.a.mapToInt(toIntFunction));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ InterfaceC0171p0 mapToLong(ToLongFunction toLongFunction) {
        return C0161n0.k(this.a.mapToLong(toLongFunction));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ C0090n max(Comparator comparator) {
        return AbstractC0078b.k(this.a.max(comparator));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ C0090n min(Comparator comparator) {
        return AbstractC0078b.k(this.a.min(comparator));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ boolean noneMatch(Predicate predicate) {
        return this.a.noneMatch(predicate);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ InterfaceC0171p0 o(C0095a c0095a) {
        return C0161n0.k(this.a.flatMapToLong(A0.R(c0095a)));
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h onClose(Runnable runnable) {
        return C0120f.k(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h parallel() {
        return C0120f.k(this.a.parallel());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream peek(Consumer consumer) {
        return k(this.a.peek(consumer));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ C0090n reduce(BinaryOperator binaryOperator) {
        return AbstractC0078b.k(this.a.reduce(binaryOperator));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        return this.a.reduce(obj, biFunction, binaryOperator);
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object reduce(Object obj, BinaryOperator binaryOperator) {
        return this.a.reduce(obj, binaryOperator);
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h sequential() {
        return C0120f.k(this.a.sequential());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream skip(long j) {
        return k(this.a.skip(j));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream sorted() {
        return k(this.a.sorted());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream sorted(Comparator comparator) {
        return k(this.a.sorted(comparator));
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.U spliterator() {
        return j$.util.S.a(this.a.spliterator());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ InterfaceC0116e0 t(C0095a c0095a) {
        return C0106c0.k(this.a.flatMapToInt(A0.R(c0095a)));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Stream takeWhile(Predicate predicate) {
        return k(this.a.takeWhile(predicate));
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object[] toArray() {
        return this.a.toArray();
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return this.a.toArray(intFunction);
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h unordered() {
        return C0120f.k(this.a.unordered());
    }

    @Override // j$.util.stream.Stream
    public final /* synthetic */ E z(C0095a c0095a) {
        return C.k(this.a.flatMapToDouble(A0.R(c0095a)));
    }
}
