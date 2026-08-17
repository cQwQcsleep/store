package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C2273oe0;
import com.android.tools.r8.internal.RU;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oe0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2273oe0 extends AbstractC2359pe0 {
    public final AbstractC0706Nu a;

    public C2273oe0(AbstractC0706Nu abstractC0706Nu) {
        this.a = abstractC0706Nu;
    }

    public final C2273oe0 a(final C0333y c0333y, final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        this.a.forEach(new BiConsumer() { // from class: fyh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C2273oe0.a(c0629KuE, abstractC3148ys, c0333y, abstractC3148ys2, (C0245l1) obj, (RU) obj2);
            }
        });
        return new C2273oe0(c0629KuE.b());
    }

    @Override // com.android.tools.r8.internal.AbstractC2359pe0
    public final C2273oe0 a() {
        return this;
    }

    public static void a(C0629Ku c0629Ku, AbstractC3148ys abstractC3148ys, C0333y c0333y, AbstractC3148ys abstractC3148ys2, C0245l1 c0245l1, RU ru) {
        c0629Ku.a(abstractC3148ys.e((AbstractC3148ys) null, c0245l1), ru.a(c0333y, abstractC3148ys, abstractC3148ys2));
    }
}
