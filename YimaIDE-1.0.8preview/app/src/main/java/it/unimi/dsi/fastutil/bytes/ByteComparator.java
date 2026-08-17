package it.unimi.dsi.fastutil.bytes;

import defpackage.s31;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface ByteComparator extends Comparator<Byte> {
    static /* synthetic */ int R(ByteComparator byteComparator, ByteComparator byteComparator2, byte b, byte b2) {
        int iCompare = byteComparator.compare(b, b2);
        return iCompare == 0 ? byteComparator2.compare(b, b2) : iCompare;
    }

    int compare(byte b, byte b2);

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Byte b, Byte b2) {
        return compare(b.byteValue(), b2.byteValue());
    }

    @Override // java.util.Comparator
    default Comparator<Byte> thenComparing(Comparator<? super Byte> comparator) {
        return comparator instanceof ByteComparator ? thenComparing((ByteComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Byte> reversed2() {
        return ByteComparators.oppositeComparator(this);
    }

    default ByteComparator thenComparing(ByteComparator byteComparator) {
        return new s31(this, byteComparator);
    }
}
