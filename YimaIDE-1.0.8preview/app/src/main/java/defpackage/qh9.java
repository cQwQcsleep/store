package defpackage;

import it.unimi.dsi.fastutil.longs.LongComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class qh9 implements LongComparator, Serializable {
    public final /* synthetic */ LongComparator b;
    public final /* synthetic */ LongComparator c;

    public /* synthetic */ qh9(LongComparator longComparator, LongComparator longComparator2) {
        this.b = longComparator;
        this.c = longComparator2;
    }

    @Override // it.unimi.dsi.fastutil.longs.LongComparator
    public final int compare(long j, long j2) {
        return LongComparator.P(this.b, this.c, j, j2);
    }
}
