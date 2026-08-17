package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3240zy {
    public C2215ny b;
    public C2215ny c;
    public C2215ny d;
    public int e = 0;
    public final /* synthetic */ C0399By f;

    public AbstractC3240zy(C0399By c0399By) {
        this.f = c0399By;
        this.c = c0399By.e;
    }

    public final C2215ny a() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        C2215ny c2215ny = this.c;
        this.b = c2215ny;
        this.d = c2215ny;
        this.e++;
        d();
        return this.d;
    }

    public final C2215ny b() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        C2215ny c2215ny = this.b;
        this.c = c2215ny;
        this.d = c2215ny;
        this.e--;
        e();
        return this.d;
    }

    public void d() {
        this.c = this.c.c();
    }

    public void e() {
        this.b = this.b.e();
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
        C2215ny c2215ny = this.d;
        if (c2215ny == null) {
            g33.a();
            return;
        }
        if (c2215ny == this.b) {
            this.e--;
        }
        this.b = c2215ny;
        this.c = c2215ny;
        e();
        d();
        this.f.remove(this.d.b);
        this.d = null;
    }
}
