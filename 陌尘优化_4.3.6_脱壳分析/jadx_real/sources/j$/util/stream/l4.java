package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.function.LongConsumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class l4 extends n4 implements LongConsumer, j$.util.N {
    long e;
    public final /* synthetic */ int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l4(j$.util.U u, int i) {
        super(u);
        this.f = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l4(j$.util.U u, n4 n4Var, int i) {
        super(u, n4Var);
        this.f = i;
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // j$.util.stream.n4, j$.util.U
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        AbstractC0078b.c(this, consumer);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return AbstractC0078b.j(this, consumer);
    }

    @Override // j$.util.Q
    public final void forEachRemaining(LongConsumer longConsumer) {
        while (tryAdvance(longConsumer)) {
        }
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.d = (this.d + 1) & 63;
        this.e = j;
    }

    @Override // j$.util.stream.n4
    final j$.util.U c(j$.util.U u) {
        switch (this.f) {
            case 0:
                return new l4((j$.util.N) u, this, 0);
            default:
                return new l4((j$.util.N) u, this, 1);
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

    @Override // j$.util.N
    public final boolean tryAdvance(LongConsumer longConsumer) {
        switch (this.f) {
            case 0:
                boolean z = this.c;
                j$.util.U u = this.a;
                if (z) {
                    this.c = false;
                    boolean zTryAdvance = ((j$.util.N) u).tryAdvance((LongConsumer) this);
                    if (zTryAdvance && b()) {
                        LongPredicate longPredicate = null;
                        longPredicate.test(this.e);
                        throw null;
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    longConsumer.accept(this.e);
                    return zTryAdvance;
                }
                return ((j$.util.N) u).tryAdvance(longConsumer);
            default:
                if (this.c && b() && ((j$.util.N) this.a).tryAdvance((LongConsumer) this)) {
                    LongPredicate longPredicate2 = null;
                    longPredicate2.test(this.e);
                    throw null;
                }
                this.c = false;
                return false;
        }
    }

    @Override // j$.util.stream.n4, j$.util.U
    public j$.util.N trySplit() {
        switch (this.f) {
            case 1:
                if (this.b.get()) {
                    return null;
                }
                return (j$.util.N) super.trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.Q
    public /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        switch (this.f) {
            case 1:
                tryAdvance((LongConsumer) obj);
                return false;
            default:
                return tryAdvance((LongConsumer) obj);
        }
    }
}
