package j$.util.stream;

/* renamed from: j$.util.stream.s2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0187s2 extends AbstractC0163n2 {
    long b;
    long c;
    final /* synthetic */ C0192t2 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0187s2(C0192t2 c0192t2, InterfaceC0182r2 interfaceC0182r2) {
        super(interfaceC0182r2);
        this.d = c0192t2;
        this.b = c0192t2.m;
        long j = c0192t2.n;
        this.c = j < 0 ? Long.MAX_VALUE : j;
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        this.a.m(A0.A(j, this.d.m, this.c));
    }

    @Override // java.util.function.Consumer
    /* renamed from: accept */
    public final void q(Object obj) {
        long j = this.b;
        if (j == 0) {
            long j2 = this.c;
            if (j2 > 0) {
                this.c = j2 - 1;
                this.a.q((InterfaceC0182r2) obj);
                return;
            }
            return;
        }
        this.b = j - 1;
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final boolean o() {
        return this.c == 0 || this.a.o();
    }
}
