package j$.util.stream;

import java.util.ArrayDeque;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.p1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0172p1 extends AbstractC0177q1 {
    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        M0 m0A;
        if (!c()) {
            return false;
        }
        boolean zTryAdvance = this.d.tryAdvance(consumer);
        if (!zTryAdvance) {
            if (this.c == null && (m0A = AbstractC0177q1.a(this.e)) != null) {
                j$.util.U uSpliterator = m0A.spliterator();
                this.d = uSpliterator;
                return uSpliterator.tryAdvance(consumer);
            }
            this.a = null;
        }
        return zTryAdvance;
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            j$.util.U u = this.c;
            if (u == null) {
                ArrayDeque arrayDequeB = b();
                while (true) {
                    M0 m0A = AbstractC0177q1.a(arrayDequeB);
                    if (m0A != null) {
                        m0A.forEach(consumer);
                    } else {
                        this.a = null;
                        return;
                    }
                }
            } else {
                u.forEachRemaining(consumer);
            }
        } else {
            while (tryAdvance(consumer)) {
            }
        }
    }
}
