package j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: j$.util.stream.c, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0105c extends AbstractC0115e {
    protected final AtomicReference h;
    protected volatile boolean i;

    protected abstract Object j();

    protected AbstractC0105c(AbstractC0100b abstractC0100b, j$.util.U u) {
        super(abstractC0100b, u);
        this.h = new AtomicReference(null);
    }

    protected AbstractC0105c(AbstractC0105c abstractC0105c, j$.util.U u) {
        super(abstractC0105c, u);
        this.h = abstractC0105c.h;
    }

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter
    public final void compute() {
        Object objJ;
        j$.util.U uTrySplit;
        j$.util.U u = this.b;
        long jEstimateSize = u.estimateSize();
        long jG = this.c;
        if (jG == 0) {
            jG = AbstractC0115e.g(jEstimateSize);
            this.c = jG;
        }
        AtomicReference atomicReference = this.h;
        boolean z = false;
        AbstractC0105c abstractC0105c = this;
        while (true) {
            objJ = atomicReference.get();
            if (objJ != null) {
                break;
            }
            boolean z2 = abstractC0105c.i;
            if (!z2) {
                CountedCompleter<?> completer = abstractC0105c.getCompleter();
                while (true) {
                    AbstractC0105c abstractC0105c2 = (AbstractC0105c) ((AbstractC0115e) completer);
                    if (z2 || abstractC0105c2 == null) {
                        break;
                    }
                    z2 = abstractC0105c2.i;
                    completer = abstractC0105c2.getCompleter();
                }
            }
            if (z2) {
                objJ = abstractC0105c.j();
                break;
            }
            if (jEstimateSize <= jG || (uTrySplit = u.trySplit()) == null) {
                break;
            }
            AbstractC0105c abstractC0105c3 = (AbstractC0105c) abstractC0105c.e(uTrySplit);
            abstractC0105c.d = abstractC0105c3;
            AbstractC0105c abstractC0105c4 = (AbstractC0105c) abstractC0105c.e(u);
            abstractC0105c.e = abstractC0105c4;
            abstractC0105c.setPendingCount(1);
            if (z) {
                u = uTrySplit;
                abstractC0105c = abstractC0105c3;
                abstractC0105c3 = abstractC0105c4;
            } else {
                abstractC0105c = abstractC0105c4;
            }
            z = !z;
            abstractC0105c3.fork();
            jEstimateSize = u.estimateSize();
        }
        objJ = abstractC0105c.a();
        abstractC0105c.f(objJ);
        abstractC0105c.tryComplete();
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final void f(Object obj) {
        if (!d()) {
            super.f(obj);
        } else if (obj != null) {
            AtomicReference atomicReference = this.h;
            while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
            }
        }
    }

    @Override // j$.util.stream.AbstractC0115e, java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final Object getRawResult() {
        return c();
    }

    @Override // j$.util.stream.AbstractC0115e
    public final Object c() {
        if (d()) {
            Object obj = this.h.get();
            return obj == null ? j() : obj;
        }
        return super.c();
    }

    protected void h() {
        this.i = true;
    }

    protected final void i() {
        AbstractC0105c abstractC0105c = this;
        for (AbstractC0105c abstractC0105c2 = (AbstractC0105c) ((AbstractC0115e) getCompleter()); abstractC0105c2 != null; abstractC0105c2 = (AbstractC0105c) ((AbstractC0115e) abstractC0105c2.getCompleter())) {
            if (abstractC0105c2.d == abstractC0105c) {
                AbstractC0105c abstractC0105c3 = (AbstractC0105c) abstractC0105c2.e;
                if (!abstractC0105c3.i) {
                    abstractC0105c3.h();
                }
            }
            abstractC0105c = abstractC0105c2;
        }
    }
}
