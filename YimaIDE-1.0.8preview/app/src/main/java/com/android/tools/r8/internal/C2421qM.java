package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2421qM extends G {
    public final XI g;
    public final ArrayList h;
    public final ArrayList i;

    public C2421qM(XI xi, int[] iArr, XI[] xiArr) {
        super(171);
        this.g = xi;
        this.h = AbstractC2287ol0.a(iArr);
        this.i = AbstractC2287ol0.a(xiArr);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        int size = this.h.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) this.h.get(i)).intValue();
        }
        int size2 = this.i.size();
        WI[] wiArr = new WI[size2];
        for (int i2 = 0; i2 < size2; i2++) {
            wiArr[i2] = ((XI) this.i.get(i2)).b();
        }
        xo.a(this.g.b(), iArr, wiArr);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 12;
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        XI xi = (XI) rc.get(this.g);
        ArrayList arrayList = this.i;
        int size = arrayList.size();
        XI[] xiArr = new XI[size];
        for (int i = 0; i < size; i++) {
            xiArr[i] = rc.a((XI) arrayList.get(i));
        }
        C2421qM c2421qM = new C2421qM(xi, null, xiArr);
        c2421qM.h.addAll(this.h);
        return c2421qM.a(this);
    }
}
