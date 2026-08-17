package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Uy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0892Uy {
    public C0581Iy b;
    public C0581Iy c;
    public C0581Iy d;
    public int e = 0;
    public final /* synthetic */ C0944Wy f;

    public AbstractC0892Uy(C0944Wy c0944Wy) {
        this.f = c0944Wy;
        this.c = c0944Wy.e;
    }

    public final C0581Iy a() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        C0581Iy c0581Iy = this.c;
        this.b = c0581Iy;
        this.d = c0581Iy;
        this.e++;
        d();
        return this.d;
    }

    public final C0581Iy b() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        C0581Iy c0581Iy = this.b;
        this.c = c0581Iy;
        this.d = c0581Iy;
        this.e--;
        e();
        return this.d;
    }

    public void d() {
        this.c = this.c.b();
    }

    public void e() {
        this.b = this.b.d();
    }

    public final boolean hasNext() {
        return this.c != null;
    }

    public final boolean hasPrevious() {
        return this.b != null;
    }

    public Object next() {
        return a();
    }

    public final int nextIndex() {
        return this.e;
    }

    public Object previous() {
        return b();
    }

    public final int previousIndex() {
        return this.e - 1;
    }

    public final void remove() {
        C0581Iy c0581Iy = this.d;
        if (c0581Iy == null) {
            g33.a();
            return;
        }
        if (c0581Iy == this.b) {
            this.e--;
        }
        this.b = c0581Iy;
        this.c = c0581Iy;
        e();
        d();
        this.f.remove(this.d.b);
        this.d = null;
    }
}
