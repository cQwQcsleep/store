package com.android.tools.r8.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GM extends AbstractC3220zi0 {
    public final Bi0 a;
    public final Bi0 b;
    public final /* synthetic */ HM c;

    public GM(HM hm, C0471Es c0471Es, Type type, AbstractC3220zi0 abstractC3220zi0, Type type2, AbstractC3220zi0 abstractC3220zi1, AU au) {
        this.c = hm;
        this.a = new Bi0(c0471Es, abstractC3220zi0, type);
        this.b = new Bi0(c0471Es, abstractC3220zi1, type2);
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        String strG;
        Map map = (Map) obj;
        if (map == null) {
            c2754uD.i();
            return;
        }
        if (!this.c.c) {
            c2754uD.e();
            for (Map.Entry entry : map.entrySet()) {
                c2754uD.b(String.valueOf(entry.getKey()));
                this.b.a(c2754uD, entry.getValue());
            }
            c2754uD.g();
            return;
        }
        ArrayList arrayList = new ArrayList(map.size());
        ArrayList arrayList2 = new ArrayList(map.size());
        int i = 0;
        boolean z = false;
        for (Map.Entry entry2 : map.entrySet()) {
            Bi0 bi0 = this.a;
            Object key = entry2.getKey();
            bi0.getClass();
            try {
                C2668tD c2668tD = new C2668tD();
                bi0.a(c2668tD, key);
                if (!c2668tD.l.isEmpty()) {
                    throw new IllegalStateException("Expected one JSON element but was " + c2668tD.l);
                }
                AbstractC1643hD abstractC1643hD = c2668tD.n;
                arrayList.add(abstractC1643hD);
                arrayList2.add(entry2.getValue());
                abstractC1643hD.getClass();
                z |= (abstractC1643hD instanceof C1558gD) || (abstractC1643hD instanceof C1898kD);
            } catch (IOException e) {
                throw new C1729iD(e);
            }
        }
        if (z) {
            c2754uD.d();
            int size = arrayList.size();
            while (i < size) {
                c2754uD.d();
                AbstractC1643hD abstractC1643hD2 = (AbstractC1643hD) arrayList.get(i);
                Zi0 zi0 = AbstractC2197nj0.a;
                Wi0.a(c2754uD, abstractC1643hD2);
                this.b.a(c2754uD, arrayList2.get(i));
                c2754uD.f();
                i++;
            }
            c2754uD.f();
            return;
        }
        c2754uD.e();
        int size2 = arrayList.size();
        while (i < size2) {
            AbstractC1643hD abstractC1643hD3 = (AbstractC1643hD) arrayList.get(i);
            abstractC1643hD3.getClass();
            boolean z2 = abstractC1643hD3 instanceof C2155nD;
            if (z2) {
                if (!z2) {
                    qu7.a("Not a JSON Primitive: ", abstractC1643hD3);
                    return;
                }
                C2155nD c2155nD = (C2155nD) abstractC1643hD3;
                Object obj2 = c2155nD.b;
                if (obj2 instanceof Number) {
                    strG = String.valueOf(c2155nD.i());
                } else if (obj2 instanceof Boolean) {
                    strG = Boolean.toString(c2155nD.a());
                } else {
                    if (!(obj2 instanceof String)) {
                        x1f.a();
                        return;
                    }
                    strG = c2155nD.g();
                }
            } else {
                if (!(abstractC1643hD3 instanceof C1813jD)) {
                    x1f.a();
                    return;
                }
                strG = "null";
            }
            c2754uD.b(strG);
            this.b.a(c2754uD, arrayList2.get(i));
            i++;
        }
        c2754uD.g();
    }
}
