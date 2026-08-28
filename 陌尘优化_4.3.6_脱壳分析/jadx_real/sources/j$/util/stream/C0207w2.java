package j$.util.stream;

/* renamed from: j$.util.stream.w2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0207w2 extends AbstractC0158m2 {
    long b;
    long c;
    final /* synthetic */ C0212x2 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0207w2(C0212x2 c0212x2, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0212x2;
        this.b = c0212x2.m;
        long j = c0212x2.n;
        this.c = j < 0 ? Long.MAX_VALUE : j;
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(A0.A(j, this.d.m, this.c));
    }

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        long j2 = this.b;
        if (j2 == 0) {
            long j3 = this.c;
            if (j3 > 0) {
                this.c = j3 - 1;
                this.a.accept(j);
                return;
            }
            return;
        }
        this.b = j2 - 1;
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return this.c == 0 || this.a.o();
    }
}
