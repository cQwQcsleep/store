package it.unimi.dsi.fastutil.chars;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CharListIterator extends CharBidirectionalIterator, ListIterator<Character> {
    @Override // java.util.ListIterator
    @Deprecated
    default void add(Character ch) {
        add(ch.charValue());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    default void set(Character ch) {
        set(ch.charValue());
    }

    @Override // it.unimi.dsi.fastutil.chars.CharIterator, java.util.Iterator
    @Deprecated
    default Character next() {
        return super.next();
    }

    @Override // it.unimi.dsi.fastutil.chars.CharBidirectionalIterator, it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Character previous() {
        return super.previous();
    }

    default void add(char c) {
        throw new UnsupportedOperationException();
    }

    default void set(char c) {
        throw new UnsupportedOperationException();
    }
}
