package com.android.tools.r8.graph;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0033e0;
import com.android.tools.r8.dex.code.C0040f2;
import com.android.tools.r8.dex.code.C0060j2;
import com.android.tools.r8.dex.code.C0081n3;
import com.android.tools.r8.dex.code.C0133y1;
import com.android.tools.r8.dex.code.InterfaceC0022c;
import com.android.tools.r8.graph.J0;
import com.android.tools.r8.internal.AS;
import com.android.tools.r8.internal.AbstractC0594Jl;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2166nO;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C0931Wl;
import com.android.tools.r8.internal.C1032a30;
import com.android.tools.r8.internal.C1041a8;
import com.android.tools.r8.internal.C1211c8;
import com.android.tools.r8.internal.C1370e30;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C3047xh;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.InterfaceC1713i30;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.W7;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.utils.structural.A;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class J0 extends AbstractC0223i0 implements InterfaceC0170a3, com.android.tools.r8.utils.structural.x<J0>, Z2 {
    public static final /* synthetic */ boolean o = true;
    public final int e;
    public final int f;
    public final int g;
    public final a[] h;
    public final b[] i;
    public final AbstractC0138z1[] j;
    public H2 k;
    public W0 l;
    public Z0 m;
    public final C1211c8 n;

    public J0(int i, int i2, int i3, AbstractC0138z1[] abstractC0138z1Arr, a[] aVarArr, b[] bVarArr, W0 w0, C1211c8 c1211c8) {
        this.f = i2;
        this.e = i;
        this.g = i3;
        this.j = abstractC0138z1Arr;
        this.h = aVarArr;
        this.i = bVarArr;
        this.l = w0;
        this.n = c1211c8;
        boolean z = o;
        if (!z && aVarArr == null) {
            x1f.a();
            throw null;
        }
        if (!z && bVarArr == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC0138z1Arr == null) {
            x1f.a();
            throw null;
        }
        if (z || w0 == null || w0.u0() || AbstractC0594Jl.a(w0.o0().g)) {
            hashCode();
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final int A() {
        return this.g;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public boolean A0() {
        AbstractC0138z1[] abstractC0138z1Arr = this.j;
        return abstractC0138z1Arr.length == 1 && (abstractC0138z1Arr[0] instanceof C0081n3);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final Z0 C() {
        if (this.m == null) {
            this.m = W0.a(this.l);
        }
        return this.m;
    }

    public W0 H0() {
        return this.l;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0, com.android.tools.r8.graph.InterfaceC0170a3
    public final J0 N() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final int S() {
        AbstractC0138z1[] abstractC0138z1Arr = this.j;
        AbstractC0138z1 abstractC0138z1 = abstractC0138z1Arr[abstractC0138z1Arr.length - 1];
        boolean z = o;
        if (!z && abstractC0138z1.b < 0) {
            x1f.a();
            return 0;
        }
        int iT = abstractC0138z1.t() + abstractC0138z1.q();
        if (!z) {
            int iT2 = 0;
            for (AbstractC0138z1 abstractC0138z2 : this.j) {
                iT2 += abstractC0138z2.t();
            }
            if (iT != iT2) {
                x1f.a();
                return 0;
            }
        }
        return iT;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00a8 A[PHI: r6
      0x00a8: PHI (r6v2 java.util.Iterator<com.android.tools.r8.graph.K0>) = 
      (r6v1 java.util.Iterator<com.android.tools.r8.graph.K0>)
      (r6v1 java.util.Iterator<com.android.tools.r8.graph.K0>)
      (r6v1 java.util.Iterator<com.android.tools.r8.graph.K0>)
      (r6v32 java.util.Iterator<com.android.tools.r8.graph.K0>)
     binds: [B:17:0x007f, B:19:0x0085, B:20:0x0087, B:22:0x009f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String a(C0231j1 c0231j1, C1581ga0 c1581ga0) {
        K0 next;
        StringBuilder sb = new StringBuilder();
        if (c0231j1 != null) {
            sb.append(c1581ga0.c(c0231j1.getReference()));
            sb.append("\n");
        }
        sb.append("registers: ");
        sb.append(this.e);
        sb.append(", inputs: ");
        sb.append(this.f);
        sb.append(", outputs: ");
        sb.append(this.g);
        sb.append("\n------------------------------------------------------------\ninst#  offset  instruction         arguments\n------------------------------------------------------------\n");
        HashMap map = new HashMap();
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            if (abstractC0138z1.x()) {
                map.put(Integer.valueOf(abstractC0138z1.s() + abstractC0138z1.q()), abstractC0138z1);
            }
        }
        Iterator<K0> itEmptyIterator = Collections.emptyIterator();
        boolean z = H0() != null && H0().u0();
        if (z || H0() == null || c0231j1 == null) {
            next = null;
        } else {
            itEmptyIterator = new M0(c0231j1, new B1()).a().iterator();
            if (itEmptyIterator.hasNext()) {
                next = itEmptyIterator.next();
            } else {
                next = null;
            }
        }
        Map<Integer, C0230j0> map2 = Collections.EMPTY_MAP;
        AbstractC0138z1[] abstractC0138z1Arr = this.j;
        int length = abstractC0138z1Arr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            AbstractC0138z1 abstractC0138z2 = abstractC0138z1Arr[i];
            int iQ = abstractC0138z2.q() - 1;
            while (true) {
                itEmptyIterator = itEmptyIterator;
                if (next == null || next.b > iQ) {
                    break;
                }
                next = itEmptyIterator.hasNext() ? itEmptyIterator.next() : null;
            }
            while (next != null && next.b == abstractC0138z2.q()) {
                if (next.a || !map2.equals(next.f)) {
                    sb.append("         ");
                    sb.append(next.a(false));
                    sb.append("\n");
                }
                map2 = next.f;
                next = itEmptyIterator.hasNext() ? itEmptyIterator.next() : null;
            }
            int i3 = i2 + 1;
            Wf0.a(5, Integer.toString(i2), sb);
            sb.append(": ");
            if (abstractC0138z2.H()) {
                sb.append(abstractC0138z2.a(c1581ga0, (AbstractC0138z1) map.get(Integer.valueOf(abstractC0138z2.q()))));
            } else {
                sb.append(abstractC0138z2.b(c1581ga0));
            }
            sb.append('\n');
            i++;
            i2 = i3;
            itEmptyIterator = itEmptyIterator;
        }
        Iterator<K0> it = itEmptyIterator;
        if (z) {
            sb.append(H0());
            sb.append("\n");
        } else if (it.hasNext()) {
            int iQ2 = ((AbstractC0138z1) com.android.tools.r8.internal.R3.b(this.j)).q();
            while (next != null && next.b <= iQ2) {
                next = it.hasNext() ? it.next() : null;
            }
            if (next != null) {
                sb.append("(warning: has unhandled debug events @ pc:");
                sb.append(next.b);
                sb.append(", line:");
                sb.append(next.b().f());
            } else {
                sb.append("(has debug events past last pc)\n");
            }
        }
        if (this.h.length > 0) {
            sb.append("Tries (numbers are offsets)\n");
            for (a aVar : this.h) {
                sb.append("  ");
                sb.append(aVar.toString());
                sb.append('\n');
            }
            sb.append("Handlers (numbers are offsets)\n");
            int i4 = 0;
            while (true) {
                b[] bVarArr = this.i;
                if (i4 >= bVarArr.length) {
                    break;
                }
                b bVar = bVarArr[i4];
                sb.append("  ");
                sb.append(i4);
                sb.append(": ");
                sb.append(bVar.toString());
                sb.append('\n');
                i4++;
            }
        }
        return sb.toString();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final void c(B5 b5) {
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            D0 d0J = abstractC0138z1.j();
            if (d0J != null) {
                C0322w2 reference = b5.getReference();
                int iQ = abstractC0138z1.q();
                boolean z = D0.l;
                if (!z && reference == null) {
                    x1f.a();
                    return;
                }
                if (!z && iQ < 0) {
                    x1f.a();
                    return;
                }
                if (!z && d0J.j != null) {
                    x1f.a();
                    return;
                } else if (!z && d0J.k != -1) {
                    x1f.a();
                    return;
                } else {
                    d0J.j = reference;
                    d0J.k = iQ;
                }
            }
        }
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final int n() {
        return 1;
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        int iHashCode = (Arrays.hashCode(this.j) * 7) + (this.g * 5) + (this.e * 3) + (this.f * 2);
        W0 w0 = this.l;
        return (Arrays.hashCode(this.i) * 17) + (Arrays.hashCode(this.h) * 13) + ((w0 == null ? 0 : w0.hashCode()) * 11) + iHashCode;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: o77
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a2) {
                J0.a(a2);
            }
        };
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final InterfaceC0170a3 p0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final a[] r() {
        return this.h;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final AbstractC0223i0 s() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int t0() {
        return S();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String toString() {
        return a((C0231j1) null, C1581ga0.b);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean v0() {
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            if (abstractC0138z1 instanceof C0060j2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final b[] w() {
        return this.i;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean y0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean z0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final int b(B5 b5) {
        return this.f;
    }

    public static class b extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<b> {
        public static final b[] d = new b[0];
        public static final /* synthetic */ boolean e = true;
        public final a[] b;
        public final int c;

        public b(a[] aVarArr, int i) {
            this.b = aVarArr;
            this.c = i;
        }

        public static void a(com.android.tools.r8.utils.structural.A a2) {
            a2.a(new ToIntFunction() { // from class: e87
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((J0.b) obj).c;
                }
            }).f(new Function() { // from class: f87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((J0.b) obj).b;
                }
            });
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        public final boolean equals(Object obj) {
            return com.android.tools.r8.utils.structural.k.a(this, obj);
        }

        public final int hashCode() {
            boolean z = com.android.tools.r8.utils.structural.l.c;
            return com.android.tools.r8.utils.structural.l.a(this, o());
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.y o() {
            return new com.android.tools.r8.utils.structural.y() { // from class: d87
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a2) {
                    J0.b.a(a2);
                }
            };
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("[\n");
            for (a aVar : this.b) {
                sb.append("       ");
                sb.append(aVar.b);
                sb.append(" -> ");
                sb.append(Wf0.a(aVar.c, 2));
                sb.append("\n");
            }
            if (this.c != -1) {
                sb.append("       default -> ");
                sb.append(Wf0.a(this.c, 2));
                sb.append("\n");
            }
            sb.append("     ]");
            return sb.toString();
        }

        public static class a extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<a> {
            public static final /* synthetic */ boolean d = true;
            public final I2 b;
            public final int c;

            public a(int i, I2 i2) {
                this.b = i2;
                this.c = i;
            }

            public static void a(com.android.tools.r8.utils.structural.A a) {
                a.e(new Function() { // from class: g87
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((J0.b.a) obj).b;
                    }
                }).a(new ToIntFunction() { // from class: h87
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return ((J0.b.a) obj).c;
                    }
                });
            }

            @Override // com.android.tools.r8.utils.structural.x
            public final com.android.tools.r8.utils.structural.x R() {
                return this;
            }

            public final boolean equals(Object obj) {
                return com.android.tools.r8.utils.structural.k.a(this, obj);
            }

            public I2 getType() {
                return this.b;
            }

            public final int hashCode() {
                return (this.b.hashCode() * 7) + this.c;
            }

            @Override // com.android.tools.r8.utils.structural.x
            public final com.android.tools.r8.utils.structural.y o() {
                return new com.android.tools.r8.utils.structural.y() { // from class: i87
                    @Override // com.android.tools.r8.utils.structural.y
                    public final void a(A a) {
                        J0.b.a.a(a);
                    }
                };
            }

            @Override // com.android.tools.r8.graph.AbstractC0259n1
            public final void a(com.android.tools.r8.dex.X x) {
                if (d) {
                    return;
                }
                x1f.a();
            }
        }

        @Override // com.android.tools.r8.graph.AbstractC0259n1
        public final void a(com.android.tools.r8.dex.X x) {
            if (e) {
                return;
            }
            x1f.a();
        }
    }

    public static class a extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<a> {
        public static final a[] f = new a[0];
        public static final /* synthetic */ boolean g = true;
        public final int b;
        public int c;
        public int d;
        public int e = -1;

        public a(int i, int i2, int i3) {
            this.c = i;
            this.d = i2;
            this.b = i3;
            if (g || W7.b(i2)) {
                return;
            }
            x1f.a();
            throw null;
        }

        public static void a(com.android.tools.r8.utils.structural.A a) {
            a.a(new ToIntFunction() { // from class: a87
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((J0.a) obj).c;
                }
            }).a(new ToIntFunction() { // from class: b87
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((J0.a) obj).d;
                }
            }).a(new ToIntFunction() { // from class: c87
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((J0.a) obj).e;
                }
            });
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        public final boolean equals(Object obj) {
            return com.android.tools.r8.utils.structural.k.a(this, obj);
        }

        public final int hashCode() {
            return (this.e * 5) + (this.d * 3) + (this.c * 2);
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.y o() {
            return new com.android.tools.r8.utils.structural.y() { // from class: z77
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a) {
                    J0.a.a(a);
                }
            };
        }

        public final String toString() {
            return "[" + Wf0.a(this.c, 2) + " .. " + Wf0.a(this.c + this.d, 2) + "[ -> " + this.e;
        }

        @Override // com.android.tools.r8.graph.AbstractC0259n1
        public final void a(com.android.tools.r8.dex.X x) {
            if (g) {
                return;
            }
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public J0(int i, int i2, int i3, AbstractC0138z1[] abstractC0138z1Arr, a[] aVarArr, b[] bVarArr, W0 w0) {
        this(i, i2, i3, abstractC0138z1Arr, aVarArr, bVarArr, w0, C1211c8.b);
    }

    public static void a(com.android.tools.r8.utils.structural.A a2) {
        a2.a(new ToIntFunction() { // from class: t77
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((J0) obj).e;
            }
        }).a(new ToIntFunction() { // from class: u77
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((J0) obj).f;
            }
        }).a(new ToIntFunction() { // from class: v77
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((J0) obj).g;
            }
        }).f(new Function() { // from class: w77
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((J0) obj).h;
            }
        }).f(new Function() { // from class: x77
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((J0) obj).i;
            }
        }).j(new Function() { // from class: y77
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((J0) obj).l;
            }
        }).f(new Function() { // from class: p77
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((J0) obj).j;
            }
        });
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C1041a8 a(InterfaceC0022c interfaceC0022c) {
        return (C1041a8) this.n.a.get(interfaceC0022c.p());
    }

    public static boolean a(C0333y c0333y, B5 b5) {
        H2 h2C1;
        C2752uB c2752uBM = c0333y.M();
        c2752uBM.getClass();
        boolean z = C2752uB.Y1;
        if (!z && b5.e().U0().N() == null) {
            x1f.a();
            return false;
        }
        if (!z && b5.e().U0().N().H0() != null) {
            x1f.a();
            return false;
        }
        if (b5.a().u == ProgramResource.Kind.DEX && c2752uBM.j()) {
            if (c2752uBM.F().a(EnumC3077y2.B) && ((h2C1 = b5.a().c1()) == null || h2C1.equals(c2752uBM.a.b5))) {
                return true;
            }
        }
        return false;
    }

    public void a(W0 w0) {
        this.l = w0;
        if (this.m != null) {
            this.m = null;
        }
        this.b = -1;
    }

    public final W0.a a(B1 b1) {
        W0.a aVarA = W0.a(this, b1);
        if (aVarA == null) {
            return aVarA;
        }
        int iMax = 0;
        for (H2 h2 : aVarA.f) {
            iMax = Integer.max(iMax, a(b1, h2));
        }
        for (O0 o0 : aVarA.g) {
            if (o0 instanceof O0.d) {
                iMax = Integer.max(iMax, a(b1, ((O0.d) o0).e));
            }
        }
        String str = "_".repeat(iMax + 1) + "this";
        H2[] h2Arr = aVarA.f;
        H2[] h2Arr2 = new H2[h2Arr.length + 1];
        h2Arr2[0] = b1.c(str);
        System.arraycopy(h2Arr, 0, h2Arr2, 1, h2Arr.length);
        return new W0.a(aVarA.e, h2Arr2, aVarA.g);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final InterfaceC0170a3 a(final B5 b5, C0284q5 c0284q5, final C0333y c0333y, boolean z) {
        H2 h2 = null;
        if (z) {
            C1032a30 c1032a30 = new C1032a30(((C1370e30) c0284q5.j.i()).b);
            while (c1032a30.hasNext()) {
                InterfaceC1713i30 interfaceC1713i30 = (InterfaceC1713i30) c1032a30.next();
                if (interfaceC1713i30.getIntValue() == 0) {
                    h2 = (H2) interfaceC1713i30.getKey();
                    break;
                }
            }
        } else {
            boolean z2 = o;
            if (!z2 && this.k == null && !Arrays.stream(this.j).noneMatch(new Predicate() { // from class: q77
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((AbstractC0138z1) obj).z();
                }
            })) {
                x1f.a();
                return null;
            }
            if (!z2 && !Arrays.stream(this.j).noneMatch(new Predicate() { // from class: r77
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((AbstractC0138z1) obj).B();
                }
            })) {
                x1f.a();
                return null;
            }
            H2 h3 = this.k;
            if (h3 != null && h3.a(c0284q5.n)) {
                h2 = c0284q5.n;
            }
        }
        return h2 != null ? new com.android.tools.r8.dex.U(b5.e(), h2, new BooleanSupplier() { // from class: s77
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return J0.a(c0333y, b5);
            }
        }, c0333y.a()).a() : this;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r4v0 ??, still in use, count: 1, list:
          (r4v0 ?? I:??[OBJECT, ARRAY]) from 0x019b: RETURN (r4v0 ?? I:??[OBJECT, ARRAY])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final com.android.tools.r8.graph.AbstractC0223i0 a(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r4v0 ??, still in use, count: 1, list:
          (r4v0 ?? I:??[OBJECT, ARRAY]) from 0x019b: RETURN (r4v0 ?? I:??[OBJECT, ARRAY])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:75)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r21v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:215)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:150)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:415)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:295)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:284)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:268)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:160)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:104)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:89)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:127)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
        */

    public static int a(B1 b1, H2 h2) {
        if (h2 != null && h2.a(b1.e1.f)) {
            String string = h2.toString();
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) != '_') {
                    return i;
                }
            }
        }
        return 0;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3, com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.y yVarO = o();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        yVarO.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(B5 b5, C0333y c0333y, AbstractC2166nO.a aVar) {
        return C0602Jt.a(b5, c0333y, new C0931Wl(this, b5, null, c0333y.a())).a(b5, aVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(B5 b5, B5 b6, C0333y c0333y, AbstractC3148ys abstractC3148ys, AS as, AbstractC2004lX abstractC2004lX, com.android.tools.r8.graph.proto.j jVar) {
        return new C0602Jt(b6, c0333y, abstractC3148ys, new C0931Wl(this, b6, abstractC2004lX, c0333y.a()), jVar, as).a(b5, AbstractC2166nO.e());
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(B5 b5, Z5 z5) {
        a(z5);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0195e0 c0195e0, C3047xh c3047xh) {
        a(c3047xh);
    }

    public final void a(Z5 z5) {
        if (!o && !z5.c.d()) {
            x1f.a();
            return;
        }
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            abstractC0138z1.a(z5);
            if (z5.c.c()) {
                return;
            }
        }
        for (b bVar : this.i) {
            for (b.a aVar : bVar.b) {
                z5.a(aVar.b);
                if (z5.c.c()) {
                    return;
                }
            }
        }
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final Z2 a(B5 b5, B1 b1) {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
        this.k = null;
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            boolean z = o;
            if (!z) {
                abstractC0138z1.getClass();
                if (abstractC0138z1 instanceof C0040f2) {
                    x1f.a();
                    return;
                }
            }
            abstractC0138z1.a(c0333y, abstractC3148ys, m, b5, rj);
            if (abstractC0138z1.z()) {
                H2 h2I = abstractC0138z1.d().I();
                if (!z && h2I == null) {
                    x1f.a();
                    return;
                }
                H2 h2 = this.k;
                if (h2 == null || h2.a(h2I) < 0) {
                    this.k = h2I;
                }
            } else if (abstractC0138z1 instanceof C0033e0) {
                H2 h2I2 = abstractC0138z1.e().I();
                if (!z && h2I2 == null) {
                    x1f.a();
                    return;
                }
                H2 h3 = this.k;
                if (h3 == null || h3.a(h2I2) < 0) {
                    this.k = h2I2;
                }
            } else {
                continue;
            }
        }
        if (this.l != null) {
            C().a(c0333y, abstractC3148ys, m);
        }
        for (b bVar : this.i) {
            for (b.a aVar : bVar.b) {
                aVar.getClass();
                c0333y.A().c(abstractC3148ys, aVar.b).a(c0333y, m);
            }
        }
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final int a(B5 b5) {
        return this.e;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0, com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        if (this.l != null) {
            C().a(x);
        }
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final void a(com.android.tools.r8.dex.r rVar) {
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            C0322w2 c0322w2L = abstractC0138z1.l();
            C0245l1 field = abstractC0138z1.getField();
            if (field != null) {
                if (!o && c0322w2L != null) {
                    x1f.a();
                    return;
                }
                rVar.a(field);
            } else if (c0322w2L != null) {
                rVar.a(c0322w2L);
            } else if (abstractC0138z1 instanceof com.android.tools.r8.dex.code.Z) {
                rVar.a(abstractC0138z1.c().I());
            } else if (abstractC0138z1 instanceof C0133y1) {
                rVar.a(abstractC0138z1.h().I());
            } else if (abstractC0138z1.y()) {
                rVar.a(abstractC0138z1.b().I());
            }
        }
    }

    @Override // com.android.tools.r8.graph.InterfaceC0170a3
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        for (AbstractC0138z1 abstractC0138z1 : this.j) {
            abstractC0138z1.a(c0284q5, b5, abstractC3148ys, abstractC3148ys2, rj, shortBuffer);
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0322w2 c0322w2, boolean z, Consumer consumer) {
        if (H0() == null || H0().u0()) {
            return;
        }
        for (O0 o0 : H0().o0().g) {
            o0.getClass();
            if (o0 instanceof S0) {
                consumer.accept(o0.p0().d);
            }
        }
    }
}
