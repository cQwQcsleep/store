package androidx.compose.material3.carousel;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0006\b\u0001\u0018\u0000 @2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001@B\u0017\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020#J\u000e\u0010&\u001a\u00020\u00072\u0006\u0010%\u001a\u00020#J\u000e\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020#J\u000e\u0010)\u001a\u00020\u00022\u0006\u0010(\u001a\u00020#J\u0013\u0010*\u001a\u00020 2\b\u0010+\u001a\u0004\u0018\u00010,H\u0096\u0002J\b\u0010-\u001a\u00020\u0007H\u0016J\u0011\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020\u0002H\u0096\u0003J\u0017\u00100\u001a\u00020 2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000202H\u0096\u0001J\u0011\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u0007H\u0096\u0003J\u0011\u00105\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0002H\u0096\u0001J\t\u00106\u001a\u00020 H\u0096\u0001J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000208H\u0096\u0003J\u0011\u00109\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0002H\u0096\u0001J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00020;H\u0096\u0001J\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00020;2\u0006\u00104\u001a\u00020\u0007H\u0096\u0001J\u001f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010=\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u0007H\u0096\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0011\u0010\u0013\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u0011\u0010\u0017\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\fR\u0011\u0010\u0019\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\tR\u0011\u0010\u001b\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\fR\u0011\u0010\u001d\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\tR\u0012\u0010%\u001a\u00020\u0007X\u0096\u0005¢\u0006\u0006\u001a\u0004\b?\u0010\t¨\u0006A"}, d2 = {"Landroidx/compose/material3/carousel/KeylineList;", "", "Landroidx/compose/material3/carousel/Keyline;", "keylines", "<init>", "(Ljava/util/List;)V", "pivotIndex", "", "getPivotIndex", "()I", "pivot", "getPivot", "()Landroidx/compose/material3/carousel/Keyline;", "firstNonAnchorIndex", "getFirstNonAnchorIndex", "firstNonAnchor", "getFirstNonAnchor", "lastNonAnchorIndex", "getLastNonAnchorIndex", "lastNonAnchor", "getLastNonAnchor", "firstFocalIndex", "getFirstFocalIndex", "firstFocal", "getFirstFocal", "lastFocalIndex", "getLastFocalIndex", "lastFocal", "getLastFocal", "focalCount", "getFocalCount", "isFirstFocalItemAtStartOfContainer", "", "isLastFocalItemAtEndOfContainer", "carouselMainAxisSize", "", "firstIndexAfterFocalRangeWithSize", "size", "lastIndexBeforeFocalRangeWithSize", "getKeylineBefore", "unadjustedOffset", "getKeylineAfter", "equals", "other", "", "hashCode", "contains", "element", "containsAll", PathTree.NAME_elements, "", "get", "index", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "getSize", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class KeylineList implements List<Keyline>, KMappedMarker {
    private final /* synthetic */ List<Keyline> $$delegate_0;
    private final int firstFocalIndex;
    private final int firstNonAnchorIndex;
    private final int focalCount;
    private final int lastFocalIndex;
    private final int lastNonAnchorIndex;
    private final int pivotIndex;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KeylineList Empty = new KeylineList(CollectionsKt.emptyList());

    public KeylineList(List<Keyline> list) {
        int iNextIndex;
        int iNextIndex2;
        this.$$delegate_0 = list;
        Iterator<Keyline> it = iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            iNextIndex = -1;
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (it.next().isPivot()) {
                break;
            } else {
                i2++;
            }
        }
        this.pivotIndex = i2;
        Iterator<Keyline> it2 = iterator();
        int i3 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i3 = -1;
                break;
            } else if (!it2.next().isAnchor()) {
                break;
            } else {
                i3++;
            }
        }
        this.firstNonAnchorIndex = i3;
        ListIterator<Keyline> listIterator = listIterator(size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex2 = -1;
                break;
            } else if (!listIterator.previous().isAnchor()) {
                iNextIndex2 = listIterator.nextIndex();
                break;
            }
        }
        this.lastNonAnchorIndex = iNextIndex2;
        Iterator<Keyline> it3 = iterator();
        while (true) {
            if (!it3.hasNext()) {
                i = -1;
                break;
            } else if (it3.next().isFocal()) {
                break;
            } else {
                i++;
            }
        }
        this.firstFocalIndex = i;
        ListIterator<Keyline> listIterator2 = listIterator(size());
        while (listIterator2.hasPrevious()) {
            if (listIterator2.previous().isFocal()) {
                iNextIndex = listIterator2.nextIndex();
                break;
            }
        }
        this.lastFocalIndex = iNextIndex;
        this.focalCount = (iNextIndex - this.firstFocalIndex) + 1;
    }

    /* JADX INFO: renamed from: add, reason: avoid collision after fix types in other method */
    public void add2(int i, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends Keyline> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void addFirst(Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void addLast(Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Keyline) {
            return contains((Keyline) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        return this.$$delegate_0.containsAll(elements);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeylineList)) {
            return false;
        }
        KeylineList keylineList = (KeylineList) other;
        if (size() != keylineList.size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (!Intrinsics.areEqual(get(i), keylineList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public final int firstIndexAfterFocalRangeWithSize(float size) {
        Object next;
        Iterator it = new IntRange(this.lastFocalIndex, CollectionsKt.getLastIndex(this)).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (get(((Number) next).intValue()).getSize() != size);
        Integer num = (Integer) next;
        return num != null ? num.intValue() : CollectionsKt.getLastIndex(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public Keyline get(int index) {
        return this.$$delegate_0.get(index);
    }

    public final Keyline getFirstFocal() {
        Keyline keyline = (Keyline) CollectionsKt.getOrNull(this, this.firstFocalIndex);
        if (keyline != null) {
            return keyline;
        }
        hb9.a("All KeylineLists must have at least one focal keyline");
        return null;
    }

    public final int getFirstFocalIndex() {
        return this.firstFocalIndex;
    }

    public final Keyline getFirstNonAnchor() {
        return get(this.firstNonAnchorIndex);
    }

    public final int getFirstNonAnchorIndex() {
        return this.firstNonAnchorIndex;
    }

    public final int getFocalCount() {
        return this.focalCount;
    }

    public final Keyline getKeylineAfter(float unadjustedOffset) {
        Keyline keyline;
        int size = size();
        int i = 0;
        while (true) {
            if (i >= size) {
                keyline = null;
                break;
            }
            keyline = get(i);
            if (keyline.getUnadjustedOffset() >= unadjustedOffset) {
                break;
            }
            i++;
        }
        Keyline keyline2 = keyline;
        return keyline2 == null ? (Keyline) CollectionsKt.last(this) : keyline2;
    }

    public final Keyline getKeylineBefore(float unadjustedOffset) {
        int size = size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                Keyline keyline = get(size);
                if (keyline.getUnadjustedOffset() < unadjustedOffset) {
                    return keyline;
                }
                if (i >= 0) {
                    size = i;
                }
            }
        }
        return (Keyline) CollectionsKt.first(this);
    }

    public final Keyline getLastFocal() {
        Keyline keyline = (Keyline) CollectionsKt.getOrNull(this, this.lastFocalIndex);
        if (keyline != null) {
            return keyline;
        }
        hb9.a("All KeylineLists must have at least one focal keyline");
        return null;
    }

    public final int getLastFocalIndex() {
        return this.lastFocalIndex;
    }

    public final Keyline getLastNonAnchor() {
        return get(this.lastNonAnchorIndex);
    }

    public final int getLastNonAnchorIndex() {
        return this.lastNonAnchorIndex;
    }

    public final Keyline getPivot() {
        return get(this.pivotIndex);
    }

    public final int getPivotIndex() {
        return this.pivotIndex;
    }

    public int getSize() {
        return this.$$delegate_0.size();
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        int size = size();
        int iHashCode = 0;
        for (int i = 0; i < size; i++) {
            iHashCode += get(i).hashCode() * 31;
        }
        return iHashCode;
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Keyline) {
            return indexOf((Keyline) obj);
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.$$delegate_0.isEmpty();
    }

    public final boolean isFirstFocalItemAtStartOfContainer() {
        return getFirstFocal().getOffset() - (getFirstFocal().getSize() / 2.0f) >= 0.0f && Intrinsics.areEqual(getFirstFocal(), getFirstNonAnchor());
    }

    public final boolean isLastFocalItemAtEndOfContainer(float carouselMainAxisSize) {
        return getLastFocal().getOffset() + (getLastFocal().getSize() / 2.0f) <= carouselMainAxisSize && Intrinsics.areEqual(getLastFocal(), getLastNonAnchor());
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Keyline> iterator() {
        return this.$$delegate_0.iterator();
    }

    public final int lastIndexBeforeFocalRangeWithSize(float size) {
        Object next;
        Iterator it = RangesKt.downTo(this.firstFocalIndex - 1, 0).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (get(((Number) next).intValue()).getSize() != size);
        Integer num = (Integer) next;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Keyline) {
            return lastIndexOf((Keyline) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<Keyline> listIterator() {
        return this.$$delegate_0.listIterator();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public Keyline remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Keyline removeFirst() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Keyline removeLast() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<Keyline> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: renamed from: set, reason: avoid collision after fix types in other method */
    public Keyline set2(int i, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.List
    public void sort(Comparator<? super Keyline> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<Keyline> subList(int fromIndex, int toIndex) {
        return this.$$delegate_0.subList(fromIndex, toIndex);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/material3/carousel/KeylineList$Companion;", "", "<init>", "()V", "Empty", "Landroidx/compose/material3/carousel/KeylineList;", "getEmpty", "()Landroidx/compose/material3/carousel/KeylineList;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KeylineList getEmpty() {
            return KeylineList.Empty;
        }

        private Companion() {
        }
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    @Override // java.util.List
    public ListIterator<Keyline> listIterator(int index) {
        return this.$$delegate_0.listIterator(index);
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ void add(int i, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends Keyline> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* bridge */ /* synthetic */ void addFirst(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* bridge */ /* synthetic */ void addLast(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Keyline remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: renamed from: removeFirst, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m1363removeFirst() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: renamed from: removeLast, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m1364removeLast() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Keyline set(int i, Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(Keyline keyline) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Keyline element) {
        return this.$$delegate_0.contains(element);
    }

    public int indexOf(Keyline element) {
        return this.$$delegate_0.indexOf(element);
    }

    public int lastIndexOf(Keyline element) {
        return this.$$delegate_0.lastIndexOf(element);
    }
}
