package j$.util.stream;

import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.k0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0146k0 extends AbstractC0156m0 {
    @Override // j$.util.stream.InterfaceC0130h
    public final InterfaceC0130h unordered() {
        return !L() ? this : new C0204w(this, EnumC0129g3.r, 4);
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ InterfaceC0171p0 parallel() {
        parallel();
        return this;
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ InterfaceC0171p0 sequential() {
        sequential();
        return this;
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h
    public final /* bridge */ /* synthetic */ j$.util.U spliterator() {
        return spliterator();
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean Q() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.stream.AbstractC0156m0, j$.util.stream.InterfaceC0171p0
    public final void forEach(LongConsumer longConsumer) {
        if (!isParallel()) {
            AbstractC0156m0.Z(T()).forEachRemaining(longConsumer);
        } else {
            super.forEach(longConsumer);
        }
    }

    @Override // j$.util.stream.AbstractC0156m0, j$.util.stream.InterfaceC0171p0
    public final void forEachOrdered(LongConsumer longConsumer) {
        if (!isParallel()) {
            AbstractC0156m0.Z(T()).forEachRemaining(longConsumer);
        } else {
            super.forEachOrdered(longConsumer);
        }
    }
}
