package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.C2799ul0;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2885vl0 {
    public static final /* synthetic */ boolean b = true;
    public final IdentityHashMap a = new IdentityHashMap();

    public final void a() {
        Collection collectionValues = this.a.values();
        Sm0 sm0 = new Sm0(2);
        sm0.b((Iterable) collectionValues);
        while (sm0.b()) {
            C2799ul0 c2799ul0 = (C2799ul0) sm0.d();
            C3119ya0 c3119ya0 = new C3119ya0(new Function() { // from class: lmi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((C2799ul0) obj).a();
                }
            });
            if (!C3119ya0.h && c3119ya0.a != 0) {
                x1f.a();
                return;
            }
            c3119ya0.a(c2799ul0);
            for (Set set : c3119ya0.f) {
                Iterator it = set.iterator();
                if (!b && !it.hasNext()) {
                    x1f.a();
                    return;
                }
                C2799ul0 c2799ul1 = (C2799ul0) it.next();
                while (it.hasNext()) {
                    C2799ul0 c2799ul2 = (C2799ul0) it.next();
                    for (C2799ul0 c2799ul3 : c2799ul2.c) {
                        c2799ul3.d.remove(c2799ul2);
                        c2799ul3.d.add(c2799ul1);
                        c2799ul1.c.add(c2799ul3);
                    }
                    c2799ul2.c.clear();
                    for (C2799ul0 c2799ul4 : c2799ul2.d) {
                        c2799ul4.c.remove(c2799ul2);
                        c2799ul4.c.add(c2799ul1);
                        c2799ul1.d.add(c2799ul4);
                    }
                    c2799ul2.d.clear();
                    c2799ul1.b.addAll(c2799ul2.b);
                    this.a.put(c2799ul2.a, c2799ul1);
                }
                sm0.a(set);
            }
        }
    }

    public final C2799ul0 a(C2543rl0 c2543rl0) {
        return (C2799ul0) this.a.computeIfAbsent(c2543rl0, new Function() { // from class: kmi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C2799ul0((C2543rl0) obj);
            }
        });
    }
}
