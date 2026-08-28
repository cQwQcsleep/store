package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class k4 extends n4 implements IntConsumer, j$.util.K {
    int e;
    public final /* synthetic */ int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k4(j$.util.U u, int i) {
        super(u);
        this.f = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k4(j$.util.U u, n4 n4Var, int i) {
        super(u, n4Var);
        this.f = i;
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // j$.util.stream.n4, j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.b(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.i(this, consumer);
    }

    @Override // j$.util.Q
    public final void forEachRemaining(IntConsumer intConsumer) {
        while (tryAdvance(intConsumer)) {
        }
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.d = (this.d + 1) & 63;
        this.e = i;
    }

    @Override // j$.util.stream.n4
    final j$.util.U c(j$.util.U u) {
        switch (this.f) {
            case 0:
                return new k4((j$.util.K) u, this, 0);
            default:
                return new k4((j$.util.K) u, this, 1);
        }
    }

    @Override // j$.util.stream.n4, j$.util.U
    public /* bridge */ /* synthetic */ j$.util.Q trySplit() {
        switch (this.f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.stream.n4, j$.util.U
    public /* bridge */ /* synthetic */ j$.util.U trySplit() {
        switch (this.f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.K
    public final boolean tryAdvance(IntConsumer intConsumer) {
        switch (this.f) {
            case 0:
                boolean z = this.c;
                j$.util.U u = this.a;
                if (z) {
                    this.c = false;
                    boolean zTryAdvance = ((j$.util.K) u).tryAdvance((IntConsumer) this);
                    if (zTryAdvance && b()) {
                        IntPredicate intPredicate = null;
                        intPredicate.test(this.e);
                        throw null;
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    intConsumer.accept(this.e);
                    return zTryAdvance;
                }
                return ((j$.util.K) u).tryAdvance(intConsumer);
            default:
                if (this.c && b() && ((j$.util.K) this.a).tryAdvance((IntConsumer) this)) {
                    IntPredicate intPredicate2 = null;
                    intPredicate2.test(this.e);
                    throw null;
                }
                this.c = false;
                return false;
        }
    }

    @Override // j$.util.stream.n4, j$.util.U
    public j$.util.K trySplit() {
        switch (this.f) {
            case 1:
                if (this.b.get()) {
                    return null;
                }
                return (j$.util.K) super.trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.Q
    public /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        switch (this.f) {
            case 1:
                tryAdvance((IntConsumer) obj);
                return false;
            default:
                return tryAdvance((IntConsumer) obj);
        }
    }
}
