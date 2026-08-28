package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.C0085i;
import j$.util.C0091o;
import j$.util.C0094s;
import j$.util.InterfaceC0225u;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C implements E {
    public final /* synthetic */ DoubleStream a;

    private /* synthetic */ C(DoubleStream doubleStream) {
        this.a = doubleStream;
    }

    public static /* synthetic */ E k(DoubleStream doubleStream) {
        if (doubleStream == null) {
            return null;
        }
        return doubleStream instanceof D ? ((D) doubleStream).a : new C(doubleStream);
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E a() {
        return k(this.a.takeWhile(null));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ C0091o average() {
        return AbstractC0078b.l(this.a.average());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E b() {
        return k(this.a.filter(null));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ Stream boxed() {
        return C0109c3.k(this.a.boxed());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E c() {
        return k(this.a.dropWhile(null));
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objDoubleConsumer, biConsumer);
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E d() {
        return k(this.a.map(null));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E distinct() {
        return k(this.a.distinct());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        DoubleStream doubleStream = this.a;
        if (obj instanceof C) {
            obj = ((C) obj).a;
        }
        return doubleStream.equals(obj);
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ C0091o findAny() {
        return AbstractC0078b.l(this.a.findAny());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ C0091o findFirst() {
        return AbstractC0078b.l(this.a.findFirst());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ void forEach(DoubleConsumer doubleConsumer) {
        this.a.forEach(doubleConsumer);
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ void forEachOrdered(DoubleConsumer doubleConsumer) {
        this.a.forEachOrdered(doubleConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ boolean i() {
        return this.a.allMatch(null);
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfDouble] */
    @Override // j$.util.stream.E
    public final /* synthetic */ InterfaceC0225u iterator() {
        return C0094s.a(this.a.iterator());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ InterfaceC0171p0 j() {
        return C0161n0.k(this.a.mapToLong(null));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E limit(long j) {
        return k(this.a.limit(j));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ Stream mapToObj(DoubleFunction doubleFunction) {
        return C0109c3.k(this.a.mapToObj(doubleFunction));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ C0091o max() {
        return AbstractC0078b.l(this.a.max());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ C0091o min() {
        return AbstractC0078b.l(this.a.min());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h onClose(Runnable runnable) {
        return C0120f.k(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ boolean p() {
        return this.a.anyMatch(null);
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E parallel() {
        return k(this.a.parallel());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h parallel() {
        return C0120f.k(this.a.parallel());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E peek(DoubleConsumer doubleConsumer) {
        return k(this.a.peek(doubleConsumer));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        return this.a.reduce(d, doubleBinaryOperator);
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ C0091o reduce(DoubleBinaryOperator doubleBinaryOperator) {
        return AbstractC0078b.l(this.a.reduce(doubleBinaryOperator));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E sequential() {
        return k(this.a.sequential());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h sequential() {
        return C0120f.k(this.a.sequential());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E skip(long j) {
        return k(this.a.skip(j));
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ E sorted() {
        return k(this.a.sorted());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfDouble] */
    @Override // j$.util.stream.E, j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.H spliterator() {
        return j$.util.F.a(this.a.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.U spliterator() {
        return j$.util.S.a(this.a.spliterator());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ double sum() {
        return this.a.sum();
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ double[] toArray() {
        return this.a.toArray();
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ InterfaceC0116e0 u() {
        return C0106c0.k(this.a.mapToInt(null));
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h unordered() {
        return C0120f.k(this.a.unordered());
    }

    @Override // j$.util.stream.E
    public final /* synthetic */ boolean y() {
        return this.a.noneMatch(null);
    }

    @Override // j$.util.stream.E
    public final C0085i summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.DoubleSummaryStatistics");
    }

    @Override // j$.util.stream.E
    public final E e(C0095a c0095a) {
        DoubleStream doubleStream = this.a;
        C0095a c0095a2 = new C0095a(7);
        c0095a2.b = c0095a;
        return k(doubleStream.flatMap(c0095a2));
    }
}
