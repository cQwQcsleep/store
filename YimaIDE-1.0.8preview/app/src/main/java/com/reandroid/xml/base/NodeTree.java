package com.reandroid.xml.base;

import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.xml.base.Node;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NodeTree<T extends Node> extends Node {
    void add(int i, T t);

    boolean add(T t);

    void clear();

    default <T1 extends Node> boolean containsNodeWithType(Class<T1> cls) {
        return iterator(cls).hasNext();
    }

    default <T1 extends Node> int countNodeWithType(Class<T1> cls) {
        return CollectionUtil.count((Iterator<?>) iterator(cls));
    }

    T get(int i);

    Iterator<? extends T> iterator();

    default <T1 extends Node> Iterator<T1> iterator(Class<T1> cls, Predicate<? super T1> predicate) {
        return new InstanceIterator(iterator(), cls, predicate);
    }

    T remove(int i);

    boolean remove(T t);

    boolean removeIf(Predicate<? super T> predicate);

    int size();

    boolean sort(Comparator<? super T> comparator);

    default <T1 extends Node> Iterator<T1> iterator(Class<T1> cls) {
        return iterator(cls, null);
    }
}
