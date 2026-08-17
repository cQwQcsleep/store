package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.AbstractC1123b6;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1123b6 implements V5 {
    public final Map b;
    public final Map c;

    public AbstractC1123b6() {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        IdentityHashMap identityHashMap2 = new IdentityHashMap();
        this.b = identityHashMap;
        this.c = identityHashMap2;
    }

    public static /* synthetic */ Set f(Object obj) {
        return new LinkedHashSet();
    }

    public final void a(Object obj, Object obj2) {
        C1207c6 c1207c6 = (C1207c6) this;
        Object objRemove = c1207c6.c.remove(obj2);
        if (objRemove != null) {
            Set set = (Set) c1207c6.b.get(objRemove);
            set.remove(obj2);
            if (set.isEmpty()) {
                c1207c6.b.remove(objRemove);
            }
        }
        if (c1207c6.e(objRemove).size() <= 1 || c1207c6.c(objRemove) == obj2) {
            c1207c6.d.remove(objRemove);
        }
        ((Set) this.b.computeIfAbsent(obj, new Function() { // from class: ghg
            @Override // java.util.function.Function
            public final Object apply(Object obj3) {
                return AbstractC1123b6.f(obj3);
            }
        })).add(obj2);
        this.c.put(obj2, obj);
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean containsKey(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean containsValue(Object obj) {
        return this.c.containsKey(obj);
    }

    public final Set e(Object obj) {
        return (Set) this.b.getOrDefault(obj, Collections.EMPTY_SET);
    }

    @Override // com.android.tools.r8.internal.V5
    public final void forEach(final BiConsumer biConsumer) {
        this.b.forEach(new BiConsumer() { // from class: fhg
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Set) obj2).forEach(new Consumer() { // from class: ehg
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj3) {
                        biConsumer.accept(obj, obj3);
                    }
                });
            }
        });
    }

    @Override // com.android.tools.r8.internal.V5
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    public final Object a(C0322w2 c0322w2, C0322w2 c0322w3) {
        return this.c.getOrDefault(c0322w2, c0322w3);
    }
}
