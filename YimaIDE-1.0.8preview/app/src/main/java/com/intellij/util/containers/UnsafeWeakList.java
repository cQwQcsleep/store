package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.reference.SoftReference;
import defpackage.a5e;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class UnsafeWeakList<T> extends AbstractCollection<T> {
    private int modCount;
    private int myAlive;
    private final ReferenceQueue<T> myQueue = new ReferenceQueue<>();
    final List<MyReference<T>> myList = new ArrayList();

    public final class MyIterator implements Iterator<T> {
        private T curElement;
        private int curIndex;
        private boolean modified;
        private T nextElement;
        private int nextIndex;
        private final int startModCount;

        private MyIterator() {
            this.nextIndex = -1;
            this.startModCount = UnsafeWeakList.this.modCount;
            findNext();
        }

        private void findNext() {
            if (UnsafeWeakList.this.modCount != this.startModCount) {
                a1e.a();
                return;
            }
            int i = this.nextIndex;
            this.curIndex = i;
            this.curElement = this.nextElement;
            this.nextElement = null;
            this.nextIndex = -1;
            while (true) {
                i++;
                if (i >= UnsafeWeakList.this.myList.size()) {
                    break;
                }
                MyReference<T> myReference = UnsafeWeakList.this.myList.get(i);
                T t = myReference == null ? null : myReference.get();
                if (t != null) {
                    this.nextElement = t;
                    this.nextIndex = i;
                    break;
                }
            }
            if (this.nextIndex == -1 && this.modified) {
                UnsafeWeakList.access$308(UnsafeWeakList.this);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextElement != null;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                findNext();
                return this.curElement;
            }
            z0e.a();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.curElement == null) {
                z0e.a();
                return;
            }
            UnsafeWeakList.this.nullizeAt(this.curIndex);
            this.modified = true;
        }
    }

    public static final class MyReference<T> extends WeakReference<T> {
        private final int index;

        private MyReference(int i, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.index = i;
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return obj != null && Objects.equals(get(), ((Reference) obj).get());
            }
            return true;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 3:
                objArr[0] = "o";
                break;
            case 4:
            case 5:
                objArr[0] = "c";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "com/intellij/util/containers/UnsafeWeakList";
                break;
            case 8:
                objArr[0] = "other";
                break;
            case 9:
                objArr[0] = "allowEntity";
                break;
            default:
                objArr[0] = "element";
                break;
        }
        if (i == 6 || i == 7) {
            objArr[1] = "toStrongList";
        } else {
            objArr[1] = "com/intellij/util/containers/UnsafeWeakList";
        }
        switch (i) {
            case 1:
                objArr[2] = "add";
                break;
            case 2:
                objArr[2] = "addIfAbsent";
                break;
            case 3:
                objArr[2] = "remove";
                break;
            case 4:
                objArr[2] = "addAll";
                break;
            case 5:
                objArr[2] = "removeAll";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                break;
            case 8:
            case 9:
                objArr[2] = "containsAll";
                break;
            default:
                objArr[2] = "append";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ int access$308(UnsafeWeakList unsafeWeakList) {
        int i = unsafeWeakList.modCount;
        unsafeWeakList.modCount = i + 1;
        return i;
    }

    private void append(T t) {
        if (t == null) {
            $$$reportNull$$$0(0);
        }
        this.myList.add(new MyReference<>(this.myList.size(), t, this.myQueue));
        this.myAlive++;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nullizeAt(int i) {
        this.myList.set(i, null);
        this.myAlive--;
    }

    private void reduceCapacity() {
        List<MyReference<T>> list;
        int i = 0;
        int i2 = 0;
        while (true) {
            int size = this.myList.size();
            list = this.myList;
            if (i >= size) {
                break;
            }
            MyReference<T> myReference = list.get(i);
            if (myReference != null) {
                T t = myReference.get();
                if (t == null) {
                    this.myAlive--;
                } else {
                    if (i2 != i) {
                        this.myList.set(i2, new MyReference<>(i2, t, this.myQueue));
                    }
                    i2++;
                }
            }
            i++;
        }
        if (i2 != list.size()) {
            List<MyReference<T>> list2 = this.myList;
            list2.subList(i2, list2.size()).clear();
            this.modCount++;
        }
        this.myAlive = i2;
    }

    private static void throwNotAllowedException() {
        throw new UnsupportedOperationException("index/size-based operations in UnsafeWeakList are not supported because they don't make sense in the presence of weak references. Use .iterator() (which retains its elements to avoid sudden GC) instead.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(T t) {
        if (t == null) {
            $$$reportNull$$$0(1);
        }
        processQueue();
        append(t);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == 0) {
            $$$reportNull$$$0(4);
        }
        processQueue();
        return super.addAll(collection);
    }

    public boolean addIfAbsent(T t) {
        if (t == null) {
            $$$reportNull$$$0(2);
        }
        processQueue();
        if (contains(t)) {
            return false;
        }
        append(t);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        processQueue();
        this.myList.clear();
        this.myAlive = 0;
        this.modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return !isEmpty() && super.contains(obj);
    }

    public boolean containsAll(UnsafeWeakList<T> unsafeWeakList, java.util.function.Predicate<? super T> predicate) {
        if (unsafeWeakList == null) {
            $$$reportNull$$$0(8);
        }
        if (predicate == null) {
            $$$reportNull$$$0(9);
        }
        List<MyReference<T>> list = this.myList;
        List<MyReference<T>> list2 = unsafeWeakList.myList;
        if (list2.isEmpty()) {
            return true;
        }
        for (MyReference<T> myReference : list2) {
            a5e a5eVar = (Object) SoftReference.dereference(myReference);
            if (a5eVar != null && predicate.test(a5eVar) && !list.contains(myReference)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        if (this.myList.isEmpty()) {
            return true;
        }
        for (MyReference<T> myReference : this.myList) {
            if (myReference != null && myReference.get() != null) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public boolean processQueue() {
        boolean z = false;
        while (true) {
            MyReference<T> myReference = (MyReference) this.myQueue.poll();
            if (myReference == null) {
                break;
            }
            int i = ((MyReference) myReference).index;
            if (i < this.myList.size() && myReference == this.myList.get(i)) {
                nullizeAt(i);
            }
            z = true;
        }
        if (this.myAlive < this.myList.size() / 2) {
            reduceCapacity();
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        processQueue();
        for (int i = 0; i < this.myList.size(); i++) {
            MyReference<T> myReference = this.myList.get(i);
            T t = myReference == null ? null : myReference.get();
            if (t != null && t.equals(obj)) {
                nullizeAt(i);
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(5);
        }
        processQueue();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public int size() {
        throwNotAllowedException();
        return -1;
    }

    public List<T> toStrongList() {
        if (this.myList.isEmpty()) {
            List<T> list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(6);
            }
            return list;
        }
        ArrayList arrayList = new ArrayList(this.myList.size());
        Iterator<MyReference<T>> it = this.myList.iterator();
        while (it.hasNext()) {
            MyReference<T> next = it.next();
            T t = next == null ? null : next.get();
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }
}
