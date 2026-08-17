package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.C2081mO;
import com.android.tools.r8.internal.HS;
import com.android.tools.r8.internal.IS;
import defpackage.dh6;
import defpackage.eug;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IS {
    public static final /* synthetic */ boolean c = true;
    public final Map a;
    public final IdentityHashMap b = new IdentityHashMap();

    public IS(ConcurrentHashMap concurrentHashMap) {
        this.a = concurrentHashMap;
    }

    public final void a(AbstractC1088ai0 abstractC1088ai0) {
        GS gs;
        C2628sl0 c2628sl0;
        Sm0 sm0 = new Sm0(2);
        sm0.b(abstractC1088ai0);
        int i = 0;
        while (sm0.b()) {
            AbstractC1088ai0 abstractC1088ai1 = (AbstractC1088ai0) sm0.d();
            boolean z = c;
            if (!z) {
                abstractC1088ai1.getClass();
            }
            abstractC1088ai1.getClass();
            C0322w2 c0322w2 = abstractC1088ai1.a;
            if (this.a.containsKey(c0322w2)) {
                HS hsA = a(c0322w2);
                if (abstractC1088ai1 instanceof C1174bi0) {
                    gs = hsA.a;
                } else {
                    if (!z && !(abstractC1088ai1 instanceof Zh0)) {
                        x1f.a();
                        return;
                    }
                    gs = hsA.b[abstractC1088ai1.a().b];
                }
            } else {
                gs = GS.d;
            }
            if (gs == GS.c) {
                i++;
            } else {
                C2081mO c2081mO = (C2081mO) this.a.get(abstractC1088ai1.a);
                if (c2081mO == null) {
                    c2628sl0 = C2628sl0.c;
                } else if (abstractC1088ai1 instanceof C1174bi0) {
                    if (!C2081mO.e && c2081mO.a()) {
                        x1f.a();
                        return;
                    }
                    c2628sl0 = c2081mO.a;
                } else {
                    if (!z && !(abstractC1088ai1 instanceof Zh0)) {
                        x1f.a();
                        return;
                    }
                    int i2 = abstractC1088ai1.a().b;
                    boolean z2 = C2081mO.e;
                    if (!z2 && c2081mO.a()) {
                        x1f.a();
                        return;
                    } else {
                        if (!z2 && c2081mO.b[i2] == null) {
                            x1f.a();
                            return;
                        }
                        c2628sl0 = c2081mO.b[i2];
                    }
                }
                if (gs == GS.d || c2628sl0.a()) {
                    i = -1;
                    break;
                }
                if (!z && gs != GS.b) {
                    x1f.a();
                    return;
                }
                sm0.b((Iterable) c2628sl0.b);
                int size = c2628sl0.b.size() + i;
                if (!C2628sl0.d && c2628sl0.a()) {
                    x1f.a();
                    return;
                }
                i = c2628sl0.a + size;
            }
        }
        GS gs2 = i > 0 ? GS.c : GS.d;
        for (AbstractC1088ai0 abstractC1088ai2 : sm0.a()) {
            boolean z3 = c;
            if (!z3) {
                abstractC1088ai2.getClass();
            }
            abstractC1088ai2.getClass();
            C0322w2 c0322w3 = abstractC1088ai2.a;
            if (gs2 != GS.d || this.a.containsKey(c0322w3)) {
                HS hsA2 = a(c0322w3);
                if (abstractC1088ai2 instanceof C1174bi0) {
                    hsA2.a = gs2;
                } else {
                    if (!z3 && !(abstractC1088ai2 instanceof Zh0)) {
                        x1f.a();
                        return;
                    }
                    hsA2.b[abstractC1088ai2.a().b] = gs2;
                }
            }
        }
    }

    public final /* synthetic */ void b(C0322w2 c0322w2, HS hs) {
        if (c || this.a.containsKey(c0322w2)) {
            return;
        }
        x1f.a();
    }

    public final void c() {
        this.b.forEach(new BiConsumer() { // from class: eh6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b((C0322w2) obj, (HS) obj2);
            }
        });
    }

    public final IdentityHashMap d() {
        if (!c) {
            a(this.a);
        }
        for (C0322w2 c0322w2 : C2847vL.a(this.a.keySet(), new dh6())) {
            C2081mO c2081mO = (C2081mO) this.a.get(c0322w2);
            if (!c && c2081mO.a()) {
                x1f.a();
                return null;
            }
            HS hsA = a(c0322w2);
            if (!C2081mO.e && c2081mO.a()) {
                x1f.a();
                return null;
            }
            if (c2081mO.a.a()) {
                hsA.a = GS.d;
            } else if (hsA.a == GS.b) {
                a(new C1174bi0(c0322w2));
            }
            int i = 0;
            while (true) {
                boolean z = C2081mO.e;
                if (!z && c2081mO.a()) {
                    x1f.a();
                    return null;
                }
                if (i < c2081mO.b.length) {
                    if (!z && c2081mO.a()) {
                        x1f.a();
                        return null;
                    }
                    if (!z && c2081mO.b[i] == null) {
                        x1f.a();
                        return null;
                    }
                    if (c2081mO.b[i].a()) {
                        hsA.b[i] = GS.d;
                    } else if (hsA.b[i] == GS.b) {
                        a(new Zh0(c0322w2, i));
                    }
                    i++;
                }
            }
        }
        boolean z2 = c;
        if (!z2) {
            c();
        }
        if (!z2) {
            a();
        }
        b();
        return this.b;
    }

    public final void b() {
        this.b.values().removeIf(new Predicate() { // from class: ch6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((HS) obj).a();
            }
        });
    }

    public final HS a(C0322w2 c0322w2) {
        if (c || this.a.containsKey(c0322w2)) {
            return (HS) this.b.computeIfAbsent(c0322w2, new Function() { // from class: fh6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return HS.a((C0322w2) obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    public static void a(Map map) {
        map.forEach(new BiConsumer() { // from class: ah6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                IS.a((C0322w2) obj, (C2081mO) obj2);
            }
        });
    }

    public static /* synthetic */ void a(C0322w2 c0322w2, C2081mO c2081mO) {
        boolean z = c;
        if (!z && c2081mO.a()) {
            eug.a(c2081mO, " registered for ", c0322w2);
        } else {
            if (z || !c2081mO.b()) {
                return;
            }
            eug.a(c2081mO, " registered for ", c0322w2);
        }
    }

    public final void a() {
        this.b.forEach(new BiConsumer() { // from class: bh6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                IS.a((C0322w2) obj, (HS) obj2);
            }
        });
    }

    public static void a(C0322w2 c0322w2, HS hs) {
        if (!c && hs.a == GS.b) {
            x1f.a();
            return;
        }
        for (GS gs : hs.b) {
            if (!c && gs == GS.b) {
                x1f.a();
                return;
            }
        }
    }
}
