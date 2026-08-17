package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Oc0 implements Iterator {
    public int b = -1;
    public boolean c;
    public Iterator d;
    public final /* synthetic */ Rc0 e;

    public Oc0(Rc0 rc0) {
        this.e = rc0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b + 1 >= this.e.c.size()) {
            if (this.e.d.isEmpty()) {
                return false;
            }
            if (this.d == null) {
                this.d = this.e.d.entrySet().iterator();
            }
            if (!this.d.hasNext()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        this.c = true;
        int i = this.b + 1;
        this.b = i;
        if (i < this.e.c.size()) {
            return (Map.Entry) this.e.c.get(this.b);
        }
        if (this.d == null) {
            this.d = this.e.d.entrySet().iterator();
        }
        return (Map.Entry) this.d.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.c) {
            k2d.a("remove() was called before next()");
            return;
        }
        this.c = false;
        Rc0 rc0 = this.e;
        int i = Rc0.h;
        rc0.e();
        if (this.b >= this.e.c.size()) {
            if (this.d == null) {
                this.d = this.e.d.entrySet().iterator();
            }
            this.d.remove();
            return;
        }
        Rc0 rc1 = this.e;
        int i2 = this.b;
        this.b = i2 - 1;
        rc1.e();
        Object obj = ((Mc0) rc1.c.remove(i2)).c;
        if (rc1.d.isEmpty()) {
            return;
        }
        Iterator it = rc1.j().entrySet().iterator();
        rc1.c.add(new Mc0(rc1, (Map.Entry) it.next()));
        it.remove();
    }
}
