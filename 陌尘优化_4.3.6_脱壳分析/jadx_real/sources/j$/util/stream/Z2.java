package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.Comparator;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class Z2 implements j$.util.Q {
    int a;
    final int b;
    int c;
    final int d;
    Object e;
    final /* synthetic */ AbstractC0099a3 f;

    abstract void a(int i, Object obj, Object obj2);

    abstract j$.util.Q b(Object obj, int i, int i2);

    abstract j$.util.Q c(int i, int i2, int i3, int i4);

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
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    Z2(AbstractC0099a3 abstractC0099a3, int i, int i2, int i3, int i4) {
        this.f = abstractC0099a3;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        Object[] objArr = abstractC0099a3.f;
        this.e = objArr == null ? abstractC0099a3.e : objArr[i];
    }

    @Override // j$.util.U
    public final long estimateSize() {
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i == i3) {
            return i2 - this.c;
        }
        long[] jArr = this.f.d;
        return ((jArr[i3] + i2) - jArr[i]) - this.c;
    }

    @Override // j$.util.Q
    public final boolean tryAdvance(Object obj) {
        Objects.requireNonNull(obj);
        int i = this.a;
        int i2 = this.b;
        if (i >= i2 && (i != i2 || this.c >= this.d)) {
            return false;
        }
        Object obj2 = this.e;
        int i3 = this.c;
        this.c = i3 + 1;
        a(i3, obj2, obj);
        int i4 = this.c;
        Object obj3 = this.e;
        AbstractC0099a3 abstractC0099a3 = this.f;
        if (i4 == abstractC0099a3.t(obj3)) {
            this.c = 0;
            int i5 = this.a + 1;
            this.a = i5;
            Object[] objArr = abstractC0099a3.f;
            if (objArr != null && i5 <= i2) {
                this.e = objArr[i5];
            }
        }
        return true;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(Object obj) {
        AbstractC0099a3 abstractC0099a3;
        Objects.requireNonNull(obj);
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i < i3 || (i == i3 && this.c < i2)) {
            int i4 = this.c;
            while (true) {
                abstractC0099a3 = this.f;
                if (i >= i3) {
                    break;
                }
                Object obj2 = abstractC0099a3.f[i];
                abstractC0099a3.s(obj2, i4, abstractC0099a3.t(obj2), obj);
                i++;
                i4 = 0;
            }
            abstractC0099a3.s(this.a == i3 ? this.e : abstractC0099a3.f[i3], i4, i2, obj);
            this.a = i3;
            this.c = i2;
        }
    }

    @Override // j$.util.U
    public final j$.util.Q trySplit() {
        int i = this.a;
        int i2 = this.b;
        if (i < i2) {
            int i3 = i2 - 1;
            int i4 = this.c;
            AbstractC0099a3 abstractC0099a3 = this.f;
            j$.util.Q qC = c(i, i3, i4, abstractC0099a3.t(abstractC0099a3.f[i3]));
            this.a = i2;
            this.c = 0;
            this.e = abstractC0099a3.f[i2];
            return qC;
        }
        if (i != i2) {
            return null;
        }
        int i5 = this.c;
        int i6 = (this.d - i5) / 2;
        if (i6 == 0) {
            return null;
        }
        j$.util.Q qB = b(this.e, i5, i6);
        this.c += i6;
        return qB;
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        forEachRemaining((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
        return tryAdvance((Object) intConsumer);
    }

    @Override // j$.util.Q, j$.util.U
    public /* bridge */ /* synthetic */ j$.util.K trySplit() {
        return (j$.util.K) trySplit();
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        forEachRemaining((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
        return tryAdvance((Object) longConsumer);
    }

    @Override // j$.util.Q, j$.util.U
    public /* bridge */ /* synthetic */ j$.util.N trySplit() {
        return (j$.util.N) trySplit();
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        forEachRemaining((Object) doubleConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
        return tryAdvance((Object) doubleConsumer);
    }

    @Override // j$.util.Q, j$.util.U
    public /* bridge */ /* synthetic */ j$.util.H trySplit() {
        return (j$.util.H) trySplit();
    }
}
