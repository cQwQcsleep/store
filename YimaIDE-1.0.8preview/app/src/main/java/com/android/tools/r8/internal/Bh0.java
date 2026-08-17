package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Bh0 extends Ch0 {
    public final Ch0 g;
    public final C2752uB h;

    public Bh0(C2752uB c2752uB, Ch0 ch0) {
        super("<cancel>", false);
        this.g = ch0;
        this.h = c2752uB;
    }

    @Override // com.android.tools.r8.internal.Ch0
    public final void a(String str) {
        C2752uB c2752uB = this.h;
        if (c2752uB.c != null) {
            if (!c2752uB.b.get()) {
                if (c2752uB.c.cancel()) {
                    c2752uB.b.set(true);
                }
            }
            throw new C2148n8();
        }
        if (!C2752uB.Y1 && c2752uB.b.get()) {
            x1f.a();
            return;
        }
        this.g.a(str);
    }

    @Override // com.android.tools.r8.internal.Ch0
    public final void b() {
        this.g.b();
    }

    @Override // com.android.tools.r8.internal.Ch0
    public final void c() {
        this.g.c();
    }

    @Override // com.android.tools.r8.internal.Ch0
    public final void a(String str, InterfaceC1681hh0 interfaceC1681hh0) {
        this.g.a(str, interfaceC1681hh0);
    }

    @Override // com.android.tools.r8.internal.Ch0
    public final Object a(String str, InterfaceC2706th0 interfaceC2706th0) {
        return this.g.a(str, interfaceC2706th0);
    }

    @Override // com.android.tools.r8.internal.Ch0
    public final Ah0 a(int i, String str) {
        return this.g.a(i, str);
    }
}
