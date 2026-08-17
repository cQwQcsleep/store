package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AR;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AR extends AbstractC1096am0 {
    public static final /* synthetic */ boolean b = true;

    @Override // com.android.tools.r8.internal.AbstractC1096am0
    public final boolean a(C1434em0 c1434em0, Object obj) {
        Map map = (Map) obj;
        if (!map.containsKey(c1434em0.c)) {
            return true;
        }
        C1434em0 c1434em1 = (C1434em0) map.remove(c1434em0.b);
        if (!b && c1434em1 != c1434em0) {
            x1f.a();
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1096am0
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final IdentityHashMap b(final Collection collection) {
        return IM.a(new Consumer() { // from class: tj
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AR.a(collection, (IdentityHashMap) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoNestedMergingPolicy";
    }

    public static void a(Collection collection, IdentityHashMap identityHashMap) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            C1434em0 c1434em0 = (C1434em0) it.next();
            identityHashMap.put(c1434em0.b, c1434em0);
        }
    }
}
