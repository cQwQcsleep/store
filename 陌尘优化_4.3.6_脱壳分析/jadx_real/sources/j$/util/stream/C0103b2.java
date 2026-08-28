package j$.util.stream;

/* renamed from: j$.util.stream.b2, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0103b2 extends AbstractC0108c2 {
    @Override // j$.util.stream.X1, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.b);
    }

    @Override // j$.util.stream.W1
    public final void h(W1 w1) {
        this.b += ((AbstractC0108c2) w1).b;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b++;
    }
}
