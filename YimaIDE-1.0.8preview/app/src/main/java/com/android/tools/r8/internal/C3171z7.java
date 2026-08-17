package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.z7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3171z7 implements Iterable {
    public final ArrayList b;
    public C3086y7 c;

    public C3171z7(com.android.tools.r8.graph.D2 d2, C3086y7 c3086y7) {
        boolean z = C2847vL.a;
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(d2);
        this.b = arrayList;
        this.c = c3086y7;
    }

    public final boolean a() {
        return this.b.size() == 1;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.b.iterator();
    }

    public C3171z7() {
        this.b = new ArrayList();
        this.c = null;
    }
}
