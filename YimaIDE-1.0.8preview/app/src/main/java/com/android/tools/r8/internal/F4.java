package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class F4 extends NP {
    @Override // com.android.tools.r8.internal.NP
    public final Collection a(C1868jt c1868jt) {
        LinkedList linkedList = new LinkedList();
        for (com.android.tools.r8.graph.D2 d2 : c1868jt.b) {
            if (a(d2)) {
                linkedList.add(new C1868jt(d2));
            }
        }
        if (linkedList.size() <= 1) {
            int i = AbstractC0551Hu.c;
            return new Bc0(c1868jt);
        }
        if (!BC.a && AbstractC3179zC.b(linkedList)) {
            x1f.a();
            return null;
        }
        Iterator it = linkedList.iterator();
        for (com.android.tools.r8.graph.D2 d3 : c1868jt.b) {
            if (!a(d3)) {
                if (!it.hasNext()) {
                    it = linkedList.iterator();
                }
                ((C1868jt) it.next()).b.add(d3);
            }
        }
        return AbstractC1238cX.a(linkedList);
    }

    public abstract boolean a(com.android.tools.r8.graph.D2 d2);
}
