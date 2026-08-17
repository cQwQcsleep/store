package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.k9, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1893k9 {
    public static final /* synthetic */ boolean a = true;

    public static void a(int i, InterfaceC1101ar interfaceC1101ar, C0813Rx c0813Rx) {
        boolean z = a;
        if (!z && interfaceC1101ar.f()) {
            x1f.a();
            return;
        }
        InterfaceC1101ar interfaceC1101ar2 = (InterfaceC1101ar) c0813Rx.a(i, interfaceC1101ar);
        if (interfaceC1101ar2 != null && interfaceC1101ar2.i()) {
            int i2 = InterfaceC1101ar.a;
            InterfaceC1101ar interfaceC1101ar3 = (InterfaceC1101ar) c0813Rx.a(i - 1, C1490fV.c);
            if (!z && interfaceC1101ar3 != interfaceC1101ar2.k().L()) {
                x1f.a();
                return;
            }
        }
        if (interfaceC1101ar.B()) {
            if (!z && !interfaceC1101ar.r()) {
                x1f.a();
                return;
            }
            interfaceC1101ar2 = (InterfaceC1101ar) c0813Rx.a(i + 1, interfaceC1101ar.k().K());
        }
        if (interfaceC1101ar2 == null || !interfaceC1101ar2.r()) {
            return;
        }
        int iO = interfaceC1101ar.o() + i;
        int i3 = InterfaceC1101ar.a;
        InterfaceC1101ar interfaceC1101ar4 = (InterfaceC1101ar) c0813Rx.a(iO, C1490fV.c);
        if (z || interfaceC1101ar4 == interfaceC1101ar2.k().K()) {
            return;
        }
        x1f.a();
    }

    public static void a(InterfaceC0425Cy interfaceC0425Cy) {
        InterfaceC2942wU it = interfaceC0425Cy.b().iterator();
        while (it.hasNext()) {
            InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
            int iA = interfaceC0943Wx.a();
            InterfaceC1101ar interfaceC1101ar = (InterfaceC1101ar) interfaceC0943Wx.getValue();
            if (interfaceC1101ar.r()) {
                if (!a && interfaceC0425Cy.get(iA + 1) != interfaceC1101ar.k().K()) {
                    x1f.a();
                    return;
                }
            } else if (interfaceC1101ar.i()) {
                if (!a && interfaceC0425Cy.get(iA - 1) != interfaceC1101ar.k().L()) {
                    x1f.a();
                    return;
                }
            } else if (!a && interfaceC1101ar.f()) {
                x1f.a();
                return;
            }
        }
    }
}
