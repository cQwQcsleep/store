package j$.util.stream;

/* renamed from: j$.util.stream.u2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0197u2 extends AbstractC0153l2 {
    long b;
    long c;
    final /* synthetic */ C0202v2 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0197u2(C0202v2 c0202v2, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0202v2;
        this.b = c0202v2.m;
        long j = c0202v2.n;
        this.c = j < 0 ? Long.MAX_VALUE : j;
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(A0.A(j, this.d.m, this.c));
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        long j = this.b;
        if (j == 0) {
            long j2 = this.c;
            if (j2 > 0) {
                this.c = j2 - 1;
                this.a.accept(i);
                return;
            }
            return;
        }
        this.b = j - 1;
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return this.c == 0 || this.a.o();
    }
}
