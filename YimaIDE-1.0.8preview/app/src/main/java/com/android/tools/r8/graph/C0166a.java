package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0166a;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.SY;
import com.android.tools.r8.internal.TY;
import com.android.tools.r8.internal.UY;
import defpackage.fag;
import defpackage.x0g;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0166a extends AbstractC0187d {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public C0166a() {
        this.a = new IdentityHashMap();
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final AbstractC0187d a(I5 i5) {
        for (UY uy : this.a.values()) {
            Iterator it = uy.b.values().iterator();
            UY uyC = null;
            while (it.hasNext()) {
                C0322w2 reference = ((B5) it.next()).getReference();
                if (i5.a(reference)) {
                    it.remove();
                    if (i5.c.containsKey(reference)) {
                        if (uyC == null) {
                            uyC = UY.c();
                        }
                        Objects.requireNonNull(uyC);
                        i5.a(reference, new fag(uyC));
                    }
                }
            }
            if (uyC != null) {
                uy.b.putAll(uyC.b);
            }
        }
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:27:0x0068 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0060 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x000c A[SYNTHETIC] */
    @Override // com.android.tools.r8.graph.AbstractC0187d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C0166a a(InterfaceC0189d1 interfaceC0189d1, AbstractC3148ys abstractC3148ys) {
        UY uy;
        final IdentityHashMap identityHashMap = null;
        for (Map.Entry entry : this.a.entrySet()) {
            C0245l1 c0245l1 = (C0245l1) entry.getKey();
            C0245l1 c0245l1E = abstractC3148ys.e((AbstractC3148ys) null, c0245l1);
            UY uy2 = (UY) entry.getValue();
            UY uyA = uy2.a(interfaceC0189d1, abstractC3148ys);
            if (c0245l1E != c0245l1 || uyA != uy2) {
                if (identityHashMap == null) {
                    identityHashMap = new IdentityHashMap(this.a.size());
                    IM.a(this.a, new BiConsumer() { // from class: mag
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            identityHashMap.put((C0245l1) obj, (UY) obj2);
                        }
                    }, (Object) c0245l1);
                }
                uy = (UY) identityHashMap.put(c0245l1E, uyA);
                if (uy == null) {
                    if (uy.b.size() <= uyA.b.size()) {
                        uyA.b.putAll(uy.b);
                    } else {
                        identityHashMap.put(c0245l1E, uy);
                        uy.b.putAll(uyA.b);
                    }
                }
            } else if (identityHashMap != null) {
                uy = (UY) identityHashMap.put(c0245l1E, uyA);
                if (uy == null) {
                    if (uy.b.size() <= uyA.b.size()) {
                        uyA.b.putAll(uy.b);
                    } else {
                        identityHashMap.put(c0245l1E, uy);
                        uy.b.putAll(uyA.b);
                    }
                }
            }
        }
        return identityHashMap != null ? new C0166a(IM.a(identityHashMap, this.a.size())) : this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean c() {
        return this.a.isEmpty();
    }

    public C0166a(Map map) {
        this.a = map;
    }

    public static void a(C0245l1 c0245l1, UY uy, C0245l1 c0245l2, UY uy2) {
        if (c0245l2 != c0245l1) {
            uy.b.putAll(uy2.b);
        }
    }

    public final void a(final Consumer consumer, final Predicate predicate) {
        Map map = this.a;
        if (map != null) {
            map.forEach(new BiConsumer() { // from class: oag
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    C0166a.a(predicate, consumer, (C0245l1) obj, (UY) obj2);
                }
            });
        }
    }

    public static /* synthetic */ void a(Predicate predicate, Consumer consumer, C0245l1 c0245l1, UY uy) {
        if (predicate.test(c0245l1)) {
            consumer.accept(c0245l1);
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final void a(Consumer consumer) {
        UY uyC = UY.c();
        Map map = this.a;
        if (map != null) {
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                for (B5 b5 : ((UY) it.next()).b.values()) {
                    if (uyC.add(b5)) {
                        consumer.accept(b5);
                    }
                }
            }
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final void a(final C0245l1 c0245l1) {
        Map map = this.a;
        if (map != null) {
            final UY uy = (UY) map.computeIfAbsent(c0245l1, new Function() { // from class: qag
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UY.c();
                }
            });
            this.a.forEach(new BiConsumer() { // from class: sag
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    C0166a.a(c0245l1, uy, (C0245l1) obj, (UY) obj2);
                }
            });
            this.a.clear();
            if (!uy.b.isEmpty()) {
                this.a.put(c0245l1, uy);
            }
            if (b || this.a.size() <= 1) {
                return;
            }
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean a(Predicate predicate) {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((UY) it.next()).b.values().iterator();
            while (it2.hasNext()) {
                if (predicate.test((B5) it2.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean a(C0231j1 c0231j1) {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((UY) it.next()).b.values().iterator();
            while (it2.hasNext()) {
                if (((B5) it2.next()).e() != c0231j1) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean a(C0245l1 c0245l1, B5 b5) {
        return ((UY) this.a.computeIfAbsent(c0245l1, new Function() { // from class: kag
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UY.c();
            }
        })).add(b5);
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final AbstractC0187d a(AbstractC0187d abstractC0187d) {
        if (abstractC0187d.c()) {
            return this;
        }
        if (abstractC0187d instanceof C0180c) {
            return abstractC0187d;
        }
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        this.a.forEach(new BiConsumer() { // from class: x9g
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C0166a.a(identityHashMap, (C0245l1) obj, (UY) obj2);
            }
        });
        abstractC0187d.a().a.forEach(new BiConsumer() { // from class: bag
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C0166a.b(identityHashMap, (C0245l1) obj, (UY) obj2);
            }
        });
        return new C0166a(identityHashMap);
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final boolean b(Predicate predicate) {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((UY) it.next()).b.values().iterator();
            while (it2.hasNext()) {
                if (!predicate.test((B5) it2.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final int b() {
        if (this.a.size() == 1) {
            return ((UY) this.a.values().iterator().next()).b.size();
        }
        x0g.a("Should only be querying the number of access contexts after flattening");
        return 0;
    }

    public static void a(Map map, C0245l1 c0245l1, UY uy) {
        SY sy = UY.d;
        TY ty = new TY(uy.size());
        ty.addAll(uy);
        map.put(c0245l1, ty);
    }

    public static void b(Map map, C0245l1 c0245l1, UY uy) {
        ((UY) map.computeIfAbsent(c0245l1, new Function() { // from class: q9g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UY.c();
            }
        })).b.putAll(uy.b);
    }

    @Override // com.android.tools.r8.graph.AbstractC0187d
    public final C0166a a() {
        return this;
    }
}
