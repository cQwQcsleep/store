package j$.util.stream;

import java.util.Arrays;

/* loaded from: /workspace/unpacked/classes3.dex */
final class I2 extends E2 {
    private Y2 c;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.Y2, j$.util.stream.a3] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.a3] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.a3] */
    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final void m(long j) {
        ?? abstractC0099a3;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            abstractC0099a3 = new AbstractC0099a3();
        } else {
            abstractC0099a3 = new Y2((int) j);
        }
        this.c = abstractC0099a3;
    }

    @Override // j$.util.stream.AbstractC0158m2, j$.util.stream.InterfaceC0182r2
    public final void l() {
        long[] jArr = (long[]) this.c.e();
        Arrays.sort(jArr);
        long length = jArr.length;
        InterfaceC0182r2 interfaceC0182r2 = this.a;
        interfaceC0182r2.m(length);
        int i = 0;
        if (!this.b) {
            int length2 = jArr.length;
            while (i < length2) {
                interfaceC0182r2.accept(jArr[i]);
                i++;
            }
        } else {
            int length3 = jArr.length;
            while (i < length3) {
                long j = jArr[i];
                if (interfaceC0182r2.o()) {
                    break;
                }
                interfaceC0182r2.accept(j);
                i++;
            }
        }
        interfaceC0182r2.l();
    }

    @Override // j$.util.stream.InterfaceC0178q2, java.util.function.LongConsumer
    public final void accept(long j) {
        this.c.accept(j);
    }
}
