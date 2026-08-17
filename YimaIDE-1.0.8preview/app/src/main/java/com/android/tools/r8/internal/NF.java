package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NF extends VF {
    public final C2516rW j;
    public final C1475fG k;
    public String l;

    public NF(C2516rW c2516rW, C1475fG c1475fG) {
        super(c2516rW);
        this.j = c2516rW;
        this.k = c1475fG;
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.J2
    public final void a() {
        super.a();
        AbstractC2757uG abstractC2757uGC = c();
        if (!abstractC2757uGC.f()) {
            this.k.a(this.l, abstractC2757uGC.c());
            return;
        }
        this.j.a("Invalid binding reference to '" + abstractC2757uGC.a() + "' in binding definition of '" + this.l + "'");
        throw null;
    }

    @Override // com.android.tools.r8.internal.VF
    public final C1475fG b() {
        return this.k;
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("bindingName") && (obj instanceof String)) {
            this.l = (String) obj;
        } else {
            super.a(obj, str);
        }
    }
}
