package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3175z9;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O {
    public static final /* synthetic */ boolean d = true;
    public final G a;
    public final G b;
    public K c = null;

    public O(G g, G g2) {
        this.a = g;
        this.b = g2;
    }

    public static void a(AbstractC3175z9 abstractC3175z9, AbstractC3175z9 abstractC3175z10) {
        boolean z = d;
        if (!z && abstractC3175z9.getClass() != abstractC3175z10.getClass()) {
            x1f.a();
            return;
        }
        if (!z && abstractC3175z9.z() != abstractC3175z10.z()) {
            x1f.a();
        } else {
            if (z || abstractC3175z9.toString().equals(abstractC3175z10.toString())) {
                return;
            }
            x1f.a();
        }
    }

    public final com.android.tools.r8.utils.structural.u a() {
        if (this.c == null) {
            this.c = new K(this);
        }
        return this.c;
    }
}
