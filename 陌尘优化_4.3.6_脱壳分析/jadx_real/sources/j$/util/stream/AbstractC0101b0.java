package j$.util.stream;

import j$.util.C0086j;
import j$.util.C0091o;
import j$.util.C0092p;
import j$.util.InterfaceC0229y;
import j$.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.b0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0101b0 extends AbstractC0100b implements InterfaceC0116e0 {
    @Override // j$.util.stream.InterfaceC0116e0
    public final C0092p findAny() {
        return (C0092p) D(H.d);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0092p findFirst() {
        return (C0092p) D(H.c);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 sorted() {
        return new K2(this, EnumC0129g3.q | EnumC0129g3.o, 0);
    }

    public void forEach(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        D(new N(intConsumer, false));
    }

    public void forEachOrdered(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        D(new N(intConsumer, true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static j$.util.K Z(j$.util.U u) {
        if (u instanceof j$.util.K) {
            return (j$.util.K) u;
        }
        if (P3.a) {
            P3.a(AbstractC0100b.class, "using IntStream.adapt(Spliterator<Integer> s)");
            throw null;
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    @Override // j$.util.stream.AbstractC0100b
    final EnumC0134h3 I() {
        return EnumC0134h3.INT_VALUE;
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 F(AbstractC0100b abstractC0100b, j$.util.U u, boolean z, IntFunction intFunction) {
        return A0.G(abstractC0100b, u, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U U(AbstractC0100b abstractC0100b, Supplier supplier, boolean z) {
        return new C0193t3(abstractC0100b, supplier, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean H(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        IntConsumer u2;
        boolean zO;
        j$.util.K kZ = Z(u);
        if (interfaceC0182r2 instanceof IntConsumer) {
            u2 = (IntConsumer) interfaceC0182r2;
        } else {
            if (P3.a) {
                P3.a(AbstractC0100b.class, "using IntStream.adapt(Sink<Integer> s)");
                throw null;
            }
            Objects.requireNonNull(interfaceC0182r2);
            u2 = new U(interfaceC0182r2);
        }
        do {
            zO = interfaceC0182r2.o();
            if (zO) {
                break;
            }
        } while (kZ.tryAdvance(u2));
        return zO;
    }

    @Override // j$.util.stream.AbstractC0100b
    final E0 N(long j, IntFunction intFunction) {
        return A0.S(j);
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final InterfaceC0229y iterator() {
        return j$.util.i0.g(spliterator());
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h
    public final j$.util.K spliterator() {
        return Z(super.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0171p0 asLongStream() {
        return new C0204w(this, 0, 1);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final E asDoubleStream() {
        return new C0194u(this, 0, 3);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final Stream boxed() {
        return new C0189t(this, 0, new r(8), 1);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 d() {
        Objects.requireNonNull(null);
        return new C0199v(this, EnumC0129g3.p | EnumC0129g3.n, 1);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final Stream mapToObj(IntFunction intFunction) {
        Objects.requireNonNull(intFunction);
        return new C0189t(this, EnumC0129g3.p | EnumC0129g3.n, intFunction, 1);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0171p0 m() {
        Objects.requireNonNull(null);
        return new C0204w(this, EnumC0129g3.p | EnumC0129g3.n, 2);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final E f() {
        Objects.requireNonNull(null);
        return new C0194u(this, EnumC0129g3.p | EnumC0129g3.n, 4);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final int reduce(int i, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return ((Integer) D(new R1(EnumC0134h3.INT_VALUE, intBinaryOperator, i))).intValue();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 q(S0 s0) {
        Objects.requireNonNull(s0);
        return new W(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, s0, 1);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0092p reduce(IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return (C0092p) D(new E1(EnumC0134h3.INT_VALUE, intBinaryOperator, 3));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 b() {
        Objects.requireNonNull(null);
        return new C0199v(this, EnumC0129g3.t, 3);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 peek(IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new W(this, intConsumer);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return A0.Y(this, 0L, j);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : A0.Y(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 a() {
        int i = o4.a;
        Objects.requireNonNull(null);
        return new U3(this, o4.a, 0);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 c() {
        int i = o4.a;
        Objects.requireNonNull(null);
        return new W3(this, o4.b, 0);
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final long count() {
        return ((Long) D(new I1(3))).longValue();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final InterfaceC0116e0 distinct() {
        return ((AbstractC0143j2) boxed()).distinct().mapToInt(new r(7));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final int sum() {
        return reduce(0, new r(12));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0092p min() {
        return reduce(new r(9));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0092p max() {
        return reduce(new r(13));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0091o average() {
        long j = ((long[]) collect(new r(14), new r(15), new r(16)))[0];
        return j > 0 ? C0091o.d(r0[1] / j) : C0091o.a();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final C0086j summaryStatistics() {
        return (C0086j) collect(new C0170p(17), new r(10), new r(11));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        C0175q c0175q = new C0175q(biConsumer, 1);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(c0175q);
        return D(new G1(EnumC0134h3.INT_VALUE, c0175q, objIntConsumer, supplier, 4));
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final boolean s() {
        return ((Boolean) D(A0.Z(EnumC0210x0.ANY))).booleanValue();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final boolean v() {
        return ((Boolean) D(A0.Z(EnumC0210x0.ALL))).booleanValue();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final boolean g() {
        return ((Boolean) D(A0.Z(EnumC0210x0.NONE))).booleanValue();
    }

    @Override // j$.util.stream.InterfaceC0116e0
    public final int[] toArray() {
        return (int[]) A0.P((I0) E(new r(6))).e();
    }
}
