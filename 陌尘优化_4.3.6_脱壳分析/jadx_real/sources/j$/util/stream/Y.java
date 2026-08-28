package j$.util.stream;

import j$.util.Objects;
import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Y extends AbstractC0153l2 {
    boolean b;
    U c;
    final /* synthetic */ W d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Y(W w, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = w;
        InterfaceC0182r2 interfaceC0182r22 = this.a;
        Objects.requireNonNull(interfaceC0182r22);
        this.c = new U(interfaceC0182r22);
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(-1L);
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) throws Exception {
        InterfaceC0116e0 interfaceC0116e0 = (InterfaceC0116e0) ((S0) this.d.n).apply(i);
        if (interfaceC0116e0 != null) {
            try {
                boolean z = this.b;
                U u = this.c;
                if (!z) {
                    interfaceC0116e0.sequential().forEach(u);
                } else {
                    j$.util.K kSpliterator = interfaceC0116e0.sequential().spliterator();
                    while (!this.a.o() && kSpliterator.tryAdvance((IntConsumer) u)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    interfaceC0116e0.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (interfaceC0116e0 != null) {
            interfaceC0116e0.close();
        }
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        this.b = true;
        return this.a.o();
    }
}
