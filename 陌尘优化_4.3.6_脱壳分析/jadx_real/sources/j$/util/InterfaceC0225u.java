package j$.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.u, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public interface InterfaceC0225u extends D {
    @Override // java.util.Iterator, j$.util.InterfaceC0087k
    void forEachRemaining(Consumer consumer);

    void forEachRemaining(DoubleConsumer doubleConsumer);

    @Override // java.util.Iterator
    Double next();

    double nextDouble();
}
