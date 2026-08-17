package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Y00;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P10 {
    public final com.android.tools.r8.graph.B1 a;

    public P10(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
    }

    public static ArrayList a(C1366e10 c1366e10) {
        final ArrayList arrayList = new ArrayList();
        LinkedList linkedList = c1366e10.e;
        if ((linkedList != null ? linkedList.size() : 0) > 0) {
            Iterator it = c1366e10.e.iterator();
            while (it.hasNext()) {
                ((C2307p10) it.next()).a(new Consumer() { // from class: hua
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        arrayList.add((Y00) obj);
                    }
                });
            }
        }
        LinkedList linkedList2 = c1366e10.d;
        if ((linkedList2 != null ? linkedList2.size() : 0) > 0) {
            arrayList.addAll(c1366e10.d);
        }
        if (c1366e10.a()) {
            Iterator it2 = c1366e10.c.iterator();
            while (it2.hasNext()) {
                arrayList.addAll(((X00) it2.next()).d);
            }
        }
        return arrayList;
    }
}
