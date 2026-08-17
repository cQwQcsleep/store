package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ui, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2792ui {
    public static final /* synthetic */ boolean e = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.W3 b;
    public final IdentityHashMap c = new IdentityHashMap();
    public final ArrayList d = new ArrayList();

    public AbstractC2792ui(C0333y c0333y, com.android.tools.r8.graph.W3 w3) {
        this.a = c0333y;
        this.b = w3;
    }

    public void a(Collection collection) {
        ArrayDeque arrayDeque = new ArrayDeque();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.D2 d2 = (com.android.tools.r8.graph.D2) it.next();
            if (c(d2)) {
                arrayDeque.add(d2);
            }
        }
        while (!arrayDeque.isEmpty()) {
            k((com.android.tools.r8.graph.D2) arrayDeque.removeLast());
            a(arrayDeque);
        }
    }

    public final boolean b(com.android.tools.r8.graph.D2 d2) {
        return this.c.get(d2) == EnumC2707ti.b;
    }

    public boolean c(com.android.tools.r8.graph.D2 d2) {
        if (d2.l1()) {
            if (com.android.tools.r8.graph.D2.b(this.a.d(d2.d1())) != null) {
                return false;
            }
        }
        Iterator<com.android.tools.r8.graph.I2> it = d2.U0().iterator();
        while (it.hasNext()) {
            if (com.android.tools.r8.graph.D2.b(this.a.d(it.next())) != null) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ boolean d(com.android.tools.r8.graph.D2 d2) {
        boolean z = e;
        if (!z && !c(d2)) {
            x1f.a();
            return false;
        }
        if (z || b(d2)) {
            return true;
        }
        x1f.a();
        return false;
    }

    public final /* synthetic */ boolean e(com.android.tools.r8.graph.D2 d2) {
        return !b(d2);
    }

    public final void f(com.android.tools.r8.graph.D2 d2) {
        if (!e && this.c.containsKey(d2)) {
            x1f.a();
            return;
        }
        i(d2);
        g(d2);
        if (c(d2)) {
            this.d.add(d2);
        }
    }

    public final void g(com.android.tools.r8.graph.D2 d2) {
        boolean z = e;
        if (!z && b(d2)) {
            x1f.a();
            return;
        }
        if (!z && a(d2)) {
            x1f.a();
            return;
        }
        l(d2);
        if (z || !this.c.containsKey(d2)) {
            this.c.put(d2, EnumC2707ti.b);
        } else {
            x1f.a();
        }
    }

    public final void h(com.android.tools.r8.graph.D2 d2) {
        a(new Consumer() { // from class: thi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.k((D2) obj);
            }
        }, d2);
    }

    public final void i(com.android.tools.r8.graph.D2 d2) {
        boolean z = e;
        if (!z && b(d2)) {
            x1f.a();
        } else if (z || !a(d2)) {
            this.b.a(new Predicate() { // from class: rhi
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.e((D2) obj);
                }
            }, new Consumer() { // from class: shi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.f((D2) obj);
                }
            }, d2);
        } else {
            x1f.a();
        }
    }

    public abstract void j(com.android.tools.r8.graph.D2 d2);

    public final void k(com.android.tools.r8.graph.D2 d2) {
        if (a(d2)) {
            return;
        }
        if (!b(d2)) {
            i(d2);
            g(d2);
        }
        h(d2);
        if (!e && !b(d2)) {
            x1f.a();
        } else {
            this.c.put(d2, EnumC2707ti.c);
            j(d2);
        }
    }

    public abstract void l(com.android.tools.r8.graph.D2 d2);

    public final void a(ArrayDeque arrayDeque) {
        if (!e && !this.d.stream().allMatch(new Predicate() { // from class: uhi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.d((D2) obj);
            }
        })) {
            x1f.a();
        } else {
            arrayDeque.addAll(this.d);
            this.d.clear();
        }
    }

    public void a(Consumer consumer, com.android.tools.r8.graph.D2 d2) {
        ((List) this.b.b.getOrDefault(d2, Collections.EMPTY_LIST)).forEach(consumer);
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return this.c.get(d2) == EnumC2707ti.c;
    }
}
