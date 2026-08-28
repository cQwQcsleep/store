package j$.util.stream;

import java.util.function.IntFunction;

/* renamed from: j$.util.stream.g1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0127g1 implements M0 {
    @Override // j$.util.stream.M0
    public final long count() {
        return 0L;
    }

    public final void d(Object obj, int i) {
    }

    public final void f(Object obj) {
    }

    @Override // j$.util.stream.M0
    public /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.w(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ int r() {
        return 0;
    }

    @Override // j$.util.stream.M0
    public M0 b(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.M0
    public final Object[] p(IntFunction intFunction) {
        return (Object[]) intFunction.apply(0);
    }
}
