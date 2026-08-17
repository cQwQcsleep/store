package org.jetbrains.kotlin.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001aF\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u0002H\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00040\u0006H\u0086\bø\u0001\u0000\u001a<\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\b2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u0006H\u0086\bø\u0001\u0000\u001a6\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"flatMapToNullableSet", "", "R", "T", "", "transform", "Lkotlin/Function1;", "mapToSetOrEmpty", "", "filterToSetOrEmpty", "predicate", "", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class SetUtilsKt {
    public static final <T> Set<T> filterToSetOrEmpty(Collection<? extends T> collection, Function1<? super T, Boolean> function1) {
        collection.getClass();
        function1.getClass();
        Collection linkedHashSet = new LinkedHashSet();
        for (T t : collection) {
            if (((Boolean) function1.invoke(t)).booleanValue()) {
                linkedHashSet.add(t);
            }
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    public static final <T, R> Set<R> flatMapToNullableSet(Iterable<? extends T> iterable, Function1<? super T, ? extends Iterable<? extends R>> function1) {
        iterable.getClass();
        function1.getClass();
        Collection linkedHashSet = new LinkedHashSet();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            Iterable iterable2 = (Iterable) function1.invoke(it.next());
            if (iterable2 == null) {
                return null;
            }
            kotlin.collections.CollectionsKt.addAll(linkedHashSet, iterable2);
        }
        if (linkedHashSet.isEmpty()) {
            linkedHashSet = SetsKt.emptySet();
        }
        return (Set) linkedHashSet;
    }

    public static final <T, R> Set<R> mapToSetOrEmpty(Collection<? extends T> collection, Function1<? super T, ? extends R> function1) {
        collection.getClass();
        function1.getClass();
        if (collection.isEmpty()) {
            return SetsKt.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(function1.invoke(it.next()));
        }
        return linkedHashSet;
    }
}
