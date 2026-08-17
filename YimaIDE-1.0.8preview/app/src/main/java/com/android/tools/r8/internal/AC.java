package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AC implements Iterator {
    public Object b;
    public final /* synthetic */ Iterator c;
    public final /* synthetic */ Predicate d;

    public AC(Iterator it, Predicate predicate) {
        Object next;
        this.c = it;
        this.d = predicate;
        while (this.c.hasNext()) {
            next = this.c.next();
            if (this.d.test(next)) {
                this.b = next;
            }
        }
        next = null;
        this.b = next;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object obj = this.b;
        Object obj2 = null;
        if (obj == null) {
            z0e.a();
            return null;
        }
        while (this.c.hasNext()) {
            Object next = this.c.next();
            if (this.d.test(next)) {
                obj2 = next;
                break;
            }
        }
        this.b = obj2;
        return obj;
    }
}
