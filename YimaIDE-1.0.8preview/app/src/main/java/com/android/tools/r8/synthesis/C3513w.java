package com.android.tools.r8.synthesis;

import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.EX;
import com.android.tools.r8.shaking.R1;
import com.android.tools.r8.synthesis.AbstractC3509s;
import com.android.tools.r8.synthesis.C3513w;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3513w {
    public static final /* synthetic */ boolean d = true;
    public final List a;
    public final AbstractC3509s b;
    public final AbstractC2173nV c;

    public C3513w(AbstractC3509s abstractC3509s, List list, AbstractC2173nV abstractC2173nV) {
        boolean z = d;
        if (!z && abstractC3509s == null) {
            x1f.a();
            throw null;
        }
        if (!z && list == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC2173nV == null) {
            x1f.a();
            throw null;
        }
        this.a = list;
        this.b = abstractC3509s;
        this.c = abstractC2173nV;
    }

    public final boolean a(final R1 r1) {
        return r1.a.contains(this.b.b.c) || AbstractC3179zC.b(this.a, new EX() { // from class: vmi
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return C3513w.a(r1, (AbstractC3509s) obj);
            }
        });
    }

    public final void b(Consumer consumer) {
        this.a.forEach(consumer);
    }

    public final String toString() {
        return "EquivalenceGroup{ size = " + (this.a.size() + 1) + ", repr = " + this.b + " }";
    }

    public final void a(Consumer consumer) {
        consumer.accept(this.b);
        this.a.forEach(consumer);
    }

    public final AbstractC3509s a() {
        return this.b;
    }

    public static boolean a(R1 r1, AbstractC3509s abstractC3509s) {
        return r1.a.contains(abstractC3509s.b.c);
    }
}
