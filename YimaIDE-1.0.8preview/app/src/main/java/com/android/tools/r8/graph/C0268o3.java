package com.android.tools.r8.graph;

import com.android.tools.r8.graph.E0;
import com.android.tools.r8.internal.C1755ib0;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.o3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0268o3 {
    public static final /* synthetic */ boolean b = true;
    public final InterfaceC0189d1 a;

    public C0268o3(InterfaceC0189d1 interfaceC0189d1) {
        this.a = interfaceC0189d1;
    }

    public final void a(final E0 e0, final C0245l1 c0245l1, final E0 e1, final Set set, final C0275p3 c0275p3) {
        if (!b && e0 == null) {
            x1f.a();
            return;
        }
        C0210g1 c0210g1A = e0.a(c0245l1);
        if (c0210g1A != null) {
            c0275p3.a(AbstractC0330x3.a(e1, e0, c0210g1A));
            return;
        }
        AbstractC0330x3 abstractC0330x3A = a(e1, e0, c0245l1, set);
        if (abstractC0330x3A != null) {
            c0275p3.a(abstractC0330x3A);
            return;
        }
        I2 i2 = e0.g;
        if (i2 != null) {
            this.a.g(i2).b(new Consumer() { // from class: jxh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(e0, c0245l1, e1, set, c0275p3, (E0) obj);
                }
            });
        } else {
            boolean z = AbstractC0330x3.a;
            c0275p3.a(C0282q3.b);
        }
    }

    public final /* synthetic */ void a(C0245l1 c0245l1, C0275p3 c0275p3, E0 e0) {
        a(e0, c0245l1, e0, C1755ib0.b(8), c0275p3);
    }

    public final AbstractC0330x3 a(I2 i2, final C0245l1 c0245l1) {
        boolean z = AbstractC0330x3.a;
        final C0275p3 c0275p3 = new C0275p3();
        this.a.g(i2).b(new Consumer() { // from class: hxh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0245l1, c0275p3, (E0) obj);
            }
        });
        C0282q3 c0282q3 = C0282q3.b;
        AbstractC0330x3 abstractC0330x3 = c0275p3.a;
        return abstractC0330x3 == null ? c0282q3 : abstractC0330x3;
    }

    public final /* synthetic */ void a(E0 e0, C0245l1 c0245l1, E0 e1, Set set, C0275p3 c0275p3, E0 e2) {
        if (!e0.b0() || e2.b0()) {
            a(e2, c0245l1, e1, set, c0275p3);
        }
    }

    public final AbstractC0330x3 a(final E0 e0, final E0 e1, final C0245l1 c0245l1, final Set set) {
        I2[] i2Arr = e1.h.b;
        int length = i2Arr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            I2 i2 = i2Arr[i];
            if (set.add(i2)) {
                boolean z = AbstractC0330x3.a;
                final C0275p3 c0275p3 = new C0275p3();
                this.a.g(i2).b(new Consumer() { // from class: ixh
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.a(e1, e0, c0245l1, set, c0275p3, (E0) obj);
                    }
                });
                AbstractC0330x3 abstractC0330x3 = c0275p3.a;
                AbstractC0330x3 abstractC0330x4 = abstractC0330x3 != null ? abstractC0330x3 : null;
                if (abstractC0330x4 != null) {
                    return abstractC0330x4;
                }
            }
            i++;
        }
    }

    public final void a(E0 e0, E0 e1, C0245l1 c0245l1, Set set, C0275p3 c0275p3, E0 e2) {
        AbstractC0330x3 abstractC0330x3A;
        if (!e0.b0() || e2.b0()) {
            C0210g1 c0210g1A = e2.a(c0245l1);
            if (c0210g1A != null) {
                abstractC0330x3A = AbstractC0330x3.a(e1, e2, c0210g1A);
            } else {
                abstractC0330x3A = a(e1, e2, c0245l1, set);
            }
            if (abstractC0330x3A != null) {
                c0275p3.a(abstractC0330x3A);
            }
        }
    }
}
