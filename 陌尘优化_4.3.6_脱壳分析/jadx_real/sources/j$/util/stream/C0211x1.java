package j$.util.stream;

/* renamed from: j$.util.stream.x1, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0211x1 extends AbstractC0216y1 {
    private final Object[] h;

    C0211x1(j$.util.U u, AbstractC0100b abstractC0100b, Object[] objArr) {
        super(u, abstractC0100b, objArr.length);
        this.h = objArr;
    }

    C0211x1(C0211x1 c0211x1, j$.util.U u, long j, long j2) {
        super(c0211x1, u, j, j2, c0211x1.h.length);
        this.h = c0211x1.h;
    }

    @Override // j$.util.stream.AbstractC0216y1
    final AbstractC0216y1 b(j$.util.U u, long j, long j2) {
        return new C0211x1(this, u, j, j2);
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        Object[] objArr = this.h;
        this.f = i + 1;
        objArr[i] = obj;
    }
}
