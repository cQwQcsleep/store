package j$.util.stream;

import j$.util.Objects;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.x, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0209x extends AbstractC0148k2 {
    boolean b;
    C0165o c;
    final /* synthetic */ C0214y d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0209x(C0214y c0214y, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0214y;
        InterfaceC0182r2 interfaceC0182r22 = this.a;
        Objects.requireNonNull(interfaceC0182r22);
        this.c = new C0165o(interfaceC0182r22);
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(-1L);
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) throws Exception {
        E e = (E) ((C0095a) this.d.n).apply(d);
        if (e != null) {
            try {
                boolean z = this.b;
                C0165o c0165o = this.c;
                if (!z) {
                    e.sequential().forEach(c0165o);
                } else {
                    j$.util.H hSpliterator = e.sequential().spliterator();
                    while (!this.a.o() && hSpliterator.tryAdvance((DoubleConsumer) c0165o)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    e.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (e != null) {
            e.close();
        }
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        this.b = true;
        return this.a.o();
    }
}
