package j$.util.stream;

import java.util.Arrays;

/* loaded from: /workspace/unpacked/classes3.dex */
final class H2 extends D2 {
    private W2 c;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.W2, j$.util.stream.a3] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.a3] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.a3] */
    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        ?? abstractC0099a3;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            abstractC0099a3 = new AbstractC0099a3();
        } else {
            abstractC0099a3 = new W2((int) j);
        }
        this.c = abstractC0099a3;
    }

    @Override // j$.util.stream.AbstractC0153l2, j$.util.stream.InterfaceC0182r2
    public final void l() {
        int[] iArr = (int[]) this.c.e();
        Arrays.sort(iArr);
        long length = iArr.length;
        InterfaceC0182r2 interfaceC0182r2 = this.a;
        interfaceC0182r2.m(length);
        int i = 0;
        if (!this.b) {
            int length2 = iArr.length;
            while (i < length2) {
                interfaceC0182r2.accept(iArr[i]);
                i++;
            }
        } else {
            int length3 = iArr.length;
            while (i < length3) {
                int i2 = iArr[i];
                if (interfaceC0182r2.o()) {
                    break;
                }
                interfaceC0182r2.accept(i2);
                i++;
            }
        }
        interfaceC0182r2.l();
    }

    @Override // j$.util.stream.InterfaceC0173p2, j$.util.stream.InterfaceC0182r2
    public final void accept(int i) {
        this.c.accept(i);
    }
}
