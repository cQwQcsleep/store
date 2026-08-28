package j$.util;

import java.util.Collection;
import java.util.SortedSet;

/* loaded from: /workspace/unpacked/classes3.dex */
final class E extends g0 {
    final /* synthetic */ SortedSet f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    E(SortedSet sortedSet, Collection collection) {
        super(collection, 21);
        this.f = sortedSet;
    }

    @Override // j$.util.g0, j$.util.U
    public final java.util.Comparator getComparator() {
        return this.f.comparator();
    }
}
