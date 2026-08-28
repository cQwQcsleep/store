package j$.util.stream;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class O0 implements M0 {
    protected final M0 a;
    protected final M0 b;
    private final long c;

    @Override // j$.util.stream.M0
    public final int r() {
        return 2;
    }

    O0(M0 m0, M0 m02) {
        this.a = m0;
        this.b = m02;
        this.c = m0.count() + m02.count();
    }

    @Override // j$.util.stream.M0
    public final M0 b(int i) {
        if (i == 0) {
            return this.a;
        }
        if (i == 1) {
            return this.b;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.M0
    public final long count() {
        return this.c;
    }

    @Override // j$.util.stream.M0
    public /* bridge */ /* synthetic */ L0 b(int i) {
        return (L0) b(i);
    }
}
