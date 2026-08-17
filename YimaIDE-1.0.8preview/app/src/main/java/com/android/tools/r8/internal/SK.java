package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.InterfaceC0022c;
import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.C0195e0;
import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.SK;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SK<EV> extends AbstractC0223i0 implements com.android.tools.r8.utils.structural.x<SK<EV>>, Iterable<ZK> {
    public static final /* synthetic */ boolean o = true;
    public final AbstractC2420qL e;
    public final boolean f;
    public final UK[] g;
    public final OK[] h;
    public final int i;
    public final byte[] j;
    public final int k;
    public final RK l;
    public final MK m;
    public InterfaceC2045lz n;

    public SK(UK[] ukArr, OK[] okArr, int i, byte[] bArr, int i2, RK rk, MK mk, AbstractC2420qL abstractC2420qL, boolean z, InterfaceC2045lz interfaceC2045lz) {
        if (!o && okArr == null) {
            x1f.a();
            throw null;
        }
        this.g = ukArr;
        this.h = okArr;
        this.i = i;
        this.j = bArr;
        this.k = i2;
        this.l = rk;
        this.m = mk;
        this.e = abstractC2420qL;
        this.f = z;
        this.n = interfaceC2045lz;
    }

    public static /* synthetic */ boolean h(SK sk) {
        return sk.n == null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean A0() {
        C1056aL it = iterator();
        while (it.hasNext()) {
            int iB = it.next().b();
            if (iB != 177 && iB != 209) {
                return false;
            }
        }
        return true;
    }

    public final UK[] H0() {
        return this.g;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: I0, reason: merged with bridge method [inline-methods] */
    public C1056aL iterator() {
        return new C1056aL(new E7(this.j));
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.Z5 z5) {
        if (!o && !z5.c.d()) {
            x1f.a();
            return;
        }
        for (OK ok : this.h) {
            if (ok.b()) {
                z5.a(ok.a(b5.getReference(), b5.e().I0()));
            }
        }
        C2505rL c2505rL = new C2505rL(this, z5);
        C1056aL it = iterator();
        while (it.hasNext()) {
            ZK next = it.next();
            InterfaceC2045lz interfaceC2045lz = this.n;
            if (interfaceC2045lz != null) {
                c2505rL.d = (C1041a8) interfaceC2045lz.get(next.j());
            }
            c2505rL.a(next);
            if (z5.c.c()) {
                return;
            }
        }
        RK rk = this.l;
        if (rk != null) {
            C2900vz c2900vz = new C2900vz(((C2302oz) rk.b.values()).b);
            while (c2900vz.hasNext()) {
                Iterator it2 = ((C2490r8) c2900vz.h.d[c2900vz.a()]).b.iterator();
                while (it2.hasNext()) {
                    z5.a((com.android.tools.r8.graph.I2) it2.next());
                    if (z5.c.c()) {
                        return;
                    }
                }
            }
        }
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        throw new Kk0("LIR code should not be subject to equality checks.");
    }

    public final C0230j0 d(Object obj) {
        MK mk = this.m;
        if (mk == null) {
            return null;
        }
        return (C0230j0) mk.b.get(obj);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int k(int i) {
        if (!this.f) {
            int i2 = this.k;
            if (i2 <= i) {
                return i2;
            }
            return -1;
        }
        gL gLVar = new gL(this);
        C1056aL it = iterator();
        while (it.hasNext()) {
            gLVar.a(it.next());
            if (gLVar.c > i) {
                return -1;
            }
        }
        return gLVar.c;
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        throw new Kk0("LIR code should not be subject to hashing.");
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: lnc
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                SK.a(a);
            }
        };
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final SK r0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void s0() {
        this.n = null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final int t0() {
        gL gLVar = new gL(this);
        C1056aL it = iterator();
        while (it.hasNext()) {
            gLVar.a(it.next());
            if (gLVar.c > Integer.MAX_VALUE) {
                return -1;
            }
        }
        return gLVar.c;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String toString() {
        return new C1480fL(this).k();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final boolean v0() {
        C1056aL it = iterator();
        while (it.hasNext()) {
            int iB = it.next().b();
            if (iB == 194 || iB == 195) {
                return true;
            }
        }
        return false;
    }

    public SK(SK sk) {
        this(sk.g, sk.h, sk.i, sk.j, sk.k, sk.l, sk.m, sk.e, sk.f, sk.n);
    }

    public static <V, EV> LK<V, EV> a(C0322w2 c0322w2, boolean z, XK<V, EV> xk, C2752uB c2752uB) {
        return new LK<>(c0322w2, z, xk, c2752uB);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.b(new Function() { // from class: mnc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SK) obj).g;
            }
        }).f(new Function() { // from class: nnc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SK) obj).h;
            }
        }).a(new ToIntFunction() { // from class: onc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((SK) obj).i;
            }
        }).a(new Function() { // from class: pnc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SK) obj).j;
            }
        }).a(new ToIntFunction() { // from class: qnc
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((SK) obj).k;
            }
        }).j(new Function() { // from class: rnc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SK) obj).l;
            }
        }).j(new Function() { // from class: snc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SK) obj).m;
            }
        }).a(new Predicate() { // from class: tnc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SK.h((SK) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C1041a8 a(InterfaceC0022c interfaceC0022c) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(com.android.tools.r8.graph.B5 b5, C0333y c0333y, AbstractC2166nO.a aVar) {
        return DK.a(b5, this, new RW(this, new AS()), c0333y, null, c0333y.A().e(b5.e().U0().a(c0333y), b5.getReference()), aVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final C0705Nt a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6, C0333y c0333y, AbstractC3148ys abstractC3148ys, AS as, AbstractC2004lX abstractC2004lX, com.android.tools.r8.graph.proto.j jVar) {
        boolean z = o;
        if (!z && as == null) {
            x1f.a();
            return null;
        }
        if (!z && abstractC2004lX == null) {
            x1f.a();
            return null;
        }
        if (!z && jVar == null) {
            x1f.a();
            return null;
        }
        return DK.a(b6, this, new RW(this, as), c0333y, abstractC2004lX, jVar, AbstractC2166nO.e());
    }

    public static /* synthetic */ void a(AbstractC2004lX abstractC2004lX) {
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0195e0 c0195e0, C3047xh c3047xh) {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final InterfaceC2045lz a(C0333y c0333y, C0231j1 c0231j1) {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final String a(C0231j1 c0231j1, C1581ga0 c1581ga0) {
        return new C1480fL(this).k();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001f  */
    public final OK[] a(AbstractC2004lX abstractC2004lX, C0322w2 c0322w2, boolean z, Consumer consumer) {
        AbstractC2004lX abstractC2004lXA;
        OK[] okArr;
        if (z && this.h.length == 0) {
            consumer.accept(abstractC2004lX);
            return OK.c;
        }
        OK[] okArr2 = this.h;
        int i = 0;
        if (okArr2.length > 0) {
            OK ok = okArr2[0];
            if (ok.b == 0) {
                abstractC2004lXA = ok.a(c0322w2, z);
            } else {
                AbstractC2004lX.c.a aVarA = AbstractC2004lX.c.s().a(0).a(c0322w2);
                aVarA.e = z;
                abstractC2004lXA = ((AbstractC2004lX.c.a) aVarA.c()).a();
            }
        } else {
            AbstractC2004lX.c.a aVarA2 = AbstractC2004lX.c.s().a(0).a(c0322w2);
            aVarA2.e = z;
            abstractC2004lXA = ((AbstractC2004lX.c.a) aVarA2.c()).a();
        }
        C2234o8 c2234o8 = new C2234o8(abstractC2004lX, this.h.length, c0322w2, z, abstractC2004lXA);
        OK[] okArr3 = this.h;
        if (okArr3.length == 0) {
            okArr = new OK[]{new PK(0, c2234o8.c)};
        } else {
            okArr = new OK[okArr3.length];
            while (true) {
                OK[] okArr4 = this.h;
                if (i >= okArr4.length) {
                    break;
                }
                OK ok2 = okArr4[i];
                okArr[i] = new PK(ok2.b, c2234o8.b(ok2.a(c0322w2, z)));
                i++;
            }
        }
        consumer.accept(c2234o8.c);
        return okArr;
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final AbstractC0223i0 a(C0322w2 c0322w2, boolean z, C0322w2 c0322w3, boolean z2, com.android.tools.r8.graph.B1 b1) {
        AbstractC2004lX.c.a aVarA = AbstractC2004lX.c.s().a(0).a(c0322w2);
        aVarA.e = true;
        OK[] okArrA = a(aVarA.a(), c0322w3, z2, new Consumer() { // from class: unc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SK.a((AbstractC2004lX) obj);
            }
        });
        return Arrays.equals(this.h, okArrA) ? this : new SK(this.g, okArrA, this.i, this.j, this.k, this.l, this.m, this.e, this.f, this.n);
    }

    @Override // com.android.tools.r8.graph.AbstractC0223i0
    public final void a(C0322w2 c0322w2, boolean z, Consumer consumer) {
        for (OK ok : this.h) {
            consumer.accept(ok.a(c0322w2, z));
        }
    }
}
