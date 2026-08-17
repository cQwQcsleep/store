package com.android.tools.r8.naming;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.naming.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3319e implements L {
    public static final /* synthetic */ boolean g = true;
    public final String b;
    public final char[] c;
    public int d = 0;
    public int e = 1;
    public final /* synthetic */ C3321f f;

    public C3319e(C3321f c3321f, String str, String str2) {
        this.f = c3321f;
        this.b = str;
        this.c = ("L" + str + (str.isEmpty() ? XmlPullParser.NO_NAMESPACE : str2)).toCharArray();
    }

    @Override // com.android.tools.r8.naming.L
    public final int a() {
        int i = this.e;
        this.e = i + 1;
        return i;
    }

    @Override // com.android.tools.r8.naming.L
    public final int b() {
        return this.d;
    }

    @Override // com.android.tools.r8.naming.L
    public final int c() {
        int i = this.d;
        this.d = i + 1;
        return i;
    }
}
