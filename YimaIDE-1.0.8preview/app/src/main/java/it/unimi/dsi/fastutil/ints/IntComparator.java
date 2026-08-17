package it.unimi.dsi.fastutil.ints;

import defpackage.zr6;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface IntComparator extends Comparator<Integer> {
    static /* synthetic */ int j0(IntComparator intComparator, IntComparator intComparator2, int i, int i2) {
        int iCompare = intComparator.compare(i, i2);
        return iCompare == 0 ? intComparator2.compare(i, i2) : iCompare;
    }

    int compare(int i, int i2);

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Integer num, Integer num2) {
        return compare(num.intValue(), num2.intValue());
    }

    @Override // java.util.Comparator
    default Comparator<Integer> thenComparing(Comparator<? super Integer> comparator) {
        return comparator instanceof IntComparator ? thenComparing((IntComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Integer> reversed2() {
        return IntComparators.oppositeComparator(this);
    }

    default IntComparator thenComparing(IntComparator intComparator) {
        return new zr6(this, intComparator);
    }
}
