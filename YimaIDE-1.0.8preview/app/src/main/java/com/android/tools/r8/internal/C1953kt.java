package com.android.tools.r8.internal;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.C1868jt;
import java.util.LinkedList;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1953kt {
    public static final /* synthetic */ boolean b = true;
    public final Z5 a = Z5.a();

    public final void a(C1868jt c1868jt, com.android.tools.r8.graph.D2 d2) {
        com.android.tools.r8.graph.I2 type = d2.getType();
        com.android.tools.r8.graph.I2 type2 = c1868jt.d.getType();
        if (b || !this.a.b.containsKey(type)) {
            this.a.a(type, type2);
        } else {
            x1f.a();
        }
    }

    public final void a(final C1868jt c1868jt) {
        c1868jt.a(new Consumer() { // from class: gih
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c1868jt, (D2) obj);
            }
        });
    }

    public final C1953kt a(LinkedList linkedList) {
        linkedList.forEach(new Consumer() { // from class: fih
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C1868jt) obj);
            }
        });
        return this;
    }
}
