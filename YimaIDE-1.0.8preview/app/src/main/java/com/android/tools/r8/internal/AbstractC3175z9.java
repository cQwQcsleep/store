package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.InterfaceC0022c;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.ListIterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z9, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3175z9 implements InterfaceC0022c {
    public static final /* synthetic */ boolean b = true;

    public K9 A() {
        return null;
    }

    public boolean B() {
        return false;
    }

    public boolean C() {
        return false;
    }

    public boolean D() {
        return this instanceof C2920w9;
    }

    public boolean E() {
        return false;
    }

    public boolean F() {
        return this instanceof C3006x9;
    }

    public boolean G() {
        return false;
    }

    public boolean H() {
        return false;
    }

    public boolean I() {
        return false;
    }

    public boolean J() {
        return false;
    }

    public boolean K() {
        return false;
    }

    public boolean L() {
        return false;
    }

    public boolean M() {
        return false;
    }

    public boolean N() {
        return this instanceof C2322p9;
    }

    public boolean O() {
        return this instanceof K9;
    }

    public boolean P() {
        return this instanceof C1752ia;
    }

    public boolean Q() {
        return this instanceof C2948wa;
    }

    public boolean R() {
        return this instanceof C0946Xa;
    }

    public boolean S() {
        return false;
    }

    public abstract int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o);

    public AbstractC1597gi0 a(BiFunction biFunction, AbstractC3175z9 abstractC3175z9, AbstractC1808j9 abstractC1808j9) {
        boolean z = b;
        if (!z && M()) {
            x1f.a();
            return null;
        }
        if (abstractC3175z9 != null) {
            return (AbstractC1597gi0) biFunction.apply(abstractC3175z9, abstractC1808j9);
        }
        if (z || (this instanceof K9)) {
            return new C1512fi0(abstractC1808j9);
        }
        x1f.a();
        return null;
    }

    public abstract AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8);

    public abstract void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo);

    public abstract void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca);

    public abstract void a(C2520ra c2520ra);

    public abstract void a(com.android.tools.r8.utils.structural.o oVar);

    public K8 b() {
        return null;
    }

    public V8 c() {
        return null;
    }

    public W8 d() {
        return null;
    }

    public C1213c9 e() {
        return null;
    }

    public AbstractC1638h9 f() {
        return null;
    }

    @Override // com.android.tools.r8.internal.H
    public final boolean g() {
        return x();
    }

    public C1724i9 h() {
        return null;
    }

    public C3006x9 i() {
        return null;
    }

    public C3090y9 j() {
        return null;
    }

    public G9 k() {
        return null;
    }

    public H9 l() {
        return null;
    }

    public J9 m() {
        return null;
    }

    @Override // com.android.tools.r8.dex.code.InterfaceC0022c
    public final AbstractC3175z9 n() {
        return this;
    }

    public K9 o() {
        return null;
    }

    @Override // com.android.tools.r8.dex.code.InterfaceC0022c
    public final AbstractC0138z1 p() {
        return null;
    }

    public P9 q() {
        return null;
    }

    public C1752ia r() {
        return null;
    }

    public C2605sa s() {
        return null;
    }

    public C0790Ra t() {
        return null;
    }

    public String toString() {
        C2520ra c2520ra = new C2520ra();
        a(c2520ra);
        return c2520ra.toString();
    }

    public C0842Ta u() {
        return null;
    }

    public InterfaceC0998Za v() {
        return null;
    }

    public int w() {
        throw new Kk0("Instruction must specify size");
    }

    public boolean x() {
        return this instanceof C3088y8;
    }

    public boolean y() {
        return !(this instanceof C1724i9);
    }

    public abstract int z();

    public B8 a() {
        return null;
    }

    public void a(com.android.tools.r8.graph.Z5 z5, ListIterator listIterator) {
    }

    public final void a(final Consumer consumer, AbstractC3175z9 abstractC3175z9) {
        a(new BiFunction() { // from class: qzi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC3175z9.a(consumer, (AbstractC3175z9) obj, obj2);
            }
        }, abstractC3175z9, (AbstractC1808j9) null);
    }

    public static /* synthetic */ AbstractC1597gi0 a(Consumer consumer, AbstractC3175z9 abstractC3175z9, Object obj) {
        consumer.accept(abstractC3175z9);
        return C1512fi0.c;
    }
}
