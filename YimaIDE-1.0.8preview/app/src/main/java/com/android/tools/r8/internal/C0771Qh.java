package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0771Qh {
    public final C0944Wy a = new C0944Wy();
    public C0849Th b = C0849Th.e;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;

    public final void a() {
        int i = this.c;
        C0849Th c0849Th = this.b;
        int i2 = c0849Th.a;
        if (i < i2) {
            this.a.a(i2, c0849Th);
            this.c = this.b.a;
        }
        int i3 = this.c;
        if (i3 < this.d) {
            if (i3 > 0 && !((C0849Th) this.a.get(i3)).b) {
                this.a.remove(this.c);
            }
            int i4 = this.d;
            this.a.a(i4, new C0849Th(i4, false, this.f, this.e));
            this.c = this.d;
        }
        this.b = C0849Th.e;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }
}
