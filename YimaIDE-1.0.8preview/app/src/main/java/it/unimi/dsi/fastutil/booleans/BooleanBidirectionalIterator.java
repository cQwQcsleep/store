package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BooleanBidirectionalIterator extends BooleanIterator, ObjectBidirectionalIterator<Boolean> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Boolean previous() {
        return Boolean.valueOf(previousBoolean());
    }

    boolean previousBoolean();
}
