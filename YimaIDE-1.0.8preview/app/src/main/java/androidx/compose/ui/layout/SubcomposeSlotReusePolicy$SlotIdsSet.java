package androidx.compose.ui.layout;

import androidx.collection.MutableOrderedScatterSet;
import androidx.collection.OrderedScatterSetKt;
import androidx.collection.SieveCacheKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010)\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u001b\b\u0000\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0016J\u0013\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\u0017\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0002\b\u0017J\u0011\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0019H\u0096\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002J\u0016\u0010\u001b\u001a\u00020\u00102\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u001c\u0010\u001b\u001a\u00020\u00102\u0014\u0010\u001d\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00100\u001eJ\u0016\u0010\u001f\u001a\u00020\u00102\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u001c\u0010\u001f\u001a\u00020\u00102\u0014\u0010\u001d\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00100\u001eJ\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\fJ\u001c\u0010$\u001a\u00020!2\u0014\u0010%\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020!0\u001eJ\u001f\u0010&\u001a\u00020!2\u0014\u0010%\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020!0\u001eH\u0086\bR$\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006'"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "", "", "set", "Landroidx/collection/MutableOrderedScatterSet;", "<init>", "(Landroidx/collection/MutableOrderedScatterSet;)V", "getSet$annotations", "()V", "getSet", "()Landroidx/collection/MutableOrderedScatterSet;", "size", "", "getSize", "()I", "isEmpty", "", "containsAll", "elements", "contains", "element", "add", "slotId", "add$ui", "iterator", "", "remove", "removeAll", "slotIds", "predicate", "Lkotlin/Function1;", "retainAll", "clear", "", "trimToSize", "maxSlotsToRetainForReuse", "forEach", "block", "fastForEach", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SubcomposeSlotReusePolicy$SlotIdsSet implements Collection<Object>, KMappedMarker {
    public static final int $stable = 8;
    private final MutableOrderedScatterSet<Object> set;

    public /* synthetic */ SubcomposeSlotReusePolicy$SlotIdsSet(MutableOrderedScatterSet mutableOrderedScatterSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? OrderedScatterSetKt.mutableOrderedScatterSetOf() : mutableOrderedScatterSet);
    }

    public static /* synthetic */ void getSet$annotations() {
    }

    @Override // java.util.Collection
    /* JADX INFO: renamed from: add$ui, reason: merged with bridge method [inline-methods] */
    public final boolean add(Object slotId) {
        return this.set.add(slotId);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        this.set.clear();
    }

    @Override // java.util.Collection
    public boolean contains(Object element) {
        return this.set.contains(element);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (!this.set.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final void fastForEach(Function1<Object, Unit> block) {
        MutableOrderedScatterSet<Object> set = getSet();
        Object[] objArr = set.elements;
        long[] jArr = set.nodes;
        int i = set.tail;
        while (i != Integer.MAX_VALUE) {
            int i2 = (int) ((jArr[i] >> 31) & SieveCacheKt.NodeLinkMask);
            block.invoke(objArr[i]);
            i = i2;
        }
    }

    public final void forEach(Function1<Object, Unit> block) {
        MutableOrderedScatterSet<Object> mutableOrderedScatterSet = this.set;
        Object[] objArr = mutableOrderedScatterSet.elements;
        long[] jArr = mutableOrderedScatterSet.nodes;
        int i = mutableOrderedScatterSet.tail;
        while (i != Integer.MAX_VALUE) {
            int i2 = (int) ((jArr[i] >> 31) & SieveCacheKt.NodeLinkMask);
            block.invoke(objArr[i]);
            i = i2;
        }
    }

    public final MutableOrderedScatterSet<Object> getSet() {
        return this.set;
    }

    public int getSize() {
        return this.set.get_size();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.set.asMutableSet().iterator();
    }

    @Override // java.util.Collection
    public final boolean remove(Object slotId) {
        return this.set.remove(slotId);
    }

    public final boolean removeAll(Function1<Object, Boolean> predicate) {
        int i = this.set.get_size();
        MutableOrderedScatterSet<Object> mutableOrderedScatterSet = this.set;
        Object[] objArr = mutableOrderedScatterSet.elements;
        long[] jArr = mutableOrderedScatterSet.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i2 << 3) + i4;
                            if (((Boolean) predicate.invoke(objArr[i5])).booleanValue()) {
                                mutableOrderedScatterSet.removeElementAt(i5);
                            }
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return i != this.set.get_size();
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super Object> predicate) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean retainAll(Function1<Object, Boolean> predicate) {
        MutableOrderedScatterSet<Object> mutableOrderedScatterSet = this.set;
        Object[] objArr = mutableOrderedScatterSet.elements;
        int i = mutableOrderedScatterSet.get_size();
        long[] jArr = mutableOrderedScatterSet.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i2 << 3) + i4;
                            if (!((Boolean) predicate.invoke(objArr[i5])).booleanValue()) {
                                mutableOrderedScatterSet.removeElementAt(i5);
                            }
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return i != mutableOrderedScatterSet.get_size();
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public final void trimToSize(int maxSlotsToRetainForReuse) {
        this.set.trimToSize(maxSlotsToRetainForReuse);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public SubcomposeSlotReusePolicy$SlotIdsSet(MutableOrderedScatterSet<Object> mutableOrderedScatterSet) {
        this.set = mutableOrderedScatterSet;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SubcomposeSlotReusePolicy$SlotIdsSet() {
        MutableOrderedScatterSet mutableOrderedScatterSet = null;
        this(mutableOrderedScatterSet, 1, mutableOrderedScatterSet);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection<?> slotIds) {
        return this.set.retainAll((Collection<? extends Object>) slotIds);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection<?> slotIds) {
        return this.set.remove(slotIds);
    }
}
