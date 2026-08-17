package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2780ub0;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class G5 {
    public static final /* synthetic */ boolean h = true;
    public AbstractC0327x0 a;
    public final Set b = c();
    public final Map c = b();
    public final Set d = c();
    public Set e = c();
    public final Set f = c();
    public final Set g = c();

    public final I5 a() {
        if (!this.c.isEmpty()) {
            IdentityHashMap identityHashMap = new IdentityHashMap();
            for (Map.Entry entry : this.c.entrySet()) {
                C0322w2 c0322w2 = (C0322w2) entry.getKey();
                if (!identityHashMap.containsKey(c0322w2)) {
                    B5 b5 = (B5) entry.getValue();
                    B5 b6 = (B5) this.c.get(b5.getReference());
                    if (b6 == null) {
                        continue;
                    } else {
                        C0322w2[] c0322w2Arr = {c0322w2, b5.getReference()};
                        ArrayDeque arrayDeque = new ArrayDeque();
                        Collections.addAll(arrayDeque, c0322w2Arr);
                        while (true) {
                            C0322w2 reference = b6.getReference();
                            B5 b7 = (B5) this.c.get(reference);
                            if (b7 == null) {
                                break;
                            }
                            arrayDeque.addLast(reference);
                            b6 = b7;
                        }
                        if (!h && this.g.contains(b6.getReference())) {
                            x1f.a();
                            return null;
                        }
                        Iterator it = arrayDeque.iterator();
                        while (it.hasNext()) {
                            identityHashMap.put((C0322w2) it.next(), b6);
                        }
                    }
                }
            }
            this.c.putAll(identityHashMap);
        }
        return new I5(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
    }

    public Map b() {
        return new IdentityHashMap();
    }

    public Set c() {
        return AbstractC2780ub0.c();
    }

    public final G5 a(Set set) {
        this.d.addAll(set);
        this.e.addAll(set);
        return this;
    }
}
