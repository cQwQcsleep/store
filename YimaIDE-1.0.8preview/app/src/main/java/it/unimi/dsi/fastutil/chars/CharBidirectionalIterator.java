package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CharBidirectionalIterator extends CharIterator, ObjectBidirectionalIterator<Character> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Character previous() {
        return Character.valueOf(previousChar());
    }

    char previousChar();
}
