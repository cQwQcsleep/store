package com.intellij.util.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class FreezableArrayList<T> extends ArrayList<T> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "c";
        } else {
            objArr[0] = "com/intellij/util/containers/FreezableArrayList";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/containers/FreezableArrayList";
        } else {
            objArr[1] = "freeze";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FreezableArrayList(Collection<? extends T> collection) {
        super(collection);
        if (collection == null) {
            $$$reportNull$$$0(0);
        }
    }

    private void checkForFrozen() {
        if (((ArrayList) this).modCount != -1) {
            return;
        }
        c41.a("This list is unmodifiable. For a temporary workaround, please run IDE in non-internal mode.");
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t) {
        checkForFrozen();
        return super.add(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends T> collection) {
        checkForFrozen();
        return super.addAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        checkForFrozen();
        super.clear();
    }

    public List<T> emptyOrFrozen() {
        if (isEmpty()) {
            return ContainerUtil.emptyList();
        }
        return ContainerUtil.Options.RETURN_REALLY_UNMODIFIABLE_COLLECTION_FROM_METHODS_MARKED_UNMODIFIABLE ? freeze() : this;
    }

    public List<T> freeze() {
        ((ArrayList) this).modCount = -1;
        return this;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T remove(int i) {
        checkForFrozen();
        return (T) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        checkForFrozen();
        return super.removeAll(collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.Collection
    public boolean removeIf(java.util.function.Predicate<? super T> predicate) {
        checkForFrozen();
        return super.removeIf(predicate);
    }

    @Override // java.util.ArrayList, java.util.AbstractList
    public void removeRange(int i, int i2) {
        checkForFrozen();
        super.removeRange(i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.List
    public void replaceAll(UnaryOperator<T> unaryOperator) {
        checkForFrozen();
        super.replaceAll(unaryOperator);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        checkForFrozen();
        return super.retainAll(collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        checkForFrozen();
        return (T) super.set(i, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.List
    public void sort(Comparator<? super T> comparator) {
        checkForFrozen();
        super.sort(comparator);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        checkForFrozen();
        super.add(i, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        checkForFrozen();
        return super.addAll(i, collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        checkForFrozen();
        return super.remove(obj);
    }

    public FreezableArrayList() {
    }

    public FreezableArrayList(int i) {
        super(i);
    }
}
