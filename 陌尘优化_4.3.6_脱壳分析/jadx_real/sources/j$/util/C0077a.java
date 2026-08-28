package j$.util;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.function.Consumer;

/* renamed from: j$.util.a, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0077a implements U {
    private final List a;
    private int b;
    private int c;

    @Override // j$.util.U
    public final int characteristics() {
        return 16464;
    }

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return AbstractC0078b.d(this);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    @Override // j$.util.U
    public final java.util.Comparator getComparator() {
        throw new IllegalStateException();
    }

    C0077a(List list) {
        this.a = list;
        this.b = 0;
        this.c = -1;
    }

    private C0077a(C0077a c0077a, int i, int i2) {
        this.a = c0077a.a;
        this.b = i;
        this.c = i2;
    }

    private int a() {
        int i = this.c;
        if (i >= 0) {
            return i;
        }
        int size = this.a.size();
        this.c = size;
        return size;
    }

    @Override // j$.util.U
    public final U trySplit() {
        int iA = a();
        int i = this.b;
        int i2 = (iA + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.b = i2;
        return new C0077a(this, i, i2);
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        int iA = a();
        int i = this.b;
        if (i >= iA) {
            return false;
        }
        this.b = i + 1;
        try {
            consumer.accept(this.a.get(i));
            return true;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        int iA = a();
        this.b = iA;
        for (int i = this.b; i < iA; i++) {
            try {
                consumer.accept(this.a.get(i));
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // j$.util.U
    public final long estimateSize() {
        return a() - this.b;
    }
}
