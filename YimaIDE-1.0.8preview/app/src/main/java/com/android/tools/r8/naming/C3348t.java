package com.android.tools.r8.naming;

import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C2986wz;
import com.android.tools.r8.internal.InterfaceC2045lz;
import defpackage.vef;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.naming.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3348t {
    public final InterfaceC2045lz a;

    public C3348t(C2986wz c2986wz) {
        this.a = c2986wz;
    }

    public static C3348t a(List list) {
        C2986wz c2986wz = new C2986wz(16);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C3331k.b bVar = (C3331k.b) it.next();
            N0 n0F = bVar.f();
            for (int i = n0F.a; i <= n0F.b; i++) {
                ((List) c2986wz.computeIfAbsent(Integer.valueOf(i), C0470Er.a(new vef()))).add(Integer.valueOf((i - n0F.a) + bVar.b.a));
            }
        }
        return new C3348t(c2986wz);
    }

    public final void a(int i, Consumer consumer) {
        List list = (List) this.a.get(i);
        if (list != null) {
            list.forEach(consumer);
        }
    }
}
