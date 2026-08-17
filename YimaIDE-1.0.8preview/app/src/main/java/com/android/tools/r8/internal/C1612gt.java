package com.android.tools.r8.internal;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.C1612gt;
import com.android.tools.r8.internal.C1868jt;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1612gt extends AbstractC1321dX {
    public static final /* synthetic */ boolean b = true;

    public static void a(final AbstractC1757ic0 abstractC1757ic0, LinkedList linkedList) {
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            C1868jt c1868jt = (C1868jt) it.next();
            boolean zJ = c1868jt.j();
            int size = c1868jt.b.size();
            c1868jt.removeIf(new Predicate() { // from class: czg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C1612gt.a(abstractC1757ic0, (D2) obj);
                }
            });
            if (!b) {
                int i = AbstractC0551Hu.c;
                abstractC1757ic0.a(zJ, size, new Bc0(c1868jt));
            }
            if (c1868jt.k()) {
                it.remove();
            }
        }
    }

    public static void b(C1868jt c1868jt, C1868jt c1868jt2) {
        c1868jt2.getClass();
        c1868jt.getClass();
    }

    @Override // com.android.tools.r8.internal.AbstractC1321dX
    public final LinkedList a(AbstractC1238cX abstractC1238cX, LinkedList linkedList, ExecutorService executorService) {
        if (abstractC1238cX.j()) {
            a(abstractC1238cX.c(), linkedList);
            return linkedList;
        }
        if (abstractC1238cX.h()) {
            return a(abstractC1238cX.a(), linkedList);
        }
        if (b || abstractC1238cX.i()) {
            return a(abstractC1238cX.b(), linkedList, executorService);
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ boolean a(AbstractC1757ic0 abstractC1757ic0, com.android.tools.r8.graph.D2 d2) {
        return !abstractC1757ic0.a(d2);
    }

    public static LinkedList a(final NP np, LinkedList linkedList) {
        final LinkedList linkedList2 = new LinkedList();
        linkedList.forEach(new Consumer() { // from class: bzg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1612gt.a(np, linkedList2, (C1868jt) obj);
            }
        });
        return linkedList2;
    }

    public static void a(NP np, LinkedList linkedList, final C1868jt c1868jt) {
        boolean zJ = c1868jt.j();
        int size = c1868jt.b.size();
        Collection collectionA = np.a(c1868jt);
        collectionA.forEach(new Consumer() { // from class: yyg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1612gt.a(c1868jt, (C1868jt) obj);
            }
        });
        if (!b) {
            np.a(zJ, size, collectionA);
        }
        linkedList.addAll(collectionA);
    }

    public static void a(C1868jt c1868jt, C1868jt c1868jt2) {
        c1868jt2.getClass();
        c1868jt.getClass();
    }

    public static LinkedList a(final OP op, LinkedList linkedList, ExecutorService executorService) {
        final Object objA = op.a(linkedList, executorService);
        final LinkedList linkedList2 = new LinkedList();
        linkedList.forEach(new Consumer() { // from class: azg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C1612gt.a(op, objA, linkedList2, (C1868jt) obj);
            }
        });
        return linkedList2;
    }

    public static void a(OP op, Object obj, LinkedList linkedList, final C1868jt c1868jt) {
        boolean zJ = c1868jt.j();
        int size = c1868jt.b.size();
        Collection collectionA = op.a(c1868jt, obj);
        collectionA.forEach(new Consumer() { // from class: zyg
            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                C1612gt.b(c1868jt, (C1868jt) obj2);
            }
        });
        if (!b) {
            op.a(zJ, size, collectionA);
        }
        linkedList.addAll(collectionA);
    }
}
