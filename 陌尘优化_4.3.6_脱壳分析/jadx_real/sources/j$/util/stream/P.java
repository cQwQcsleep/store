package j$.util.stream;

import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class P extends Q {
    final Consumer b;

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.M3
    public final Object b(AbstractC0100b abstractC0100b, j$.util.U u) {
        abstractC0100b.V(u, this);
        return null;
    }

    @Override // j$.util.stream.M3
    public final /* bridge */ /* synthetic */ Object c(AbstractC0100b abstractC0100b, j$.util.U u) {
        e(abstractC0100b, u);
        return null;
    }

    P(Consumer consumer, boolean z) {
        super(z);
        this.b = consumer;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.accept(obj);
    }
}
