package com.reandroid.utils.collection;

import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CollectionUtil {
    private static final Comparator<? extends Comparable> COMPARABLE_COMPARATOR = new Comparator<Comparable<?>>() { // from class: com.reandroid.utils.collection.CollectionUtil.1
        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return compare2((Comparable) comparable, (Comparable) comparable2);
        }

        /* JADX INFO: renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    private static final Predicate<?> ACCEPT_ALL = new Predicate() { // from class: h62
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return CollectionUtil.a(obj);
        }
    };
    private static final Predicate<?> REJECT_ALL = new Predicate() { // from class: i62
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return CollectionUtil.g(obj);
        }
    };

    public static /* synthetic */ boolean a(Object obj) {
        return true;
    }

    public static <T> Predicate<T> andFilter(final Predicate<T> predicate, final Predicate<T> predicate2) {
        if (predicate == null || predicate == getAcceptAll()) {
            return predicate2;
        }
        return (predicate2 == null || predicate2 == getAcceptAll()) ? predicate : new Predicate() { // from class: k62
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CollectionUtil.f(predicate, predicate2, obj);
            }
        };
    }

    @SafeVarargs
    public static <T> HashSet<T> asHashSet(T... tArr) {
        return new HashSet<>(asList(tArr));
    }

    @SafeVarargs
    public static <T> List<T> asList(T... tArr) {
        return new ArrayCollection(tArr);
    }

    public static /* synthetic */ boolean b(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) != predicate2.test(obj);
    }

    public static /* synthetic */ boolean c(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) == predicate2.test(obj);
    }

    public static <T> Collection<T> collect(Iterator<? extends T> it) {
        if (!it.hasNext()) {
            return ArrayCollection.empty();
        }
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.addAll(it);
        if (arrayCollection.size() > 1000) {
            arrayCollection.trimToSize();
        }
        return arrayCollection;
    }

    public static boolean contains(Iterator<?> it, Object obj) {
        if (it == null) {
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> Iterator<T> copyOf(Iterator<? extends T> it) {
        return !it.hasNext() ? EmptyIterator.of() : toList(it).iterator();
    }

    public static <T> Iterator<T> copyOfUniqueOf(Iterator<? extends T> it) {
        return copyOf(uniqueOf(it));
    }

    public static int count(Iterable<?> iterable) {
        int i = 0;
        if (iterable != null && !(iterable instanceof EmptyItem)) {
            if (iterable instanceof Collection) {
                return ((Collection) iterable).size();
            }
            if ((iterable instanceof SizedItem) && ((SizedItem) iterable).size() == 0) {
                return 0;
            }
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                it.next();
                i++;
            }
        }
        return i;
    }

    public static /* synthetic */ boolean d(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    public static <T> Predicate<T> diffFilter(final Predicate<T> predicate, final Predicate<T> predicate2) {
        return new Predicate() { // from class: l62
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CollectionUtil.b(predicate, predicate2, obj);
            }
        };
    }

    public static /* synthetic */ boolean e(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) || predicate2.test(obj);
    }

    public static <T> Predicate<T> equalFilter(final Predicate<T> predicate, final Predicate<T> predicate2) {
        return new Predicate() { // from class: g62
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CollectionUtil.c(predicate, predicate2, obj);
            }
        };
    }

    public static /* synthetic */ boolean f(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) && predicate2.test(obj);
    }

    public static /* synthetic */ boolean g(Object obj) {
        return false;
    }

    public static <T> Predicate<T> getAcceptAll() {
        return (Predicate<T>) ACCEPT_ALL;
    }

    public static <T extends Comparable<T>> Comparator<T> getComparator() {
        return (Comparator<T>) COMPARABLE_COMPARATOR;
    }

    public static <T> T getFirst(Iterator<T> it) {
        if (it == null || !it.hasNext()) {
            return null;
        }
        return it.next();
    }

    public static <T> T getLast(Iterator<T> it) {
        T next = null;
        if (it == null) {
            return null;
        }
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static <T> Predicate<T> getRejectAll() {
        return (Predicate<T>) REJECT_ALL;
    }

    public static <T> T getSingle(Iterator<T> it) {
        if (it == null || !it.hasNext()) {
            return null;
        }
        T next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable == null || (iterable instanceof EmptyItem)) {
            return true;
        }
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        if ((iterable instanceof SizedItem) && ((SizedItem) iterable).size() == 0) {
            return true;
        }
        return !iterable.iterator().hasNext();
    }

    public static <T> Predicate<T> negateFilter(final Predicate<T> predicate) {
        return new Predicate() { // from class: m62
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CollectionUtil.d(predicate, obj);
            }
        };
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... tArr) {
        if (tArr == null || tArr.length == 0) {
            return new HashSet<>();
        }
        HashSet<T> hashSet = new HashSet<>(tArr.length);
        for (T t : tArr) {
            hashSet.add(t);
        }
        return hashSet;
    }

    public static <T> Iterator<T> newIterator(Collection<? extends T> collection) {
        return collection.size() == 0 ? EmptyIterator.of() : ArrayIterator.of(collection.toArray());
    }

    public static <T> Predicate<? super T> orFilter(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
        if (predicate == null || predicate == getRejectAll()) {
            return predicate2;
        }
        return (predicate2 == null || predicate2 == getRejectAll()) ? predicate : new Predicate() { // from class: j62
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CollectionUtil.e(predicate, predicate2, obj);
            }
        };
    }

    public static <T> Iterator<T> reversedOf(Iterator<? extends T> it) {
        if (!it.hasNext()) {
            return EmptyIterator.of();
        }
        T next = it.next();
        boolean zHasNext = it.hasNext();
        if (!zHasNext) {
            return SingleIterator.of(next);
        }
        ArrayCollection arrayCollection = new ArrayCollection(8);
        arrayCollection.add(next);
        while (zHasNext) {
            arrayCollection.add(it.next());
            zHasNext = it.hasNext();
        }
        return arrayCollection.reversedIterator();
    }

    public static void shuffle(int i, List<?> list) {
        if (list.isEmpty()) {
            return;
        }
        if (list instanceof ArrayCollection) {
            shuffle(i, (ArrayCollection<?>) list);
            return;
        }
        ArrayCollection arrayCollection = new ArrayCollection(list);
        list.clear();
        shuffle(i, (ArrayCollection<?>) arrayCollection);
        list.addAll(arrayCollection);
    }

    public static <T extends Comparable<T>> void sort(List<T> list) {
        list.sort(getComparator());
    }

    public static <T> HashSet<T> toHashSet(Iterator<? extends T> it) {
        HashSet<T> hashSet = new HashSet<>();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        return hashSet;
    }

    public static <T> List<T> toList(Iterator<? extends T> it) {
        boolean zHasNext = it.hasNext();
        if (!zHasNext) {
            return EmptyList.of();
        }
        ArrayCollection arrayCollection = new ArrayCollection(2);
        while (zHasNext) {
            arrayCollection.add(it.next());
            zHasNext = it.hasNext();
        }
        if (arrayCollection.size() > 1000) {
            arrayCollection.trimToSize();
        }
        return arrayCollection;
    }

    public static <T> List<T> toUniqueList(Iterator<? extends T> it) {
        return new ArrayCollection(toHashSet(it));
    }

    public static <T> Iterator<T> uniqueOf(Iterator<? extends T> it) {
        return !it.hasNext() ? EmptyIterator.of() : new UniqueIterator((Iterator) ObjectsUtil.cast(it));
    }

    public static void walk(Iterator<?> it) {
        if (it == null) {
            return;
        }
        while (it.hasNext()) {
            it.next();
        }
    }

    public static void shuffle(List<?> list) {
        if (list.isEmpty()) {
            return;
        }
        shuffle(Long.toString(System.currentTimeMillis()).hashCode(), list);
    }

    private static void shuffle(int i, ArrayCollection<?> arrayCollection) {
        int size = arrayCollection.size();
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = i % size;
            if (i3 < 0) {
                i3 = -i3;
            }
            arrayCollection.swap(i2, i3);
            i = (i * 31) + size + 1;
        }
    }

    public static boolean isEmpty(Iterator<?> it) {
        if (it == null || (it instanceof EmptyItem)) {
            return true;
        }
        if ((it instanceof SizedItem) && ((SizedItem) it).size() == 0) {
            return true;
        }
        return !it.hasNext();
    }

    public static int count(Iterator<?> it) {
        int i = 0;
        if (it != null && !(it instanceof EmptyItem)) {
            if ((it instanceof SizedItem) && ((SizedItem) it).size() == 0) {
                return 0;
            }
            while (it.hasNext()) {
                it.next();
                i++;
            }
        }
        return i;
    }
}
