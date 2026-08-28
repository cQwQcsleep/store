package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.Predicate;

/* loaded from: /workspace/unpacked/classes3.dex */
final class m4 extends n4 implements Consumer {
    final Predicate e;
    Object f;
    public final /* synthetic */ int g;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m4(j$.util.U u, Predicate predicate, int i) {
        super(u);
        this.g = i;
        this.e = predicate;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m4(j$.util.U u, m4 m4Var, int i) {
        super(u, m4Var);
        this.g = i;
        this.e = m4Var.e;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.d = (this.d + 1) & 63;
        this.f = obj;
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        boolean zTryAdvance;
        boolean zTest;
        switch (this.g) {
            case 0:
                boolean z = this.c;
                j$.util.U u = this.a;
                if (z) {
                    boolean z2 = false;
                    this.c = false;
                    while (true) {
                        zTryAdvance = u.tryAdvance(this);
                        if (zTryAdvance && b() && this.e.test(this.f)) {
                            z2 = true;
                        }
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    if (z2) {
                        this.b.set(true);
                    }
                    consumer.accept(this.f);
                    return zTryAdvance;
                }
                return u.tryAdvance(consumer);
            default:
                if (this.c && b() && this.a.tryAdvance(this)) {
                    zTest = this.e.test(this.f);
                    if (zTest) {
                        consumer.accept(this.f);
                        return true;
                    }
                } else {
                    zTest = true;
                }
                this.c = false;
                if (!zTest) {
                    this.b.set(true);
                }
                return false;
        }
    }

    @Override // j$.util.stream.n4, j$.util.U
    public j$.util.U trySplit() {
        switch (this.g) {
            case 1:
                if (!this.b.get()) {
                    break;
                }
                break;
        }
        return super.trySplit();
    }

    @Override // j$.util.stream.n4
    final j$.util.U c(j$.util.U u) {
        switch (this.g) {
            case 0:
                return new m4(u, this, 0);
            default:
                return new m4(u, this, 1);
        }
    }
}
