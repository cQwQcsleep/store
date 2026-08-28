package j$.util.stream;

import java.util.ArrayDeque;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.o1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0167o1 extends AbstractC0177q1 implements j$.util.Q {
    @Override // j$.util.Q
    public final boolean tryAdvance(Object obj) {
        L0 l0;
        if (!c()) {
            return false;
        }
        boolean zTryAdvance = ((j$.util.Q) this.d).tryAdvance(obj);
        if (!zTryAdvance) {
            if (this.c == null && (l0 = (L0) AbstractC0177q1.a(this.e)) != null) {
                j$.util.Q qSpliterator = l0.spliterator();
                this.d = qSpliterator;
                return qSpliterator.tryAdvance(obj);
            }
            this.a = null;
        }
        return zTryAdvance;
    }

    @Override // j$.util.Q
    public final void forEachRemaining(Object obj) {
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            j$.util.U u = this.c;
            if (u == null) {
                ArrayDeque arrayDequeB = b();
                while (true) {
                    L0 l0 = (L0) AbstractC0177q1.a(arrayDequeB);
                    if (l0 != null) {
                        l0.f(obj);
                    } else {
                        this.a = null;
                        return;
                    }
                }
            } else {
                ((j$.util.Q) u).forEachRemaining(obj);
            }
        } else {
            while (tryAdvance(obj)) {
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
