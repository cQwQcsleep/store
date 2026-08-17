package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC2780ub0;
import defpackage.k26;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PR extends AbstractC2116mm {
    public static final /* synthetic */ boolean c = true;
    public final AbstractC0551Hu a;
    public final IdentityHashMap b = new IdentityHashMap();

    public PR(com.android.tools.r8.shaking.E2 e2) {
        boolean z = c;
        if (!z && e2 == null) {
            x1f.a();
            throw null;
        }
        if (z || !e2.b()) {
            this.a = e2.a;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final void a() {
        for (com.android.tools.r8.shaking.F2 f2 : this.a) {
            if (!c && f2.d() && ((Set) this.b.getOrDefault(f2, Collections.EMPTY_SET)).size() == 1) {
                k26.a("Unexpected unnecessary wildcard in -dontwarn ", f2.toString(), " (only matches ", ((com.android.tools.r8.graph.I2) ((Set) this.b.get(f2)).iterator().next()).H0(), ")");
                return;
            }
        }
    }

    public final void b(C2752uB c2752uB) {
        for (com.android.tools.r8.shaking.F2 f2 : this.a) {
            if (!c && !this.b.containsKey(f2) && !c2752uB.u1.D0.contains(f2.toString())) {
                throw new AssertionError("Unexpected unused rule -dontwarn " + f2.toString());
            }
        }
    }

    public final void a(com.android.tools.r8.shaking.F2 f2, com.android.tools.r8.graph.I2 i2) {
        if (C2752uB.b()) {
            ((Set) this.b.computeIfAbsent(f2, new Function() { // from class: eva
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return AbstractC2780ub0.c();
                }
            })).add(i2);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2116mm
    public final void a(C2752uB c2752uB) {
        boolean z = c;
        if (!z && !c2752uB.u1.R) {
            a();
        }
        if (z || c2752uB.u1.S) {
            return;
        }
        b(c2752uB);
    }

    @Override // com.android.tools.r8.internal.AbstractC2116mm
    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        for (com.android.tools.r8.shaking.F2 f2 : this.a) {
            if (f2.a(i2)) {
                a(f2, i2);
                return true;
            }
        }
        return false;
    }
}
