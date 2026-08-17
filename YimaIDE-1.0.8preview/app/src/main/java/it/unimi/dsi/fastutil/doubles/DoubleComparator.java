package it.unimi.dsi.fastutil.doubles;

import defpackage.bw3;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface DoubleComparator extends Comparator<Double> {
    static /* synthetic */ int d(DoubleComparator doubleComparator, DoubleComparator doubleComparator2, double d, double d2) {
        int iCompare = doubleComparator.compare(d, d2);
        return iCompare == 0 ? doubleComparator2.compare(d, d2) : iCompare;
    }

    int compare(double d, double d2);

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Double d, Double d2) {
        return compare(d.doubleValue(), d2.doubleValue());
    }

    @Override // java.util.Comparator
    default Comparator<Double> thenComparing(Comparator<? super Double> comparator) {
        return comparator instanceof DoubleComparator ? thenComparing((DoubleComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Double> reversed2() {
        return DoubleComparators.oppositeComparator(this);
    }

    default DoubleComparator thenComparing(DoubleComparator doubleComparator) {
        return new bw3(this, doubleComparator);
    }
}
