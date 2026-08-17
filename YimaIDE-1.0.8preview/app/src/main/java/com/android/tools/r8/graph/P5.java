package com.android.tools.r8.graph;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.P5;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P5 extends E5 {
    public P5(String str) {
        super(str, new Supplier() { // from class: rua
            @Override // java.util.function.Supplier
            public final Object get() {
                return P5.b();
            }
        });
    }

    public static /* synthetic */ Set b() {
        return new TreeSet(new Comparator() { // from class: qua
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((D2) obj).getType().compareTo(((D2) obj2).getType());
            }
        });
    }
}
