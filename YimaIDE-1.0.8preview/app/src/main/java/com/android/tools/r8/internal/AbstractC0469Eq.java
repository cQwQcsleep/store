package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0469Eq;
import defpackage.jc4;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Eq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0469Eq {
    public static final /* synthetic */ boolean d = true;
    public final Set a = AbstractC2780ub0.c();
    public final IdentityHashMap b = new IdentityHashMap();
    public boolean c = true;

    public abstract void a(Cl0 cl0);

    public final void a(AbstractC0469Eq abstractC0469Eq, r rVar) {
        ((Set) abstractC0469Eq.b.computeIfAbsent(this, IM.a(new jc4()))).add(rVar);
        this.a.add(abstractC0469Eq);
    }

    public C0495Fq b() {
        return null;
    }

    public abstract Cl0 c();

    public abstract com.android.tools.r8.graph.I2 d();

    public final boolean e() {
        Cl0 cl0C = c();
        cl0C.getClass();
        return cl0C instanceof Bk0;
    }

    public C0443Dq a() {
        return null;
    }

    public final void a(final BiPredicate biPredicate) {
        this.b.entrySet().removeIf(new Predicate() { // from class: kc4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                return biPredicate.test((AbstractC0469Eq) entry.getKey(), (Set) entry.getValue());
            }
        });
    }

    public final void a(Deque deque) {
        if (this.c || this.b.isEmpty()) {
            return;
        }
        deque.add(this);
        this.c = true;
    }
}
