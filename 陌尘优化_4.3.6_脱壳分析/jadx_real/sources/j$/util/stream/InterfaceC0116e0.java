package j$.util.stream;

import j$.util.C0086j;
import j$.util.C0091o;
import j$.util.C0092p;
import j$.util.InterfaceC0229y;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.e0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public interface InterfaceC0116e0 extends InterfaceC0130h {
    InterfaceC0116e0 a();

    E asDoubleStream();

    InterfaceC0171p0 asLongStream();

    C0091o average();

    InterfaceC0116e0 b();

    Stream boxed();

    InterfaceC0116e0 c();

    Object collect(Supplier supplier, ObjIntConsumer objIntConsumer, BiConsumer biConsumer);

    long count();

    InterfaceC0116e0 d();

    InterfaceC0116e0 distinct();

    E f();

    C0092p findAny();

    C0092p findFirst();

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    boolean g();

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    InterfaceC0229y iterator();

    InterfaceC0116e0 limit(long j);

    InterfaceC0171p0 m();

    Stream mapToObj(IntFunction intFunction);

    C0092p max();

    C0092p min();

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    InterfaceC0116e0 parallel();

    InterfaceC0116e0 peek(IntConsumer intConsumer);

    InterfaceC0116e0 q(S0 s0);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    C0092p reduce(IntBinaryOperator intBinaryOperator);

    boolean s();

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    InterfaceC0116e0 sequential();

    InterfaceC0116e0 skip(long j);

    InterfaceC0116e0 sorted();

    @Override // j$.util.stream.InterfaceC0130h
    j$.util.K spliterator();

    int sum();

    C0086j summaryStatistics();

    int[] toArray();

    boolean v();
}
