package org.jetbrains.kotlin.container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\b¨\u0006\t"}, d2 = {"topologicalSort", Argument.Delimiters.none, "T", "items", Argument.Delimiters.none, "reverseOrder", Argument.Delimiters.none, "dependencies", "Lkotlin/Function1;", "org.jetbrains.kotlin:container"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DataStructuresKt {
    public static final <T> List<T> topologicalSort(Iterable<? extends T> iterable, boolean z, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        iterable.getClass();
        function1.getClass();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList = new ArrayList();
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            topologicalSort$DfsVisit(hashSet2, hashSet, function1, arrayList, it.next());
        }
        if (!z) {
            CollectionsKt.reverse(arrayList);
        }
        return arrayList;
    }

    private static final <T> void topologicalSort$DfsVisit(HashSet<T> hashSet, HashSet<T> hashSet2, Function1<? super T, ? extends Iterable<? extends T>> function1, ArrayList<T> arrayList, T t) {
        if (hashSet.contains(t) || hashSet2.contains(t)) {
            return;
        }
        hashSet2.add(t);
        Iterator<T> it = ((Iterable) function1.invoke(t)).iterator();
        while (it.hasNext()) {
            topologicalSort$DfsVisit(hashSet, hashSet2, function1, arrayList, it.next());
        }
        hashSet2.remove(t);
        hashSet.add(t);
        arrayList.add(t);
    }

    public static /* synthetic */ List topologicalSort$default(Iterable iterable, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return topologicalSort(iterable, z, function1);
    }
}
