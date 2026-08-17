package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class WeakList<T> extends UnsafeWeakList<T> {
    /* JADX WARN: Code duplicated, block: B:24:0x0035  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 6 || i == 7) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "c";
        } else if (i == 3 || i == 4) {
            objArr[0] = "o";
        } else if (i == 5) {
            objArr[0] = "c";
        } else if (i == 6 || i == 7) {
            objArr[0] = "com/intellij/util/containers/WeakList";
        } else {
            objArr[0] = "element";
        }
        if (i == 6) {
            objArr[1] = "toStrongList";
        } else if (i != 7) {
            objArr[1] = "com/intellij/util/containers/WeakList";
        } else {
            objArr[1] = "copyAndClear";
        }
        switch (i) {
            case 1:
                objArr[2] = "addAll";
                break;
            case 2:
                objArr[2] = "addIfAbsent";
                break;
            case 3:
                objArr[2] = "contains";
                break;
            case 4:
                objArr[2] = "remove";
                break;
            case 5:
                objArr[2] = "removeAll";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                break;
            default:
                objArr[2] = "add";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public boolean add(T t) {
        boolean zAdd;
        if (t == null) {
            $$$reportNull$$$0(0);
        }
        synchronized (this.myList) {
            zAdd = super.add(t);
        }
        return zAdd;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        boolean zAddAll;
        if (collection == null) {
            $$$reportNull$$$0(1);
        }
        synchronized (this.myList) {
            zAddAll = super.addAll(collection);
        }
        return zAddAll;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList
    public boolean addIfAbsent(T t) {
        boolean zAddIfAbsent;
        if (t == null) {
            $$$reportNull$$$0(2);
        }
        synchronized (this.myList) {
            zAddIfAbsent = super.addIfAbsent(t);
        }
        return zAddIfAbsent;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        synchronized (this.myList) {
            super.clear();
        }
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        boolean zContains;
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        synchronized (this.myList) {
            zContains = super.contains(obj);
        }
        return zContains;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.myList) {
            zIsEmpty = super.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        final Iterator<T> it;
        synchronized (this.myList) {
            it = super.iterator();
        }
        return new Iterator<T>() { // from class: com.intellij.util.containers.WeakList.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                boolean zHasNext;
                synchronized (WeakList.this.myList) {
                    zHasNext = it.hasNext();
                }
                return zHasNext;
            }

            @Override // java.util.Iterator
            public T next() {
                T t;
                synchronized (WeakList.this.myList) {
                    t = (T) it.next();
                }
                return t;
            }

            @Override // java.util.Iterator
            public void remove() {
                synchronized (WeakList.this.myList) {
                    it.remove();
                }
            }
        };
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        boolean zRemove;
        if (obj == null) {
            $$$reportNull$$$0(4);
        }
        synchronized (this.myList) {
            zRemove = super.remove(obj);
        }
        return zRemove;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList, java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean zRemoveAll;
        if (collection == null) {
            $$$reportNull$$$0(5);
        }
        synchronized (this.myList) {
            zRemoveAll = super.removeAll(collection);
        }
        return zRemoveAll;
    }

    @Override // com.intellij.util.containers.UnsafeWeakList
    public List<T> toStrongList() {
        List<T> strongList;
        synchronized (this.myList) {
            strongList = super.toStrongList();
        }
        if (strongList == null) {
            $$$reportNull$$$0(6);
        }
        return strongList;
    }
}
