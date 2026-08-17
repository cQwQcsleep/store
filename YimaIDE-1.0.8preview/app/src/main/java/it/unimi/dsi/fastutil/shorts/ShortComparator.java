package it.unimi.dsi.fastutil.shorts;

import defpackage.x9d;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ShortComparator extends Comparator<Short> {
    static /* synthetic */ int z(ShortComparator shortComparator, ShortComparator shortComparator2, short s, short s2) {
        int iCompare = shortComparator.compare(s, s2);
        return iCompare == 0 ? shortComparator2.compare(s, s2) : iCompare;
    }

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Short sh, Short sh2) {
        return compare(sh.shortValue(), sh2.shortValue());
    }

    int compare(short s, short s2);

    @Override // java.util.Comparator
    default Comparator<Short> thenComparing(Comparator<? super Short> comparator) {
        return comparator instanceof ShortComparator ? thenComparing((ShortComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Short> reversed2() {
        return ShortComparators.oppositeComparator(this);
    }

    default ShortComparator thenComparing(ShortComparator shortComparator) {
        return new x9d(this, shortComparator);
    }
}
