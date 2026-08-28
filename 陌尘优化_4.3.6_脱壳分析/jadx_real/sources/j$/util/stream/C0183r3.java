package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.r3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0183r3 extends AbstractC0139i3 implements j$.util.H {
    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.a(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.h(this, consumer);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final AbstractC0139i3 e(j$.util.U u) {
        return new C0183r3(this.b, u, this.a);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final void d() {
        U2 u2 = new U2();
        this.h = u2;
        Objects.requireNonNull(u2);
        this.e = this.b.W(new C0179q3(u2, 0));
        this.f = new C0095a(this, 2);
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.H trySplit() {
        return (j$.util.H) super.trySplit();
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.Q trySplit() {
        return (j$.util.H) super.trySplit();
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.U trySplit() {
        return (j$.util.H) super.trySplit();
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        double d;
        Objects.requireNonNull(doubleConsumer);
        boolean zA = a();
        if (zA) {
            U2 u2 = (U2) this.h;
            long j = this.g;
            int iU = u2.u(j);
            if (u2.c == 0 && iU == 0) {
                d = ((double[]) u2.e)[(int) j];
            } else {
                d = ((double[][]) u2.f)[iU][(int) (j - u2.d[iU])];
            }
            doubleConsumer.accept(d);
        }
        return zA;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(doubleConsumer);
            c();
            Objects.requireNonNull(doubleConsumer);
            C0179q3 c0179q3 = new C0179q3(doubleConsumer, 1);
            this.b.V(this.d, c0179q3);
            this.i = true;
            return;
        }
        while (tryAdvance(doubleConsumer)) {
        }
    }
}
