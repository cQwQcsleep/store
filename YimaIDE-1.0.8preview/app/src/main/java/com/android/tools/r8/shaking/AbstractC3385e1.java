package com.android.tools.r8.shaking;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3385e1 {
    public final AbstractC3395g1 a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;

    public AbstractC3385e1(AbstractC3395g1 abstractC3395g1) {
        this.a = abstractC3395g1;
        this.b = abstractC3395g1.a;
        this.c = abstractC3395g1.b;
        this.d = abstractC3395g1.c;
        this.e = abstractC3395g1.d;
        this.f = abstractC3395g1.e;
        this.g = abstractC3395g1.f;
        this.h = abstractC3395g1.g;
        this.i = abstractC3395g1.h;
    }

    public boolean a(AbstractC3395g1 abstractC3395g1) {
        return this.b == abstractC3395g1.a && this.c == abstractC3395g1.b && this.d == abstractC3395g1.c && this.e == abstractC3395g1.d && this.f == abstractC3395g1.e && this.g == abstractC3395g1.f && this.h == abstractC3395g1.g && this.i == abstractC3395g1.h;
    }

    public void b() {
        this.e = false;
        j();
    }

    public abstract boolean b(AbstractC3395g1 abstractC3395g1);

    public void c() {
        this.f = false;
        j();
    }

    public void d() {
        this.g = false;
        j();
    }

    public abstract AbstractC3395g1 e();

    public abstract AbstractC3395g1 f();

    public abstract AbstractC3395g1 g();

    public final AbstractC3385e1 h() {
        this.b = true;
        j();
        this.c = true;
        j();
        this.d = true;
        j();
        this.e = true;
        j();
        this.f = true;
        j();
        this.g = true;
        j();
        this.h = true;
        j();
        this.i = false;
        j();
        return j();
    }

    public abstract AbstractC3385e1 i();

    public abstract AbstractC3385e1 j();

    public AbstractC3385e1() {
    }

    public AbstractC3395g1 a() {
        AbstractC3395g1 abstractC3395g1 = this.a;
        if (abstractC3395g1 != null) {
            if (a(abstractC3395g1)) {
                return this.a;
            }
            if (a(g())) {
                return g();
            }
            if (a(f())) {
                return f();
            }
        }
        return e();
    }
}
