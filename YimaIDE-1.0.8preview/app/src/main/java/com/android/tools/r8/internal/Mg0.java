package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Mg0 extends G {
    public final int g;
    public final int h;
    public final XI i;
    public final ArrayList j;

    public Mg0(int i, int i2, XI xi, XI... xiArr) {
        super(170);
        this.g = i;
        this.h = i2;
        this.i = xi;
        this.j = AbstractC2287ol0.a(xiArr);
    }

    @Override // com.android.tools.r8.internal.G
    public final G a(RC rc) {
        int i = this.g;
        int i2 = this.h;
        XI xi = (XI) rc.get(this.i);
        ArrayList arrayList = this.j;
        int size = arrayList.size();
        XI[] xiArr = new XI[size];
        for (int i3 = 0; i3 < size; i3++) {
            xiArr[i3] = rc.a((XI) arrayList.get(i3));
        }
        return new Mg0(i, i2, xi, xiArr).a(this);
    }

    @Override // com.android.tools.r8.internal.G
    public final void a(XO xo) {
        int size = this.j.size();
        WI[] wiArr = new WI[size];
        for (int i = 0; i < size; i++) {
            wiArr[i] = ((XI) this.j.get(i)).b();
        }
        xo.a(this.g, this.h, this.i.b(), wiArr);
        b(xo);
    }

    @Override // com.android.tools.r8.internal.G
    public final int a() {
        return 11;
    }
}
