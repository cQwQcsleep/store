package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.w3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0208w3 extends AbstractC0223z3 implements j$.util.H {
    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.a(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.h(this, consumer);
    }

    @Override // j$.util.stream.B3
    protected final j$.util.U a(j$.util.U u, long j, long j2, long j3, long j4) {
        return new C0208w3((j$.util.H) u, j, j2, j3, j4);
    }

    @Override // j$.util.stream.AbstractC0223z3
    protected final Object b() {
        return new F0(1);
    }
}
