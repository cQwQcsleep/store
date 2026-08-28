package j$.util.stream;

import j$.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class Y0 extends O0 {
    @Override // j$.util.stream.M0
    public final j$.util.U spliterator() {
        return new C0172p1(this);
    }

    @Override // j$.util.stream.M0
    public final void j(Object[] objArr, int i) {
        Objects.requireNonNull(objArr);
        M0 m0 = this.a;
        m0.j(objArr, i);
        this.b.j(objArr, i + ((int) m0.count()));
    }

    @Override // j$.util.stream.M0
    public final Object[] p(IntFunction intFunction) {
        long jCount = count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) jCount);
        j(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.M0
    public final void forEach(Consumer consumer) {
        this.a.forEach(consumer);
        this.b.forEach(consumer);
    }

    @Override // j$.util.stream.M0
    public final M0 i(long j, long j2, IntFunction intFunction) {
        if (j == 0 && j2 == count()) {
            return this;
        }
        long jCount = this.a.count();
        if (j >= jCount) {
            return this.b.i(j - jCount, j2 - jCount, intFunction);
        }
        if (j2 > jCount) {
            return A0.I(EnumC0134h3.REFERENCE, this.a.i(j, jCount, intFunction), this.b.i(0L, j2 - jCount, intFunction));
        }
        return this.a.i(j, j2, intFunction);
    }

    public final String toString() {
        return count() < 32 ? String.format("ConcNode[%s.%s]", this.a, this.b) : String.format("ConcNode[size=%d]", Long.valueOf(count()));
    }
}
