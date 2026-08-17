package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface DoubleBidirectionalIterator extends DoubleIterator, ObjectBidirectionalIterator<Double> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Double previous() {
        return Double.valueOf(previousDouble());
    }

    double previousDouble();
}
