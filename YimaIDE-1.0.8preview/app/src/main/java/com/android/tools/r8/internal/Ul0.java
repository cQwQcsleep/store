package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import defpackage.waa;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ul0 extends AbstractC0429Dc {
    public static final /* synthetic */ boolean j = true;
    public final C1291d6 a = new C1291d6();
    public final IdentityHashMap b = new IdentityHashMap();
    public final IdentityHashMap c = new IdentityHashMap();
    public final Z5 d = Z5.a();
    public final IdentityHashMap e = new IdentityHashMap();
    public final C1291d6 f = new C1291d6();
    public final IdentityHashMap g = new IdentityHashMap();
    public final Set h = AbstractC2780ub0.c();
    public final IdentityHashMap i = new IdentityHashMap();

    public final void a(Set set, final C0322w2 c0322w2, C0322w2 c0322w3) {
        if (!AbstractC3179zC.b(set, new EX() { // from class: lze
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.c(c0322w2, (C0322w2) obj);
            }
        })) {
            if (this.d.c.containsKey(c0322w2) && !this.d.e.containsKey(c0322w2)) {
                Z5 z5 = this.d;
                z5.e.put(c0322w2, (C0322w2) z5.d(c0322w2));
            }
            this.d.a((Iterable) set, (Object) c0322w2);
            if (set.size() <= 1 || this.d.e.containsKey(c0322w2)) {
                return;
            }
            this.d.e.put(c0322w2, c0322w3);
            return;
        }
        boolean z = j;
        if (!z && set.size() != 1) {
            x1f.a();
            return;
        }
        C0322w2 c0322w4 = (C0322w2) set.iterator().next();
        C0322w2 c0322w5 = (C0322w2) this.d.d(c0322w4);
        Set setB = this.d.b(c0322w4);
        if (!z && !setB.contains(c0322w4)) {
            x1f.a();
        } else {
            this.d.a((Iterable) setB, (Object) c0322w2);
            this.d.e.put(c0322w2, c0322w5);
        }
    }

    public final void b(C0322w2 c0322w2, C0322w2 c0322w3) {
        if (j || this.d.a(c0322w2).size() > 1) {
            this.d.e.put(c0322w2, c0322w3);
        } else {
            x1f.a();
        }
    }

    public final boolean c(C0322w2 c0322w2, C0322w2 c0322w3) {
        return this.d.c.containsKey(c0322w3) && !c0322w3.a(c0322w2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final void a() {
        C1291d6 c1291d6 = this.a;
        c1291d6.b.putAll(this.b);
        this.b.clear();
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        final IdentityHashMap identityHashMap2 = new IdentityHashMap();
        this.e.forEach(new BiConsumer() { // from class: oze
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(identityHashMap, identityHashMap2, (C0322w2) obj, (C0322w2) obj2);
            }
        });
        this.d.a(this.e.keySet());
        this.d.putAll(identityHashMap);
        identityHashMap2.forEach(new BiConsumer() { // from class: pze
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b((C0322w2) obj, (C0322w2) obj2);
            }
        });
        this.e.clear();
        C1291d6 c1291d7 = this.f;
        c1291d7.b.putAll(this.g);
        this.g.clear();
        this.h.removeAll(this.i.keySet());
        this.h.addAll(this.i.values());
        this.i.clear();
    }

    public final void a(Map map, Map map2, C0322w2 c0322w2, C0322w2 c0322w3) {
        Set setA = this.d.a(c0322w2);
        if (setA.isEmpty()) {
            map.put(c0322w2, c0322w3);
            return;
        }
        Iterator it = setA.iterator();
        while (it.hasNext()) {
            map.put((C0322w2) it.next(), c0322w3);
        }
        if (this.d.e.containsKey(c0322w2)) {
            if (j || setA.size() > 1) {
                map2.put(c0322w3, (C0322w2) this.d.d(c0322w2));
                return;
            } else {
                x1f.a();
                return;
            }
        }
        if (j || setA.size() == 1) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final void a(C0245l1 c0245l1, C0245l1 c0245l2) {
        Object obj = this.a.b.f().get(c0245l1);
        Object obj2 = c0245l1;
        if (obj != null) {
            obj2 = obj;
        }
        this.b.put((C0245l1) obj2, c0245l2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final void a(C0322w2 c0322w2, C0322w2 c0322w3) {
        if (this.f.b.containsValue(c0322w2)) {
            this.g.put((C0322w2) this.f.b.f().get(c0322w2), c0322w3);
        } else {
            this.e.put(c0322w2, c0322w3);
        }
        if (this.h.contains(c0322w2)) {
            this.i.put(c0322w2, c0322w3);
        }
    }

    public final void a(C0231j1 c0231j1, C0231j1 c0231j2) {
        this.d.a(c0231j1.getReference(), c0231j2.getReference());
    }

    public final void a(C0231j1 c0231j1, C0231j1 c0231j2, C0231j1 c0231j3, C0231j1 c0231j4) {
        if (c0231j2 != null) {
            if (!j && c0231j3 != null) {
                x1f.a();
                return;
            }
            this.d.a(c0231j1.getReference(), c0231j2.getReference());
            this.d.a(c0231j2.getReference(), c0231j2.getReference());
            Z5 z5 = this.d;
            z5.e.put(c0231j2.getReference(), c0231j2.getReference());
        } else {
            if (!j && c0231j3 == null) {
                x1f.a();
                return;
            }
            this.d.a(c0231j1.getReference(), c0231j3.getReference());
        }
        if (c0231j4 == null) {
            return;
        }
        C1291d6 c1291d6 = this.f;
        c1291d6.b.a(c0231j1.getReference(), c0231j4.getReference());
        if (c0231j4.z0()) {
            this.h.add(c0231j4.getReference());
        }
    }

    public final void a(C0322w2 c0322w2, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.D2 d2) {
        ((Map) this.c.computeIfAbsent(d2.getType(), IM.a(new waa()))).put(c0322w2, b5.getReference());
    }

    public final void a(Ul0 ul0) {
        this.a.a((V5) ul0.a);
        ul0.d.a(new InterfaceC1853ji0() { // from class: mze
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                this.a.a((Set) obj, (C0322w2) obj2, (C0322w2) obj3);
            }
        });
        this.h.addAll(ul0.h);
        this.f.a((V5) ul0.f);
        ul0.c.forEach(new BiConsumer() { // from class: nze
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((I2) obj, (Map) obj2);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final AbstractC0455Ec a(C0333y c0333y, ON on) {
        C1605gm0 c1605gm0 = (C1605gm0) on;
        if (j || !c1605gm0.b()) {
            return new Vl0(c0333y, c1605gm0, this.a, this.c, this.d, this.f, this.h);
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ void a(com.android.tools.r8.graph.I2 i2, Map map) {
        ((Map) this.c.computeIfAbsent(i2, IM.a(new waa()))).putAll(map);
    }
}
