package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0259n1;
import java.util.Collection;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.n1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0259n1 {
    public static void a(final com.android.tools.r8.dex.X x, AbstractC0259n1[] abstractC0259n1Arr) {
        Consumer consumer = new Consumer() { // from class: woh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC0259n1) obj).a(x);
            }
        };
        if (abstractC0259n1Arr == null) {
            return;
        }
        for (AbstractC0259n1 abstractC0259n1 : abstractC0259n1Arr) {
            if (abstractC0259n1 != null) {
                consumer.accept(abstractC0259n1);
            }
        }
    }

    public abstract void a(com.android.tools.r8.dex.X x);

    public String l0() {
        return toString();
    }

    public String m0() {
        return toString();
    }

    public static void a(final com.android.tools.r8.dex.X x, Collection collection) {
        collection.forEach(new Consumer() { // from class: soh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbstractC0259n1) obj).a(x);
            }
        });
    }
}
