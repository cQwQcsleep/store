package j$.util;

import j$.util.stream.Stream;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* renamed from: j$.util.c, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public interface InterfaceC0079c {
    void forEach(Consumer consumer);

    boolean removeIf(Predicate predicate);

    U spliterator();

    Stream stream();
}
