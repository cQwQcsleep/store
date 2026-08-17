package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import java.util.IdentityHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UA {
    public IdentityHashMap a;
    public IdentityHashMap b;
    public boolean c;
    public boolean d;

    public final void a(C0231j1 c0231j1, C0231j1 c0231j2) {
        if (this.a == null) {
            this.a = new IdentityHashMap();
        }
        this.a.put(c0231j1, c0231j2);
    }

    public final void a(C0322w2 c0322w2, C0322w2 c0322w3) {
        if (this.b == null) {
            this.b = new IdentityHashMap();
        }
        this.b.put(c0322w2, c0322w3);
    }

    public final void a() {
        this.c = true;
    }
}
