package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.js, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1867js implements InterfaceC1188bs {
    public final Class a;
    public final C1856jk b;
    public final boolean c;
    public final boolean d;
    public final C1782is e;

    /* JADX WARN: Code duplicated, block: B:27:0x0041  */
    /* JADX WARN: Code duplicated, block: B:32:0x004e  */
    public C1867js(C1856jk c1856jk, String str, Class cls, Class cls2, String str2) {
        boolean z;
        C2198nk c2198nk = c1856jk.k;
        boolean z2 = (c2198nk == null || c2198nk.f()) ? false : true;
        this.c = z2;
        char c = 2;
        if (c1856jk.e.f() == 2 || c1856jk.g) {
            z = true;
        } else if (c1856jk.e.f() == 2) {
            int i = c1856jk.c.h;
            if (i == 1) {
                c = 1;
            } else if (i != 2) {
                c = 3;
                if (i != 3) {
                    c = 0;
                }
            }
            if ((c == 0 ? (char) 1 : c) == 1 && c1856jk.k == null) {
                z = true;
            } else {
                if (z2) {
                }
                z = false;
            }
        } else if (z2 && c1856jk.h.b == EnumC1686hk.k) {
            z = true;
        } else {
            z = false;
        }
        this.d = z;
        C1782is c1782is = new C1782is(str, cls, cls2, str2, z2, z);
        this.b = c1856jk;
        this.a = c1782is.a.getReturnType();
        this.e = c1782is;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public final boolean a(AbstractC2209ns abstractC2209ns) {
        if (this.d) {
            return ((Boolean) AbstractC2209ns.a(this.e.d, abstractC2209ns, new Object[0])).booleanValue();
        }
        if (this.c) {
            return ((InterfaceC1046aB) AbstractC2209ns.a(this.e.f, abstractC2209ns, new Object[0])).a() == this.b.c.g;
        }
        return !b(abstractC2209ns).equals(this.b.f());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public Object b(AbstractC2209ns abstractC2209ns) {
        return AbstractC2209ns.a(this.e.a, abstractC2209ns, new Object[0]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public Object b(AbstractC0911Vr abstractC0911Vr) {
        return AbstractC2209ns.a(this.e.b, abstractC0911Vr, new Object[0]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public void b(AbstractC0911Vr abstractC0911Vr, Object obj) {
        AbstractC2209ns.a(this.e.c, abstractC0911Vr, new Object[]{obj});
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public final void a(AbstractC0911Vr abstractC0911Vr, Object obj) {
        throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public final boolean a(AbstractC0911Vr abstractC0911Vr) {
        if (!this.d) {
            if (this.c) {
                return ((InterfaceC1046aB) AbstractC2209ns.a(this.e.g, abstractC0911Vr, new Object[0])).a() == this.b.c.g;
            }
            return !b(abstractC0911Vr).equals(this.b.f());
        }
        return ((Boolean) AbstractC2209ns.a(this.e.e, abstractC0911Vr, new Object[0])).booleanValue();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public H0 a() {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
    }
}
