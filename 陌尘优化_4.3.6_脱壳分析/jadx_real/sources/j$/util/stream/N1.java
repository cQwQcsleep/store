package j$.util.stream;

import j$.util.C0083g;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* loaded from: /workspace/unpacked/classes3.dex */
final class N1 extends A0 {
    final /* synthetic */ BinaryOperator h;
    final /* synthetic */ BiConsumer i;
    final /* synthetic */ Supplier j;
    final /* synthetic */ C0140j k;

    @Override // j$.util.stream.A0, j$.util.stream.M3
    public final int d() {
        Set<Collector.Characteristics> setCharacteristics = this.k.a.characteristics();
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
        if (setCharacteristics.contains(EnumC0135i.UNORDERED)) {
            return EnumC0129g3.r;
        }
        return 0;
    }

    @Override // j$.util.stream.A0
    public final W1 e0() {
        return new O1(this.j, this.i, this.h);
    }

    N1(EnumC0134h3 enumC0134h3, BinaryOperator binaryOperator, BiConsumer biConsumer, Supplier supplier, C0140j c0140j) {
        this.h = binaryOperator;
        this.i = biConsumer;
        this.j = supplier;
        this.k = c0140j;
    }
}
