package io.vavr.collection;

import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class Comparators {
    private Comparators() {
    }

    public static <U> Comparator<U> naturalComparator() {
        return NaturalComparator.instance();
    }
}
