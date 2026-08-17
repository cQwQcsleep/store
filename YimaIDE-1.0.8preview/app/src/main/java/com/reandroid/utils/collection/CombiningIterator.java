package com.reandroid.utils.collection;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CombiningIterator<T, E extends T> implements Iterator<T> {
    private Iterator<? extends T> iterator1;
    private Iterator<? extends T> iterator2;
    private Iterator<? extends T> iterator3;
    private Iterator<? extends T> iterator4;
    private Iterator<Iterator<E>> iteratorIterator;
    private T mCurrentItem;
    private Iterator<? extends T> mCurrentIterator;

    public CombiningIterator(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<Iterator<E>> it4) {
        this.iterator1 = it;
        this.iterator2 = it2;
        this.iterator3 = it3;
        this.iteratorIterator = it4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1> Iterator<T1> four(Iterator<? extends T1> it, Iterator<? extends T1> it2, Iterator<? extends T1> it3, Iterator<? extends T1> it4) {
        CombiningIterator combiningIterator = new CombiningIterator(it, it2, it3);
        combiningIterator.iterator4 = it4;
        return combiningIterator;
    }

    private T getCurrentItem() {
        T t = this.mCurrentItem;
        if (t != null) {
            return t;
        }
        T next = readNext();
        this.mCurrentItem = next;
        return next;
    }

    private Iterator<? extends T> getCurrentIterator() {
        Iterator<? extends T> it = this.mCurrentIterator;
        if (it != null) {
            return it;
        }
        Iterator<Iterator<E>> it2 = this.iteratorIterator;
        Iterator<E> next = null;
        if (it2 == null) {
            return null;
        }
        while (it2.hasNext() && next == null) {
            next = it2.next();
        }
        this.mCurrentIterator = next;
        return next;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1, E1 extends T1> Iterator<T1> of(T1 t1, Iterator<Iterator<E1>> it) {
        if (!it.hasNext()) {
            return SingleIterator.of(t1);
        }
        CombiningIterator combiningIterator = new CombiningIterator(null, null);
        combiningIterator.mCurrentItem = t1;
        combiningIterator.iteratorIterator = it;
        return combiningIterator;
    }

    private T readCurrentIterator() {
        Iterator<? extends T> currentIterator = getCurrentIterator();
        while (currentIterator != null) {
            while (currentIterator.hasNext()) {
                T next = currentIterator.next();
                if (next != null) {
                    return next;
                }
            }
            this.mCurrentIterator = null;
            currentIterator = getCurrentIterator();
        }
        return null;
    }

    private T readNext() {
        Iterator<? extends T> it = this.iterator1;
        if (it != null) {
            while (it.hasNext()) {
                T next = it.next();
                if (next != null) {
                    return next;
                }
            }
            this.iterator1 = null;
        }
        Iterator<? extends T> it2 = this.iterator2;
        if (it2 != null) {
            while (it2.hasNext()) {
                T next2 = it2.next();
                if (next2 != null) {
                    return next2;
                }
            }
            this.iterator2 = null;
        }
        Iterator<? extends T> it3 = this.iterator3;
        if (it3 != null) {
            while (it3.hasNext()) {
                T next3 = it3.next();
                if (next3 != null) {
                    return next3;
                }
            }
            this.iterator3 = null;
        }
        Iterator<? extends T> it4 = this.iterator4;
        if (it4 != null) {
            while (it4.hasNext()) {
                T next4 = it4.next();
                if (next4 != null) {
                    return next4;
                }
            }
            this.iterator4 = null;
        }
        return readCurrentIterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1> Iterator<T1> singleOne(T1 t1, Iterator<? extends T1> it) {
        if (t1 == 0) {
            return it;
        }
        CombiningIterator combiningIterator = new CombiningIterator(it, null);
        combiningIterator.mCurrentItem = t1;
        return combiningIterator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1> Iterator<T1> singleThree(T1 t1, Iterator<? extends T1> it, Iterator<? extends T1> it2, Iterator<? extends T1> it3) {
        if (t1 == 0) {
            return it;
        }
        CombiningIterator combiningIterator = new CombiningIterator(it, it2, it3);
        combiningIterator.mCurrentItem = t1;
        return combiningIterator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1> Iterator<T1> singleTwo(T1 t1, Iterator<? extends T1> it, Iterator<? extends T1> it2) {
        if (t1 == 0) {
            return two(it, it2);
        }
        CombiningIterator combiningIterator = new CombiningIterator(it, it2);
        combiningIterator.mCurrentItem = t1;
        return combiningIterator;
    }

    public static <T1> Iterator<T1> three(Iterator<? extends T1> it, Iterator<? extends T1> it2, Iterator<? extends T1> it3) {
        return new CombiningIterator(it, it2, it3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1> Iterator<T1> two(Iterator<? extends T1> it, Iterator<? extends T1> it2) {
        if (it.hasNext()) {
            return !it2.hasNext() ? it : new CombiningIterator(it, it2);
        }
        return it2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return getCurrentItem() != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T currentItem = getCurrentItem();
        if (currentItem != null) {
            this.mCurrentItem = null;
            return currentItem;
        }
        z0e.a();
        return null;
    }

    public CombiningIterator(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3) {
        this(it, it2, it3, null);
    }

    public CombiningIterator(Iterator<? extends T> it, Iterator<? extends T> it2) {
        this(it, it2, null, null);
    }

    public CombiningIterator(Iterator<? extends T> it, Iterator<Iterator<E>> it2, Void r3) {
        this(it, null, null, it2);
    }

    public CombiningIterator(Iterator<Iterator<E>> it) {
        this(null, null, null, it);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T1, E1 extends T1> Iterator<T1> of(Iterator<T1> it, Iterator<Iterator<E1>> it2) {
        if (!it2.hasNext()) {
            return it;
        }
        CombiningIterator combiningIterator = new CombiningIterator(it, null);
        combiningIterator.iteratorIterator = it2;
        return combiningIterator;
    }
}
