package com.android.tools.r8.naming;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: renamed from: com.android.tools.r8.naming.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3341p {
    public static final /* synthetic */ boolean b = true;
    public final TreeMap a;

    public C3341p(TreeMap treeMap) {
        this.a = treeMap;
    }

    public static C3341p a(List list) {
        TreeMap treeMap = new TreeMap();
        if (!list.isEmpty()) {
            C0473Eu c0473EuG = AbstractC0551Hu.g();
            N0 n0 = ((C3331k.b) list.get(0)).b;
            if (n0 != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    C3331k.b bVar = (C3331k.b) it.next();
                    if (!n0.equals(bVar.b)) {
                        treeMap.put(Integer.valueOf(n0.a), c0473EuG.a());
                        c0473EuG = new C0473Eu(4);
                        n0 = bVar.b;
                    }
                    c0473EuG.a(bVar);
                }
                treeMap.put(Integer.valueOf(n0.a), c0473EuG.a());
            } else {
                if (!b && list.size() != 1) {
                    x1f.a();
                    return null;
                }
                treeMap.put(-1, Collections.singletonList((C3331k.b) list.get(0)));
            }
        }
        return new C3341p(treeMap);
    }

    public final List b(int i) {
        Integer num = (Integer) this.a.floorKey(Integer.valueOf(i));
        if (num == null) {
            return null;
        }
        return (List) this.a.get(num);
    }

    public final List a(int i) {
        Integer num = (Integer) this.a.ceilingKey(Integer.valueOf(i));
        if (num == null) {
            return null;
        }
        return (List) this.a.get(num);
    }
}
