package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0738Pa {
    public static final /* synthetic */ boolean c = true;
    public AbstractC0686Na a;
    public AbstractC2004lX b;

    public static C0505Ga a(C0505Ga c0505Ga, C0505Ga c0505Ga2, com.android.tools.r8.graph.B5 b5) {
        AbstractC0660Ma[] abstractC0660MaArr = c0505Ga.d;
        int length = abstractC0660MaArr.length;
        AbstractC0660Ma[] abstractC0660MaArr2 = c0505Ga2.d;
        if (length != abstractC0660MaArr2.length) {
            throw new C0613Ke("Different stack heights at jump target: " + abstractC0660MaArr.length + " != " + abstractC0660MaArr2.length, b5.getOrigin());
        }
        int i = 0;
        while (true) {
            AbstractC0660Ma[] abstractC0660MaArr3 = c0505Ga.d;
            if (i >= abstractC0660MaArr3.length) {
                return c0505Ga;
            }
            if (abstractC0660MaArr3[i].a() != c0505Ga2.d[i].a()) {
                throw new C0613Ke("Incompatible types in stack position " + i + ": " + c0505Ga.d[i] + " and " + c0505Ga2.d[i], b5.getOrigin());
            }
            i++;
        }
    }

    public final String toString() {
        AbstractC0686Na abstractC0686Na = this.a;
        C0505Ga c0505Ga = new C0505Ga(abstractC0686Na.b() + 1, abstractC0686Na.d());
        abstractC0686Na.a(c0505Ga);
        return c0505Ga.toString();
    }

    public final C0583Ja a(com.android.tools.r8.graph.I2 i2) {
        return a(new C0635La(i2));
    }

    public final C0583Ja a(El0 el0) {
        return a(new C0609Ka(el0));
    }

    public final C0583Ja a(AbstractC0660Ma abstractC0660Ma) {
        a(new C0557Ia(this.a, abstractC0660Ma));
        return this.a.c();
    }

    public final void a(AbstractC0686Na abstractC0686Na) {
        if (abstractC0686Na.b >= 4) {
            C0505Ga c0505Ga = new C0505Ga(abstractC0686Na.b() + 1, abstractC0686Na.d());
            abstractC0686Na.a(c0505Ga);
            abstractC0686Na = c0505Ga;
        }
        this.a = abstractC0686Na;
    }

    public final C0583Ja a() {
        C0583Ja c0583JaC = this.a.c();
        a(new C0531Ha(this.a));
        return c0583JaC;
    }

    public final C0583Ja a(int i) {
        return this.a.a(i);
    }

    public final C0583Ja a(int i, com.android.tools.r8.graph.I2 i2) {
        a(new C0712Oa(this.a, i, new C0635La(i2)));
        return this.a.a(i);
    }
}
