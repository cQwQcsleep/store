package com.android.tools.r8.graph;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.hkh;
import java.nio.ShortBuffer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.a3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0170a3 {
    static {
        boolean z = X2.a;
    }

    int A();

    Z0 C();

    J0 N();

    int S();

    int a(B5 b5);

    /* JADX WARN: Multi-variable type inference failed */
    default int a(InterfaceC0170a3 interfaceC0170a3, AbstractC3519a abstractC3519a) {
        int iN = n();
        int iN2 = interfaceC0170a3.n();
        if (iN != iN2) {
            return AbstractC0007c.a(iN, iN2);
        }
        int iB = AbstractC0007c.b(iN);
        if (iB == 0) {
            return N().a(interfaceC0170a3.N(), abstractC3519a);
        }
        if (iB != 1 && iB != 2) {
            if (iB == 3) {
                if (X2.a || (((AbstractC0223i0) this) instanceof U5)) {
                    return u().a(interfaceC0170a3.u(), abstractC3519a);
                }
                x1f.a();
                return 0;
            }
            hkh.a();
        }
        return 0;
    }

    Z2 a(B5 b5, B1 b1);

    InterfaceC0170a3 a(B5 b5, C0284q5 c0284q5, C0333y c0333y, boolean z);

    void a(com.android.tools.r8.dex.X x);

    void a(com.android.tools.r8.dex.r rVar);

    void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer);

    void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj);

    void a(com.android.tools.r8.utils.structural.o oVar);

    int b(B5 b5);

    void c(B5 b5);

    int n();

    J0.a[] r();

    AbstractC0223i0 s();

    U5 u();

    J0.b[] w();
}
