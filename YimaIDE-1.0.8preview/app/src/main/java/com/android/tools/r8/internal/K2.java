package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class K2 extends J2 {
    public final AbstractC3114yW b;

    public K2(AbstractC3114yW abstractC3114yW) {
        super(null);
        this.b = abstractC3114yW;
    }

    @Override // com.android.tools.r8.internal.J2
    public void a(String str, String str2, String str3) {
        this.b.a("Unexpected enum for property " + str + " of enum type " + C3050xi0.a(0, str2.length(), str2).a() + " with value " + str3);
        throw null;
    }

    @Override // com.android.tools.r8.internal.J2
    public J2 a(String str, String str2) {
        this.b.a("Unexpected annotation for property " + str + " of annotation type " + C3050xi0.a(0, str2.length(), str2).a());
        throw null;
    }

    @Override // com.android.tools.r8.internal.J2
    public void a(Object obj, String str) {
        this.b.a("Unexpected value for property " + str + " with value " + obj);
        throw null;
    }

    @Override // com.android.tools.r8.internal.J2
    public J2 a(String str) {
        this.b.a("Unexpected array for property " + str);
        throw null;
    }
}
