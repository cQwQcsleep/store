package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2914w6 {
    public static final /* synthetic */ boolean c = true;
    public final boolean a;
    public final C2828v6 b;

    public C2914w6(String str) {
        int iIndexOf = str.indexOf(58);
        this.a = str.substring(0, iIndexOf).trim().equals("good");
        this.b = new C2828v6(str.substring(iIndexOf + 1).trim());
    }

    public C2914w6(EnumC2743u6 enumC2743u6, C2828v6 c2828v6) {
        if (!c && enumC2743u6 == EnumC2743u6.b) {
            x1f.a();
            throw null;
        }
        this.a = enumC2743u6 == EnumC2743u6.c;
        this.b = c2828v6;
    }
}
