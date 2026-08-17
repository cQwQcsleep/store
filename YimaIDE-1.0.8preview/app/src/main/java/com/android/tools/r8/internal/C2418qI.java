package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2418qI extends AbstractC1223cI implements InterfaceC1139bI {
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList(0);
    public final ArrayList d;

    public C2418qI() {
        InterfaceC1654hO.a.getClass();
        List list = (List) C1568gO.b.a();
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FD) ((InterfaceC1654hO) it.next())).d());
        }
        this.d = arrayList;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1139bI
    public final List a() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1139bI
    public final List b() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1139bI
    public final List c() {
        return this.a;
    }
}
