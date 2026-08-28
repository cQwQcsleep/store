package j$.util.stream;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: j$.util.stream.z0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0220z0 extends AbstractC0105c {
    private final C0215y0 j;

    C0220z0(C0215y0 c0215y0, AbstractC0100b abstractC0100b, j$.util.U u) {
        super(abstractC0100b, u);
        this.j = c0215y0;
    }

    C0220z0(C0220z0 c0220z0, j$.util.U u) {
        super(c0220z0, u);
        this.j = c0220z0.j;
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final AbstractC0115e e(j$.util.U u) {
        return new C0220z0(this, u);
    }

    @Override // j$.util.stream.AbstractC0115e
    protected final Object a() {
        AbstractC0100b abstractC0100b = this.a;
        AbstractC0205w0 abstractC0205w0 = (AbstractC0205w0) this.j.b.get();
        abstractC0100b.V(this.b, abstractC0205w0);
        boolean z = abstractC0205w0.b;
        if (z == this.j.a.b) {
            Boolean boolValueOf = Boolean.valueOf(z);
            AtomicReference atomicReference = this.h;
            while (!atomicReference.compareAndSet(null, boolValueOf) && atomicReference.get() == null) {
            }
        }
        return null;
    }

    @Override // j$.util.stream.AbstractC0105c
    protected final Object j() {
        return Boolean.valueOf(!this.j.a.b);
    }
}
