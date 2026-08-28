package j$.util.stream;

import j$.util.C0085i;
import j$.util.C0091o;
import j$.util.InterfaceC0225u;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface E extends InterfaceC0130h {
    E a();

    C0091o average();

    E b();

    Stream boxed();

    E c();

    Object collect(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BiConsumer biConsumer);

    long count();

    E d();

    E distinct();

    E e(C0095a c0095a);

    C0091o findAny();

    C0091o findFirst();

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    boolean i();

    InterfaceC0225u iterator();

    InterfaceC0171p0 j();

    E limit(long j);

    Stream mapToObj(DoubleFunction doubleFunction);

    C0091o max();

    C0091o min();

    boolean p();

    E parallel();

    E peek(DoubleConsumer doubleConsumer);

    double reduce(double d, DoubleBinaryOperator doubleBinaryOperator);

    C0091o reduce(DoubleBinaryOperator doubleBinaryOperator);

    E sequential();

    E skip(long j);

    E sorted();

    @Override // j$.util.stream.InterfaceC0130h
    j$.util.H spliterator();

    double sum();

    C0085i summaryStatistics();

    double[] toArray();

    InterfaceC0116e0 u();

    boolean y();
}
