package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ok0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2285ok0 implements SN {
    public final TreeMap b = new TreeMap();

    public final boolean a(int i, AbstractC0663Md abstractC0663Md) throws PB {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            C2371pk0 c2371pk0A = a(i2);
            long jK = abstractC0663Md.k();
            C2456qk0 c2456qk0 = c2371pk0A.a;
            if (c2456qk0.a == null) {
                c2456qk0.a = new ArrayList();
            }
            c2371pk0A.a.a.add(Long.valueOf(jK));
            return true;
        }
        if (i3 == 1) {
            C2371pk0 c2371pk0A2 = a(i2);
            long jH = abstractC0663Md.h();
            C2456qk0 c2456qk1 = c2371pk0A2.a;
            if (c2456qk1.c == null) {
                c2456qk1.c = new ArrayList();
            }
            c2371pk0A2.a.c.add(Long.valueOf(jH));
            return true;
        }
        if (i3 == 2) {
            C2371pk0 c2371pk0A3 = a(i2);
            Q7 q7D = abstractC0663Md.d();
            C2456qk0 c2456qk2 = c2371pk0A3.a;
            if (c2456qk2.d == null) {
                c2456qk2.d = new ArrayList();
            }
            c2371pk0A3.a.d.add(q7D);
            return true;
        }
        if (i3 == 3) {
            C2712tk0 c2712tk0 = C2712tk0.c;
            C2285ok0 c2285ok0 = new C2285ok0();
            abstractC0663Md.a(i2, c2285ok0, C3144yo.e);
            C2371pk0 c2371pk0A4 = a(i2);
            C2712tk0 c2712tk0Build = c2285ok0.build();
            C2456qk0 c2456qk3 = c2371pk0A4.a;
            if (c2456qk3.e == null) {
                c2456qk3.e = new ArrayList();
            }
            c2371pk0A4.a.e.add(c2712tk0Build);
            return true;
        }
        if (i3 == 4) {
            return false;
        }
        if (i3 != 5) {
            throw new PB();
        }
        C2371pk0 c2371pk0A5 = a(i2);
        int iG = abstractC0663Md.g();
        C2456qk0 c2456qk4 = c2371pk0A5.a;
        if (c2456qk4.b == null) {
            c2456qk4.b = new ArrayList();
        }
        c2371pk0A5.a.b.add(Integer.valueOf(iG));
        return true;
    }

    public final Object clone() {
        C2712tk0 c2712tk0 = C2712tk0.c;
        C2285ok0 c2285ok0 = new C2285ok0();
        for (Map.Entry entry : this.b.entrySet()) {
            c2285ok0.b.put((Integer) entry.getKey(), ((C2371pk0) entry.getValue()).m18clone());
        }
        return c2285ok0;
    }

    @Override // com.android.tools.r8.internal.SN
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final C2712tk0 build() {
        if (this.b.isEmpty()) {
            return C2712tk0.c;
        }
        TreeMap treeMap = new TreeMap();
        for (Map.Entry entry : this.b.entrySet()) {
            treeMap.put(entry.getKey(), ((C2371pk0) entry.getValue()).a());
        }
        return new C2712tk0(treeMap);
    }

    public final C2371pk0 a(int i) {
        if (i == 0) {
            return null;
        }
        C2371pk0 c2371pk0 = (C2371pk0) this.b.get(Integer.valueOf(i));
        if (c2371pk0 != null) {
            return c2371pk0;
        }
        int i2 = C2456qk0.f;
        C2371pk0 c2371pk1 = new C2371pk0();
        this.b.put(Integer.valueOf(i), c2371pk1);
        return c2371pk1;
    }

    public final void a(int i, int i2) {
        if (i > 0) {
            C2371pk0 c2371pk0A = a(i);
            long j = i2;
            C2456qk0 c2456qk0 = c2371pk0A.a;
            if (c2456qk0.a == null) {
                c2456qk0.a = new ArrayList();
            }
            c2371pk0A.a.a.add(Long.valueOf(j));
            return;
        }
        zqc.a(i, " is not a valid field number.");
    }

    public final void a(int i, C2456qk0 c2456qk0) {
        if (i > 0) {
            if (this.b.containsKey(Integer.valueOf(i))) {
                a(i).a(c2456qk0);
                return;
            }
            if (i > 0) {
                TreeMap treeMap = this.b;
                Integer numValueOf = Integer.valueOf(i);
                int i2 = C2456qk0.f;
                treeMap.put(numValueOf, new C2371pk0().a(c2456qk0));
                return;
            }
            zqc.a(i, " is not a valid field number.");
            return;
        }
        zqc.a(i, " is not a valid field number.");
    }

    @Override // com.android.tools.r8.internal.SN
    public final SN a(AbstractC0663Md abstractC0663Md, C0415Co c0415Co) {
        int iS;
        do {
            iS = abstractC0663Md.s();
            if (iS == 0) {
                break;
            }
        } while (a(iS, abstractC0663Md));
        return this;
    }

    public final C2285ok0 a(C2712tk0 c2712tk0) {
        if (c2712tk0 != C2712tk0.c) {
            for (Map.Entry entry : c2712tk0.b.entrySet()) {
                a(((Integer) entry.getKey()).intValue(), (C2456qk0) entry.getValue());
            }
        }
        return this;
    }
}
