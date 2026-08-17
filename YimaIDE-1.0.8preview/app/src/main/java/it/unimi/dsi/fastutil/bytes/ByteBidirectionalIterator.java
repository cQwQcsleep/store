package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ByteBidirectionalIterator extends ByteIterator, ObjectBidirectionalIterator<Byte> {
    @Override // it.unimi.dsi.fastutil.BidirectionalIterator
    @Deprecated
    default Byte previous() {
        return Byte.valueOf(previousByte());
    }

    byte previousByte();
}
