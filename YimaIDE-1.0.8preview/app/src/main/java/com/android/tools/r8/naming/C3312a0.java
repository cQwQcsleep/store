package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.PO;
import com.android.tools.r8.naming.C3312a0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3312a0 implements L {
    public static final /* synthetic */ boolean g = true;
    public final C3312a0 b;
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public int e;
    public int f;

    public C3312a0(C3312a0 c3312a0) {
        this.b = c3312a0;
        this.f = c3312a0 == null ? 0 : c3312a0.f;
        this.e = c3312a0 == null ? 1 : c3312a0.e;
    }

    @Override // com.android.tools.r8.naming.L
    public final int a() {
        if (!g) {
            int iMax = 0;
            for (C3312a0 c3312a0 = this.b; c3312a0 != null; c3312a0 = c3312a0.b) {
                iMax = Math.max(c3312a0.e, iMax);
            }
            if (!g && iMax > this.e) {
                x1f.a();
                return 0;
            }
        }
        int i = this.e;
        this.e = i + 1;
        return i;
    }

    @Override // com.android.tools.r8.naming.L
    public final int b() {
        return this.f;
    }

    @Override // com.android.tools.r8.naming.L
    public final int c() {
        int i = this.f;
        this.f = i + 1;
        return i;
    }

    public static /* synthetic */ Set a(H2 h2) {
        return new HashSet();
    }

    public final void a(H2 h2, C0322w2 c0322w2) {
        C2119mo c2119mo = new C2119mo(PO.a, c0322w2);
        this.c.put(c2119mo, h2);
        ((Set) this.d.computeIfAbsent(h2, new Function() { // from class: vag
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3312a0.a((H2) obj);
            }
        })).add(c2119mo);
    }
}
