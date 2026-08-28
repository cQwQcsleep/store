package j$.util.stream;

import java.util.Arrays;

/* loaded from: /workspace/unpacked/classes3.dex */
final class P2 extends D2 {
    private int[] c;
    private int d;

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.c = new int[(int) j];
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void l() {
        int i = 0;
        Arrays.sort(this.c, 0, this.d);
        long j = this.d;
        InterfaceC0182r2 interfaceC0182r2 = this.a;
        interfaceC0182r2.m(j);
        if (!this.b) {
            while (i < this.d) {
                interfaceC0182r2.accept(this.c[i]);
                i++;
            }
        } else {
            while (i < this.d && !interfaceC0182r2.o()) {
                interfaceC0182r2.accept(this.c[i]);
                i++;
            }
        }
        interfaceC0182r2.l();
        this.c = null;
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        int[] iArr = this.c;
        int i2 = this.d;
        this.d = i2 + 1;
        iArr[i2] = i;
    }
}
