package j$.util.stream;

import java.util.function.Consumer;

/* renamed from: j$.util.stream.h2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0133h2 extends AbstractC0143j2 {
    @Override // j$.util.stream.InterfaceC0130h
    public final InterfaceC0130h unordered() {
        return !L() ? this : new C0128g2(this, EnumC0129g3.r, 1);
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean Q() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.util.stream.AbstractC0143j2, j$.util.stream.Stream
    public final void forEach(Consumer consumer) {
        if (!isParallel()) {
            T().forEachRemaining(consumer);
        } else {
            super.forEach(consumer);
        }
    }

    @Override // j$.util.stream.AbstractC0143j2, j$.util.stream.Stream
    public final void forEachOrdered(Consumer consumer) {
        if (!isParallel()) {
            T().forEachRemaining(consumer);
        } else {
            super.forEachOrdered(consumer);
        }
    }
}
