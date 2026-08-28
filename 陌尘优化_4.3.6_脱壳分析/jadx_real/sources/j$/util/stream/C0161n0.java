package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.C0088l;
import j$.util.C0091o;
import j$.util.C0093q;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/* renamed from: j$.util.stream.n0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class C0161n0 implements InterfaceC0171p0 {
    public final /* synthetic */ LongStream a;

    private /* synthetic */ C0161n0(LongStream longStream) {
        this.a = longStream;
    }

    public static /* synthetic */ InterfaceC0171p0 k(LongStream longStream) {
        if (longStream == null) {
            return null;
        }
        return longStream instanceof C0166o0 ? ((C0166o0) longStream).a : new C0161n0(longStream);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 a() {
        return k(this.a.takeWhile(null));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ E asDoubleStream() {
        return C.k(this.a.asDoubleStream());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ C0091o average() {
        return AbstractC0078b.l(this.a.average());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 b() {
        return k(this.a.filter(null));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ Stream boxed() {
        return C0109c3.k(this.a.boxed());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 c() {
        return k(this.a.dropWhile(null));
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        this.a.close();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        return this.a.collect(supplier, objLongConsumer, biConsumer);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ long count() {
        return this.a.count();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 d() {
        return k(this.a.map(null));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 distinct() {
        return k(this.a.distinct());
    }

    public final /* synthetic */ boolean equals(Object obj) {
        LongStream longStream = this.a;
        if (obj instanceof C0161n0) {
            obj = ((C0161n0) obj).a;
        }
        return longStream.equals(obj);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ C0093q findAny() {
        return AbstractC0078b.n(this.a.findAny());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ C0093q findFirst() {
        return AbstractC0078b.n(this.a.findFirst());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ void forEach(LongConsumer longConsumer) {
        this.a.forEach(longConsumer);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ void forEachOrdered(LongConsumer longConsumer) {
        this.a.forEachOrdered(longConsumer);
    }

    public final /* synthetic */ int hashCode() {
        return this.a.hashCode();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ boolean isParallel() {
        return this.a.isParallel();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.PrimitiveIterator$OfLong] */
    @Override // j$.util.stream.InterfaceC0171p0, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ j$.util.C iterator() {
        return j$.util.A.a(this.a.iterator());
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ Iterator iterator() {
        return this.a.iterator();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ E l() {
        return C.k(this.a.mapToDouble(null));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 limit(long j) {
        return k(this.a.limit(j));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ Stream mapToObj(LongFunction longFunction) {
        return C0109c3.k(this.a.mapToObj(longFunction));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ C0093q max() {
        return AbstractC0078b.n(this.a.max());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ C0093q min() {
        return AbstractC0078b.n(this.a.min());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ boolean n() {
        return this.a.noneMatch(null);
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h onClose(Runnable runnable) {
        return C0120f.k(this.a.onClose(runnable));
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h parallel() {
        return C0120f.k(this.a.parallel());
    }

    @Override // j$.util.stream.InterfaceC0171p0, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0171p0 parallel() {
        return k(this.a.parallel());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 peek(LongConsumer longConsumer) {
        return k(this.a.peek(longConsumer));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ boolean r() {
        return this.a.allMatch(null);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ long reduce(long j, LongBinaryOperator longBinaryOperator) {
        return this.a.reduce(j, longBinaryOperator);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ C0093q reduce(LongBinaryOperator longBinaryOperator) {
        return AbstractC0078b.n(this.a.reduce(longBinaryOperator));
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0130h sequential() {
        return C0120f.k(this.a.sequential());
    }

    @Override // j$.util.stream.InterfaceC0171p0, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* synthetic */ InterfaceC0171p0 sequential() {
        return k(this.a.sequential());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 skip(long j) {
        return k(this.a.skip(j));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0171p0 sorted() {
        return k(this.a.sorted());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Spliterator$OfLong] */
    @Override // j$.util.stream.InterfaceC0171p0, j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.N spliterator() {
        return j$.util.L.a(this.a.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ j$.util.U spliterator() {
        return j$.util.S.a(this.a.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ long sum() {
        return this.a.sum();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ long[] toArray() {
        return this.a.toArray();
    }

    @Override // j$.util.stream.InterfaceC0130h
    public final /* synthetic */ InterfaceC0130h unordered() {
        return C0120f.k(this.a.unordered());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ boolean w() {
        return this.a.anyMatch(null);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final /* synthetic */ InterfaceC0116e0 x() {
        return C0106c0.k(this.a.mapToInt(null));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0088l summaryStatistics() {
        this.a.summaryStatistics();
        throw new Error("Java 8+ API desugaring (library desugaring) cannot convert from java.util.LongSummaryStatistics");
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 e(C0095a c0095a) {
        LongStream longStream = this.a;
        C0095a c0095a2 = new C0095a(9);
        c0095a2.b = c0095a;
        return k(longStream.flatMap(c0095a2));
    }
}
