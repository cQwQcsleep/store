package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface FloatBidirectionalIterator extends FloatIterator, ObjectBidirectionalIterator<Float> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Float previous() {
        return Float.valueOf(previousFloat());
    }

    float previousFloat();
}
