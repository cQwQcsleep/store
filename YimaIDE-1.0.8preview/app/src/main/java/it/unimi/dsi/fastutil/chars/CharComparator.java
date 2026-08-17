package it.unimi.dsi.fastutil.chars;

import defpackage.sf1;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface CharComparator extends Comparator<Character> {
    static /* synthetic */ int n(CharComparator charComparator, CharComparator charComparator2, char c, char c2) {
        int iCompare = charComparator.compare(c, c2);
        return iCompare == 0 ? charComparator2.compare(c, c2) : iCompare;
    }

    int compare(char c, char c2);

    @Override // java.util.Comparator
    @Deprecated
    default int compare(Character ch, Character ch2) {
        return compare(ch.charValue(), ch2.charValue());
    }

    @Override // java.util.Comparator
    default Comparator<Character> thenComparing(Comparator<? super Character> comparator) {
        return comparator instanceof CharComparator ? thenComparing((CharComparator) comparator) : super.thenComparing(comparator);
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: reversed, reason: merged with bridge method [inline-methods] */
    default Comparator<Character> reversed2() {
        return CharComparators.oppositeComparator(this);
    }

    default CharComparator thenComparing(CharComparator charComparator) {
        return new sf1(this, charComparator);
    }
}
