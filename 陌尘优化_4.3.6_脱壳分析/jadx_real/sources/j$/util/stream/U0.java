package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class U0 extends X0 implements G0 {
    @Override // j$.util.stream.M0
    public final /* synthetic */ void forEach(Consumer consumer) {
        A0.q(this, consumer);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.t(this, j, j2);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ void j(Object[] objArr, int i) {
        A0.n(this, (Double[]) objArr, i);
    }

    @Override // j$.util.stream.L0
    public final Object c(int i) {
        return new double[i];
    }

    @Override // j$.util.stream.M0
    public final j$.util.Q spliterator() {
        return new C0152l1(this);
    }

    @Override // j$.util.stream.M0
    public final j$.util.U spliterator() {
        return new C0152l1(this);
    }
}
