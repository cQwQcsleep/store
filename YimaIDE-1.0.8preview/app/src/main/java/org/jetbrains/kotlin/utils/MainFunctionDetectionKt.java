package org.jetbrains.kotlin.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"pickMainFunctionFromCandidates", "T", "candidates", "", "convertToCandidate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/utils/MainFunctionCandidate;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:js.config"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class MainFunctionDetectionKt {
    public static final <T> T pickMainFunctionFromCandidates(List<? extends T> list, Function1<? super T, MainFunctionCandidate> function1) {
        T next;
        list.getClass();
        function1.getClass();
        List<? extends T> list2 = list;
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (T t : list2) {
            arrayList.add(TuplesKt.to(t, function1.invoke(t)));
        }
        Iterator<T> it = kotlin.collections.CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.utils.MainFunctionDetectionKt$pickMainFunctionFromCandidates$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t2, T t3) {
                return ComparisonsKt.compareValues(((MainFunctionCandidate) ((Pair) t2).getSecond()).getPackageFqn(), ((MainFunctionCandidate) ((Pair) t3).getSecond()).getPackageFqn());
            }
        }).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((MainFunctionCandidate) ((Pair) next).getSecond()).getMainFunctionTag() == null);
        Pair pair = (Pair) next;
        if (pair != null) {
            return (T) pair.getFirst();
        }
        return null;
    }
}
