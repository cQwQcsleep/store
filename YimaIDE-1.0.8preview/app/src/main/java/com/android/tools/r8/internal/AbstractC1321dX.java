package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1321dX;
import com.android.tools.r8.internal.MN;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1321dX {
    public static final /* synthetic */ boolean a = true;

    public abstract LinkedList a(AbstractC1238cX abstractC1238cX, LinkedList linkedList, ExecutorService executorService);

    public final LinkedList a(AbstractList abstractList, Collection collection, ExecutorService executorService, Ch0 ch0) {
        LinkedList linkedList = abstractList instanceof LinkedList ? (LinkedList) abstractList : new LinkedList(abstractList);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            AbstractC1238cX abstractC1238cX = (AbstractC1238cX) it.next();
            if (!abstractC1238cX.l()) {
                ch0.a(abstractC1238cX.f());
                linkedList = a(abstractC1238cX, linkedList, executorService);
                ch0.b();
                abstractC1238cX.e();
                if (linkedList.isEmpty()) {
                    return linkedList;
                }
                if (!a && !linkedList.stream().allMatch(new Predicate() { // from class: rmg
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return AbstractC1321dX.a((MN) obj);
                    }
                })) {
                    x1f.a();
                    return null;
                }
            }
        }
        return linkedList;
    }

    public static /* synthetic */ boolean a(MN mn) {
        return mn.size() >= 2;
    }
}
