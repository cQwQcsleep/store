package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.C0086j;
import j$.util.C0091o;
import j$.util.C0092p;
import j$.util.C0227w;
import j$.util.InterfaceC0229y;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/* renamed from: j$.util.stream.c0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0106c0 implements InterfaceC0116e0 {
    public final /* synthetic */ IntStream a;

    private /* synthetic */ C0106c0(IntStream intStream) {
        this.a = intStream;
    }

    public static /* synthetic */ InterfaceC0116e0 k(IntStream intStream) {
        if (intStream == null) {
            return null;
        }
        return intStream instanceof C0111d0 ? ((C0111d0) intStream).a : new C0106c0(intStream);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 a() {
        return k(this.a.takeWhile(null));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ E asDoubleStream() {
        return C.k(this.a.asDoubleStream());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0171p0 asLongStream() {
        return C0161n0.k(this.a.asLongStream());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ C0091o average() {
        return AbstractC0078b.l(this.a.average());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 b() {
        return k(this.a.filter(null));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ Stream boxed() {
        return C0109c3.k(this.a.boxed());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 c() {
        return k(this.a.dropWhile(null));
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objIntConsumer, biConsumer);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 d() {
        return k(this.a.map(null));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 distinct() {
        return k(this.a.distinct());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        IntStream intStream = this.a;
        if (obj instanceof C0106c0) {
            obj = ((C0106c0) obj).a;
        }
        return intStream.equals(obj);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ E f() {
        return C.k(this.a.mapToDouble(null));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ C0092p findAny() {
        return AbstractC0078b.m(this.a.findAny());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ C0092p findFirst() {
        return AbstractC0078b.m(this.a.findFirst());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ void forEach(IntConsumer intConsumer) {
        this.a.forEach(intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ void forEachOrdered(IntConsumer intConsumer) {
        this.a.forEachOrdered(intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ boolean g() {
        return this.a.noneMatch(null);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfInt] */
    @Override // j$.util.stream.InterfaceC0116e0, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0229y iterator() {
        return C0227w.a(this.a.iterator());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 limit(long j) {
        return k(this.a.limit(j));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0171p0 m() {
        return C0161n0.k(this.a.mapToLong(null));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ Stream mapToObj(IntFunction intFunction) {
        return C0109c3.k(this.a.mapToObj(intFunction));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ C0092p max() {
        return AbstractC0078b.m(this.a.max());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ C0092p min() {
        return AbstractC0078b.m(this.a.min());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h onClose(Runnable runnable) {
        return C0120f.k(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.InterfaceC0116e0, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0116e0 parallel() {
        return k(this.a.parallel());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h parallel() {
        return C0120f.k(this.a.parallel());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 peek(IntConsumer intConsumer) {
        return k(this.a.peek(intConsumer));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ int reduce(int i, IntBinaryOperator intBinaryOperator) {
        return this.a.reduce(i, intBinaryOperator);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ C0092p reduce(IntBinaryOperator intBinaryOperator) {
        return AbstractC0078b.m(this.a.reduce(intBinaryOperator));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ boolean s() {
        return this.a.anyMatch(null);
    }

    @Override // j$.util.stream.InterfaceC0116e0, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0116e0 sequential() {
        return k(this.a.sequential());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h sequential() {
        return C0120f.k(this.a.sequential());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 skip(long j) {
        return k(this.a.skip(j));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ InterfaceC0116e0 sorted() {
        return k(this.a.sorted());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfInt] */
    @Override // j$.util.stream.InterfaceC0116e0, j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.K spliterator() {
        return j$.util.I.a(this.a.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.U spliterator() {
        return j$.util.S.a(this.a.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ int sum() {
        return this.a.sum();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ int[] toArray() {
        return this.a.toArray();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h unordered() {
        return C0120f.k(this.a.unordered());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final /* synthetic */ boolean v() {
        return this.a.allMatch(null);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0086j summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.IntSummaryStatistics");
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 q(S0 s0) {
        IntStream intStream = this.a;
        S0 s02 = new S0();
        s02.a = s0;
        return k(intStream.flatMap(s02));
    }
}
