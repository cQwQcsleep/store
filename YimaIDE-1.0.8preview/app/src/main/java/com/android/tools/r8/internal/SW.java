package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SW extends XK {
    public static final /* synthetic */ boolean d = true;
    public final C1651hL a = AbstractC1737iL.a;
    public final C2481r30 b = new C2481r30();
    public final C2481r30 c = new C2481r30();

    @Override // com.android.tools.r8.internal.XK
    public final int a(H5 h5) {
        if (d || this.c.containsKey(h5)) {
            return this.c.b(h5);
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.XK
    public final boolean b(Object obj, int i) {
        C2543rl0 c2543rl0 = (C2543rl0) obj;
        if (d || i == this.b.b(c2543rl0)) {
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.XK
    public final boolean b() {
        return true;
    }

    @Override // com.android.tools.r8.internal.XK
    public final Object a(Object obj) {
        return Integer.valueOf(this.b.b((C2543rl0) obj));
    }

    @Override // com.android.tools.r8.internal.XK
    public final void a(H5 h5, int i) {
        if (d || !this.c.containsKey(h5)) {
            this.c.b(i, h5);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.XK
    public final Object a(Object obj, int i) {
        this.b.b(i, (C2543rl0) obj);
        return Integer.valueOf(i);
    }

    @Override // com.android.tools.r8.internal.XK
    public final AbstractC2420qL a() {
        return new TW(this.a);
    }
}
