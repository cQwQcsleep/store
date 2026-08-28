package j$.util;

import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface C extends D {
    @Override // java.util.Iterator, j$.util.InterfaceC0087k
    void forEachRemaining(Consumer consumer);

    void forEachRemaining(LongConsumer longConsumer);

    @Override // java.util.Iterator
    Long next();

    long nextLong();
}
