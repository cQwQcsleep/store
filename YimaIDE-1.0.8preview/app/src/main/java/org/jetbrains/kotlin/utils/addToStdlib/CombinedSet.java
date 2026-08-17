package org.jetbrains.kotlin.utils.addToStdlib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractSet;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007B)\b\u0016\u0012\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\b\"\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\tJ\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0082\u0004J\u0017\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0015R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u00020\r8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/utils/addToStdlib/CombinedSet;", "E", "Lkotlin/collections/AbstractSet;", "sets", HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/List;)V", HttpUrl.FRAGMENT_ENCODE_SET, "([Ljava/util/Set;)V", "getSets", "()Ljava/util/List;", "size", HttpUrl.FRAGMENT_ENCODE_SET, "getSize", "()I", "iterator", HttpUrl.FRAGMENT_ENCODE_SET, "contains", HttpUrl.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)Z", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CombinedSet<E> extends AbstractSet<E> {
    private final List<Set<E>> sets;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CombinedSet(Set<? extends E>... setArr) {
        this(ArraysKt.toList(setArr));
        setArr.getClass();
    }

    public boolean contains(Object element) {
        List<Set<E>> list = this.sets;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((Set) it.next()).contains(element)) {
                return true;
            }
        }
        return false;
    }

    public final List<Set<E>> getSets() {
        return this.sets;
    }

    public int getSize() {
        Iterator<T> it = this.sets.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((Set) it.next()).size();
        }
        return size;
    }

    public Iterator<E> iterator() {
        List<Set<E>> list = this.sets;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Set) it.next()).iterator());
        }
        return new ChainedIterator(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CombinedSet(List<? extends Set<? extends E>> list) {
        list.getClass();
        this.sets = list;
    }
}
