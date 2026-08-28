package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: /workspace/unpacked/classes3.dex */
final class L extends AbstractC0105c {
    private final F j;
    private final boolean k;

    L(F f, boolean z, AbstractC0100b abstractC0100b, j$.util.U u) {
        super(abstractC0100b, u);
        this.k = z;
        this.j = f;
    }

    L(L l, j$.util.U u) {
        super(l, u);
        this.k = l.k;
        this.j = l.j;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final AbstractC0115e e(j$.util.U u) {
        return new L(this, u);
    }

    @Override // j$.util.stream.AbstractC0105c
    protected final Object j() {
        return this.j.b;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final Object a() {
        AbstractC0100b abstractC0100b = this.a;
        N3 n3 = (N3) this.j.d.get();
        abstractC0100b.V(this.b, n3);
        Object obj = n3.get();
        if (!this.k) {
            if (obj != null) {
                AtomicReference atomicReference = this.h;
                while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
                }
            }
            return null;
        }
        if (obj == null) {
            return null;
        }
        AbstractC0115e abstractC0115e = this;
        while (true) {
            if (abstractC0115e != null) {
                AbstractC0115e abstractC0115e2 = (AbstractC0115e) abstractC0115e.getCompleter();
                if (abstractC0115e2 != null && abstractC0115e2.d != abstractC0115e) {
                    i();
                    break;
                }
                abstractC0115e = abstractC0115e2;
            } else {
                AtomicReference atomicReference2 = this.h;
                while (!atomicReference2.compareAndSet(null, obj) && atomicReference2.get() == null) {
                }
            }
        }
        return obj;
    }

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        if (this.k) {
            L l = (L) this.d;
            L l2 = null;
            while (true) {
                if (l != l2) {
                    Object objC = l.c();
                    if (objC != null && this.j.c.test(objC)) {
                        f(objC);
                        AbstractC0115e abstractC0115e = this;
                        while (true) {
                            if (abstractC0115e != null) {
                                AbstractC0115e abstractC0115e2 = (AbstractC0115e) abstractC0115e.getCompleter();
                                if (abstractC0115e2 != null && abstractC0115e2.d != abstractC0115e) {
                                    i();
                                    break;
                                }
                                abstractC0115e = abstractC0115e2;
                            } else {
                                AtomicReference atomicReference = this.h;
                                while (!atomicReference.compareAndSet(null, objC) && atomicReference.get() == null) {
                                }
                            }
                        }
                    } else {
                        l2 = l;
                        l = (L) this.e;
                    }
                } else {
                    break;
                }
            }
        }
        super.onCompletion(countedCompleter);
    }
}
