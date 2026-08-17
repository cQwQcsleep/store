package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ArrayUtilRt;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class LockFreeCopyOnWriteArrayList<E> extends AtomicReference<Object[]> implements ConcurrentList<E>, List<E>, RandomAccess {

    public final class COWIterator implements ListIterator<E> {
        private int cursor;
        private int lastRet;
        private final Object[] snapshot;
        final /* synthetic */ LockFreeCopyOnWriteArrayList this$0;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/intellij/util/containers/LockFreeCopyOnWriteArrayList$COWIterator", "<init>"));
        }

        private COWIterator(LockFreeCopyOnWriteArrayList lockFreeCopyOnWriteArrayList, Object[] objArr, int i) {
            if (objArr == null) {
                $$$reportNull$$$0(0);
            }
            this.this$0 = lockFreeCopyOnWriteArrayList;
            this.lastRet = -1;
            this.cursor = i;
            this.snapshot = objArr;
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.cursor < this.snapshot.length;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.cursor > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            if (!hasNext()) {
                z0e.a();
                return null;
            }
            int i = this.cursor;
            this.lastRet = i;
            Object[] objArr = this.snapshot;
            this.cursor = i + 1;
            return (E) objArr[i];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.cursor;
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (!hasPrevious()) {
                z0e.a();
                return null;
            }
            Object[] objArr = this.snapshot;
            int i = this.cursor - 1;
            this.cursor = i;
            this.lastRet = i;
            return (E) objArr[i];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            int i = this.lastRet;
            if (i < 0) {
                z0e.a();
                return;
            }
            Object obj = this.snapshot[i];
            this.lastRet = -1;
            this.this$0.remove(obj);
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0082  */
    /* JADX WARN: Code duplicated, block: B:47:0x0090  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 6 || i == 8 || i == 9 || i == 12 || i == 14 || i == 16 || i == 18 || i == 35 || i == 37 || i == 41) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 6 || i == 8 || i == 9 || i == 12 || i == 14 || i == 16 || i == 18 || i == 35 || i == 37 || i == 41) ? 2 : 3];
        if (i != 24 && i != 27 && i != 31) {
            switch (i) {
                case 1:
                    objArr[0] = "oldArray";
                    break;
                case 2:
                    objArr[0] = "newArray";
                    break;
                case 3:
                case 4:
                case 11:
                case 13:
                case 15:
                case 17:
                case 19:
                case 20:
                    objArr[0] = "elements";
                    break;
                case 5:
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                case 8:
                case 9:
                case 12:
                case 14:
                case 16:
                case 18:
                    objArr[0] = "com/intellij/util/containers/LockFreeCopyOnWriteArrayList";
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 10:
                    objArr[0] = "a";
                    break;
                default:
                    switch (i) {
                        case 34:
                        case 40:
                        case 45:
                            objArr[0] = "elements";
                            break;
                        case 35:
                        case 37:
                        case 41:
                            objArr[0] = "com/intellij/util/containers/LockFreeCopyOnWriteArrayList";
                            break;
                        case 36:
                            objArr[0] = "action";
                            break;
                        case 38:
                        case 39:
                            objArr[0] = "operator";
                            break;
                        case 42:
                        case 44:
                            objArr[0] = "filter";
                            break;
                        case 43:
                            objArr[0] = "change";
                            break;
                        default:
                            objArr[0] = "c";
                            break;
                    }
                    break;
            }
        } else {
            objArr[0] = "elements";
        }
        if (i == 5 || i == 6 || i == 8 || i == 9) {
            objArr[1] = "toArray";
        } else if (i == 12) {
            objArr[1] = "createArraySet";
        } else if (i == 14 || i == 16) {
            objArr[1] = "createArrayAdd";
        } else if (i == 18) {
            objArr[1] = "createArrayRemove";
        } else if (i == 35) {
            objArr[1] = "toString";
        } else if (i == 37) {
            objArr[1] = "listIterator";
        } else if (i != 41) {
            objArr[1] = "com/intellij/util/containers/LockFreeCopyOnWriteArrayList";
        } else {
            objArr[1] = "createArrayMap";
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "replaceArray";
                break;
            case 3:
                objArr[2] = "indexOf";
                break;
            case 4:
                objArr[2] = "lastIndexOf";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 9:
            case 12:
            case 14:
            case 16:
            case 18:
            case 35:
            case 37:
            case 41:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "toArray";
                break;
            case 10:
                objArr[2] = "get";
                break;
            case 11:
                objArr[2] = "createArraySet";
                break;
            case 13:
            case 15:
                objArr[2] = "createArrayAdd";
                break;
            case 17:
            case 19:
                objArr[2] = "createArrayRemove";
                break;
            case 20:
                objArr[2] = "createArrayAddIfAbsent";
                break;
            case 21:
                objArr[2] = "containsAll";
                break;
            case 22:
                objArr[2] = "removeAll";
                break;
            case 23:
            case 24:
                objArr[2] = "createArrayRemoveAll";
                break;
            case 25:
                objArr[2] = "retainAll";
                break;
            case 26:
            case 27:
                objArr[2] = "createArrayRetainAll";
                break;
            case 28:
                objArr[2] = "addAllAbsent";
                break;
            case 29:
            case 32:
                objArr[2] = "addAll";
                break;
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 34:
                objArr[2] = "createArrayAddAll";
                break;
            case 36:
                objArr[2] = "forEach";
                break;
            case 38:
                objArr[2] = "replaceAll";
                break;
            case 39:
            case 40:
                objArr[2] = "createArrayMap";
                break;
            case 42:
                objArr[2] = "removeIf";
                break;
            case 43:
                objArr[2] = "changeAndReplace";
                break;
            case 44:
            case 45:
                objArr[2] = "createArrayRemoveIf";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 6 && i != 8 && i != 9 && i != 12 && i != 14 && i != 16 && i != 18 && i != 35 && i != 37 && i != 41) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public LockFreeCopyOnWriteArrayList() {
        clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Object[] createArrayAdd(Object[] objArr, int i, E e) {
        if (objArr == null) {
            $$$reportNull$$$0(15);
        }
        int length = objArr.length;
        if (i > length || i < 0) {
            rnd.a("Index: ", i, ", Size: ", length);
            return null;
        }
        Object[] objArr2 = new Object[length + 1];
        if (i != 0) {
            System.arraycopy(objArr, 0, objArr2, 0, i);
        }
        int i2 = length - i;
        if (i2 != 0) {
            System.arraycopy(objArr, i, objArr2, i + 1, i2);
        }
        objArr2[i] = e;
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] createArrayAddAll(Object[] objArr, int i, Collection collection) {
        Object[] objArrCopyOf;
        if (collection == null) {
            $$$reportNull$$$0(33);
        }
        if (objArr == null) {
            $$$reportNull$$$0(34);
        }
        if (collection.isEmpty()) {
            return null;
        }
        Object[] array = collection.toArray();
        if (array.length == 0) {
            return null;
        }
        int length = objArr.length;
        if (i > length || i < 0) {
            rnd.a("Index: ", i, ", Size: ", length);
            return null;
        }
        int i2 = length - i;
        if (i2 == 0) {
            objArrCopyOf = Arrays.copyOf(objArr, length + array.length, Object[].class);
        } else {
            Object[] objArr2 = new Object[length + array.length];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(objArr, i, objArr2, array.length + i, i2);
            objArrCopyOf = objArr2;
        }
        System.arraycopy(array, 0, objArrCopyOf, i, array.length);
        return objArrCopyOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Object[] createArrayAddIfAbsent(Object[] objArr, E e) {
        if (objArr == null) {
            $$$reportNull$$$0(20);
        }
        int length = objArr.length;
        Object[] objArr2 = new Object[length + 1];
        for (int i = 0; i < length; i++) {
            if (Objects.equals(e, objArr[i])) {
                return null;
            }
            objArr2[i] = objArr[i];
        }
        objArr2[length] = e;
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object[] createArrayMap(Object[] objArr, UnaryOperator<? super E> unaryOperator) {
        if (unaryOperator == null) {
            $$$reportNull$$$0(39);
        }
        if (objArr == null) {
            $$$reportNull$$$0(40);
        }
        Object[] objArrNewObjectArray = ArrayUtil.newObjectArray(objArr.length);
        for (int i = 0; i < objArr.length; i++) {
            objArrNewObjectArray[i] = unaryOperator.apply(objArr[i]);
        }
        if (objArrNewObjectArray == null) {
            $$$reportNull$$$0(41);
        }
        return objArrNewObjectArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] createArrayRemove(Object[] objArr, Object obj) {
        if (objArr == null) {
            $$$reportNull$$$0(19);
        }
        int length = objArr.length;
        if (length == 0) {
            return null;
        }
        int i = length - 1;
        Object[] objArr2 = i == 0 ? ArrayUtilRt.EMPTY_OBJECT_ARRAY : new Object[i];
        while (i != 0) {
            Object obj2 = objArr[i];
            if (Objects.equals(obj, obj2)) {
                System.arraycopy(objArr, 0, objArr2, 0, i);
                break;
            }
            objArr2[i - 1] = obj2;
            i--;
        }
        if (i != 0 || Objects.equals(obj, objArr[0])) {
            return objArr2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] createArrayRemoveAll(Object[] objArr, Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(23);
        }
        if (objArr == null) {
            $$$reportNull$$$0(24);
        }
        int length = objArr.length;
        if (length == 0) {
            return null;
        }
        Object[] objArr2 = new Object[length];
        int i = 0;
        for (Object obj : objArr) {
            if (!collection.contains(obj)) {
                objArr2[i] = obj;
                i++;
            }
        }
        if (i == length) {
            return null;
        }
        return Arrays.copyOf(objArr2, i, Object[].class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] createArrayRemoveIf(Object[] objArr, java.util.function.Predicate predicate) {
        if (predicate == null) {
            $$$reportNull$$$0(44);
        }
        if (objArr == null) {
            $$$reportNull$$$0(45);
        }
        int i = 0;
        while (i < objArr.length && !predicate.test(objArr[i])) {
            i++;
        }
        if (i == objArr.length) {
            return null;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        for (int i2 = i + 1; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (!predicate.test(obj)) {
                objArr2[i] = obj;
                i++;
            }
        }
        return ArrayUtil.realloc(objArr2, i, ArrayUtil.OBJECT_ARRAY_FACTORY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] createArrayRetainAll(Object[] objArr, Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(26);
        }
        if (objArr == null) {
            $$$reportNull$$$0(27);
        }
        int length = objArr.length;
        if (length == 0) {
            return null;
        }
        Object[] objArr2 = new Object[length];
        int i = 0;
        for (Object obj : objArr) {
            if (collection.contains(obj)) {
                objArr2[i] = obj;
                i++;
            }
        }
        if (i == length) {
            return null;
        }
        return Arrays.copyOf(objArr2, i, Object[].class);
    }

    private static Object[] createArraySet(Object[] objArr, int i, Object obj) {
        if (objArr == null) {
            $$$reportNull$$$0(11);
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        objArrCopyOf[i] = obj;
        return objArrCopyOf;
    }

    public static /* synthetic */ Object[] j0(Comparator comparator, Object[] objArr) {
        Object[] objArr2 = (Object[]) objArr.clone();
        Arrays.sort(objArr2, comparator);
        return objArr2;
    }

    private static int lastIndexOf(Object obj, Object[] objArr, int i) {
        if (objArr == null) {
            $$$reportNull$$$0(4);
        }
        if (obj == null) {
            while (i >= 0) {
                if (objArr[i] == null) {
                    return i;
                }
                i--;
            }
            return -1;
        }
        while (i >= 0) {
            if (obj.equals(objArr[i])) {
                return i;
            }
            i--;
        }
        return -1;
    }

    private boolean replaceArray(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            $$$reportNull$$$0(1);
        }
        if (objArr2 == null) {
            $$$reportNull$$$0(2);
        }
        return compareAndSet(objArr, objArr2);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(final E e) {
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.p
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayAdd((Object[]) obj, e);
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(final Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(29);
        }
        changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.m
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayAddAll((Object[]) obj, collection);
            }
        });
        return true;
    }

    public boolean addIfAbsent(final E e) {
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.u
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayAddIfAbsent((Object[]) obj, e);
            }
        });
    }

    public boolean changeAndReplace(UnaryOperator<Object[]> unaryOperator) {
        Object[] objArr;
        Object[] objArr2;
        if (unaryOperator == null) {
            $$$reportNull$$$0(43);
        }
        do {
            objArr = get();
            objArr2 = (Object[]) unaryOperator.apply(objArr);
            if (objArr2 == null) {
                return false;
            }
        } while (!replaceArray(objArr, objArr2));
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        set(ArrayUtilRt.EMPTY_OBJECT_ARRAY);
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        Object[] objArr = get();
        return indexOf(obj, objArr, 0, objArr.length) >= 0;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(21);
        }
        Object[] objArr = get();
        int length = objArr.length;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (indexOf(it.next(), objArr, 0, length) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Iterator<E> it = ((List) obj).iterator();
        for (Object obj2 : get()) {
            if (!it.hasNext() || !Objects.equals(obj2, it.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> consumer) {
        if (consumer == null) {
            $$$reportNull$$$0(36);
        }
        for (Object obj : get()) {
            consumer.accept(obj);
        }
    }

    @Override // java.util.List
    public E get(int i) {
        return get(get(), i);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        Object[] objArr = get();
        int length = objArr.length;
        int iHashCode = 1;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            iHashCode = (iHashCode * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return iHashCode;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Object[] objArr = get();
        return indexOf(obj, objArr, 0, objArr.length);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        Object[] objArr = get();
        return objArr.length == 0 ? Collections.emptyIterator() : new COWIterator(objArr, 0);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        Object[] objArr = get();
        int length = objArr.length;
        if (i >= 0 && i <= length) {
            return objArr.length == 0 ? Collections.emptyListIterator() : new COWIterator(objArr, i);
        }
        b1e.a("Index: ", i);
        return null;
    }

    @Override // java.util.List
    public E remove(int i) throws IndexOutOfBoundsException {
        Object[] objArr;
        do {
            objArr = get();
        } while (!replaceArray(objArr, createArrayRemove(objArr, i)));
        return get(objArr, i);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(final Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(22);
        }
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.o
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayRemoveAll((Object[]) obj, collection);
            }
        });
    }

    @Override // java.util.Collection
    public boolean removeIf(final java.util.function.Predicate<? super E> predicate) {
        if (predicate == null) {
            $$$reportNull$$$0(42);
        }
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.q
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayRemoveIf((Object[]) obj, predicate);
            }
        });
    }

    @Override // java.util.List
    public void replaceAll(final UnaryOperator<E> unaryOperator) {
        if (unaryOperator == null) {
            $$$reportNull$$$0(38);
        }
        changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.t
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.createArrayMap((Object[]) obj, unaryOperator);
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(final Collection<?> collection) {
        if (collection == null) {
            $$$reportNull$$$0(25);
        }
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.r
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayRetainAll((Object[]) obj, collection);
            }
        });
    }

    @Override // java.util.List
    public E set(int i, E e) throws IndexOutOfBoundsException {
        Object[] objArr;
        E e2;
        do {
            objArr = get();
            e2 = get(objArr, i);
        } while (!replaceArray(objArr, e2 == e ? objArr : createArraySet(objArr, i, e)));
        return e2;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return get().length;
    }

    @Override // java.util.List
    public void sort(final Comparator<? super E> comparator) {
        changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.l
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.j0(comparator, (Object[]) obj);
            }
        });
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Arrays.spliterator(get());
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        if (tArr == null) {
            $$$reportNull$$$0(7);
        }
        Object[] objArr = get();
        int length = objArr.length;
        if (tArr.length < length) {
            T[] tArr2 = (T[]) Arrays.copyOf(objArr, length, tArr.getClass());
            if (tArr2 == null) {
                $$$reportNull$$$0(8);
            }
            return tArr2;
        }
        System.arraycopy(objArr, 0, tArr, 0, length);
        if (tArr.length > length) {
            tArr[length] = null;
        }
        return tArr;
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        String string = Arrays.toString(get());
        if (string == null) {
            $$$reportNull$$$0(35);
        }
        return string;
    }

    @Override // java.util.List
    public void add(final int i, final E e) throws IndexOutOfBoundsException {
        changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.s
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayAdd((Object[]) obj, i, e);
            }
        });
    }

    private E get(Object[] objArr, int i) {
        if (objArr == null) {
            $$$reportNull$$$0(10);
        }
        return (E) objArr[i];
    }

    private static int indexOf(Object obj, Object[] objArr, int i, int i2) {
        if (objArr == null) {
            $$$reportNull$$$0(3);
        }
        return ArrayUtilRt.indexOf(objArr, obj, i, i2);
    }

    @Override // java.util.List
    public boolean addAll(final int i, final Collection<? extends E> collection) throws IndexOutOfBoundsException {
        if (collection == null) {
            $$$reportNull$$$0(32);
        }
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.n
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LockFreeCopyOnWriteArrayList.createArrayAddAll((Object[]) obj, i, collection);
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(final Object obj) {
        return changeAndReplace(new UnaryOperator() { // from class: com.intellij.util.containers.v
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return LockFreeCopyOnWriteArrayList.createArrayRemove((Object[]) obj2, obj);
            }
        });
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        ListIterator<E> listIterator = listIterator(0);
        if (listIterator == null) {
            $$$reportNull$$$0(37);
        }
        return listIterator;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        Object[] objArr = get();
        return lastIndexOf(obj, objArr, objArr.length - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Object[] createArrayAdd(Object[] objArr, E e) {
        if (objArr == null) {
            $$$reportNull$$$0(13);
        }
        int length = objArr.length;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, length + 1);
        objArrCopyOf[length] = e;
        return objArrCopyOf;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] objArr = get();
        if (objArr.length == 0) {
            Object[] objArr2 = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
            if (objArr2 == null) {
                $$$reportNull$$$0(5);
            }
            return objArr2;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        if (objArrCopyOf == null) {
            $$$reportNull$$$0(6);
        }
        return objArrCopyOf;
    }

    private static Object[] createArrayRemove(Object[] objArr, int i) {
        if (objArr == null) {
            $$$reportNull$$$0(17);
        }
        int length = objArr.length;
        Object[] objArr2 = length == 1 ? ArrayUtilRt.EMPTY_OBJECT_ARRAY : new Object[length - 1];
        if (i != 0) {
            System.arraycopy(objArr, 0, objArr2, 0, i);
        }
        int i2 = (length - i) - 1;
        if (i2 != 0) {
            System.arraycopy(objArr, i + 1, objArr2, i, i2);
        }
        if (objArr2 == null) {
            $$$reportNull$$$0(18);
        }
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] createArrayAddAll(Object[] objArr, Collection collection) {
        if (collection == null) {
            $$$reportNull$$$0(30);
        }
        if (objArr == null) {
            $$$reportNull$$$0(31);
        }
        if (collection.isEmpty()) {
            return null;
        }
        Object[] array = collection.toArray();
        if (array.length == 0) {
            return null;
        }
        int length = objArr.length;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, array.length + length, Object[].class);
        System.arraycopy(array, 0, objArrCopyOf, length, array.length);
        return objArrCopyOf;
    }
}
