package org.jetbrains.kotlin.utils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aU\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u00062\u001d\u0010\b\u001a\u0019\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0006¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"topologicalSort", "", "A", "nodes", "", "reportCycle", "Lkotlin/Function1;", "", "dependencies", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class SortUtilsKt {
    public static final <A> List<A> topologicalSort(Iterable<? extends A> iterable, Function1 function1, Function1<? super A, ? extends Iterable<? extends A>> function2) {
        iterable.getClass();
        function1.getClass();
        function2.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Iterator<? extends A> it = iterable.iterator();
        while (it.hasNext()) {
            topologicalSort$visit(linkedHashSet2, linkedHashSet, function1, function2, it.next());
        }
        List<A> mutableList = kotlin.collections.CollectionsKt.toMutableList(linkedHashSet2);
        kotlin.collections.CollectionsKt.reverse(mutableList);
        return mutableList;
    }

    public static /* synthetic */ List topologicalSort$default(Iterable iterable, Function1 function1, Function1 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: org.jetbrains.kotlin.utils.SortUtilsKt.topologicalSort.1
                public final Void invoke(Object obj2) {
                    throw new IllegalStateException("Cannot compute a topological sort: The node " + obj2 + " is in a cycle.");
                }
            };
        }
        return topologicalSort(iterable, function1, function2);
    }

    private static final <A> void topologicalSort$visit(Set<A> set, Set<A> set2, Function1 function1, Function1<? super A, ? extends Iterable<? extends A>> function2, A a) {
        if (set.contains(a)) {
            return;
        }
        if (set2.contains(a)) {
            function1.invoke(a);
            wq6.a();
            return;
        }
        set2.add(a);
        Iterator it = ((Iterable) function2.invoke(a)).iterator();
        while (it.hasNext()) {
            topologicalSort$visit(set, set2, function1, function2, it.next());
        }
        set2.remove(a);
        set.add(a);
    }
}
