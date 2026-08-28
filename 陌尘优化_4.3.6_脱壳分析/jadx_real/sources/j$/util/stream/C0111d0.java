package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.C0228x;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/* renamed from: j$.util.stream.d0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0111d0 implements IntStream {
    public final /* synthetic */ InterfaceC0116e0 a;

    private /* synthetic */ C0111d0(InterfaceC0116e0 interfaceC0116e0) {
        this.a = interfaceC0116e0;
    }

    public static /* synthetic */ IntStream k(InterfaceC0116e0 interfaceC0116e0) {
        if (interfaceC0116e0 == null) {
            return null;
        }
        return interfaceC0116e0 instanceof C0106c0 ? ((C0106c0) interfaceC0116e0).a : new C0111d0(interfaceC0116e0);
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ boolean allMatch(IntPredicate intPredicate) {
        return this.a.v();
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ boolean anyMatch(IntPredicate intPredicate) {
        return this.a.s();
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ DoubleStream asDoubleStream() {
        return D.k(this.a.asDoubleStream());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ LongStream asLongStream() {
        return C0166o0.k(this.a.asLongStream());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ OptionalDouble average() {
        return AbstractC0078b.p(this.a.average());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ java.util.stream.Stream boxed() {
        return C0114d3.k(this.a.boxed());
    }

    @Override // java.util.stream.BaseStream, java.lang.AutoCloseable
    public final /* synthetic */ void close() throws Exception {
        this.a.close();
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objIntConsumer, biConsumer);
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream distinct() {
        return k(this.a.distinct());
    }

    public final /* synthetic */ IntStream dropWhile(IntPredicate intPredicate) {
        return k(this.a.c());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        InterfaceC0116e0 interfaceC0116e0 = this.a;
        if (obj instanceof C0111d0) {
            obj = ((C0111d0) obj).a;
        }
        return interfaceC0116e0.equals(obj);
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream filter(IntPredicate intPredicate) {
        return k(this.a.b());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ OptionalInt findAny() {
        return AbstractC0078b.q(this.a.findAny());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ OptionalInt findFirst() {
        return AbstractC0078b.q(this.a.findFirst());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ void forEach(IntConsumer intConsumer) {
        this.a.forEach(intConsumer);
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
        this.a.forEachOrdered(intConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    public final /* synthetic */ Iterator<Integer> iterator() {
        return this.a.iterator();
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    /* renamed from: iterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Iterator<Integer> iterator2() {
        return C0228x.a(this.a.iterator());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream limit(long j) {
        return k(this.a.limit(j));
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream map(IntUnaryOperator intUnaryOperator) {
        return k(this.a.d());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction) {
        return D.k(this.a.f());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ LongStream mapToLong(IntToLongFunction intToLongFunction) {
        return C0166o0.k(this.a.m());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ java.util.stream.Stream mapToObj(IntFunction intFunction) {
        return C0114d3.k(this.a.mapToObj(intFunction));
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ OptionalInt max() {
        return AbstractC0078b.q(this.a.max());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ OptionalInt min() {
        return AbstractC0078b.q(this.a.min());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ boolean noneMatch(IntPredicate intPredicate) {
        return this.a.g();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream onClose(Runnable runnable) {
        return C0125g.k(this.a.onClose(runnable));
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    public final /* synthetic */ BaseStream parallel() {
        return C0125g.k(this.a.parallel());
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    public final /* synthetic */ IntStream parallel() {
        return k(this.a.parallel());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream peek(IntConsumer intConsumer) {
        return k(this.a.peek(intConsumer));
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
        return this.a.reduce(i, intBinaryOperator);
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        return AbstractC0078b.q(this.a.reduce(intBinaryOperator));
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    public final /* synthetic */ BaseStream sequential() {
        return C0125g.k(this.a.sequential());
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    public final /* synthetic */ IntStream sequential() {
        return k(this.a.sequential());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream skip(long j) {
        return k(this.a.skip(j));
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ IntStream sorted() {
        return k(this.a.sorted());
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    public final /* synthetic */ Spliterator<Integer> spliterator() {
        return j$.util.J.a(this.a.spliterator());
    }

    @Override // java.util.stream.IntStream, java.util.stream.BaseStream
    /* renamed from: spliterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Spliterator<Integer> spliterator2() {
        return j$.util.T.a(this.a.spliterator());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ int sum() {
        return this.a.sum();
    }

    public final /* synthetic */ IntStream takeWhile(IntPredicate intPredicate) {
        return k(this.a.a());
    }

    @Override // java.util.stream.IntStream
    public final /* synthetic */ int[] toArray() {
        return this.a.toArray();
    }

    @Override // java.util.stream.BaseStream
    public final /* synthetic */ BaseStream unordered() {
        return C0125g.k(this.a.unordered());
    }

    @Override // java.util.stream.IntStream
    public final IntSummaryStatistics summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert to java.util.IntSummaryStatistics");
    }

    @Override // java.util.stream.IntStream
    public final IntStream flatMap(IntFunction intFunction) {
        InterfaceC0116e0 interfaceC0116e0 = this.a;
        S0 s0 = new S0();
        s0.a = intFunction;
        return k(interfaceC0116e0.q(s0));
    }
}
