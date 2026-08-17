package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ta, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2691ta {
    public final InterfaceC2045lz a;
    public final List b;

    public C2691ta(C2986wz c2986wz, ArrayList arrayList) {
        this.a = c2986wz;
        this.b = arrayList;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("regs[");
        BU it = this.a.c().iterator();
        while (it.hasNext()) {
            InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it.next();
            sb.append(interfaceC1959kz.a());
            sb.append(":");
            sb.append(interfaceC1959kz.getValue());
            sb.append(", ");
        }
        sb.append("], stack[");
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            sb.append((Mj0) it2.next());
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
