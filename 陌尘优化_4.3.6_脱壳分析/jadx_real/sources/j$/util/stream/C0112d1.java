package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.IntFunction;

/* renamed from: j$.util.stream.d1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0112d1 extends AbstractC0127g1 implements I0 {
    @Override // j$.util.stream.M0
    public final /* synthetic */ void forEach(Consumer consumer) {
        A0.r(this, consumer);
    }

    @Override // j$.util.stream.AbstractC0127g1, j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.u(this, j, j2);
    }

    @Override // j$.util.stream.AbstractC0127g1, j$.util.stream.M0
    public final /* bridge */ /* synthetic */ M0 b(int i) {
        b(i);
        throw null;
    }

    @Override // j$.util.stream.AbstractC0127g1, j$.util.stream.M0
    public final L0 b(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ void j(Object[] objArr, int i) {
        A0.o(this, (Integer[]) objArr, i);
    }

    @Override // j$.util.stream.M0
    public final j$.util.Q spliterator() {
        return j$.util.i0.c();
    }

    @Override // j$.util.stream.M0
    public final j$.util.U spliterator() {
        return j$.util.i0.c();
    }

    @Override // j$.util.stream.L0
    public final Object e() {
        return A0.e;
    }
}
