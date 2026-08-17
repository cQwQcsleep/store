package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.containers.DisposableWrapperList;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DisposableWrapperList<E> extends AbstractList<E> {
    private final List<DisposableWrapperList<E>.DisposableWrapper> myWrappedList = ContainerUtil.createLockFreeCopyOnWriteList();

    public final class DisposableWrapperListIterator implements ListIterator<E> {
        private final ListIterator<DisposableWrapperList<E>.DisposableWrapper> myDelegate;
        private DisposableWrapperList<E>.DisposableWrapper myLastReturned;

        public DisposableWrapperListIterator(int i) {
            this.myDelegate = DisposableWrapperList.this.myWrappedList.listIterator(i);
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !DisposableWrapperListIterator.class.equals(obj.getClass())) {
                return false;
            }
            return this.myDelegate.equals(((DisposableWrapperListIterator) obj).myDelegate);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.myDelegate.hasNext();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.myDelegate.hasPrevious();
        }

        public int hashCode() {
            return this.myDelegate.hashCode();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            DisposableWrapperList<E>.DisposableWrapper next = this.myDelegate.next();
            this.myLastReturned = next;
            return (E) ((DisposableWrapper) next).delegate;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.myDelegate.nextIndex();
        }

        @Override // java.util.ListIterator
        public E previous() {
            DisposableWrapperList<E>.DisposableWrapper disposableWrapperPrevious = this.myDelegate.previous();
            this.myLastReturned = disposableWrapperPrevious;
            return (E) ((DisposableWrapper) disposableWrapperPrevious).delegate;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.myDelegate.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            DisposableWrapperList<E>.DisposableWrapper disposableWrapper = this.myLastReturned;
            if (disposableWrapper == null) {
                z0e.a();
            } else if (disposableWrapper.makeUnique()) {
                this.myDelegate.remove();
                this.myLastReturned.disposeWithoutRemoval();
                this.myLastReturned = null;
            }
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0087  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 7 || i == 16 || i == 20 || i == 13 || i == 14 || i == 22 || i == 23) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 7 || i == 16 || i == 20 || i == 13 || i == 14 || i == 22 || i == 23) ? 2 : 3];
        switch (i) {
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 19:
                objArr[0] = "parentDisposable";
                break;
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 13:
            case 14:
            case 16:
            case 20:
            case 22:
            case 23:
                objArr[0] = "com/intellij/util/containers/DisposableWrapperList";
                break;
            case 5:
            case 18:
            default:
                objArr[0] = "element";
                break;
            case 8:
            case 9:
            case 17:
            case 21:
                objArr[0] = "collection";
                break;
            case 10:
            case 12:
                objArr[0] = "objects";
                break;
            case 11:
                objArr[0] = "filter";
                break;
            case 15:
                objArr[0] = "array";
                break;
        }
        if (i == 4 || i == 7) {
            objArr[1] = "add";
        } else if (i == 16) {
            objArr[1] = "toArray";
        } else if (i == 20) {
            objArr[1] = "createDisposableWrapper";
        } else if (i == 13 || i == 14) {
            objArr[1] = "toArray";
        } else if (i == 22 || i == 23) {
            objArr[1] = "wrapAll";
        } else {
            objArr[1] = "com/intellij/util/containers/DisposableWrapperList";
        }
        if (i != 4) {
            switch (i) {
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 13:
                case 14:
                case 16:
                case 20:
                case 22:
                case 23:
                    break;
                case 8:
                case 9:
                    objArr[2] = "addAll";
                    break;
                case 10:
                    objArr[2] = "removeAll";
                    break;
                case 11:
                    objArr[2] = "removeIf";
                    break;
                case 12:
                    objArr[2] = "retainAll";
                    break;
                case 15:
                    objArr[2] = "toArray";
                    break;
                case 17:
                    objArr[2] = "containsAll";
                    break;
                case 18:
                case 19:
                    objArr[2] = "createDisposableWrapper";
                    break;
                case 21:
                    objArr[2] = "wrapAll";
                    break;
                default:
                    objArr[2] = "add";
                    break;
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 7 && i != 16 && i != 20 && i != 13 && i != 14 && i != 22 && i != 23) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ boolean a(java.util.function.Predicate predicate, Set set, DisposableWrapper disposableWrapper) {
        if (!predicate.test(disposableWrapper.delegate)) {
            return false;
        }
        if (!disposableWrapper.makeUnique() && !set.contains(disposableWrapper)) {
            return false;
        }
        set.add(disposableWrapper);
        return true;
    }

    public static /* synthetic */ boolean b(Object obj, List list, DisposableWrapper disposableWrapper) {
        if (!disposableWrapper.delegate.equals(obj)) {
            return false;
        }
        if ((!list.isEmpty() || !disposableWrapper.makeUnique()) && !list.contains(disposableWrapper)) {
            return false;
        }
        list.add(disposableWrapper);
        return true;
    }

    public static /* synthetic */ boolean c(Collection collection, Object obj) {
        return !collection.contains(obj);
    }

    public static /* synthetic */ boolean d(Object obj) {
        return true;
    }

    private E unwrapAndDispose(DisposableWrapperList<E>.DisposableWrapper disposableWrapper) {
        if (disposableWrapper == null) {
            return null;
        }
        E e = (E) ((DisposableWrapper) disposableWrapper).delegate;
        disposableWrapper.disposeWithoutRemoval();
        return e;
    }

    private Collection<DisposableWrapperList<E>.DisposableWrapper> wrapAll(Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(21);
        }
        if (collection.isEmpty()) {
            List list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(22);
            }
            return list;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new DisposableWrapper(this, it.next()));
        }
        return arrayList;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        return this.myWrappedList.add(new DisposableWrapper(this, e));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(8);
        }
        return this.myWrappedList.addAll(wrapAll(collection));
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        removeIf(new java.util.function.Predicate() { // from class: tu3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DisposableWrapperList.d(obj);
            }
        });
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return obj != null && this.myWrappedList.contains(new DisposableWrapper(this, obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean containsAll(Collection<?> collection) {
        if (collection == 0) {
            $$$reportNull$$$0(17);
        }
        return this.myWrappedList.containsAll(wrapAll(collection));
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        return (E) ((DisposableWrapper) this.myWrappedList.get(i)).delegate;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return this.myWrappedList.indexOf(new DisposableWrapper(this, obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.myWrappedList.isEmpty();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new DisposableWrapperListIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        return this.myWrappedList.lastIndexOf(new DisposableWrapper(this, obj));
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return new DisposableWrapperListIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(final Object obj) {
        final ArrayList arrayList = new ArrayList(1);
        boolean zRemoveIf = this.myWrappedList.removeIf(new java.util.function.Predicate() { // from class: com.intellij.util.containers.c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                return DisposableWrapperList.b(obj, arrayList, (DisposableWrapperList.DisposableWrapper) obj2);
            }
        });
        Iterator<E> it = arrayList.iterator();
        while (it.hasNext()) {
            ((DisposableWrapper) it.next()).disposeWithoutRemoval();
        }
        return zRemoveIf;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(final Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(10);
        }
        return removeIf(new java.util.function.Predicate() { // from class: su3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return collection.contains(obj);
            }
        });
    }

    @Override // java.util.Collection
    public boolean removeIf(final java.util.function.Predicate<? super E> predicate) {
        if (predicate == null) {
            $$$reportNull$$$0(11);
        }
        final ReferenceOpenHashSet referenceOpenHashSet = new ReferenceOpenHashSet(this.myWrappedList.size());
        boolean zRemoveIf = this.myWrappedList.removeIf(new java.util.function.Predicate() { // from class: com.intellij.util.containers.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DisposableWrapperList.a(predicate, referenceOpenHashSet, (DisposableWrapperList.DisposableWrapper) obj);
            }
        });
        Iterator<E> it = referenceOpenHashSet.iterator();
        while (it.hasNext()) {
            ((DisposableWrapper) it.next()).disposeWithoutRemoval();
        }
        return zRemoveIf;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(final Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(12);
        }
        return removeIf(new java.util.function.Predicate() { // from class: uu3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DisposableWrapperList.c(collection, obj);
            }
        });
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        return unwrapAndDispose(this.myWrappedList.set(i, new DisposableWrapper(this, e)));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.myWrappedList.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public List<E> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        if (tArr == null) {
            $$$reportNull$$$0(15);
        }
        Object[] array = this.myWrappedList.toArray();
        int length = array.length;
        if (tArr.length < length) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length));
        } else {
            Arrays.fill(tArr, length, tArr.length, (Object) null);
        }
        for (int i = 0; i < length; i++) {
            tArr[i] = ((DisposableWrapper) array[i]).delegate;
        }
        if (tArr == null) {
            $$$reportNull$$$0(16);
        }
        return tArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int i) {
        return new DisposableWrapperListIterator(i);
    }

    public final class DisposableWrapper extends AtomicBoolean implements Disposable {
        private final E delegate;
        private boolean removeFromContainer;
        final /* synthetic */ DisposableWrapperList this$0;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 3 || i == 4) ? 2 : 3];
            if (i == 1) {
                objArr[0] = "delegate";
            } else if (i == 2) {
                objArr[0] = "o";
            } else if (i == 3 || i == 4) {
                objArr[0] = "com/intellij/util/containers/DisposableWrapperList$DisposableWrapper";
            } else {
                objArr[0] = "obj";
            }
            if (i == 3 || i == 4) {
                objArr[1] = "classInfo";
            } else {
                objArr[1] = "com/intellij/util/containers/DisposableWrapperList$DisposableWrapper";
            }
            if (i == 2) {
                objArr[2] = "classInfo";
            } else if (i != 3 && i != 4) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i != 3 && i != 4) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        public DisposableWrapper(DisposableWrapperList disposableWrapperList, E e, boolean z) {
            if (e == null) {
                $$$reportNull$$$0(1);
            }
            this.this$0 = disposableWrapperList;
            this.delegate = e;
            this.removeFromContainer = z;
        }

        private String classInfo(E e) {
            if (e == null) {
                $$$reportNull$$$0(2);
            }
            try {
                return e + " (" + e.getClass() + "; super interfaces: " + Arrays.toString(e.getClass().getInterfaces()) + ")";
            } catch (Throwable th) {
                String message = th.getMessage();
                if (message == null) {
                    $$$reportNull$$$0(4);
                }
                return message;
            }
        }

        private boolean isUnique() {
            return get();
        }

        public void dispose() {
            if (this.removeFromContainer) {
                makeUnique();
                this.this$0.myWrappedList.remove(this);
            }
        }

        public void disposeWithoutRemoval() {
            if (this.removeFromContainer) {
                this.removeFromContainer = false;
                Disposer.dispose(this);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !DisposableWrapper.class.equals(obj.getClass())) {
                return false;
            }
            DisposableWrapper disposableWrapper = (DisposableWrapper) obj;
            try {
                return (!this.delegate.equals(disposableWrapper.delegate) || isUnique() || disposableWrapper.isUnique()) ? false : true;
            } catch (ClassCastException e) {
                throw new RuntimeException("failed DisposableWrapper.equals(" + classInfo(disposableWrapper.delegate) + "; this.delegate=" + classInfo(this.delegate) + ". Whole list=" + this.this$0.myWrappedList, e);
            }
        }

        public int hashCode() {
            return this.delegate.hashCode();
        }

        public boolean makeUnique() {
            return compareAndSet(false, true);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public DisposableWrapper(DisposableWrapperList disposableWrapperList, E e) {
            this(disposableWrapperList, e, false);
            if (e == null) {
                $$$reportNull$$$0(0);
            }
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        if (e == null) {
            $$$reportNull$$$0(1);
        }
        this.myWrappedList.add(i, new DisposableWrapper(this, e));
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(9);
        }
        return this.myWrappedList.addAll(i, wrapAll(collection));
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        return unwrapAndDispose(this.myWrappedList.remove(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        Object[] array = this.myWrappedList.toArray();
        if (array.length == 0) {
            Object[] objArr = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
            if (objArr == null) {
                $$$reportNull$$$0(13);
            }
            return objArr;
        }
        int length = array.length;
        for (int i = 0; i < length; i++) {
            array[i] = ((DisposableWrapper) array[i]).delegate;
        }
        return array;
    }
}
