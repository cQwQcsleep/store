package it.unimi.dsi.fastutil.booleans;

import defpackage.ly0;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface BooleanComparator extends Comparator<Boolean> {
    static /* synthetic */ int e0(BooleanComparator booleanComparator, BooleanComparator booleanComparator2, boolean z, boolean z2) {
        int iCompare = booleanComparator.compare(z, z2);
        return iCompare == 0 ? booleanComparator2.compare(z, z2) : iCompare;
    }

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Boolean bool, Boolean bool2) {
        return compare(bool.booleanValue(), bool2.booleanValue());
    }

    int compare(boolean z, boolean z2);

    @Override // java.util.Comparator
    default Comparator<Boolean> thenComparing(Comparator<? super Boolean> comparator) {
        return comparator instanceof BooleanComparator ? thenComparing((BooleanComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Boolean> reversed2() {
        return BooleanComparators.oppositeComparator(this);
    }

    default BooleanComparator thenComparing(BooleanComparator booleanComparator) {
        return new ly0(this, booleanComparator);
    }
}
