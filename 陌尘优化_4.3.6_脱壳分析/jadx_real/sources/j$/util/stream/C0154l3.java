package j$.util.stream;

import j$.util.function.LongConsumer$CC;
import java.util.function.LongConsumer;

/* renamed from: j$.util.stream.l3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0154l3 extends AbstractC0159m3 implements LongConsumer {
    final long[] c;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    C0154l3(int i) {
        this.c = new long[i];
    }

    @Override // j$.util.stream.AbstractC0159m3
    public final void b(Object obj, long j) {
        LongConsumer longConsumer = (LongConsumer) obj;
        for (int i = 0; i < j; i++) {
            longConsumer.accept(this.c[i]);
        }
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        int i = this.b;
        this.b = i + 1;
        this.c[i] = j;
    }
}
