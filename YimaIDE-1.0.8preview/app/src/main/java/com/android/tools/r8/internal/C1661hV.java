package com.android.tools.r8.internal;

import com.android.tools.r8.graph.D2;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1661hV {
    public final C1868jt a;
    public final Set b;
    public final Set c;
    public final Set d;

    public C1661hV(Set set, com.android.tools.r8.graph.D2 d2, Set set2) {
        this.a = new C1868jt(d2);
        Set setC = AbstractC2780ub0.c();
        setC.add(d2);
        this.b = setC;
        this.c = set;
        this.d = set2;
    }

    public final void a(Set set, com.android.tools.r8.graph.D2 d2, Set set2) {
        this.a.b.add(d2);
        this.b.add(d2);
        AbstractC3179zC.a((Iterable) AbstractC3179zC.c(set, new EX() { // from class: a3h
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.a((D2) obj);
            }
        }), (Collection) this.c);
        this.c.remove(d2);
        AbstractC3179zC.a((Iterable) AbstractC3179zC.c(set2, new EX() { // from class: b3h
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.b((D2) obj);
            }
        }), (Collection) this.d);
        this.d.remove(d2);
    }

    public final /* synthetic */ boolean b(com.android.tools.r8.graph.D2 d2) {
        return !this.b.contains(d2);
    }

    public final /* synthetic */ boolean a(com.android.tools.r8.graph.D2 d2) {
        return !this.b.contains(d2);
    }
}
