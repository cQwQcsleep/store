package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class s4 {
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();

    public final void a() {
        this.a.sort(new Comparator() { // from class: fai
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((C0210g1) obj).getReference().compareTo(((C0210g1) obj2).getReference());
            }
        });
        this.b.sort(new Comparator() { // from class: gai
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((C0231j1) obj).getReference().compareTo(((C0231j1) obj2).getReference());
            }
        });
    }
}
