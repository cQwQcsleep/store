package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1391eI {
    public Integer b;
    public WH c;
    public C3100yI d;
    public int a = 0;
    public final ArrayList e = new ArrayList(0);
    public final ArrayList f = new ArrayList(0);

    public final void a(C1391eI c1391eI) {
        int i = this.a;
        Integer num = this.b;
        c1391eI.a = i;
        c1391eI.b = num;
        WH wh = this.c;
        if (wh != null) {
            c1391eI.c = new WH((Boolean) wh.a);
        }
        C3100yI c3100yI = this.d;
        if (c3100yI != null) {
            C3100yI c3100yI2 = new C3100yI(c3100yI.e());
            c1391eI.d = c3100yI2;
            c3100yI.a((HI) c3100yI2);
        }
        for (C1391eI c1391eI2 : this.e) {
            C1391eI c1391eI3 = new C1391eI();
            ArrayList arrayList = c1391eI.e;
            KB.c(arrayList, "collection");
            arrayList.add(c1391eI3);
            c1391eI2.a(c1391eI3);
        }
        for (C1391eI c1391eI4 : this.f) {
            C1391eI c1391eI5 = new C1391eI();
            ArrayList arrayList2 = c1391eI.f;
            KB.c(arrayList2, "collection");
            arrayList2.add(c1391eI5);
            c1391eI4.a(c1391eI5);
        }
    }

    public final int b() {
        return this.a;
    }

    public final Integer c() {
        return this.b;
    }

    public final void a(C3100yI c3100yI) {
        this.d = c3100yI;
    }

    public final WH a() {
        return this.c;
    }
}
