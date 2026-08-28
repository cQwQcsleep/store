package j$.util.stream;

import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.z, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0219z extends B {
    @Override // j$.util.stream.InterfaceC0130h
    public final InterfaceC0130h unordered() {
        return !L() ? this : new C0194u(this, EnumC0129g3.r, 1);
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ E parallel() {
        parallel();
        return this;
    }

    @Override // j$.util.stream.AbstractC0100b, j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final /* bridge */ /* synthetic */ E sequential() {
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

    @Override // j$.util.stream.B, j$.util.stream.E
    public final void forEach(DoubleConsumer doubleConsumer) {
        if (!isParallel()) {
            B.Z(T()).forEachRemaining(doubleConsumer);
        } else {
            super.forEach(doubleConsumer);
        }
    }

    @Override // j$.util.stream.B, j$.util.stream.E
    public final void forEachOrdered(DoubleConsumer doubleConsumer) {
        if (!isParallel()) {
            B.Z(T()).forEachRemaining(doubleConsumer);
        } else {
            super.forEachOrdered(doubleConsumer);
        }
    }
}
