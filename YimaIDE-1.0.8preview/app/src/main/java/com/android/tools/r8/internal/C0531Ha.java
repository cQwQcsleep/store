package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ha, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0531Ha extends AbstractC0686Na {
    public static final /* synthetic */ boolean d = true;
    public final int c;

    public C0531Ha(AbstractC0686Na abstractC0686Na) {
        super(abstractC0686Na.b + 1, abstractC0686Na);
        int iD = abstractC0686Na.d() - 1;
        this.c = iD;
        if (d || iD >= 0) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja b(int i) {
        if (d || i < this.c) {
            return this.a.b(i);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja c() {
        return this.a.b(this.c - 1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final int d() {
        return this.c;
    }

    public final String toString() {
        return this.a.toString() + "; pop";
    }
}
