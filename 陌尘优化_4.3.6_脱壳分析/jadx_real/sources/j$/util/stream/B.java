package j$.util.stream;

import j$.util.C0085i;
import j$.util.C0091o;
import j$.util.InterfaceC0225u;
import j$.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class B extends AbstractC0100b implements E {
    @Override // j$.util.stream.E
    public final C0091o findAny() {
        return (C0091o) D(G.d);
    }

    @Override // j$.util.stream.E
    public final C0091o findFirst() {
        return (C0091o) D(G.c);
    }

    @Override // j$.util.stream.E
    public final E sorted() {
        return new J2(this, EnumC0129g3.q | EnumC0129g3.o, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static j$.util.H Z(j$.util.U u) {
        if (u instanceof j$.util.H) {
            return (j$.util.H) u;
        }
        if (P3.a) {
            P3.a(AbstractC0100b.class, "using DoubleStream.adapt(Spliterator<Double> s)");
            throw null;
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    @Override // j$.util.stream.E
    public void forEach(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        D(new M(doubleConsumer, false));
    }

    @Override // j$.util.stream.E
    public void forEachOrdered(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        D(new M(doubleConsumer, true));
    }

    @Override // j$.util.stream.AbstractC0100b
    final EnumC0134h3 I() {
        return EnumC0134h3.DOUBLE_VALUE;
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 F(AbstractC0100b abstractC0100b, j$.util.U u, boolean z, IntFunction intFunction) {
        return A0.F(abstractC0100b, u, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U U(AbstractC0100b abstractC0100b, Supplier supplier, boolean z) {
        return new C0183r3(abstractC0100b, supplier, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean H(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        DoubleConsumer c0165o;
        boolean zO;
        j$.util.H hZ = Z(u);
        if (interfaceC0182r2 instanceof DoubleConsumer) {
            c0165o = (DoubleConsumer) interfaceC0182r2;
        } else {
            if (P3.a) {
                P3.a(AbstractC0100b.class, "using DoubleStream.adapt(Sink<Double> s)");
                throw null;
            }
            Objects.requireNonNull(interfaceC0182r2);
            c0165o = new C0165o(interfaceC0182r2);
        }
        do {
            zO = interfaceC0182r2.o();
            if (zO) {
                break;
            }
        } while (hZ.tryAdvance(c0165o));
        return zO;
    }

    @Override // j$.util.stream.AbstractC0100b
    final E0 N(long j, IntFunction intFunction) {
        return A0.J(j);
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final InterfaceC0225u iterator() {
        return j$.util.i0.f(spliterator());
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h
    public final j$.util.H spliterator() {
        return Z(super.spliterator());
    }

    @Override // j$.util.stream.E
    public final Stream boxed() {
        return new C0189t(this, 0, new C0170p(26), 0);
    }

    @Override // j$.util.stream.E
    public final E d() {
        Objects.requireNonNull(null);
        return new C0194u(this, EnumC0129g3.p | EnumC0129g3.n, 0);
    }

    @Override // j$.util.stream.E
    public final Stream mapToObj(DoubleFunction doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return new C0189t(this, EnumC0129g3.p | EnumC0129g3.n, doubleFunction, 0);
    }

    @Override // j$.util.stream.E
    public final InterfaceC0116e0 u() {
        Objects.requireNonNull(null);
        return new C0199v(this, EnumC0129g3.p | EnumC0129g3.n, 0);
    }

    @Override // j$.util.stream.E
    public final InterfaceC0171p0 j() {
        Objects.requireNonNull(null);
        return new C0204w(this, EnumC0129g3.p | EnumC0129g3.n, 0);
    }

    @Override // j$.util.stream.E
    public final E e(C0095a c0095a) {
        Objects.requireNonNull(c0095a);
        return new C0214y(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, c0095a, 0);
    }

    @Override // j$.util.stream.E
    public final E b() {
        Objects.requireNonNull(null);
        return new C0194u(this, EnumC0129g3.t, 2);
    }

    @Override // j$.util.stream.E
    public final E peek(DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return new C0214y(this, doubleConsumer);
    }

    @Override // j$.util.stream.E
    public final E limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return A0.W(this, 0L, j);
    }

    @Override // j$.util.stream.E
    public final E skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : A0.W(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.E
    public final E a() {
        int i = o4.a;
        Objects.requireNonNull(null);
        return new c4(this, o4.a, 0);
    }

    @Override // j$.util.stream.E
    public final E c() {
        int i = o4.a;
        Objects.requireNonNull(null);
        return new e4(this, o4.b, 0);
    }

    @Override // j$.util.stream.E
    public final E distinct() {
        return ((AbstractC0143j2) boxed()).distinct().mapToDouble(new C0170p(27));
    }

    @Override // j$.util.stream.E
    public final double sum() {
        double[] dArr = (double[]) collect(new r(0), new C0170p(3), new C0170p(0));
        int i = AbstractC0145k.a;
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        return (Double.isNaN(d) && Double.isInfinite(d2)) ? d2 : d;
    }

    @Override // j$.util.stream.E
    public final C0091o min() {
        return reduce(new C0170p(22));
    }

    @Override // j$.util.stream.E
    public final C0091o max() {
        return reduce(new C0170p(29));
    }

    @Override // j$.util.stream.E
    public final C0091o average() {
        double[] dArr = (double[]) collect(new C0170p(23), new C0170p(1), new C0170p(2));
        if (dArr[2] <= 0.0d) {
            return C0091o.a();
        }
        int i = AbstractC0145k.a;
        double d = dArr[0] + dArr[1];
        double d2 = dArr[dArr.length - 1];
        if (Double.isNaN(d) && Double.isInfinite(d2)) {
            d = d2;
        }
        return C0091o.d(d / dArr[2]);
    }

    @Override // j$.util.stream.E
    public final C0085i summaryStatistics() {
        return (C0085i) collect(new C0170p(16), new C0170p(24), new C0170p(25));
    }

    @Override // j$.util.stream.E
    public final Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        C0175q c0175q = new C0175q(biConsumer, 0);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(c0175q);
        return D(new G1(EnumC0134h3.DOUBLE_VALUE, c0175q, objDoubleConsumer, supplier, 1));
    }

    @Override // j$.util.stream.E
    public final boolean p() {
        return ((Boolean) D(A0.X(EnumC0210x0.ANY))).booleanValue();
    }

    @Override // j$.util.stream.E
    public final boolean i() {
        return ((Boolean) D(A0.X(EnumC0210x0.ALL))).booleanValue();
    }

    @Override // j$.util.stream.E
    public final boolean y() {
        return ((Boolean) D(A0.X(EnumC0210x0.NONE))).booleanValue();
    }

    @Override // j$.util.stream.E
    public final double[] toArray() {
        return (double[]) A0.O((G0) E(new C0170p(28))).e();
    }

    @Override // j$.util.stream.E
    public final double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return ((Double) D(new K1(EnumC0134h3.DOUBLE_VALUE, doubleBinaryOperator, d))).doubleValue();
    }

    @Override // j$.util.stream.E
    public final C0091o reduce(DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return (C0091o) D(new E1(EnumC0134h3.DOUBLE_VALUE, doubleBinaryOperator, 1));
    }

    @Override // j$.util.stream.E
    public final long count() {
        return ((Long) D(new I1(1))).longValue();
    }
}
