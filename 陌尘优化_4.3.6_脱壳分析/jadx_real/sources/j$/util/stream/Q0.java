package j$.util.stream;

import j$.util.Collection$EL;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Q0 implements M0 {
    private final Collection a;

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.w(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ int r() {
        return 0;
    }

    @Override // j$.util.stream.M0
    public final M0 b(int i) {
        throw new IndexOutOfBoundsException();
    }

    Q0(Collection collection) {
        this.a = collection;
    }

    @Override // j$.util.stream.M0
    public final j$.util.U spliterator() {
        return Collection$EL.stream(this.a).spliterator();
    }

    @Override // j$.util.stream.M0
    public final void j(Object[] objArr, int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    @Override // j$.util.stream.M0
    public final Object[] p(IntFunction intFunction) {
        Collection collection = this.a;
        return collection.toArray((Object[]) intFunction.apply(collection.size()));
    }

    @Override // j$.util.stream.M0
    public final long count() {
        return this.a.size();
    }

    @Override // j$.util.stream.M0
    public final void forEach(Consumer consumer) {
        Collection$EL.a(this.a, consumer);
    }

    public final String toString() {
        Collection collection = this.a;
        return String.format("CollectionNode[%d][%s]", Integer.valueOf(collection.size()), collection);
    }
}
