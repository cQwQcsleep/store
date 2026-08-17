package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ls, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0652Ls extends F0 {
    public C0627Ks b;
    public final /* synthetic */ C0678Ms c;

    public C0652Ls(C0678Ms c0678Ms, C0627Ks c0627Ks) {
        this.c = c0678Ms;
        this.b = c0627Ks;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b.c;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.b.b;
    }

    @Override // com.android.tools.r8.internal.F0, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.b.b;
        int iA = AbstractC1189bt.a(obj);
        if (iA == this.b.d && WU.a(obj, obj2)) {
            return obj;
        }
        C0860Ts c0860Ts = this.c.g.b;
        int i = C0860Ts.j;
        DX.a(c0860Ts.a(iA, obj) == null, "value already present: %s", obj);
        this.c.g.b.a(this.b);
        C0627Ks c0627Ks = this.b;
        C0627Ks c0627Ks2 = new C0627Ks(obj, iA, c0627Ks.c, c0627Ks.e);
        this.b = c0627Ks2;
        this.c.g.b.a(c0627Ks2, (C0627Ks) null);
        C0678Ms c0678Ms = this.c;
        c0678Ms.d = c0678Ms.g.b.h;
        return obj2;
    }
}
