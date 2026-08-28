package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
interface M0 {
    M0 b(int i);

    long count();

    void forEach(Consumer consumer);

    M0 i(long j, long j2, IntFunction intFunction);

    void j(Object[] objArr, int i);

    Object[] p(IntFunction intFunction);

    int r();

    j$.util.U spliterator();
}
