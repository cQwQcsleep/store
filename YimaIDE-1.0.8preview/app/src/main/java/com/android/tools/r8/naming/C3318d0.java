package com.android.tools.r8.naming;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.PO;
import com.android.tools.r8.naming.C3318d0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.naming.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3318d0 {
    public static final /* synthetic */ boolean c = true;
    public HashMap a = null;
    public HashSet b = null;

    public final void a(com.android.tools.r8.graph.H0 h0, H2 h2) {
        if (this.b == null) {
            if (!c && this.a != null) {
                x1f.a();
                return;
            } else {
                this.a = new HashMap();
                this.b = new HashSet();
            }
        }
        ((Set) this.a.computeIfAbsent(new C2119mo(PO.a, h0.getReference()), new Function() { // from class: jlg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3318d0.a((C2119mo) obj);
            }
        })).add(h2);
        this.b.add(h2);
    }

    public static /* synthetic */ Set a(C2119mo c2119mo) {
        return new HashSet();
    }
}
