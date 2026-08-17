package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.va0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2863va0 extends C2949wa0 {
    public final C2778ua0 c;

    public C2863va0(C2778ua0 c2778ua0) {
        super(c2778ua0.a);
        this.c = c2778ua0;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 a() {
        this.c.b("(...)");
        return this;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 b() {
        this.c.b("**");
        return this;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 c() {
        this.c.b("%");
        return this;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 d() {
        this.c.b("*");
        return this;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 e() {
        this.c.b("***");
        return this;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 b(String str) {
        this.c.b(str);
        return this;
    }
}
