package com.android.tools.r8.graph;

import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.R5;
import com.android.tools.r8.graph.S5;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C0702Nq;
import com.android.tools.r8.internal.EX;
import com.android.tools.r8.internal.W40;
import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class S5 {
    public static final W40 e;
    public static final /* synthetic */ boolean f = true;
    public final Map a;
    public final Map b;
    public final InterfaceC0189d1 c;
    public final B1 d;

    static {
        int i = AbstractC2554rv.c;
        e = W40.j;
    }

    public S5(ConcurrentHashMap concurrentHashMap, IdentityHashMap identityHashMap, InterfaceC0189d1 interfaceC0189d1) {
        this.b = concurrentHashMap;
        this.a = identityHashMap;
        this.c = interfaceC0189d1;
        this.d = interfaceC0189d1.a();
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.AbstractCollection, java.util.Set] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.util.AbstractCollection, java.util.Set] */
    public static void a(Iterable iterable, IdentityHashMap identityHashMap, ConcurrentHashMap concurrentHashMap, InterfaceC0189d1 interfaceC0189d1) {
        a(interfaceC0189d1.a().a2, concurrentHashMap).a(0);
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            E0 e0 = (E0) it.next();
            a(identityHashMap, concurrentHashMap, e0.e, e0, interfaceC0189d1);
        }
        identityHashMap.replaceAll(new BiFunction() { // from class: ymc
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AbstractC2554rv.a((Set) obj2);
            }
        });
        if (f) {
            return;
        }
        Set setC = AbstractC2780ub0.c();
        ArrayDeque arrayDeque = new ArrayDeque();
        I2 i2 = interfaceC0189d1.a().a2;
        arrayDeque.add(i2);
        while (!arrayDeque.isEmpty()) {
            I2 i3 = (I2) arrayDeque.pop();
            E0 e0A = interfaceC0189d1.a(i3);
            I2 i4 = e0A == null ? i3 == i2 ? null : i2 : e0A.g;
            boolean z = f;
            if (!z && setC.contains(i3)) {
                x1f.a();
                return;
            }
            setC.add(i3);
            R5 r5A = a(i3, concurrentHashMap);
            if (i4 != null) {
                R5 r5A2 = a(i4, concurrentHashMap);
                if (!z) {
                    int i = r5A2.b;
                    int i5 = r5A.b;
                    if (i != i5 - 1 && (i != 0 || i5 != -2)) {
                        x1f.a();
                        return;
                    }
                }
                if (!z && !r5A2.c.contains(i3)) {
                    x1f.a();
                    return;
                }
            } else if (!z && r5A.b != 0) {
                x1f.a();
                return;
            }
            if (r5A.b != -2) {
                arrayDeque.addAll(r5A.c);
            } else if (e0A != null) {
                for (I2 i6 : e0A.h.b) {
                    R5 r5A3 = a(i6, concurrentHashMap);
                    boolean z2 = f;
                    if (!z2 && !r5A3.c.contains(i3)) {
                        x1f.a();
                        return;
                    } else {
                        if (!z2 && r5A3.b != -2) {
                            x1f.a();
                            return;
                        }
                    }
                }
            } else {
                continue;
            }
        }
    }

    public static void b(Map map, Map map2, I2 i2, E0 e0, InterfaceC0189d1 interfaceC0189d1) {
        if (i2 == null || !((Set) map.computeIfAbsent(i2, new Function() { // from class: vmc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return S5.f((I2) obj);
            }
        })).add(e0.e)) {
            return;
        }
        a(map, map2, i2, e0, interfaceC0189d1);
    }

    public static /* synthetic */ Set f(I2 i2) {
        return new HashSet();
    }

    public final boolean c(I2 i2) {
        return a(i2, this.b).b();
    }

    public final boolean d(I2 i2) {
        return !a(i2, this.b).b();
    }

    public final boolean e(I2 i2) {
        return a(i2, this.b).b();
    }

    public Set<I2> g(I2 i2) {
        if (f || i2.M0()) {
            Set<I2> set = (Set) this.a.get(i2);
            return set == null ? W40.j : set;
        }
        x1f.a();
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.AbstractCollection, java.util.Set] */
    public final Set b(I2 i2) {
        return a(i2, this.b).c;
    }

    public final Iterable a(I2 i2) {
        R5 r5A = a(i2, this.b);
        if (!f && r5A.b == -1) {
            x1f.a();
            return null;
        }
        int i = r5A.b;
        if (i == -2) {
            return AbstractC3179zC.c(r5A.c, new EX() { // from class: bnc
                @Override // com.android.tools.r8.internal.EX
                public final boolean apply(Object obj) {
                    return this.b.c((I2) obj);
                }
            });
        }
        AbstractCollection abstractCollection = r5A.c;
        return i == 0 ? AbstractC3179zC.c(abstractCollection, new EX() { // from class: cnc
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.d((I2) obj);
            }
        }) : abstractCollection;
    }

    public static R5 a(I2 i2, Map map) {
        if (f || i2 != null) {
            return (R5) map.computeIfAbsent(i2, new Function() { // from class: xmc
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new R5((I2) obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    /* JADX WARN: Type inference failed for: r9v2, types: [java.util.AbstractCollection, java.util.Set] */
    public static void a(final Map map, final Map map2, final I2 i2, final E0 e0, final InterfaceC0189d1 interfaceC0189d1) {
        E0 e0A = interfaceC0189d1.a(i2);
        final R5 r5A = a(i2, map2);
        if (e0A != null) {
            e0A.b(new BiConsumer() { // from class: anc
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    S5.a(map, map2, e0, interfaceC0189d1, i2, r5A, (I2) obj, (Boolean) obj2);
                }
            });
            if (e0A.isInterface()) {
                r5A.a(-2);
                return;
            }
            return;
        }
        I2 i3 = interfaceC0189d1.a().a2;
        if (i2 != i3) {
            R5 r5A2 = a(i3, map2);
            if (!R5.d && r5A2.b == -1) {
                x1f.a();
                return;
            }
            r5A2.a();
            r5A2.c.add(r5A.a);
            r5A.a(r5A2.b + 1);
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.AbstractCollection, java.util.Set] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.AbstractCollection, java.util.Set] */
    public static void a(Map map, Map map2, E0 e0, InterfaceC0189d1 interfaceC0189d1, I2 i2, R5 r5, I2 i3, Boolean bool) {
        b(map, map2, i3, e0, interfaceC0189d1);
        R5 r5A = a(i3, map2);
        if (bool.booleanValue()) {
            r5A.a(-2);
            r5A.a();
            r5A.c.add(i2);
        } else {
            if (!R5.d && r5A.b == -1) {
                x1f.a();
                return;
            }
            r5A.a();
            r5A.c.add(r5.a);
            r5.a(r5A.b + 1);
        }
    }

    public static S5 a(C0333y<? extends C0229j> c0333y) {
        C0229j c0229j = (C0229j) c0333y.g();
        C0177b3 c0177b3B = c0229j.b().b();
        C0702Nq c0702NqA = AbstractC0728Oq.a(c0177b3B.k, c0177b3B.l, c0177b3B.k());
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        IdentityHashMap identityHashMap = new IdentityHashMap();
        a(c0702NqA, identityHashMap, concurrentHashMap, c0229j);
        return new S5(concurrentHashMap, identityHashMap, c0229j);
    }

    public final void a(I2 i2, Consumer consumer) {
        a(i2).forEach(consumer);
    }

    public final void a(Consumer consumer) {
        AbstractC3179zC.c(a(this.d.a2, this.b).c, new EX() { // from class: wmc
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.e((I2) obj);
            }
        }).forEach(consumer);
    }

    public final List a() {
        final ArrayList arrayList = new ArrayList();
        a(new Consumer() { // from class: zmc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(arrayList, (I2) obj);
            }
        });
        return AbstractC0327x0.a(arrayList);
    }

    public final /* synthetic */ void a(final List list, I2 i2) {
        InterfaceC0174b0 interfaceC0174b0G = this.c.g(i2);
        Objects.requireNonNull(list);
        interfaceC0174b0G.b(new Consumer() { // from class: dnc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((E0) obj);
            }
        });
    }
}
