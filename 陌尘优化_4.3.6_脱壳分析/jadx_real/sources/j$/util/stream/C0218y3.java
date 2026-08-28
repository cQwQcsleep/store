package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.y3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0218y3 extends AbstractC0223z3 implements j$.util.N {
    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.j(this, consumer);
    }

    @Override // j$.util.stream.B3
    protected final j$.util.U a(j$.util.U u, long j, long j2, long j3, long j4) {
        return new C0218y3((j$.util.N) u, j, j2, j3, j4);
    }

    @Override // j$.util.stream.AbstractC0223z3
    protected final Object b() {
        return new J0(1);
    }
}
