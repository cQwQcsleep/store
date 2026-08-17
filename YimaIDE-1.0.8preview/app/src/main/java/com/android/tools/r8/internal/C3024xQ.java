package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C3024xQ;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3024xQ {
    public static final /* synthetic */ boolean d = true;
    public final com.android.tools.r8.graph.E0 a;
    public final List b;
    public final List c;

    public C3024xQ(com.android.tools.r8.graph.E0 e0, ArrayList arrayList, ArrayList arrayList2) {
        this.a = e0;
        this.b = arrayList;
        this.c = arrayList2;
    }

    public static C3024xQ a(final C0333y c0333y, com.android.tools.r8.graph.E0 e0, Consumer consumer) {
        if (!d && !e0.t1()) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.E0 e0D = e0.w1() ? e0 : c0333y.d(e0.W0());
        if (e0D == null) {
            if (consumer != null) {
                consumer.accept(e0);
            }
            return null;
        }
        final ArrayList arrayList = new ArrayList(e0D.Y0().size());
        final ArrayList arrayList2 = new ArrayList();
        e0D.i(new Consumer() { // from class: hri
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3024xQ.a(c0333y, arrayList, arrayList2, (I2) obj);
            }
        });
        return new C3024xQ(e0D, arrayList, arrayList2);
    }

    public static /* synthetic */ void a(C0333y c0333y, List list, List list2, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0D = c0333y.d(i2);
        if (e0D != null) {
            list.add(e0D);
        } else {
            list2.add(i2);
        }
    }

    public final C2924wC a() {
        return new C2924wC(AbstractC3179zC.c(this.b, new EX() { // from class: iri
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((E0) obj).q1();
            }
        }), new InterfaceC0392Br() { // from class: jri
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((E0) obj).m();
            }
        });
    }
}
