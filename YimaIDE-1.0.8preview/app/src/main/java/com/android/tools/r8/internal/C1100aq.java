package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1100aq;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1100aq extends AbstractC0837Sv {
    public final Map a;

    public C1100aq(ConcurrentHashMap concurrentHashMap) {
        this.a = concurrentHashMap;
    }

    @Override // com.android.tools.r8.internal.AbstractC0837Sv
    public final C0245l1 a(com.android.tools.r8.graph.I2 i2) {
        C0245l1 c0245l1 = (C0245l1) this.a.get(i2);
        if (c0245l1 != null) {
            return c0245l1;
        }
        throw new Kk0("Unexpected InitClass instruction for `" + i2.m0() + "`");
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C1100aq a(final AbstractC3148ys abstractC3148ys) {
        final C0811Rv c0811Rv = new C0811Rv();
        this.a.forEach(new BiConsumer() { // from class: deg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C1100aq.a(abstractC3148ys, c0811Rv, (I2) obj, (C0245l1) obj2);
            }
        });
        return new C1100aq(c0811Rv.a);
    }

    @Override // com.android.tools.r8.internal.AbstractC0837Sv
    public final AbstractC0837Sv a(final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        return (AbstractC0837Sv) ch0.a("Rewrite FinalInitClassLens", new InterfaceC2706th0() { // from class: ceg
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(abstractC3148ys);
            }
        });
    }

    public static void a(AbstractC3148ys abstractC3148ys, C0811Rv c0811Rv, com.android.tools.r8.graph.I2 i2, C0245l1 c0245l1) {
        abstractC3148ys.getClass();
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(AbstractC3148ys.g(), i2);
        C0245l1 c0245l1E = abstractC3148ys.e((AbstractC3148ys) null, c0245l1);
        if (!C0811Rv.b) {
            c0811Rv.getClass();
            if (c0245l1E.f != i2C) {
                x1f.a();
                return;
            }
        }
        c0811Rv.a.put(i2C, c0245l1E);
    }
}
