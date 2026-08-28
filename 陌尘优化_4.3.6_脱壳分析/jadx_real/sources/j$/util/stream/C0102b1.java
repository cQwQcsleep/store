package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;

/* renamed from: j$.util.stream.b1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0102b1 extends U2 implements G0, B0 {
    @Override // j$.util.stream.B0, j$.util.stream.E0
    public final G0 a() {
        return this;
    }

    @Override // j$.util.stream.E0
    public final M0 a() {
        return this;
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

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        q((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.t(this, j, j2);
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void l() {
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final /* synthetic */ boolean o() {
        return false;
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ Object[] p(IntFunction intFunction) {
        return A0.m(this, intFunction);
    }

    @Override // j$.util.stream.InterfaceC0168o2
    public final /* synthetic */ void q(Double d) {
        A0.e(this, d);
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
        A0.n(this, (Double[]) objArr, i);
    }

    @Override // j$.util.stream.AbstractC0099a3, j$.util.stream.L0
    public final void d(Object obj, int i) {
        super.d((double[]) obj, i);
    }

    @Override // j$.util.stream.AbstractC0099a3, j$.util.stream.L0
    public final void f(Object obj) {
        super.f((DoubleConsumer) obj);
    }

    @Override // j$.util.stream.U2, j$.util.stream.AbstractC0099a3, java.lang.Iterable
    public final j$.util.Q spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.U2, j$.util.stream.AbstractC0099a3, java.lang.Iterable
    public final j$.util.U spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        clear();
        v(j);
    }

    @Override // j$.util.stream.AbstractC0099a3, j$.util.stream.L0
    public final Object e() {
        return (double[]) super.e();
    }
}
