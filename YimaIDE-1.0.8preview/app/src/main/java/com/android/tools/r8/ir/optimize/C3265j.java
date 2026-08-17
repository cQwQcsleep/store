package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C0491Fm;
import com.android.tools.r8.internal.C1034a40;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.ir.optimize.C3258i;
import com.android.tools.r8.ir.optimize.C3265j;
import defpackage.s9h;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3265j {
    public final LinkedHashMap a = new LinkedHashMap();
    public final Set b = AbstractC2780ub0.c();

    public final void a(AbstractC0890Uw abstractC0890Uw, C2543rl0 c2543rl0, final AbstractC3256h abstractC3256h, Consumer consumer) {
        C3258i c3258i = (C3258i) ((Map) this.a.computeIfAbsent(abstractC0890Uw, new Function() { // from class: y9h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3265j.a((AbstractC0890Uw) obj);
            }
        })).computeIfAbsent(c2543rl0, new Function() { // from class: cah
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3265j.a(abstractC3256h, (C2543rl0) obj);
            }
        });
        consumer.accept(c3258i);
        if ((abstractC3256h instanceof C3267l) && c3258i.b()) {
            this.b.add(c2543rl0);
        }
    }

    public final void b(AbstractC0890Uw abstractC0890Uw, C2543rl0 c2543rl0, final C0491Fm c0491Fm) {
        a(abstractC0890Uw, c2543rl0, C3271p.a, new Consumer() { // from class: l9h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C3258i) obj).a(c0491Fm);
            }
        });
    }

    public final void b(AbstractC0890Uw abstractC0890Uw, C2543rl0 c2543rl0) {
        a(abstractC0890Uw, c2543rl0, C3271p.a, new s9h());
    }

    public static /* synthetic */ Map a(AbstractC0890Uw abstractC0890Uw) {
        return new LinkedHashMap();
    }

    public static /* synthetic */ C3258i a(AbstractC3256h abstractC3256h, C2543rl0 c2543rl0) {
        return new C3258i(abstractC3256h);
    }

    public final void a(AbstractC0890Uw abstractC0890Uw, C2543rl0 c2543rl0, final C0491Fm c0491Fm) {
        a(abstractC0890Uw, c2543rl0, C3267l.a, new Consumer() { // from class: gah
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C3258i) obj).a(c0491Fm);
            }
        });
    }

    public final void a(AbstractC0890Uw abstractC0890Uw, C2543rl0 c2543rl0) {
        a(abstractC0890Uw, c2543rl0, C3267l.a, new s9h());
    }

    public final boolean a(C2543rl0 c2543rl0) {
        if (this.b.contains(c2543rl0)) {
            return false;
        }
        AbstractC2624sj0 abstractC2624sj0T = c2543rl0.t();
        abstractC2624sj0T.getClass();
        return !(abstractC2624sj0T instanceof C1034a40);
    }
}
