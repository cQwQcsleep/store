package org.jetbrains.kotlin.metadata.serialization;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/metadata/serialization/Interner;", "T", "", "parent", "<init>", "(Lorg/jetbrains/kotlin/metadata/serialization/Interner;)V", "firstIndex", "", "interned", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "allInternedObjects", "", "getAllInternedObjects", "()Ljava/util/List;", "isEmpty", "", "()Z", "find", "obj", "(Ljava/lang/Object;)Ljava/lang/Integer;", "intern", "(Ljava/lang/Object;)I", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Interner<T> {
    private final int firstIndex;
    private final HashMap<T, Integer> interned;
    private final Interner<T> parent;

    public Interner(Interner<T> interner) {
        this.parent = interner;
        this.firstIndex = interner != null ? interner.interned.size() + interner.firstIndex : 0;
        this.interned = new HashMap<>();
    }

    private final Integer find(T obj) {
        Integer numFind;
        Interner<T> interner = this.parent;
        if (interner != null) {
            int size = interner.interned.size() + this.parent.firstIndex;
            int i = this.firstIndex;
        }
        Interner<T> interner2 = this.parent;
        return (interner2 == null || (numFind = interner2.find(obj)) == null) ? this.interned.get(obj) : numFind;
    }

    public final List<T> getAllInternedObjects() {
        Set<T> setKeySet = this.interned.keySet();
        setKeySet.getClass();
        final HashMap<T, Integer> map = this.interned;
        return CollectionsKt.sortedWith(setKeySet, new Comparator() { // from class: org.jetbrains.kotlin.metadata.serialization.Interner$special$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((Integer) map.get(t), (Integer) map.get(t2));
            }
        });
    }

    public final int intern(T obj) {
        Integer numFind = find(obj);
        if (numFind != null) {
            return numFind.intValue();
        }
        int size = this.firstIndex + this.interned.size();
        this.interned.put(obj, Integer.valueOf(size));
        return size;
    }

    public final boolean isEmpty() {
        if (!this.interned.isEmpty()) {
            return false;
        }
        Interner<T> interner = this.parent;
        return interner == null || interner.isEmpty();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Interner() {
        Interner interner = null;
        this(interner, 1, interner);
    }

    public /* synthetic */ Interner(Interner interner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : interner);
    }
}
