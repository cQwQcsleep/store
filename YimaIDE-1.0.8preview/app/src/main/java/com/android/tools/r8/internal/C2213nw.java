package com.android.tools.r8.internal;

import com.android.tools.r8.inspector.Inspector;
import com.android.tools.r8.internal.C2213nw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2213nw implements Inspector {
    public final Collection a;

    public C2213nw(Collection collection) {
        this.a = collection;
    }

    public static List a(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            final Consumer consumer = (Consumer) it.next();
            Objects.requireNonNull(consumer);
            arrayList.add(new Consumer() { // from class: awh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    consumer.accept((C2213nw) obj);
                }
            });
        }
        return arrayList;
    }

    @Override // com.android.tools.r8.inspector.Inspector
    public final void forEachClass(Consumer consumer) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            consumer.accept(new C2439qc((com.android.tools.r8.graph.D2) it.next()));
        }
    }

    public static void a(List list, Collection collection) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C2213nw c2213nw = new C2213nw(collection);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(c2213nw);
        }
    }
}
