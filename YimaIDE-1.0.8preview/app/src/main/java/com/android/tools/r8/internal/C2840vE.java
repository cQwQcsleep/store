package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2671tG;
import com.android.tools.r8.internal.C2159nH;
import com.android.tools.r8.internal.EE;
import com.android.tools.r8.internal.HE;
import com.android.tools.r8.internal.QE;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2840vE {
    public static final /* synthetic */ boolean i = true;
    public final C2415qF a;
    public final C2481r30 b = new C2481r30();
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final C0919Vz f = new C0919Vz(16);
    public final C0919Vz g = new C0919Vz(16);
    public final ArrayList h = new ArrayList();

    public C2840vE(C2415qF c2415qF) {
        this.a = c2415qF;
        c2415qF.c.a(new Consumer() { // from class: lli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((QE) obj);
            }
        });
        c2415qF.d.a(new Consumer() { // from class: mli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C2159nH) obj);
            }
        });
    }

    public final int a(AbstractC2671tG abstractC2671tG) {
        if (abstractC2671tG.d()) {
            int size = this.c.size();
            this.c.add(abstractC2671tG.a());
            this.e.add(new C0919Vz(16));
            if (i || size >= 0) {
                return size;
            }
            x1f.a();
            return 0;
        }
        int iA = a(abstractC2671tG.b().a);
        int size2 = this.d.size();
        this.d.add(abstractC2671tG.b());
        ((InterfaceC1981lA) this.e.get(iA)).add(size2);
        if (i || size2 >= 0) {
            return -(size2 + 1);
        }
        x1f.a();
        return 0;
    }

    public final int a(EE ee) {
        return ((Integer) this.b.computeIfAbsent(ee.a, new Function() { // from class: nli
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((HE) obj);
            }
        })).intValue();
    }

    public final Integer a(HE he) {
        return Integer.valueOf(a(((FE) this.a.b.a.get(he)).a));
    }

    public final int a(AbstractC2757uG abstractC2757uG) {
        return ((Integer) abstractC2757uG.a(new Function() { // from class: jli
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(this.b.a((EE) obj));
            }
        }, new Function() { // from class: kli
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(this.b.a((AbstractC2671tG) obj));
            }
        })).intValue();
    }

    public final void a(QE qe) {
        this.f.add(a(qe.a));
    }

    public final void a(C2159nH c2159nH) {
        this.g.add(a(c2159nH.a));
        this.h.add(c2159nH.b);
    }
}
