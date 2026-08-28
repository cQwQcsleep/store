package j$.util.stream;

import j$.util.C0088l;
import j$.util.C0091o;
import j$.util.C0093q;
import j$.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.m0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0156m0 extends AbstractC0100b implements InterfaceC0171p0 {
    @Override // j$.util.stream.InterfaceC0171p0
    public final C0093q findAny() {
        return (C0093q) D(I.d);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0093q findFirst() {
        return (C0093q) D(I.c);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 sorted() {
        return new L2(this, EnumC0129g3.q | EnumC0129g3.o, 0);
    }

    public void forEach(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        D(new O(longConsumer, false));
    }

    public void forEachOrdered(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        D(new O(longConsumer, true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static j$.util.N Z(j$.util.U u) {
        if (u instanceof j$.util.N) {
            return (j$.util.N) u;
        }
        if (P3.a) {
            P3.a(AbstractC0100b.class, "using LongStream.adapt(Spliterator<Long> s)");
            throw null;
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    @Override // j$.util.stream.AbstractC0100b
    final EnumC0134h3 I() {
        return EnumC0134h3.LONG_VALUE;
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 F(AbstractC0100b abstractC0100b, j$.util.U u, boolean z, IntFunction intFunction) {
        return A0.H(abstractC0100b, u, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U U(AbstractC0100b abstractC0100b, Supplier supplier, boolean z) {
        return new C0203v3(abstractC0100b, supplier, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean H(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        LongConsumer c0121f0;
        boolean zO;
        j$.util.N nZ = Z(u);
        if (interfaceC0182r2 instanceof LongConsumer) {
            c0121f0 = (LongConsumer) interfaceC0182r2;
        } else {
            if (P3.a) {
                P3.a(AbstractC0100b.class, "using LongStream.adapt(Sink<Long> s)");
                throw null;
            }
            Objects.requireNonNull(interfaceC0182r2);
            c0121f0 = new C0121f0(interfaceC0182r2);
        }
        do {
            zO = interfaceC0182r2.o();
            if (zO) {
                break;
            }
        } while (nZ.tryAdvance(c0121f0));
        return zO;
    }

    @Override // j$.util.stream.AbstractC0100b
    final E0 N(long j, IntFunction intFunction) {
        return A0.U(j);
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final j$.util.C iterator() {
        return j$.util.i0.h(spliterator());
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h
    public final j$.util.N spliterator() {
        return Z(super.spliterator());
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final E asDoubleStream() {
        return new C0194u(this, EnumC0129g3.n, 5);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final Stream boxed() {
        return new C0189t(this, 0, new r(22), 2);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 d() {
        Objects.requireNonNull(null);
        return new C0204w(this, EnumC0129g3.p | EnumC0129g3.n, 3);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final Stream mapToObj(LongFunction longFunction) {
        Objects.requireNonNull(longFunction);
        return new C0189t(this, EnumC0129g3.p | EnumC0129g3.n, longFunction, 2);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0116e0 x() {
        Objects.requireNonNull(null);
        return new C0199v(this, EnumC0129g3.p | EnumC0129g3.n, 4);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final E l() {
        Objects.requireNonNull(null);
        return new C0194u(this, EnumC0129g3.p | EnumC0129g3.n, 6);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 e(C0095a c0095a) {
        Objects.requireNonNull(c0095a);
        return new C0141j0(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, c0095a, 0);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 b() {
        Objects.requireNonNull(null);
        return new C0204w(this, EnumC0129g3.t, 5);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 peek(LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new C0141j0(this, longConsumer);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return A0.a0(this, 0L, j);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : A0.a0(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 a() {
        int i = o4.a;
        Objects.requireNonNull(null);
        return new Y3(this, o4.a, 0);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 c() {
        int i = o4.a;
        Objects.requireNonNull(null);
        return new a4(this, o4.b, 0);
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final InterfaceC0171p0 distinct() {
        return ((AbstractC0143j2) boxed()).distinct().mapToLong(new r(19));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final long sum() {
        return reduce(0L, new r(27));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0093q min() {
        return reduce(new r(18));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0093q max() {
        return reduce(new r(26));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0091o average() {
        long j = ((long[]) collect(new r(23), new r(24), new r(25)))[0];
        return j > 0 ? C0091o.d(r0[1] / j) : C0091o.a();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final long reduce(long j, LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return ((Long) D(new C1(EnumC0134h3.LONG_VALUE, longBinaryOperator, j))).longValue();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0088l summaryStatistics() {
        return (C0088l) collect(new C0170p(18), new r(17), new r(20));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer) {
        Objects.requireNonNull(biConsumer);
        C0175q c0175q = new C0175q(biConsumer, 2);
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(c0175q);
        return D(new G1(EnumC0134h3.LONG_VALUE, c0175q, objLongConsumer, supplier, 0));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final boolean w() {
        return ((Boolean) D(A0.b0(EnumC0210x0.ANY))).booleanValue();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final boolean r() {
        return ((Boolean) D(A0.b0(EnumC0210x0.ALL))).booleanValue();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final C0093q reduce(LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return (C0093q) D(new E1(EnumC0134h3.LONG_VALUE, longBinaryOperator, 0));
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final boolean n() {
        return ((Boolean) D(A0.b0(EnumC0210x0.NONE))).booleanValue();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final long[] toArray() {
        return (long[]) A0.Q((K0) E(new r(21))).e();
    }

    @Override // j$.util.stream.InterfaceC0171p0
    public final long count() {
        return ((Long) D(new I1(0))).longValue();
    }
}
