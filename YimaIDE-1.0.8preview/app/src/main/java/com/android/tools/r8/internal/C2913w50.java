package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2913w50 extends AbstractC3084y50 {
    public static final C2913w50 c = new C2913w50("LINVALID;");
    public static final /* synthetic */ boolean d = true;
    public final String b;

    public C2913w50(String str) {
        if (d || C0929Wj.A(str)) {
            this.b = str;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final boolean a(com.android.tools.r8.naming.mappinginformation.e eVar) {
        eVar.getClass();
        return !(eVar instanceof C2913w50) || this.b.equals(eVar.g().b);
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final com.android.tools.r8.naming.mappinginformation.e b(com.android.tools.r8.naming.mappinginformation.e eVar) {
        if (d) {
            return this;
        }
        eVar.getClass();
        if (eVar instanceof C2913w50) {
            return this;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.naming.mappinginformation.e
    public final C2913w50 g() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3084y50
    public final boolean s() {
        return this != c;
    }

    @Override // com.android.tools.r8.internal.AbstractC3084y50
    public final String t() {
        return this.b;
    }

    public static C2913w50 a(String str) {
        if (C0929Wj.A(str)) {
            return new C2913w50(str);
        }
        return c;
    }
}
