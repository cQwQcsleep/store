package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.C0559Ic;
import defpackage.px5;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ic, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0559Ic {
    public static final /* synthetic */ boolean d = true;
    public final Set a = AbstractC2780ub0.c();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();

    public final void a(final com.android.tools.r8.graph.D2 d2) {
        boolean z = d;
        if (!z && !this.b.stream().allMatch(new Predicate() { // from class: mh6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0559Ic.a(d2, (C0231j1) obj);
            }
        })) {
            x1f.a();
            return;
        }
        if (!z && !this.b.stream().allMatch(new px5())) {
            x1f.a();
            return;
        }
        if (!z && !this.c.stream().allMatch(new Predicate() { // from class: nh6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0559Ic.b(d2, (C0231j1) obj);
            }
        })) {
            x1f.a();
            return;
        }
        if (!z && !this.c.stream().allMatch(new Predicate() { // from class: oh6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0231j1) obj).L0();
            }
        })) {
            x1f.a();
            return;
        }
        ArrayList arrayList = this.b;
        d2.getClass();
        C0231j1[] c0231j1Arr = C0231j1.u;
        d2.a((C0231j1[]) arrayList.toArray(c0231j1Arr));
        d2.l.a((C0231j1[]) this.c.toArray(c0231j1Arr));
    }

    public final void b(C0231j1 c0231j1) {
        this.b.add(c0231j1);
        boolean zAdd = this.a.add(c0231j1.getReference());
        if (d || zAdd) {
            return;
        }
        x1f.a();
    }

    public static /* synthetic */ boolean b(com.android.tools.r8.graph.D2 d2, C0231j1 c0231j1) {
        return c0231j1.E0() == d2.e;
    }

    public final boolean a(C0322w2 c0322w2) {
        return !this.a.contains(c0322w2);
    }

    public static /* synthetic */ boolean a(com.android.tools.r8.graph.D2 d2, C0231j1 c0231j1) {
        return c0231j1.E0() == d2.e;
    }

    public final void a(C0231j1 c0231j1) {
        this.c.add(c0231j1);
        boolean zAdd = this.a.add(c0231j1.getReference());
        if (d || zAdd) {
            return;
        }
        x1f.a();
    }
}
