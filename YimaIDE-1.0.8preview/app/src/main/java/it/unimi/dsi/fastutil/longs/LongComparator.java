package it.unimi.dsi.fastutil.longs;

import defpackage.qh9;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface LongComparator extends Comparator<Long> {
    static /* synthetic */ int P(LongComparator longComparator, LongComparator longComparator2, long j, long j2) {
        int iCompare = longComparator.compare(j, j2);
        return iCompare == 0 ? longComparator2.compare(j, j2) : iCompare;
    }

    int compare(long j, long j2);

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Long l, Long l2) {
        return compare(l.longValue(), l2.longValue());
    }

    @Override // java.util.Comparator
    default Comparator<Long> thenComparing(Comparator<? super Long> comparator) {
        return comparator instanceof LongComparator ? thenComparing((LongComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Long> reversed2() {
        return LongComparators.oppositeComparator(this);
    }

    default LongComparator thenComparing(LongComparator longComparator) {
        return new qh9(this, longComparator);
    }
}
