package com.android.tools.r8.internal;

import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KC implements Iterator {
    public Iterator b;
    public Iterator c = JC.f;
    public Iterator d;
    public ArrayDeque e;

    public KC(Iterator it) {
        this.d = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Iterator it;
        while (true) {
            Iterator it2 = this.c;
            it2.getClass();
            if (it2.hasNext()) {
                return true;
            }
            while (true) {
                Iterator it3 = this.d;
                if (it3 != null && it3.hasNext()) {
                    it = this.d;
                    break;
                }
                ArrayDeque arrayDeque = this.e;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    it = null;
                    break;
                }
                this.d = (Iterator) this.e.removeFirst();
            }
            this.d = it;
            if (it == null) {
                return false;
            }
            Iterator it4 = (Iterator) it.next();
            this.c = it4;
            if (it4 instanceof KC) {
                KC kc = (KC) it4;
                this.c = kc.c;
                if (this.e == null) {
                    this.e = new ArrayDeque();
                }
                this.e.addFirst(this.d);
                if (kc.e != null) {
                    while (!kc.e.isEmpty()) {
                        this.e.addFirst((Iterator) kc.e.removeLast());
                    }
                }
                this.d = kc.d;
            }
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        Iterator it = this.c;
        this.b = it;
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        Iterator it = this.b;
        if (it == null) {
            k2d.a("no calls to next() since the last call to remove()");
        } else {
            it.remove();
            this.b = null;
        }
    }
}
