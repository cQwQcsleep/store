package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C2543rl0;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Se0 extends AbstractC3159z1 {
    public static final Se0 g = new Se0();
    public static final /* synthetic */ boolean h = true;
    public final Map b;
    public final Map c;
    public final Set d;
    public final Set e;
    public final Set f;

    public Se0(Map map, Map map2, Set set, Set set2, Set set3) {
        if (!h && map.isEmpty() && set.isEmpty() && map2.isEmpty() && set2.isEmpty()) {
            x01.a("Creating an instance of BOTTOM");
            throw null;
        }
        this.b = map;
        this.c = map2;
        this.d = set;
        this.e = set2;
        this.f = set3;
    }

    public final Se0 a(Se0 se0) {
        Se0 se1 = g;
        if (this == se1) {
            return se0;
        }
        se0.getClass();
        if (se0 == se1) {
            return this;
        }
        final Re0 re0 = new Re0(this);
        Set set = se0.d;
        if (set != null) {
            if (re0.c == re0.f.d) {
                re0.c = new HashSet(re0.c);
            }
            re0.c.addAll(set);
        }
        Set set2 = se0.e;
        re0.d();
        re0.d.addAll(set2);
        se0.b.forEach(new BiConsumer() { // from class: vyc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                re0.a((C2543rl0) obj, (Set) obj2);
            }
        });
        se0.c.forEach(new BiConsumer() { // from class: wyc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                re0.b((C2543rl0) obj, (Set) obj2);
            }
        });
        return re0.a();
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final /* bridge */ /* synthetic */ AbstractC3159z1 b(C0333y c0333y, AbstractC3159z1 abstractC3159z1) {
        return a((Se0) abstractC3159z1);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004d  */
    /* JADX WARN: Code duplicated, block: B:26:0x0058  */
    /* JADX WARN: Code duplicated, block: B:29:0x0066  */
    /* JADX WARN: Code duplicated, block: B:32:0x007f  */
    /* JADX WARN: Code duplicated, block: B:34:0x0089  */
    /* JADX WARN: Code duplicated, block: B:42:0x0094 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:? A[LOOP:1: B:27:0x0060->B:43:?, LOOP_END, SYNTHETIC] */
    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final boolean equals(Object obj) {
        Map map;
        Map map2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Se0)) {
            return false;
        }
        Se0 se0 = (Se0) obj;
        Map map3 = this.b;
        Map map4 = se0.b;
        if (map3 == map4) {
            map = this.c;
            map2 = se0.c;
            if (map != map2) {
                if (map.size() == map2.size()) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                        }
                    }
                    if (!this.d.equals(se0.d)) {
                    }
                }
            } else if (!this.d.equals(se0.d)) {
            }
        } else if (map3.size() == map4.size()) {
            for (Map.Entry entry2 : map3.entrySet()) {
                if (!entry2.getValue().equals(map4.get(entry2.getKey()))) {
                }
            }
            map = this.c;
            map2 = se0.c;
            if (map != map2) {
                if (map.size() == map2.size()) {
                    while (r1.hasNext()) {
                        if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                        }
                    }
                    if (!this.d.equals(se0.d) && this.e.equals(se0.e)) {
                        return true;
                    }
                }
            } else if (!this.d.equals(se0.d)) {
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.b, this.c, this.d, this.e);
    }

    public Se0() {
        Map map = Collections.EMPTY_MAP;
        this.b = map;
        this.c = map;
        Set set = Collections.EMPTY_SET;
        this.d = set;
        this.e = set;
        this.f = set;
    }

    public final boolean a(C2543rl0 c2543rl0) {
        return this.e.contains(c2543rl0);
    }

    @Override // com.android.tools.r8.internal.Vh0
    public final AbstractC3159z1 a() {
        return this;
    }
}
