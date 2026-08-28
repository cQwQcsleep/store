package j$.util.stream;

import j$.util.AbstractC0078b;
import j$.util.Objects;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: /workspace/unpacked/classes3.dex */
final class S2 implements j$.util.U {
    int a;
    final int b;
    int c;
    final int d;
    Object[] e;
    final /* synthetic */ C0104b3 f;

    @Override // j$.util.U
    public final int characteristics() {
        return 16464;
    }

    @Override // j$.util.U
    public final /* synthetic */ long getExactSizeIfKnown() {
        return AbstractC0078b.d(this);
    }

    @Override // j$.util.U
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return AbstractC0078b.e(this, i);
    }

    S2(C0104b3 c0104b3, int i, int i2, int i3, int i4) {
        this.f = c0104b3;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        Object[][] objArr = c0104b3.f;
        this.e = objArr == null ? c0104b3.e : objArr[i];
    }

    @Override // j$.util.U
    public final long estimateSize() {
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i == i3) {
            return i2 - this.c;
        }
        long[] jArr = this.f.d;
        return ((jArr[i3] + i2) - jArr[i]) - this.c;
    }

    @Override // j$.util.U
    public final boolean tryAdvance(Consumer consumer) {
        Objects.requireNonNull(consumer);
        int i = this.a;
        int i2 = this.b;
        if (i >= i2 && (i != i2 || this.c >= this.d)) {
            return false;
        }
        Object[] objArr = this.e;
        int i3 = this.c;
        this.c = i3 + 1;
        consumer.accept(objArr[i3]);
        if (this.c == this.e.length) {
            this.c = 0;
            int i4 = this.a + 1;
            this.a = i4;
            Object[][] objArr2 = this.f.f;
            if (objArr2 != null && i4 <= i2) {
                this.e = objArr2[i4];
            }
        }
        return true;
    }

    @Override // j$.util.U
    public final void forEachRemaining(Consumer consumer) {
        C0104b3 c0104b3;
        Objects.requireNonNull(consumer);
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i < i3 || (i == i3 && this.c < i2)) {
            int i4 = this.c;
            while (true) {
                c0104b3 = this.f;
                if (i >= i3) {
                    break;
                }
                Object[] objArr = c0104b3.f[i];
                while (i4 < objArr.length) {
                    consumer.accept(objArr[i4]);
                    i4++;
                }
                i++;
                i4 = 0;
            }
            Object[] objArr2 = this.a == i3 ? this.e : c0104b3.f[i3];
            while (i4 < i2) {
                consumer.accept(objArr2[i4]);
                i4++;
            }
            this.a = i3;
            this.c = i2;
        }
    }

    @Override // j$.util.U
    public final j$.util.U trySplit() {
        int i = this.a;
        int i2 = this.b;
        if (i < i2) {
            int i3 = i2 - 1;
            int i4 = this.c;
            C0104b3 c0104b3 = this.f;
            S2 s2 = new S2(c0104b3, i, i3, i4, c0104b3.f[i3].length);
            this.a = i2;
            this.c = 0;
            this.e = c0104b3.f[i2];
            return s2;
        }
        if (i != i2) {
            return null;
        }
        int i5 = this.c;
        int i6 = (this.d - i5) / 2;
        if (i6 == 0) {
            return null;
        }
        j$.util.U uM = j$.util.i0.m(this.e, i5, i5 + i6);
        this.c += i6;
        return uM;
    }

    @Override // j$.util.U
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }
}
