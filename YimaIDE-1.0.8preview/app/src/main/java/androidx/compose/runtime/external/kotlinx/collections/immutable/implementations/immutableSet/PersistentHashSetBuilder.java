package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010)\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001dJ\u0015\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001f\u001a\u00020\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016J\u0016\u0010\"\u001a\u00020\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016J\u0016\u0010#\u001a\u00020\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016J\u0016\u0010$\u001a\u00020\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016J\u0015\u0010%\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\b\u0010&\u001a\u00020'H\u0016J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)H\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR*\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0011@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0011@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;", "E", "Lkotlin/collections/AbstractMutableSet;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet$Builder;", "set", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;)V", "value", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "ownership", "getOwnership$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "node", "getNode$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "", "modCount", "getModCount$runtime", "()I", "size", "getSize", "setSize", "(I)V", "build", "contains", "", "element", "(Ljava/lang/Object;)Z", "add", "addAll", PathTree.NAME_elements, "", "retainAll", "removeAll", "containsAll", "remove", "clear", "", "iterator", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PersistentHashSetBuilder<E> extends AbstractMutableSet<E> implements PersistentSet.Builder<E> {
    public static final int $stable = 8;
    private int modCount;
    private TrieNode<E> node;
    private MutabilityOwnership ownership = new MutabilityOwnership();
    private PersistentHashSet<E> set;
    private int size;

    public PersistentHashSetBuilder(PersistentHashSet<E> persistentHashSet) {
        this.set = persistentHashSet;
        this.node = this.set.getNode$runtime();
        this.size = this.set.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E element) {
        int size = size();
        this.node = this.node.mutableAdd(element != null ? element.hashCode() : 0, element, 0, this);
        return size != size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> elements) {
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild == null) {
            return super/*java.util.AbstractCollection*/.addAll(elements);
        }
        DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
        int size = size();
        TrieNode<E> trieNodeMutableAddAll = this.node.mutableAddAll(persistentHashSetBuild.getNode$runtime(), 0, deltaCounter, this);
        int size2 = (elements.size() + size) - deltaCounter.getCount();
        if (size != size2) {
            this.node = trieNodeMutableAddAll;
            setSize(size2);
        }
        return size != size();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet.Builder, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection.Builder
    public PersistentHashSet<E> build() {
        PersistentHashSet<E> persistentHashSet;
        if (this.node == this.set.getNode$runtime()) {
            persistentHashSet = this.set;
        } else {
            this.ownership = new MutabilityOwnership();
            persistentHashSet = new PersistentHashSet<>(this.node, size());
        }
        this.set = persistentHashSet;
        return persistentHashSet;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        TrieNode<E> eMPTY$runtime = TrieNode.INSTANCE.getEMPTY$runtime();
        eMPTY$runtime.getClass();
        this.node = eMPTY$runtime;
        setSize(0);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object element) {
        return this.node.contains(element != null ? element.hashCode() : 0, element, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        if (elements instanceof PersistentHashSet) {
            return this.node.containsAll(((PersistentHashSet) elements).getNode$runtime(), 0);
        }
        return elements instanceof PersistentHashSetBuilder ? this.node.containsAll(((PersistentHashSetBuilder) elements).node, 0) : super/*java.util.AbstractCollection*/.containsAll(elements);
    }

    /* JADX INFO: renamed from: getModCount$runtime, reason: from getter */
    public final int getModCount() {
        return this.modCount;
    }

    public final TrieNode<E> getNode$runtime() {
        return this.node;
    }

    /* JADX INFO: renamed from: getOwnership$runtime, reason: from getter */
    public final MutabilityOwnership getOwnership() {
        return this.ownership;
    }

    public int getSize() {
        return this.size;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new PersistentHashSetMutableIterator(this);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object element) {
        int size = size();
        this.node = this.node.mutableRemove(element != null ? element.hashCode() : 0, element, 0, this);
        return size != size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> elements) {
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild == null) {
            return super/*java.util.AbstractCollection*/.removeAll(elements);
        }
        DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
        int size = size();
        Object objMutableRemoveAll = this.node.mutableRemoveAll(persistentHashSetBuild.getNode$runtime(), 0, deltaCounter, this);
        int count = size - deltaCounter.getCount();
        if (count == 0) {
            clear();
        } else if (count != size) {
            objMutableRemoveAll.getClass();
            this.node = (TrieNode) objMutableRemoveAll;
            setSize(count);
        }
        return size != size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> elements) {
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild == null) {
            return super/*java.util.AbstractCollection*/.retainAll(elements);
        }
        DeltaCounter deltaCounter = new DeltaCounter(0, 1, null);
        int size = size();
        Object objMutableRetainAll = this.node.mutableRetainAll(persistentHashSetBuild.getNode$runtime(), 0, deltaCounter, this);
        int count = deltaCounter.getCount();
        if (count == 0) {
            clear();
        } else if (count != size) {
            objMutableRetainAll.getClass();
            this.node = (TrieNode) objMutableRetainAll;
            setSize(count);
        }
        return size != size();
    }

    public void setSize(int i) {
        this.size = i;
        this.modCount++;
    }
}
