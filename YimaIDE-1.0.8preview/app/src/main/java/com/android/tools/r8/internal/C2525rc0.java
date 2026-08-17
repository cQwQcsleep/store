package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2525rc0 extends AbstractC1926kc0 implements InterfaceC2617sg {
    public static final /* synthetic */ boolean c = true;
    public final long b;

    public C2525rc0(long j) {
        this.b = j;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean D() {
        return this.b == 0;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean H() {
        return D() || P();
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean P() {
        return this.b == 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final boolean Q() {
        return true;
    }

    public final boolean R() {
        if (!c) {
            long j = this.b;
            if (j != 0 && j != 1) {
                x1f.a();
                return false;
            }
        }
        return this.b != 0;
    }

    public final long S() {
        return this.b;
    }

    public final C1678hg a(C0333y c0333y, InterfaceC2714tl0 interfaceC2714tl0, InterfaceC2507rN interfaceC2507rN) {
        AbstractC2624sj0 abstractC2624sj0A = interfaceC2507rN.a();
        C0230j0 c0230j0K = interfaceC2507rN.k();
        AbstractC2004lX position = interfaceC2507rN.getPosition();
        if (!c && !abstractC2624sj0A.H()) {
            x1f.a();
            return null;
        }
        boolean z = C1678hg.k;
        C1592gg c1592gg = (C1592gg) ((C1592gg) new C1592gg().a(interfaceC2714tl0, abstractC2624sj0A, c0230j0K)).a(position, c0333y.M());
        c1592gg.d = this.b;
        return c1592gg.c();
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0, com.android.tools.r8.internal.B1
    public final B1 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final long c() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.B1, com.android.tools.r8.internal.InterfaceC2617sg
    public final C2525rc0 e() {
        return this;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.android.tools.r8.internal.B1
    public final InterfaceC2617sg k() {
        return this;
    }

    @Override // com.android.tools.r8.internal.B1
    public final String toString() {
        return "SingleNumberValue(" + this.b + ")";
    }

    @Override // com.android.tools.r8.internal.B1
    public final int v() {
        return (int) this.b;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int w() {
        return ~((int) this.b);
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean z() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    /* JADX INFO: renamed from: c */
    public final AbstractC3122yc0 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0, com.android.tools.r8.internal.InterfaceC2385pw
    /* JADX INFO: renamed from: a */
    public final InterfaceC2385pw b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final boolean a(C0333y c0333y) {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final AbstractC2173nV a(int[] iArr) {
        int i = (int) this.b;
        boolean z = R3.a;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == i) {
                z2 = true;
                break;
            }
        }
        return AbstractC2173nV.a(z2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final boolean a(int i) {
        return i == ((int) this.b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final boolean a(InterfaceC2617sg interfaceC2617sg) {
        B1 b1 = (B1) interfaceC2617sg;
        b1.getClass();
        if (b1 instanceof C1682hi) {
            return true;
        }
        if (b1 instanceof C2525rc0) {
            return equals(interfaceC2617sg.e());
        }
        if (!c && !(b1 instanceof C3110yS) && !(b1 instanceof AbstractC3195zS)) {
            x1f.a();
            return false;
        }
        return interfaceC2617sg.b().a((int) this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC3122yc0
    public final AbstractC0890Uw[] a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, InterfaceC2714tl0 interfaceC2714tl0, InterfaceC2507rN interfaceC2507rN) {
        return new AbstractC0890Uw[]{a(c0333y, interfaceC2714tl0, interfaceC2507rN)};
    }

    @Override // com.android.tools.r8.internal.InterfaceC2385pw
    public final InterfaceC2385pw a(com.android.tools.r8.graph.proto.c cVar) {
        return this;
    }
}
