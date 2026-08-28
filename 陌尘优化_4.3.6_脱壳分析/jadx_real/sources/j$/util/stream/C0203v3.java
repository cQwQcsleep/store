package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.v3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0203v3 extends AbstractC0139i3 implements j$.util.N {
    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.j(this, consumer);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final AbstractC0139i3 e(j$.util.U u) {
        return new C0203v3(this.b, u, this.a);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final void d() {
        Y2 y2 = new Y2();
        this.h = y2;
        Objects.requireNonNull(y2);
        this.e = this.b.W(new C0198u3(y2, 0));
        this.f = new C0095a(this, 4);
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.N trySplit() {
        return (j$.util.N) super.trySplit();
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.Q trySplit() {
        return (j$.util.N) super.trySplit();
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.U trySplit() {
        return (j$.util.N) super.trySplit();
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(LongConsumer longConsumer) {
        long j;
        Objects.requireNonNull(longConsumer);
        boolean zA = a();
        if (zA) {
            Y2 y2 = (Y2) this.h;
            long j2 = this.g;
            int iU = y2.u(j2);
            if (y2.c == 0 && iU == 0) {
                j = ((long[]) y2.e)[(int) j2];
            } else {
                j = ((long[][]) y2.f)[iU][(int) (j2 - y2.d[iU])];
            }
            longConsumer.accept(j);
        }
        return zA;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(LongConsumer longConsumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(longConsumer);
            c();
            Objects.requireNonNull(longConsumer);
            C0198u3 c0198u3 = new C0198u3(longConsumer, 1);
            this.b.V(this.d, c0198u3);
            this.i = true;
            return;
        }
        while (tryAdvance(longConsumer)) {
        }
    }
}
