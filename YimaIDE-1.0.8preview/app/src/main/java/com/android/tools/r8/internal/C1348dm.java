package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1348dm;
import com.android.tools.r8.internal.IM;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1348dm<T> {
    public static final /* synthetic */ boolean b = true;
    public final HashMap a = new HashMap();

    public T a(T t, T t2) {
        boolean z = b;
        if (!z && t == null) {
            x1f.a();
            return null;
        }
        if (!z && t2 == null) {
            x1f.a();
            return null;
        }
        if (t != t2) {
            if (!z && this.a.get(t) != t) {
                x1f.a();
                return null;
            }
            if (!z && this.a.get(t2) != t2) {
                x1f.a();
                return null;
            }
            this.a.put(t2, t);
            if (!z && b(t) != t) {
                x1f.a();
                return null;
            }
            if (!z && b(t2) != t) {
                x1f.a();
                return null;
            }
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T b(T t) {
        T t2 = (T) this.a.get(t);
        if (t2 == null) {
            return null;
        }
        Object obj = this.a.get(t2);
        if (t2 == obj) {
            return t2;
        }
        T t3 = (T) b(obj);
        this.a.put(t, t3);
        return t3;
    }

    public boolean c(T t) {
        T tB = b(t);
        return tB == null || tB.equals(t);
    }

    public T d(T t) {
        boolean z = b;
        if (!z && this.a.containsKey(t)) {
            x1f.a();
            return null;
        }
        this.a.put(t, t);
        if (z || b(t) == t) {
            return t;
        }
        x1f.a();
        return null;
    }

    public final String toString() {
        Map<T, Set<T>> mapA = a();
        final StringBuilder sb = new StringBuilder();
        sb.append("Number of sets: ");
        sb.append(mapA.keySet().size());
        sb.append(System.lineSeparator());
        mapA.forEach(new BiConsumer() { // from class: cng
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C1348dm.a(sb, obj, (Set) obj2);
            }
        });
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Set<T> a(T t) {
        Object objB = b(t);
        if (objB == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (Object obj : this.a.keySet()) {
            if (b(obj).equals(objB)) {
                hashSet.add(obj);
            }
        }
        return hashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Map<T, Set<T>> a() {
        final HashMap map = new HashMap();
        BiConsumer biConsumer = new BiConsumer() { // from class: ang
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Set) map.computeIfAbsent(obj, IM.a(new jc4()))).add(obj2);
            }
        };
        for (Object obj : this.a.keySet()) {
            biConsumer.accept(b(obj), obj);
        }
        return map;
    }

    public static /* synthetic */ void a(final StringBuilder sb, Object obj, Set set) {
        sb.append("Representative: ");
        sb.append(obj);
        sb.append(System.lineSeparator());
        set.forEach(new Consumer() { // from class: bng
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                C1348dm.a(sb, obj2);
            }
        });
    }

    public static /* synthetic */ void a(StringBuilder sb, Object obj) {
        sb.append("    ");
        sb.append(obj);
        sb.append(System.lineSeparator());
    }
}
