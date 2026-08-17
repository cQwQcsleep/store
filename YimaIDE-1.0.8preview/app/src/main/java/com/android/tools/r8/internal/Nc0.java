package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Nc0 implements Iterator {
    public int b = -1;
    public boolean c;
    public Iterator d;
    public final /* synthetic */ Dc0 e;

    public Nc0(Dc0 dc0) {
        this.e = dc0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b + 1 >= this.e.c.size()) {
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
        Dc0 dc0 = this.e;
        int i = Dc0.g;
        dc0.e();
        if (this.b >= this.e.c.size()) {
            if (this.d == null) {
                this.d = this.e.d.entrySet().iterator();
            }
            this.d.remove();
            return;
        }
        Dc0 dc1 = this.e;
        int i2 = this.b;
        this.b = i2 - 1;
        dc1.e();
        Object obj = ((Lc0) dc1.c.remove(i2)).c;
        if (dc1.d.isEmpty()) {
            return;
        }
        dc1.e();
        if (dc1.d.isEmpty() && !(dc1.d instanceof TreeMap)) {
            dc1.d = new TreeMap();
        }
        Iterator it = ((SortedMap) dc1.d).entrySet().iterator();
        dc1.c.add(new Lc0(dc1, (Map.Entry) it.next()));
        it.remove();
    }
}
