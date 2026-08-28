package j$.util;

import java.util.function.IntConsumer;

/* renamed from: j$.util.j, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0086j implements IntConsumer {
    private long count;
    private long sum;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.com.android.tools.r8.a.e(this, intConsumer);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.count++;
        this.sum += i;
        this.min = Math.min(this.min, i);
        this.max = Math.max(this.max, i);
    }

    public final void b(C0086j c0086j) {
        this.count += c0086j.count;
        this.sum += c0086j.sum;
        this.min = Math.min(this.min, c0086j.min);
        this.max = Math.max(this.max, c0086j.max);
    }

    public final String toString() {
        String simpleName = C0086j.class.getSimpleName();
        Long lValueOf = Long.valueOf(this.count);
        Long lValueOf2 = Long.valueOf(this.sum);
        Integer numValueOf = Integer.valueOf(this.min);
        long j = this.count;
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", simpleName, lValueOf, lValueOf2, numValueOf, Double.valueOf(j > 0 ? this.sum / j : 0.0d), Integer.valueOf(this.max));
    }
}
