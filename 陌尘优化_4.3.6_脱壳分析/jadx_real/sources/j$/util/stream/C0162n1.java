package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.n1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0162n1 extends AbstractC0167o1 implements j$.util.N {
    @Override // j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.j(this, consumer);
    }
}
