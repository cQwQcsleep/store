package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2526rd {
    public final int a = 589824;
    public final AbstractC2526rd b;

    public AbstractC2526rd(C0714Oc c0714Oc) {
        this.b = c0714Oc;
    }

    public void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (this.a < 524288 && (65536 & i2) != 0) {
            c41.a("Records requires ASM8");
            return;
        }
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.a(i, i2, str, str2, str3, strArr);
        }
    }

    public AbstractC1966l20 b(String str, String str2, String str3) {
        if (this.a < 524288) {
            c41.a("Record requires ASM8");
            return null;
        }
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            return abstractC2526rd.b(str, str2, str3);
        }
        return null;
    }

    public void c(String str) {
        if (this.a < 589824) {
            c41.a("PermittedSubclasses requires ASM9");
            return;
        }
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.c(str);
        }
    }

    public void b(String str) {
        if (this.a >= 458752) {
            AbstractC2526rd abstractC2526rd = this.b;
            if (abstractC2526rd != null) {
                abstractC2526rd.b(str);
                return;
            }
            return;
        }
        c41.a("NestMember requires ASM7");
    }

    public void a(String str, String str2) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.a(str, str2);
        }
    }

    public AbstractC2167nP a(int i, String str, String str2) {
        if (this.a >= 393216) {
            AbstractC2526rd abstractC2526rd = this.b;
            if (abstractC2526rd != null) {
                return abstractC2526rd.a(i, str, str2);
            }
            return null;
        }
        c41.a("Module requires ASM6");
        return null;
    }

    public void a(String str) {
        if (this.a >= 458752) {
            AbstractC2526rd abstractC2526rd = this.b;
            if (abstractC2526rd != null) {
                abstractC2526rd.a(str);
                return;
            }
            return;
        }
        c41.a("NestHost requires ASM7");
    }

    public void a(String str, String str2, String str3) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.a(str, str2, str3);
        }
    }

    public J2 a(String str, boolean z) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            return abstractC2526rd.a(str, z);
        }
        return null;
    }

    public J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.a >= 327680) {
            AbstractC2526rd abstractC2526rd = this.b;
            if (abstractC2526rd != null) {
                return abstractC2526rd.a(i, c3052xj0, str, z);
            }
            return null;
        }
        c41.a("TypeAnnotation requires ASM5");
        return null;
    }

    public void a(H4 h4) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.a(h4);
        }
    }

    public void a(int i, String str, String str2, String str3) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.a(i, str, str2, str3);
        }
    }

    public AbstractC0779Qp a(int i, String str, String str2, String str3, Object obj) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            return abstractC2526rd.a(i, str, str2, str3, obj);
        }
        return null;
    }

    public XO a(int i, String str, String str2, String str3, String[] strArr) {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            return abstractC2526rd.a(i, str, str2, str3, strArr);
        }
        return null;
    }

    public void a() {
        AbstractC2526rd abstractC2526rd = this.b;
        if (abstractC2526rd != null) {
            abstractC2526rd.a();
        }
    }
}
