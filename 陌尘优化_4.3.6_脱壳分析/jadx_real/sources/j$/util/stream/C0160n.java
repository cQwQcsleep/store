package j$.util.stream;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;

/* renamed from: j$.util.stream.n, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0160n extends AbstractC0138i2 {
    static Q0 Y(AbstractC0100b abstractC0100b, j$.util.U u) {
        C0170p c0170p = new C0170p(19);
        C0170p c0170p2 = new C0170p(20);
        C0170p c0170p3 = new C0170p(21);
        Objects.requireNonNull(c0170p);
        Objects.requireNonNull(c0170p2);
        Objects.requireNonNull(c0170p3);
        return new Q0((Collection) new G1(EnumC0134h3.REFERENCE, c0170p3, c0170p2, c0170p, 3).c(abstractC0100b, u));
    }

    @Override // j$.util.stream.AbstractC0100b
    final M0 O(AbstractC0100b abstractC0100b, j$.util.U u, IntFunction intFunction) {
        if (EnumC0129g3.DISTINCT.n(abstractC0100b.K())) {
            return abstractC0100b.C(u, false, intFunction);
        }
        if (EnumC0129g3.ORDERED.n(abstractC0100b.K())) {
            return Y(abstractC0100b, u);
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        C0180r0 c0180r0 = new C0180r0(2, atomicBoolean, concurrentHashMap);
        Objects.requireNonNull(c0180r0);
        new P(c0180r0, false).e(abstractC0100b, u);
        Set setKeySet = concurrentHashMap.keySet();
        if (atomicBoolean.get()) {
            HashSet hashSet = new HashSet(setKeySet);
            hashSet.add(null);
            setKeySet = hashSet;
        }
        return new Q0(setKeySet);
    }

    @Override // j$.util.stream.AbstractC0100b
    final j$.util.U P(AbstractC0100b abstractC0100b, j$.util.U u) {
        if (EnumC0129g3.DISTINCT.n(abstractC0100b.K())) {
            return abstractC0100b.X(u);
        }
        if (EnumC0129g3.ORDERED.n(abstractC0100b.K())) {
            return Y(abstractC0100b, u).spliterator();
        }
        return new C0174p3(abstractC0100b.X(u));
    }

    @Override // j$.util.stream.AbstractC0100b
    final InterfaceC0182r2 R(int i, InterfaceC0182r2 interfaceC0182r2) {
        Objects.requireNonNull(interfaceC0182r2);
        if (EnumC0129g3.DISTINCT.n(i)) {
            return interfaceC0182r2;
        }
        if (EnumC0129g3.SORTED.n(i)) {
            return new C0150l(interfaceC0182r2);
        }
        return new C0155m(interfaceC0182r2);
    }
}
