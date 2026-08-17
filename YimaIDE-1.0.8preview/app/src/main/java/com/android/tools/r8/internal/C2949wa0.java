package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2949wa0 {
    public static final /* synthetic */ boolean b = true;
    public final StringBuilder a;

    public C2949wa0(StringBuilder sb) {
        this.a = sb;
    }

    public final C2949wa0 a(String str) {
        boolean z = b;
        if (!z && str.contains("*")) {
            x1f.a();
            return null;
        }
        if (!z && str.contains("(...)")) {
            x1f.a();
            return null;
        }
        if (z || !str.contains("%")) {
            return b(str);
        }
        x1f.a();
        return null;
    }

    public C2949wa0 b() {
        return b("**");
    }

    public C2949wa0 c() {
        return b("%");
    }

    public C2949wa0 d() {
        return b("*");
    }

    public C2949wa0 e() {
        return b("***");
    }

    public C2949wa0 b(String str) {
        this.a.append(str);
        return this;
    }

    public C2949wa0 a(boolean z) {
        return this;
    }

    public C2949wa0 a() {
        return b("(...)");
    }
}
