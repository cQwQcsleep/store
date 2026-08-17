package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LongBidirectionalIterator extends LongIterator, ObjectBidirectionalIterator<Long> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Long previous() {
        return Long.valueOf(previousLong());
    }

    long previousLong();
}
