package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class A3 extends B3 implements j$.util.U {
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

    A3(j$.util.U u, long j, long j2) {
        super(u, j, j2, 0L, Math.min(u.estimateSize(), j2));
    }

    @Override // j$.util.stream.B3
    protected final j$.util.U a(j$.util.U u, long j, long j2, long j3, long j4) {
        return new A3(u, j, j2, j3, j4);
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        long j;
        Objects.requireNonNull(consumer);
        long j2 = this.e;
        long j3 = this.a;
        if (j3 >= j2) {
            return false;
        }
        while (true) {
            j = this.d;
            if (j3 <= j) {
                break;
            }
            this.c.tryAdvance(new C0118e2(4));
            this.d++;
        }
        if (j >= this.e) {
            return false;
        }
        this.d = j + 1;
        return this.c.tryAdvance(consumer);
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        long j = this.e;
        long j2 = this.a;
        if (j2 >= j) {
            return;
        }
        long j3 = this.d;
        if (j3 >= j) {
            return;
        }
        if (j3 >= j2 && this.c.estimateSize() + j3 <= this.b) {
            this.c.forEachRemaining(consumer);
            this.d = this.e;
            return;
        }
        while (j2 > this.d) {
            this.c.tryAdvance(new C0118e2(5));
            this.d++;
        }
        while (this.d < this.e) {
            this.c.tryAdvance(consumer);
            this.d++;
        }
    }
}
