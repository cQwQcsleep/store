package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.Fa0;
import com.android.tools.r8.internal.InterfaceC2781uc;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC2781uc {
    static void a(C0333y c0333y, com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.D2 d3, BiConsumer biConsumer) {
        LinkedHashMap linkedHashMapA = a(d3);
        ArrayList<C0210g1> arrayList = new ArrayList();
        for (C0210g1 c0210g1 : d2.m1()) {
            Fa0 fa0 = new Fa0(c0210g1.getAccessFlags(), c0210g1.getType());
            LinkedList linkedList = (LinkedList) linkedHashMapA.get(fa0);
            if (linkedList == null || linkedList.isEmpty()) {
                arrayList.add(c0210g1);
            } else {
                biConsumer.accept(c0210g1, (C0210g1) linkedList.removeFirst());
                if (linkedList.isEmpty()) {
                    linkedHashMapA.remove(fa0);
                }
            }
        }
        LinkedHashMap linkedHashMapA2 = a(c0333y, linkedHashMapA);
        for (C0210g1 c0210g2 : arrayList) {
            boolean z = AbstractC2609sc.a;
            if (!z && !c0210g2.getType().U0()) {
                x1f.a();
                return;
            }
            C0210g1 c0210g3 = (C0210g1) ((LinkedList) linkedHashMapA2.get(new Fa0(c0210g2.getAccessFlags(), c0210g2.getType().U0() ? c0333y.a().a2 : c0210g2.getType()))).removeFirst();
            if (!z && c0210g3 == null) {
                x1f.a();
                return;
            } else {
                if (!z && !c0210g3.getType().U0()) {
                    x1f.a();
                    return;
                }
                biConsumer.accept(c0210g2, c0210g3);
            }
        }
    }

    static /* synthetic */ LinkedList b(Fa0 fa0) {
        return new LinkedList();
    }

    void a(C0210g1 c0210g1);

    C0210g1[] a();

    static LinkedHashMap a(com.android.tools.r8.graph.D2 d2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (C0210g1 c0210g1 : d2.m1()) {
            ((LinkedList) linkedHashMap.computeIfAbsent(new Fa0(c0210g1.getAccessFlags(), c0210g1.getType()), new Function() { // from class: ogi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InterfaceC2781uc.a((Fa0) obj);
                }
            })).add(c0210g1);
        }
        return linkedHashMap;
    }

    static /* synthetic */ LinkedList a(Fa0 fa0) {
        return new LinkedList();
    }

    static LinkedHashMap a(final C0333y c0333y, LinkedHashMap linkedHashMap) {
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap.forEach(new BiConsumer() { // from class: mgi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                InterfaceC2781uc.a(linkedHashMap2, c0333y, (Fa0) obj, (LinkedList) obj2);
            }
        });
        return linkedHashMap2;
    }

    static void a(Map map, C0333y c0333y, Fa0 fa0, LinkedList linkedList) {
        ((LinkedList) map.computeIfAbsent(new Fa0(fa0.a, fa0.b.U0() ? c0333y.a().a2 : fa0.b), new Function() { // from class: ngi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InterfaceC2781uc.b((Fa0) obj);
            }
        })).addAll(linkedList);
    }
}
