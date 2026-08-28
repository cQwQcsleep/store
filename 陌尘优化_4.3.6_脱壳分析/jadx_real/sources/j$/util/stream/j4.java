package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class j4 extends n4 implements DoubleConsumer, j$.util.H {
    double e;
    public final /* synthetic */ int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j4(j$.util.U u, int i) {
        super(u);
        this.f = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j4(j$.util.U u, n4 n4Var, int i) {
        super(u, n4Var);
        this.f = i;
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.d(this, doubleConsumer);
    }

    @Override // j$.util.stream.n4, j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.a(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.h(this, consumer);
    }

    @Override // j$.util.Q
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        while (tryAdvance(doubleConsumer)) {
        }
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.d = (this.d + 1) & 63;
        this.e = d;
    }

    @Override // j$.util.stream.n4
    final j$.util.U c(j$.util.U u) {
        switch (this.f) {
            case 0:
                return new j4((j$.util.H) u, this, 0);
            default:
                return new j4((j$.util.H) u, this, 1);
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

    @Override // j$.util.H
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        switch (this.f) {
            case 0:
                boolean z = this.c;
                j$.util.U u = this.a;
                if (z) {
                    this.c = false;
                    boolean zTryAdvance = ((j$.util.H) u).tryAdvance((DoubleConsumer) this);
                    if (zTryAdvance && b()) {
                        DoublePredicate doublePredicate = null;
                        doublePredicate.test(this.e);
                        throw null;
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    doubleConsumer.accept(this.e);
                    return zTryAdvance;
                }
                return ((j$.util.H) u).tryAdvance(doubleConsumer);
            default:
                if (this.c && b() && ((j$.util.H) this.a).tryAdvance((DoubleConsumer) this)) {
                    DoublePredicate doublePredicate2 = null;
                    doublePredicate2.test(this.e);
                    throw null;
                }
                this.c = false;
                return false;
        }
    }

    @Override // j$.util.stream.n4, j$.util.U
    public j$.util.H trySplit() {
        switch (this.f) {
            case 1:
                if (this.b.get()) {
                    return null;
                }
                return (j$.util.H) super.trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.Q
    public /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        switch (this.f) {
            case 1:
                tryAdvance((DoubleConsumer) obj);
                return false;
            default:
                return tryAdvance((DoubleConsumer) obj);
        }
    }
}
