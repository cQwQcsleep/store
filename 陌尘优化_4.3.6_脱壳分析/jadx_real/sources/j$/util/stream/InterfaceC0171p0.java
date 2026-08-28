package j$.util.stream;

import j$.util.C0088l;
import j$.util.C0091o;
import j$.util.C0093q;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.p0, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public interface InterfaceC0171p0 extends InterfaceC0130h {
    InterfaceC0171p0 a();

    E asDoubleStream();

    C0091o average();

    InterfaceC0171p0 b();

    Stream boxed();

    InterfaceC0171p0 c();

    Object collect(Supplier supplier, ObjLongConsumer objLongConsumer, BiConsumer biConsumer);

    long count();

    InterfaceC0171p0 d();

    InterfaceC0171p0 distinct();

    InterfaceC0171p0 e(C0095a c0095a);

    C0093q findAny();

    C0093q findFirst();

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    j$.util.C iterator();

    E l();

    InterfaceC0171p0 limit(long j);

    Stream mapToObj(LongFunction longFunction);

    C0093q max();

    C0093q min();

    boolean n();

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    InterfaceC0171p0 parallel();

    InterfaceC0171p0 peek(LongConsumer longConsumer);

    boolean r();

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    C0093q reduce(LongBinaryOperator longBinaryOperator);

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    InterfaceC0171p0 sequential();

    InterfaceC0171p0 skip(long j);

    InterfaceC0171p0 sorted();

    @Override // j$.util.stream.InterfaceC0130h
    j$.util.N spliterator();

    long sum();

    C0088l summaryStatistics();

    long[] toArray();

    boolean w();

    InterfaceC0116e0 x();
}
