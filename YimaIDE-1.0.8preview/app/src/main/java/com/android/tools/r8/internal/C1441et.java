package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C1441et;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.et, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1441et extends AbstractC0429Dc {
    public static final /* synthetic */ boolean h = true;
    public final Z5 a = Z5.a();
    public final X5 b = new X5(new IdentityHashMap(), new IdentityHashMap());
    public final Z5 c = Z5.a();
    public final IdentityHashMap d = new IdentityHashMap();
    public final X5 e = new X5(new IdentityHashMap(), new IdentityHashMap());
    public final Z5 f = Z5.a();
    public final Z5 g = Z5.a();

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(C2924wC c2924wC, final C0245l1 c0245l1, C0245l1 c0245l2) {
        boolean zContains;
        boolean z = h;
        if (!z && !De0.a(c2924wC).anyMatch(new Predicate() { // from class: utg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C1441et.b(c0245l1, (C0245l1) obj);
            }
        })) {
            x1f.a();
            return;
        }
        if (!z) {
            Stream streamA = De0.a(c2924wC);
            final Z5 z5 = this.a;
            Objects.requireNonNull(z5);
            if (!streamA.noneMatch(new Predicate() { // from class: vtg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return z5.containsValue((C0245l1) obj);
                }
            })) {
                x1f.a();
                return;
            }
        }
        if (!z) {
            if (c2924wC instanceof Collection) {
                try {
                    zContains = ((Collection) c2924wC).contains(c0245l2);
                } catch (ClassCastException | NullPointerException unused) {
                    zContains = false;
                }
            } else {
                GC gcA = NC.a(c2924wC.b.iterator(), c2924wC.c);
                if (c0245l2 == null) {
                    while (true) {
                        if (!gcA.hasNext()) {
                            zContains = false;
                        } else if (gcA.next() == null) {
                            zContains = true;
                        }
                    }
                } else {
                    while (true) {
                        if (!gcA.hasNext()) {
                            zContains = false;
                        } else if (c0245l2.equals(gcA.next())) {
                            zContains = true;
                        }
                    }
                }
            }
            if (!zContains) {
                x1f.a();
                return;
            }
        }
        GC gcA2 = NC.a(c2924wC.b.iterator(), c2924wC.c);
        while (gcA2.b.hasNext()) {
            this.a.a((C0245l1) gcA2.a(gcA2.b.next()), c0245l1);
        }
        this.a.e.put(c0245l1, c0245l2);
    }

    public final void b(C0322w2 c0322w2, C0322w2 c0322w3, boolean z) {
        this.c.a(c0322w2, c0322w3);
        if (z) {
            this.c.e.put(c0322w3, c0322w2);
        }
    }

    public final void c(C0322w2 c0322w2, C0322w2 c0322w3) {
        b(c0322w2, c0322w3, false);
    }

    public final void b(C0322w2 c0322w2, C0322w2 c0322w3) {
        this.b.a(c0322w2, c0322w3);
    }

    public static boolean b(C0245l1 c0245l1, C0245l1 c0245l2) {
        return !c0245l2.a(c0245l1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final C1527ft a(C0333y c0333y, C2039lt c2039lt) {
        boolean z = h;
        if (!z && !this.e.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.f.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.g.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.c.c.keySet().stream().allMatch(new Predicate() { // from class: stg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a((C0322w2) obj);
            }
        })) {
            x1f.a();
            return null;
        }
        return new C1527ft(c0333y, c2039lt, this.d, this.a, this.b, this.c);
    }

    public final boolean a(C0322w2 c0322w2) {
        if (h || this.c.a(c0322w2).size() == 1 || this.c.e.containsKey(c0322w2)) {
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final void a(C0245l1 c0245l1, C0245l1 c0245l2) {
        Z5 z5 = this.a;
        Z5 z6 = this.f;
        Set setA = z5.a(c0245l1);
        if (setA.isEmpty()) {
            z6.a(c0245l1, c0245l2);
            return;
        }
        z6.a((Iterable) setA, (Object) c0245l2);
        AbstractC0287r2 abstractC0287r2 = (AbstractC0287r2) z5.d(c0245l1);
        if (abstractC0287r2 != null) {
            z6.e.put(c0245l2, abstractC0287r2);
        }
    }

    public final void a(C0322w2 c0322w2, C0322w2 c0322w3, boolean z) {
        this.b.a(c0322w2, c0322w3);
        b(c0322w2, c0322w3, z);
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final void a(C0322w2 c0322w2, C0322w2 c0322w3) {
        Set setA = this.b.a(c0322w2);
        boolean zIsEmpty = setA.isEmpty();
        X5 x5 = this.e;
        if (zIsEmpty) {
            x5.a(c0322w2, c0322w3);
        } else {
            x5.a((Iterable) setA, (Object) c0322w3);
        }
        Z5 z5 = this.c;
        Z5 z6 = this.g;
        Set setA2 = z5.a(c0322w2);
        if (setA2.isEmpty()) {
            z6.a(c0322w2, c0322w3);
            return;
        }
        z6.a((Iterable) setA2, (Object) c0322w3);
        AbstractC0287r2 abstractC0287r2 = (AbstractC0287r2) z5.d(c0322w2);
        if (abstractC0287r2 != null) {
            z6.e.put(c0322w3, abstractC0287r2);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0429Dc
    public final void a() {
        this.b.b(this.e.b.keySet());
        X5 x5 = this.e;
        final X5 x6 = this.b;
        Objects.requireNonNull(x6);
        x5.a(new BiConsumer() { // from class: ttg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                x6.a((Set) obj, (C0322w2) obj2);
            }
        });
        X5 x7 = this.e;
        x7.b.clear();
        x7.c.clear();
        Z5 z5 = this.a;
        Z5 z6 = this.f;
        z5.b(z6.b.keySet());
        z5.a((InterfaceC1037a6) z6);
        z6.b.clear();
        z6.c.clear();
        z6.e.clear();
        Z5 z7 = this.c;
        Z5 z8 = this.g;
        z7.b(z8.b.keySet());
        z7.a((InterfaceC1037a6) z8);
        z8.b.clear();
        z8.c.clear();
        z8.e.clear();
    }
}
