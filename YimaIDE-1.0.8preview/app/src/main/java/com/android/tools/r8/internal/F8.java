package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class F8 {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.B1 b;

    public F8(C0333y c0333y) {
        this.a = c0333y;
        this.b = c0333y.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean a(InterfaceC1101ar interfaceC1101ar, InterfaceC1101ar interfaceC1101ar2) {
        if (interfaceC1101ar.b() != interfaceC1101ar2.b()) {
            return false;
        }
        if (interfaceC1101ar.b()) {
            InterfaceC2355pc0 interfaceC2355pc0Q = interfaceC1101ar.q();
            InterfaceC1101ar interfaceC1101arQ = interfaceC1101ar2.q();
            if (interfaceC2355pc0Q.equals(interfaceC1101arQ)) {
                return true;
            }
            AbstractC3167z5 abstractC3167z5 = (AbstractC3167z5) interfaceC1101arQ;
            abstractC3167z5.getClass();
            if (abstractC3167z5 instanceof C1490fV) {
                return true;
            }
            AbstractC3167z5 abstractC3167z6 = (AbstractC3167z5) interfaceC2355pc0Q;
            if (!(abstractC3167z6 instanceof C1490fV)) {
                if ((abstractC3167z6 instanceof C1687hk0) && (abstractC3167z5 instanceof C1687hk0)) {
                    com.android.tools.r8.graph.I2 i2W = interfaceC2355pc0Q.w();
                    com.android.tools.r8.graph.I2 i2W2 = interfaceC1101arQ.w();
                    if (i2W == null || i2W2 == null || i2W == i2W2) {
                        return true;
                    }
                } else if (interfaceC1101arQ.isPrimitive()) {
                    if (interfaceC2355pc0Q.isPrimitive() && interfaceC2355pc0Q.A().K() && interfaceC1101arQ.A().K()) {
                        return true;
                    }
                } else if (!interfaceC2355pc0Q.isPrimitive() && interfaceC1101arQ.a()) {
                    if (interfaceC2355pc0Q.a()) {
                        return b(interfaceC2355pc0Q.s().a(this.b), interfaceC1101arQ.s().a(this.b));
                    }
                    if (interfaceC1101arQ.s().a(this.b) == this.b.a2) {
                        return true;
                    }
                }
            }
        } else {
            InterfaceC3228zm0 interfaceC3228zm0N = interfaceC1101ar.n();
            InterfaceC3228zm0 interfaceC3228zm0N2 = interfaceC1101ar2.n();
            if (!c) {
                AbstractC3167z5 abstractC3167z7 = (AbstractC3167z5) interfaceC3228zm0N;
                abstractC3167z7.getClass();
                if (abstractC3167z7 instanceof C2793ui0) {
                    x1f.a();
                    return false;
                }
            }
            if (interfaceC3228zm0N.a(interfaceC3228zm0N2) == interfaceC3228zm0N2) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        boolean z = c;
        if (!z && i3.R0()) {
            x1f.a();
            return false;
        }
        com.android.tools.r8.graph.B1 b1 = this.b;
        if (i2.J0() || i2.K0() || i2.L0() || i2.P0() || i2.V0()) {
            i2 = b1.B1;
        }
        com.android.tools.r8.graph.B1 b2 = this.b;
        if (i3.J0() || i3.K0() || i3.L0() || i3.P0() || i3.V0()) {
            i3 = b2.B1;
        }
        if (i2 == i3) {
            return true;
        }
        if (i2.T0() || i3.T0()) {
            return false;
        }
        if (!z && !i2.U0()) {
            x1f.a();
            return false;
        }
        if (!z && !i3.U0()) {
            x1f.a();
            return false;
        }
        if (i3 == this.b.a2 || i2.R0()) {
            return true;
        }
        if (i3.I0()) {
            return i2.I0() && b(i2.a(1, this.b), i3.a(1, this.b));
        }
        if (!z && !i3.M0()) {
            x1f.a();
            return false;
        }
        if (i2.I0()) {
            com.android.tools.r8.graph.B1 b3 = this.b;
            return i3 == b3.I5 || i3 == b3.G5;
        }
        if (z || i2.M0()) {
            return a(i2, i3);
        }
        x1f.a();
        return false;
    }

    public boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        return true;
    }

    public final boolean a(com.android.tools.r8.graph.I2 i2, El0 el0) {
        return b(i2, el0.a(this.b));
    }

    public final C8 a(InterfaceC0425Cy interfaceC0425Cy, InterfaceC0425Cy interfaceC0425Cy2) {
        InterfaceC1101ar interfaceC1101ar;
        InterfaceC1101ar interfaceC1101ar2;
        int iMax = Math.max(interfaceC0425Cy.isEmpty() ? -1 : interfaceC0425Cy.d(), interfaceC0425Cy2.isEmpty() ? -1 : interfaceC0425Cy2.d());
        for (int i = 0; i <= iMax; i++) {
            if (interfaceC0425Cy.a(i)) {
                interfaceC1101ar = (InterfaceC1101ar) interfaceC0425Cy.get(i);
            } else {
                int i2 = InterfaceC1101ar.a;
                interfaceC1101ar = C1490fV.c;
            }
            if (interfaceC0425Cy2.a(i)) {
                interfaceC1101ar2 = (InterfaceC1101ar) interfaceC0425Cy2.get(i);
            } else {
                int i3 = InterfaceC1101ar.a;
                interfaceC1101ar2 = C1490fV.c;
            }
            if (interfaceC1101ar.H() && interfaceC1101ar2.l()) {
                int i4 = InterfaceC1101ar.a;
                interfaceC1101ar2 = C2793ui0.c;
            }
            if (!a(interfaceC1101ar, interfaceC1101ar2)) {
                return a(interfaceC0425Cy, interfaceC0425Cy2, interfaceC1101ar, interfaceC1101ar2, i);
            }
        }
        return new E8();
    }

    public static D8 a(InterfaceC0425Cy interfaceC0425Cy, InterfaceC0425Cy interfaceC0425Cy2, InterfaceC1101ar interfaceC1101ar, InterfaceC1101ar interfaceC1101ar2, int i) {
        return new D8("Could not assign '" + IM.b(interfaceC0425Cy) + "' to '" + IM.b(interfaceC0425Cy2) + "'. The local at index " + i + " with '" + interfaceC1101ar + "' not being assignable to '" + interfaceC1101ar2 + "'");
    }

    public final C8 a(Deque deque, Deque deque2) {
        if (deque.size() != deque2.size()) {
            return new D8("Source stack " + Arrays.toString(deque.toArray()) + " and destination stack " + Arrays.toString(deque2.toArray()) + " is not the same size");
        }
        Iterator it = deque2.iterator();
        Iterator it2 = deque.iterator();
        int i = 0;
        while (it2.hasNext()) {
            BX bx = (BX) it2.next();
            BX bx2 = (BX) it.next();
            if (!a(bx, bx2)) {
                return new D8("Could not assign '" + Arrays.toString(deque.toArray()) + "' to '" + Arrays.toString(deque2.toArray()) + "'. The stack value at index " + i + " (from top) with '" + bx + "' not being assignable to '" + bx2 + "'");
            }
            i++;
        }
        return new E8();
    }
}
