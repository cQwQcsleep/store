package j$.util.stream;

import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Z extends AbstractC0101b0 {
    @Override // j$.util.stream.InterfaceC0130h
    public final InterfaceC0130h unordered() {
        return !L() ? this : new C0199v(this, EnumC0129g3.r, 2);
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ InterfaceC0116e0 parallel() {
        parallel();
        return this;
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ InterfaceC0116e0 sequential() {
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

    @Override // j$.util.stream.AbstractC0101b0, j$.util.stream.InterfaceC0116e0
    public final void forEach(IntConsumer intConsumer) {
        if (!isParallel()) {
            AbstractC0101b0.Z(T()).forEachRemaining(intConsumer);
        } else {
            super.forEach(intConsumer);
        }
    }

    @Override // j$.util.stream.AbstractC0101b0, j$.util.stream.InterfaceC0116e0
    public final void forEachOrdered(IntConsumer intConsumer) {
        if (!isParallel()) {
            AbstractC0101b0.Z(T()).forEachRemaining(intConsumer);
        } else {
            super.forEachOrdered(intConsumer);
        }
    }
}
