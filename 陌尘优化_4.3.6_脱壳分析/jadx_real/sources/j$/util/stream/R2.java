package j$.util.stream;

import java.util.Arrays;

/* loaded from: /workspace/unpacked/classes3.dex */
final class R2 extends F2 {
    private Object[] d;
    private int e;

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.d = new Object[(int) j];
    }

    @Override // j$.util.stream.AbstractC0163n2, j$.util.stream.InterfaceC0182r2
    public final void l() {
        int i = 0;
        Arrays.sort(this.d, 0, this.e, this.b);
        long j = this.e;
        InterfaceC0182r2 interfaceC0182r2 = this.a;
        interfaceC0182r2.m(j);
        if (!this.c) {
            while (i < this.e) {
                interfaceC0182r2.accept((InterfaceC0182r2) this.d[i]);
                i++;
            }
        } else {
            while (i < this.e && !interfaceC0182r2.o()) {
                interfaceC0182r2.accept((InterfaceC0182r2) this.d[i]);
                i++;
            }
        }
        interfaceC0182r2.l();
        this.d = null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Object[] objArr = this.d;
        int i = this.e;
        this.e = i + 1;
        objArr[i] = obj;
    }
}
