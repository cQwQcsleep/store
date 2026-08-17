package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OF extends K2 {
    public static final /* synthetic */ boolean e = true;
    public final C3030xW c;
    public final C1475fG d;

    public OF(C3030xW c3030xW, C1475fG c1475fG) {
        super(c3030xW);
        this.c = c3030xW;
        this.d = c1475fG;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        if (!e && str != null) {
            x1f.a();
            return null;
        }
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/KeepBinding;")) {
            super.a(str, str2);
            throw null;
        }
        C3030xW c3030xW = this.c;
        c3030xW.getClass();
        return new NF(new C2516rW(c3030xW, str2), this.d);
    }
}
