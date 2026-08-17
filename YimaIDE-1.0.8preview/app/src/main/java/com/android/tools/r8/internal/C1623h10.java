package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0343z2;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1623h10 {
    public static final /* synthetic */ boolean c = true;
    public final ArrayList a = new ArrayList(2);
    public final C1291d6 b = new C1291d6();

    public final com.android.tools.r8.graph.B2 a(C0343z2 c0343z2, com.android.tools.r8.graph.B1 b1, C1538g10 c1538g10, boolean z) {
        if (c1538g10.b(c0343z2)) {
            if (c || a(c0343z2) == null || c0343z2.equals(a(c0343z2))) {
                return c0343z2;
            }
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.B2 b2A = a(c0343z2);
        if (b2A != null) {
            return b2A;
        }
        com.android.tools.r8.graph.A2 a2A = c0343z2.a(c1538g10.a(c0343z2), b1);
        Sm0 sm0 = new Sm0(2);
        sm0.b(this);
        while (sm0.b()) {
            C1623h10 c1623h10 = (C1623h10) sm0.d();
            if (c1623h10.b.b.containsValue(a2A)) {
                String string = c0343z2.a().toString();
                int i = 1;
                loop1: while (true) {
                    a2A = a2A.a(b1.c(string + "$" + i));
                    i++;
                    Sm0 sm1 = new Sm0(2);
                    sm1.b(this);
                    while (true) {
                        if (!sm1.b()) {
                            break;
                        }
                        C1623h10 c1623h11 = (C1623h10) sm1.d();
                        if (!c1623h11.b.b.containsValue(a2A)) {
                            sm1.b((Iterable) c1623h11.a);
                        }
                    }
                }
            } else {
                sm0.b((Iterable) c1623h10.a);
            }
        }
        if (z) {
            this.b.put(c0343z2, a2A);
        }
        return a2A;
    }

    public final com.android.tools.r8.graph.B2 a(C0343z2 c0343z2) {
        Sm0 sm0 = new Sm0(2);
        sm0.b(this);
        while (sm0.b()) {
            C1623h10 c1623h10 = (C1623h10) sm0.d();
            com.android.tools.r8.graph.B2 b2 = (com.android.tools.r8.graph.B2) c1623h10.b.b.get(c0343z2);
            if (b2 != null) {
                return b2;
            }
            sm0.b((Iterable) c1623h10.a);
        }
        return null;
    }
}
