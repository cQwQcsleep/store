package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Va0 implements Iterator, InterfaceC0952Xg {
    public int b;
    public Object c;
    public InterfaceC0952Xg d;

    @Override // com.android.tools.r8.internal.InterfaceC0952Xg
    public final void a(Object obj) throws Throwable {
        AbstractC2579s90.a(obj);
        this.b = 4;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0952Xg
    public final C0750Pm getContext() {
        return C0750Pm.b;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i;
        while (true) {
            i = this.b;
            if (i != 0) {
                break;
            }
            this.b = 5;
            InterfaceC0952Xg interfaceC0952Xg = this.d;
            KB.a(interfaceC0952Xg);
            this.d = null;
            interfaceC0952Xg.a(C2028lk0.a);
        }
        if (i == 1) {
            KB.a((Object) null);
            throw null;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        if (i == 4) {
            return false;
        }
        if (i == 4) {
            throw new NoSuchElementException();
        }
        if (i == 5) {
            throw new IllegalStateException("Iterator has failed.");
        }
        throw new IllegalStateException("Unexpected state of the iterator: " + this.b);
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.b;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            z0e.a();
            return null;
        }
        if (i == 2) {
            this.b = 1;
            KB.a((Object) null);
            throw null;
        }
        if (i == 3) {
            this.b = 0;
            Object obj = this.c;
            this.c = null;
            return obj;
        }
        if (i == 4) {
            throw new NoSuchElementException();
        }
        if (i == 5) {
            throw new IllegalStateException("Iterator has failed.");
        }
        throw new IllegalStateException("Unexpected state of the iterator: " + this.b);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
