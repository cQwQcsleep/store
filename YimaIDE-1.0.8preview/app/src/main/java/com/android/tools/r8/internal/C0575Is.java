package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Is, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0575Is extends F0 {
    public C0627Ks b;
    public final /* synthetic */ C0601Js c;

    public C0575Is(C0601Js c0601Js, C0627Ks c0627Ks) {
        this.c = c0601Js;
        this.b = c0627Ks;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.b.c;
    }

    @Override // com.android.tools.r8.internal.F0, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.b.c;
        int iA = AbstractC1189bt.a(obj);
        if (iA == this.b.e && WU.a(obj, obj2)) {
            return obj;
        }
        C0860Ts c0860Ts = this.c.g;
        int i = C0860Ts.j;
        DX.a(c0860Ts.b(iA, obj) == null, "value already present: %s", obj);
        this.c.g.a(this.b);
        C0627Ks c0627Ks = this.b;
        C0627Ks c0627Ks2 = new C0627Ks(c0627Ks.b, c0627Ks.d, obj, iA);
        this.c.g.a(c0627Ks2, this.b);
        C0627Ks c0627Ks3 = this.b;
        c0627Ks3.i = null;
        c0627Ks3.h = null;
        C0601Js c0601Js = this.c;
        c0601Js.d = c0601Js.g.h;
        if (c0601Js.c == c0627Ks3) {
            c0601Js.c = c0627Ks2;
        }
        this.b = c0627Ks2;
        return obj2;
    }
}
