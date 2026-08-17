package it.unimi.dsi.fastutil.floats;

import defpackage.zh5;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface FloatComparator extends Comparator<Float> {
    static /* synthetic */ int D(FloatComparator floatComparator, FloatComparator floatComparator2, float f, float f2) {
        int iCompare = floatComparator.compare(f, f2);
        return iCompare == 0 ? floatComparator2.compare(f, f2) : iCompare;
    }

    int compare(float f, float f2);

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Float f, Float f2) {
        return compare(f.floatValue(), f2.floatValue());
    }

    @Override // java.util.Comparator
    default Comparator<Float> thenComparing(Comparator<? super Float> comparator) {
        return comparator instanceof FloatComparator ? thenComparing((FloatComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Float> reversed2() {
        return FloatComparators.oppositeComparator(this);
    }

    default FloatComparator thenComparing(FloatComparator floatComparator) {
        return new zh5(this, floatComparator);
    }
}
