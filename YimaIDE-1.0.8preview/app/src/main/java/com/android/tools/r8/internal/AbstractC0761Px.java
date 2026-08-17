package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Px, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0761Px {
    public C0450Dx b;
    public C0450Dx c;
    public C0450Dx d;
    public int e = 0;
    public final /* synthetic */ C0813Rx f;

    public AbstractC0761Px(C0813Rx c0813Rx) {
        this.f = c0813Rx;
        this.c = c0813Rx.e;
    }

    public final C0450Dx a() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        C0450Dx c0450Dx = this.c;
        this.b = c0450Dx;
        this.d = c0450Dx;
        this.e++;
        d();
        return this.d;
    }

    public final C0450Dx b() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        C0450Dx c0450Dx = this.b;
        this.c = c0450Dx;
        this.d = c0450Dx;
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
        C0450Dx c0450Dx = this.d;
        if (c0450Dx == null) {
            g33.a();
            return;
        }
        if (c0450Dx == this.b) {
            this.e--;
        }
        this.b = c0450Dx;
        this.c = c0450Dx;
        e();
        d();
        this.f.remove(this.d.b);
        this.d = null;
    }
}
