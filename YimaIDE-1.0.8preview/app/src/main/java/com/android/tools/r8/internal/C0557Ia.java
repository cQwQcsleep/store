package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ia, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0557Ia extends AbstractC0686Na {
    public static final /* synthetic */ boolean d = true;
    public final C0583Ja c;

    public C0557Ia(AbstractC0686Na abstractC0686Na, AbstractC0660Ma abstractC0660Ma) {
        super(abstractC0686Na.b + 1, abstractC0686Na);
        int iD = abstractC0686Na.d() + 100000;
        this.c = new C0583Ja(iD, abstractC0660Ma);
        if (d) {
            return;
        }
        if (100000 > iD || iD >= 200000) {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final void a(C0505Ga c0505Ga) {
        this.a.a(c0505Ga);
        int iB = C0583Ja.b(this.c.a);
        AbstractC0660Ma[] abstractC0660MaArr = c0505Ga.d;
        if (iB < abstractC0660MaArr.length) {
            abstractC0660MaArr[C0583Ja.b(this.c.a)] = this.c.d;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja b(int i) {
        return i == C0583Ja.b(this.c.a) ? this.c : this.a.b(i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja c() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final int d() {
        return C0583Ja.b(this.c.a) + 1;
    }

    public final String toString() {
        return this.a.toString() + "; push(" + this.c.d + ")";
    }
}
