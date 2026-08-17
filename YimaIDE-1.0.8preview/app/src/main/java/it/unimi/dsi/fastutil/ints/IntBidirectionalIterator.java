package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IntBidirectionalIterator extends IntIterator, ObjectBidirectionalIterator<Integer> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Integer previous() {
        return Integer.valueOf(previousInt());
    }

    int previousInt();
}
