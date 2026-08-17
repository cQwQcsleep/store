package com.reandroid.utils.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UniqueIterator<T> extends FilterIterator<T> {
    private Set<T> excludeSet;

    public UniqueIterator(Iterator<T> it, Predicate<? super T> predicate) {
        super(it, predicate);
    }

    private T addExclude(T t) {
        if (t == null) {
            return null;
        }
        Set hashSet = this.excludeSet;
        if (hashSet == null) {
            hashSet = new HashSet();
            this.excludeSet = hashSet;
        }
        hashSet.add(t);
        return t;
    }

    public UniqueIterator<T> exclude(T t) {
        addExclude(t);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.utils.collection.FilterIterator, java.util.Iterator
    public T next() {
        return (T) addExclude(super.next());
    }

    @Override // com.reandroid.utils.collection.FilterIterator
    public void onFinished() {
        Set<T> set = this.excludeSet;
        if (set != null) {
            set.clear();
            this.excludeSet = null;
        }
    }

    @Override // com.reandroid.utils.collection.FilterIterator, java.util.function.Predicate
    public boolean test(T t) {
        if (t == null) {
            return false;
        }
        Set<T> set = this.excludeSet;
        return set == null || !set.contains(t);
    }

    public UniqueIterator(Iterator<T> it) {
        super(it);
    }
}
