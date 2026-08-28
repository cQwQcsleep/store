package j$.util.stream;

import j$.util.Objects;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.i0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0136i0 extends AbstractC0158m2 {
    boolean b;
    C0121f0 c;
    final /* synthetic */ C0141j0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0136i0(C0141j0 c0141j0, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0141j0;
        InterfaceC0182r2 interfaceC0182r22 = this.a;
        Objects.requireNonNull(interfaceC0182r22);
        this.c = new C0121f0(interfaceC0182r22);
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(-1L);
    }

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) throws Exception {
        InterfaceC0171p0 interfaceC0171p0 = (InterfaceC0171p0) ((C0095a) this.d.n).apply(j);
        if (interfaceC0171p0 != null) {
            try {
                boolean z = this.b;
                C0121f0 c0121f0 = this.c;
                if (!z) {
                    interfaceC0171p0.sequential().forEach(c0121f0);
                } else {
                    j$.util.N nSpliterator = interfaceC0171p0.sequential().spliterator();
                    while (!this.a.o() && nSpliterator.tryAdvance((LongConsumer) c0121f0)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    interfaceC0171p0.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (interfaceC0171p0 != null) {
            interfaceC0171p0.close();
        }
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        this.b = true;
        return this.a.o();
    }
}
