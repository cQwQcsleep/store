package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.Comparator;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class F3 extends I3 implements j$.util.Q {
    protected abstract void e(Object obj);

    protected abstract AbstractC0159m3 f(int i);

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

    @Override // j$.util.Q
    public final boolean tryAdvance(Object obj) {
        Objects.requireNonNull(obj);
        while (d() != H3.NO_MORE && ((j$.util.Q) this.a).tryAdvance(this)) {
            if (b(1L) == 1) {
                e(obj);
                return true;
            }
        }
        return false;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(Object obj) {
        Objects.requireNonNull(obj);
        AbstractC0159m3 abstractC0159m3F = null;
        while (true) {
            H3 h3D = d();
            if (h3D == H3.NO_MORE) {
                return;
            }
            H3 h3 = H3.MAYBE_MORE;
            j$.util.U u = this.a;
            if (h3D == h3) {
                int i = this.c;
                if (abstractC0159m3F == null) {
                    abstractC0159m3F = f(i);
                } else {
                    abstractC0159m3F.b = 0;
                }
                long j = 0;
                while (((j$.util.Q) u).tryAdvance(abstractC0159m3F)) {
                    j++;
                    if (j >= i) {
                        break;
                    }
                }
                if (j == 0) {
                    return;
                } else {
                    abstractC0159m3F.b(obj, b(j));
                }
            } else {
                ((j$.util.Q) u).forEachRemaining(obj);
                return;
            }
        }
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        forEachRemaining((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
        return tryAdvance((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        forEachRemaining((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
        return tryAdvance((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        forEachRemaining((Object) doubleConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
        return tryAdvance((Object) doubleConsumer);
    }
}
