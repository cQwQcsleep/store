package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* renamed from: j$.util.stream.t3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0193t3 extends AbstractC0139i3 implements j$.util.K {
    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.b(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.i(this, consumer);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final AbstractC0139i3 e(j$.util.U u) {
        return new C0193t3(this.b, u, this.a);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final void d() {
        W2 w2 = new W2();
        this.h = w2;
        Objects.requireNonNull(w2);
        this.e = this.b.W(new C0188s3(w2, 0));
        this.f = new C0095a(this, 3);
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.K trySplit() {
        return (j$.util.K) super.trySplit();
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.Q trySplit() {
        return (j$.util.K) super.trySplit();
    }

    @Override // j$.util.stream.AbstractC0139i3, j$.util.U
    public final j$.util.U trySplit() {
        return (j$.util.K) super.trySplit();
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(IntConsumer intConsumer) {
        int i;
        Objects.requireNonNull(intConsumer);
        boolean zA = a();
        if (zA) {
            W2 w2 = (W2) this.h;
            long j = this.g;
            int iU = w2.u(j);
            if (w2.c == 0 && iU == 0) {
                i = ((int[]) w2.e)[(int) j];
            } else {
                i = ((int[][]) w2.f)[iU][(int) (j - w2.d[iU])];
            }
            intConsumer.accept(i);
        }
        return zA;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(IntConsumer intConsumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(intConsumer);
            c();
            Objects.requireNonNull(intConsumer);
            C0188s3 c0188s3 = new C0188s3(intConsumer, 1);
            this.b.V(this.d, c0188s3);
            this.i = true;
            return;
        }
        while (tryAdvance(intConsumer)) {
        }
    }
}
