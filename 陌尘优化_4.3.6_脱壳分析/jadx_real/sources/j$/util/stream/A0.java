package j$.util.stream;

import com.shadow.okhttp3.internal.http2.Http2;
import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;

/* loaded from: /workspace/unpacked/classes3.dex */
public abstract /* synthetic */ class A0 implements M3 {
    private static final C0122f1 a = new C0122f1();
    private static final I0 b = new C0112d1();
    private static final K0 c = new C0117e1();
    private static final G0 d = new C0107c1();
    private static final int[] e = new int[0];
    private static final long[] f = new long[0];
    private static final double[] g = new double[0];

    static long B(long j, long j2) {
        long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
        if (j3 >= 0) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    @Override // j$.util.stream.M3
    public /* synthetic */ int d() {
        return 0;
    }

    public abstract W1 e0();

    public static C0095a R(Function function) {
        C0095a c0095a = new C0095a(8);
        c0095a.b = function;
        return c0095a;
    }

    static long A(long j, long j2, long j3) {
        if (j >= 0) {
            return Math.max(-1L, Math.min(j - j2, j3));
        }
        return -1L;
    }

    public static Stream f0(j$.util.U u, boolean z) {
        Objects.requireNonNull(u);
        return new C0133h2(u, EnumC0129g3.m(u), z);
    }

    static j$.util.U C(EnumC0134h3 enumC0134h3, j$.util.U u, long j, long j2) {
        long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
        long j4 = j3 >= 0 ? j3 : Long.MAX_VALUE;
        int i = A2.a[enumC0134h3.ordinal()];
        if (i == 1) {
            return new A3(u, j, j4);
        }
        if (i == 2) {
            return new C0213x3((j$.util.K) u, j, j4);
        }
        if (i == 3) {
            return new C0218y3((j$.util.N) u, j, j4);
        }
        if (i != 4) {
            throw new IllegalStateException("Unknown shape " + enumC0134h3);
        }
        return new C0208w3((j$.util.H) u, j, j4);
    }

    public static C0215y0 c0(EnumC0210x0 enumC0210x0, Predicate predicate) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(enumC0210x0);
        return new C0215y0(EnumC0134h3.REFERENCE, enumC0210x0, new C0180r0(0, enumC0210x0, predicate));
    }

    static AbstractC0127g1 L(EnumC0134h3 enumC0134h3) {
        int i = N0.a[enumC0134h3.ordinal()];
        if (i == 1) {
            return a;
        }
        if (i == 2) {
            return (AbstractC0127g1) b;
        }
        if (i == 3) {
            return (AbstractC0127g1) c;
        }
        if (i == 4) {
            return (AbstractC0127g1) d;
        }
        throw new IllegalStateException("Unknown shape " + enumC0134h3);
    }

    public static C0215y0 Z(EnumC0210x0 enumC0210x0) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(enumC0210x0);
        return new C0215y0(EnumC0134h3.INT_VALUE, enumC0210x0, new C0176q0(enumC0210x0, 1));
    }

    public static Stream d0(AbstractC0143j2 abstractC0143j2, long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new C0192t2(abstractC0143j2, M(j2), j, j2);
    }

    public static M0 w(M0 m0, long j, long j2, IntFunction intFunction) {
        if (j == 0 && j2 == m0.count()) {
            return m0;
        }
        j$.util.U uSpliterator = m0.spliterator();
        long j3 = j2 - j;
        E0 e0D = D(j3, intFunction);
        e0D.m(j3);
        for (int i = 0; i < j && uSpliterator.tryAdvance(new r(28)); i++) {
        }
        if (j2 == m0.count()) {
            uSpliterator.forEachRemaining(e0D);
        } else {
            for (int i2 = 0; i2 < j3 && uSpliterator.tryAdvance(e0D); i2++) {
            }
        }
        e0D.l();
        return e0D.a();
    }

    static O0 I(EnumC0134h3 enumC0134h3, M0 m0, M0 m02) {
        int i = N0.a[enumC0134h3.ordinal()];
        if (i == 1) {
            return new Y0(m0, m02);
        }
        if (i == 2) {
            return new V0((I0) m0, (I0) m02);
        }
        if (i == 3) {
            return new W0((K0) m0, (K0) m02);
        }
        if (i != 4) {
            throw new IllegalStateException("Unknown shape " + enumC0134h3);
        }
        return new U0((G0) m0, (G0) m02);
    }

    public static InterfaceC0116e0 T(j$.util.K k) {
        return new Z(k, EnumC0129g3.m(k), false);
    }

    public static C0215y0 b0(EnumC0210x0 enumC0210x0) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(enumC0210x0);
        return new C0215y0(EnumC0134h3.LONG_VALUE, enumC0210x0, new C0176q0(enumC0210x0, 0));
    }

    public static void k() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static C0215y0 X(EnumC0210x0 enumC0210x0) {
        Objects.requireNonNull(null);
        Objects.requireNonNull(enumC0210x0);
        return new C0215y0(EnumC0134h3.DOUBLE_VALUE, enumC0210x0, new C0176q0(enumC0210x0, 2));
    }

    public static void l() {
        throw new IllegalStateException("called wrong accept method");
    }

    static E0 D(long j, IntFunction intFunction) {
        if (j >= 0 && j < 2147483639) {
            return new C0132h1(j, intFunction);
        }
        return new C0221z1();
    }

    public static void a() {
        throw new IllegalStateException("called wrong accept method");
    }

    public static void g(InterfaceC0173p2 interfaceC0173p2, Integer num) {
        if (P3.a) {
            P3.a(interfaceC0173p2.getClass(), "{0} calling Sink.OfInt.accept(Integer)");
            throw null;
        }
        interfaceC0173p2.accept(num.intValue());
    }

    public static InterfaceC0171p0 V(j$.util.N n) {
        return new C0146k0(n, EnumC0129g3.m(n), false);
    }

    public static void i(InterfaceC0178q2 interfaceC0178q2, Long l) {
        if (P3.a) {
            P3.a(interfaceC0178q2.getClass(), "{0} calling Sink.OfLong.accept(Long)");
            throw null;
        }
        interfaceC0178q2.accept(l.longValue());
    }

    static C0 S(long j) {
        if (j < 0 || j >= 2147483639) {
            return new C0147k1();
        }
        return new C0142j1(j);
    }

    public static void e(InterfaceC0168o2 interfaceC0168o2, Double d2) {
        if (P3.a) {
            P3.a(interfaceC0168o2.getClass(), "{0} calling Sink.OfDouble.accept(Double)");
            throw null;
        }
        interfaceC0168o2.accept(d2.doubleValue());
    }

    public static InterfaceC0116e0 Y(AbstractC0101b0 abstractC0101b0, long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new C0202v2(abstractC0101b0, M(j2), j, j2);
    }

    static D0 U(long j) {
        if (j < 0 || j >= 2147483639) {
            return new C0191t1();
        }
        return new C0186s1(j);
    }

    public static Object[] m(L0 l0, IntFunction intFunction) {
        if (P3.a) {
            P3.a(l0.getClass(), "{0} calling Node.OfPrimitive.asArray");
            throw null;
        }
        if (l0.count() >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) l0.count());
        l0.j(objArr, 0);
        return objArr;
    }

    public static E K(j$.util.H h) {
        return new C0219z(h, EnumC0129g3.m(h), false);
    }

    static B0 J(long j) {
        if (j < 0 || j >= 2147483639) {
            return new C0102b1();
        }
        return new C0097a1(j);
    }

    public static M0 E(AbstractC0100b abstractC0100b, j$.util.U u, boolean z, IntFunction intFunction) {
        long jG = abstractC0100b.G(u);
        if (jG < 0 || !u.hasCharacteristics(Http2.INITIAL_MAX_FRAME_SIZE)) {
            S0 s0 = new S0();
            s0.a = intFunction;
            M0 m0 = (M0) new R0(abstractC0100b, u, s0, new C0170p(14), 3).invoke();
            return z ? N(m0, intFunction) : m0;
        }
        if (jG >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jG);
        new C0211x1(u, abstractC0100b, objArr).invoke();
        return new P0(objArr);
    }

    public static void r(I0 i0, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            i0.f((IntConsumer) consumer);
        } else {
            if (P3.a) {
                P3.a(i0.getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.K) i0.spliterator()).forEachRemaining(consumer);
        }
    }

    public static InterfaceC0171p0 a0(AbstractC0156m0 abstractC0156m0, long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new C0212x2(abstractC0156m0, M(j2), j, j2);
    }

    public static void o(I0 i0, Integer[] numArr, int i) {
        if (P3.a) {
            P3.a(i0.getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");
            throw null;
        }
        int[] iArr = (int[]) i0.e();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            numArr[i + i2] = Integer.valueOf(iArr[i2]);
        }
    }

    public static I0 u(I0 i0, long j, long j2) {
        if (j == 0 && j2 == i0.count()) {
            return i0;
        }
        long j3 = j2 - j;
        j$.util.K k = (j$.util.K) i0.spliterator();
        C0 c0S = S(j3);
        c0S.m(j3);
        for (int i = 0; i < j && k.tryAdvance((IntConsumer) new H0(0)); i++) {
        }
        if (j2 == i0.count()) {
            k.forEachRemaining((IntConsumer) c0S);
        } else {
            for (int i2 = 0; i2 < j3 && k.tryAdvance((IntConsumer) c0S); i2++) {
            }
        }
        c0S.l();
        return c0S.a();
    }

    public static I0 G(AbstractC0100b abstractC0100b, j$.util.U u, boolean z) {
        long jG = abstractC0100b.G(u);
        if (jG < 0 || !u.hasCharacteristics(Http2.INITIAL_MAX_FRAME_SIZE)) {
            I0 i0 = (I0) new R0(abstractC0100b, u, new C0170p(10), new C0170p(11), 1).invoke();
            return z ? P(i0) : i0;
        }
        if (jG >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        int[] iArr = new int[(int) jG];
        new C0201v1(u, abstractC0100b, iArr).invoke();
        return new C0137i1(iArr);
    }

    public static K0 H(AbstractC0100b abstractC0100b, j$.util.U u, boolean z) {
        long jG = abstractC0100b.G(u);
        if (jG < 0 || !u.hasCharacteristics(Http2.INITIAL_MAX_FRAME_SIZE)) {
            K0 k0 = (K0) new R0(abstractC0100b, u, new C0170p(12), new C0170p(13), 2).invoke();
            return z ? Q(k0) : k0;
        }
        if (jG >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        long[] jArr = new long[(int) jG];
        new C0206w1(u, abstractC0100b, jArr).invoke();
        return new C0181r1(jArr);
    }

    public static void s(K0 k0, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            k0.f((LongConsumer) consumer);
        } else {
            if (P3.a) {
                P3.a(k0.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.N) k0.spliterator()).forEachRemaining(consumer);
        }
    }

    public static void p(K0 k0, Long[] lArr, int i) {
        if (P3.a) {
            P3.a(k0.getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");
            throw null;
        }
        long[] jArr = (long[]) k0.e();
        for (int i2 = 0; i2 < jArr.length; i2++) {
            lArr[i + i2] = Long.valueOf(jArr[i2]);
        }
    }

    public static K0 v(K0 k0, long j, long j2) {
        if (j == 0 && j2 == k0.count()) {
            return k0;
        }
        long j3 = j2 - j;
        j$.util.N n = (j$.util.N) k0.spliterator();
        D0 d0U = U(j3);
        d0U.m(j3);
        for (int i = 0; i < j && n.tryAdvance((LongConsumer) new J0(0)); i++) {
        }
        if (j2 == k0.count()) {
            n.forEachRemaining((LongConsumer) d0U);
        } else {
            for (int i2 = 0; i2 < j3 && n.tryAdvance((LongConsumer) d0U); i2++) {
            }
        }
        d0U.l();
        return d0U.a();
    }

    public static G0 F(AbstractC0100b abstractC0100b, j$.util.U u, boolean z) {
        long jG = abstractC0100b.G(u);
        if (jG < 0 || !u.hasCharacteristics(Http2.INITIAL_MAX_FRAME_SIZE)) {
            G0 g0 = (G0) new R0(abstractC0100b, u, new C0170p(8), new C0170p(9), 0).invoke();
            return z ? O(g0) : g0;
        }
        if (jG >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        double[] dArr = new double[(int) jG];
        new C0196u1(u, abstractC0100b, dArr).invoke();
        return new Z0(dArr);
    }

    public static E W(B b2, long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new C0222z2(b2, M(j2), j, j2);
    }

    public static M0 N(M0 m0, IntFunction intFunction) {
        if (m0.r() <= 0) {
            return m0;
        }
        long jCount = m0.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jCount);
        new B1(m0, objArr, 1).invoke();
        return new P0(objArr);
    }

    public static void q(G0 g0, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            g0.f((DoubleConsumer) consumer);
        } else {
            if (P3.a) {
                P3.a(g0.getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                throw null;
            }
            ((j$.util.H) g0.spliterator()).forEachRemaining(consumer);
        }
    }

    public static I0 P(I0 i0) {
        if (i0.r() <= 0) {
            return i0;
        }
        long jCount = i0.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        int[] iArr = new int[(int) jCount];
        new A1(i0, iArr, 0).invoke();
        return new C0137i1(iArr);
    }

    public static void n(G0 g0, Double[] dArr, int i) {
        if (P3.a) {
            P3.a(g0.getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");
            throw null;
        }
        double[] dArr2 = (double[]) g0.e();
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            dArr[i + i2] = Double.valueOf(dArr2[i2]);
        }
    }

    public static G0 t(G0 g0, long j, long j2) {
        if (j == 0 && j2 == g0.count()) {
            return g0;
        }
        long j3 = j2 - j;
        j$.util.H h = (j$.util.H) g0.spliterator();
        B0 b0J = J(j3);
        b0J.m(j3);
        for (int i = 0; i < j && h.tryAdvance((DoubleConsumer) new F0(0)); i++) {
        }
        if (j2 == g0.count()) {
            h.forEachRemaining((DoubleConsumer) b0J);
        } else {
            for (int i2 = 0; i2 < j3 && h.tryAdvance((DoubleConsumer) b0J); i2++) {
            }
        }
        b0J.l();
        return b0J.a();
    }

    public static K0 Q(K0 k0) {
        if (k0.r() <= 0) {
            return k0;
        }
        long jCount = k0.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        long[] jArr = new long[(int) jCount];
        new A1(k0, jArr, 0).invoke();
        return new C0181r1(jArr);
    }

    private static int M(long j) {
        return (j != -1 ? EnumC0129g3.u : 0) | EnumC0129g3.t;
    }

    public static G0 O(G0 g0) {
        if (g0.r() <= 0) {
            return g0;
        }
        long jCount = g0.count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        double[] dArr = new double[(int) jCount];
        new A1(g0, dArr, 0).invoke();
        return new Z0(dArr);
    }

    @Override // j$.util.stream.M3
    public Object b(AbstractC0100b abstractC0100b, j$.util.U u) {
        W1 w1E0 = e0();
        abstractC0100b.V(u, w1E0);
        return w1E0.get();
    }

    @Override // j$.util.stream.M3
    public Object c(AbstractC0100b abstractC0100b, j$.util.U u) {
        return ((W1) new C0113d2(this, abstractC0100b, u).invoke()).get();
    }
}
