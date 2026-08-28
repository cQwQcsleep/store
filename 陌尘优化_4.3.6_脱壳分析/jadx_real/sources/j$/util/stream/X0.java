package j$.util.stream;

import java.util.function.IntFunction;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class X0 extends O0 implements L0 {
    @Override // j$.util.stream.M0
    public final /* synthetic */ Object[] p(IntFunction intFunction) {
        return A0.m(this, intFunction);
    }

    @Override // j$.util.stream.L0
    public final void f(Object obj) {
        ((L0) this.a).f(obj);
        ((L0) this.b).f(obj);
    }

    @Override // j$.util.stream.L0
    public final void d(Object obj, int i) {
        M0 m0 = this.a;
        ((L0) m0).d(obj, i);
        ((L0) this.b).d(obj, i + ((int) ((L0) m0).count()));
    }

    @Override // j$.util.stream.L0
    public final Object e() {
        long jCount = count();
        if (jCount >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object objC = c((int) jCount);
        d(objC, 0);
        return objC;
    }

    public final String toString() {
        return count() < 32 ? String.format("%s[%s.%s]", getClass().getName(), this.a, this.b) : String.format("%s[size=%d]", getClass().getName(), Long.valueOf(count()));
    }
}
