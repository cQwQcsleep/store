package j$.util.stream;

import java.util.Arrays;

/* loaded from: /workspace/unpacked/classes3.dex */
final class G2 extends C2 {
    private U2 c;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.U2, j$.util.stream.a3] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.a3] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.a3] */
    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        ?? abstractC0099a3;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            abstractC0099a3 = new AbstractC0099a3();
        } else {
            abstractC0099a3 = new U2((int) j);
        }
        this.c = abstractC0099a3;
    }

    @Override // j$.util.stream.AbstractC0148k2, j$.util.stream.InterfaceC0182r2
    public final void l() {
        double[] dArr = (double[]) this.c.e();
        Arrays.sort(dArr);
        long length = dArr.length;
        InterfaceC0182r2 interfaceC0182r2 = this.a;
        interfaceC0182r2.m(length);
        int i = 0;
        if (!this.b) {
            int length2 = dArr.length;
            while (i < length2) {
                interfaceC0182r2.accept(dArr[i]);
                i++;
            }
        } else {
            int length3 = dArr.length;
            while (i < length3) {
                double d = dArr[i];
                if (interfaceC0182r2.o()) {
                    break;
                }
                interfaceC0182r2.accept(d);
                i++;
            }
        }
        interfaceC0182r2.l();
    }

    @Override // j$.util.stream.InterfaceC0168o2, j$.util.stream.InterfaceC0182r2
    public final void accept(double d) {
        this.c.accept(d);
    }
}
