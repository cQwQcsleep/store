package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0792Rc {
    public static final C0792Rc c = new C0792Rc(C0870Uc.b, C0818Sc.b);
    public static final /* synthetic */ boolean d = true;
    public final C0870Uc a;
    public final C0818Sc b;

    public C0792Rc(C0870Uc c0870Uc, C0818Sc c0818Sc) {
        this.a = c0870Uc;
        this.b = c0818Sc;
    }

    public final C0792Rc a(C0792Rc c0792Rc) {
        if (a()) {
            return c0792Rc;
        }
        if (c0792Rc.a()) {
            return this;
        }
        C0870Uc c0870UcA = this.a.a(c0792Rc.a);
        C0818Sc c0818Sc = this.b;
        C0818Sc c0818Sc2 = c0792Rc.b;
        c0818Sc.getClass();
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) c0818Sc.a);
        c1870jv.a((Iterable) c0818Sc2.a);
        return new C0792Rc(c0870UcA, new C0818Sc(c1870jv.a()));
    }

    public final boolean a() {
        if (!d && this.b.a.isEmpty() && !this.a.a.isEmpty()) {
            x1f.a();
            return false;
        }
        return this.b.a.isEmpty();
    }
}
