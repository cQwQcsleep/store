package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class V0 extends X0 implements I0 {
    @Override // j$.util.stream.M0
    public final /* synthetic */ void forEach(Consumer consumer) {
        A0.r(this, consumer);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.u(this, j, j2);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ void j(Object[] objArr, int i) {
        A0.o(this, (Integer[]) objArr, i);
    }

    @Override // j$.util.stream.L0
    public final Object c(int i) {
        return new int[i];
    }

    @Override // j$.util.stream.M0
    public final j$.util.Q spliterator() {
        return new C0157m1(this);
    }

    @Override // j$.util.stream.M0
    public final j$.util.U spliterator() {
        return new C0157m1(this);
    }
}
