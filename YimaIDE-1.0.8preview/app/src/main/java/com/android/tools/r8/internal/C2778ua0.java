package com.android.tools.r8.internal;

import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ua0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2778ua0 extends C2949wa0 {
    public final Supplier c;
    public final StringBuilder d;

    public C2778ua0(StringBuilder sb, Supplier supplier) {
        super(sb);
        this.d = new StringBuilder();
        this.c = supplier;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 a(boolean z) {
        return z ? this : new C2863va0(this);
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 b(String str) {
        this.d.append(str);
        this.a.append(str);
        return this;
    }

    public final C2778ua0 c(String str) {
        StringBuilder sb = this.d;
        sb.append('<');
        sb.append(this.c.get());
        sb.append('>');
        this.a.append(str);
        return this;
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 d() {
        return c("*");
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 e() {
        return c("***");
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 a() {
        return c("(...)");
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 b() {
        return c("**");
    }

    @Override // com.android.tools.r8.internal.C2949wa0
    public final C2949wa0 c() {
        return c("%");
    }
}
