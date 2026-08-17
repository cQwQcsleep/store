package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstanceIterator<T> implements Iterator<T> {
    private final Predicate<? super T> filter;
    private final Class<T> instance;
    private final Iterator<?> iterator;
    private T mCurrent;

    public InstanceIterator(Iterator<?> it, Class<T> cls, Predicate<? super T> predicate) {
        this.iterator = it;
        this.instance = cls;
        this.filter = predicate;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.Object] */
    private T getCurrent() {
        T t = this.mCurrent;
        if (t != null) {
            return t;
        }
        Iterator<?> it = this.iterator;
        Class<T> cls = this.instance;
        Predicate<? super T> predicate = this.filter;
        while (it.hasNext()) {
            ?? r3 = (T) it.next();
            if (r3 != 0 && cls.isInstance(r3) && (predicate == null || predicate.test(r3))) {
                this.mCurrent = r3;
                return r3;
            }
        }
        return null;
    }

    public static <T1> Iterator<T1> of(Iterator<?> it, Class<T1> cls) {
        return !it.hasNext() ? EmptyIterator.of() : new InstanceIterator(it, cls);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return getCurrent() != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T current = getCurrent();
        if (current != null) {
            this.mCurrent = null;
            return current;
        }
        z0e.a();
        return null;
    }

    public InstanceIterator(Iterator<?> it, Class<T> cls) {
        this(it, cls, null);
    }

    public static <T1> Iterator<T1> of(Iterator<?> it, Class<T1> cls, Predicate<? super T1> predicate) {
        if (!it.hasNext()) {
            return EmptyIterator.of();
        }
        return new InstanceIterator(it, cls, predicate);
    }
}
