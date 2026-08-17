package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Na, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0686Na {
    public final AbstractC0686Na a;
    public final int b;

    public AbstractC0686Na(int i, AbstractC0686Na abstractC0686Na) {
        this.a = abstractC0686Na;
        this.b = i;
    }

    public C0505Ga a() {
        C0505Ga c0505Ga = new C0505Ga(b() + 1, d());
        a(c0505Ga);
        return c0505Ga;
    }

    public int b() {
        return this.a.b();
    }

    public C0583Ja c() {
        return this.a.c();
    }

    public int d() {
        return this.a.d();
    }

    public C0583Ja b(int i) {
        return this.a.b(i);
    }

    public void a(C0505Ga c0505Ga) {
        this.a.a(c0505Ga);
    }

    public C0583Ja a(int i) {
        return this.a.a(i);
    }
}
