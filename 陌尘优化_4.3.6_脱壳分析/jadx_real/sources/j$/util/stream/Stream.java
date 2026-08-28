package j$.util.stream;

import j$.util.C0090n;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
public interface Stream<T> extends InterfaceC0130h {
    boolean allMatch(Predicate predicate);

    boolean anyMatch(Predicate<? super T> predicate);

    Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2);

    long count();

    Stream distinct();

    Stream dropWhile(Predicate predicate);

    Stream e(C0095a c0095a);

    Stream filter(Predicate predicate);

    C0090n findAny();

    C0090n findFirst();

    void forEach(Consumer consumer);

    void forEachOrdered(Consumer consumer);

    Object h(C0140j c0140j);

    Stream limit(long j);

    Stream map(Function function);

    E mapToDouble(ToDoubleFunction toDoubleFunction);

    InterfaceC0116e0 mapToInt(ToIntFunction toIntFunction);

    InterfaceC0171p0 mapToLong(ToLongFunction toLongFunction);

    C0090n max(Comparator comparator);

    C0090n min(Comparator comparator);

    boolean noneMatch(Predicate predicate);

    InterfaceC0171p0 o(C0095a c0095a);

    Stream peek(Consumer consumer);

    C0090n reduce(BinaryOperator binaryOperator);

    Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator);

    Object reduce(Object obj, BinaryOperator binaryOperator);

    Stream skip(long j);

    Stream sorted();

    Stream sorted(Comparator comparator);

    InterfaceC0116e0 t(C0095a c0095a);

    Stream takeWhile(Predicate predicate);

    Object[] toArray();

    Object[] toArray(IntFunction intFunction);

    E z(C0095a c0095a);
}
