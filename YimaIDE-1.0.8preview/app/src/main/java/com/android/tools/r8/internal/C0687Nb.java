package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0687Nb extends AbstractC2167nP {
    public final boolean b;
    public final C0661Mb c;
    public final C0661Mb d;
    public final C0661Mb e;
    public final C0661Mb f;
    public final C0661Mb g;
    public int h;
    public boolean i;

    public C0687Nb(int i, AbstractC2167nP abstractC2167nP, boolean z) {
        super(i, abstractC2167nP);
        this.c = new C0661Mb("Modules requires");
        this.d = new C0661Mb("Module exports");
        this.e = new C0661Mb("Module opens");
        this.f = new C0661Mb("Module uses");
        this.g = new C0661Mb("Module provides");
        this.b = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(int i, String str, String str2) {
        b();
        AbstractC0480Fb.b(53, str, "required module");
        this.c.a(str);
        AbstractC0480Fb.a(i, 36960);
        if (this.h >= 54 && str.equals("java.base") && (i & 96) != 0) {
            w01.a(AbstractC1784iv.a(i, "Invalid access flags: ", " java.base can not be declared ACC_TRANSITIVE or ACC_STATIC_PHASE"));
            return;
        }
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.a(i, str, str2);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void b(String str, int i, String... strArr) {
        b();
        if (this.b) {
            c41.a("An open module can not use open directive");
            return;
        }
        C0636Lb.b(53, str, "package name");
        this.e.a(str);
        AbstractC0480Fb.a(i, 36864);
        if (strArr != null) {
            for (String str2 : strArr) {
                AbstractC0480Fb.b(53, str2, "module open to");
            }
        }
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.b(str, i, strArr);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void c(String str) {
        b();
        C0636Lb.b(53, str, "service");
        this.f.a(str);
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.c(str);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void b(String str) {
        C0636Lb.b(53, str, "module package");
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.b(str);
        }
    }

    public final void b() {
        if (this.i) {
            k2d.a("Cannot call a visit method after visitEnd has been called");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str) {
        C0636Lb.b(53, str, "module main class");
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.a(str);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str, int i, String... strArr) {
        b();
        C0636Lb.b(53, str, "package name");
        this.d.a(str);
        AbstractC0480Fb.a(i, 36864);
        if (strArr != null) {
            for (String str2 : strArr) {
                AbstractC0480Fb.b(53, str2, "module export to");
            }
        }
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.a(str, i, strArr);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a(String str, String... strArr) {
        b();
        C0636Lb.b(53, str, "service");
        this.g.a(str);
        if (strArr.length != 0) {
            for (String str2 : strArr) {
                C0636Lb.b(53, str2, "provider");
            }
            AbstractC2167nP abstractC2167nP = this.a;
            if (abstractC2167nP != null) {
                abstractC2167nP.a(str, strArr);
                return;
            }
            return;
        }
        w01.a("Providers cannot be null or empty");
    }

    @Override // com.android.tools.r8.internal.AbstractC2167nP
    public final void a() {
        b();
        this.i = true;
        AbstractC2167nP abstractC2167nP = this.a;
        if (abstractC2167nP != null) {
            abstractC2167nP.a();
        }
    }
}
