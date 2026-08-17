package com.android.tools.r8.internal;

import com.android.tools.r8.graph.InterfaceC0332x5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2584sE {
    public static final /* synthetic */ boolean d = true;
    public final List a;
    public final List b;
    public boolean c = false;

    public C2584sE(C2840vE c2840vE) {
        this.a = Arrays.asList(new com.android.tools.r8.graph.D2[c2840vE.c.size()]);
        this.b = Arrays.asList(new com.android.tools.r8.graph.A5[c2840vE.d.size()]);
    }

    public final ArrayList a(C0919Vz c0919Vz) {
        if (!d && c0919Vz.isEmpty()) {
            x1f.a();
            return null;
        }
        ArrayList arrayList = new ArrayList(c0919Vz.c);
        for (int i = 0; i < c0919Vz.c; i++) {
            InterfaceC0332x5 interfaceC0332x5A = a(c0919Vz.i(i));
            if (!d && interfaceC0332x5A == null && !this.c) {
                x1f.a();
                return null;
            }
            if (interfaceC0332x5A != null) {
                arrayList.add(interfaceC0332x5A);
            }
        }
        return arrayList;
    }

    public final InterfaceC0332x5 a(int i) {
        if (i >= 0) {
            List list = this.a;
            if (C2840vE.i || i >= 0) {
                return (InterfaceC0332x5) list.get(i);
            }
            x1f.a();
            return null;
        }
        List list2 = this.b;
        boolean z = C2840vE.i;
        if (!z && i >= 0) {
            x1f.a();
            return null;
        }
        if (!z && i >= 0) {
            x1f.a();
            return null;
        }
        return (InterfaceC0332x5) list2.get(-(i + 1));
    }
}
