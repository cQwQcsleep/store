package j$.util.concurrent;

import j$.util.AbstractC0078b;
import j$.util.Collection$EL;
import j$.util.InterfaceC0079c;
import j$.util.T;
import j$.util.U;
import j$.util.stream.A0;
import j$.util.stream.C0114d3;
import j$.util.stream.Stream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class i extends b implements Set, InterfaceC0079c {
    private static final long serialVersionUID = 7249069246763182397L;

    @Override // java.util.Collection, j$.util.InterfaceC0079c
    public final /* synthetic */ boolean removeIf(Predicate predicate) {
        return AbstractC0078b.f(this, predicate);
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Spliterator spliterator() {
        return T.a(spliterator());
    }

    @Override // java.util.Collection, j$.util.InterfaceC0079c
    public final /* synthetic */ Stream stream() {
        return AbstractC0078b.g(this);
    }

    @Override // java.util.Collection
    public final /* synthetic */ java.util.stream.Stream stream() {
        return C0114d3.k(AbstractC0078b.g(this));
    }

    @Override // java.util.Collection
    public final Object[] toArray(IntFunction intFunction) {
        return toArray((Object[]) intFunction.apply(0));
    }

    @Override // java.util.Collection
    public final java.util.stream.Stream parallelStream() {
        return C0114d3.k(A0.f0(Collection$EL.b(this), true));
    }

    @Override // j$.util.concurrent.b, java.util.Collection
    public final boolean contains(Object obj) {
        return this.a.containsKey(obj);
    }

    @Override // j$.util.concurrent.b, java.util.Collection
    public final boolean remove(Object obj) {
        return this.a.remove(obj) != null;
    }

    @Override // j$.util.concurrent.b, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        ConcurrentHashMap concurrentHashMap = this.a;
        l[] lVarArr = concurrentHashMap.a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        return new h(lVarArr, length, length, concurrentHashMap, 0);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        Object it = iterator();
        int iHashCode = 0;
        while (((a) it).hasNext()) {
            iHashCode += ((h) it).next().hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        Set set;
        return (obj instanceof Set) && ((set = (Set) obj) == this || (containsAll(set) && set.containsAll(this)));
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set, j$.util.InterfaceC0079c
    public final U spliterator() {
        ConcurrentHashMap concurrentHashMap = this.a;
        long j = concurrentHashMap.j();
        l[] lVarArr = concurrentHashMap.a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        return new j(lVarArr, length, 0, length, j < 0 ? 0L : j, 0);
    }

    @Override // java.lang.Iterable, j$.util.InterfaceC0079c
    public final void forEach(Consumer consumer) {
        consumer.getClass();
        l[] lVarArr = this.a.a;
        if (lVarArr == null) {
            return;
        }
        p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA == null) {
                return;
            } else {
                consumer.q(lVarA.b);
            }
        }
    }
}
