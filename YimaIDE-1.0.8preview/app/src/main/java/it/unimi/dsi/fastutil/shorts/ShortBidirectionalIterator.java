package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ShortBidirectionalIterator extends ObjectBidirectionalIterator<Short>, ShortIterator {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Short previous() {
        return Short.valueOf(previousShort());
    }

    short previousShort();
}
