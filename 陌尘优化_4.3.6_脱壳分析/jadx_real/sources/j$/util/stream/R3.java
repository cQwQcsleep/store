package j$.util.stream;

/* loaded from: /workspace/unpacked/classes3.dex */
final class R3 extends AbstractC0163n2 implements g4 {
    long b;
    boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ S3 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    R3(S3 s3, InterfaceC0182r2 interfaceC0182r2, boolean z) {
        super(interfaceC0182r2);
        this.e = s3;
        this.d = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    @Override // java.util.function.Consumer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void accept(Object obj) {
        boolean z;
        if (!this.c) {
            boolean zTest = this.e.m.test(obj);
            this.c = !zTest;
            z = !zTest;
        }
        boolean z2 = this.d;
        if (z2 && !z) {
            this.b++;
        }
        if (z2 || z) {
            this.a.accept((InterfaceC0182r2) obj);
        }
    }

    @Override // j$.util.stream.g4
    public final long g() {
        return this.b;
    }
}
