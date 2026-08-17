package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ub0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2780ub0 {
    public static boolean a(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof InterfaceC1231cQ) {
            collection = ((InterfaceC1231cQ) collection).F();
        }
        boolean zRemove = false;
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                zRemove |= set.remove(it.next());
            }
            return zRemove;
        }
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            if (collection.contains(it2.next())) {
                it2.remove();
                zRemove = true;
            }
        }
        return zRemove;
    }

    public static HashSet b() {
        return new HashSet();
    }

    public static Set c() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static Set a() {
        return WW.a();
    }

    public static C1924kb0 a(Set set, Set set2) {
        if (set == null) {
            x0e.a("set1");
            return null;
        }
        if (set2 != null) {
            return new C1924kb0(set, set2);
        }
        x0e.a("set2");
        return null;
    }

    public static HashSet a(Iterable iterable) {
        if (iterable instanceof Collection) {
            return new HashSet((Collection) iterable);
        }
        Iterator it = iterable.iterator();
        HashSet hashSet = new HashSet();
        NC.a(hashSet, it);
        return hashSet;
    }

    public static int a(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }
}
