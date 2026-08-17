package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oP, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2253oP extends AbstractC2167nP {
    public final Ag0 b;
    public final int c;
    public final int d;
    public final int e;
    public int f;
    public final X7 g;
    public int h;
    public final X7 i;
    public int j;
    public final X7 k;
    public int l;
    public final X7 m;
    public int n;
    public final X7 o;
    public int p;
    public final X7 q;
    public int r;

    public C2253oP(Ag0 ag0, int i, int i2, int i3) {
        super(589824, null);
        this.b = ag0;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.g = new X7();
        this.i = new X7();
        this.k = new X7();
        this.m = new X7();
        this.o = new X7();
        this.q = new X7();
    }

    public final void a(X7 x7) {
        X7 x7D = x7.d(this.b.a("Module")).c(this.g.b + 16 + this.i.b + this.k.b + this.m.b + this.o.b).d(this.c).d(this.d).d(this.e).d(this.f);
        X7 x8 = this.g;
        X7 x7D2 = x7D.a(x8.a, 0, x8.b).d(this.h);
        X7 x9 = this.i;
        X7 x7D3 = x7D2.a(x9.a, 0, x9.b).d(this.j);
        X7 x10 = this.k;
        X7 x7D4 = x7D3.a(x10.a, 0, x10.b).d(this.l);
        X7 x11 = this.m;
        X7 x7D5 = x7D4.a(x11.a, 0, x11.b).d(this.n);
        X7 x12 = this.o;
        x7D5.a(x12.a, 0, x12.b);
        if (this.p > 0) {
            X7 x7D6 = x7.d(this.b.a("ModulePackages")).c(this.q.b + 2).d(this.p);
            X7 x13 = this.q;
            x7D6.a(x13.a, 0, x13.b);
        }
        if (this.r > 0) {
            x7.d(this.b.a("ModuleMainClass")).c(2).d(this.r);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void b(String str, int i, String... strArr) {
        this.k.d(this.b.a(20, str).a).d(i);
        X7 x7 = this.k;
        if (strArr == null) {
            x7.d(0);
        } else {
            x7.d(strArr.length);
            for (String str2 : strArr) {
                this.k.d(this.b.a(19, str2).a);
            }
        }
        this.j++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void c(String str) {
        this.m.d(this.b.a(7, str).a);
        this.l++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void b(String str) {
        this.q.d(this.b.a(20, str).a);
        this.p++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str) {
        this.r = this.b.a(7, str).a;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(int i, String str, String str2) {
        this.g.d(this.b.a(19, str).a).d(i).d(str2 == null ? 0 : this.b.a(str2));
        this.f++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str, int i, String... strArr) {
        this.i.d(this.b.a(20, str).a).d(i);
        X7 x7 = this.i;
        if (strArr == null) {
            x7.d(0);
        } else {
            x7.d(strArr.length);
            for (String str2 : strArr) {
                this.i.d(this.b.a(19, str2).a);
            }
        }
        this.h++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str, String... strArr) {
        this.o.d(this.b.a(7, str).a);
        this.o.d(strArr.length);
        for (String str2 : strArr) {
            this.o.d(this.b.a(7, str2).a);
        }
        this.n++;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a() {
    }
}
