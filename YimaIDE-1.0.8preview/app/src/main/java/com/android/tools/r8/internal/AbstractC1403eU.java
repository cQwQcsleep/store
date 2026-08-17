package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1403eU {
    public ST b;
    public ST c;
    public ST d;
    public int e = 0;
    public final /* synthetic */ C1574gU f;

    public AbstractC1403eU(C1574gU c1574gU) {
        this.f = c1574gU;
        this.c = c1574gU.e;
    }

    public final ST a() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        ST st = this.c;
        this.b = st;
        this.d = st;
        this.e++;
        d();
        return this.d;
    }

    public final ST b() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        ST st = this.b;
        this.c = st;
        this.d = st;
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
        ST st = this.d;
        if (st == null) {
            g33.a();
            return;
        }
        if (st == this.b) {
            this.e--;
        }
        this.b = st;
        this.c = st;
        e();
        d();
        this.f.remove(this.d.b);
        this.d = null;
    }
}
