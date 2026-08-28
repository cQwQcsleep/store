package j$.util.stream;

import java.util.function.IntFunction;

/* renamed from: j$.util.stream.z1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0221z1 extends C0104b3 implements M0, E0 {
    @Override // j$.util.stream.E0
    public final M0 a() {
        return this;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(double d) {
        A0.a();
        throw null;
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ void accept(int i) {
        A0.k();
        throw null;
    }

    @Override // j$.util.stream.InterfaceC0182r2, j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final /* synthetic */ void accept(long j) {
        A0.l();
        throw null;
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.w(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void l() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ int r() {
        return 0;
    }

    @Override // j$.util.stream.M0
    public final M0 b(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.M0
    public final void j(Object[] objArr, int i) {
        long j = i;
        long jCount = count() + j;
        if (jCount > objArr.length || jCount < j) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (this.c == 0) {
            System.arraycopy(this.e, 0, objArr, i, this.b);
            return;
        }
        for (int i2 = 0; i2 < this.c; i2++) {
            Object[] objArr2 = this.f[i2];
            System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
            i += this.f[i2].length;
        }
        int i3 = this.b;
        if (i3 > 0) {
            System.arraycopy(this.e, 0, objArr, i, i3);
        }
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

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        clear();
        s(j);
    }
}
