package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ph0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2365ph0 {
    public final /* synthetic */ Iterator a;

    public C2365ph0(Iterator it) {
        this.a = it;
    }

    public final ArrayList a(int i) {
        ArrayList arrayList = new ArrayList(i);
        while (i > 0) {
            if (!this.a.hasNext()) {
                z0e.a();
                return null;
            }
            arrayList.add(this.a.next());
            i--;
        }
        return arrayList;
    }

    public static C2365ph0 a(Iterator it) {
        return new C2365ph0(it);
    }

    public final Object a(InterfaceC2706th0 interfaceC2706th0) {
        if (this.a.hasNext()) {
            return this.a.next();
        }
        return interfaceC2706th0.get();
    }

    public final boolean a() {
        return this.a.hasNext();
    }
}
