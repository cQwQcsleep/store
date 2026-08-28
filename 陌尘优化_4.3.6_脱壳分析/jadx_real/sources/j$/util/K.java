package j$.util;

import java.util.function.IntConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface K extends Q {
    void forEachRemaining(IntConsumer intConsumer);

    boolean tryAdvance(IntConsumer intConsumer);

    @Override // j$.util.Q, j$.util.U
    K trySplit();
}
