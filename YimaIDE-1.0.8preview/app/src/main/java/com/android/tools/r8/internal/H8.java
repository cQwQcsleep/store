package com.android.tools.r8.internal;

import com.android.tools.r8.internal.H8;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class H8 {
    public int a = -1;
    public int b = -1;
    public int c = -1;
    public final LinkedHashSet d = new LinkedHashSet();
    public final ArrayList e = new ArrayList();
    public final LinkedHashMap f = new LinkedHashMap();

    public final String a() {
        return this.a + "->" + this.c;
    }

    public final String toString() {
        final ArrayList arrayList = new ArrayList();
        this.d.forEach(new Consumer() { // from class: n36
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add(((H8) obj).a());
            }
        });
        this.e.forEach(new Consumer() { // from class: o36
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add("*" + ((H8) obj).a());
            }
        });
        return "CfBlock(range=" + a() + ", predecessors=" + Wf0.a(", ", (Iterable) arrayList) + ")";
    }
}
