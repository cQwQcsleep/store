package j$.util.stream;

import j$.util.C0083g;
import j$.util.C0090n;
import j$.util.Objects;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
import java.util.stream.Collector;

/* renamed from: j$.util.stream.j2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0143j2 extends AbstractC0100b implements Stream {
    @Override // j$.util.stream.Stream
    public final Stream sorted() {
        return new M2(this);
    }

    @Override // j$.util.stream.Stream
    public final Stream distinct() {
        return new C0160n(this, EnumC0129g3.m | EnumC0129g3.t, 0);
    }

    @Override // j$.util.stream.Stream
    public final C0090n min(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new j$.util.function.b(comparator, 1));
    }

    @Override // j$.util.stream.Stream
    public final C0090n findAny() {
        return (C0090n) D(J.d);
    }

    @Override // j$.util.stream.Stream
    public final C0090n findFirst() {
        return (C0090n) D(J.c);
    }

    @Override // j$.util.stream.Stream
    public final Stream sorted(Comparator comparator) {
        return new M2(this, comparator);
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return D(new G1(EnumC0134h3.REFERENCE, binaryOperator, biFunction, obj, 2));
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        Objects.requireNonNull(binaryOperator);
        return D(new G1(EnumC0134h3.REFERENCE, binaryOperator, binaryOperator, obj, 2));
    }

    public void forEach(Consumer consumer) {
        Objects.requireNonNull(consumer);
        D(new P(consumer, false));
    }

    public void forEachOrdered(Consumer consumer) {
        Objects.requireNonNull(consumer);
        D(new P(consumer, true));
    }

    @Override // j$.util.stream.Stream
    public final C0090n max(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new j$.util.function.b(comparator, 0));
    }

    @Override // j$.util.stream.AbstractC0100b
    final EnumC0134h3 I() {
        return EnumC0134h3.REFERENCE;
    }

    @Override // j$.util.stream.Stream
    public final C0090n reduce(BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return (C0090n) D(new E1(EnumC0134h3.REFERENCE, binaryOperator, 2));
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 F(AbstractC0100b abstractC0100b, j$.util.U u, boolean z, IntFunction intFunction) {
        return A0.E(abstractC0100b, u, z, intFunction);
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U U(AbstractC0100b abstractC0100b, Supplier supplier, boolean z) {
        return new K3(abstractC0100b, supplier, z);
    }

    @Override // j$.util.stream.AbstractC0100b
    final boolean H(j$.util.U u, InterfaceC0182r2 interfaceC0182r2) {
        boolean zO;
        do {
            zO = interfaceC0182r2.o();
            if (zO) {
                break;
            }
        } while (u.tryAdvance(interfaceC0182r2));
        return zO;
    }

    @Override // j$.util.stream.AbstractC0100b
    final E0 N(long j, IntFunction intFunction) {
        return A0.D(j, intFunction);
    }

    @Override // j$.util.stream.InterfaceC0130h, j$.util.stream.E
    public final Iterator iterator() {
        return j$.util.i0.i(spliterator());
    }

    @Override // j$.util.stream.Stream
    public final Stream filter(Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new C0189t(this, EnumC0129g3.t, predicate, 4);
    }

    @Override // j$.util.stream.Stream
    public final Stream map(Function function) {
        Objects.requireNonNull(function);
        return new C0189t(this, EnumC0129g3.p | EnumC0129g3.n, function, 5);
    }

    @Override // j$.util.stream.Stream
    public final InterfaceC0116e0 mapToInt(ToIntFunction toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new W(this, EnumC0129g3.p | EnumC0129g3.n, toIntFunction, 2);
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return D(new G1(EnumC0134h3.REFERENCE, biConsumer2, biConsumer, supplier, 3));
    }

    @Override // j$.util.stream.Stream
    public final InterfaceC0171p0 mapToLong(ToLongFunction toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new C0141j0(this, EnumC0129g3.p | EnumC0129g3.n, toLongFunction, 3);
    }

    @Override // j$.util.stream.Stream
    public final E mapToDouble(ToDoubleFunction toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new C0214y(this, EnumC0129g3.p | EnumC0129g3.n, toDoubleFunction, 2);
    }

    @Override // j$.util.stream.Stream
    public final long count() {
        return ((Long) D(new I1(2))).longValue();
    }

    @Override // j$.util.stream.Stream
    public final Stream e(C0095a c0095a) {
        Objects.requireNonNull(c0095a);
        return new C0189t(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, c0095a, 6);
    }

    @Override // j$.util.stream.Stream
    public final InterfaceC0116e0 t(C0095a c0095a) {
        Objects.requireNonNull(c0095a);
        return new W(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, c0095a, 3);
    }

    @Override // j$.util.stream.Stream
    public final E z(C0095a c0095a) {
        Objects.requireNonNull(c0095a);
        return new C0214y(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, c0095a, 3);
    }

    @Override // j$.util.stream.Stream
    public final InterfaceC0171p0 o(C0095a c0095a) {
        Objects.requireNonNull(c0095a);
        return new C0141j0(this, EnumC0129g3.p | EnumC0129g3.n | EnumC0129g3.t, c0095a, 2);
    }

    @Override // j$.util.stream.Stream
    public final Stream peek(Consumer consumer) {
        Objects.requireNonNull(consumer);
        return new C0189t(this, consumer);
    }

    @Override // j$.util.stream.Stream
    public final Stream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return A0.d0(this, 0L, j);
    }

    @Override // j$.util.stream.Stream
    public final Stream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : A0.d0(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.Stream
    public final Stream takeWhile(Predicate predicate) {
        int i = o4.a;
        Objects.requireNonNull(predicate);
        return new Q3(this, o4.a, predicate);
    }

    @Override // j$.util.stream.Stream
    public final Stream dropWhile(Predicate predicate) {
        int i = o4.a;
        Objects.requireNonNull(predicate);
        return new S3(this, o4.b, predicate);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray(IntFunction intFunction) {
        return A0.N(E(intFunction), intFunction).p(intFunction);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray() {
        return toArray(new C0118e2(0));
    }

    @Override // j$.util.stream.Stream
    public final boolean anyMatch(Predicate predicate) {
        return ((Boolean) D(A0.c0(EnumC0210x0.ANY, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean allMatch(Predicate predicate) {
        return ((Boolean) D(A0.c0(EnumC0210x0.ALL, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean noneMatch(Predicate predicate) {
        return ((Boolean) D(A0.c0(EnumC0210x0.NONE, predicate))).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x014b  */
    @Override // j$.util.stream.Stream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object h(C0140j c0140j) {
        Object objD;
        if (isParallel()) {
            Set<Collector.Characteristics> setCharacteristics = c0140j.a.characteristics();
            if (setCharacteristics != null && !setCharacteristics.isEmpty()) {
                HashSet hashSet = new HashSet();
                Collector.Characteristics next = setCharacteristics.iterator().next();
                if (next instanceof EnumC0135i) {
                    Iterator<Collector.Characteristics> it = setCharacteristics.iterator();
                    while (it.hasNext()) {
                        try {
                            EnumC0135i enumC0135i = (EnumC0135i) it.next();
                            hashSet.add(enumC0135i == null ? null : enumC0135i == EnumC0135i.CONCURRENT ? Collector.Characteristics.CONCURRENT : enumC0135i == EnumC0135i.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH);
                        } catch (ClassCastException e) {
                            C0083g.a("java.util.stream.Collector.Characteristics", e);
                            throw null;
                        }
                    }
                } else {
                    if (!(next instanceof Collector.Characteristics)) {
                        C0083g.a("java.util.stream.Collector.Characteristics", next.getClass());
                        throw null;
                    }
                    Iterator<Collector.Characteristics> it2 = setCharacteristics.iterator();
                    while (it2.hasNext()) {
                        try {
                            Collector.Characteristics next2 = it2.next();
                            hashSet.add(next2 == null ? null : next2 == Collector.Characteristics.CONCURRENT ? EnumC0135i.CONCURRENT : next2 == Collector.Characteristics.UNORDERED ? EnumC0135i.UNORDERED : EnumC0135i.IDENTITY_FINISH);
                        } catch (ClassCastException e2) {
                            C0083g.a("java.util.stream.Collector.Characteristics", e2);
                            throw null;
                        }
                    }
                }
                setCharacteristics = hashSet;
            }
            if (setCharacteristics.contains(EnumC0135i.CONCURRENT)) {
                if (L()) {
                    Set<Collector.Characteristics> setCharacteristics2 = c0140j.a.characteristics();
                    if (setCharacteristics2 != null && !setCharacteristics2.isEmpty()) {
                        HashSet hashSet2 = new HashSet();
                        Collector.Characteristics next3 = setCharacteristics2.iterator().next();
                        if (next3 instanceof EnumC0135i) {
                            Iterator<Collector.Characteristics> it3 = setCharacteristics2.iterator();
                            while (it3.hasNext()) {
                                try {
                                    EnumC0135i enumC0135i2 = (EnumC0135i) it3.next();
                                    hashSet2.add(enumC0135i2 == null ? null : enumC0135i2 == EnumC0135i.CONCURRENT ? Collector.Characteristics.CONCURRENT : enumC0135i2 == EnumC0135i.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH);
                                } catch (ClassCastException e3) {
                                    C0083g.a("java.util.stream.Collector.Characteristics", e3);
                                    throw null;
                                }
                            }
                        } else {
                            if (!(next3 instanceof Collector.Characteristics)) {
                                C0083g.a("java.util.stream.Collector.Characteristics", next3.getClass());
                                throw null;
                            }
                            Iterator<Collector.Characteristics> it4 = setCharacteristics2.iterator();
                            while (it4.hasNext()) {
                                try {
                                    Collector.Characteristics next4 = it4.next();
                                    hashSet2.add(next4 == null ? null : next4 == Collector.Characteristics.CONCURRENT ? EnumC0135i.CONCURRENT : next4 == Collector.Characteristics.UNORDERED ? EnumC0135i.UNORDERED : EnumC0135i.IDENTITY_FINISH);
                                } catch (ClassCastException e4) {
                                    C0083g.a("java.util.stream.Collector.Characteristics", e4);
                                    throw null;
                                }
                            }
                        }
                        setCharacteristics2 = hashSet2;
                    }
                    if (setCharacteristics2.contains(EnumC0135i.UNORDERED)) {
                    }
                }
                objD = c0140j.a.supplier().get();
                forEach(new C0180r0(3, c0140j.a.accumulator(), objD));
            }
        } else {
            objD = D(new N1(EnumC0134h3.REFERENCE, c0140j.a.combiner(), c0140j.a.accumulator(), ((C0140j) Objects.requireNonNull(c0140j)).a.supplier(), c0140j));
        }
        Set<Collector.Characteristics> setCharacteristics3 = c0140j.a.characteristics();
        if (setCharacteristics3 != null && !setCharacteristics3.isEmpty()) {
            HashSet hashSet3 = new HashSet();
            Collector.Characteristics next5 = setCharacteristics3.iterator().next();
            if (next5 instanceof EnumC0135i) {
                Iterator<Collector.Characteristics> it5 = setCharacteristics3.iterator();
                while (it5.hasNext()) {
                    try {
                        EnumC0135i enumC0135i3 = (EnumC0135i) it5.next();
                        hashSet3.add(enumC0135i3 == null ? null : enumC0135i3 == EnumC0135i.CONCURRENT ? Collector.Characteristics.CONCURRENT : enumC0135i3 == EnumC0135i.UNORDERED ? Collector.Characteristics.UNORDERED : Collector.Characteristics.IDENTITY_FINISH);
                    } catch (ClassCastException e5) {
                        C0083g.a("java.util.stream.Collector.Characteristics", e5);
                        throw null;
                    }
                }
            } else {
                if (!(next5 instanceof Collector.Characteristics)) {
                    C0083g.a("java.util.stream.Collector.Characteristics", next5.getClass());
                    throw null;
                }
                Iterator<Collector.Characteristics> it6 = setCharacteristics3.iterator();
                while (it6.hasNext()) {
                    try {
                        Collector.Characteristics next6 = it6.next();
                        hashSet3.add(next6 == null ? null : next6 == Collector.Characteristics.CONCURRENT ? EnumC0135i.CONCURRENT : next6 == Collector.Characteristics.UNORDERED ? EnumC0135i.UNORDERED : EnumC0135i.IDENTITY_FINISH);
                    } catch (ClassCastException e6) {
                        C0083g.a("java.util.stream.Collector.Characteristics", e6);
                        throw null;
                    }
                }
            }
            setCharacteristics3 = hashSet3;
        }
        return setCharacteristics3.contains(EnumC0135i.IDENTITY_FINISH) ? objD : c0140j.a.finisher().apply(objD);
    }
}
