package j$.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
class g0 implements U {
    private final Collection a;
    private Iterator b = null;
    private final int c;
    private long d;
    private int e;

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return AbstractC0078b.d(this);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    public g0(Collection collection, int i) {
        this.a = collection;
        this.c = i | 16448;
    }

    @Override // j$.util.U
    public final U trySplit() {
        long size;
        Iterator it = this.b;
        if (it == null) {
            Collection collection = this.a;
            Iterator it2 = collection.iterator();
            this.b = it2;
            size = collection.size();
            this.d = size;
            it = it2;
        } else {
            size = this.d;
        }
        if (size <= 1 || !it.hasNext()) {
            return null;
        }
        int i = this.e + 1024;
        if (i > size) {
            i = (int) size;
        }
        if (i > 33554432) {
            i = 33554432;
        }
        Object[] objArr = new Object[i];
        int i2 = 0;
        do {
            objArr[i2] = it.next();
            i2++;
            if (i2 >= i) {
                break;
            }
        } while (it.hasNext());
        this.e = i2;
        long j = this.d;
        if (j != Long.MAX_VALUE) {
            this.d = j - i2;
        }
        return new Z(objArr, 0, i2, this.c);
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        Iterator it = this.b;
        if (it == null) {
            Iterator it2 = this.a.iterator();
            this.b = it2;
            this.d = r0.size();
            it = it2;
        }
        if (it instanceof InterfaceC0087k) {
            ((InterfaceC0087k) it).forEachRemaining(consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        if (this.b == null) {
            this.b = this.a.iterator();
            this.d = r0.size();
        }
        if (!this.b.hasNext()) {
            return false;
        }
        consumer.accept(this.b.next());
        return true;
    }

    @Override // j$.util.U
    public final long estimateSize() {
        if (this.b == null) {
            Collection collection = this.a;
            this.b = collection.iterator();
            long size = collection.size();
            this.d = size;
            return size;
        }
        return this.d;
    }

    @Override // j$.util.U
    public final int characteristics() {
        return this.c;
    }

    @Override // j$.util.U
    public java.util.Comparator getComparator() {
        if (AbstractC0078b.e(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
