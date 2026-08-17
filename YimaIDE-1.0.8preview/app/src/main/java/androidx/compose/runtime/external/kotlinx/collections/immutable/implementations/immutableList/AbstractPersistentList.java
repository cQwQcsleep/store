package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010*\n\u0000\b!\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u000e\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00172\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0096\u0002J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0016¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/AbstractPersistentList;", "E", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "Lkotlin/collections/AbstractList;", "<init>", "()V", "subList", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/ImmutableList;", "fromIndex", "", "toIndex", "addAll", PathTree.NAME_elements, "", "index", "c", "remove", "element", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "removeAll", "retainAll", "clear", "contains", "", "(Ljava/lang/Object;)Z", "containsAll", "iterator", "", "listIterator", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class AbstractPersistentList<E> extends AbstractList<E> implements PersistentList<E> {
    public static final int $stable = 8;

    public static boolean d(Collection collection, Object obj) {
        return collection.contains(obj);
    }

    public static boolean n(Collection collection, Object obj) {
        return !collection.contains(obj);
    }

    @Override // java.util.List, java.util.Collection, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> addAll(Collection<? extends E> elements) {
        PersistentList.Builder<E> builder = builder();
        builder.addAll(elements);
        return builder.build();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Collection<?> collection = elements;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List, java.util.Collection, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> remove(E element) {
        int iIndexOf = indexOf(element);
        return iIndexOf != -1 ? removeAt(iIndexOf) : this;
    }

    @Override // java.util.List, java.util.Collection, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> removeAll(final Collection<? extends E> elements) {
        return removeAll((Function1) new Function1() { // from class: fp
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AbstractPersistentList.d(elements, obj));
            }
        });
    }

    @Override // java.util.List, java.util.Collection, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> retainAll(final Collection<? extends E> elements) {
        return removeAll((Function1) new Function1() { // from class: dp
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AbstractPersistentList.n(elements, obj));
            }
        });
    }

    @Override // java.util.List, java.util.Collection, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> clear() {
        return UtilsKt.persistentVectorOf();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList, java.util.List
    public ImmutableList<E> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList, java.util.List
    public PersistentList<E> addAll(int index, Collection<? extends E> c) {
        PersistentList.Builder<E> builder = builder();
        builder.addAll(index, c);
        return builder.build();
    }
}
