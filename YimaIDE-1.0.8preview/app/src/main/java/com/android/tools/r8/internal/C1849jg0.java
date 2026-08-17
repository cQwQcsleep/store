package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1849jg0 {
    public final XI a;
    public final boolean[] b;
    public final ArrayList c;

    public C1849jg0(C1849jg0 c1849jg0) {
        this.a = c1849jg0.a;
        this.b = (boolean[]) c1849jg0.b.clone();
        this.c = new ArrayList(c1849jg0.c);
    }

    public final boolean a(C1849jg0 c1849jg0) {
        int i = 0;
        boolean z = false;
        while (true) {
            boolean[] zArr = this.b;
            if (i >= zArr.length) {
                break;
            }
            if (c1849jg0.b[i] && !zArr[i]) {
                zArr[i] = true;
                z = true;
            }
            i++;
        }
        if (c1849jg0.a == this.a) {
            for (int i2 = 0; i2 < c1849jg0.c.size(); i2++) {
                C2839vD c2839vD = (C2839vD) c1849jg0.c.get(i2);
                if (!this.c.contains(c2839vD)) {
                    this.c.add(c2839vD);
                    z = true;
                }
            }
        }
        return z;
    }

    public C1849jg0(XI xi, int i, C2839vD c2839vD) {
        this.a = xi;
        this.b = new boolean[i];
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        arrayList.add(c2839vD);
    }
}
