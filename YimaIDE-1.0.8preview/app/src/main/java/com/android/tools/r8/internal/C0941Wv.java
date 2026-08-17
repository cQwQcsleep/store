package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.shaking.C3403i;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0941Wv extends AbstractC1256ci {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.B5 b;
    public final Set c = AbstractC2780ub0.c();

    public C0941Wv(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        this.a = c0333y;
        this.b = b5;
    }

    public final void a(com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0D;
        if (i2 != this.b.s() && (e0D = this.a.d(i2)) != null && e0D.a0() && e0D.a(this.a)) {
            ArrayList arrayList = null;
            for (com.android.tools.r8.graph.I2 i3 : this.c) {
                if (this.a.a(i3, i2).d()) {
                    return;
                }
                if (this.a.a(i2, i3).d()) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(i3);
                }
            }
            this.c.add(i2);
            if (arrayList != null) {
                this.c.removeAll(arrayList);
            }
        }
    }

    public final void a(Set set) {
        set.forEach(new Consumer() { // from class: kvf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((I2) obj);
            }
        });
    }

    public final void a(AbstractC1949kp abstractC1949kp) {
        C0210g1 c0210g1Q = ((C3403i) this.a.g()).c(abstractC1949kp.getField()).q();
        if (c0210g1Q != null) {
            if (c0210g1Q.E0().M0()) {
                a(c0210g1Q.E0());
            } else {
                if (d) {
                    return;
                }
                x01.a("Expected holder of field type to be a class type");
            }
        }
    }

    public final void a(SB sb) {
        if (sb.W1()) {
            AbstractC1047aC abstractC1047aCB0 = sb.b0();
            C0322w2 c0322w2U2 = abstractC1047aCB0.U2();
            if (c0322w2U2.f.M0()) {
                com.android.tools.r8.graph.H0 h0G = abstractC1047aCB0.g(this.a, this.b);
                if (h0G != null) {
                    a(h0G.s());
                    C0231j1 c0231j1E = h0G.e();
                    c0231j1E.O0();
                    a(c0231j1E.m.p());
                    return;
                }
                a(c0322w2U2.w0());
            }
        }
    }
}
