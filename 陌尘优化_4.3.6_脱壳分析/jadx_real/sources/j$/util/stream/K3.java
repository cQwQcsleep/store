package j$.util.stream;

import j$.util.Objects;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class K3 extends AbstractC0139i3 {
    @Override // j$.util.stream.AbstractC0139i3
    final AbstractC0139i3 e(j$.util.U u) {
        return new K3(this.b, u, this.a);
    }

    @Override // j$.util.stream.AbstractC0139i3
    final void d() {
        C0104b3 c0104b3 = new C0104b3();
        this.h = c0104b3;
        Objects.requireNonNull(c0104b3);
        this.e = this.b.W(new J3(c0104b3, 0));
        this.f = new C0095a(this, 5);
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        Object obj;
        Objects.requireNonNull(consumer);
        boolean zA = a();
        if (zA) {
            C0104b3 c0104b3 = (C0104b3) this.h;
            long j = this.g;
            if (c0104b3.c != 0) {
                if (j >= c0104b3.count()) {
                    throw new IndexOutOfBoundsException(Long.toString(j));
                }
                for (int i = 0; i <= c0104b3.c; i++) {
                    long j2 = c0104b3.d[i];
                    Object[] objArr = c0104b3.f[i];
                    if (j < objArr.length + j2) {
                        obj = objArr[(int) (j - j2)];
                    }
                }
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            if (j < c0104b3.b) {
                obj = c0104b3.e[(int) j];
            } else {
                throw new IndexOutOfBoundsException(Long.toString(j));
            }
            consumer.q(obj);
        }
        return zA;
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        if (this.h == null && !this.i) {
            Objects.requireNonNull(consumer);
            c();
            Objects.requireNonNull(consumer);
            J3 j3 = new J3(consumer, 1);
            this.b.V(this.d, j3);
            this.i = true;
            return;
        }
        while (tryAdvance(consumer)) {
        }
    }
}
