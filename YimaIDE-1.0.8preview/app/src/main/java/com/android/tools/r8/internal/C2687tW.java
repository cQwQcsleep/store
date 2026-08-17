package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2687tW extends AbstractC2858vW {
    public final String c;
    public final String d;

    public C2687tW(C2601sW c2601sW, String str, String str2) {
        super(c2601sW);
        this.c = str;
        this.d = str2;
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String a() {
        return AbstractC1732iG.d(this.d) + " " + this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String b() {
        return "field";
    }
}
