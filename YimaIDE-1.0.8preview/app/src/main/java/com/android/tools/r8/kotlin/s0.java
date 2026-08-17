package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC0441Do;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C1734iI;
import com.android.tools.r8.internal.C1985lE;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C3015xH;
import com.android.tools.r8.internal.C3100yI;
import com.android.tools.r8.internal.DI;
import com.android.tools.r8.internal.FI;
import com.android.tools.r8.internal.KB;
import com.android.tools.r8.internal.KI;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.kotlin.C3296n;
import com.android.tools.r8.kotlin.r0;
import com.android.tools.r8.kotlin.s0;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import defpackage.b67;
import defpackage.j2i;
import defpackage.w33;
import defpackage.z1i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class s0 implements InterfaceC3369b0 {
    public static final P40 g;
    public static final P40 h;
    public final int a;
    public final int b;
    public final String c;
    public final KI d;
    public final List e;
    public final List f;

    static {
        int i = AbstractC0551Hu.c;
        P40 p40 = P40.e;
        g = p40;
        h = p40;
    }

    public s0(int i, int i2, String str, KI ki, AbstractC0551Hu abstractC0551Hu, AbstractC0551Hu abstractC0551Hu2) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = ki;
        this.e = abstractC0551Hu;
        this.f = abstractC0551Hu2;
    }

    public static AbstractC0551Hu a(List list, B1 b1, C2742u50 c2742u50) {
        AbstractC0551Hu abstractC0551HuA;
        AbstractC0551Hu abstractC0551HuA2;
        if (list.isEmpty()) {
            return g;
        }
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DI di = (DI) it.next();
            int iA = di.a();
            int iB = di.b();
            String strC = di.c();
            KI kiE = di.e();
            List<C3100yI> listD = di.d();
            if (listD.isEmpty()) {
                abstractC0551HuA = h;
            } else {
                C0473Eu c0473EuG2 = AbstractC0551Hu.g();
                Iterator<C3100yI> it2 = listD.iterator();
                while (it2.hasNext()) {
                    c0473EuG2.a(r0.a(b1, c2742u50, it2.next()));
                }
                abstractC0551HuA = c0473EuG2.a();
            }
            C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o.b;
            KB.c(c1734iI, "type");
            ArrayList<C3015xH> arrayList = ((C1985lE) ((FI) AbstractC0441Do.a(di.g, c1734iI))).c;
            P40 p40 = C3296n.c;
            if (arrayList.isEmpty()) {
                abstractC0551HuA2 = C3296n.c;
            } else {
                C0473Eu c0473EuG3 = AbstractC0551Hu.g();
                for (C3015xH c3015xH : arrayList) {
                    c0473EuG3.a(new C3296n(u0.a(c3015xH.b(), b1, c3015xH.b()), AbstractC3295m.a(c3015xH.a(), b1)));
                }
                abstractC0551HuA2 = c0473EuG3.a();
            }
            c0473EuG.a(new s0(iA, iB, strC, kiE, abstractC0551HuA, abstractC0551HuA2));
        }
        return c0473EuG.a();
    }

    public final boolean b(Consumer consumer, C0333y c0333y) {
        DI di = (DI) d0.a(consumer, new DI(this.a, this.c, this.b, this.d));
        boolean zA = d0.a(c0333y, this.e, di.d(), new w33());
        List list = this.f;
        C1734iI c1734iI = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.o.b;
        KB.c(c1734iI, "type");
        return d0.a(c0333y, list, ((C1985lE) ((FI) AbstractC0441Do.a(di.g, c1734iI))).c, new j2i()) | zA;
    }

    public static /* synthetic */ Consumer a(r0 r0Var) {
        Objects.requireNonNull(r0Var);
        return new b67(r0Var);
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        C0470Er.a((Iterable) this.e, new Function() { // from class: k9i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return s0.a((r0) obj);
            }
        }, (Object) interfaceC0189d1);
        C0470Er.a((Iterable) this.f, new Function() { // from class: n9i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return s0.a((C3296n) obj);
            }
        }, (Object) interfaceC0189d1);
    }

    public static /* synthetic */ Consumer a(C3296n c3296n) {
        Objects.requireNonNull(c3296n);
        return new z1i(c3296n);
    }
}
