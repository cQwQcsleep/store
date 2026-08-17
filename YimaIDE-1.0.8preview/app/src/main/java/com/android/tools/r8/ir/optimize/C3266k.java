package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.InterfaceC2024li0;
import com.android.tools.r8.ir.optimize.C3266k;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3266k {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public C3266k(LinkedHashMap linkedHashMap) {
        this.a = linkedHashMap;
    }

    public final void a(InterfaceC1938ki0 interfaceC1938ki0) {
        Iterator it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) entry.getKey();
            Map map = (Map) entry.getValue();
            Iterator it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it2.next();
                final C2543rl0 c2543rl0 = (C2543rl0) entry2.getKey();
                C3258i c3258i = (C3258i) entry2.getValue();
                AbstractC3256h abstractC3256h = c3258i.a;
                abstractC3256h.getClass();
                if (abstractC3256h instanceof C3267l) {
                    if (!b && !c2543rl0.c(new Predicate() { // from class: yeh
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return C3266k.a(c2543rl0, (AbstractC0890Uw) obj);
                        }
                    })) {
                        x1f.a();
                        return;
                    }
                } else {
                    if (!b && !(abstractC3256h instanceof C3271p)) {
                        x1f.a();
                        return;
                    }
                    AbstractC3256h abstractC3256h2 = (AbstractC3256h) interfaceC1938ki0.a(abstractC0890Uw, c2543rl0, c3258i);
                    abstractC3256h2.getClass();
                    if ((!(abstractC3256h2 instanceof C3269n) || c2543rl0.F()) && !(abstractC3256h2 instanceof C3271p)) {
                        c3258i.a = abstractC3256h2;
                    } else {
                        it2.remove();
                    }
                }
            }
            if (map.isEmpty()) {
                it.remove();
            }
        }
    }

    public static /* synthetic */ boolean a(C2543rl0 c2543rl0, AbstractC0890Uw abstractC0890Uw) {
        return abstractC0890Uw.c() == c2543rl0;
    }

    public final void a(IdentityHashMap identityHashMap) {
        identityHashMap.forEach(new BiConsumer() { // from class: neh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((AbstractC0890Uw) obj, (Map) obj2);
            }
        });
    }

    public final /* synthetic */ void a(AbstractC0890Uw abstractC0890Uw, Map map) {
        final Map map2 = (Map) this.a.get(abstractC0890Uw);
        if (map2 != null) {
            map.keySet().forEach(new Consumer() { // from class: wfh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    map2.remove((C2543rl0) obj);
                }
            });
            if (map2.isEmpty()) {
                this.a.remove(abstractC0890Uw);
            }
        }
    }

    public final void a(InterfaceC2024li0 interfaceC2024li0) {
        Iterator it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) entry.getKey();
            Map map = (Map) entry.getValue();
            Iterator it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it2.next();
                if (interfaceC2024li0.a(abstractC0890Uw, (C2543rl0) entry2.getKey(), (C3258i) entry2.getValue())) {
                    it2.remove();
                }
            }
            if (map.isEmpty()) {
                it.remove();
            }
        }
    }
}
