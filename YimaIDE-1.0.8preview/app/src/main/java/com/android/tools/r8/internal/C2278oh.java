package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C2278oh;
import com.android.tools.r8.internal.InterfaceC2705th;
import defpackage.n33;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2278oh<N extends InterfaceC2705th> {
    public static final /* synthetic */ boolean j = true;
    public final ArrayDeque a = new ArrayDeque();
    public final IdentityHashMap b = new IdentityHashMap();
    public final ArrayDeque c = new ArrayDeque();
    public final ArrayDeque d = new ArrayDeque();
    public final Set e = AbstractC2780ub0.c();
    public final IdentityHashMap f = new IdentityHashMap();
    public final IdentityHashMap g = new IdentityHashMap();
    public IdentityHashMap h = new IdentityHashMap();
    public LinkedHashSet i = new LinkedHashSet();

    /* JADX INFO: renamed from: com.android.tools.r8.internal.oh$a */
    public static class a {
        public final Map a;

        public a(IdentityHashMap identityHashMap) {
            this.a = identityHashMap;
        }

        public int a() {
            Iterator it = this.a.values().iterator();
            int size = 0;
            while (it.hasNext()) {
                size += ((UY) it.next()).b.size();
            }
            return size;
        }
    }

    public final InterfaceC2705th a(InterfaceC2705th interfaceC2705th, KC kc) {
        C2192nh c2192nh;
        while (kc.hasNext()) {
            InterfaceC2705th interfaceC2705th2 = (InterfaceC2705th) kc.next();
            C2534rh c2534rh = (C2534rh) this.b.get(interfaceC2705th2);
            if (c2534rh == null) {
                return interfaceC2705th2;
            }
            if (interfaceC2705th2.f(interfaceC2705th)) {
                d(interfaceC2705th, interfaceC2705th2);
            } else if (this.d.isEmpty() || !a((InterfaceC2705th) this.d.peek(), interfaceC2705th2, c2534rh, new BiConsumer() { // from class: zyh
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.d((InterfaceC2705th) obj, (InterfaceC2705th) obj2);
                }
            })) {
                if (interfaceC2705th2.d().m1()) {
                    if (!j && !a(interfaceC2705th, interfaceC2705th2)) {
                        x1f.a();
                        return null;
                    }
                    c(interfaceC2705th, interfaceC2705th2);
                } else if (this.c.isEmpty() || !a((InterfaceC2705th) this.c.peek(), interfaceC2705th2, c2534rh, new BiConsumer() { // from class: azh
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        this.a.c((InterfaceC2705th) obj, (InterfaceC2705th) obj2);
                    }
                })) {
                    if (a(interfaceC2705th, interfaceC2705th2)) {
                        c(interfaceC2705th, interfaceC2705th2);
                    } else {
                        LinkedList linkedList = new LinkedList();
                        do {
                            if (!j && this.a.isEmpty()) {
                                x1f.a();
                                return null;
                            }
                            linkedList.add((InterfaceC2705th) this.a.pop());
                        } while (linkedList.getLast() != interfaceC2705th2);
                        InterfaceC2705th interfaceC2705th3 = (InterfaceC2705th) linkedList.getLast();
                        Iterator it = linkedList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                n33.a("Unable to satisfy force inlining constraints due to cyclic force inlining");
                                return null;
                            }
                            InterfaceC2705th interfaceC2705th4 = (InterfaceC2705th) it.next();
                            if (interfaceC2705th4.d(interfaceC2705th3)) {
                                boolean z = j;
                                if (!z && interfaceC2705th4.b(interfaceC2705th3)) {
                                    x1f.a();
                                    return null;
                                }
                                if (!z && interfaceC2705th3.a(interfaceC2705th4)) {
                                    x1f.a();
                                    return null;
                                }
                            } else {
                                if (!interfaceC2705th4.b(interfaceC2705th3)) {
                                    if (j || !interfaceC2705th3.a(interfaceC2705th4)) {
                                        c2192nh = null;
                                        break;
                                    }
                                    x1f.a();
                                    return null;
                                }
                                if (a(interfaceC2705th4, interfaceC2705th3)) {
                                    c2192nh = new C2192nh(interfaceC2705th4, interfaceC2705th3);
                                    break;
                                }
                            }
                            interfaceC2705th3 = interfaceC2705th4;
                        }
                        if (c2192nh != null) {
                            if (!j && !a(c2192nh.a, c2192nh.b)) {
                                x1f.a();
                                return null;
                            }
                            c(c2192nh.a, c2192nh.b);
                            this.i.add(c2192nh.b);
                        }
                        Iterator itDescendingIterator = linkedList.descendingIterator();
                        while (itDescendingIterator.hasNext()) {
                            this.a.push((InterfaceC2705th) itDescendingIterator.next());
                        }
                    }
                }
            }
        }
        return null;
    }

    public final void b(Collection collection) {
        ArrayDeque arrayDeque = new ArrayDeque(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayDeque.addLast(new C2449qh((InterfaceC2705th) it.next()));
        }
        while (!arrayDeque.isEmpty()) {
            AbstractC2619sh abstractC2619sh = (AbstractC2619sh) arrayDeque.removeFirst();
            abstractC2619sh.getClass();
            if (abstractC2619sh instanceof C2449qh) {
                InterfaceC2705th interfaceC2705th = abstractC2619sh.b().a;
                if (this.e.contains(interfaceC2705th)) {
                    continue;
                } else {
                    InterfaceC2705th interfaceC2705th2 = this.a.isEmpty() ? null : (InterfaceC2705th) this.a.peek();
                    this.a.push(interfaceC2705th);
                    if (!j && this.b.containsKey(interfaceC2705th)) {
                        x1f.a();
                        return;
                    }
                    this.b.put(interfaceC2705th, new C2534rh(this.a.size() - 1, interfaceC2705th2));
                    if (interfaceC2705th2 != null) {
                        if (interfaceC2705th.d().m1() && interfaceC2705th.a(interfaceC2705th2)) {
                            this.c.push(interfaceC2705th);
                        } else if (interfaceC2705th2.b().contains(interfaceC2705th)) {
                            this.d.push(interfaceC2705th);
                        }
                    }
                    Iterator it2 = interfaceC2705th.c().iterator();
                    Iterator it3 = interfaceC2705th.b().iterator();
                    it2.getClass();
                    it3.getClass();
                    arrayDeque.addFirst(new C2364ph(interfaceC2705th, new KC(new EC(new Iterator[]{it2, it3}))));
                }
            } else {
                boolean z = j;
                if (!z && !(abstractC2619sh instanceof C2364ph)) {
                    x1f.a();
                    return;
                }
                final C2364ph c2364phA = abstractC2619sh.a();
                InterfaceC2705th interfaceC2705thA = a(c2364phA.a, (KC) c2364phA.b);
                if (interfaceC2705thA != null) {
                    arrayDeque.addFirst(c2364phA);
                    arrayDeque.addFirst(new C2449qh(interfaceC2705thA));
                } else {
                    if (!z && c2364phA.b.hasNext()) {
                        x1f.a();
                        return;
                    }
                    InterfaceC2705th interfaceC2705th3 = c2364phA.a;
                    InterfaceC2705th interfaceC2705th4 = (InterfaceC2705th) this.a.pop();
                    if (!z && interfaceC2705th4 != interfaceC2705th3) {
                        x1f.a();
                        return;
                    }
                    if (!z && !this.b.containsKey(interfaceC2705th3)) {
                        x1f.a();
                        return;
                    }
                    this.b.remove(interfaceC2705th3);
                    if (this.c.peek() == interfaceC2705th4) {
                        if (!z && this.d.peek() == interfaceC2705th4) {
                            x1f.a();
                            return;
                        }
                        this.c.pop();
                    } else if (this.d.peek() == interfaceC2705th4) {
                        this.d.pop();
                    }
                    this.e.add(c2364phA.a);
                    Collection collection2 = (Collection) this.f.remove(c2364phA.a);
                    if (collection2 != null) {
                        collection2.forEach(new Consumer() { // from class: xyh
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                this.b.a(c2364phA, (InterfaceC2705th) obj);
                            }
                        });
                    }
                    Collection collection3 = (Collection) this.g.remove(c2364phA.a);
                    if (collection3 != null) {
                        collection3.forEach(new Consumer() { // from class: yyh
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((InterfaceC2705th) obj).c(c2364phA.a);
                            }
                        });
                    }
                }
            }
        }
    }

    public final void c(InterfaceC2705th interfaceC2705th, InterfaceC2705th interfaceC2705th2) {
        ((Set) this.f.computeIfAbsent(interfaceC2705th, new Function() { // from class: vyh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC2780ub0.c();
            }
        })).add(interfaceC2705th2);
    }

    public final void d(InterfaceC2705th interfaceC2705th, InterfaceC2705th interfaceC2705th2) {
        ((Set) this.g.computeIfAbsent(interfaceC2705th, new Function() { // from class: tyh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC2780ub0.c();
            }
        })).add(interfaceC2705th2);
    }

    public final /* synthetic */ void a(C2364ph c2364ph, InterfaceC2705th interfaceC2705th) {
        interfaceC2705th.e(c2364ph.a);
        b(c2364ph.a, interfaceC2705th);
    }

    public a a(Collection<N> collection) {
        boolean z;
        do {
            b(collection);
            collection = this.i;
            z = j;
            if (!z && !this.f.isEmpty()) {
                x1f.a();
                return null;
            }
            if (!z && !this.c.isEmpty()) {
                x1f.a();
                return null;
            }
            if (!z && !this.a.isEmpty()) {
                x1f.a();
                return null;
            }
            if (!z && !this.b.isEmpty()) {
                x1f.a();
                return null;
            }
            if (!z && !this.g.isEmpty()) {
                x1f.a();
                return null;
            }
            if (!z && !this.d.isEmpty()) {
                x1f.a();
                return null;
            }
            this.e.clear();
            this.i = new LinkedHashSet();
        } while (!collection.isEmpty());
        a aVar = new a(this.h);
        if (!z && !this.c.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.e.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.i.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.a.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z && !this.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (z || this.d.isEmpty()) {
            this.h = new IdentityHashMap();
            return aVar;
        }
        x1f.a();
        return null;
    }

    public final boolean a(final InterfaceC2705th interfaceC2705th, InterfaceC2705th interfaceC2705th2, C2534rh c2534rh, BiConsumer biConsumer) {
        final C2534rh c2534rh2 = (C2534rh) this.b.get(interfaceC2705th);
        if (c2534rh2.a <= c2534rh.a) {
            return false;
        }
        boolean z = j;
        if (!z) {
            Predicate predicate = new Predicate() { // from class: wyh
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C2278oh.a(interfaceC2705th, c2534rh2, (LinkedList) obj);
                }
            };
            LinkedList linkedList = new LinkedList();
            do {
                if (!j && this.a.isEmpty()) {
                    x1f.a();
                    return false;
                }
                linkedList.add((InterfaceC2705th) this.a.pop());
            } while (linkedList.getLast() != interfaceC2705th2);
            if (!z && !predicate.test(linkedList)) {
                x1f.a();
                return false;
            }
            Iterator itDescendingIterator = linkedList.descendingIterator();
            while (itDescendingIterator.hasNext()) {
                this.a.push((InterfaceC2705th) itDescendingIterator.next());
            }
        }
        if (!c2534rh2.c) {
            biConsumer.accept(c2534rh2.b, interfaceC2705th);
            this.i.add(interfaceC2705th);
            c2534rh2.c = true;
        }
        return true;
    }

    public static /* synthetic */ boolean a(InterfaceC2705th interfaceC2705th, C2534rh c2534rh, LinkedList linkedList) {
        return linkedList.contains(interfaceC2705th) && linkedList.contains(c2534rh.b);
    }

    public static boolean a(InterfaceC2705th interfaceC2705th, InterfaceC2705th interfaceC2705th2) {
        if (!j && !interfaceC2705th2.a(interfaceC2705th)) {
            x1f.a();
            return false;
        }
        C0231j1 c0231j1D = interfaceC2705th2.d();
        c0231j1D.O0();
        return !c0231j1D.m.g();
    }

    public static UY a(C0231j1 c0231j1) {
        SY sy = UY.d;
        return new TY(2);
    }

    public final void b(InterfaceC2705th interfaceC2705th, InterfaceC2705th interfaceC2705th2) {
        ((UY) this.h.computeIfAbsent(interfaceC2705th2.d(), new Function() { // from class: uyh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2278oh.a((C0231j1) obj);
            }
        })).add(interfaceC2705th.a());
    }
}
