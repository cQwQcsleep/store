package j$.util;

import j$.util.function.LongConsumer$CC;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* renamed from: j$.util.l, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0088l implements LongConsumer, IntConsumer {
    private long count;
    private long sum;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return LongConsumer$CC.$default$andThen(this, longConsumer);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        accept(i);
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j) {
        this.count++;
        this.sum += j;
        this.min = Math.min(this.min, j);
        this.max = Math.max(this.max, j);
    }

    public final void b(C0088l c0088l) {
        this.count += c0088l.count;
        this.sum += c0088l.sum;
        this.min = Math.min(this.min, c0088l.min);
        this.max = Math.max(this.max, c0088l.max);
    }

    public final String toString() {
        String simpleName = C0088l.class.getSimpleName();
        Long lValueOf = Long.valueOf(this.count);
        Long lValueOf2 = Long.valueOf(this.sum);
        Long lValueOf3 = Long.valueOf(this.min);
        long j = this.count;
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", simpleName, lValueOf, lValueOf2, lValueOf3, Double.valueOf(j > 0 ? this.sum / j : 0.0d), Long.valueOf(this.max));
    }
}
