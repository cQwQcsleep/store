package j$.util.stream;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.t1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0191t1 extends Y2 implements K0, D0 {
    @Override // j$.util.stream.D0, j$.util.stream.E0
    public final K0 a() {
        return this;
    }

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

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final /* bridge */ /* synthetic */ void q(Object obj) {
        k((Long) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return j$.com.android.tools.r8.a.c(this, consumer);
    }

    @Override // j$.util.stream.M0
    public final /* synthetic */ M0 i(long j, long j2, IntFunction intFunction) {
        return A0.v(this, j, j2);
    }

    @Override // j$.util.stream.InterfaceC0178q2
    public final /* synthetic */ void k(Long l) {
        A0.i(this, l);
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

    @Override // j$.util.stream.AbstractC0099a3, j$.util.stream.L0
    public final void d(Object obj, int i) {
        super.d((long[]) obj, i);
    }

    @Override // j$.util.stream.AbstractC0099a3, j$.util.stream.L0
    public final void f(Object obj) {
        super.f((LongConsumer) obj);
    }

    @Override // j$.util.stream.Y2, j$.util.stream.AbstractC0099a3, java.lang.Iterable
    public final j$.util.Q spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.Y2, j$.util.stream.AbstractC0099a3, java.lang.Iterable
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
        return (long[]) super.e();
    }
}
