package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class G3 extends I3 implements j$.util.U, Consumer {
    Object f;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
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

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f = obj;
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        while (d() != H3.NO_MORE && this.a.tryAdvance(this)) {
            if (b(1L) == 1) {
                consumer.accept(this.f);
                this.f = null;
                return true;
            }
        }
        return false;
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        C0164n3 c0164n3 = null;
        while (true) {
            H3 h3D = d();
            if (h3D == H3.NO_MORE) {
                return;
            }
            H3 h3 = H3.MAYBE_MORE;
            j$.util.U u = this.a;
            if (h3D == h3) {
                int i = this.c;
                if (c0164n3 == null) {
                    c0164n3 = new C0164n3(i);
                } else {
                    c0164n3.a = 0;
                }
                long j = 0;
                while (u.tryAdvance(c0164n3)) {
                    j++;
                    if (j >= i) {
                        break;
                    }
                }
                if (j == 0) {
                    return;
                }
                long jB = b(j);
                for (int i2 = 0; i2 < jB; i2++) {
                    consumer.accept(c0164n3.b[i2]);
                }
            } else {
                u.forEachRemaining(consumer);
                return;
            }
        }
    }

    @Override // j$.util.stream.I3
    protected final j$.util.U c(j$.util.U u) {
        return new G3(u, this);
    }
}
