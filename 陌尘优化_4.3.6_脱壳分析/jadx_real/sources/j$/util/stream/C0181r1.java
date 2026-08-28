package j$.util.stream;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.r1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
class C0181r1 implements K0 {
    final long[] a;
    int b;

    @Override // j$.util.stream.M0
    public final /* synthetic */ void forEach(Consumer consumer) {
        A0.s(this, consumer);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.v(this, j, j2);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ Object[] p(IntFunction intFunction) {
        return A0.m(this, intFunction);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ int r() {
        return 0;
    }

    @Override // j$.util.stream.M0
    public final /* bridge */ /* synthetic */ M0 b(int i) {
        b(i);
        throw null;
    }

    @Override // j$.util.stream.L0, j$.util.stream.M0
    public final L0 b(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ void j(Object[] objArr, int i) {
        A0.p(this, (Long[]) objArr, i);
    }

    @Override // j$.util.stream.L0
    public final void d(Object obj, int i) {
        int i2 = this.b;
        System.arraycopy(this.a, 0, (long[]) obj, i, i2);
    }

    @Override // j$.util.stream.L0
    public final void f(Object obj) {
        LongConsumer longConsumer = (LongConsumer) obj;
        for (int i = 0; i < this.b; i++) {
            longConsumer.accept(this.a[i]);
        }
    }

    C0181r1(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.a = new long[(int) j];
        this.b = 0;
    }

    C0181r1(long[] jArr) {
        this.a = jArr;
        this.b = jArr.length;
    }

    @Override // j$.util.stream.L0, j$.util.stream.M0
    public final j$.util.Q spliterator() {
        return j$.util.i0.l(this.a, 0, this.b);
    }

    @Override // j$.util.stream.M0
    public final j$.util.U spliterator() {
        return j$.util.i0.l(this.a, 0, this.b);
    }

    @Override // j$.util.stream.L0
    public final Object e() {
        long[] jArr = this.a;
        int length = jArr.length;
        int i = this.b;
        return length == i ? jArr : Arrays.copyOf(jArr, i);
    }

    @Override // j$.util.stream.M0
    public final long count() {
        return this.b;
    }

    public String toString() {
        long[] jArr = this.a;
        return String.format("LongArrayNode[%d][%s]", Integer.valueOf(jArr.length - this.b), Arrays.toString(jArr));
    }
}
