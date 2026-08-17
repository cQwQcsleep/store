package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Oa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0712Oa extends AbstractC0686Na {
    public static final /* synthetic */ boolean d = true;
    public final C0583Ja c;

    public C0712Oa(AbstractC0686Na abstractC0686Na, int i, AbstractC0660Ma abstractC0660Ma) {
        super(abstractC0686Na.b + 1, abstractC0686Na);
        this.c = new C0583Ja(i, abstractC0660Ma);
        if (d) {
            return;
        }
        if (i < 0 || i >= 100000) {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final void a(C0505Ga c0505Ga) {
        this.a.a(c0505Ga);
        AbstractC0660Ma[] abstractC0660MaArr = c0505Ga.c;
        C0583Ja c0583Ja = this.c;
        abstractC0660MaArr[c0583Ja.a] = c0583Ja.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final int b() {
        return Math.max(this.c.a, this.a.b());
    }

    public final String toString() {
        String string = this.a.toString();
        C0583Ja c0583Ja = this.c;
        return string + "; write " + c0583Ja.a + " := " + c0583Ja.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja a(int i) {
        C0583Ja c0583Ja = this.c;
        return i == c0583Ja.a ? c0583Ja : this.a.a(i);
    }
}
