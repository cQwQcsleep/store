package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1917kW;
import com.android.tools.r8.internal.C3054xk0;
import com.android.tools.r8.internal.UR;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UR extends AbstractC2003lW {
    public static final /* synthetic */ boolean c = true;
    public final InterfaceC0969Xx b;

    public UR(C1873jy c1873jy) {
        if (c || !c1873jy.isEmpty()) {
            this.b = c1873jy;
        } else {
            x01.a("Should use bottom() instead");
            throw null;
        }
    }

    public final UR a(InterfaceC2665tA interfaceC2665tA) {
        InterfaceC0943Wx interfaceC0943Wx;
        int iA;
        BU it = this.b.b().iterator();
        C1873jy c1873jy = null;
        while (it.hasNext()) {
            InterfaceC0943Wx interfaceC0943Wx2 = (InterfaceC0943Wx) it.next();
            int iA2 = interfaceC0943Wx2.a();
            AbstractC1917kW abstractC1917kW = (AbstractC1917kW) interfaceC0943Wx2.getValue();
            AbstractC1917kW abstractC1917kW2 = (AbstractC1917kW) interfaceC2665tA.a(iA2, (AbstractC1917kW) interfaceC0943Wx2.getValue());
            if (abstractC1917kW2 != abstractC1917kW) {
                if (c1873jy == null) {
                    c1873jy = new C1873jy(16);
                    BU it2 = this.b.b().iterator();
                    while (it2.hasNext() && (iA = (interfaceC0943Wx = (InterfaceC0943Wx) it2.next()).a()) != iA2) {
                        c1873jy.a(iA, (AbstractC1917kW) interfaceC0943Wx.getValue());
                    }
                }
                c1873jy.a(iA2, abstractC1917kW2);
            } else if (c1873jy != null) {
                c1873jy.a(iA2, abstractC1917kW2);
            }
        }
        return c1873jy != null ? new UR(c1873jy) : this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final UR d() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final AbstractC2003lW e() {
        UR urA = a(new InterfaceC2665tA() { // from class: iye
            @Override // com.android.tools.r8.internal.InterfaceC2665tA
            public final Object a(int i, Object obj) {
                return ((AbstractC1917kW) obj).b();
            }
        });
        BU it = urA.b.values().iterator();
        boolean z = true;
        boolean z2 = true;
        while (it.hasNext()) {
            AbstractC1917kW abstractC1917kW = (AbstractC1917kW) it.next();
            abstractC1917kW.getClass();
            if (!(abstractC1917kW instanceof C1377e7)) {
                z = false;
            }
            if (!(abstractC1917kW instanceof C3138yk0)) {
                z2 = false;
            }
        }
        if (z) {
            return C1463f7.b;
        }
        return z2 ? C3224zk0.b : urA;
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final boolean equals(Object obj) {
        if (obj == null || UR.class != obj.getClass()) {
            return false;
        }
        return this.b.equals(((UR) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final AbstractC2003lW a(int i, AbstractC1917kW abstractC1917kW) {
        C1873jy c1873jy = new C1873jy((C1873jy) this.b);
        c1873jy.a(i, abstractC1917kW);
        return c1873jy.isEmpty() ? C1463f7.b : new UR(c1873jy);
    }

    @Override // com.android.tools.r8.internal.AbstractC2003lW
    public final AbstractC1917kW a(int i) {
        return (AbstractC1917kW) this.b.getOrDefault(Integer.valueOf(i), C3138yk0.a);
    }

    public final UR a(C2543rl0 c2543rl0) {
        return a(c2543rl0, new BiFunction() { // from class: jye
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C3054xk0.a;
            }
        });
    }

    public final UR a(ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            return this;
        }
        final int[] iArr = new int[arrayList.size()];
        Iterator it = arrayList.iterator();
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((C2543rl0) it.next()).p().r().b(true);
        }
        return a(new InterfaceC2665tA() { // from class: hye
            @Override // com.android.tools.r8.internal.InterfaceC2665tA
            public final Object a(int i2, Object obj) {
                return UR.a(iArr, i2, (AbstractC1917kW) obj);
            }
        });
    }

    public static AbstractC1917kW a(int[] iArr, int i, AbstractC1917kW abstractC1917kW) {
        boolean z = R3.a;
        for (int i2 : iArr) {
            if (i2 == i) {
                return abstractC1917kW.a(new BiFunction() { // from class: mye
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return C3054xk0.a;
                    }
                });
            }
        }
        return abstractC1917kW;
    }

    public final UR a(Iterable iterable, Predicate predicate) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            C2543rl0 c2543rl0 = (C2543rl0) it.next();
            A4 a4 = A4.a;
            c2543rl0.getClass();
            C2543rl0 c2543rl0A = c2543rl0.a(a4, MX.c);
            if (predicate.test(c2543rl0A)) {
                arrayList.add(c2543rl0A);
            }
        }
        return a(arrayList);
    }

    public final UR a(C2543rl0 c2543rl0, final BiFunction biFunction) {
        A4 a4 = A4.a;
        c2543rl0.getClass();
        C2543rl0 c2543rl0A = c2543rl0.a(a4, MX.c);
        if (c || c2543rl0A.F()) {
            final int iB = c2543rl0A.p().r().b(true);
            return a(new InterfaceC2665tA() { // from class: lye
                @Override // com.android.tools.r8.internal.InterfaceC2665tA
                public final Object a(int i, Object obj) {
                    return UR.a(iB, biFunction, i, (AbstractC1917kW) obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ AbstractC1917kW a(int i, BiFunction biFunction, int i2, AbstractC1917kW abstractC1917kW) {
        return i2 == i ? abstractC1917kW.a(biFunction) : abstractC1917kW;
    }

    public final UR a(UR ur) {
        ur.getClass();
        final C1873jy c1873jy = new C1873jy((C1873jy) this.b);
        InterfaceC2494rA interfaceC2494rA = new InterfaceC2494rA() { // from class: kye
            @Override // com.android.tools.r8.internal.InterfaceC2494rA
            public final void a(int i, Object obj) {
                UR.a(c1873jy, i, (AbstractC1917kW) obj);
            }
        };
        BU it = ur.b.b().iterator();
        while (it.hasNext()) {
            InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
            interfaceC2494rA.a(interfaceC0943Wx.a(), interfaceC0943Wx.getValue());
        }
        return new UR(c1873jy);
    }

    public static void a(InterfaceC0969Xx interfaceC0969Xx, int i, AbstractC1917kW abstractC1917kW) {
        AbstractC1917kW abstractC1917kWA;
        Object obj = C1377e7.a;
        Object obj2 = interfaceC0969Xx.get(i);
        if (obj2 != null) {
            obj = obj2;
        }
        AbstractC1917kW abstractC1917kW2 = (AbstractC1917kW) obj;
        abstractC1917kW.getClass();
        if (!(abstractC1917kW instanceof C3138yk0) && !(abstractC1917kW2 instanceof C3138yk0)) {
            abstractC1917kWA = abstractC1917kW.a().a(abstractC1917kW2.a());
        } else {
            abstractC1917kWA = C3138yk0.a;
        }
        interfaceC0969Xx.a(i, abstractC1917kWA);
    }
}
