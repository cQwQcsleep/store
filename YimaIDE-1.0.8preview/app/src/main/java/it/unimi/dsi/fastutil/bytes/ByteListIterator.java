package it.unimi.dsi.fastutil.bytes;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ByteListIterator extends ByteBidirectionalIterator, ListIterator<Byte> {
    @Override // java.util.ListIterator
    @Deprecated
    default void add(Byte b) {
        add(b.byteValue());
    }

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    default void set(Byte b) {
        set(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.ByteIterator, java.util.Iterator
    @Deprecated
    default Byte next() {
        return super.next();
    }

    @Override // it.unimi.dsi.fastutil.bytes.ByteBidirectionalIterator, it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Byte previous() {
        return super.previous();
    }

    default void add(byte b) {
        throw new UnsupportedOperationException();
    }

    default void set(byte b) {
        throw new UnsupportedOperationException();
    }
}
