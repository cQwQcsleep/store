package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.G9;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.bx5;
import defpackage.exe;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class G9 extends AbstractC3175z9 {
    public static final /* synthetic */ boolean f = true;
    public final C0322w2 c;
    public final int d;
    public final boolean e;

    public G9(int i, C0322w2 c0322w2, boolean z) {
        boolean z2 = f;
        if (!z2 && (182 > i || i > 185)) {
            x1f.a();
            throw null;
        }
        if (!z2 && i == 182 && z) {
            x01.a("InvokeVirtual on interface type");
            throw null;
        }
        if (!z2 && i == 185 && !z) {
            x01.a("InvokeInterface on class type");
            throw null;
        }
        this.d = i;
        this.c = c0322w2;
        this.e = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean H() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean I() {
        return this.d == 185;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean J() {
        return this.d == 183;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean K() {
        return this.d == 184;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean L() {
        return this.d == 182;
    }

    public C0322w2 T() {
        return this.c;
    }

    public int U() {
        return this.d;
    }

    public boolean V() {
        return this.e;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0066  */
    /* JADX WARN: Code duplicated, block: B:22:0x0070 A[LOOP:0: B:21:0x006e->B:22:0x0070, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:25:0x009d  */
    /* JADX WARN: Code duplicated, block: B:28:0x00b0  */
    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca) {
        C0322w2 c0322w2A;
        EnumC2326pC enumC2326pC;
        com.android.tools.r8.graph.E2 e2;
        EnumC2326pC enumC2326pCA;
        C0322w2 c0322w2;
        int size;
        El0[] el0Arr;
        Integer[] numArr;
        int i;
        int i2 = this.d;
        com.android.tools.r8.graph.E2 e3 = null;
        switch (i2) {
            case 182:
                c0322w2A = c0602Jt.d().K4.a(this.c);
                if (c0322w2A == null || c0602Jt.o.M().p0()) {
                    enumC2326pC = EnumC2326pC.h;
                    c0322w2A = this.c;
                } else {
                    enumC2326pC = EnumC2326pC.k;
                    e3 = this.c.i;
                }
                c0322w2 = c0322w2A;
                e2 = e3;
                enumC2326pCA = enumC2326pC;
                size = this.c.B0().size();
                if (enumC2326pCA != EnumC2326pC.f) {
                    size++;
                }
                el0Arr = new El0[size];
                numArr = new Integer[size];
                for (i = size - 1; i >= 0; i--) {
                    C0583Ja c0583JaA = c0738Pa.a();
                    el0Arr[i] = c0583JaA.b;
                    numArr[i] = Integer.valueOf(c0583JaA.a);
                }
                c0602Jt.a(enumC2326pCA, c0322w2, e2, Arrays.asList(el0Arr), Arrays.asList(numArr), this.e);
                if (!this.c.D0().W0()) {
                    c0602Jt.c(c0738Pa.a(this.c.D0()).a);
                }
                if (f && enumC2326pCA != EnumC2326pC.a(this.d, this.c, c0602Jt.f(), c0602Jt.o, c0602Jt.e())) {
                    x1f.a();
                    break;
                }
                break;
            case 183:
                C0333y c0333y = c0602Jt.o;
                com.android.tools.r8.graph.B5 b5F = c0602Jt.f();
                C0322w2 c0322w3 = this.c;
                e2 = null;
                enumC2326pCA = EnumC2326pC.a(c0322w3, b5F, c0333y, c0602Jt.e());
                c0322w2 = c0322w3;
                size = this.c.B0().size();
                if (enumC2326pCA != EnumC2326pC.f) {
                    size++;
                }
                el0Arr = new El0[size];
                numArr = new Integer[size];
                while (i >= 0) {
                    C0583Ja c0583JaA2 = c0738Pa.a();
                    el0Arr[i] = c0583JaA2.b;
                    numArr[i] = Integer.valueOf(c0583JaA2.a);
                }
                c0602Jt.a(enumC2326pCA, c0322w2, e2, Arrays.asList(el0Arr), Arrays.asList(numArr), this.e);
                if (!this.c.D0().W0()) {
                    c0602Jt.c(c0738Pa.a(this.c.D0()).a);
                }
                if (f) {
                }
                break;
            case 184:
                c0322w2A = this.c;
                enumC2326pC = EnumC2326pC.f;
                c0322w2 = c0322w2A;
                e2 = e3;
                enumC2326pCA = enumC2326pC;
                size = this.c.B0().size();
                if (enumC2326pCA != EnumC2326pC.f) {
                    size++;
                }
                el0Arr = new El0[size];
                numArr = new Integer[size];
                while (i >= 0) {
                    C0583Ja c0583JaA3 = c0738Pa.a();
                    el0Arr[i] = c0583JaA3.b;
                    numArr[i] = Integer.valueOf(c0583JaA3.a);
                }
                c0602Jt.a(enumC2326pCA, c0322w2, e2, Arrays.asList(el0Arr), Arrays.asList(numArr), this.e);
                if (!this.c.D0().W0()) {
                    c0602Jt.c(c0738Pa.a(this.c.D0()).a);
                }
                if (f) {
                }
                break;
            case 185:
                c0322w2A = this.c;
                enumC2326pC = EnumC2326pC.e;
                c0322w2 = c0322w2A;
                e2 = e3;
                enumC2326pCA = enumC2326pC;
                size = this.c.B0().size();
                if (enumC2326pCA != EnumC2326pC.f) {
                    size++;
                }
                el0Arr = new El0[size];
                numArr = new Integer[size];
                while (i >= 0) {
                    C0583Ja c0583JaA4 = c0738Pa.a();
                    el0Arr[i] = c0583JaA4.b;
                    numArr[i] = Integer.valueOf(c0583JaA4.a);
                }
                c0602Jt.a(enumC2326pCA, c0322w2, e2, Arrays.asList(el0Arr), Arrays.asList(numArr), this.e);
                if (!this.c.D0().W0()) {
                    c0602Jt.c(c0738Pa.a(this.c.D0()).a);
                }
                if (f) {
                }
                break;
            default:
                exe.a("unknown CfInvoke opcode ", i2);
                break;
        }
    }

    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        if (this.d != 183) {
            return false;
        }
        C0322w2 c0322w2 = this.c;
        return (c0322w2.f == i2 || c0322w2.g.toString().equals("<init>")) ? false : true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public G9 k() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return this.d == 185 ? 5 : 3;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean x() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int z() {
        return U();
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o) {
        return abstractC3519a.a(this, abstractC3175z9.k(), new bx5());
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        bx5 bx5Var = new bx5();
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.getClass();
        bx5Var.a(new com.android.tools.r8.utils.structural.p(this, qVar));
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        EnumC2326pC enumC2326pCA = EnumC2326pC.a(this.d, this.c, b5, c0333y, c0333y.g);
        if (enumC2326pCA == EnumC2326pC.k) {
            if (f || b1.K4.b(this.c)) {
                yo.a(enumC2326pCA.a(), C0929Wj.a(this.c.f.Z0()), this.c.x0().toString(), rj.a(this.c.C0()).a(abstractC3345r0), this.e);
                return;
            } else {
                x1f.a();
                return;
            }
        }
        C2850vO c2850vOA = abstractC3148ys.a(this.c, b5.getReference(), enumC2326pCA, abstractC3148ys2);
        EnumC2326pC enumC2326pC = c2850vOA.c;
        C0322w2 c0322w2 = (C0322w2) c2850vOA.a;
        yo.a(enumC2326pC.a(), abstractC3345r0.d(c0322w2.f), abstractC3345r0.a(c0322w2).toString(), c0322w2.i.a(abstractC3345r0), enumC2326pC.c() || this.e);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C2520ra c2520ra) {
        c2520ra.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.graph.Z5 z5, ListIterator listIterator) {
        int i = this.d;
        switch (i) {
            case 182:
                z5.h(this.c);
                break;
            case 183:
                z5.c(this.c);
                break;
            case 184:
                z5.f(this.c);
                break;
            case 185:
                z5.b(this.c);
                break;
            default:
                exe.a("Unknown CfInvoke opcode ", i);
                break;
        }
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.b(new Predicate() { // from class: cx5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((G9) obj).V();
            }
        }).e(new Function() { // from class: dx5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((G9) obj).T();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8) {
        AbstractC1808j9 abstractC1808j9A = abstractC1808j9.a(c0333y, interfaceC2576s8, this.c.B0().b);
        if (this.d != 184) {
            if (this.c.w0().I0()) {
                abstractC1808j9A = abstractC1808j9A.g();
            } else {
                com.android.tools.r8.graph.B1 b1A = c0333y.a();
                if (this.d == 183 && this.c.b(b1A)) {
                    abstractC1808j9A = abstractC1808j9A.a(this.c, interfaceC2576s8);
                } else {
                    abstractC1808j9A = abstractC1808j9A.a(c0333y, interfaceC2576s8, this.c.w0());
                }
            }
        }
        return this.c.D0().W0() ? abstractC1808j9A : abstractC1808j9A.a(interfaceC2576s8, this.c.D0());
    }
}
