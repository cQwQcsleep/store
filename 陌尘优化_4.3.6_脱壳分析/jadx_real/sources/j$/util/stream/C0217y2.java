package j$.util.stream;

/* renamed from: j$.util.stream.y2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0217y2 extends AbstractC0148k2 {
    long b;
    long c;
    final /* synthetic */ C0222z2 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0217y2(C0222z2 c0222z2, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0222z2;
        this.b = c0222z2.m;
        long j = c0222z2.n;
        this.c = j < 0 ? Long.MAX_VALUE : j;
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(A0.A(j, this.d.m, this.c));
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        long j = this.b;
        if (j == 0) {
            long j2 = this.c;
            if (j2 > 0) {
                this.c = j2 - 1;
                this.a.accept(d);
                return;
            }
            return;
        }
        this.b = j - 1;
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return this.c == 0 || this.a.o();
    }
}
