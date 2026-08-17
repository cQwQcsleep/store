package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0685Mz {
    public C0374Az b;
    public C0374Az c;
    public C0374Az d;
    public int e = 0;
    public final /* synthetic */ C0737Oz f;

    public AbstractC0685Mz(C0737Oz c0737Oz) {
        this.f = c0737Oz;
        this.c = c0737Oz.e;
    }

    public final C0374Az a() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        C0374Az c0374Az = this.c;
        this.b = c0374Az;
        this.d = c0374Az;
        this.e++;
        d();
        return this.d;
    }

    public final C0374Az b() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        C0374Az c0374Az = this.b;
        this.c = c0374Az;
        this.d = c0374Az;
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
        C0374Az c0374Az = this.d;
        if (c0374Az == null) {
            g33.a();
            return;
        }
        if (c0374Az == this.b) {
            this.e--;
        }
        this.b = c0374Az;
        this.c = c0374Az;
        e();
        d();
        this.f.remove(this.d.b);
        this.d = null;
    }
}
