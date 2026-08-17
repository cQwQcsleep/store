package com.android.tools.r8.internal;

import defpackage.v8h;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ib0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1755ib0 {
    public static <T, S> Set<T> a(Collection<S> collection, Function<S, T> function) {
        Set<T> setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(collection.size()));
        Iterator<S> it = collection.iterator();
        while (it.hasNext()) {
            setNewSetFromMap.add(function.apply(it.next()));
        }
        return setNewSetFromMap;
    }

    public static LinkedHashSet b(Collection collection, Function function) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(function.apply(it.next()));
        }
        return linkedHashSet;
    }

    public static AbstractC2554rv c(Object... objArr) {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        for (Object obj : objArr) {
            if (obj != null) {
                c1870jv.a(obj);
            }
        }
        return c1870jv.a();
    }

    public static Set b(int i) {
        return Collections.newSetFromMap(new IdentityHashMap(i));
    }

    public static AbstractC2554rv b(InterfaceC0806Rq interfaceC0806Rq) {
        int i = AbstractC2554rv.c;
        final C1870jv c1870jv = new C1870jv();
        interfaceC0806Rq.forEach(new Consumer() { // from class: w8h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c1870jv.a(obj);
            }
        });
        return c1870jv.a();
    }

    public static Set a(int i) {
        return ConcurrentHashMap.newKeySet(i);
    }

    public static <T> HashSet<T> a(T... tArr) {
        HashSet<T> hashSet = new HashSet<>(tArr.length);
        Collections.addAll(hashSet, tArr);
        return hashSet;
    }

    public static <T> Set<T> b(T... tArr) {
        Set<T> setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(tArr.length));
        Collections.addAll(setNewSetFromMap, tArr);
        return setNewSetFromMap;
    }

    public static Set a(InterfaceC0806Rq interfaceC0806Rq) {
        Set setC = AbstractC2780ub0.c();
        Objects.requireNonNull(setC);
        interfaceC0806Rq.forEach(new v8h(setC));
        return setC;
    }

    public static <T> Set<T> a(Iterable<? extends T> iterable) {
        Set<T> setC = AbstractC2780ub0.c();
        Objects.requireNonNull(setC);
        iterable.forEach(new v8h(setC));
        return setC;
    }

    public static <T> Set<T> a(Iterable<T> iterable, Iterable<T> iterable2) {
        Set<T> setC = AbstractC2780ub0.c();
        Objects.requireNonNull(setC);
        iterable.forEach(new v8h(setC));
        iterable2.forEach(new v8h(setC));
        return setC;
    }

    public static Set a(Iterable iterable, Set set, Iterable iterable2) {
        Set setC = AbstractC2780ub0.c();
        Objects.requireNonNull(setC);
        iterable.forEach(new v8h(setC));
        set.forEach(new v8h(setC));
        iterable2.forEach(new v8h(setC));
        return setC;
    }

    public static <T> Set<T> a() {
        return ConcurrentHashMap.newKeySet();
    }

    public static Set a(Set set) {
        return C2752uB.b() ? Collections.unmodifiableSet(set) : set;
    }
}
