package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0779Qp {
    public final int a;
    public AbstractC0779Qp b;

    public AbstractC0779Qp(int i, AbstractC0779Qp abstractC0779Qp) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            w01.a(CX.a(i, "Unsupported api "));
            throw null;
        }
        if (i == 17432576) {
            ug.a(this);
        }
        this.a = i;
        this.b = abstractC0779Qp;
    }

    public J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        if (this.a < 327680) {
            c41.a("This feature requires ASM5");
            return null;
        }
        AbstractC0779Qp abstractC0779Qp = this.b;
        if (abstractC0779Qp != null) {
            return abstractC0779Qp.a(i, c3052xj0, str, z);
        }
        return null;
    }

    public J2 a(String str, boolean z) {
        AbstractC0779Qp abstractC0779Qp = this.b;
        if (abstractC0779Qp != null) {
            return abstractC0779Qp.a(str, z);
        }
        return null;
    }

    public void a(H4 h4) {
        AbstractC0779Qp abstractC0779Qp = this.b;
        if (abstractC0779Qp != null) {
            abstractC0779Qp.a(h4);
        }
    }

    public void a() {
        AbstractC0779Qp abstractC0779Qp = this.b;
        if (abstractC0779Qp != null) {
            abstractC0779Qp.a();
        }
    }
}
