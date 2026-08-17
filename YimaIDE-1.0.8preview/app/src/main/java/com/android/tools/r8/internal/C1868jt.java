package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1868jt extends MN implements Collection {
    public static final /* synthetic */ boolean f = true;
    public final LinkedList b;
    public C0245l1 c;
    public com.android.tools.r8.graph.D2 d;
    public Y5 e;

    public C1868jt() {
        this.d = null;
        this.b = new LinkedList();
    }

    public final void a(final C0333y c0333y) {
        boolean z = f;
        if (!z && !g()) {
            x1f.a();
            return;
        }
        final X5 x5 = new X5(new LinkedHashMap(), new LinkedHashMap());
        a(new Consumer() { // from class: geh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c0333y, x5, (D2) obj);
            }
        });
        if (z || !e()) {
            this.e = x5;
        } else {
            x1f.a();
        }
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        return this.b.add((com.android.tools.r8.graph.D2) obj);
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        return this.b.addAll(collection);
    }

    public final void b(C0333y c0333y) {
        Iterable<com.android.tools.r8.graph.D2> iterableC = AbstractC3179zC.c(this.b, new EX() { // from class: heh
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((D2) obj).z1();
            }
        });
        if (!((AbstractC1279d0) iterableC.iterator()).hasNext()) {
            iterableC = this.b;
        }
        com.android.tools.r8.graph.D2 next = iterableC.iterator().next();
        for (com.android.tools.r8.graph.D2 d2 : iterableC) {
            if (c0333y.r != null && c0333y.t().a(d2).b(c0333y.M())) {
                next = d2;
                break;
            } else if (d2.getType().z0().e < next.getType().z0().e) {
                next = d2;
            }
        }
        com.android.tools.r8.graph.D2 d2A = c0333y.Q().D.a(c0333y, iterableC, next);
        if (f || !g()) {
            this.d = d2A;
        } else {
            x1f.a();
        }
    }

    public final com.android.tools.r8.graph.I2 c() {
        if (f || C2753uC.a(this.b, new Function() { // from class: deh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).d1();
            }
        })) {
            return ((com.android.tools.r8.graph.D2) this.b.getFirst()).d1();
        }
        x1f.a();
        return null;
    }

    @Override // java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.b.contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.b.containsAll(collection);
    }

    public final boolean d() {
        return this.c != null;
    }

    public final boolean e() {
        return this.e != null;
    }

    public final boolean g() {
        return this.d != null;
    }

    public final boolean i() {
        return !j();
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.b.iterator();
    }

    public final boolean j() {
        boolean z = f;
        if (!z && this.b.isEmpty()) {
            x1f.a();
            return false;
        }
        if (z || C2753uC.a(this.b, new Function() { // from class: ieh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((D2) obj).isInterface());
            }
        })) {
            return ((com.android.tools.r8.graph.D2) this.b.getFirst()).isInterface();
        }
        x1f.a();
        return false;
    }

    public final boolean k() {
        return this.b.size() < 2;
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        return this.b.remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.b.removeAll(collection);
    }

    @Override // java.util.Collection
    public final boolean removeIf(Predicate predicate) {
        return this.b.removeIf(predicate);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.b.retainAll(collection);
    }

    @Override // com.android.tools.r8.internal.MN
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return this.b.toArray();
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.toArray(objArr);
    }

    public C1868jt(com.android.tools.r8.graph.D2 d2) {
        this();
        this.b.add(d2);
    }

    public final C0245l1 a() {
        if (f || d()) {
            return this.c;
        }
        x1f.a();
        return null;
    }

    public final void a(Consumer consumer) {
        if (!f && !g()) {
            x1f.a();
            return;
        }
        for (com.android.tools.r8.graph.D2 d2 : this.b) {
            if (d2 != this.d) {
                consumer.accept(d2);
            }
        }
    }

    public final /* synthetic */ void a(C0333y c0333y, final InterfaceC1911kQ interfaceC1911kQ, com.android.tools.r8.graph.D2 d2) {
        com.android.tools.r8.graph.D2 d3 = this.d;
        Objects.requireNonNull(interfaceC1911kQ);
        InterfaceC2781uc.a(c0333y, d2, d3, new BiConsumer() { // from class: feh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                interfaceC1911kQ.a((C0210g1) obj, (C0210g1) obj2);
            }
        });
    }

    public final /* synthetic */ boolean a(com.android.tools.r8.graph.D2 d2) {
        return d2 != this.d;
    }

    public final C2838vC b() {
        if (f || g()) {
            return AbstractC3179zC.c(this.b, new EX() { // from class: eeh
                @Override // com.android.tools.r8.internal.EX
                public final boolean apply(Object obj) {
                    return this.b.a((D2) obj);
                }
            });
        }
        x1f.a();
        return null;
    }
}
