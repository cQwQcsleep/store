package j$.util.stream;

import java.util.function.IntConsumer;

/* renamed from: j$.util.stream.k3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0149k3 extends AbstractC0159m3 implements IntConsumer {
    final int[] c;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    C0149k3(int i) {
        this.c = new int[i];
    }

    @Override // j$.util.stream.AbstractC0159m3
    public final void b(Object obj, long j) {
        IntConsumer intConsumer = (IntConsumer) obj;
        for (int i = 0; i < j; i++) {
            intConsumer.accept(this.c[i]);
        }
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        int i2 = this.b;
        this.b = i2 + 1;
        this.c[i2] = i;
    }
}
