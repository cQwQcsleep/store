package j$.util.stream;

import java.util.Iterator;

/* renamed from: j$.util.stream.h, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public interface InterfaceC0130h extends AutoCloseable {
    boolean isParallel();

    Iterator iterator();

    InterfaceC0130h onClose(Runnable runnable);

    InterfaceC0130h parallel();

    InterfaceC0130h sequential();

    j$.util.U spliterator();

    InterfaceC0130h unordered();
}
