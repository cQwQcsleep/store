package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* renamed from: j$.util.y, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public interface InterfaceC0229y extends D {
    @Override // java.util.Iterator, j$.util.InterfaceC0087k
    void forEachRemaining(Consumer consumer);

    void forEachRemaining(IntConsumer intConsumer);

    @Override // java.util.Iterator
    Integer next();

    int nextInt();
}
