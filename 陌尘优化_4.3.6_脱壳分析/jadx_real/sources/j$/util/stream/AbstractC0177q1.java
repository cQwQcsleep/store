package j$.util.stream;

import j$.util.AbstractC0078b;
import java.util.ArrayDeque;
import java.util.Comparator;

/* renamed from: j$.util.stream.q1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0177q1 implements j$.util.U {
    M0 a;
    int b;
    j$.util.U c;
    j$.util.U d;
    ArrayDeque e;

    @Override // j$.util.U
    public final int characteristics() {
        return 64;
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

    AbstractC0177q1(M0 m0) {
        this.a = m0;
    }

    protected final ArrayDeque b() {
        ArrayDeque arrayDeque = new ArrayDeque(8);
        int iR = this.a.r();
        while (true) {
            iR--;
            if (iR < this.b) {
                return arrayDeque;
            }
            arrayDeque.addFirst(this.a.b(iR));
        }
    }

    protected static M0 a(ArrayDeque arrayDeque) {
        while (true) {
            M0 m0 = (M0) arrayDeque.pollFirst();
            if (m0 == null) {
                return null;
            }
            if (m0.r() != 0) {
                for (int iR = m0.r() - 1; iR >= 0; iR--) {
                    arrayDeque.addFirst(m0.b(iR));
                }
            } else if (m0.count() > 0) {
                return m0;
            }
        }
    }

    protected final boolean c() {
        if (this.a == null) {
            return false;
        }
        if (this.d != null) {
            return true;
        }
        j$.util.U u = this.c;
        if (u == null) {
            ArrayDeque arrayDequeB = b();
            this.e = arrayDequeB;
            M0 m0A = a(arrayDequeB);
            if (m0A != null) {
                this.d = m0A.spliterator();
                return true;
            }
            this.a = null;
            return false;
        }
        this.d = u;
        return true;
    }

    @Override // j$.util.U
    public final j$.util.U trySplit() {
        M0 m0 = this.a;
        if (m0 == null || this.d != null) {
            return null;
        }
        j$.util.U u = this.c;
        if (u != null) {
            return u.trySplit();
        }
        if (this.b < m0.r() - 1) {
            M0 m02 = this.a;
            int i = this.b;
            this.b = i + 1;
            return m02.b(i).spliterator();
        }
        M0 m0B = this.a.b(this.b);
        this.a = m0B;
        if (m0B.r() == 0) {
            j$.util.U uSpliterator = this.a.spliterator();
            this.c = uSpliterator;
            return uSpliterator.trySplit();
        }
        M0 m03 = this.a;
        this.b = 1;
        return m03.b(0).spliterator();
    }

    @Override // j$.util.U
    public final long estimateSize() {
        long jCount = 0;
        if (this.a == null) {
            return 0L;
        }
        j$.util.U u = this.c;
        if (u != null) {
            return u.estimateSize();
        }
        for (int i = this.b; i < this.a.r(); i++) {
            jCount += this.a.b(i).count();
        }
        return jCount;
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.Q trySplit() {
        return (j$.util.Q) trySplit();
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.K trySplit() {
        return (j$.util.K) trySplit();
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.N trySplit() {
        return (j$.util.N) trySplit();
    }

    @Override // j$.util.U
    public /* bridge */ /* synthetic */ j$.util.H trySplit() {
        return (j$.util.H) trySplit();
    }
}
